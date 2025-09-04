package it.pn.frontend.e2e.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Getter
@Configuration
@PropertySource(value = "file:config/configuration.properties", ignoreResourceNotFound = true)
public class WebDriverConfig {

    @Value("${browser}")
    private String browser;

    @Value("${environment}")
    private String environment;

    @Value("${cookie.config}")
    private String cookie;

    @Value("${headless}")
    @Setter
    private String headless;

    @Value("${downloadFilePath}")
    private String downloadFilePath;

    @Value("${loadComponentWaitTime}")
    private String loadComponentWaitTime;

    @Value("${apiBaseUrl}")
    private String baseUrl;

    @Value("${ragione.sociale.baldassarre}")
    private String ragioneSocialeBaldassarre;

    @Value("${url.login.pg.test}")
    private String baseUrlPgTest;

    @Value("${url.login.pf.test}")
    private String baseUrlPfTest;

    @Value("${pn.bearer-token.tokentestPFDelegante}")
    private String tokentestPFDelegante;

    @Value("${pn.bearer-token.tokentestPFDelegato}")
    private String tokentestPFDelegato;

    @Value("${pn.bearer-token.tokentestPGDelegante}")
    private String tokentestPGDelegante;

    @Value("${pn.bearer-token.tokentestPGDelegato}")
    private String tokentestPGDelegato;

    @Value("${pn.bearer-token.tokentestMittente}")
    private String tokentestMittente;

    @Value("${pn.bearer-token.tokentestMittenteViggiu}")
    private String tokentestMittenteViggiu;

    @Value("${pn.bearer-token.tokendevMittenteViggiu}")
    private String tokendevMittenteViggiu;

    @Value("${pn.bearer-token.tokentestPFColombo}")
    private String tokentestPFColombo;

    @Value("${pn.bearer-token.tokentestRaddista1}")
    private String tokentestRaddista1;

    @Value("${url.selfcare}")
    private String urlSelfCare;

    @Value("${pn.user.cesare}")
    private String userCesare;

    @Value("${pn.pwd.cesare}")
    private String pwdCesare;

    @Value("${pn.user.lucrezia}")
    private String userLucrezia;

    @Value("${pn.pwd.lucrezia}")
    private String pwdLucrezia;

    @Value("${pn.user.dante}")
    private String userDante;

    @Value("${pn.pwd.dante}")
    private String pwdDante;

    @Value("${pn.user.petrarca}")
    private String userPetrarca;

    @Value("${pn.pwd.petrarca}")
    private String pwdPetrarca;

    @Value("${pn.user.mittente}")
    private String userMittente;

    @Value("${pn.pwd.mittente}")
    private String pwdMittente;

    @Value("${pn.user.mittenteViggiu}")
    private String userMittenteViggiu;

    @Value("${pn.pwd.mittenteViggiu}")
    private String pwdMittenteViggiu;

    @Value("${pn.userUat.helpdesk}")
    private String userUatHelpdesk;

    @Value("${pn.pwdUat.helpdesk}")
    private String pwdUatHelpdesk;

    @Value("${pn.userTest.helpdesk}")
    private String userTestHelpdesk;

    @Value("${pn.pwdTest.helpdesk}")
    private String pwdTestHelpdesk;

    @Value("${url.mittente}")
    private String urlMittente;
    @Value("${url.helpdesk.test.notifichedigitali}")
    private String urlHelpdeskTestNotifichedigitali;

    @Value("${codice.iun}")
    private String codiceIun;


    @Value("${codice.iun.n1}")
    private String codiceIunN1;

    @Value("${codice.iun.n2}")
    private String codiceIunN2;

    @Value("${codice.iun.n3}")
    private String codiceIunN3;

    @Value("${pn.externalChannels.base-url}")
    private String externalChannels;


}
