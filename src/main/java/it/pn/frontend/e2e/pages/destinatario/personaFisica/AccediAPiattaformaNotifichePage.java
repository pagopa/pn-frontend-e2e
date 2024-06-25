package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccediAPiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AccediAPiattaformaNotifichePage");

    @FindBy(id = "login-button")
    WebElement accediButton;

    @FindBy(css = "[id='notificationsTable.body.row']")
    WebElement notificheButton;

    @FindBy(id = "side-item-Recapiti")
    WebElement recapitiButton;

    @FindBy(id = "side-item-Stato della piattaforma")
    WebElement buttonEnterIntoDisservizi;

    @FindBy(css = "[data-testid='download-legalfact']")
    WebElement attestazione;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    @FindBy(css = "[class='MuiTableBody-root css-1xnox0e']")
    WebElement sezioneDeiDati;

    @FindBy(css = "[data-testid='paymentInfoBox']")
    WebElement sezionePagamento;

    @FindBy(css = "[role='radiogroup']")
    WebElement radioBox;

    @FindBy(css = "[name='radio-buttons-group']")
    WebElement radioButton;

    @FindBy(linkText = "Pagamento di Test")
    WebElement titoloPagamento;

    @FindBy(css = "[data-testid='download-f24-button']")
    WebElement modelloF24;

    @FindBy(css = "[data-testid='download-pagoPA-notice-button']")
    WebElement scaricaAvviso;

    @FindBy(css = "[data-testid='pay-button']")
    WebElement pagaAvviso;

    @FindBy(xpath = "//span[contains(text(),'Codice avviso')]")
    WebElement codiceAvviso;
    @FindBy(css = ".MuiTypography-caption-semibold.css-1g3z0lx")
    WebElement codiceAvvisoSpan;

    public AccediAPiattaformaNotifichePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAccediAPiattaformaNotifichePage() {
        try {
            By titleLabel = By.id("login-page-title");
            By loginBy = By.id("login-button");
            this.getWebDriverWait(30).withMessage("Il titolo della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(30).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(loginBy));
            this.getWebDriverWait(30).withMessage("Il bottone login della pagina accedi a piattaforma notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.accediButton));
            logger.info("Accedi A Piattaforma Notifiche Page caricata");
        } catch (TimeoutException e) {
            logger.info("Accedi A Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
            Assert.fail("Accedi A Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
        }
    }

    public void selezionaAccediButton() {
        js().executeScript("arguments[0].click()", this.accediButton);
    }


    public void clickRecapitiButton() {
        getWebDriverWait(30).withMessage("Il bottone recapiti non è visibile").until(ExpectedConditions.visibilityOf(recapitiButton));
        js().executeScript("arguments[0].click()", this.recapitiButton);
    }

    public void clickNotificheButton() {
        getWebDriverWait(30).withMessage("Il bottone notifiche non è visibile").until(ExpectedConditions.visibilityOf(notificheButton));
        notificheButton.click();
    }

    public void clickAttestazionePersalvare() {
        getWebDriverWait(30).withMessage("L'attestazione non è visibile").until(ExpectedConditions.visibilityOf(attestazione));
        this.js().executeScript("arguments[0].click()", this.attestazione);
    }

    public boolean isBackButtonDisplayed() {
        return getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton)).isDisplayed();
    }

    public void clickIndietroButton() {
        getWebDriverWait(30).withMessage("Il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        this.js().executeScript("arguments[0].click()", this.indietroButton);
    }

    public boolean sezioneDeiDatiDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione dei dati non è visibile").until(ExpectedConditions.visibilityOf(sezioneDeiDati)).isDisplayed();
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

    public void clickRadioBoxButton(String css) {
        getWebDriverWait(30).withMessage("Il radio box button non è cliccabile").until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }

    public void clickStatoDellaPiattaforma() {
        getWebDriverWait(10).withMessage("Il bottone stato della piattaforma non è visibile").until(ExpectedConditions.visibilityOf(buttonEnterIntoDisservizi));
        buttonEnterIntoDisservizi.click();
    }

    public boolean titoloDiPagamentoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione titolo pagamento non è visibile").until(ExpectedConditions.visibilityOf(titoloPagamento)).isDisplayed();
    }

    public boolean codiceAvvisoDisplayed() {
        try {
            getWebDriverWait(5).withMessage("Il sezione codice avviso non è visibile").until(ExpectedConditions.visibilityOf(codiceAvviso)).isDisplayed();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean modelloF24Displayed() {
        return getWebDriverWait(5).withMessage("Il sezione scarica modello F24 non è visibile").until(ExpectedConditions.visibilityOf(modelloF24)).isDisplayed();
    }

    public boolean scaricaAvvisoDisplayed() {
        try {
            getWebDriverWait(5).withMessage("Il sezione scarica avviso non è visibile").until(ExpectedConditions.visibilityOf(scaricaAvviso)).isDisplayed();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void clickAvvisoPagoPAPG() {
        getWebDriverWait(30).withMessage("Il sezione scarica avviso non è cliccabile").until(ExpectedConditions.elementToBeClickable(scaricaAvviso));
        scaricaAvviso.click();
    }

    public boolean pagaAvvisoDisplayed() {
        return getWebDriverWait(30).withMessage("Il sezione paga avviso non è visibile").until(ExpectedConditions.visibilityOf(pagaAvviso)).isDisplayed();
    }

    public void checkButtonPagaIsDisplayed() {
        getWebDriverWait(10).withMessage("Il bottone per il pagamento della notifica è visibile").until(ExpectedConditions.invisibilityOf(pagaAvviso));
    }

    public void clickLink(String linkName) {
        By linkBy = By.xpath("//button//div[contains(text(),'" + linkName + "')] | //button[contains(text(),'" + linkName + "')]");
        getWebDriverWait(10).withMessage("Il button " + linkName + " non è visibile o cliccabile").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(linkBy),
                ExpectedConditions.elementToBeClickable(linkBy)));
        element(linkBy).click();
        WebTool.waitTime(5);
    }
}


