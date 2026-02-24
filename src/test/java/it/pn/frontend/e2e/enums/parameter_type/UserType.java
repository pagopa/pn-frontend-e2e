package it.pn.frontend.e2e.enums.parameter_type;

import io.cucumber.java.ParameterType;
import it.pn.frontend.e2e.enums.User;

public class UserType {

    @ParameterType("Utente")
    public User user(String username) {
       return User.fromUsername(username);
    }
}
