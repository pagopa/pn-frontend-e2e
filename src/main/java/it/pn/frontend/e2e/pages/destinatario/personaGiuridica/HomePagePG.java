package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class HomePagePG extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HomePagePG");


    @FindBy(xpath = "//button[@class = 'MuiButtonBase-root MuiFab-root MuiFab-circular MuiFab-sizeSmall MuiFab-primary css-ngdrb8']")
    List<WebElement> notificheDigitaliCardList;

    @Autowired
    private WebTool webTool;

    public HomePagePG(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadHomePagePGPage() {

       // WebElement titlePageBy = driver.findElement(By.xpath("//h4[contains(text(),'Panoramica')]"));
       // WebElement subtitlePageBy = driver.findElement(By.xpath("//h4[contains(text(),'Notifiche digitali')]"));
       // WebElement cardNotificheDevBy = driver.findElement(By.xpath("//h6[@aria-label='SEND - Notifiche Digitali DEV']"));
        getWebDriverWait(10).withMessage("il titolo Panoramica nella pagina home page riepilogo dati non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[contains(text(),'Panoramica')]"))));
        getWebDriverWait(10).withMessage("il sottotitolo nella pagina home page riepilogo dati non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[contains(text(),'Notifiche digitali')]"))));
        getWebDriverWait(10).withMessage("la card SEND - Notifiche Digitali DEV non è visibile ").until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//h6[@aria-label='SEND - Notifiche Digitali DEV']"))));

        //Aggiunta wait per tabella notifiche in modo da consentire il fetch di tutti gli header dopo l'accesso al portale
        getWebDriverWait(80).withMessage("Pagina Notifiche PG non caricata correttamente: la tabella delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));

        logger.info("HomePagePG caricata correttamente");
    }

    public void waitLoadHomePagePGRuoloOperatorePage(String ragioneSociale) {
        getWebDriverWait(30).withMessage("il titolo Panoramica nella pagina home page  non è visibile").until(ExpectedConditions.visibilityOfElementLocated((By.id("Notifiche delegate a " + ragioneSociale + "-page"))));
        getWebDriverWait(30).withMessage("il bottone del groppu nella pagina home page  non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid= 'groupSelectorButton']"))));
        logger.info("HomePagePG ruolo operatore caricata correttamente");
    }

    public void checkBottoneDeleghe() {
        boolean isDisplayed = false;
        try {
            webTool.waitTime(5);
            WebElement sideMenuDeleghe = driver.findElement(By.id("side-item-Deleghe"));
            isDisplayed = sideMenuDeleghe.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false; // Elemento non trovato
        }

        Assertions.assertTrue(!isDisplayed, "Il side menu deleghe non è visibile");

    }

    public void clickSendNotificheDigitali(int i) {
        notificheDigitaliCardList = driver.findElements(By.xpath("//button[@class = 'MuiButtonBase-root MuiFab-root MuiFab-circular MuiFab-sizeSmall MuiFab-primary css-ngdrb8']"));
        notificheDigitaliCardList.get(i).click();
    }
}
