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
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.devtools.v126.network.model.RequestWillBeSent;
import org.openqa.selenium.devtools.v138.network.model.RequestWillBeSent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HooksNew {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(HooksNew.class);

    private WebDriverWait wait;

    private final Map<String, RequestWillBeSent> requests = new HashMap<>();
    @Getter
    @Setter
    public  String scenario;

    private final String os = System.getProperty("os.name");
    @Autowired
    private MandateSingleton mandateSingleton;
    @Autowired
    private RestContact restContact;
    @Autowired
    private CookieConfig cookieConfig;
    @Autowired
    private RestDelegation restDelegation;
    @Autowired
    private WebDriverManager webDriveManager;
    @Autowired
    private WebDriverConfig webDriverConfig;


    @Before
    public void startScenario(Scenario scenario) {
        logger.info("----- START SCENARIO: {} -----", scenario.getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // WebDriverManager.getDriverThreadLocal().get();

        setScenario(scenario.getName());
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

        webDriveManager.getNetworkInfosThread().get().forEach(netWorkInfo -> {
            logger.info("Request ID: {}", netWorkInfo.getRequestId());
            logger.info("Request URL: {}", netWorkInfo.getRequestUrl());
            logger.info("Method: {}", netWorkInfo.getRequestMethod());
            logger.info("Response Status: {}", netWorkInfo.getResponseStatus());
            logger.info("Response Body: {}", netWorkInfo.getResponseBody());
        });

        if (scenario.isFailed()) {
            try {
                logger.error("Scenario failed: {}", scenario.getName());
                var screenshot = ((TakesScreenshot) webDriveManager.getDriverThreadLocal().get()).getScreenshotAs(OutputType.FILE);
                var screenshotBytes = Files.readAllBytes(screenshot.toPath());
                var formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
                var timestamp = formatter.format(new Date());
                var fileName = "logs/" + scenario.getName() + "_" + timestamp + ".png";
                FileUtils.copyFile(screenshot, new File(fileName));
                scenario.attach(screenshotBytes, "image/png", scenario.getName());
            } catch (IOException e) {
                logger.error("Failed to take screenshot: {}", e.getMessage());
            }
        }

        try {
            webDriveManager.quitDriver();
        } catch (Exception e) {
            logger.error("Error while quitting driver: {}", e.getMessage());
        }
        try {
            webDriveManager.clearRequest();
            webDriveManager.clearNetWorkInfos();
        } catch (Exception e) {
            logger.error("Error while clearing network infos: {}", e.getMessage());
        }
        logger.info("----- END SCENARIO: {} -----", scenario.getName());
    }


    /**
     * @After public void endScenario(Scenario scenario) throws IOException {
     * <p>
     * <p>
     * <p>
     * <p>
     * System.clearProperty("IUN");
     * NetworkInfoManager.getNetworkInfo().forEach(netWorkInfo -> {
     * logger.info("Request ID: {}", netWorkInfo.getRequestId());
     * logger.info("Request URL: {}", netWorkInfo.getRequestUrl());
     * logger.info("Method: {}", netWorkInfo.getRequestMethod());
     * logger.info("Response Status: {}", netWorkInfo.getResponseStatus());
     * logger.info("Response Body: {}", netWorkInfo.getResponseBody());
     * });
     * <p>
     * if (scenario.isFailed()) {
     * logger.error("Scenario failed: {}", scenario.getName());
     * var screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
     * var screenshotBytes = Files.readAllBytes(screenshot.toPath());
     * var formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
     * var timestamp = formatter.format(new Date());
     * var fileName = "logs/" + scenario.getName() + "_" + timestamp + ".png";
     * FileUtils.copyFile(screenshot, new File(fileName));
     * scenario.attach(screenshotBytes, "image/png", scenario.getName());
     * }
     * <p>
     * try {
     * webDriveManager.quitDriver();
     * } catch (Exception e) {
     * logger.error("Error while quitting driver: {}", e.getMessage());
     * }
     * try {
     * webDriveManager.clearRequest();
     * webDriveManager.clearNetWorkInfos();
     * } catch (Exception e) {
     * logger.error("Error while clearing network infos: {}", e.getMessage());
     * }
     * <p>
     * <p>
     * logger.info("----- END SCENARIO: {} -----", scenario.getName());
     * }
     **/

    /**
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
     **/


    @After("@DeleghePF")
    @And("Revoca deleghe PF se esistono")
    public void clearDelegatePF() {
        logger.info("Revoking all delegations...");
        var mandateId = mandateSingleton.getMandateId(getScenario());
        if (mandateId != null) {
            restDelegation.revokeDelegation(mandateId);
            logger.info("Delegation revoked: {}", mandateId);
        } else {
            logger.info("Mandate ID not found");
        }
    }

    @After("@DeleghePG")
    @And("Revoca deleghe PG se esistono")
    public void clearDelegatePG() {
        logger.info("Revoking all delegations...");
        var mandateId = mandateSingleton.getMandateId(getScenario());
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
                    if ("LEGAL".equalsIgnoreCase(address.getAddressType())) {
                        if ("SERCQ_SEND".equalsIgnoreCase(address.getChannelType())) {
                            restContact.removeDigitalAddressLegalSend();
                        }
                        else if ("PEC".equalsIgnoreCase(address.getChannelType())) {
                            restContact.removeDigitalAddressLegalPec();
                        }
                    }
                    else {
                        if ("EMAIL".equalsIgnoreCase(address.getChannelType())) {
                            restContact.removeDigitalAddressCourtesyEmail();
                        }
                        else if ("SMS".equalsIgnoreCase(address.getChannelType())) {
                            restContact.removeDigitalAddressSms();
                        }
                    }
                } else {
                    restContact.removeSpecialContact(address);
                }
            });
        }
    }

}