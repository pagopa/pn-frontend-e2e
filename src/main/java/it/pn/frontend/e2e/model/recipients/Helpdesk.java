package it.pn.frontend.e2e.model.recipients;

import lombok.Data;

@Data
public class Helpdesk {
    private String url;
    private String userDev;
    private String pwdDev;
    private String userUat;
    private String pwdUat;
    private String userTest;
    private String pwdTest;
    private String cfPf;

}
