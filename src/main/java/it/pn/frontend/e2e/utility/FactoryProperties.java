package it.pn.frontend.e2e.utility;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FactoryProperties {
    private final String codiceFiscaleCesare = System.getProperty("codice.fiscale.cesare");
    private final String codiceFiscaleLucrezia = System.getProperty("codice.fiscale.lucrezia");
    private final String urlLoginPF = System.getProperty("url.login.pf");
    private final String urlLoginPG = System.getProperty("url.login.pg");
    private final String urlLoginPA = System.getProperty("url.login.pa");


    private final String tokenCesare = System.getProperty("token.login.pf.cesare");
    private final String tokenLucrezia = System.getProperty("token.login.pf.lucrezia");

    private final String tokenConvivio = System.getProperty("token.login.pg.convivio");
    private final String tokenEpistolae = System.getProperty("token.login.pg.epistolae");
}
