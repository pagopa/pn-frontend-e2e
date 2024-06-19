package it.pn.frontend.e2e.rest;// ... Altre importazioni ...

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.model.NewNotificationRequest;
import it.pn.frontend.e2e.model.NewNotificationResponse;
import it.pn.frontend.e2e.model.PreLoadRequest;
import it.pn.frontend.e2e.model.PreLoadResponse;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

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
    public NewNotificationResponse newNotificationWithOneRecipientAndDocument(NewNotificationRequest notification) throws RestNotificationException {
        final CustomHttpClient<NewNotificationRequest, NewNotificationResponse> httpClient2 = CustomHttpClient.getInstance();  // Modifica qui
        try {
            NewNotificationResponse response = httpClient2.sendHttpPostRequest("/delivery/v2.3/requests", null, notification, NewNotificationResponse.class);
            if (response != null) {
                logger.info(String.valueOf(response));
                return response;
            }
        } catch (IOException e) {
            logger.error("Error during createNewNotification", e);
        }
        return null;
    }

    public List<PreLoadResponse> preLoadDocument(List<PreLoadRequest> preLoadList) throws RestNotificationException {
        final CustomHttpClient<PreLoadRequest, PreLoadResponse> httpClient2 = CustomHttpClient.getInstance();
        try {
            List<PreLoadResponse> response = httpClient2.sendHttpPreloadPostRequest("/delivery/attachments/preload", null, preLoadList, PreLoadResponse.class);
            if (response != null) {
                return response;
            }
        } catch (IOException e) {
            logger.error("Error during document preload", e);
        }
        return null;
    }

    public void uploadDocument(String url, String secret, String sha256) throws RestNotificationException {
        final CustomHttpClient<?,?> httpClient2 = CustomHttpClient.getInstance();
        try {
            httpClient2.sendHttpUpLoadPutRequest(url, secret, sha256, null);
        } catch (IOException e) {
            logger.error("Error during document upload", e);
            Assert.fail("Error during document upload" + e.getMessage());
        }
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
