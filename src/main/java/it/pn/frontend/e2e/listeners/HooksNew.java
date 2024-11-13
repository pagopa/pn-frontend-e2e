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
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.WebDriveBean;
import it.pn.frontend.e2e.config.DriverConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.config.WebDriverManager;
import it.pn.frontend.e2e.model.singleton.MandateSingleton;
import it.pn.frontend.e2e.rest.RestContact;
import it.pn.frontend.e2e.rest.RestDelegation;
import it.pn.frontend.e2e.utility.CookieConfig;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

public class HooksNew extends BasePage {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(HooksNew.class);

    private WebDriverWait wait;

    private DevTools devTools;

    private final Map<String, RequestWillBeSent> requests = new HashMap<>();
    @Getter
    public static String scenario;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    private WebDriverManager webDriveBean;


    @Getter
    private final List<NetWorkInfo> netWorkInfos = new ArrayList<>();

    private final String os = System.getProperty("os.name");
    @Autowired
    MandateSingleton mandateSingleton ;
    @Autowired
    private RestContact restContact;
    @Autowired
    private  CookieConfig cookieConfig;
    @Autowired
    private RestDelegation restDelegation ;


    @Before
    public void startScenario(Scenario scenario) {
        logger.info("----- START SCENARIO: {} -----", scenario.getName());
        driver = WebDriverManager.getDriver();

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
        webDriveBean.getNetWorkInfos().forEach(netWorkInfo -> {
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
        webDriveBean.clearRequest();
        webDriveBean.clearNetWorkInfos();
        logger.info("----- END SCENARIO: {} -----", scenario.getName());
    }

    @AfterEach
    public void closeWebDriver() {
        logger.info("###STOP FROM THE LIFECYCLE###");
        driver.quit();
        //WebDriverManager.quitDriver();
    }
/**
    @PreDestroy
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
 **/


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