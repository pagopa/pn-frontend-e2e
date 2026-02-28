package it.pn.frontend.e2e.steps.send.login.component;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Writable;
import it.frontend.e2e.framework.web.domain.Component;
import it.pn.frontend.e2e.enums.User;

public interface OneIdLoginForm extends Component {

    @Selector("//*[@id=\"username\"]")
    Writable<String> username();

    @Selector("//*[@id=\"password\"]")
    Writable<String> password();

    @Selector("//*[@id=\"login-form\"]/div[4]/button[1]")
    Clickable submit();

    @Selector("//*[@id=\"consent-form\"]")
    OneIdPrivacyDialog oneIdPrivacyDialog();

    default void loginWith(User user) {
        this.username().writeAndAssert(user.getUsername());
        this.password().writeAndAssert(user.getPassword());
        this.submit().click();

        oneIdPrivacyDialog().accept();
    }
}



