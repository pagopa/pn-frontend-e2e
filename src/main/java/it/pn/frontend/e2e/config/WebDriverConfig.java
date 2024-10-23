package it.pn.frontend.e2e.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
@PropertySource( value = "file:config/configuration.properties", ignoreResourceNotFound = true )
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

}
