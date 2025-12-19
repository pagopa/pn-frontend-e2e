package it.pn.frontend.e2e.enums.parameter_type;

import io.cucumber.java.ParameterType;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.presentation.model.login_page.LoginPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageType {

    @Value("${url.mittente}")
    private String baseUrl;

    @ParameterType("LoginPage")
    public PageInfo page(String page) {
        return switch (page) {
            case "LoginPage" -> new PageInfo(baseUrl, LoginPage.class);
            default -> throw new IllegalArgumentException("Invalid page");
        };
    }
}
