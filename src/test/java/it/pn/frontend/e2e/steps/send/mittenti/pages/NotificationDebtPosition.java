package it.pn.frontend.e2e.steps.send.mittenti.pages;

import it.frontend.e2e.framework.annotation.location.web.Url;
import it.frontend.e2e.framework.annotation.selector.XPath;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.model.NotificationData;

import org.assertj.core.api.Assertions;

@Url("${url.selfcare.notifiche.base}/dashboard/nuova-notifica#selfCareToken=${token.mittente}")
public interface NotificationDebtPosition extends Page {

    @XPath("//*[@data-testid='titleBox']")
    Readable<String> header();

    @XPath("//label[@data-testid='paymentModel'][1]")
    Clickable selectPagoPa();

    @XPath("//label[@data-testid='paymentModel'][2]")
    Clickable selectF24();

    @XPath("//label[@data-testid='paymentModel'][3]")
    Clickable selectPagoPaAndF24();

    @XPath("//label[@data-testid='paymentModel'][4]")
    Clickable selectNoPayment();

    @XPath("//*[@data-testid='step-submit']")
    Clickable continueButton();

    @XPath("//*[@data-testid='previous-step']")
    Clickable backButton();

    default void selectPaymentTypeAndSubmit(String paymentType) {
        switch (paymentType) {
            case "PAGO_PA"       -> selectPagoPa().click();
            case "F24"           -> selectF24().click();
            case "PAGO_PA_F24"   -> selectPagoPaAndF24().click();
            case "NOTHING"       -> selectNoPayment().click();
            default -> throw new IllegalArgumentException(
                "Unknown payment type: " + paymentType
            );
        }
        continueButton().click();
    }

    default void selectPaymentTypeAndSubmit(NotificationData data) {
        selectPaymentTypeAndSubmit(data.getPaymentType());
    }

    @Override
    default void assertLoaded() {
        header().readAndAssert(h -> {
            Assertions.assertThat(h).isNotNull();
            Assertions.assertThat(h.getText())
                      .isIn("Invia una nuova notifica", "Send a new notification");
        });
    }
}
