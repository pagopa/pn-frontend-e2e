package it.pn.frontend.e2e.steps.send.login.component;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.domain.Component;

@Selector("//*[@id=\"onetrust-banner-sdk\"]")
public interface OneTrustBanner extends Component {

    @Selector("//*[@id=\"onetrust-accept-btn-handler\"]")
    Clickable acceptButton();

    @Selector("//*[@id=\"onetrust-reject-all-handler\"]")
    Clickable rejectButton();

    default void accept(){
        acceptButton().click();
    }

    default void reject(){
        rejectButton().click();
    }
}
