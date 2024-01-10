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
    private NotificationFeePolicyEnum notificationFeePolicy;
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
        this.notificationFeePolicy = notificationFeePolicy;
        this.group = "6564b9a671919a696157f2c0";
        this.senderDenomination = "Comune di Milano";
        this.senderTaxID = "00215150236";
        this.abstractS = "PAGAMENTO RATA IMU";
    }

    @JsonProperty("paProtocolNumber")
    public String getPaProtocolNumber() {
        return paProtocolNumber;
    }

    @JsonProperty("paProtocolNumber")
    public void setPaProtocolNumber(String value) {
        this.paProtocolNumber = value;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String value) {
        this.subject = value;
    }

    @JsonProperty("recipients")
    public ArrayList<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<Recipient> value) {
        this.recipients = value;
    }

    @JsonProperty("documents")
    public ArrayList<Document> getDocuments() {
        return documents;
    }

    @JsonProperty("documents")
    public void setDocuments(ArrayList<Document> value) {
        this.documents = value;
    }

    @JsonProperty("physicalCommunicationType")
    public PhysicalCommunicationTypeEnum getPhysicalCommunicationType() {
        return physicalCommunicationTypeEnum;
    }

    @JsonProperty("physicalCommunicationType")
    public void setPhysicalCommunicationType(PhysicalCommunicationTypeEnum value) {
        this.physicalCommunicationTypeEnum = value;
    }

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(String value) {
        this.group = value;
    }

    @JsonProperty("taxonomyCode")
    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    @JsonProperty("taxonomyCode")
    public void setTaxonomyCode(String value) {
        this.taxonomyCode = value;
    }

    @JsonProperty("notificationFeePolicy")
    public NotificationFeePolicyEnum getNotificationFeePolicy() {
        return notificationFeePolicy;
    }

    @JsonProperty("notificationFeePolicy")
    public void setNotificationFeePolicy(NotificationFeePolicyEnum value) {
        this.notificationFeePolicy = value;
    }

    @JsonProperty("senderDenomination")
    public String getSenderDenomination() {
        return senderDenomination;
    }

    @JsonProperty("senderDenomination")
    public void setSenderDenomination(String value) {
        this.senderDenomination = value;
    }

    @JsonProperty("senderTaxId")
    public String getSenderTaxID() {
        return senderTaxID;
    }

    @JsonProperty("senderTaxId")
    public void setSenderTaxID(String value) {
        this.senderTaxID = value;
    }

    @JsonProperty("abstract")
    public String getWelcomeAbstract() {
        return abstractS;
    }

    @JsonProperty("abstract")
    public void setWelcomeAbstract(String value) {
        this.abstractS = value;
    }

}
