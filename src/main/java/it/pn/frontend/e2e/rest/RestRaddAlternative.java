package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RestRaddAlternative {

    private static RestRaddAlternative instance;
    final CustomHttpClient<?, String> httpClient = CustomHttpClient.getInstance();
    private Map<String, String> headers = new HashMap<>();
    final private String token = System.getProperty("token");
    final private String env = System.getProperty("environment");
    private final String uid = "1234556";


    public static synchronized RestRaddAlternative getInstance() {
        if (instance == null) {
            instance = new RestRaddAlternative();
        }
        return instance;
    }

    public RestRaddAlternative() {
        this.httpClient.setBaseUrlApi("https://webapi." + env + ".notifichedigitali.it");
        if (token != null) {
            this.headers.put("Authorization", token);
        } else {
            log.warn("Auth token non trovato, impossibile fare la richiesta HTTP in background!");
        }
    }
}
