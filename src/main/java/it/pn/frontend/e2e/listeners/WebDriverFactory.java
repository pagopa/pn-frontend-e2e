package it.pn.frontend.e2e.listeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.utility.CookieConfig;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
@Configuration
public class WebDriverFactory {
    private static final Logger logger = LoggerFactory.getLogger("WebDriverFactory");

    @Getter
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    public static WebDriver getDriver(ChromeOptions chromeOptions, EdgeOptions edgeOptions, FirefoxOptions firefoxOptions ) {
        if (driverThreadLocal.get() == null) {

            if (chromeOptions!= null){
                ChromeDriver driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driverThreadLocal.set(driver);
            } else if (edgeOptions != null) {
                EdgeDriver driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

                driverThreadLocal.set(driver);
            } else if (firefoxOptions != null) {
                FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                logger.info("Firefox driver started");

                driverThreadLocal.set(driver);
            }
        }
        logger.info("WebDriverFactory..."+driverThreadLocal.get());
        return driverThreadLocal.get();
    }



    public static void quitDriver() {
        logger.info("Quit WebDriverFactory..."+driverThreadLocal.get());
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}