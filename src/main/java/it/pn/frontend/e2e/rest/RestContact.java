package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.exceptions.RestContactException;
import it.pn.frontend.e2e.exceptions.RestDelegationException;
import it.pn.frontend.e2e.model.address.DigitalAddress;
import it.pn.frontend.e2e.model.address.DigitalAddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestContact {
    private static final Logger logger = LoggerFactory.getLogger("RestContact");
    final CustomHttpClient<?, String> httpClient = CustomHttpClient.getInstance();
    private static RestContact instance;
    final private String env = System.getProperty("environment");
    final private String token = System.getProperty("token");
    private Map<String, String> headers = new HashMap<>();

    public static synchronized RestContact getInstance() {
        if (instance == null) {
            instance = new RestContact();
        }
        return instance;
    }

    public RestContact() {

        this.httpClient.setBaseUrlApi("https://webapi." + env + ".notifichedigitali.it");
        if (token != null) {
            this.headers.put("Authorization", token);
        } else {
            logger.warn("Auth token non trovato, impossibile fare la richiesta HTTP in background!");
        }
    }

    /**
     * Remove the default digital address email from the contact
     *
     * @throws RestContactException if there is an error during the request
     */
    public void removeDigitalAddressCourtesyEmail() throws RestContactException {
        String url = "https://webapi." + env + ".notifichedigitali.it/address-book/v1/digital-address/courtesy/default/EMAIL";
        String response = "";
        try {
            response = httpClient.sendHttpDeleteRequest(url, this.headers, String.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzo digitale di cortesia rimosso con successo");
        } catch (IOException e) {
            logger.warn("Error during removeDigitalAdressCourtesyEmail: " + e.getMessage());
            logger.warn("Non è stato possibile rimuovere l'indirizzo digitale di cortesia");
        }
    }

    /**
     * Remove the default digital address pec from the contact
     *
     * @throws RestContactException if there is an error during the request
     */
    public void removeDigitalAddressLegalPec() throws RestContactException {
        String url = "https://webapi." + env + ".notifichedigitali.it/bff/v1/addresses/LEGAL/default/PEC";
        String response = "";
        try {
            response = httpClient.sendHttpDeleteRequest(url, this.headers, String.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzo digitale di cortesia rimosso con successo");
        } catch (IOException e) {
            logger.warn("Error during removeDigitalAdressLegalPec: " + e.getMessage());
            logger.warn("Non è stato possibile rimuovere l'indirizzo digitale di cortesia");
        }
    }

    /**
     * Remove the special contact from the contact
     *
     * @param digitalAddress the digital address to remove
     * @throws RestDelegationException if there is an error during the request
     */
    public void removeSpecialContact(DigitalAddress digitalAddress) throws RestDelegationException {
        String channelType = digitalAddress.getChannelType().toString();
        String addressType = digitalAddress.getAddressType().toString();
        String url = "https://webapi." + env + ".notifichedigitali.it/address-book/v1/digital-address/"
                + addressType + "/" + digitalAddress.getSenderId() + "/" + channelType;
        String response = "";
        try {
            response = httpClient.sendHttpDeleteRequest(url, this.headers, String.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzo digitale di 'altri recapiti' rimosso con successo");
        } catch (IOException e) {
            logger.error("Error during removeSpecialContact: " + e.getMessage());
            logger.warn("Non è stato possibile rimuovere l'indirizzo digitale di 'altri recapiti'");
        }
    }

    public DigitalAddressResponse getDigitalAddress() throws RestContactException {
        CustomHttpClient<?, DigitalAddressResponse> httpClientDigitalAddress = CustomHttpClient.getInstance();
        httpClientDigitalAddress.setBaseUrlApi("https://webapi." + env + ".notifichedigitali.it");
        String url = "/bff/v1/addresses/LEGAL/default/PEC";
        try {
            DigitalAddressResponse response = httpClientDigitalAddress.sendHttpGetRequest(url, this.headers, DigitalAddressResponse.class);
            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzi digitali ricevuti con successo");
            return response;
        } catch (IOException e) {
            logger.error("Error during getDigitalAddress", e);
            logger.warn("Non è stato possibile ricevere gli indirizzi digitali");
        }
        return null;
    }


    public List<DigitalAddress> getAllDigitalAddress() throws RestContactException {
        CustomHttpClient<?, DigitalAddress> httpClientDigitalAddress = CustomHttpClient.getInstance();
        httpClientDigitalAddress.setBaseUrlApi("https://webapi." + env + ".notifichedigitali.it");
        String url = "/bff/v1/addresses";

        try {
            List<DigitalAddress> response = httpClientDigitalAddress.sendHttpGetRequestListDigitalAddress(url, this.headers, DigitalAddress.class);

            logger.info("Risposta ricevuta: " + response);
            logger.info("Indirizzi digitali ricevuti con successo");
            return response;
        } catch (IOException e) {
            logger.error("Error during getDigitalAddress", e);
            logger.warn("Non è stato possibile ricevere gli indirizzi digitali");
            return null;
        }
    }

}
