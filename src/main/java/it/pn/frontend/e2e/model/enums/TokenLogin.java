package it.pn.frontend.e2e.model.enums;


public enum TokenLogin {
    PF_DELEGATE_TOKEN("555", "11"),
    PF_DELEGATOR_TOKEN("555", "11"),
    PG_DELEGATE_TOKEN("555", "11"),
    PG_DELEGATOR_TOKEN("555", "11"),
    PA_TOKEN("555", "11");


    private final String devToken;
    private final String testToken;


    private TokenLogin(String devToken, String testToken) {
        this.devToken = devToken;
        this.testToken = testToken;
    }

    public String getToken() {
        return testToken;
    }

    public String getToken(String environment) {
        return environment.equalsIgnoreCase("dev") ? devToken : testToken;
    }
}
