package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class HomePagePG extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePagePG.class);


    @FindBy(xpath = "//button[@class = 'MuiButtonBase-root MuiFab-root MuiFab-circular MuiFab-sizeSmall MuiFab-primary css-ngdrb8']")
    List<WebElement> notificheDigitaliCardList;

    @Autowired
    private WebTool webTool;

    public HomePagePG(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadHomePagePGPage() {

        getWebDriverWait(10)
                .withMessage("Il titolo 'Panoramica' nella pagina home non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Panoramica')]")));

        getWebDriverWait(10)
                .withMessage("Il sottotitolo 'Notifiche digitali' nella pagina home non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Notifiche digitali')]")));

        getWebDriverWait(20)
                .withMessage("La card 'SEND - Notifiche Digitali DEV' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[@aria-label='SEND - Notifiche Digitali DEV']")));

        getWebDriverWait(80)
                .withMessage("La card 'SEND - Notifiche Digitali TEST' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[@aria-label='SEND - Notifiche Digitali TEST']")));

        logger.info("HomePagePG caricata correttamente");
    }

    //    public void waitLoadHomePagePGRuoloOperatorePage(String ragioneSociale) {
//        getWebDriverWait(30).withMessage("il titolo Panoramica nella pagina home page  non è visibile").until(ExpectedConditions.visibilityOfElementLocated((By.id("Notifiche delegate a " + ragioneSociale + "-page"))));
//        getWebDriverWait(30).withMessage("il bottone del groppu nella pagina home page  non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid= 'groupSelectorButton']"))));
//        logger.info("HomePagePG ruolo operatore caricata correttamente");
//    }
    public void waitLoadHomePagePGRuoloOperatorePage(String ragioneSociale) {
        // Aspetta il titolo della pagina
        By titoloBy = By.id("Notifiche delegate a " + ragioneSociale + "-page");
        getWebDriverWait(30)
                .withMessage("Il titolo Panoramica nella pagina home page non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titoloBy));

        // Aspetta il bottone del gruppo
        By gruppoButtonBy = By.xpath("//button[@data-testid='groupSelectorButton']");
        getWebDriverWait(30)
                .withMessage("Il bottone del gruppo nella pagina home page non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(gruppoButtonBy));

        logger.info("HomePagePG ruolo operatore caricata correttamente");
    }


    //    public void checkBottoneDeleghe() {
//        boolean isDisplayed = false;
//        try {
//            webTool.waitTime(5);
//            WebElement sideMenuDeleghe = driver.findElement(By.id("side-item-Deleghe"));
//            isDisplayed = sideMenuDeleghe.isDisplayed();
//        } catch (NoSuchElementException e) {
//            isDisplayed = false; // Elemento non trovato
//        }
//
//        Assertions.assertTrue(!isDisplayed, "Il side menu deleghe non è visibile");
//
//    }
    public void checkBottoneDeleghe() {
        webTool.waitTime(5);

        By sideMenuDelegheBy = By.id("side-item-Deleghe");

        boolean isDisplayed = driver.findElements(sideMenuDelegheBy).stream()
                .anyMatch(WebElement::isDisplayed);

        Assertions.assertFalse(isDisplayed, "Il side menu deleghe non dovrebbe essere visibile");
    }


    //    public void clickSendNotificheDigitali(int i) {
//        notificheDigitaliCardList = driver.findElements(By.xpath("//button[@class = 'MuiButtonBase-root MuiFab-root MuiFab-circular MuiFab-sizeSmall MuiFab-primary css-ngdrb8']"));
//        notificheDigitaliCardList.get(i).click();
//    }
    public void clickSendNotificheDigitali(int i) {
        By cardButtonsBy = By.xpath("//button[@class='MuiButtonBase-root MuiFab-root MuiFab-circular MuiFab-sizeSmall MuiFab-primary css-ngdrb8']");
        List<WebElement> notificheDigitaliCardList = driver.findElements(cardButtonsBy);

        if (i >= 0 && i < notificheDigitaliCardList.size()) {
            notificheDigitaliCardList.get(i).click();
        } else {
            Assertions.fail("Indice " + i + " fuori dal range della lista di notifiche digitali.");
        }
    }

}
