package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


public class HomePagePG extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HomePagePG");


    @FindBy(xpath = "//button[@class = 'MuiButtonBase-root MuiFab-root MuiFab-circular MuiFab-sizeSmall MuiFab-primary css-ngdrb8']")
    List<WebElement> notificheDigitaliCardList;

    public HomePagePG(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadHomePagePGPage() {

        WebElement titlePageBy = driver.findElement(By.xpath("//h4[contains(text(),'Panoramica')]"));
        WebElement subtitlePageBy = driver.findElement(By.xpath("//h4[contains(text(),'Notifiche digitali')]"));
        WebElement cardNotificheDevBy = driver.findElement(By.xpath("//h6[@aria-label='SEND - Notifiche Digitali DEV']"));
        getWebDriverWait(10).withMessage("il titolo Panoramica nella pagina home page riepilogo dati non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
        getWebDriverWait(10).withMessage("il sottotitolo nella pagina home page riepilogo dati non è visibile").until(ExpectedConditions.visibilityOf(subtitlePageBy));
        getWebDriverWait(10).withMessage("la card SEND - Notifiche Digitali DEV non è visibile ").until(ExpectedConditions.visibilityOf(cardNotificheDevBy));
        logger.info("HomePagePG caricata correttamente");

    }

    public void waitLoadHomePagePGRuoloOperatorePage(String ragioneSociale) {
        WebElement titlePageBy = driver.findElement(By.id("Notifiche delegate a " + ragioneSociale + "-page"));
        WebElement buttonOfGroup = driver.findElement(By.xpath("//button[@data-testid= 'groupSelectorButton']"));
        getWebDriverWait(10).withMessage("il titolo Panoramica nella pagina home page  non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
        getWebDriverWait(10).withMessage("il bottone del groppu nella pagina home page  non è visibile").until(ExpectedConditions.visibilityOf(buttonOfGroup));
        logger.info("HomePagePG ruolo operatore caricata correttamente");
    }

    public void checkBottoneDeleghe() {
        boolean isDisplayed = false;
        try {
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
