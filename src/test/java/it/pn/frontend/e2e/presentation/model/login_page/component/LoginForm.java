package it.pn.frontend.e2e.presentation.model.login_page.component;

import it.pn.frontend.e2e.presentation.annotation.DomAction;
import it.pn.frontend.e2e.presentation.annotation.DomField;
import it.pn.frontend.e2e.presentation.model.common.field.ReadWriteField;
import it.pn.frontend.e2e.presentation.model.common.component.Form;

public interface LoginForm extends Form {

    @DomField(selector = "#username")
    ReadWriteField<String> username();

    @DomField(selector = "#password")
    ReadWriteField<String> password();

    @DomField(selector = "#comune")
    ReadWriteField<String> comune();

    @Override
    @DomAction("#login-button")
    void submit();
}
