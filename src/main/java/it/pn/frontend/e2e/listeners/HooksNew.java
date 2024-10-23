package it.pn.frontend.e2e.listeners;


import static java.lang.Boolean.TRUE;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import it.pn.frontend.e2e.model.singleton.MandateSingleton;
import it.pn.frontend.e2e.rest.RestContact;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.utility.CookieConfig;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.springframework.stereotype.Component;


public class HooksNew {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(HooksNew.class);

    @Value("${selenium.browser}")
    private String browser;

    @Value("${selenium.browser.headless}")
    private String headless;

    @Value("${selenium.browser.remote}")
    private String remote;

    @Value("${target.application.baseUrl}")
    private String baseUrl;

    @Getter
    public WebDriver driver;

    private WebDriverWait wait;


    private DevTools devTools;
    private final Map<String, RequestWillBeSent> requests = new HashMap<>();
    @Getter
    public static String scenario;

    public static   final List<NetWorkInfo> netWorkInfos = new ArrayList<>();
    private final CookieConfig cookieConfig = new CookieConfig();
    private final String os = System.getProperty("os.name");


    private void setupFirefox() {
        WebDriverManager.firefoxdriver().setup();
        var firefoxProfile = new FirefoxProfile();
        var firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.addArguments("-private");

        if (Boolean.parseBoolean(headless)) {
            firefoxOptions.addArguments("--width=1200", "--height=800", "--headless");
        }
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Firefox driver started");
    }

    private void setupChrome() {
        WebDriverManager.chromedriver().setup();
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=it", "--incognito", "--disable-dev-shm-usage", "--remote-allow-origins=*", "--enable-clipboard");
        var downloadFilePath = System.getProperty("downloadFilePath");
        var chromePrefs = Map.of("download.default_directory", downloadFilePath);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        if (Boolean.parseBoolean(this.headless)) {
            chromeOptions.addArguments("--no-sandbox", "--headless", "window-size=1920,1080");
        }

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        setupDevTools();
        logger.info("Chrome driver started");
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

    private void setupEdge() {
        if (this.os.toLowerCase().contains("windows")) {
            WebDriverManager.edgedriver().setup();
        } else {
            throw new UnsupportedOperationException("Edge browser is not supported on OS: " + this.os);
        }
        var edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("ms:inPrivate", true);
        if (Boolean.parseBoolean(this.headless)) {
            edgeOptions.addArguments("window-size=1920,1080", "--headless");
        }
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Edge driver started");
    }

    @Before
    public void startScenario(Scenario scenario) {
        logger.info("----- START SCENARIO: {} -----", scenario.getName());
        Hooks.scenario = scenario.getName();

        scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith("@TA_"))
                .forEach(tag -> {
                    MDC.put("tag", tag);
                    MDC.put("team", "TA-QA");
                });

        var browser = Optional.ofNullable(System.getProperty("browser"))
                .orElseThrow(() -> new IllegalArgumentException("Browser must be specified"));
        this.headless = System.getProperty("headless", "false");

        switch (browser) {
            case "firefox" -> setupFirefox();
            case "chrome" -> setupChrome();
            case "edge" -> setupEdge();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        cookieConfig.addCookie();

    }

    @After
    public void endScenario(Scenario scenario) throws IOException {
        System.clearProperty("IUN");
        netWorkInfos.forEach(netWorkInfo -> {
            logger.info("Request ID: {}", netWorkInfo.getRequestId());
            logger.info("Request URL: {}", netWorkInfo.getRequestUrl());
            logger.info("Method: {}", netWorkInfo.getRequestMethod());
            logger.info("Response Status: {}", netWorkInfo.getResponseStatus());
            logger.info("Response Body: {}", netWorkInfo.getResponseBody());
        });

        if (scenario.isFailed()) {
            logger.error("Scenario failed: {}", scenario.getName());
            var screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            var screenshotBytes = Files.readAllBytes(screenshot.toPath());
            var formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
            var timestamp = formatter.format(new Date());
            var fileName = "logs/" + scenario.getName() + "_" + timestamp + ".png";
            FileUtils.copyFile(screenshot, new File(fileName));
            scenario.attach(screenshotBytes, "image/png", scenario.getName());
        }

        driver.quit();
        requests.clear();
        netWorkInfos.clear();
        logger.info("----- END SCENARIO: {} -----", scenario.getName());
    }

    @After("@DeleghePF or @DeleghePG")
    @Before("@DeleghePF or @DeleghePG")
    @And("Revoca deleghe se esistono")
    public void clearDelegate() {
        logger.info("Revoking all delegations...");
        var mandateId = MandateSingleton.getInstance().getMandateId(Hooks.getScenario());
        if (mandateId != null) {
            RestDelegation.getInstance().revokeDelegation(mandateId);
            logger.info("Delegation revoked: {}", mandateId);
        } else {
            logger.info("Mandate ID not found");
        }
    }

    @After("@File")
    public void clearDirectory() {
        var folderPath = System.getProperty("downloadFilePath");
        var folder = new File(folderPath);
        if (folder.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                    .filter(File::isFile)
                    .forEach(file -> {
                        if (file.delete()) {
                            logger.info("Deleted file: {}", file.getAbsolutePath());
                        } else {
                            logger.warn("Failed to delete file: {}", file.getAbsolutePath());
                        }
                    });
        }
    }

    @After("@recapitiPF or @recapitiPG")
    @Before("@recapitiPF or @recapitiPG")
    @And("Rimuovi tutti i recapiti se esistono")
    public void clearRecapiti() {
        var restContact = RestContact.getInstance();
        var digitalAddresses = restContact.getAllDigitalAddress();
        if (digitalAddresses != null && !digitalAddresses.isEmpty()) {
            digitalAddresses.forEach(address -> {
                if ("default".equalsIgnoreCase(address.getSenderId())) {
                    if ("PEC".equalsIgnoreCase(address.getChannelType())) {
                        restContact.removeDigitalAddressLegalPec();
                    } else {
                        restContact.removeDigitalAddressCourtesyEmail();
                    }
                } else {
                    restContact.removeSpecialContact(address);
                }
            });
        }
    }




}