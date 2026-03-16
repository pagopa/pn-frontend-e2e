package it.pn.frontend.e2e.parameter_type;

import io.cucumber.java.ParameterType;
import it.frontend.e2e.framework.web.model.location.Url;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.steps.send.login.page.OneIdPage;
import it.pn.frontend.e2e.steps.send.mittenti.Dashboard;
import org.springframework.beans.factory.annotation.Value;

public class PageType {

    @Value("${url.selfcare.notifiche.dashboard}")
    private String mittentiBaseUrl;

    @Value("${token.mittente}")
    private String token;

    @ParameterType("LoginPage|Dashboard")
    public PageInfo page(String page) {
        return switch (page) {
            case "LoginPage" -> new PageInfo(Url.of(null), OneIdPage.class);
            case "Dashboard" -> new PageInfo(Url.of(mittentiBaseUrl+"#selfCareToken="+token), Dashboard.class);
            default -> throw new IllegalArgumentException("Invalid page");
        };
    }
}
