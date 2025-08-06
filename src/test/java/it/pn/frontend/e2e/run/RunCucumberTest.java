package it.pn.frontend.e2e.run;


import org.junit.platform.suite.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cucumber.junit.platform.engine.Constants.*;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("feature")
@ConfigurationParameters({
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty"),
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-reports/report.json," +
                "html:target/cucumber-reports/report.html"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "it/pn/frontend/e2e/listeners ,it/pn/frontend/e2e/stepDefinitions"),
        @ConfigurationParameter(key = EXECUTION_MODE_FEATURE_PROPERTY_NAME, value = "concurrent"),
})

public class RunCucumberTest {
    private static final Logger logger = LoggerFactory.getLogger(RunCucumberTest.class);

}

//@Suite
//@IncludeEngines("cucumber")
//@SelectClasspathResource("it/pagopa/pn/cucumber")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-report.json," +
//        "html:target/cucumber-report.html")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "it.pagopa.pn.cucumber.steps")
//@ConfigurationParameter(key = EXECUTION_MODE_FEATURE_PROPERTY_NAME, value = "concurrent")
//@ExcludeTags({"ignore","uat","appIo", "integration","realNR","mockNormalizzatore","giacenza890Complex","raddAlternativeCsv", "rasterScartoCON996"})
//@IncludeTags({"workflowDigitale", "workflowAnalogico", "pagamentiMultipli","giacenza890Simplified",
//        "Async", "f24", "version","AOO_UO", "Annullamento", "raddTechnicalAnnex", "raddAlt",
//        "validation", "RetentionAllegati", "apiKeyManager", "downtimeLogs", "recuperoDisservizi",
//        "legalFact", "letturaDestinatario", "raddAnagrafica", "raddAttoIntero", "restApiValidation"}) //TMP EXCLUDED: ,"partitaIva","raddAlt"
//public class NrtTest_test {
//}