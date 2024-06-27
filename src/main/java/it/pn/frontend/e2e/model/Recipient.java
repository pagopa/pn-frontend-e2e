package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.pn.frontend.e2e.model.enums.RecipientTypeEnum;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipient {
    private RecipientTypeEnum recipientType;
    private String taxId;
    private String denomination;
    private PhysicalAddress physicalAddress;
    private DigitalDomicile digitalDomicile;
    private List<NotificationPaymentItem> payments;

    public Recipient() {
        this.recipientType = null;
        this.taxId = null;
        this.denomination = null;
        this.physicalAddress = null;
        this.digitalDomicile = null;
        this.payments = null;
    }

    public Recipient(String denomination, RecipientTypeEnum recipientType, String taxId, PhysicalAddress physicalAddress, DigitalDomicile digitalDomicile) {
        this.recipientType = recipientType;
        this.taxId = taxId;
        this.denomination = denomination;
        this.physicalAddress = physicalAddress;
        this.digitalDomicile = digitalDomicile;
        this.payments = null;
    }

    public Recipient(String denomination, RecipientTypeEnum recipientType, String taxId) {
        this.recipientType = recipientType;
        this.taxId = taxId;
        this.denomination = denomination;
        this.physicalAddress = new PhysicalAddress();
        this.digitalDomicile = null;
        this.payments = null;
    }

    public Recipient(String denomination, RecipientTypeEnum recipientType, String taxId, PhysicalAddress physicalAddress) {
        this.recipientType = recipientType;
        this.taxId = taxId;
        this.denomination = denomination;
        this.physicalAddress = physicalAddress;
        this.digitalDomicile = null;
        this.payments = null;
    }

    public Recipient(String denomination, RecipientTypeEnum recipientType, String taxId, PhysicalAddress physicalAddress, DigitalDomicile digitalDomicile, List<NotificationPaymentItem> payments) {
        this.recipientType = recipientType;
        this.taxId = taxId;
        this.denomination = denomination;
        this.physicalAddress = physicalAddress;
        this.digitalDomicile = digitalDomicile;
        this.payments = payments;
    }
}
