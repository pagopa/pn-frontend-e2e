package it.pn.frontend.e2e.run;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"it/pn/frontend/e2e/listeners", "it/pn/frontend/e2e/stepDefinitions"},
        //tags = "@runner",
        //plugin = { "pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
        plugin = {"pretty",
                "json:target/cucumber-reports/report.json",
                "html:target/cucumber-reports/report.html",
                "junit:target/cucumber-reports/Cucumber.xml"
        },
        monochrome = true
)

public class RunCucumberTest {
    private static final String TEST_CONFIG_FILE = "test-config.properties";
    private static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger("RunCucumberTest");
    private static String testSuite;

    private static boolean loadProperties() {
        logger.info("load properties");
        try (InputStream input = RunCucumberTest.class.getClassLoader().getResourceAsStream(TEST_CONFIG_FILE)) {
            if (input != null) {
                properties.load(input);
                logger.info(properties.getProperty("browser"));
                return true;
            } else {
                System.err.println("Unable to find " + TEST_CONFIG_FILE);
                return false;
            }
        } catch (IOException e) {
            logger.info("Exception: " + e.getMessage());
        }
        return false;
    }

    private static void setApiBaseUrl() {
        String baseUrlProperty = System.getProperty("apiBaseUrl");
        String env = System.getProperty("environment");
        if (baseUrlProperty == null || env == null) {
            logger.error("apiBaseUrl property not set");
            return;
        }
        String apiBaseUrl = baseUrlProperty.replace("{ENV}", env);
        logger.info("apiBaseUrl = " + apiBaseUrl);
        System.setProperty("apiBaseUrl", apiBaseUrl);
    }

    private static void setUpDownloadFolder() {
        String downloadDir = System.getProperty("downloadFolder");
        Path fullPath = Paths.get(System.getProperty("user.dir"), downloadDir.replace("/", File.separator));
        if (Files.notExists(fullPath)) {
            try {
                Files.createDirectories(fullPath);
                logger.info("Download directory created: " + fullPath.toAbsolutePath());
            } catch (IOException e) {
                logger.error("Error creating download folder: " + fullPath.toAbsolutePath(), e);
            }
        } else {
            logger.info("Download directory already exists: " + fullPath.toAbsolutePath());
        }
    }

    private static void removeDownloadFolder() {
        String downloadDir = System.getProperty("downloadFolder", "/src/test/resources/dataPopulation/downloadFileNotifica");
        Path fullPath = Paths.get(System.getProperty("user.dir"), downloadDir.replace("/", File.separator));
        if (Files.exists(fullPath)) {
            try {
                deleteRecursively(fullPath);
                logger.info("Download directory deleted: " + fullPath.toAbsolutePath());
            } catch (IOException e) {
                logger.error("Error deleting download folder: " + fullPath.toAbsolutePath(), e);
            }
        } else {
            logger.info("Download directory does not exist: " + fullPath.toAbsolutePath());
        }
    }

    private static void deleteRecursively(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteRecursively(entry);
                }
            }
        }
        Files.delete(path);
    }

    @BeforeClass
    public static void startTestSuite() {
        setApiBaseUrl();
        if (loadProperties()) {
            logger.info("properties loaded");
            properties.forEach((property, value) -> {
                        String actualSystemProperty = System.getProperty(property.toString());
                        if (actualSystemProperty == null) {
                            System.setProperty(property.toString(), value.toString());
                        }
                    }
            );
        }
        setUpDownloadFolder();
        if (System.getProperty("cucumber.filter.tags") != null) {
            testSuite = System.getProperty("cucumber.filter.tags").substring(1);
            logger.info("run test della test suite = " + testSuite);
        } else {
            logger.info("run tutti i test");
        }
        logger.info("start the test suite = " + testSuite);
    }

    @AfterClass
    public static void finishTestSuite() {
        removeDownloadFolder();
        logger.info("finish the test suite = " + testSuite);
    }
}

