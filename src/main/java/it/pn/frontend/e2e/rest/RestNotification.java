package it.pn.frontend.e2e.rest;// ... Altre importazioni ...

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.model.NewNotificationRequest;
import it.pn.frontend.e2e.model.NewNotificationResponse;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class RestNotification {
    private static final Logger logger = LoggerFactory.getLogger("RestNotification");

    public RestNotification() {
    }

    /**
     * Create a new notification with one recipient and one document
     *
     * @param notification NewNotification object with all the data
     * @return NewNotificationResponse object with the response
     * @throws RestNotificationException if there is an error during the request
     */
    public NewNotificationResponse newNotificationWithOneRecipientAndDocument(NewNotificationRequest notification, Map<String, Boolean> pagamenti) throws RestNotificationException {
        final CustomHttpClient<NewNotificationRequest, NewNotificationResponse> httpClient2 = CustomHttpClient.getInstance();  // Modifica qui
        try {
            /*boolean avvisoPagoPa = pagamenti.get("avvisoPagoPa");
            boolean f24 = pagamenti.get("F24");
            boolean entrambi = pagamenti.get("entrambi");*/
            NewNotificationResponse response = httpClient2.sendHttpPostRequest("/delivery/v2.3/requests", null, notification, NewNotificationResponse.class);
            /*if(avvisoPagoPa && !f24 && !entrambi){
                response = httpClient2.sendHttpPostRequest("/delivery/v2.3/requests", null, notification, NewNotificationResponse.class);
            } else if(!avvisoPagoPa && f24 && !entrambi){
                response = httpClient2.sendHttpPostRequest("/delivery/v2.3/requests", null, notification, NewNotificationResponse.class);
            } else if(!avvisoPagoPa && !f24 && entrambi){
                response = httpClient2.sendHttpPostRequest("/delivery/v2.3/requests", null, notification, NewNotificationResponse.class);
            } else {
                logger.error("Le condizioni del map non sono corrette");
                Assert.fail("Le condizioni del map non sono corrette");
            }*/
            if (response != null) {
                logger.info(String.valueOf(response));
                return response;
            }
        } catch (IOException e) {
            logger.error("Error during createNewNotification", e);
        }
        return null;
    }

    public String getNotificationStatus(String notificationRequestId) {
        final CustomHttpClient<Object, Object> httpClient2 = CustomHttpClient.getInstance();  // Modifica qui
        httpClient2.setBaseUrlApi("https://api.test.notifichedigitali.it");
        try {
            Object response = httpClient2.sendHttpGetRequest("/delivery/v2.3/requests?notificationRequestId=" + notificationRequestId, null, Object.class);
            if (response instanceof LinkedTreeMap) {
                LinkedTreeMap<String, Object> responseData = (LinkedTreeMap<String, Object>) response;
                if (responseData.containsKey("notificationRequestStatus")) {
                    return responseData.get("notificationRequestStatus").toString();
                } else {
                    logger.error("L'attributo 'notificationRequestStatus' non è presente nella risposta JSON");
                    return null;
                }
            } else {
                logger.error("La risposta non è valida o non può essere convertita in JSON");
                return null;
            }
        } catch (IOException e) {
            logger.error("Error during getNotificationStatus", e);
            return null;
        }
    }
}
