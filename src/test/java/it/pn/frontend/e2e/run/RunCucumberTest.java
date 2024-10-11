package it.pn.frontend.e2e.run;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import it.pn.frontend.e2e.stepDefinitions.common.ConfigFileReader;
import org.junit.platform.suite.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.cucumber.junit.platform.engine.Constants.*;


/**
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

 **/

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("feature")
@ConfigurationParameters({
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty"),
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-report.json," +
                "html:target/cucumber-report.html"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "it/pn/frontend/e2e/listeners ,it/pn/frontend/e2e/stepDefinitions")

})

public class RunCucumberTest {

}

