package it.pn.frontend.e2e.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.utility.CookieConfig;
import lombok.Getter;
import lombok.Setter;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import java.time.Duration;
import java.util.*;

/*
*Modifiche principali:
Iniezione di WebDriver: Ho rimosso tutte le istanze di new ChromeDriver(), new FirefoxDriver(), ecc., e ho creato un bean in una classe separata (che andremo a configurare subito dopo) che gestisce la creazione del WebDriver.

Rimozione di new per altre dipendenze: Ho sostituito la creazione di oggetti come CookieConfig, RestDelegation e RestContact con l'iniezione di dipendenze utilizzando @Autowired.

Configurazione di un WebDriver come Bean
*
*
* */
@Getter
@Configuration
@PropertySource( value = "file:config/configuration.properties", ignoreResourceNotFound = true )
public class WebDriverConfig {

    @Value("${browser}")
    private String browser;

    @Value("${environment}")
    private String environment;

    @Value("${cookie.config}")
    private String cookie;

    @Value("${headless}")
    @Setter
    private String headless;

    @Value("${downloadFilePath}")
    private String downloadFilePath;

    @Value("${loadComponentWaitTime}")
    private String loadComponentWaitTime;

    @Value("${apiBaseUrl}")
    private String baseUrl;

    @Value("${ragione.sociale.baldassarre}")
    private String ragioneSocialeBaldassarre;

    @Value("${url.login.pg.dev}")
    private String baseUrlPgDev;

    @Value("${url.login.pg.test}")
    private String baseUrlPgTest;

    @Value("${url.login.pf.dev}")
    private String baseUrlPfDev;

    @Value("${url.login.pf.test}")
    private String baseUrlPfTest;

    @Value("${pn.bearer-token.tokentestPFDelegante}")
    private String tokentestPFDelegante;

    @Value("${ pn.bearer-token.tokendevPFDelegante}")
    private String tokendevPFDelegante;

    @Value("${pn.bearer-token.tokentestPFDelegato}")
    private String tokentestPFDelegato;

    @Value("${pn.bearer-token.tokendevPFDelegato}")
    private String tokendevPFDelegato;

    @Value("${pn.bearer-token.tokentestPGDelegante}")
    private String tokentestPGDelegante;

    @Value("${pn.bearer-token.tokendevPGDelegante}")
    private String tokendevPGDelegante;

    @Value("${pn.bearer-token.tokentestPGDelegato}")
    private String tokentestPGDelegato;

    @Value("${pn.bearer-token.tokendevPGDelegato}")
    private String tokendevPGDelegato;

    @Value("${pn.bearer-token.tokentestMittente}")
    private String tokentestMittente;

    @Value("${pn.bearer-token.tokendevMittente}")
    private String tokendevMittente;

    @Value("${pn.bearer-token.tokentestPFColombo}")
    private String tokentestPFColombo;

    @Value("${pn.bearer-token.tokentestRaddista1}")
    private String tokentestRaddista1;

    @Value("${url.selfcare}")
    private String urlSelfCare;

    @Value("${pn.user.cesare}")
    private String userCesare;

    @Value("${pn.pwd.cesare}")
    private String pwdCesare;

    @Value("${pn.user.lucrezia}")
    private String userLucrezia;

    @Value("${pn.pwd.lucrezia}")
    private String pwdLucrezia;

    @Value("${pn.user.dante}")
    private String userDante;

    @Value("${pn.pwd.dante}")
    private String pwdDante;

    @Value("${pn.user.petrarca}")
    private String userPetrarca;

    @Value("${pn.pwd.petrarca}")
    private String pwdPetrarca;

    @Value("${pn.user.mittente}")
    private String userMittente;

    @Value("${pn.pwd.mittente}")
    private String pwdMittente;

    @Value("${pn.userDev.helpdesk}")
    private String userDevHelpdesk;

    @Value("${pn.pwdDev.helpdesk}")
    private String pwdDevHelpdesk;

    @Value("${pn.userUat.helpdesk}")
    private String userUatHelpdesk;

    @Value("${pn.pwdUat.helpdesk}")
    private String pwdUatHelpdesk;

    @Value("${pn.userTest.helpdesk}")
    private String userTestHelpdesk;

    @Value("${pn.pwdTest.helpdesk}")
    private String pwdTestHelpdesk;

    @Value("${url.mittente}")
    private String urlMittente;

    private final Map<String, RequestWillBeSent> requests = new HashMap<>();

    @Getter
    private final List<NetWorkInfo> netWorkInfos = new ArrayList<>();

    private final String os = System.getProperty("os.name");

    private DevTools devTools;


    @Getter
    public WebDriver driver;

    @Autowired
    @Getter
    public CookieConfig cookieConfig;

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(WebDriverConfig.class);


    @Bean
    public WebDriver webDriver() {

        var browser = Optional.ofNullable(getBrowser())
                .orElseThrow(() -> new IllegalArgumentException("Browser must be specified"));
        // var browser = Optional.ofNullable(System.getProperty("browser"))
        //        .orElseThrow(() -> new IllegalArgumentException("Browser must be specified"));
        // this.headless = System.getProperty("headless", "false");
        // webDriverConfig.setHeadless(System.getProperty("headless", "false"));
        // webDriverConfig.setHeadless("false");
        switch (browser) {
            case "firefox" -> setupFirefox();
            case "chrome" ->  setupChrome();
            case "edge" ->    setupEdge();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        cookieConfig.addCookie();

        return driver;
    }


    public void setupFirefox() {
        WebDriverManager.firefoxdriver().setup();
        var firefoxProfile = new FirefoxProfile();
        var firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.addArguments("-private");

        if (Boolean.parseBoolean(getHeadless())) {
            firefoxOptions.addArguments("--width=1200", "--height=800", "--headless");
        }
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Firefox driver started");
    }

    public void setupChrome() {
        WebDriverManager.chromedriver().setup();
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=it", "--incognito", "--disable-dev-shm-usage", "--remote-allow-origins=*", "--enable-clipboard");
        var downloadFilePath = getDownloadFilePath();
        // var downloadFilePath = System.getProperty("downloadFilePath");
        var chromePrefs = Map.of("download.default_directory", downloadFilePath);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        if (Boolean.parseBoolean(getHeadless())) {
            chromeOptions.addArguments("--no-sandbox", "--headless", "window-size=1920,1080");
        }

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        setupDevTools();
        logger.info("Chrome driver started");
    }

    public void setupEdge() {
        if (this.os.toLowerCase().contains("windows")) {
            WebDriverManager.edgedriver().setup();
        } else {
            throw new UnsupportedOperationException("Edge browser is not supported on OS: " + this.os);
        }
        var edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("ms:inPrivate", true);
        if (Boolean.parseBoolean(getHeadless())) {
            edgeOptions.addArguments("window-size=1920,1080", "--headless");
        }
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Edge driver started");
    }


    private void setupDevTools() {
        devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        captureHttpRequests();
        captureHttpResponse();
    }

    private void captureHttpRequests() {
        devTools.addListener(Network.requestWillBeSent(), request -> {
            var url = request.getRequest().getUrl();
            cookieConfig.getCookies(url).forEach(cookie -> driver.manage().addCookie(cookie));
            requests.put(request.getRequestId().toString(), request);
        });
    }

    private void captureHttpResponse() {
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
                    logger.info("NET_INFO: "+netWorkInfo.getRequestUrl());
                    netWorkInfos.add(netWorkInfo);
                }
            }
            requests.remove(requestId);
        });
    }

    public void clearRequest (){
        requests.clear();
    }
    public void clearNetWorkInfos (){
        netWorkInfos.clear();
    }

}
