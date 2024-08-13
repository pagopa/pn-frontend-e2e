package it.pn.frontend.e2e.listeners;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.it.Ma;
import io.github.bonigarcia.wdm.WebDriverManager;
import it.pn.frontend.e2e.model.singleton.MandateSingleton;
import it.pn.frontend.e2e.model.address.DigitalAddressResponse;
import it.pn.frontend.e2e.rest.RestContact;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.utility.CookieConfig;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.DevToolsException;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Headers;
import org.openqa.selenium.devtools.v126.network.model.RequestWillBeSent;
import org.openqa.selenium.devtools.v126.network.model.ResourceType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger("Hooks");
    public static WebDriver driver;
    public DevTools devTools;
    public Map<String, RequestWillBeSent> requests =  new HashMap<>();;

    @Getter
    public static String scenario;
    public static List<NetWorkInfo> netWorkInfos = new ArrayList<>();
    private String headless;
    private final CookieConfig cookieConfig = new CookieConfig();
    private final String os = System.getProperty("os.name");

    protected void firefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        firefoxOptions.addArguments("-private");

        if (this.headless != null && this.headless.equalsIgnoreCase("true")) {
            firefoxOptions.addArguments("--width=1200");
            firefoxOptions.addArguments("--height=800");
            firefoxOptions.addArguments("--headless");
        }
        driver = new FirefoxDriver(firefoxOptions);
        if (this.headless != null && this.headless.equalsIgnoreCase("false")) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("firefox driver started");
    }

    protected void chrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=it");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--enable-clipboard");
        String downloadFilepath = System.getProperty("downloadFilePath");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", downloadFilepath);

        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        if (this.headless != null && this.headless.equalsIgnoreCase("true")) {
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("headless");
            chromeOptions.addArguments("window-size=1920,1080");
        }

        driver = new ChromeDriver(chromeOptions);
        if (this.headless != null && this.headless.equalsIgnoreCase("false")) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));

        this.captureHttpRequests();
        this.captureHttpResponse();
        logger.info("chromedriver started");
    }

    private void captureHttpRequests() {
        devTools.addListener(
                Network.requestWillBeSent(),
                request -> {
                    String url = request.getRequest().getUrl();
                    if (!cookieConfig.getCookies(url).isEmpty()) {
                        cookieConfig.getCookies(url).forEach(cookie -> driver.manage().addCookie(cookie));
                    }
                    requests.put(request.getRequestId().toString(), request);
                }
        );
    }

    public void captureHttpResponse() {
        devTools.addListener(
                Network.responseReceived(),
                response -> {
                    String requestId = response.getRequestId().toString();
                    if (requests.containsKey(requestId)) {
                        RequestWillBeSent request = requests.get(requestId);
                        Headers headers = request.getRequest().getHeaders();
                        if (response.getType().equals(ResourceType.XHR)) {
                            NetWorkInfo netWorkInfo = new NetWorkInfo();
                            if (headers.get("Authorization") != null) {
                                System.setProperty("token", Objects.requireNonNull(headers.get("Authorization")).toString());
                                netWorkInfo.setAuthorizationBearer(
                                        (Objects.requireNonNull(headers.get("Authorization"))).toString());
                            }
                            netWorkInfo.setRequestId(requestId);
                            netWorkInfo.setRequestUrl(request.getRequest().getUrl());
                            netWorkInfo.setRequestMethod(request.getRequest().getMethod());
                            netWorkInfo.setResponseStatus(response.getResponse().getStatus().toString());
                            try {
                                String bodyResponse = devTools.send(Network.getResponseBody(response.getRequestId())).getBody();
                                netWorkInfo.setResponseBody(bodyResponse);
                            } catch (DevToolsException ignored) {
                                // Ignored because the response body is not always available.
                            }
                            netWorkInfos.add(netWorkInfo);
                        }
                    }
                    requests.remove(requestId);
                }
        );
    }


    protected void edge() {
        if (this.os.toLowerCase().contains("windows")) {
            WebDriverManager.edgedriver().setup();
        } else {
            Assert.fail("browser edge non compatibile con il os : " + this.os);
        }
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("ms:inPrivate", true);
        if (this.headless != null && this.headless.equalsIgnoreCase("true")) {
            edgeOptions.addArguments("window-size=1920,1080");
            edgeOptions.addArguments("--headless");
        }
        driver = new EdgeDriver(edgeOptions);
        if (this.headless != null && this.headless.equalsIgnoreCase("false")) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("edge driver started");
    }

    @Before
    public void startScenario(Scenario scenario) {
        logger.info("-------------------------------------------START SCENARIO: " + scenario.getName() + "------------------------------------------------");
        this.scenario = scenario.getName();
        Collection<String> tags = scenario.getSourceTagNames();
        for (String tag : tags) {
            if (tag.startsWith("@TA_")) {
                MDC.put("tag", tag);
                MDC.put("team", "TA-QA");
            }
        }

        logger.info("os type : " + this.os);
        logger.info("user language : " + System.getProperty("user.language"));

        String browser = null;
        if (System.getProperty("browser") == null) {
            Assert.fail("valorizzare la variabile browser");
        } else {
            browser = System.getProperty("browser");
        }
        if (System.getProperty("headless") != null) {
            this.headless = System.getProperty("headless");
        }
        if (System.getProperty("environment") == null) {
            Assert.fail("valorizzare la variabile environment");
        }
        switch (browser) {
            case "firefox" -> firefox();
            case "chrome" -> chrome();
            case "edge" -> edge();
            default -> {
                logger.error("browser not correct");
                Assert.fail("browser not correct");
            }
        }
        cookieConfig.addCookie();
    }

    @After
    public void endScenario(Scenario scenario) {

        System.clearProperty("IUN");

        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            logger.info(netWorkInfo.getRequestId());
            logger.info(netWorkInfo.getRequestUrl());
            logger.info(netWorkInfo.getRequestMethod());
            logger.info(netWorkInfo.getResponseStatus());
            logger.info(netWorkInfo.getResponseBody());
        }

        if (scenario.isFailed()) {
            logger.error("scenario go to error : " + scenario.getName());
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                byte[] screenshotByte = FileUtils.readFileToByteArray(screenshot);
                Date date = Calendar.getInstance().getTime();
                DateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
                String today = formatter.format(date);
                String testCaseFailed = "logs/" + scenario.getName() + "_" + today + ".png";
                FileUtils.copyFile(screenshot, new File(testCaseFailed));
                scenario.attach(screenshotByte, "image/png", scenario.getName());
            } catch (IOException e) {
                logger.error(e.getCause().toString());
            }
        }

        logger.info("quit driver");
        driver.quit();
        requests.clear();
        netWorkInfos.clear();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("-------------------------------------------END SCENARIO: " + scenario.getName() + "------------------------------------------------");
    }

    /**
     * Clear the delegate of PF after the scenario
     * P.S: This will work only if you invoke the feature step that creates the delegate
     */
    @After("@DeleghePF or @DeleghePG")
    public void clearDelegate() {
        MandateSingleton mandateSingleton = MandateSingleton.getInstance();
        String mandateId =mandateSingleton.getMandateId(Hooks.getScenario());
        if (mandateId != null) {
            RestDelegation restDelegation = RestDelegation.getInstance();
            restDelegation.revokeDelegation(mandateId);
            logger.info("Delega revocata con successo");
        } else {
            logger.info("mandateId non trovato");
        }
    }

    /**
     * Clear directory of file downloaded
     * P.S: This will work only if you invoke the feature step that creates the delegate
     */
    @After("@File")
    public void clearDirectory() {
        String folderPath = System.getProperty("downloadFilePath");

        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    if (file.delete()) {
                        System.out.println("File cancellato: " + file.getAbsolutePath());
                    } else {
                        System.out.println("Impossibile cancellare il file: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * Clear the contacts of PF after the scenario
     * P.S: This will work only if there are any contacts available
     */
    @After(value = "@recapitiPF or @recapitiPG")
    public void clearRecapiti() {
        RestContact restContact = RestContact.getInstance();
        DigitalAddressResponse digitalAddress = restContact.getDigitalAddress();
        // Check for legal ones and remove them
        if (!digitalAddress.getLegal().isEmpty()) {
            digitalAddress.getLegal().forEach(address -> {
                if (address.getSenderId().equalsIgnoreCase("default")) {
                    restContact.removeDigitalAddressLegalPec();
                } else {
                    restContact.removeSpecialContact(address);
                }
            });
        }
        // Check for courtesy ones and remove them
        if (!digitalAddress.getCourtesy().isEmpty()) {
            digitalAddress.getCourtesy().forEach(address -> {
                if (address.getSenderId().equalsIgnoreCase("default")) {
                    restContact.removeDigitalAddressCourtesyEmail();
                } else {
                    restContact.removeSpecialContact(address);
                }
            });
        }
    }
}
