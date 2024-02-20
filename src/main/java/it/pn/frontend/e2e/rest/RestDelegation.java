package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.exceptions.RestDelegationException;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPF;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPG;
import it.pn.frontend.e2e.model.delegate.DelegateResponsePF;
import it.pn.frontend.e2e.model.delegate.DelegateResponsePG;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestDelegation {
    private static final Logger logger = LoggerFactory.getLogger("RestDelegation");
    final CustomHttpClient<DelegateRequestPF, DelegateResponsePF> httpClientPF = CustomHttpClient.getInstance();
    final CustomHttpClient<DelegateRequestPG, DelegateResponsePG> httpClientPG = CustomHttpClient.getInstance();

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
        this.httpClientPF.setBaseUrlApi("https://webapi." + env + ".notifichedigitali.it");
        if (token != null) {
            this.headers.put("Authorization", token);
        } else {
            logger.warn("Auth token non trovato, impossibile fare la richiesta HTTP in background!");
        }
    }

    /**
     * Add a new PF delegation
     *
     * @param delegateRequestPF DelegateRequest object with all the data
     * @return DelegateResponse object with the response
     * @throws RestDelegationException if there is an error during the request
     */
    public DelegateResponsePF addDelegationPF(DelegateRequestPF delegateRequestPF, String tokenExchange) throws RestDelegationException {
        try {
            String jwtToken = httpClientPF.getJwtToken(tokenExchange);
            this.headers.put("Authorization", "Bearer " + jwtToken);
            DelegateResponsePF response = httpClientPF.sendHttpPostRequest("/mandate/api/v1/mandate", this.headers, delegateRequestPF, DelegateResponsePF.class);
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
     * Add a new PG delegation
     *
     * @param delegateRequestPG DelegateRequest object with all the data
     * @return DelegateResponse object with the response
     * @throws RestDelegationException if there is an error during the request
     */
    public DelegateResponsePG addDelegationPG(DelegateRequestPG delegateRequest, String tokenExchange) throws RestDelegationException {
        try {
            String jwtToken = httpClientPG.getJwtToken(tokenExchange);
            this.headers.put("Authorization", "Bearer " + jwtToken);
            DelegateResponsePG response = httpClientPG.sendHttpPostRequest("/mandate/api/v1/mandate", this.headers, delegateRequest, DelegateResponsePG.class);
            if (response != null) {
                logger.info(String.valueOf(response));
                return response;
            }
        } catch (IOException e) {
            logger.error("Error during addDelegationPG", e);
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
            httpClientPF.sendHttpPatchRequest("/mandate/api/v1/mandate/" + mandateId + "/revoke", this.headers);
        } catch (IOException e) {
            logger.error("Error during revokeDelegationPF", e);
        }
    }

   /* public void cancelDelegationPG(String mandateId) throws RestDelegationException {
        try {
            httpClient.sendHttpPatchRequest("/mandate/api/v1/mandate/" + mandateId + "/reject", this.headers);
        } catch (IOException e) {
            logger.error("Error during rejectDelegationPG", e);
        }
    }*/

    public List<DelegateResponsePF> getDelegator() {
        try {
            List<DelegateResponsePF> response = httpClientPF.sendHttpGetRequest("/mandate/api/v1/mandates-by-delegator", this.headers, DelegateResponsePF.class);
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
