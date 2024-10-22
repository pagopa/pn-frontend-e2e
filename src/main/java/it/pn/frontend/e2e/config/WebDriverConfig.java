package it.pn.frontend.e2e.config;

import io.github.bonigarcia.wdm.WebDriverManager;
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
/*
*Modifiche principali:
Iniezione di WebDriver: Ho rimosso tutte le istanze di new ChromeDriver(), new FirefoxDriver(), ecc., e ho creato un bean in una classe separata (che andremo a configurare subito dopo) che gestisce la creazione del WebDriver.

Rimozione di new per altre dipendenze: Ho sostituito la creazione di oggetti come CookieConfig, RestDelegation e RestContact con l'iniezione di dipendenze utilizzando @Autowired.

Configurazione di un WebDriver come Bean
*
*
* */
@Configuration
public class WebDriverConfig {

    @Value("${test.headless:false}")
    private boolean headless;

    @Value("${os.name}")
    private String os;

    @Bean
    public WebDriver webDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            throw new IllegalArgumentException("La variabile browser non è stata impostata");
        }

        return switch (browser.toLowerCase()) {
            case "firefox" -> getFirefoxDriver();
            case "chrome" -> getChromeDriver();
            case "edge" -> getEdgeDriver();
            default -> throw new IllegalArgumentException("Browser non supportato: " + browser);
        };
    }

    private WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }
        return new FirefoxDriver(options);
    }

    private WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }
        return new ChromeDriver(options);
    }

    private WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("inPrivate");
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }
        return new EdgeDriver(options);
    }
}
