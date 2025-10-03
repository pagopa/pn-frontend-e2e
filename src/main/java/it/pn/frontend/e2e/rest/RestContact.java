package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.exceptions.RestContactException;
import it.pn.frontend.e2e.exceptions.RestDelegationException;
import it.pn.frontend.e2e.model.address.DigitalAddress;
import it.pn.frontend.e2e.model.address.DigitalAddressResponse;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
*Modifiche principali:
Annotazioni @Autowired: Utilizzate per il caricamento automatico delle dipendenze CustomHttpClient e WebDriverConfig.
Costruttore per WebDriverConfig: La configurazione viene caricata tramite costruttore, impostando l’Authorization token per semplificare la gestione delle intestazioni.
Miglioramento gestione eccezioni: Le eccezioni sono lanciate per indicare fallimenti specifici nei metodi di gestione dei contatti digitali, come RestContactException e RestDelegationException, con log dettagliati.
Ottimizzazione dei metodi HTTP: I metodi HTTP GET e DELETE sono organizzati per fornire un output chiaro e gestibile, inclusi i log delle risposte.
* */
@Component
public class RestContact {

    private static final Logger logger = LoggerFactory.getLogger(RestContact.class);

    private final Map<String, String> headers = new HashMap<>();

    // Carica il token all'avvio della classe
    private WebDriverConfig  webDriverConfig;

   @Autowired
   private CustomHttpClient customHttpClient;

    private final CustomHttpClient<?, String> httpClient;

    @Setter
    @Getter
    private String sessionToken;

    @Autowired
    public RestContact(WebDriverConfig webDriverConfig, CustomHttpClient<?, String> httpClient) {
        this.httpClient = httpClient;
        this.webDriverConfig = webDriverConfig;
        this.httpClient.setBaseUrlApi("https://webapi." + this.webDriverConfig.getEnvironment() + ".notifichedigitali.it");

        String token = System.getProperty("token");
        if (token != null) {
            this.headers.put("Authorization", token);
        } else {
            logger.warn("Auth token non trovato, impossibile fare la richiesta HTTP in background!");
        }
    }



