package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.pn.frontend.e2e.model.enums.RecipientTypeEnum;

public class Recipient {
    private String denomination;
    private RecipientTypeEnum recipientType;
    private String taxID;
    private PhysicalAddress physicalAddress;
    private DigitalDomicile digitalDomicile;

    public Recipient() {
        this.denomination = "Gaio Giulio Cesare";
        this.recipientType = RecipientTypeEnum.PF;
        this.taxID = "CSRGGL44L13H501E";
        this.physicalAddress = new PhysicalAddress();
        this.digitalDomicile = new DigitalDomicile();
    }

    public Recipient(String denomination, RecipientTypeEnum recipientType, String taxID, PhysicalAddress physicalAddress, DigitalDomicile digitalDomicile) {
        this.denomination = denomination;
        this.recipientType = recipientType;
        this.taxID = taxID;
        this.physicalAddress = physicalAddress;
        this.digitalDomicile = digitalDomicile;
    }

    @JsonProperty("denomination")
    public String getDenomination() {
        return denomination;
    }

    @JsonProperty("denomination")
    public void setDenomination(String value) {
        this.denomination = value;
    }

    @JsonProperty("recipientType")
    public RecipientTypeEnum getRecipientType() {
        return recipientType;
    }

    @JsonProperty("recipientType")
    public void setRecipientType(RecipientTypeEnum value) {
        this.recipientType = value;
    }

    @JsonProperty("taxId")
    public String getTaxID() {
        return taxID;
    }

    @JsonProperty("taxId")
    public void setTaxID(String value) {
        this.taxID = value;
    }

    @JsonProperty("physicalAddress")
    public PhysicalAddress getPhysicalAddress() {
        return physicalAddress;
    }

    @JsonProperty("physicalAddress")
    public void setPhysicalAddress(PhysicalAddress value) {
        this.physicalAddress = value;
    }

    @JsonProperty("digitalDomicile")
    public DigitalDomicile getDigitalDomicile() {
        return digitalDomicile;
    }

    @JsonProperty("digitalDomicile")
    public void setDigitalDomicile(DigitalDomicile value) {
        this.digitalDomicile = value;
    }
}
