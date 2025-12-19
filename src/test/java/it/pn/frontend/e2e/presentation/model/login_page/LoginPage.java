package it.pn.frontend.e2e.presentation.model.login_page;

import it.pn.frontend.e2e.presentation.annotation.DomField;
import it.pn.frontend.e2e.presentation.model.common.page.Page;
import it.pn.frontend.e2e.presentation.model.login_page.component.LoginForm;

public interface LoginPage extends Page {

    @DomField(selector = "#login-form")
    LoginForm form();
}





