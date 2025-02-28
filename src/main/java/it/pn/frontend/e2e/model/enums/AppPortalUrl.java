package it.pn.frontend.e2e.model.enums;

import it.pn.frontend.e2e.utility.TokenService;
import it.pn.frontend.e2e.utility.TokenServiceProvider;

public enum AppPortalUrl {

    PA_URL("https://selfcare.", "#selfCareToken=", TokenLogin.PA_TOKEN),
    PF_URL("https://cittadini.", "#token=", TokenLogin.PF_DELEGATE_TOKEN),
    PG_URL("https://imprese.", "#selfCareToken=", TokenLogin.PG_DELEGATE_TOKEN),
    HELPDESK("https://helpdesk.");

    private final String baseUrl;
    private final String paramToken;
    private final TokenLogin tokenLogin;

    AppPortalUrl(String baseUrl, String paramToken, TokenLogin tokenLogin) {
        this.baseUrl = baseUrl;
        this.paramToken = paramToken;
        this.tokenLogin = tokenLogin;
    }

    AppPortalUrl(String baseUrl) {
        this(baseUrl, "", null);
    }

    public String getUrl(String env) {
        String token = "";
        if (tokenLogin != null) {
            TokenService tokenService = TokenServiceProvider.getTokenService();
            token = tokenService.getToken(tokenLogin);
        }
        return baseUrl + env + ".notifichedigitali.it/" + paramToken + token;
    }
}
