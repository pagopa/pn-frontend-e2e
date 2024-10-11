package it.pn.frontend.e2e.stepDefinitions.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.run.RunCucumberTest;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

import static it.pn.frontend.e2e.listeners.Hooks.scenario;

public class ConfigFileReader {

    private static final String TEST_CONFIG_FILE = "test-config.properties";
    private static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger("ConfigFileReader");
    private static String testSuite;

    public static boolean loadProperties() {
        logger.info("load properties");
        try (InputStream input = ConfigFileReader.class.getClassLoader().getResourceAsStream(TEST_CONFIG_FILE)) {
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

    @BeforeAll
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

    @After
    public static void finishTestSuite() {
        logger.info("finish the test suite = " + testSuite);
    }


}