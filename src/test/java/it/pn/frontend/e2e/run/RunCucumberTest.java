package it.pn.frontend.e2e.run;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
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

    @BeforeClass
    public static void startTestSuite() {
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
        logger.info("finish the test suite = " + testSuite);
    }
}

