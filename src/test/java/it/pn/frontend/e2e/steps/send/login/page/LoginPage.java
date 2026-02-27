package it.pn.frontend.e2e.steps.send.login.page;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.web.annotation.PageInfo;
import it.frontend.e2e.framework.web.domain.Component;
import it.frontend.e2e.framework.web.domain.Page;

@PageInfo(url = "/login")
public interface LoginPage extends Page {

    interface LoginForm extends Component {

    }

    @Selector(value = "#login-form")
    LoginForm form();
}
