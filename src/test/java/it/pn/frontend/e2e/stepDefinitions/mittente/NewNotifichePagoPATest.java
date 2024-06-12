package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.exceptions.RestContactException;
import it.pn.frontend.e2e.exceptions.RestNotificationException;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.*;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.model.enums.RecipientTypeEnum;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class NewNotifichePagoPATest {
    @AllArgsConstructor
    @Data
    @ToString
    public static class Pair<K, E> {
        K value1;
        E value2;
    }
    public static final String APPLICATION_PDF = "application/pdf";
    public static final String APPLICATION_JSON = "application/json";
    private static final String LOAD_TO_PRESIGNED = "LOAD_TO_PRESIGNED";
    private static final String LOAD_TO_PRESIGNED_METADATI = "LOAD_TO_PRESIGNED_METADATI";
    private final RestNotification restNotification = new RestNotification();
    private final WebDriver driver = Hooks.driver;

    @When("Creo in background una notifica con un destinatario e un documento tramite API REST")
    public void creoUnaNotificaConUnDestinatarioEUnDocumento(Map<String, Boolean> pagamenti) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        boolean avvisoPagoPa = pagamenti.get("avvisoPagoPa");
        boolean f24 = pagamenti.get("F24");
        boolean entrambi = pagamenti.get("entrambi");
        ArrayList<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient());
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(new Document());
        List<NotificationPaymentItem> payments = new ArrayList<>();
        PagoPaPayment pagoPaPayment = new PagoPaPayment(WebTool.generateNoticeCodeNumber());
        F24Payment modelloF24 = new F24Payment();

        if (avvisoPagoPa && !f24 && !entrambi) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(pagoPaPayment);
            payments.add(pagamento);
        } else if (!avvisoPagoPa && f24 && !entrambi) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(modelloF24);
            payments.add(pagamento);
        } else if (!avvisoPagoPa && !f24 && entrambi) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(pagoPaPayment, modelloF24);
            payments.add(pagamento);
        } else {
            for (Recipient recipient : recipients) {
                recipient.setPayments(null);
            }
        }
        for (Recipient recipient : recipients) {
            recipient.setPayments(payments);
        }

        NewNotificationRequest notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.DELIVERY_MODE);

        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (response != null) {
                log.info("Notifica creata con successo");
                System.setProperty("IUN", WebTool.decodeNotificationRequestId(response.getNotificationRequestId()));
                return;
            } else {
                log.warn("Tentativo #{} di creazione della notifica fallito. Riprovo...", attempt);
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }

        log.error("Errore nella creazione della notifica dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    @And("Creo in background una notifica PG con un destinatario e un documento tramite API REST")
    public void creoUnaNotificaPGConUnDestinatarioEUnDocumento(Map<String, String> datiNotifica) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        String avvisoPagoPa = datiNotifica.get("avvisoPagoPa");
        String f24 = datiNotifica.get("F24");
        String entrambi = datiNotifica.get("entrambi");
        ArrayList<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient(datiNotifica.get("denomination"), RecipientTypeEnum.PG, datiNotifica.get("taxId"), new PhysicalAddress(), new DigitalDomicile()));
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(new Document());
        List<NotificationPaymentItem> payments = new ArrayList<>();
        PagoPaPayment pagoPaPayment = new PagoPaPayment(WebTool.generateNoticeCodeNumber());
        F24Payment modelloF24 = new F24Payment();

        if (avvisoPagoPa.equalsIgnoreCase("true") && f24.equalsIgnoreCase("false") && entrambi.equalsIgnoreCase("false")) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(pagoPaPayment);
            payments.add(pagamento);
        } else if (avvisoPagoPa.equalsIgnoreCase("false") && f24.equalsIgnoreCase("true") && entrambi.equalsIgnoreCase("false")) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(modelloF24);
            payments.add(pagamento);
        } else if (avvisoPagoPa.equalsIgnoreCase("false") && f24.equalsIgnoreCase("false") && entrambi.equalsIgnoreCase("true")) {
            NotificationPaymentItem pagamento = new NotificationPaymentItem(pagoPaPayment, modelloF24);
            payments.add(pagamento);
        } else {
            for (Recipient recipient : recipients) {
                recipient.setPayments(null);
            }
        }
        for (Recipient recipient : recipients) {
            recipient.setPayments(payments);
        }

        NewNotificationRequest notification = new NewNotificationRequest(WebTool.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890, "010202N", NotificationFeePolicyEnum.DELIVERY_MODE);

        while (attempt <= maxAttempts) {
            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (response != null) {
                log.info("Notifica creata con successo");
                System.setProperty("IUN", WebTool.decodeNotificationRequestId(response.getNotificationRequestId()));
                return;
            } else {
                log.warn("Tentativo #{} di creazione della notifica fallito. Riprovo...", attempt);
                notification.setPaProtocolNumber(WebTool.generatePaProtocolNumber());
                attempt++;
            }
        }

        log.error("Errore nella creazione della notifica dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }

    @And("Faccio il preload del documento")
    public void faccioIlPreloadDelDocumento() {
        int maxAttempts = 3;
        int attempt = 1;

        PreLoadRequest preLoadRequest = new PreLoadRequest()
                .preloadIdx("0")
                .sha256("z18S3aiGYNsz4FMCZSkuQhnF7yxcqkt1ehJlFc7c88I=")
                .contentType(APPLICATION_PDF);

        List<PreLoadRequest> preLoadRequestList = new ArrayList<>();
        preLoadRequestList.add(preLoadRequest);

        while (attempt <= maxAttempts) {
            List<PreLoadResponse> response = restNotification.preLoadDocument(preLoadRequestList);

            if (response != null) {
                log.info("PreLoad del documento effettuato con successo");
                log.info("\n\n\nkey: {}\n\n\n", response.get(0).getKey());
                return;
            } else {
                log.warn("Tentativo #{} di preload del documento. Riprovo...", attempt);
                attempt++;
            }
        }

        log.error("Errore nella creazione della notifica dopo {} tentativi", maxAttempts);
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }


    /* TODO
    @When("Creo in background una notifica con multi destinatario {int} e multi documento tramite API REST")
    public void creoUnaNotificaConUnDestinatarioEUnDocumento(Integer numMultiRecipients) throws RestNotificationException {
        int maxAttempts = 3;
        int attempt = 1;

        while (attempt <= maxAttempts) {
            ArrayList<Recipient> recipients = new ArrayList<>();
            ArrayList<Document> documents = new ArrayList<>();
            for(int i=0; i<numMultiRecipients; i++){
                recipients.add(new Recipient());
                documents.add(new Document());
            }

            NewNotification notification = new NewNotification(DataPopulation.generatePaProtocolNumber(), "Pagamento Rata IMU", recipients, documents, PhysicalCommunicationTypeEnum.AR_REGISTERED_LETTER, "123456A", NotificationFeePolicyEnum.FLAT_RATE);

            NewNotificationResponse response = restNotification.newNotificationWithOneRecipientAndDocument(notification);

            if (response != null) {
                log.info("Notifica creata con successo");
                return;
            } else {
                log.warn("Tentativo #" + attempt + " di creazione della notifica fallito. Riprovo...");
                attempt++;
            }

        }

        log.error("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
        Assert.fail("Errore nella creazione della notifica dopo " + maxAttempts + " tentativi");
    }
     */

    @Then("Attendo {int} minuti e verifico in background che la notifica sia stata creata correttamente")
    public void verificoCheLaNotificaSiaStataCreataCorrettamente(int minutes) {
        WebTool.waitTime(minutes * 60);
        driver.navigate().refresh();
        /* TODO
        Need to implement the check of the notification
         */
    }

    /*public NotificationDocument preloadDocumentV2(NotificationDocument document) throws IOException {
        Pair<String, String> preloadDocument = preloadGeneric(document.getRef().getKey(), LOAD_TO_PRESIGNED);
        documentSetKeyV20(document, preloadDocument.getValue1());
        documentSetVersionTokenV20(document, "v1");
        documentSetDigestsV20(document, preloadDocument.getValue2());
        return document;
    }

    private Pair<String, String> preloadGeneric(String resourceName, String loadToMetadata) throws IOException {
        String sha256 = computeSha256(resourceName);
        PreLoadResponse preLoadResponse;

        if (loadToMetadata.equals(LOAD_TO_PRESIGNED_METADATI)) {
            preLoadResponse = getPreLoadMetaDatiResponse(sha256);
        } else {
            preLoadResponse = getPreLoadResponse(sha256);
        }

        String key = preLoadResponse.getKey();
        String secret = preLoadResponse.getSecret();
        String url = preLoadResponse.getUrl();
        log.info("Attachment resourceKey={} sha256={} secret={} presignedUrl={}", resourceName, sha256, secret, url);

        if (loadToMetadata.equals(LOAD_TO_PRESIGNED_METADATI)) {
            loadToPresignedMetadati(url, secret, sha256, resourceName);
        } else if (loadToMetadata.equals(LOAD_TO_PRESIGNED)) {
            loadToPresigned(url, secret, sha256, resourceName);
        }
        return new Pair<>(key, sha256);
    }

    private void documentSetKeyV20(NotificationDocument notificationDocument, String key) {
        notificationDocument.getRef().setKey(key);
    }

    private void documentSetVersionTokenV20(NotificationDocument notificationDocument, String version) {
        notificationDocument.getRef().setVersionToken(version);
    }

    private void documentSetDigestsV20(NotificationDocument notificationDocument, String sha256) {
        Digests digests = new Digests(sha256);
        notificationDocument.setDigests(digests);
    }

    public String computeSha256(String resName) throws IOException {
        Resource res = ResourceLoader.getResource(resName);
        return computeSha256(res);
    }

    private String computeSha256(Resource res) throws IOException {
        return computeSha256(res.getInputStream());
    }

    public String computeSha256(InputStream inStrm) {
        try (inStrm) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(StreamUtils.copyToByteArray(inStrm));
            return bytesToBase64(encodedhash);
        } catch (IOException | NoSuchAlgorithmException exc) {
            throw new RestContactException(exc.getMessage());
        }
    }

    private static String bytesToBase64(byte[] hash) {
        byte[] decodedBytes = Base64.getDecoder().decode(hash);
        return new String(decodedBytes);
    }

    private PreLoadResponse getPreLoadMetaDatiResponse(String sha256) {
        PreLoadRequest preLoadRequest = new PreLoadRequest()
                .preloadIdx("0")
                .sha256(sha256)
                .contentType(APPLICATION_JSON);
        return client.presignedUploadRequest(
                Collections.singletonList(preLoadRequest)
        ).get(0);
    }

    private PreLoadResponse getPreLoadResponse(String sha256) {
        PreLoadRequest preLoadRequest = new PreLoadRequest()
                .preloadIdx("0")
                .sha256(sha256)
                .contentType(APPLICATION_PDF);
        return client.presignedUploadRequest(
                Collections.singletonList(preLoadRequest)
        ).get(0);
    }

    private void loadToPresignedMetadati(String url, String secret, String sha256, String resource) {
        loadToPresigned(url, secret, sha256, resource, APPLICATION_JSON, 0);
    }

    private void loadToPresigned(String url, String secret, String sha256, String resource) {
        loadToPresigned(url, secret, sha256, resource, APPLICATION_PDF, 0);
    }

    private void loadToPresigned(String url, String secret, String sha256, String resource, String resourceType, int depth) {
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Content-type", resourceType);
            headers.add("x-amz-checksum-sha256", sha256);
            headers.add("x-amz-meta-secret", secret);
            log.info("headers: {}", headers);
            HttpEntity<Resource> req = new HttpEntity<>(ctx.getResource(resource), headers);
            CustomHttpClient.exchange(URI.create(url), HttpMethod.PUT, req, Object.class);
        } catch (Exception e) {
            if (depth >= 5) {
                throw e;
            }
            log.info("Upload in catch, retry");
            try {
                Thread.sleep(2000);
                log.error("[THREAD IN SLEEP PRELOAD] id: {} , attempt: {} , url: {}, secret: {}, sha256: {}, resourceType: {}", Thread.currentThread().getId(), depth, url, secret, sha256, resourceType);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new RestContactException(ex.getMessage());
            }
            loadToPresigned(url, secret, sha256, resource, resourceType, depth + 1);
        }
    }*/


}

/*public it.pagopa.pn.client.b2b.pa.generated.openapi.clients.externalb2bpa.model_v2.NotificationDocument preloadDocumentV2(it.pagopa.pn.client.b2b.pa.generated.openapi.clients.externalb2bpa.model_v2.NotificationDocument document) throws IOException {
    Pair<String, String> preloadDocument = preloadGeneric(document.getRef().getKey(), LOAD_TO_PRESIGNED);
    documentSetKeyV20(document, preloadDocument.getValue1());
    documentSetVersionTokenV20(document, "v1");
    documentSetDigestsV20(document, preloadDocument.getValue2());
    return document;
}*/
