package it.pn.frontend.e2e.utility;

import it.pn.frontend.e2e.model.*;
import it.pn.frontend.e2e.model.enums.RecipientTypeEnum;
import it.pn.frontend.e2e.rest.RestNotification;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
public class NotificationBuilder {
    public static final String APPLICATION_PDF = "application/pdf";
    private final RestNotification restNotification = new RestNotification();

    public ArrayList<Recipient> multiDestinatarioPG(String multiDestinatarioFlag) {
        ArrayList<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient("Convivio Spa", RecipientTypeEnum.PG, "27957814470"));
        if (multiDestinatarioFlag.equalsIgnoreCase("true")) {
            recipients.add(new Recipient("DivinaCommedia Srl", RecipientTypeEnum.PG, "70412331207"));
        }
        return recipients;
    }

    public ArrayList<Recipient> multiDestinatarioPF(String multiDestinatarioFlag) {
        ArrayList<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient("Gaio Giulio Cesare", RecipientTypeEnum.PF, "CSRGGL44L13H501E"));
        if (multiDestinatarioFlag.equalsIgnoreCase("true")) {
            recipients.add(new Recipient("Lucrezia Borgia", RecipientTypeEnum.PF, "BRGLRZ80D58H501Q"));
        }
        return recipients;
    }

    public ArrayList<NotificationPaymentItem> paymentsCreation(int avvisoPagoPa, int F24, String costiNotifica) {
        ArrayList<NotificationPaymentItem> payments = new ArrayList<>();
        if (avvisoPagoPa == 0 && F24 == 0) {
            return null;
        }
        for (int i = 0; i < avvisoPagoPa; i++) {
            payments = preloadPagoPaPayment(payments, costiNotifica);
        }
        for (int i = 0; i < F24; i++) {
            payments = preloadF24Payment(payments, i, costiNotifica);
        }
        return payments;
    }

    public ArrayList<NotificationPaymentItem> preloadPagoPaPayment(ArrayList<NotificationPaymentItem> payments, String costiNotifica) {
        File documentFile = new File("src/test/resources/dataPopulation/fileUpload/sample.pdf");
        String sha256 = computeSha256(documentFile.getAbsolutePath().replace("\\", "/"));
        PreLoadRequest preLoadRequest = new PreLoadRequest()
                .preloadIdx("0")
                .sha256(sha256)
                .contentType(APPLICATION_PDF);
        List<PreLoadRequest> preLoadRequestList = new ArrayList<>();
        preLoadRequestList.add(preLoadRequest);
        List<PreLoadResponse> response = restNotification.preLoadDocument(preLoadRequestList);
        Assert.assertNotNull("La chiamata per il preload del documento non è andata a buon fine", response);
        log.info("PreLoad del documento effettuato con successo");
        if (costiNotifica.equalsIgnoreCase("false")) {
            PagoPaPayment avvisoPagoPa = new PagoPaPayment(WebTool.generateNoticeCodeNumber(), sha256, response.get(0).getKey(), "v1", false);
            restNotification.uploadDocument(response.get(0).getUrl(), response.get(0).getSecret(), sha256);
            NotificationPaymentItem payment = new NotificationPaymentItem(avvisoPagoPa);
            payments.add(payment);
        } else {
            PagoPaPayment avvisoPagoPa = new PagoPaPayment(WebTool.generateNoticeCodeNumber(), sha256, response.get(0).getKey(), "v1");
            restNotification.uploadDocument(response.get(0).getUrl(), response.get(0).getSecret(), sha256);
            NotificationPaymentItem payment = new NotificationPaymentItem(avvisoPagoPa);
            payments.add(payment);
        }
        return payments;
    }

    public ArrayList<NotificationPaymentItem> preloadF24Payment(ArrayList<NotificationPaymentItem> payments, int i, String costiNotifica) {
        File documentFile = new File("src/test/resources/dataPopulation/fileUpload/sample.pdf");
        String sha256 = computeSha256(documentFile.getAbsolutePath().replace("\\", "/"));
        PreLoadRequest preLoadRequest = new PreLoadRequest()
                .preloadIdx("0")
                .sha256(sha256)
                .contentType(APPLICATION_PDF);
        List<PreLoadRequest> preLoadRequestList = new ArrayList<>();
        preLoadRequestList.add(preLoadRequest);
        List<PreLoadResponse> response = restNotification.preLoadDocument(preLoadRequestList);
        Assert.assertNotNull("La chiamata per il preload del documento non è andata a buon fine", response);
        log.info("PreLoad del documento effettuato con successo");
        if (costiNotifica.equalsIgnoreCase("false")) {
            F24Payment f24 = new F24Payment(sha256, response.get(0).getKey(), "v1", false);
            restNotification.uploadDocument(response.get(i).getUrl(), response.get(i).getSecret(), sha256);
            if (!payments.get(i).getPagoPa().getNoticeCode().isEmpty()) {
                payments.get(i).setF24(f24);
            } else {
                NotificationPaymentItem payment = new NotificationPaymentItem(f24);
                payments.add(payment);
            }
        } else {
            F24Payment f24 = new F24Payment(sha256, response.get(0).getKey(), "v1");
            restNotification.uploadDocument(response.get(i).getUrl(), response.get(i).getSecret(), sha256);
            if (!payments.get(i).getPagoPa().getNoticeCode().isEmpty()) {
                payments.get(i).setF24(f24);
            } else {
                NotificationPaymentItem payment = new NotificationPaymentItem(f24);
                payments.add(payment);
            }
        }
        return payments;
    }

    public ArrayList<Document> preloadDocument(int numeroDocumenti) {
        List<PreLoadRequest> preLoadRequestList = new ArrayList<>();
        List<String> sha256List = new ArrayList<>();
        listForPreloadPopulation(numeroDocumenti, sha256List, preLoadRequestList);
        List<PreLoadResponse> response = restNotification.preLoadDocument(preLoadRequestList);
        Assert.assertNotNull("La chiamata per il preload del documento non è andata a buon fine", response);
        log.info("PreLoad del documento effettuato con successo");
        ArrayList<Document> documents = new ArrayList<>();
        for (int i = 0; i < numeroDocumenti; i++) {
            documents.add(new Document(new Digests(sha256List.get(i)), APPLICATION_PDF, new Ref(response.get(i).getKey(), "v1")));
            restNotification.uploadDocument(response.get(i).getUrl(), response.get(i).getSecret(), sha256List.get(i));
        }
        return documents;
    }

    public void listForPreloadPopulation(int documentNumber, List<String> sha256List, List<PreLoadRequest> preLoadRequestList) {
        File documentFile = new File("src/test/resources/dataPopulation/fileUpload/sample.pdf");
        for (int i = 0; i < documentNumber; i++) {
            String sha256 = computeSha256(documentFile.getAbsolutePath().replace("\\", "/"));
            PreLoadRequest preLoadRequest = new PreLoadRequest()
                    .preloadIdx("0")
                    .sha256(sha256)
                    .contentType(APPLICATION_PDF);
            sha256List.add(sha256);
            preLoadRequestList.add(preLoadRequest);
        }
    }

    //come eccezione ho: IOException e FileNotFoundException
    public String computeSha256(String resName) {
        try (InputStream stream = new BufferedInputStream(new FileInputStream(resName))) {
            return computeSha256(stream);
        } catch (Exception e) {
            log.error("Il preload non è andato a buon fine con errore: {}", e.getMessage());
            Assert.fail("Il preload non è andato a buon fine con errore: " + e.getMessage());
            return null;
        }
    }

    public String computeSha256(InputStream inStrm) throws IOException {
        try (inStrm) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(inStrm.readAllBytes());
            return bytesToBase64(encodedhash);
        } catch (NoSuchAlgorithmException exc) {
            throw new IOException("SHA-256 algorithm not found", exc);
        }
    }

    private static String bytesToBase64(byte[] hash) {
        byte[] encodedBytes = Base64.getEncoder().encode(hash);
        return new String(encodedBytes);
    }
}
