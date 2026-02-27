package it.pn.frontend.e2e.enums.parameter_type;

import io.cucumber.java.ParameterType;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.steps.send.login.page.LoginPage;
import it.pn.frontend.e2e.steps.send.mittenti.Dashboard;
import org.springframework.beans.factory.annotation.Value;

public class PageType {

    @Value("${url.mittente}")
    private String mittentiBaseUrl;

    @Value("${url.login}")
    private String loginUrl;

    @ParameterType("LoginPage|Dashboard")
    public PageInfo page(String page) {
        return switch (page) {
            case "LoginPage" -> new PageInfo(WebLocation.of(loginUrl), LoginPage.class);
            case "Dashboard" -> new PageInfo(WebLocation.of(""), Dashboard.class);
            default -> throw new IllegalArgumentException("Invalid page");
        };
    }
}
