package it.pn.frontend.e2e.config;

import it.pn.frontend.e2e.common.WebdriverScopeBean;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.utility.CookieConfig;
import lombok.Getter;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.RequestWillBeSent;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Configuration
@Getter
public class WebDriverManager {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(WebDriverManager.class);

    @Getter
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @Getter
    private static final ThreadLocal<DevTools> devToolsThread = new ThreadLocal<>();

    @Getter
    private static final ThreadLocal<List<NetWorkInfo>> networkInfosThread = ThreadLocal.withInitial(ArrayList::new);

    private final Map<String, RequestWillBeSent> requests = new HashMap<>();

    private WebDriver driver;

    @Autowired
    @Lazy
    private WebDriverConfig webDriverConfig;

    @Autowired
    public CookieConfig cookieConfig;

    @Getter
    private List<NetWorkInfo> netWorkInfos = new ArrayList<>();

    private final String os = System.getProperty("os.name");

    private DevTools devTools;


    @WebdriverScopeBean
    @Primary
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @ConditionalOnProperty(name = "browser", havingValue = "chrome", matchIfMissing = true)
    public WebDriver chromeDriver() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("NUOVO BEAN......." + Math.random());
        var browser = Optional.ofNullable(webDriverConfig.getBrowser())
                .orElseThrow(() -> new IllegalArgumentException("Browser must be specified"));
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=it", "--incognito", "--disable-dev-shm-usage", "--remote-allow-origins=*", "--enable-clipboard", "--disable-geolocation");

        var downloadFilePath = webDriverConfig.getDownloadFilePath();
        // var downloadFilePath = System.getProperty("downloadFilePath");
        var chromePrefs = Map.of("download.default_directory", downloadFilePath, "intl.accept_languages", "it,it-IT");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--user-data-dir=/path/to/unique/profile" + Thread.currentThread().getId());

        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disk-cache-size=0");
        chromeOptions.addArguments("--disable-cache");

        if (Boolean.parseBoolean(webDriverConfig.getHeadless())) {
            chromeOptions.addArguments("--no-sandbox", "--headless", "window-size=1920,1080");
        }

        driver = getDriver(chromeOptions, null, null);

        setupDevTools();

        logger.info("Chrome driver started - WebDriverManager");

        cookieConfig.addCookie();


