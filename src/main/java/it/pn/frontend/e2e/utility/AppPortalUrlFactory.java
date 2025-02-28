package it.pn.frontend.e2e.utility;

import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.model.enums.AppPortalUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppPortalUrlFactory  {

    @Autowired
    private WebDriverConfig webDriverConfig;

    public String getPortalUrl(AppPortalUrl portal) {
        String env = webDriverConfig.getEnvironment();
        return portal.getUrl(env);
    }
}
