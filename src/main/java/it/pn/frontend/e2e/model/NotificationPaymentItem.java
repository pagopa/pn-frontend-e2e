package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationPaymentItem {
    private PagoPaPayment pagoPa;
    private F24Payment f24;

    public NotificationPaymentItem() {
        this.pagoPa = new PagoPaPayment();
        this.f24 = new F24Payment();
    }

    public NotificationPaymentItem(PagoPaPayment pagoPa) {
        this.pagoPa = pagoPa;
    }

    public NotificationPaymentItem(F24Payment f24) {
        this.f24 = f24;
    }

    public NotificationPaymentItem(PagoPaPayment pagoPa, F24Payment f24) {
        this.pagoPa = pagoPa;
        this.f24 = f24;
    }
}
