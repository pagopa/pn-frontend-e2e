package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.model.NewNotification;
import it.pn.frontend.e2e.model.NewNotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestNotification {
    private static final Logger logger = LoggerFactory.getLogger("RestNotification");

    private final CustomHttpClient httpClient;

    public RestNotification() {
        this.httpClient = CustomHttpClient.getInstance();
    }


    public NewNotificationResponse newNotificationWithOneRecipientAndDocument(NewNotification notification) {
        try {
            String response = this.httpClient.sendHttpPostRequest("/delivery/v2.1/requests", null, notification);
            if (response != null) {
                logger.info(response);
                return new NewNotificationResponse("test", "test", "test");
            }
        } catch (Exception e) {
            logger.error("Error during createNewNotification");
            e.printStackTrace();
        }
        return null;
    }

}
