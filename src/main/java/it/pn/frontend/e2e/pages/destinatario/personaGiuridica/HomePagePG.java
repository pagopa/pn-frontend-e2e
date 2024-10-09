package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HomePagePG extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HomePagePG");

    public HomePagePG(WebDriver driver) {
        super(driver);
    }



    @FindBy(xpath = "//button[@class = 'MuiButtonBase-root MuiFab-root MuiFab-circular MuiFab-sizeSmall MuiFab-primary css-ngdrb8']")
    List<WebElement> notificheDigitaliCardList;



    public void waitLoadHomePagePGPage() {

            By titlePageBy = By.xpath("//h4[contains(text(),'Panoramica')]");
            By subtitlePageBy = By.xpath("//h4[contains(text(),'Notifiche digitali')]");
            By cardNotificheDevBy = By.xpath("//h6[@aria-label='SEND - Notifiche Digitali DEV']");
            getWebDriverWait(10).withMessage("il titolo Panoramica nella pagina home page riepilogo dati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            getWebDriverWait(10).withMessage("il sottotitolo nella pagina home page riepilogo dati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(subtitlePageBy));
            getWebDriverWait(10).withMessage("la card SEND - Notifiche Digitali DEV non è visibile ").until(ExpectedConditions.visibilityOfElementLocated(cardNotificheDevBy));
            logger.info("HomePagePG caricata correttamente");

    }

    public void waitLoadHomePagePGRuoloOperatorePage(String ragioneSociale) {
            By titlePageBy = By.id("Notifiche delegate a "+ ragioneSociale+ "-page");
            By buttonOfGroup = By.xpath("//button[@data-testid= 'groupSelectorButton']");
            getWebDriverWait(10).withMessage("il titolo Panoramica nella pagina home page  non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            getWebDriverWait(10).withMessage("il bottone del groppu nella pagina home page  non è visibile").until(ExpectedConditions.visibilityOfElementLocated(buttonOfGroup));
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

        Assertions.assertTrue(!isDisplayed,"Il side menu deleghe non è visibile");

    }

    public void clickSendNotificheDigitali(int i) {
        notificheDigitaliCardList.get(i).click();
    }
}
