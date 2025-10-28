package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.exceptions.RestDelegationException;
import it.pn.frontend.e2e.model.delegate.*;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestDelegation {

    private static final Logger logger = LoggerFactory.getLogger(RestDelegation.class);


    private WebDriverConfig webDriverConfig;

    private final Map<String, String> headers = new HashMap<>();

    private final CustomHttpClient<DelegateRequestPF, DelegateResponsePF> httpClientPF;
    private final CustomHttpClient<DelegateRequestPG, DelegateResponsePG> httpClientPG;


    @Autowired
    public RestDelegation(WebDriverConfig webDriverConfig, CustomHttpClient<DelegateRequestPF, DelegateResponsePF> httpClientPF, CustomHttpClient<DelegateRequestPG, DelegateResponsePG> httpClientPG) {
        this.httpClientPF = httpClientPF;
        this.httpClientPG = httpClientPG;
        this.webDriverConfig = webDriverConfig;
        initializeHeaders();
        setupHttpClients();
    }

    private void initializeHeaders() {
        String token = System.getProperty("token");
        if (token != null) {
            headers.put("Authorization", token);
        } else {
            logger.warn("Auth token non trovato, impossibile fare la richiesta HTTP in background!");
        }
    }

    private void setupHttpClients() {
        String baseUrl = "https://webapi." + webDriverConfig.getEnvironment() + ".notifichedigitali.it";
        httpClientPF.setBaseUrlApi(baseUrl);
        httpClientPG.setBaseUrlApi(baseUrl);
    }

    /**
     * Aggiunge una nuova delega PF.
     *
     * @param delegateRequestPF DelegateRequest con i dati della delega
     * @param tokenExchange     token per l'exchange JWT
     * @return DelegateResponse con la risposta
     * @throws RestDelegationException in caso di errore nella richiesta
     */
    public DelegateResponsePF addDelegationPF(DelegateRequestPF delegateRequestPF, String tokenExchange) throws RestDelegationException {
        try {
            String jwtToken = httpClientPF.getJwtToken(tokenExchange);
            headers.put("Authorization", "Bearer " + jwtToken);
            DelegateResponsePF response = httpClientPF.sendHttpPostRequest("/bff/v1/mandate", headers, delegateRequestPF, DelegateResponsePF.class);
            if (response != null) {
                Assertions.fail("Wrong response after create BFF: {}" + response);
            }
            //Case BFF 201
            else {
                logger.info("Response is null from BFF, correct!");
                return response;
            }
        } catch (IOException e) {
            throw new RestDelegationException("Errore durante la richiesta di delega PF", e);
        }
        return null;
    }

    /**
     * Aggiunge una nuova delega PG.
     *
     * @param delegateRequest DelegateRequest con i dati della delega
     * @param tokenExchange   token per l'exchange JWT
     * @return DelegateResponse con la risposta
     * @throws RestDelegationException in caso di errore nella richiesta
     */
    public DelegateResponsePG addDelegationPG(DelegateRequestPG delegateRequest, String tokenExchange) throws RestDelegationException {
        try {
            String jwtToken = httpClientPG.getJwtToken(tokenExchange);
            headers.put("Authorization", "Bearer " + jwtToken);
            DelegateResponsePG response = httpClientPG.sendHttpPostRequest("/bff/v1/mandate", headers, delegateRequest, DelegateResponsePG.class);
            if (response != null) {
                logger.info("Response: {}", response);
                return response;
            }
        } catch (IOException e) {
            throw new RestDelegationException("Errore durante la richiesta di delega PG", e);
        }
        return null;
    }

    /**
     * Revoca una delega.
     *
     * @param mandateId ID della delega da revocare
     * @throws RestDelegationException in caso di errore nella richiesta
     */
    public void revokeDelegation(String mandateId) throws RestDelegationException {
        try {
            httpClientPF.sendHttpPatchRequest("/bff/v1/mandate/" + mandateId + "/revoke", headers);
            logger.info("Delega {} revocata con successo", mandateId);
        } catch (IOException e) {
            throw new RestDelegationException("Errore durante la revoca della delega", e);
        }
    }

    /**
     * Rifiuta una delega.
     *
     * @param mandateId ID della delega da rifiutare
     * @throws RestDelegationException in caso di errore nella richiesta
     */
    public void rejectDelegation(String mandateId) throws RestDelegationException {
        try {
            //N.B. Controllare se funziona quando metodo verrà riusato
            httpClientPG.sendHttpPatchRequest("/bff/v1/mandate/" + mandateId + "/reject", headers);
            logger.info("Delega {} rifiutata con successo", mandateId);
        } catch (IOException e) {
            throw new RestDelegationException("Errore durante il rifiuto della delega", e);
        }
    }

    /**
     * Ottiene le deleghe per il delegante corrente.
     *
     * @return lista di `DelegateResponsePF` con le deleghe
     */
    //non usato, probabilmente deprecato
    /*public List<DelegateResponsePF> getDelegator() {
        try {
            List<DelegateResponsePF> response = httpClientPF.sendHttpGetRequestListDelegate("/mandate/api/v1/mandates-by-delegator", headers, DelegateResponsePF.class);
            if (response != null) {
                logger.info("Response: {}", response);
                return response;
            }
        } catch (IOException e) {
            logger.error("Errore durante getDelegator", e);
        }
        return null;
    }*/

    public List<DelegateResponsePF> getDeleghePF() {
        try {
            List<DelegateResponsePF> getResponseList = httpClientPF.sendHttpGetRequestListDelegate("/bff/v1/mandate/delegator", headers, DelegateResponsePF.class);
            if (getResponseList != null) {
                logger.info("getResponseList {}", getResponseList.toArray());
                return getResponseList;
            }
        }
        catch (IOException e) {
            logger.error("Errore durante getDeleghe", e);
        }
        return null;
    }

    public List<DelegateResponsePG> getDeleghePG() {
        try {
            List<DelegateResponsePG> getResponseList = httpClientPG.sendHttpGetRequestListDelegatePG("/bff/v1/mandate/delegator", headers, DelegateResponsePG.class);

            if (getResponseList != null) {
                logger.info("getResponseList {}", getResponseList.toArray());
                return getResponseList;
            }
        }
        catch (IOException e) {
            logger.error("Errore durante getDeleghe", e);
        }
        return null;
    }

}
