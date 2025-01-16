package it.pn.frontend.e2e.model.enums;

import it.pn.frontend.e2e.config.WebDriverConfig;
import org.springframework.beans.factory.annotation.Autowired;

public enum AppPortal {
    PA("https://selfcare.", "#selfCareToken=", TokenLogin.PA_TOKEN.getToken()),
    PF("https://cittadini.", "#token=", TokenLogin.PF_DELEGATE_TOKEN.getToken()),
    PG("https://imprese.", "#selfCareToken=", TokenLogin.PG_DELEGATE_TOKEN.getToken()),
    HELPDESK("https://helpdesk.");
    public final String url;

    @Autowired
    private WebDriverConfig webDriverConfig;

    private AppPortal(String url, String paramToken, String token) {
        String env = webDriverConfig.getEnvironment();
        this.url = url + env + ".notifichedigitali.it/" + paramToken + token;
    }

    private AppPortal(String url) {
        String env = webDriverConfig.getEnvironment();
        this.url = url + env + ".notifichedigitali.it/";
    }
}
