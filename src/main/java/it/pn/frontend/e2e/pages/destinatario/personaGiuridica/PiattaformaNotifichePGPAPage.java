package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class PiattaformaNotifichePGPAPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("PiattaformaNotifichePGPAPage");

    @FindBy(xpath = "//div[@data-testid='sideMenuItem-Deleghe']")
    WebElement delegheSideMenu;

    @FindBy(xpath = "//div[@data-testid='sideMenuItem-Recapiti']")
    WebElement recapitiButton;

    @FindBy(id = "side-item-Stato della piattaforma")
    WebElement buttonEnterIntoDisservizi;

    public PiattaformaNotifichePGPAPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnButtonEnterIntoDisservizi() {
        logger.info("click sul bottone 'stato della piattaforma'");
        this.getWebDriverWait(60).until(ExpectedConditions.visibilityOf(this.buttonEnterIntoDisservizi));
        buttonEnterIntoDisservizi.click();

    }

    public void waitLoadPitattaformaNotificaPage(String ragioneSociale) {
        try {
            By titlePageBy = By.id("Notifiche di "+ragioneSociale+"-page");
            By codiceIunTextFieldBy = By.id("iunMatch");
            By dataInizioFieldBy = By.id("startDate");
            By dataFineFieldBy = By.id("endDate");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(codiceIunTextFieldBy));
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(dataInizioFieldBy));
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(dataFineFieldBy));
            logger.info("La piagine Piattamorma Notifiche si carica correttamente");
        } catch (TimeoutException e) {
            logger.error("La piagine Piattamorma Notifiche non si carica correttamente con errore: " + e.getMessage());
            Assert.fail("La piagine Piattamorma Notifiche non si carica correttamente con errore: " + e.getMessage());
        }
    }

    public void clickSuDelegeButton() {
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.delegheSideMenu));
        this.js().executeScript("arguments[0].click()",this.delegheSideMenu);
    }

    public void clickNotificheDelegate() {
        try {
            By notificheDelegateButton = By.xpath("//div[@data-testid='sideMenuItem-Notifiche delegate']");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheDelegateButton));
            this.js().executeScript("arguments[0].click()",this.element(notificheDelegateButton));
            logger.info("Si clicca correttamente sulla voce notifiche delegate");
        } catch (TimeoutException e) {
            logger.error("Non si clicca correttamente sulla voce notifiche delegate con errore:" + e.getMessage());
            Assert.fail("Non si clicca correttamente sulla voce notifiche delegate con errore" + e.getMessage());
        }
    }

    public void waitLoadSezioneNotificheDelegate() {
        try{
            By notificheDelegatePageTitle = By.id("Notifiche delegate a Vita Nova Sas-page");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheDelegatePageTitle));
            logger.info("Si visualizza correttamente la sezione notifiche delegate");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente la sezione notifiche delegate con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente la sezione notifiche delegate con errore"+e.getMessage());
        }
    }
        public void clickRecapitiButton () {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(recapitiButton));
            this.js().executeScript("arguments[0].click()",this.recapitiButton);
        }
    }


