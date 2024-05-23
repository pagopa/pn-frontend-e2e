package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationPaymentItem {
    private PagoPaPayment pagoPaPayment;
    private F24 f24;

    public NotificationPaymentItem() {
        this.pagoPaPayment = new PagoPaPayment();
        this.f24 = new F24();
    }

    public NotificationPaymentItem(PagoPaPayment pagoPaPayment) {
        this.pagoPaPayment = pagoPaPayment;
    }

    public NotificationPaymentItem(F24 f24) {
        this.f24 = f24;
    }

    public void NotificationPaymentItemPagoPa() {
        this.pagoPaPayment = new PagoPaPayment();
    }

    public void NotificationPaymentItemF24() {
        this.f24 = new F24();
    }

    public void NotificationPaymentItemDouble(String noticeCode, String creditorTaxId, boolean applyCostPagoPaPayment, String sha256PagoPaPayment, String keyPagoPaPayment, String versionTokenPagoPaPayment, String title, boolean applyCostF24, String sha256F24, String keyF24, String versionTokenF24) {
        this.pagoPaPayment = new PagoPaPayment(noticeCode, creditorTaxId, applyCostPagoPaPayment, sha256PagoPaPayment, keyPagoPaPayment, versionTokenPagoPaPayment);
        this.f24 = new F24(title, applyCostF24, sha256F24, keyF24, versionTokenF24);
    }

    public void NotificationPaymentItemPagoPa(String noticeCode, String creditorTaxId, boolean applyCost, String sha256, String key, String versionToken) {
        this.pagoPaPayment = new PagoPaPayment(noticeCode, creditorTaxId, applyCost, sha256, key, versionToken);
    }

    public void NotificationPaymentItemF24(String title, boolean applyCost, String sha256, String key, String versionToken) {
        this.f24 = new F24(title, applyCost, sha256, key, versionToken);
    }
}
