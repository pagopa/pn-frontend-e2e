package it.pn.frontend.e2e.run;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"it/pn/frontend/e2e/listeners","it/pn/frontend/e2e/stepDefinitions"},
        //tags = "@runner",
        //plugin = { "pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
       plugin = { "pretty",
                "json:target/cucumber-reports/report.json",
                "html:target/cucumber-reports/report.html",
                "junit:target/cucumber-reports/Cucumber.xml"
        },
        monochrome = true
)
public class RunCucumberTest {

    private static final Logger logger =  LoggerFactory.getLogger("RunCucumberTest");

    private static String testSuite;


    @BeforeClass
    public static void startTestSuite(){

        if(System.getProperty("cucumber.filter.tags") != null){
            testSuite = System.getProperty("cucumber.filter.tags").substring(1);
            logger.info("run test della test suite = "+testSuite);
        }else{
            logger.info("run tutti i test");
        }


        logger.info("start the test suite = "+testSuite);


    }


    @AfterClass
    public static void finishTestSuite() {

        logger.info("finish the test suite = "+testSuite);


    }
}

