package it.pn.frontend.e2e.listeners;

import io.cucumber.java.Scenario;
import it.pn.frontend.e2e.model.address.DigitalAddress;
import it.pn.frontend.e2e.model.singleton.MandateSingleton;
import it.pn.frontend.e2e.rest.RestContact;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.utility.CookieConfig;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.DevToolsException;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.RequestWillBeSent;
import org.openqa.selenium.devtools.v126.network.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
* Modifiche principali:
Iniezione di WebDriver: Ho rimosso tutte le istanze di new ChromeDriver(), new FirefoxDriver(), ecc., e ho creato un bean in una classe separata (che andremo a configurare subito dopo) che gestisce la creazione del WebDriver.

Rimozione di new per altre dipendenze: Ho sostituito la creazione di oggetti come CookieConfig, RestDelegation e RestContact con l'iniezione di dipendenze utilizzando @Autowired.*/

@Component
public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);



    @Autowired
    private CookieConfig cookieConfig;

    @Autowired
    private RestDelegation restDelegation;

    @Autowired
    private RestContact restContact;

    private DevTools devTools;
    private Map<String, RequestWillBeSent> requests = new HashMap<>();

    @Getter
    public static String scenario;

    @Value("${test.headless:false}")
    private String headless;

    @Value("${downloadFilePath}")
    private String downloadFilePath;

    @Value("${os.name}")
    private String os;
    public static  WebDriver driver;

    public static List<NetWorkInfo> netWorkInfos = new ArrayList<>();

    private void configureChromeOptions(ChromeOptions chromeOptions) {
        chromeOptions.addArguments("--lang=it");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--enable-clipboard");

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadFilePath);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        if (this.headless.equalsIgnoreCase("true")) {
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("headless");
            chromeOptions.addArguments("window-size=1920,1080");
        }
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

    private void captureHttpResponse() {
        devTools.addListener(
                Network.responseReceived(),
                response -> {
                    String requestId = response.getRequestId().toString();
                    if (requests.containsKey(requestId)) {
                        RequestWillBeSent request = requests.get(requestId);
                        if (response.getType().equals(ResourceType.XHR)) {
                            NetWorkInfo netWorkInfo = new NetWorkInfo();
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

    // @Before
    public void startScenario(Scenario scenario) {
        logger.info("-------------------------------------------START SCENARIO: " + scenario.getName() + "------------------------------------------------");
        Hooks.scenario = scenario.getName();
        Collection<String> tags = scenario.getSourceTagNames();
        for (String tag : tags) {
            if (tag.startsWith("@TA_")) {
                MDC.put("tag", tag);
                MDC.put("team", "TA-QA");
            }
        }

        logger.info("os type : " + this.os);
        logger.info("user language : " + System.getProperty("user.language"));

        devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));

        this.captureHttpRequests();
        this.captureHttpResponse();
        cookieConfig.addCookie();
    }


    //@After
    public void endScenario(Scenario scenario) throws IOException {
        System.clearProperty("IUN");
        netWorkInfos.forEach(netWorkInfo -> {
            logger.info("Request ID: {}", netWorkInfo.getRequestId());
            logger.info("Request URL: {}", netWorkInfo.getRequestUrl());
            logger.info("Method: {}", netWorkInfo.getRequestMethod());
            logger.info("Response Status: {}", netWorkInfo.getResponseStatus());
            logger.info("Response Body: {}", netWorkInfo.getResponseBody());
        });

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
    }


    //  @After("@DeleghePF or @DeleghePG")
    // @Before("@DeleghePF or @DeleghePG")
    //  @And("Revoca deleghe se esistono")
    public void clearDelegate() {
        logger.info("REVOCA TUTTE LE DELEGHE....");
        MandateSingleton mandateSingleton = MandateSingleton.getInstance();
        String mandateId = mandateSingleton.getMandateId(Hooks.getScenario());
        if (mandateId != null) {
            logger.info("REVOCA DELEGA: " + mandateId);
            restDelegation.revokeDelegation(mandateId);
            logger.info("Delega revocata con successo");
        } else {
            logger.info("mandateId non trovato");
        }
    }

    //@After("@File")
    public void clearDirectory() {
        File folder = new File(downloadFilePath);

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    if (file.delete()) {
                        logger.info("File cancellato: " + file.getAbsolutePath());
                    } else {
                        logger.info("Impossibile cancellare il file: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }


    //   @After("@recapitiPF or @recapitiPG")
    //   @Before("@recapitiPF or @recapitiPG")
    //   @And("Rimuovi tutti i recapiti se esistono")
    public void clearRecapiti() throws IOException {

        RestContact restContact = RestContact.getInstance();
        List<DigitalAddress> digitalAddress = restContact.getAllDigitalAddress();

        // Check for legal ones and remove them
        if (digitalAddress != null) {
            logger.info("SENDER DIGITAL ADDRESS...." + digitalAddress);
            logger.info("SENDER DIGITAL ADDRESS...." + digitalAddress.size());
            digitalAddress.forEach(addressDigital -> {
                logger.info("SENDER_ID: " + addressDigital.getSenderId());
                if (addressDigital.getSenderId().equalsIgnoreCase("default")) {

                    if ("PEC".equalsIgnoreCase(addressDigital.getChannelType())) {
                        logger.info("Remove Digital Address LegalPec: " + addressDigital.getSenderId());
                        restContact.removeDigitalAddressLegalPec();
                    } else {
                        logger.info("Remove Digital Address Courtesy Email: " + addressDigital.getSenderId());
                        restContact.removeDigitalAddressCourtesyEmail();
                    }
                } else {
                    logger.info("Remove Special Contact: " + addressDigital.getSenderId());
                    restContact.removeSpecialContact(addressDigital);
                }
            });
        }
    }
}
