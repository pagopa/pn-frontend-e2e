package it.pn.frontend.e2e.steps.send.mittenti;

import it.frontend.e2e.framework.annotation.Selector;
import it.frontend.e2e.framework.web.domain.Page;

public interface Dashboard extends Page {

    @Selector(value = "#dashboard-header")
    Readable header();

}
