package it.pn.frontend.e2e.model;

import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;
import lombok.Data;

import java.util.ArrayList;

@Data
public class NewNotificationRequest {
    private String paProtocolNumber;
    private String subject;
    private ArrayList<Recipient> recipients;
    private ArrayList<Document> documents;
    private PhysicalCommunicationTypeEnum physicalCommunicationType;
    private String group;
    private String taxonomyCode;
    private NotificationFeePolicyEnum notificationFeePolicy;
    private String senderDenomination;
    private String senderTaxId;
    private String abstractS;
    private int paFee;
    private int vat;

    public NewNotificationRequest(String paProtocolNumber, String subject, ArrayList<Recipient> recipients, ArrayList<Document> documents,
                                  PhysicalCommunicationTypeEnum physicalCommunicationType, String taxonomyCode, NotificationFeePolicyEnum notificationFeePolicy) {
        this.paProtocolNumber = paProtocolNumber;
        this.subject = subject;
        this.recipients = recipients;
        this.documents = documents;
        this.physicalCommunicationType = physicalCommunicationType;
        this.taxonomyCode = taxonomyCode;
        this.notificationFeePolicy = notificationFeePolicy;
        this.group = "6564b9a671919a696157f2c0";
        this.senderDenomination = "Comune di Palermo";
        this.senderTaxId = "00215150236";
        this.abstractS = "PAGAMENTO RATA IMU";
        this.paFee = 0;
        this.vat = 0;
    }

    public NewNotificationRequest(String paProtocolNumber, String subject, ArrayList<Recipient> recipients, ArrayList<Document> documents,
                                  PhysicalCommunicationTypeEnum physicalCommunicationType, String taxonomyCode, NotificationFeePolicyEnum notificationFeePolicy, ArrayList<NotificationPaymentItem> payments) {
        this.paProtocolNumber = paProtocolNumber;
        this.subject = subject;
        this.recipients = recipients;
        this.documents = documents;
        this.physicalCommunicationType = physicalCommunicationType;
        this.taxonomyCode = taxonomyCode;
        this.notificationFeePolicy = notificationFeePolicy;
        this.group = "6564b9a671919a696157f2c0";
        this.senderDenomination = "Comune di Milano";
        this.senderTaxId = "00215150236";
        this.abstractS = "PAGAMENTO RATA IMU";
        this.paFee = 0;
        this.vat = 0;
    }
}
