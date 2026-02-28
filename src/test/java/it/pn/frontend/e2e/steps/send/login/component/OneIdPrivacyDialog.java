package it.pn.frontend.e2e.steps.send.login.component;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.domain.Component;

public interface OneIdPrivacyDialog extends Component {

    @Selector("//*[@id=\"consent-form\"]/div[2]/button[2]")
    Clickable acceptButton();

    @Selector("//*[@id=\"consent-form\"]/div[2]/button[1]")
    Clickable rejectButton();

    default void accept(){
        acceptButton().click();
    }

    default void reject(){
        rejectButton().click();
    }
}
