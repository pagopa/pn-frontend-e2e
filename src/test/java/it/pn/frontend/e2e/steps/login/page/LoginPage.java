package it.pn.frontend.e2e.steps.login.page;

import it.pn.frontend.e2e.framework.annotation.Selector;

import it.pn.frontend.e2e.framework.web.capability.handler.Readable;
import it.pn.frontend.e2e.framework.web.domain.annotation.PageInfo;
import it.pn.frontend.e2e.framework.web.domain.ui.*;

@PageInfo(url = "/login")
public interface LoginPage extends Page {

    interface LoginForm extends Component, Readable {

    }

    @Selector(value = "#login-form", capabilities = {Readable.class})
    LoginForm form();
}
