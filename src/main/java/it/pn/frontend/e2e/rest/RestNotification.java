package it.pn.frontend.e2e.rest;// ... Altre importazioni ...

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.model.notification.NewNotificationRequest;
import it.pn.frontend.e2e.model.notification.NewNotificationResponse;
import it.pn.frontend.e2e.model.documents.PreLoadRequest;
import it.pn.frontend.e2e.model.documents.PreLoadResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class RestNotification {
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
        final CustomHttpClient<NewNotificationRequest, NewNotificationResponse> httpClient2 = new CustomHttpClient<>(); // Modifica qui
        try {
            NewNotificationResponse response = httpClient2.sendHttpPostRequest("/delivery/v2.3/requests", null, notification, NewNotificationResponse.class);
            Assert.assertNotNull("Error during createNewNotification", response);
            log.info(String.valueOf(response));
            return response;
        } catch (IOException e) {
            log.error("Error during createNewNotification", e);
        }
        return null;
    }

    public List<PreLoadResponse> preLoadDocument(List<PreLoadRequest> preLoadList) throws RestNotificationException {
        final CustomHttpClient<PreLoadRequest, PreLoadResponse> httpClient2 = new CustomHttpClient<>();
        try {
            List<PreLoadResponse> response = httpClient2.sendHttpPreloadPostRequest("/delivery/attachments/preload", null, preLoadList, PreLoadResponse.class);
            if (response != null) {
                return response;
            }
        } catch (IOException e) {
            log.error("Error during document preload", e);
        }
        return null;
    }

    public void uploadDocument(String url, String secret, String sha256) throws RestNotificationException {
        final CustomHttpClient<?, ?> httpClient2 = CustomHttpClient.getInstance();
        try {
            httpClient2.sendHttpUpLoadPutRequest(url, secret, sha256, null);
        } catch (IOException e) {
            log.error("Error during document upload", e);
            Assert.fail("Error during document upload" + e.getMessage());
        }
    }

    public void uploadDocumentF24(String url, String secret, String sha256, File metaDatiDocument) throws RestNotificationException {
        final CustomHttpClient<?, ?> httpClient2 = CustomHttpClient.getInstance();
        try {
            httpClient2.sendHttpUpLoadf24PutRequest(url, secret, sha256, null, metaDatiDocument);
        } catch (IOException e) {
            log.error("Error during F24 upload", e);
            Assert.fail("Error during F24 upload" + e.getMessage());
        }
    }

    public LinkedTreeMap<String, Object> getNotificationStatus(String notificationRequestId) {
        final CustomHttpClient<Object, Object> httpClient2 = CustomHttpClient.getInstance();  // Modifica qui
        httpClient2.setBaseUrlApi("https://api.test.notifichedigitali.it");
        try {
            Object response = httpClient2.sendHttpGetRequest("/delivery/v2.3/requests?notificationRequestId=" + notificationRequestId, null, Object.class);
            if (response instanceof LinkedTreeMap) {
                LinkedTreeMap<String, Object> responseData = (LinkedTreeMap<String, Object>) response;
                if (responseData.containsKey("notificationRequestStatus")) {
                    return responseData;
                }else {
                    log.error("L'attributo 'notificationRequestStatus' non è presente nella risposta JSON");
                    return null;
                }
            } else {
                log.error("La risposta non è valida o non può essere convertita in JSON");
                return null;
            }
        } catch (IOException e) {
            log.error("Error during getNotificationStatus", e);
            return null;
        }
    }

}
