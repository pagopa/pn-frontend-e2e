package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.model.incidents.IncidentStatusResponse;
import it.pn.frontend.e2e.model.incidents.NewIncidentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*Modifiche principali
Iniezione delle dipendenze: @Autowired viene utilizzato per iniettare CustomHttpClient e WebDriverConfig.
Rimozione del Singleton: RestIncident è ora un @Component gestito da Spring, quindi non è necessario il metodo getInstance() o una gestione manuale dell'istanza.
Configurazione URL base: La configurazione dell'URL base è ora gestita nel costruttore per inizializzare httpClientIncident.*/
@Component
public class RestIncident {

    private static final Logger logger = LoggerFactory.getLogger(RestIncident.class);

    @Autowired
    private CustomHttpClient<NewIncidentRequest, IncidentStatusResponse> httpClientIncident;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    public RestIncident(WebDriverConfig webDriverConfig) {
        this.webDriverConfig = webDriverConfig;
        setupHttpClient();
    }

    private void setupHttpClient() {
        String baseUrl = "https://api.bo." + webDriverConfig.getEnvironment() + ".notifichedigitali.it/";
        httpClientIncident.setBaseUrlApi(baseUrl);
        logger.info("Configured HTTP client for Incidents with base URL: {}", baseUrl);
    }
}
