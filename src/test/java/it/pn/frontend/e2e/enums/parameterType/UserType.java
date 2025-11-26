package it.pn.frontend.e2e.enums.parameterType;

import io.cucumber.java.ParameterType;
import it.pn.frontend.e2e.enums.User;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.presentation.models.LoginPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class UserType {

    @ParameterType("Utente")
    public User user(String username) {
       return User.fromUsername(username);
    }
}
