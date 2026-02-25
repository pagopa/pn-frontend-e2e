package it.pn.frontend.e2e.steps.send.login.page;

import it.pn.frontend.e2e.framework.annotation.Selector;

import it.pn.frontend.e2e.framework.web.capability.Readable;
import it.pn.frontend.e2e.framework.web.domain.Component;
import it.pn.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.framework.web.annotation.PageInfo;

@PageInfo(url = "/login")
public interface LoginPage extends Page {

    interface LoginForm extends Component, Readable {

    }

    @Selector(value = "#login-form", capabilities = {Readable.class})
    LoginForm form();
}
