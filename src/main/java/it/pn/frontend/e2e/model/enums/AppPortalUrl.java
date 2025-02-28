package it.pn.frontend.e2e.model.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum AppPortalUrl {


    PA_URL("https://selfcare.", "#selfCareToken=", "pa_token"),
    PF_URL("https://cittadini.", "#token=", "pf_delegate_token"),
    PG_URL("https://imprese.", "#selfCareToken=", "pg_delegate_token"),
    HELPDESK("https://helpdesk.");

    private final String baseUrl;
    private final String paramToken;
    private final String tokenKey;

    private static final Logger logger = LoggerFactory.getLogger("AppPortalUrl");


    AppPortalUrl(String baseUrl, String paramToken, String tokenKey) {
        this.baseUrl = baseUrl;
        this.paramToken = paramToken;
        this.tokenKey = tokenKey;
    }

    AppPortalUrl(String baseUrl) {
        this(baseUrl, "", null);
    }


    public String getUrl(String env, String token) {
        logger.info("sono dentro il metodo getUrl");
        logger.info("Url base: {}",baseUrl + env + ".notifichedigitali.it/" + paramToken + token);
        return baseUrl + env + ".notifichedigitali.it/" + paramToken + token;
    }

}