        return driver;
    }

    @WebdriverScopeBean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    public WebDriver webDriverEdge() {

        var browser = Optional.ofNullable(webDriverConfig.getBrowser())
                .orElseThrow(() -> new IllegalArgumentException("Browser must be specified"));
        if (this.os.toLowerCase().contains("windows")) {
            io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
        } else {
            throw new UnsupportedOperationException("Edge browser is not supported on OS: " + this.os);
        }
        var edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("ms:inPrivate", true);
        if (Boolean.parseBoolean(webDriverConfig.getHeadless())) {
            edgeOptions.addArguments("window-size=1920,1080", "--headless");
        }

        driver = getDriver(null, edgeOptions, null);

        cookieConfig.addCookie();

        return driver;
    }

    @WebdriverScopeBean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver webDriverFirefox() {

        var browser = Optional.ofNullable(webDriverConfig.getBrowser())
                .orElseThrow(() -> new IllegalArgumentException("Browser must be specified"));
        io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
        var firefoxProfile = new FirefoxProfile();
        var firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.addArguments("-private");

        if (Boolean.parseBoolean(webDriverConfig.getHeadless())) {
            firefoxOptions.addArguments("--width=1200", "--height=800", "--headless");
        }

        driver = getDriver(null, null, firefoxOptions);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cookieConfig.addCookie();

        return driver;
    }


    private void setupDevTools() {
        //devTools = ((HasDevTools) driver).getDevTools();
        //devTools.createSession();
        // devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        captureHttpRequests();
        captureHttpResponse();
    }

    private void captureHttpRequests() {
        devTools = devToolsThread.get();
        devTools.addListener(Network.requestWillBeSent(), request -> {
            try {
                // Safely access the request properties
                if (request != null && request.getRequest() != null) {
                    var url = request.getRequest().getUrl();
                    cookieConfig.getCookies(url).forEach(cookie -> driver.manage().addCookie(cookie));
                    requests.put(request.getRequestId().toString(), request);
                    logger.info("Request URL: " + request.getRequest().getUrl());
                } else {
                    logger.info("Received a null event or request object.");
                }
            } catch (Exception e) {
                logger.error("Error processing the request: " + e.getMessage());
            }
        });

        // Aspetta per vedere tutte le richieste di rete
        /**
         try {
         Thread.sleep(5000);
         } catch (InterruptedException e) {
         throw new RuntimeException(e);
         }
         **/

    }

    private void captureHttpResponse() {
        devTools = devToolsThread.get();
        netWorkInfos = networkInfosThread.get();
        devTools.addListener(Network.responseReceived(), response -> {
            var requestId = response.getRequestId().toString();
            if (requests.containsKey(requestId)) {
                var request = requests.get(requestId);
                var headers = request.getRequest().getHeaders();

                // Controlla il tipo di risorsa come stringa "XHR"
                if ("XHR".equals(response.getType().toString())) {
                    var netWorkInfo = new NetWorkInfo();
                    if (headers.get("Authorization") != null) {
                        var authHeader = headers.get("Authorization").toString();
                        System.setProperty("token", authHeader);
                        netWorkInfo.setAuthorizationBearer(authHeader);
                    }
                    netWorkInfo.setRequestId(requestId);
                    netWorkInfo.setRequestUrl(request.getRequest().getUrl());
                    netWorkInfo.setRequestMethod(request.getRequest().getMethod());
                    netWorkInfo.setResponseStatus(response.getResponse().getStatus().toString());

                    try {
                        var bodyResponse = devTools.send(Network.getResponseBody(response.getRequestId())).getBody();
                        netWorkInfo.setResponseBody(bodyResponse);
                    } catch (Exception ignored) {
                        // Ignorato perché non sempre è disponibile il body della risposta
                    }
                    logger.info("NET_INFO: " + netWorkInfo.getRequestUrl());

                    netWorkInfos.add(netWorkInfo);
                    networkInfosThread.set(netWorkInfos);
                }
            }
            requests.remove(requestId);
        });
    }

    public void clearRequest() {
        requests.clear();
    }

    public void clearNetWorkInfos() {
        networkInfosThread.get().clear();
    }


    public static DevTools getDevTools() {
        logger.info("DEV_TOOLS...." + devToolsThread.get().toString());
        return devToolsThread.get();
    }


    public static WebDriver getDriver(ChromeOptions chromeOptions, EdgeOptions edgeOptions, FirefoxOptions firefoxOptions) {
        if (driverThreadLocal.get() == null) {

            if (chromeOptions != null) {
                ChromeDriver driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

                driverThreadLocal.set(driver);

                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                //devTools = getDevTools();
                devTools.createSession();
                devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
                devToolsThread.set(devTools);

                logger.info("Chrome driver started");
            } else if (edgeOptions != null) {
                EdgeDriver driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                logger.info("Edge driver started");

                driverThreadLocal.set(driver);
            } else if (firefoxOptions != null) {
                FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                logger.info("Firefox driver started");

                driverThreadLocal.set(driver);
            }
        }
        logger.info("Start WebDriverManager..." + driverThreadLocal.get());
        return driverThreadLocal.get();
    }


    public static void quitDriver() {
        logger.info("Quit WebDriverManager..." + driverThreadLocal.get());
        logger.info("Quit DevTools..." + devToolsThread.get());
        WebDriver driver = driverThreadLocal.get();
        DevTools devTools = devToolsThread.get();
        Set<Cookie> cookies = CookieConfig.getThreadLocalCookies().get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            if (devTools != null) {
                devToolsThread.remove();
            }
            /**
            if (cookies != null) {
                CookieConfig.getThreadLocalCookies().remove();
            }
             **/
        }
    }

    //TODO Rivedere....
    public boolean waitForApiCall(String apiEndpoint, Duration timeout) {
        CountDownLatch latch = new CountDownLatch(1);

        // Listener per le richieste inviate
        AtomicBoolean requestCaptured = new AtomicBoolean(false);
        devTools.addListener(Network.requestWillBeSent(), request -> {
            if (request.getRequest().getUrl().contains(apiEndpoint)) {
                System.out.println("API request captured: " + request.getRequest().getUrl());
                requestCaptured.set(true);
                latch.countDown(); // Segnala che la richiesta è stata trovata
            }
        });

        try {
            // Aspetta che il latch venga rilasciato o scada il timeout
            boolean completed = latch.await(timeout.toSeconds(), TimeUnit.SECONDS);
            if (!completed) {
                throw new TimeoutException("Timeout waiting for API call: " + apiEndpoint);
            }
        } catch (InterruptedException | TimeoutException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }

        return requestCaptured.get();
    }

    private static Map<Long, Set<Cookie>> cookieStore = new ConcurrentHashMap<>();

    public void saveCookies(String url, WebDriver driver) {
        // cookieConfig.getCookies(url).forEach(cookie -> driver.manage().addCookie(cookie));
        Set<Cookie> cookies = cookieConfig.getCookies(url);
        cookieStore.put(Thread.currentThread().getId(), cookies);
    }

    public void loadCookies(String url, WebDriver driver) {
        Set<Cookie> cookies = cookieStore.get(Thread.currentThread().getId());
        if (cookies != null) {
            cookies.forEach(cookie -> driver.manage().addCookie(cookie));
        }
    }


}