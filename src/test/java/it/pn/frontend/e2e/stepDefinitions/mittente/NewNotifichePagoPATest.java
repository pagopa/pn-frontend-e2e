package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.Given;
import it.pn.frontend.e2e.model.Document;
import it.pn.frontend.e2e.model.NewNotification;
import it.pn.frontend.e2e.model.NewNotificationResponse;
import it.pn.frontend.e2e.model.Recipient;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.rest.RestNotification;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class NewNotifichePagoPATest {
    private static final Logger logger = LoggerFactory.getLogger("NewNotifichePagoPATest");
    private final RestNotification restNotification = new RestNotification();

    @Given("Creo in background una notifica con un destinatario e un documento tramite API REST")
    public void creoUnaNotificaConUnDestinatarioEUnDocumento() {
        // Invia la richiesta REST per la creazione di una notifica
        ArrayList<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient());
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(new Document());
        NewNotification notification = new NewNotification("TA-FFSMRC-20240110-46", "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.AR_REGISTERED_LETTER, "123456A", NotificationFeePolicyEnum.FLAT_RATE);
        NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);
        if (response != null) {
            logger.info("Notifica creata con successo");
        } else {
            logger.error("Errore nella creazione della notifica");
            Assert.fail("La risposta della creazione della notifica è: " + response);
        }
    }

}
