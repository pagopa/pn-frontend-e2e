package it.pn.frontend.e2e.utility;

import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.model.enums.TokenLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private WebDriverConfig webDriverConfig;

    public String getToken(TokenLogin tokenLogin) {
        String env = webDriverConfig.getEnvironment();
        return tokenLogin.getToken(env);
    }
}
