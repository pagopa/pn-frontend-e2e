package it.pn.frontend.e2e.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Getter
@Configuration
@PropertySource(value = "file:config/user-password-test.properties", ignoreResourceNotFound = true)
public class UserPasswordConfig {


}
