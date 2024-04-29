package it.pn.frontend.e2e.section.mittente;

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

import java.util.List;
import java.util.Map;

public class DestinatarioPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DestinatarioPASection");

    @FindBy(css = "input[value='PF']")
    WebElement personaFisicaCheckBox;

    @FindBy(id = "recipients[0].firstName")
    WebElement nomeDestinatarioTextField;

    @FindBy(id = "recipients[0].lastName")
    WebElement cognomeDestinatarioTextField;

    @FindBy(id = "recipients[0].taxId")
    WebElement codiceFiscaleDestinatarioTextField;

    @FindBy(xpath = "//div[@data-testid='recipients[0].physicalAddressCheckbox']")
    WebElement aggiungiUnIndirizzoFisicoCheckBox;

    @FindBy(id = "recipients[0].address")
    WebElement indirizzoTextField;

    @FindBy(id = "recipients[0].houseNumber")
    WebElement numeroCivicoTextField;

    @FindBy(id = "recipients[0].municipalityDetails")
    WebElement localitaTextField;

    @FindBy(id = "recipients[0].municipality")
    WebElement comuneTextField;

    @FindBy(id = "recipients[0].province")
    WebElement provinciaTextField;

    @FindBy(id = "recipients[0].zip")
    WebElement codicePostaleTextField;

    @FindBy(id = "recipients[0].foreignState")
    WebElement statoTextField;

    @FindBy(id = "add-recipient")
    WebElement aggiungiDestinatarioButton;

    @FindBy(xpath = "//button[contains(@data-testid,'DeleteRecipientIcon')]")
    List<WebElement> rimuoviDestinatarioButtons;


    @FindBy(xpath = "//label[@data-testid='showDigitalDomicile0']")
    WebElement checkBoxAggiungiDomicilioDigitale;

    @FindBy(xpath = "//input[@id='recipients[0].digitalDomicile']")
    WebElement domicilioDigitaleTextField;

    @FindBy(xpath = "//input[@id='recipients[0].firstName']")
    WebElement ragioneSocialeTextField;

    @FindBy(id = "recipients[0].taxId")
    WebElement partitaIvaTextField;

    @FindBy(xpath = "//button[@data-testid='previous-step']")
    WebElement informazioniPreliminariButton;

    @FindBy(xpath = "//input[@value='PG']")
    WebElement personaGiuridicaRadioButton;


    public DestinatarioPASection(WebDriver driver) {
        super(driver);
    }

    public String ricercaInformazione(String[] dati, int posizioneDestinatario) {
        String datoDestianario = dati[posizioneDestinatario];
        if (posizioneDestinatario == 0) {
            datoDestianario = datoDestianario.replace("[", "");
        } else if (posizioneDestinatario == 3) {
            datoDestianario = datoDestianario.replace("]", "");
        }
        if (posizioneDestinatario > 0) {
            datoDestianario = datoDestianario.substring(1);
        }
        return datoDestianario;
    }


    public void waitLoadDestinatarioPASection() {
        try {
            By titleDestinatarioFieald = By.id("title-heading-section");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleDestinatarioFieald));
            logger.info("Destinatario PA Section caricata ");
        } catch (TimeoutException e) {
            logger.error("Destinatario PA Section non caricata con errore : " + e.getMessage());
            Assert.fail("Destinatario PA Section non caricata con errore : " + e.getMessage());
        }

    }

    public void selezionarePersonaFisica() {
        logger.info("selezione pf su checkbox");
        this.personaFisicaCheckBox.click();
    }


    public void inserireNomeDestinatario(String nomeDestinatario) {
        logger.info("inserimento nome destinatario");
        this.scrollToElementClickAndInsertText(this.nomeDestinatarioTextField, nomeDestinatario);
    }

    public void inserireCognomeDestinatario(String cognomeDestinatario) {
        logger.info("inserimento cognome destinatario");
        this.scrollToElementClickAndInsertText(this.cognomeDestinatarioTextField, cognomeDestinatario);
    }

    public void inserireCodiceFiscaleDestinatario(String codiceFiscale) {
        logger.info("inserimento codice fiscale destinatario");
        this.scrollToElementClickAndInsertText(this.codiceFiscaleDestinatarioTextField, codiceFiscale);
    }

    public void selezionaAggiungiUnIndirizzoFisico() {
        logger.info("click su scelta indirizzo fisico");
        this.aggiungiUnIndirizzoFisicoCheckBox.click();
    }

    public void inserireIndirizzo(String indirizzo) {
        logger.info("inserimento indirizzo fisico");
        this.scrollToElementClickAndInsertText(this.indirizzoTextField, indirizzo);
    }

    public void inserireNumeroCivico(String numeroCivico) {
        logger.info("inserimento numero civico");
        this.scrollToElementClickAndInsertText(this.numeroCivicoTextField, numeroCivico);
    }

    public void inserireLocalita(String localita) {
        logger.info("inserimento localitá");
        this.scrollToElementClickAndInsertText(this.localitaTextField, localita);
    }

    public void inserireComune(String comune) {
        logger.info("inserimento comune");
        this.scrollToElementClickAndInsertText(this.comuneTextField, comune);
    }

    public void inserireProvincia(String provincia) {
        logger.info("inserimento provincia");
        this.scrollToElementClickAndInsertText(this.provinciaTextField, provincia);
    }

    public void inserireCodicePostale(String codicePostale) {
        logger.info("inserimento codice postale");
        this.scrollToElementClickAndInsertText(this.codicePostaleTextField, codicePostale);
    }

    public void cambiareCodicePostale(String codicePostale) {
        logger.info("cambio codice postale");
        clearWebElementField(this.codicePostaleTextField);
        codicePostaleTextField.sendKeys(codicePostale);
    }

    public void inserireStato(String stato) {
        logger.info("inserimento stato");
        this.scrollToElementClickAndInsertText(this.statoTextField, stato);
    }

    public void selezionareAggiungiDestinatarioButton() {
        logger.info("scelta aggiungi destinatario");
        this.aggiungiDestinatarioButton.click();
    }

    public void selezionareRimuoviDestinatarioButton() {
        logger.info("click su rimuovi destinatario");
        this.rimuoviDestinatarioButtons.get(1).click();
    }

    public void inserimentoMultiDestinatario(Map<String, Object> destinatari, int nDestinatari) {
        for (int i = 0; i < nDestinatari; i++) {
            inserimentoInformazioniPreliminari(destinatari, i);
            inserimentoInformazioniAggiuntive(destinatari, i);
            if (i != nDestinatari - 1) {
                selezionareAggiungiDestinatarioButton();
            }
        }
    }

    public void inserimentoInformazioniPreliminari(Map<String, Object> destinatari, int i) {
        selezionarePersonaFisica();
        String nomeDestinatario = ricercaInformazione(destinatari.get("name").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'firstName')]", nomeDestinatario);
        String cognomeDestinatario = ricercaInformazione(destinatari.get("familyName").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'lastName')]", cognomeDestinatario);
        String cfDestinatario = ricercaInformazione(destinatari.get("codiceFiscale").toString().split(","), i);
        cfDestinatario = cfDestinatario.replace(" ", "");
        inserireInfoMultiDestinatario("//input[contains(@id,'taxId')]", cfDestinatario);
        selezionaAggiungiUnIndirizzoFisicoMulti(i + 1);
    }

    public void inserimentoInformazioniAggiuntive(Map<String, Object> destinatari, int i) {
        String indirizzoDestinatario = ricercaInformazione(destinatari.get("indirizzo").toString().split(","), i);
        inserireInfoMultiDestinatario("//label[contains(@id,'address-label')]/following-sibling::div/input", indirizzoDestinatario);
        String nCivicoDestinatario = ricercaInformazione(destinatari.get("numeroCivico").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'houseNumber')]", nCivicoDestinatario);
        String localitaDestinatario = ricercaInformazione(destinatari.get("localita").toString().split(","), i);
        inserireInfoMultiDestinatario("//label[contains(@id,'municipalityDetails-label')]/following-sibling::div/input", localitaDestinatario);
        String comuneDestinatario = ricercaInformazione(destinatari.get("comune").toString().split(","), i);
        inserireInfoMultiDestinatario("//label[contains(@id,'municipality-label')]/following-sibling::div/input", comuneDestinatario);
        String provinciaDestinatario = ricercaInformazione(destinatari.get("provincia").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'province')]", provinciaDestinatario);
        String codicePostale = ricercaInformazione(destinatari.get("codicepostale").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'zip')]", codicePostale);
        inserireInfoMultiDestinatario("//input[contains(@id,'foreignState')]", destinatari.get("stato").toString());
    }

    private void selezionaAggiungiUnIndirizzoFisicoMulti(int i) {
        By aggiungiIndirizzoBy = By.xpath("//label[@data-testid='showPhysicalAddress" + i + "']");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(aggiungiIndirizzoBy));
        List<WebElement> aggiungiIndirizzoButton = this.elements(aggiungiIndirizzoBy);
        this.scrollToElementClickAndInsertText(aggiungiIndirizzoButton.get(aggiungiIndirizzoButton.size() - 1), null);
    }

    private void inserireInfoMultiDestinatario(String xpath, String nomeDestinatario) {
        try {
            By nomeDestinatarioBy = By.xpath(xpath);
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDestinatarioBy));
            List<WebElement> listaNomeDestinatariField = this.elements(nomeDestinatarioBy);
            this.scrollToElementClickAndInsertText(listaNomeDestinatariField.get(listaNomeDestinatariField.size() - 1), nomeDestinatario);
        } catch (TimeoutException e) {
            logger.error("Xpath non trovato con errore: " + e.getMessage());
            Assert.fail("Xpath non trovato con errore: " + e.getMessage());
        }
    }

    public boolean inserireIlSestoDestinatario() {
        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
        By aggiungereDestinatarioButtonBy = By.xpath("//button[contains(@data-testid,'add-recipient')]");
        return this.elements(aggiungereDestinatarioButtonBy).size() == 0;
    }

    public void waitMessaggioErrore() {
        try {
            By errorMessagePrimoDestinatario = By.xpath("//p[@id='recipients[0].taxId-helper-text']");
            By errorMessageSecondoDestinatario = By.xpath("//p[@id='recipients[1].taxId-helper-text']");
            getWebDriverWait(10).withMessage("Non si visualizza il messaggio di errore del primo destinatario").until(ExpectedConditions.visibilityOfElementLocated(errorMessagePrimoDestinatario));
            getWebDriverWait(10).withMessage("Non si visualizza il messaggio di errore del secondo destinatario").until(ExpectedConditions.visibilityOfElementLocated(errorMessageSecondoDestinatario));
            logger.info("I messaggi di errore vengono visualizzati correttamente");
        } catch (TimeoutException e) {
            logger.error("Il messaggio di errore non viene visualizzato con errore: " + e.getMessage());
            Assert.fail("Il messaggio di errore non viene visualizzato con errore: " + e.getMessage());
        }

    }

    public void inserimentoMultiDestinatarioPG(Map<String, Object> personeGiuridiche, int nDestinatari) {
        for (int i = 0; i < nDestinatari; i++) {
            inserimentoInformazioniPreliminariPG(personeGiuridiche, i);
            inserimentoInformazioniAggiuntive(personeGiuridiche, i);
            if (i != nDestinatari - 1) {
                selezionareAggiungiDestinatarioButton();
            }
        }
    }

    private void inserimentoInformazioniPreliminariPG(Map<String, Object> personeGiuridiche, int i) {
        clickRadioButtonPersonaGiuridica(i + 1);
        String nomeDestinatario = ricercaInformazione(personeGiuridiche.get("name").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'firstName')]", nomeDestinatario);
        String cfDestinatario = ricercaInformazione(personeGiuridiche.get("codiceFiscale").toString().split(","), i);
        cfDestinatario = cfDestinatario.replace(" ", "");
        inserireInfoMultiDestinatario("//input[contains(@id,'taxId')]", cfDestinatario);
        selezionaAggiungiUnIndirizzoFisicoMulti(i + 1);
    }

    private void clickRadioButtonPersonaGiuridica(int posizione) {
        By radioButtonPgBy = By.xpath("//input[@name='recipients[" + posizione + "].recipientType' and @value ='PG']");
        this.element(radioButtonPgBy).click();
    }

    public void checkBoxAggiungiDomicilio() {
        this.checkBoxAggiungiDomicilioDigitale.click();
    }

    public void insertDomicilioDigitale(String emailPec) {
        this.domicilioDigitaleTextField.sendKeys(emailPec);
    }

    public void insertDomicilioDigitaleErrato(String emailPec) {
        this.domicilioDigitaleTextField.sendKeys(emailPec);
    }

    public void insertRagioneSociale(String ragioneSociale) {
        this.ragioneSocialeTextField.sendKeys(ragioneSociale);
    }

    public void insertPartitaIva(String codiceFiscale) {
        this.partitaIvaTextField.sendKeys(codiceFiscale);
    }

    public void clickSuTornaInformazioniPreliminari() {
        this.informazioniPreliminariButton.click();
    }

    public void clickRadioButtonPersonaGiuridica() {
        this.personaGiuridicaRadioButton.click();
    }

    public void insertCodiceFiscaleErrato(String codiceFiscale) {
        logger.info("TA_QA: si inserisci codice fiscale errato");
        By valoreErratoBy = By.id("recipients[0].taxId");
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(valoreErratoBy));
        this.driver.findElement(valoreErratoBy).sendKeys(codiceFiscale);
    }

    public String getCodiceFiscaleError() {
        logger.info("TA_QA: si legge il messagio di errore del Codice fiscale");
        By valoreCFErratoBy = By.id("recipients[0].taxId-helper-text");
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(valoreCFErratoBy));
        return driver.findElement(valoreCFErratoBy).getText();
    }

    public String getDomicilioDigitaleError() {
        logger.info("TA_QA: si legge il messagio di errore del digital domicile");
        By valoreErratoBy = By.id("recipients[0].digitalDomicile-helper-text");
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(valoreErratoBy));
        return this.driver.findElement(valoreErratoBy).getText();
    }

    public boolean verificaNumeroDestinatari() {
        logger.info("TA_QA: si verifica il numero dei destinatari");
        return this.rimuoviDestinatarioButtons.isEmpty();
    }

    public void selezionarePersonaFisicaMultiDestinatario(int numeroDestinatario) {
        logger.info("selezione pf su checkbox del destinatario numero: " + (numeroDestinatario + 1));
        By personaFisicaCheckBox = By.xpath("//label[@id='recipient-pf' and @data-testid='recipientType" + numeroDestinatario + "']/span");
        List<WebElement> personaFisicaCheckBoxElement = driver.findElements(personaFisicaCheckBox);
        this.js().executeScript("arguments[0].scrollIntoView(true);", personaFisicaCheckBoxElement.get(0));
        getWebDriverWait(10).withMessage("Checkbox di persona fisica non visibile del destinatario numero " + (numeroDestinatario + 1)).until(ExpectedConditions.visibilityOf(personaFisicaCheckBoxElement.get(0)));
        personaFisicaCheckBoxElement.get(0).click();
    }

    public void inserireNomeMultiDestinatario(int numeroDestinatario, String nomeDestinatario) {
        logger.info("inserimento nome del destinatario numero " + (numeroDestinatario + 1));
        By nomeDestinatarioTextFieldBy = By.id("recipients[" + numeroDestinatario + "].firstName");
        WebElement nomeDestinatarioTextField = driver.findElement(nomeDestinatarioTextFieldBy);
        this.scrollToElementClickAndInsertText(nomeDestinatarioTextField, nomeDestinatario);
    }

    public void inserireCognomeMultiDestinatario(int numeroDestinatario, String nomeDestinatario) {
        logger.info("inserimento cognome del destinatario numero " + (numeroDestinatario + 1));
        By cognomeDestinatarioTextFieldBy = By.id("recipients[" + numeroDestinatario + "].lastName");
        WebElement cognomeDestinatarioTextField = driver.findElement(cognomeDestinatarioTextFieldBy);
        this.scrollToElementClickAndInsertText(cognomeDestinatarioTextField, nomeDestinatario);
    }

    public void inserireCodiceFiscaleMultiDestinatario(int numeroDestinatario, String codiceFiscale) {
        logger.info("inserimento codice fiscale del destinatario numero " + (numeroDestinatario + 1));
        By codiceFiscaleDestinatarioTextFieldBy = By.id("recipients[" + numeroDestinatario + "].taxId");
        WebElement codiceFiscaleDestinatarioTextField = driver.findElement(codiceFiscaleDestinatarioTextFieldBy);
        this.scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextField, codiceFiscale);
    }
}