    /**
     * Rimuove l'indirizzo email di cortesia predefinito.
     */
    public void removeDigitalAddressCourtesyEmail() throws RestContactException {
        String url = "https://webapi." + webDriverConfig.getEnvironment() + ".notifichedigitali.it/bff/v1/addresses/COURTESY/default/EMAIL";
        try {
            headers.put("Authorization", setAuthorizationToken());
            String response = httpClient.sendHttpDeleteRequest(url, headers, String.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzo digitale di cortesia rimosso con successo");
        } catch (IOException e) {
            logger.error("Error during removeDigitalAdressCourtesyEmail: {}", e.getMessage());
            throw new RestContactException("Non è stato possibile rimuovere l'indirizzo digitale di cortesia", e);
        }
    }

    /**
     * Rimuove il numero di telefono usato per gli SMS.
     */
    public void removeDigitalAddressSms() throws RestContactException {
        String url = "https://webapi." + webDriverConfig.getEnvironment() + ".notifichedigitali.it/bff/v1/addresses/COURTESY/default/SMS";
        try {
            headers.put("Authorization", setAuthorizationToken());
            String response = httpClient.sendHttpDeleteRequest(url, headers, String.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzo digitale di cortesia rimosso con successo");
        } catch (IOException e) {
            logger.error("Error during removeDigitalAdressCourtesySms: {}", e.getMessage());
            throw new RestContactException("Non è stato possibile rimuovere l'indirizzo digitale di cortesia", e);
        }
    }

    /**
     * Rimuove l'indirizzo PEC legale predefinito.
     */
    public void removeDigitalAddressLegalPec() throws RestContactException {
        String url = "https://webapi." + webDriverConfig.getEnvironment() + ".notifichedigitali.it/bff/v1/addresses/LEGAL/default/PEC";
        try {
            headers.put("Authorization", setAuthorizationToken());
            String response = httpClient.sendHttpDeleteRequest(url, headers, String.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzo PEC legale rimosso con successo");
        } catch (IOException e) {
            logger.error("Error during removeDigitalAdressLegalPec: {}", e.getMessage());
            logger.error("Non è stato possibile rimuovere l'indirizzo PEC legale", e);
        }
    }

    /**
     * Rimuove il domicilio digitale di piattaforma SEND.
     */
    public void removeDigitalAddressLegalSend() throws RestContactException {
        String url = "https://webapi." + webDriverConfig.getEnvironment() + ".notifichedigitali.it/bff/v1/addresses/LEGAL/default/SERCQ_SEND";
        try {
            headers.put("Authorization", setAuthorizationToken());
            String response = httpClient.sendHttpDeleteRequest(url, headers, String.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Domicilio digitale di piattaforma SEND rimosso con successo");
        } catch (IOException e) {
            logger.error("Error during removeDigitalAdressLegalSend: {}", e.getMessage());
            logger.error("Non è stato possibile rimuovere il domicilio digitale di piattaforma SEND", e);
        }
    }

    /**
     * Rimuove un contatto speciale.
     */
    public void removeSpecialContact(DigitalAddress digitalAddress) throws RestDelegationException {
        String channelType = digitalAddress.getChannelType().toString();
        String addressType = digitalAddress.getAddressType().toString();
        String url = "https://webapi." +webDriverConfig.getEnvironment() + ".notifichedigitali.it/bff/v1/addresses/"
                + addressType + "/" + digitalAddress.getSenderId() + "/" + channelType;

        try {
            headers.put("Authorization", setAuthorizationToken());
            String response = httpClient.sendHttpDeleteRequest(url, headers, String.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzo digitale di 'altri recapiti' rimosso con successo");
        } catch (IOException e) {
            logger.error("Error during removeSpecialContact: {}", e.getMessage());
            throw new RestDelegationException("Non è stato possibile rimuovere l'indirizzo digitale di 'altri recapiti'", e);
        }
    }

    /**
     * Ottiene l'indirizzo digitale di default.
     */
    public DigitalAddressResponse getDigitalAddress() throws RestContactException {
        CustomHttpClient<?, DigitalAddressResponse> httpClientDigitalAddress = customHttpClient;
        httpClientDigitalAddress.setBaseUrlApi("https://webapi." + webDriverConfig.getEnvironment() + ".notifichedigitali.it");
        String url = "/bff/v1/addresses/LEGAL/default/PEC";

        try {
            String token = setAuthorizationToken();
            headers.put("Authorization", token);
            logger.info("TOKEN...{}", token);
            DigitalAddressResponse response = httpClientDigitalAddress.sendHttpGetRequest(url, headers, DigitalAddressResponse.class);
            logger.info("Risposta ricevuta: {}", response);
            logger.info("Indirizzi digitali ricevuti con successo");
            return response;
        } catch (IOException e) {
            logger.error("Error during getDigitalAddress", e);
            throw new RestContactException("Non è stato possibile ricevere gli indirizzi digitali", e);
        }
    }

    /**
     * Ottiene tutti gli indirizzi digitali.
     */
    public List<DigitalAddress> getAllDigitalAddress() throws RestContactException {
        CustomHttpClient<?, DigitalAddress> httpClientDigitalAddress = customHttpClient;
        httpClientDigitalAddress.setBaseUrlApi("https://webapi." + webDriverConfig.getEnvironment() + ".notifichedigitali.it");
        String url = "/bff/v1/addresses";

        try {
            headers.put("Authorization", setAuthorizationToken());
            List<DigitalAddress> response = httpClientDigitalAddress.sendHttpGetRequestListDigitalAddress(url, headers, DigitalAddress.class);
            logger.info("Risposta ricevuta: {}", response);
            logger.info("Indirizzi digitali ricevuti con successo");
            return response;
        } catch (IOException e) {
            logger.error("Error during getAllDigitalAddress {}", e.getMessage());
            throw new RestContactException("Non è stato possibile ricevere gli indirizzi digitali", e);
        }
    }

    //Per generare richieste CRUD sui recapiti, si deve recuperare il token di sessione
    //Se si è entrati dalla pagina di login il token di sessione è già presente sulla property di sistema
    //Altrimenti si recupera il valore della variabile sessionToken che deve essere preventivamente settato sulle
    //istanze della classe RestContact
    private String setAuthorizationToken() {
        if (sessionToken == null)
            return System.getProperty("token");
        else
            return "Bearer " + sessionToken;
    }
}