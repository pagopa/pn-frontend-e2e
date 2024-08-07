package it.pn.frontend.e2e.model.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.pn.frontend.e2e.model.payments.F24Payment;
import it.pn.frontend.e2e.model.payments.PagoPaPayment;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationPaymentItem {
    private PagoPaPayment pagoPa;
    private F24Payment f24;

    public NotificationPaymentItem() {
        this.pagoPa = new PagoPaPayment(WebTool.generateNoticeCodeNumber());
        this.f24 = new F24Payment();
    }

    public NotificationPaymentItem(PagoPaPayment pagoPa) {
        this.pagoPa = pagoPa;
        this.f24 = null;
    }

    public NotificationPaymentItem(F24Payment f24) {
        this.pagoPa = null;
        this.f24 = f24;
    }

    public NotificationPaymentItem(PagoPaPayment pagoPa, F24Payment f24) {
        this.pagoPa = pagoPa;
        this.f24 = f24;
    }
}
