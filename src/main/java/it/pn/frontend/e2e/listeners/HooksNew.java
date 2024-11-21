package it.pn.frontend.e2e.listeners;


import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.config.WebDriverManager;
import it.pn.frontend.e2e.model.singleton.MandateSingleton;
import it.pn.frontend.e2e.rest.RestContact;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.utility.CookieConfig;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.network.model.RequestWillBeSent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

public class HooksNew {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(HooksNew.class);

    private WebDriverWait wait;

    private DevTools devTools;

    private final Map<String, RequestWillBeSent> requests = new HashMap<>();
    @Getter
    public static String scenario;
    @Getter
    private final List<NetWorkInfo> netWorkInfos = new ArrayList<>();

    private final String os = System.getProperty("os.name");
    @Autowired
    private MandateSingleton mandateSingleton ;
    @Autowired
    private RestContact restContact;
    @Autowired
    private  CookieConfig cookieConfig;
    @Autowired
    private RestDelegation restDelegation ;
    @Autowired
    private WebDriverManager webDriveManager;
    @Autowired
    private WebDriverConfig webDriverConfig;

    private WebDriver driver;

    @Before
    public void startScenario(Scenario scenario) {
        logger.info("----- START SCENARIO: {} -----", scenario.getName());
        driver =  WebDriverManager.getDriverThreadLocal().get();

        String language = (String) ((JavascriptExecutor) driver).executeScript("return navigator.language");
        logger.info("Lingua corrente: " + language);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HooksNew.scenario = scenario.getName();
        scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith("@TA_"))
                .forEach(tag -> {
                    MDC.put("tag", tag);
                    MDC.put("team", "TA-QA");
                });
    }




    @After
    public void endScenario(Scenario scenario) throws IOException {
        System.clearProperty("IUN");
        webDriveManager.getNetWorkInfos().forEach(netWorkInfo -> {
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

        WebDriverManager.quitDriver();
        webDriveManager.clearRequest();
        webDriveManager.clearNetWorkInfos();
        logger.info("----- END SCENARIO: {} -----", scenario.getName());
    }



    @And("Revoca deleghe se esistono")
    @After("@DeleghePF or @DeleghePG")
    public void clearDelegate() {
        logger.info("Revoking all delegations...");
        var mandateId = mandateSingleton.getMandateId(HooksNew.getScenario());
        if (mandateId != null) {
            restDelegation.revokeDelegation(mandateId);
            logger.info("Delegation revoked: {}", mandateId);
        } else {
            logger.info("Mandate ID not found");
        }
    }

    @After("@File")
    public void clearDirectory() {
        var folderPath = webDriverConfig.getDownloadFilePath();
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

    @And("Rimuovi tutti i recapiti se esistono")
    public void clearRecapiti() {
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