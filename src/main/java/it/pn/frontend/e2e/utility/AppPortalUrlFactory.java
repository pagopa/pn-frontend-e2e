package it.pn.frontend.e2e.utility;

import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.model.enums.AppPortalUrl;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeaderPFSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppPortalUrlFactory  {
    private static final Logger logger = LoggerFactory.getLogger("AppPortalUrlFactory");

    @Autowired
    private WebDriverConfig webDriverConfig;

    public String getPortalUrl(AppPortalUrl portal) {
        String env = webDriverConfig.getEnvironment();
        logger.info("env: {}", env);
        String token = "";
        switch (portal) {
            case PA_URL -> {
                token = env.equalsIgnoreCase("dev") ? webDriverConfig.getTokendevMittente() : webDriverConfig.getTokentestMittente();
                break;
            }
            case PF_URL -> {
                logger.info("Switch a portale PF");
                token = env.equalsIgnoreCase("dev") ? webDriverConfig.getTokendevPFDelegante() : webDriverConfig.getTokentestPFDelegante();
                break;
            }
            case PG_URL -> {
                token = env.equalsIgnoreCase("dev") ? webDriverConfig.getTokendevPGDelegante() : webDriverConfig.getTokentestPGDelegante();
                break;
            }
            case HELPDESK -> {
//                token = env.equalsIgnoreCase("dev") ? webDriverConfig.getToken) : webDriverConfig.getTokentestPGDelegante();
                break;
            }
            default -> {
                logger.error("Tipologia di portale non specificato o errato!");
                Assertions.fail("Tipologia di portale non specificato o errato!");
            }

        }
        return portal.getUrl(env, token);
    }
}
