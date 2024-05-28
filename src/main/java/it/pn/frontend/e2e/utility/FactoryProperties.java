package it.pn.frontend.e2e.utility;

import lombok.Data;

@Data
public class FactoryProperties {
    private String codiceFiscaleCesare = System.getProperty("codice.fiscale.cesare");
    private String codiceFiscaleLucrezia = System.getProperty("codice.fiscale.lucrezia");
    private String urlLoginPF = System.getProperty("url.login.pf");
    private String urlLoginPG = System.getProperty("url.login.pg");
    private String urlLoginPA = System.getProperty("url.login.pa");


    private String tokenCesare = System.getProperty("token.login.pf.cesare");
    private String tokenLucrezia = System.getProperty("token.login.pf.lucrezia");

    private String tokenConvivio = System.getProperty("token.login.pg.convivio");
    private String tokenEpistolae = System.getProperty("token.login.pg.epistolae");
}
