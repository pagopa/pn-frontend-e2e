package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.model.incidents.IncidentStatusResponse;
import it.pn.frontend.e2e.model.incidents.NewIncidentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestIncident {
    private static final Logger logger = LoggerFactory.getLogger("RestIncident");
    final CustomHttpClient<NewIncidentRequest, IncidentStatusResponse> httpClientIncident = CustomHttpClient.getInstance();
    final private String env = System.getProperty("environment");

    private static RestIncident instance;

    public static synchronized RestIncident getInstance() {
        if (instance == null) {
            instance = new RestIncident();
        }
        return instance;
    }

    public RestIncident() {
        this.httpClientIncident.setBaseUrlApi("https://api.bo." + env + ".notifichedigitali.it/");
    }
}
