package it.pn.frontend.e2e.utility;

import it.pn.frontend.e2e.model.*;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import it.pn.frontend.e2e.model.enums.RecipientTypeEnum;
import it.pn.frontend.e2e.rest.RestNotification;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
public class NotificationBuilder {
    public static final String APPLICATION_PDF = "application/pdf";
    public static final String APPLICATION_JSON = "application/json";
    private final RestNotification restNotification = new RestNotification();
    @Getter
    private String sha;

    public PhysicalCommunicationTypeEnum modelloNotifica(String modello) {
        return PhysicalCommunicationTypeEnum.fromString(modello);
    }

    public NotificationFeePolicyEnum notificaFeePolicy(String costiNotifica) {
        if (costiNotifica.equalsIgnoreCase("false")) {
            return NotificationFeePolicyEnum.FLAT_RATE;
        } else {
            return NotificationFeePolicyEnum.DELIVERY_MODE;
        }
    }

    public ArrayList<Recipient> destinatarioBuilder(Map<String, String> datiNotifica, ArrayList<Recipient> recipients) {
        PhysicalAddress indirizzoFisico = PhysicalAddress.builder()
                .at(datiNotifica.get("at"))
                .address(datiNotifica.getOrDefault("indirizzo", "VIA ROMA 20"))
                .addressDetails(datiNotifica.get("dettagliIndirizzo"))
                .zip(datiNotifica.getOrDefault("codicePostale", "20147"))
                .municipality(datiNotifica.getOrDefault("comune", "Milano"))
                .municipalityDetails(datiNotifica.get("dettagliComune"))
                .province(datiNotifica.getOrDefault("provincia", "MI"))
                .foreignState(datiNotifica.get("stato"))
                .build();
        RecipientTypeEnum tipoDestinatario = RecipientTypeEnum.fromString(datiNotifica.get("tipoDestinatario"));
        if (recipients == null) {
            recipients = new ArrayList<>();
        }
        if (datiNotifica.get("domicilioDigitale") != null) {
            DigitalDomicile domicilioDigitale = new DigitalDomicile(datiNotifica.get("domicilioDigitale"));
            recipients.add(new Recipient(datiNotifica.get("nomeCognome"), tipoDestinatario, datiNotifica.get("codiceFiscale"), indirizzoFisico, domicilioDigitale));
        } else {
            recipients.add(new Recipient(datiNotifica.get("nomeCognome"), tipoDestinatario, datiNotifica.get("codiceFiscale"), indirizzoFisico));
        }
        return recipients;
    }

    public ArrayList<NotificationPaymentItem> paymentsBuilder(int avvisoPagoPa, int F24, String costiNotifica) {
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
        File metaDatiPreLoad;
        if (costiNotifica.equalsIgnoreCase("false")) {
            metaDatiPreLoad = new File("src/test/resources/dataPopulation/fileUpload/METADATA_CORRETTO_FLAT.json");
        } else {
            metaDatiPreLoad = new File("src/test/resources/dataPopulation/fileUpload/METADATA_CORRETTO.json");
        }
        String sha256 = computeSha256(metaDatiPreLoad.getAbsolutePath().replace("\\", "/"));
        this.sha = sha256;
        PreLoadRequest preLoadRequest = new PreLoadRequest()
                .preloadIdx("0")
                .sha256(sha256)
                .contentType(APPLICATION_JSON);
        List<PreLoadRequest> preLoadRequestList = new ArrayList<>();
        preLoadRequestList.add(preLoadRequest);
        List<PreLoadResponse> response = restNotification.preLoadDocument(preLoadRequestList);
        Assert.assertNotNull("La chiamata per il preload dell'F24 non è andata a buon fine", response);
        log.info("PreLoad dell'F24 effettuato con successo");
        if (costiNotifica.equalsIgnoreCase("false")) {
            F24Payment f24 = new F24Payment(sha256, response.get(0).getKey(), "v1", false);
            restNotification.uploadDocumentF24(response.get(0).getUrl(), response.get(0).getSecret(), sha256, metaDatiPreLoad);
            if (payments.isEmpty() || payments.size() <= i) {
                NotificationPaymentItem payment = new NotificationPaymentItem(f24);
                payments.add(payment);
            } else {
                payments.get(i).setF24(f24);
            }
        } else {
            F24Payment f24 = new F24Payment(sha256, response.get(0).getKey(), "v1");
            restNotification.uploadDocumentF24(response.get(0).getUrl(), response.get(0).getSecret(), sha256, metaDatiPreLoad);
            if (payments.isEmpty() || payments.size() <= i) {
                NotificationPaymentItem payment = new NotificationPaymentItem(f24);
                payments.add(payment);
            } else {
                payments.get(i).setF24(f24);
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

    public String computeSha256(String resName) {
        try (InputStream stream = new BufferedInputStream(new FileInputStream(resName))) {
            return computeSha256(stream);
        } catch (IOException e) {
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
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("SHA-256 algorithm not found", e);
        }
    }

    private String bytesToBase64(byte[] hash) {
        byte[] encodedBytes = Base64.getEncoder().encode(hash);
        return new String(encodedBytes);
    }

}
