package it.pn.frontend.e2e.run;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.platform.engine.Constants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;


@SelectPackages("it.pn.frontend.e2e.run")
@RunWith(Cucumber.class)
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("it/pn/frontend/e2e/stepDefinitions")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,value = "it/pn/frontend/e2e/stepDefinitions")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,value = "it/pn/frontend/e2e/listeners")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "json:target/cucumber-reports/report.json")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "html:target/cucumber-reports/report.html")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "junit:target/cucumber-reports/Cucumber.xml")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "src/test/resources/feature")
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

