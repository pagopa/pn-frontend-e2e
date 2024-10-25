package it.pn.frontend.e2e.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
*Modifiche principali:
Iniezione di WebDriver: Ho rimosso tutte le istanze di new ChromeDriver(), new FirefoxDriver(), ecc., e ho creato un bean in una classe separata (che andremo a configurare subito dopo) che gestisce la creazione del WebDriver.

Rimozione di new per altre dipendenze: Ho sostituito la creazione di oggetti come CookieConfig, RestDelegation e RestContact con l'iniezione di dipendenze utilizzando @Autowired.

Configurazione di un WebDriver come Bean
*
*
* */
@Getter
@Configuration
@PropertySource( value = "file:config/bearer-token-test.properties", ignoreResourceNotFound = true )
public class BearerTokenConfig {


}
