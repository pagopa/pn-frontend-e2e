package it.pn.frontend.e2e.model.enums;

public enum AppPortal {
    PA("https://selfcare.", "#selfCareToken=", TokenLogin.PA_TOKEN.getToken()),
    PF("https://cittadini.", "#token=", TokenLogin.PF_DELEGATE_TOKEN.getToken()),
    PG("https://imprese.", "#selfCareToken=", TokenLogin.PG_DELEGATE_TOKEN.getToken()),
    HELPDESK("https://helpdesk.");
    public final String url;

    private AppPortal(String url, String paramToken, String token) {
        String env = System.getProperty("environment");
        this.url = url + env + ".notifichedigitali.it/" + paramToken + token;
    }

    private AppPortal(String url) {
        String env = System.getProperty("environment");
        this.url = url + env + ".notifichedigitali.it/";
    }
}
