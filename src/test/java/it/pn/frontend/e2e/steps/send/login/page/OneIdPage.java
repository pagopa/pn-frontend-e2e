package it.pn.frontend.e2e.steps.send.login.page;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.domain.*;
import it.pn.frontend.e2e.enums.User;

public interface OneIdPage extends Page {

    @Selector("//*[@id=\"root\"]/div/div[2]")
    interface AuthArea extends Component {

        @Selector("/div[1]/div/h3")
        Readable<String> header();

        @Selector("/div[3]/div[1]")
        Clickable spidButton();
    }

    AuthArea authArea();

    default void loginWithSpid(User user) {
        authArea().spidButton().click();
    }
}
