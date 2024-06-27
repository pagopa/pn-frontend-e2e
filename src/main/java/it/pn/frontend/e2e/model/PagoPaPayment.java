package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class PagoPaPayment {
    private String noticeCode;
    private String creditorTaxId;
    private boolean applyCost;
    private NotificationPaymentAttachment attachment;

    public PagoPaPayment(String noticeCode) {
        this.noticeCode = noticeCode;
        this.creditorTaxId = "77777777777";
        this.applyCost = true;
        this.attachment = new NotificationPaymentAttachment();
    }

    public PagoPaPayment(String noticeCode, String sha256, String key, String versionToken) {
        this.noticeCode = noticeCode;
        this.creditorTaxId = "77777777777";
        this.applyCost = true;
        this.attachment = new NotificationPaymentAttachment(sha256, key, versionToken);
    }

    public PagoPaPayment(String noticeCode, String sha256, String key, String versionToken, boolean applyCost) {
        this.noticeCode = noticeCode;
        this.creditorTaxId = "77777777777";
        this.applyCost = applyCost;
        this.attachment = new NotificationPaymentAttachment(sha256, key, versionToken);
    }

    public PagoPaPayment(String noticeCode, String creditorTaxId, boolean applyCost, String sha256, String key, String versionToken) {
        this.noticeCode = noticeCode;
        this.creditorTaxId = creditorTaxId;
        this.applyCost = applyCost;
        this.attachment = new NotificationPaymentAttachment(sha256, key, versionToken);
    }

    public PagoPaPayment(String noticeCode, String creditorTaxId, boolean applyCost, NotificationPaymentAttachment attachment) {
        this.noticeCode = noticeCode;
        this.creditorTaxId = creditorTaxId;
        this.applyCost = applyCost;
        this.attachment = attachment;
    }
}
