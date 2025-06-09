package it.pn.frontend.e2e.model.recipients;

import lombok.Data;

@Data
public class Mittente {
    private String url;
    private String user;
    private String pwd;
    private String provider;
    private String comune;
    private String comuneViggiu;
    private String ambiente; // ???
    private String codiceFiscale;
    private String codiceAvviso;
    private String codiceApiKeyTEST;// ???
    private String codiceApiKeyDEV;// ???
    private String codiceApiKeyUAT;

}
