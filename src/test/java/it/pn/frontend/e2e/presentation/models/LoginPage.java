package it.pn.frontend.e2e.presentation.models;

import it.pn.frontend.e2e.presentation.aop.DomAction;
import it.pn.frontend.e2e.presentation.aop.DomField;

public interface LoginPage {

    @DomField("#username")
    String getUsername();

    @DomField("#username")
    void setUsername(String value);

    @DomField("#password")
    String getPassword();

    @DomField("#password")
    void setPassword(String value);

    @DomAction("#login-button")
    void login();

    @DomField("#comune")
    String getComune();

    @DomField("#comune")
    void setComune(String comune);
}





