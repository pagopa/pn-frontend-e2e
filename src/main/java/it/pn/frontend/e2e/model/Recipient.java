package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.pn.frontend.e2e.model.enums.RecipientTypeEnum;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipient {
    private String denomination;
    private RecipientTypeEnum recipientType;
    private String taxId;
    private PhysicalAddress physicalAddress;
    private DigitalDomicile digitalDomicile;
    private List<NotificationPaymentItem> payments;

    public Recipient() {
        this.denomination = "Gaio Giulio Cesare";
        this.recipientType = RecipientTypeEnum.PF;
        this.taxId = "CSRGGL44L13H501E";
        this.physicalAddress = new PhysicalAddress();
        this.digitalDomicile = new DigitalDomicile();
        this.payments = null;
    }

    public Recipient(String denomination, RecipientTypeEnum recipientType, String taxId, PhysicalAddress physicalAddress, DigitalDomicile digitalDomicile) {
        this.denomination = denomination;
        this.recipientType = recipientType;
        this.taxId = taxId;
        this.physicalAddress = physicalAddress;
        this.digitalDomicile = digitalDomicile;
        this.payments = null;
    }

    public Recipient(String denomination, RecipientTypeEnum recipientType, String taxId, PhysicalAddress physicalAddress, DigitalDomicile digitalDomicile, List<NotificationPaymentItem> payments) {
        this.denomination = denomination;
        this.recipientType = recipientType;
        this.taxId = taxId;
        this.physicalAddress = physicalAddress;
        this.digitalDomicile = digitalDomicile;
        this.payments = payments;
    }
}
