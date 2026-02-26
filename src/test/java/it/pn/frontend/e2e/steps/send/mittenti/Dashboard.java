package it.pn.frontend.e2e.steps.send.mittenti;

import it.pn.frontend.e2e.framework.annotation.Selector;
import it.pn.frontend.e2e.framework.web.annotation.PageInfo;
import it.pn.frontend.e2e.framework.web.domain.Page;

@PageInfo(url = "/dashboard")
public interface Dashboard extends Page {

    @Selector(value = "#dashboard-header")
    Readable header();

    @Override
    default boolean assertLoaded() {
        return true;
    }
}
