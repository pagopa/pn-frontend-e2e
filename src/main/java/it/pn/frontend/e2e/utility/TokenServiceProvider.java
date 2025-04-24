package it.pn.frontend.e2e.utility;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceProvider {

    @Getter
    private static TokenService tokenService;

    @Autowired
    public TokenServiceProvider(TokenService tokenService) {
        TokenServiceProvider.tokenService = tokenService;
    }

}
