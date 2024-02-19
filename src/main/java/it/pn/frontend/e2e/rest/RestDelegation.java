package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.exceptions.RestDelegationException;
import it.pn.frontend.e2e.model.DelegateRequest;
import it.pn.frontend.e2e.model.DelegateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestDelegation {
    private static final Logger logger = LoggerFactory.getLogger("RestDelegation");
    final CustomHttpClient<DelegateRequest, DelegateResponse> httpClient = CustomHttpClient.getInstance();

    private static RestDelegation instance;
    final private String env = System.getProperty("environment");
    final private String token = System.getProperty("token");
    private Map<String, String> headers = new HashMap<>();

    public static synchronized RestDelegation getInstance() {
        if (instance == null) {
            instance = new RestDelegation();
        }
        return instance;
    }

    public RestDelegation() {
        this.httpClient.setBaseUrlApi("https://webapi." + env + ".notifichedigitali.it");
        if (token != null) {
            this.headers.put("Authorization", token);
        } else {
            logger.warn("Auth token non trovato, impossibile fare la richiesta HTTP in background!");
        }
    }

    /**
     * Add a new PF delegation
     *
     * @param delegateRequest DelegateRequest object with all the data
     * @return DelegateResponse object with the response
     * @throws RestDelegationException if there is an error during the request
     */
    public DelegateResponse addDelegationPF(DelegateRequest delegateRequest, String tokenExchange) throws RestDelegationException {
        try {
            String jwtToken = httpClient.getJwtToken(tokenExchange);
            this.headers.put("Authorization", "Bearer " + jwtToken);
            DelegateResponse response = httpClient.sendHttpPostRequest("/mandate/api/v1/mandate", this.headers, delegateRequest, DelegateResponse.class);
            if (response != null) {
                logger.info(String.valueOf(response));
                return response;
            }
        } catch (IOException e) {
            logger.error("Error during addDelegationPF", e);
        }
        return null;
    }

    /**
     * Revoke a PF delegation
     * <br>
     * <b>Keep in mind this method works only for the annotation @After into Hooks.java, because there isn't a jwt token set
     * if you don't invoke an "addDelegation"</b>
     *
     * @param mandateId String with the mandateId
     * @throws RestDelegationException if there is an error during the request
     */
    public void revokeDelegationPF(String mandateId) throws RestDelegationException {
        try {
            httpClient.sendHttpPatchRequest("/mandate/api/v1/mandate/" + mandateId + "/revoke", this.headers);
        } catch (IOException e) {
            logger.error("Error during revokeDelegationPF", e);
        }
    }

    public List<DelegateResponse> getDelegator() {
        try {
            List<DelegateResponse> response = httpClient.sendHttpGetRequest("/mandate/api/v1/mandates-by-delegator", this.headers, DelegateResponse.class);
            if (response != null) {
                logger.info(String.valueOf(response));
                return response;
            }
        } catch (IOException e) {
            logger.error("Error during getDelegator", e);
        }
        return null;
    }
}
