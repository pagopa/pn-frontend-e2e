package it.pn.frontend.e2e.runner;


import org.junit.platform.suite.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.cucumber.junit.platform.engine.Constants.*;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResources({
        @SelectClasspathResource("feature"),
        @SelectClasspathResource("features")
})
@ConfigurationParameters({
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty"),
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-reports/report.json," +
                "html:target/cucumber-reports/report.html"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "it/pn/frontend/e2e/listeners ,it/pn/frontend/e2e/stepDefinitions"),
        @ConfigurationParameter(key = EXECUTION_MODE_FEATURE_PROPERTY_NAME, value = "concurrent"),
})
@IncludeTags({"newBlock"})
public class RunCucumberTest {
    private static final Logger logger = LoggerFactory.getLogger(RunCucumberTest.class);

}
