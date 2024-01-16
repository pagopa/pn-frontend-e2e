package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.pn.frontend.e2e.model.enums.NotificationFeePolicyEnum;
import it.pn.frontend.e2e.model.enums.PhysicalCommunicationTypeEnum;

import java.util.ArrayList;

public class NewNotification {
    private String paProtocolNumber;
    private String subject;
    private ArrayList<Recipient> recipients;
    private ArrayList<Document> documents;
    private PhysicalCommunicationTypeEnum physicalCommunicationTypeEnum;
    private String group;
    private String taxonomyCode;
    private NotificationFeePolicyEnum notificationFeePolicyEnum;
    private String senderDenomination;
    private String senderTaxID;
    private String abstractS;

    public NewNotification(String paProtocolNumber, String subject, ArrayList<Recipient> recipients, ArrayList<Document> documents,
                           PhysicalCommunicationTypeEnum physicalCommunicationTypeEnum, String taxonomyCode, NotificationFeePolicyEnum notificationFeePolicy) {
        this.paProtocolNumber = paProtocolNumber;
        this.subject = subject;
        this.recipients = recipients;
        this.documents = documents;
        this.physicalCommunicationTypeEnum = physicalCommunicationTypeEnum;
        this.taxonomyCode = taxonomyCode;
        this.notificationFeePolicyEnum = notificationFeePolicy;
        this.group = "6564b9a671919a696157f2c0";
        this.senderDenomination = "Comune di Milano";
        this.senderTaxID = "00215150236";
        this.abstractS = "PAGAMENTO RATA IMU";
    }

    public String getPaProtocolNumber() {
        return paProtocolNumber;
    }

    public void setPaProtocolNumber(String value) {
        this.paProtocolNumber = value;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String value) {
        this.subject = value;
    }

    public ArrayList<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<Recipient> value) {
        this.recipients = value;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> value) {
        this.documents = value;
    }

    public PhysicalCommunicationTypeEnum getPhysicalCommunicationType() {
        return physicalCommunicationTypeEnum;
    }

    public void setPhysicalCommunicationType(PhysicalCommunicationTypeEnum value) {
        this.physicalCommunicationTypeEnum = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String value) {
        this.group = value;
    }

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String value) {
        this.taxonomyCode = value;
    }

    public NotificationFeePolicyEnum getNotificationFeePolicy() {
        return notificationFeePolicyEnum;
    }

    public void setNotificationFeePolicy(NotificationFeePolicyEnum value) {
        this.notificationFeePolicyEnum = value;
    }

    public String getSenderDenomination() {
        return senderDenomination;
    }

    public void setSenderDenomination(String value) {
        this.senderDenomination = value;
    }

    public String getSenderTaxID() {
        return senderTaxID;
    }

    public void setSenderTaxID(String value) {
        this.senderTaxID = value;
    }

    public String getWelcomeAbstract() {
        return abstractS;
    }

    public void setWelcomeAbstract(String value) {
        this.abstractS = value;
    }

}
