package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PiattaformaNotifichePGPAPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("PiattaformaNotifichePGPAPage");

    @FindBy(id = "side-item-Deleghe")
    WebElement delegheSideMenu;

    @FindBy(id = "side-item-Recapiti")
    WebElement recapitiButton;

    @FindBy(id = "side-item-Stato della piattaforma")
    WebElement buttonEnterIntoDisservizi;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    @FindBy(css = "[data-testid='paymentInfoBox']")
    WebElement sezionePagamento;

    @FindBy(css = "[role='radiogroup']")
    WebElement radioBox;

    @FindBy(xpath = "//span[@data-testid='radio-button']")
    List<WebElement> radioButton;

    @FindBy(xpath = "//span[contains(text(), 'Pagamento di Test')]")
    List<WebElement> titoloPagamento;

    @FindBy(css = "[data-testid='download-f24-button']")
    WebElement modelloF24;

    @FindBy(xpath = "//span[contains(text(), 'Codice avviso')]")
    List<WebElement> codiceAvviso;
    @FindBy(css = ".MuiTypography-caption-semibold.css-1g3z0lx")
    WebElement codiceAvvisoSpan;

    @FindBy(id = "side-item-Notifiche")
    WebElement sideItemNotificheButton;

    public PiattaformaNotifichePGPAPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnButtonEnterIntoDisservizi() {
        logger.info("click sul bottone 'stato della piattaforma'");
        this.getWebDriverWait(10).withMessage("Bottone 'stato della piattaforma' non visualizzato").until(ExpectedConditions.visibilityOf(this.buttonEnterIntoDisservizi));
        buttonEnterIntoDisservizi.click();
    }

    public void waitLoadPiattaformaNotificaPage(String ragioneSociale) {
        try {
            By titlePageBy = By.id("Notifiche di " + ragioneSociale + "-page");
            By codiceIunTextFieldBy = By.id("iunMatch");
            By dataInizioFieldBy = By.id("startDate");
            By dataFineFieldBy = By.id("endDate");
            this.getWebDriverWait(this.loadComponentWaitTime).withMessage("Il titolo della pagina Notifiche PG non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(40).withMessage("Il campo codice iun della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(codiceIunTextFieldBy));
            this.getWebDriverWait(40).withMessage("Il campo data inizio della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(dataInizioFieldBy));
            this.getWebDriverWait(40).withMessage("Il campo data fine della pagina Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(dataFineFieldBy));
            logger.info("La  pagina Piattaforma Notifiche si carica correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina Piattaforma Notifiche non si carica correttamente con errore: " + e.getMessage());
            Assert.fail("La pagina Piattaforma Notifiche non si carica correttamente con errore: " + e.getMessage());
        }
    }

    public void clickSuDelegeButton() {
        try {
            this.getWebDriverWait(10).withMessage("Sezione deleghe nel side menu non visualizzata").until(ExpectedConditions.visibilityOf(this.delegheSideMenu));
            this.js().executeScript("arguments[0].click()", this.delegheSideMenu);
            logger.info("click sul bottone Deleghe effetuato");
        } catch (TimeoutException e) {
            logger.error("il bottone deleghe non è stato trovato " + e.getMessage());
            Assert.fail("il bottone deleghe non è stato trovato" + e.getMessage());
        }
    }

    public void clickNotificheDelegate() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(this.sideItemNotificheButton));
            sideItemNotificheButton.click();
            By notificheDelegateButton = By.id("side-item-Notifiche delegate");
            this.getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(notificheDelegateButton));
            this.js().executeScript("arguments[0].click()", this.element(notificheDelegateButton));
            logger.info("Si clicca correttamente sulla voce notifiche delegate");
        } catch (TimeoutException e) {
            logger.error("Non si clicca correttamente sulla voce notifiche delegate con errore:" + e.getMessage());
            Assert.fail("Non si clicca correttamente sulla voce notifiche delegate con errore" + e.getMessage());
        }
    }

    public void waitLoadSezioneNotificheDelegate(String ragioneSociale) {
        try {
            By notificheDelegatePageTitle = By.id("Notifiche delegate a " + ragioneSociale + "-page");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheDelegatePageTitle));
            logger.info("Si visualizza correttamente la sezione notifiche delegate");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la sezione notifiche delegate con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente la sezione notifiche delegate con errore" + e.getMessage());
        }
    }

    public void clickRecapitiButton() {
        getWebDriverWait(10).withMessage("Il bottone recapiti non è visibile").until(ExpectedConditions.visibilityOf(recapitiButton));
        this.js().executeScript("arguments[0].click()", this.recapitiButton);
    }



    public void clickIndietroButton() {
        getWebDriverWait(10).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        this.js().executeScript("arguments[0].click()", this.indietroButton);
    }

    public boolean sezionePagamentoDisplayed() {
        try {
            return getWebDriverWait(30).withMessage("Il sezione pagamento non è visibile").until(ExpectedConditions.visibilityOf(sezionePagamento)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il sezione pagamento non è visibile");
            return false;
        }
    }

    public boolean isRadioBoxPresent() {
        try {
            return getWebDriverWait(30).withMessage("Il radio box non è visibile").until(ExpectedConditions.visibilityOf(radioBox)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il radio box non è visibile");
            return false;
        }
    }

    public String cssBuildRadioButton() {
        return "[value='" + codiceAvvisoSpan.getText() + "']";
    }

    public void clickRadioBoxButton() {
        getWebDriverWait(10).withMessage("Il radio box button non è cliccabile").until(ExpectedConditions.elementToBeClickable(radioButton.get(0)));
        radioButton.get(0).click();
    }

    public boolean titoloDiPagamentoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOf(titoloPagamento.get(0))).isDisplayed();
    }

    public boolean codiceAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso.get(0))).isDisplayed();
    }

    public boolean modelloF24Displayed() {
        return getWebDriverWait(30).withMessage("Il sezione scarica modello F24 non è visibile").until(ExpectedConditions.visibilityOf(modelloF24)).isDisplayed();
    }

    public void clickModelloF24() {
         getWebDriverWait(30).withMessage("Il sezione scarica modello F24 non è cliccabile").until(ExpectedConditions.elementToBeClickable(modelloF24));
         modelloF24.click();
    }
}