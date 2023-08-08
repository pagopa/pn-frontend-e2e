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

    private static  final Logger logger = LoggerFactory.getLogger("DestinatarioPASection");

    @FindBy(css = "input[value='PF']")
    WebElement personaFisicaCheckBox;

    @FindBy(xpath = "//input[contains(@id,'firstName')]" )
    WebElement nomeDestinatarioTextField;

    @FindBy(xpath = "//input[contains(@id,'lastName')]")
    WebElement cognomeDestinatarioTextField;

    @FindBy(xpath = "//input[contains(@id,'taxId')]")
    WebElement codiceFiscaleDestinatarioTextField;

    @FindBy(xpath = "//div[@data-testid='PhysicalAddressCheckbox']")
    WebElement aggiungiUnIndirizzoFisicoCheckBox;

    @FindBy(xpath = "//label[contains(@id,'address-label')]/following-sibling::div/input")
    WebElement indirizzoTextField;

    @FindBy(xpath = "//input[contains(@id,'houseNumber')]")
    WebElement numeroCivicoTextField;

    @FindBy(xpath = "//label[contains(@id,'municipalityDetails-label')]/following-sibling::div/input")
    WebElement localitaTextField;

    @FindBy(xpath = "//label[contains(@id,'municipality-label')]/following-sibling::div/input")
    WebElement comuneTextField;

    @FindBy(xpath = "//input[contains(@id,'province')]")
    WebElement provinciaTextField;

    @FindBy(xpath = "//input[contains(@id,'zip')]")
    WebElement codicePostaleTextField;

    @FindBy(xpath = "//input[contains(@id,'foreignState')]")
    WebElement statoTextField;

    @FindBy(xpath = "//button[contains(@data-testid,'add-recipient')]")
    WebElement aggiungiDestinatarioButton;

    @FindBy(xpath = "//div[@data-testid='DigitalDomicileCheckbox']")
    WebElement checkBoxAggiungiDomicilioDigitale;

    @FindBy(xpath = "//input[@id='recipients[0].digitalDomicile']")
    WebElement domicilioDigitaleTextField;

    @FindBy(xpath = "//input[@id='recipients[0].firstName']")
    WebElement ragioneSocialeTextField;

    @FindBy(xpath = "//input[@id='recipients[0].taxId']")
    WebElement partitaIvaTextField;

    @FindBy(xpath = "//button[@data-testid='previous-step']")
    WebElement informazioniPreliminariButton;

    @FindBy(xpath = "//input[@value='PG']")
    WebElement personaGiuridicaRadioButton;

    public DestinatarioPASection(WebDriver driver) {
        super(driver);
    }

    public String ricercaInformazione(String[] dati, int posizioneDestinatario ){
        String datoDestianario = dati[posizioneDestinatario];
        if (posizioneDestinatario == 0){
            datoDestianario = datoDestianario.replace("[","");
        }else if (posizioneDestinatario == 4){
            datoDestianario = datoDestianario.replace("]","");
        }
        datoDestianario = datoDestianario.replace(" ","");
        return datoDestianario;
    }



    public void waitLoadDestinatarioPASection() {
        try{
            By titleDestinatarioFieald = By.xpath("//h3[contains(text(),'Destinatari')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleDestinatarioFieald));
            logger.info("Destinatario PA Section caricata ");
        }catch (TimeoutException e){
            logger.error("Destinatario PA Section non caricata con errore : "+e.getMessage());
            Assert.fail("Destinatario PA Section non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionarePersonaFisica() {
        this.personaFisicaCheckBox.click();
    }

    public void inserireNomeDestinatario(String nomeDestinatario) {
        this.scrollToElementClickAndInsertText(this.nomeDestinatarioTextField, nomeDestinatario);
    }

    public void inserireCognomeDestinatario(String cognomeDestinatario) {
        this.scrollToElementClickAndInsertText(this.cognomeDestinatarioTextField, cognomeDestinatario);
    }

    public void inserireCodiceFiscaleDestinatario(String codiceFiscale) {
        this.scrollToElementClickAndInsertText(this.codiceFiscaleDestinatarioTextField,codiceFiscale);
    }
    
    private void scrollToElementClickAndInsertText(WebElement element, String text){
        if(!element.isDisplayed()){
            this.js().executeScript("arguments[0].scrollIntoView(true);",element);
        }
        element.click();
        if (text != null) {
            element.sendKeys(text);
        }
    }

    public void selezionaAggiungiUnIndirizzoFisico() {
        this.aggiungiUnIndirizzoFisicoCheckBox.click();
    }

    public void inserireIndirizzo(String indirizzo) {
        this.scrollToElementClickAndInsertText(this.indirizzoTextField, indirizzo);
    }

    public void inserireNumeroCivico(String numeroCivico) {
        this.scrollToElementClickAndInsertText(this.numeroCivicoTextField, numeroCivico);
    }

    public void inserireLocalita(String localita) {
        this.scrollToElementClickAndInsertText(this.localitaTextField, localita);
    }

    public void inserireComune(String comune) {
        this.scrollToElementClickAndInsertText(this.comuneTextField,comune);
    }

    public void inserireProvincia(String provincia) {
        this.scrollToElementClickAndInsertText(this.provinciaTextField, provincia);
    }

    public void inserireCodicePostale(String codicePostale) {
        this.scrollToElementClickAndInsertText(this.codicePostaleTextField,codicePostale);
    }

    public void inserireStato(String stato) {
        this.scrollToElementClickAndInsertText(this.statoTextField, stato);
    }

    public void selezionareAggiungiDestinatarioButton() {
        this.aggiungiDestinatarioButton.click();
    }

    public void inserimentoMultiDestinatario(Map<String,Object> destinatari, int nDestinatari) {
        for (int i = 0; i < nDestinatari; i++) {
            inserimentoInformazioniPreliminari(destinatari,i);
            inserimentoInformazioniAggiuntive(destinatari,i);
            if (i != nDestinatari-1){
                selezionareAggiungiDestinatarioButton();
            }
        }
    }
    public void inserimentoInformazioniPreliminari(Map<String,Object> destinatari, int i){
        selezionarePersonaFisica();
        String nomeDestinatario = ricercaInformazione(destinatari.get("name").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'firstName')]",nomeDestinatario);
        String cognomeDestinatario = ricercaInformazione(destinatari.get("familyName").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'lastName')]",cognomeDestinatario);
        String cfDestinatario = ricercaInformazione(destinatari.get("codiceFiscale").toString().split(","), i);
        cfDestinatario = cfDestinatario.replace(" ","");
        inserireInfoMultiDestinatario("//input[contains(@id,'taxId')]",cfDestinatario);
        selezionaAggiungiUnIndirizzoFisicoMulti();
    }
    public void inserimentoInformazioniAggiuntive(Map<String,Object> destinatari, int i){
        String indirizzoDestinatario = ricercaInformazione(destinatari.get("indirizzo").toString().split(","), i);
        inserireInfoMultiDestinatario("//label[contains(@id,'address-label')]/following-sibling::div/input",indirizzoDestinatario);
        String nCivicoDestinatario = ricercaInformazione(destinatari.get("numeroCivico").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'houseNumber')]",nCivicoDestinatario);
        String localitaDestinatario = ricercaInformazione(destinatari.get("localita").toString().split(","), i);
        inserireInfoMultiDestinatario("//label[contains(@id,'municipalityDetails-label')]/following-sibling::div/input",localitaDestinatario);
        String comuneDestinatario = ricercaInformazione(destinatari.get("comune").toString().split(","), i);
        inserireInfoMultiDestinatario("//label[contains(@id,'municipality-label')]/following-sibling::div/input",comuneDestinatario);
        String provinciaDestinatario = ricercaInformazione(destinatari.get("provincia").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'province')]",provinciaDestinatario);
        String codicePostale = ricercaInformazione(destinatari.get("codicepostale").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'zip')]",codicePostale);
        inserireInfoMultiDestinatario("//input[contains(@id,'foreignState')]",destinatari.get("stato").toString());
    }
    private void selezionaAggiungiUnIndirizzoFisicoMulti() {
        By aggiungiIndrizzoBy = By.xpath("//div[contains(@data-testid, 'PhysicalAddressCheckbox')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(aggiungiIndrizzoBy));
        List<WebElement> aggiungiIndirizzoButton = this.elements(aggiungiIndrizzoBy);
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
        //this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(aggiungereDestinatarioButtonBy));
        return this.elements(aggiungereDestinatarioButtonBy).size() == 0;
    }

    public void waitMessaggioErrore() {
        try {
            By errorMessage = By.xpath("//p[@id='recipients[1].taxId-helper-text']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            logger.info("Il messaggio di errore viene visualizzato");
        }catch (TimeoutException e){
            logger.error("Il messaggio di errore non viene visualizzato con errore: "+e.getMessage());
            Assert.fail("Il messaggio di errore non viene visualizzato con errore: "+e.getMessage());
        }

    }

    public void inserimentoMultiDestinatarioPG(Map<String, Object> personeGiuridiche, int nDestinatari) {
        for (int i = 0; i < nDestinatari; i++) {
            inserimentoInformazioniPreliminariPG(personeGiuridiche,i);
            inserimentoInformazioniAggiuntive(personeGiuridiche,i);
            if (i != nDestinatari-1){
                selezionareAggiungiDestinatarioButton();
            }
        }
    }

    private void inserimentoInformazioniPreliminariPG(Map<String, Object> personeGiuridiche, int i) {
        clickRadioButtonPersonaGiuridica(i+1);
        String nomeDestinatario = ricercaInformazione(personeGiuridiche.get("name").toString().split(","), i);
        inserireInfoMultiDestinatario("//input[contains(@id,'firstName')]",nomeDestinatario);
        String cfDestinatario = ricercaInformazione(personeGiuridiche.get("codiceFiscale").toString().split(","), i);
        cfDestinatario = cfDestinatario.replace(" ","");
        inserireInfoMultiDestinatario("//input[contains(@id,'taxId')]",cfDestinatario);
        selezionaAggiungiUnIndirizzoFisicoMulti();
    }

    private void clickRadioButtonPersonaGiuridica(int posizione) {
    By radioButtonPgBy = By.xpath("//input[@name='recipients["+posizione+"].recipientType' and @value ='PG']");
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

    public void insertpartitaIva(String codiceFiscale) {
        this.partitaIvaTextField.sendKeys(codiceFiscale);
    }

    public void clickSuTornaInformazioniPreliminari() {
        this.informazioniPreliminariButton.click();
    }

    public void clickRadioButtonPersonaGiuridica() {
        this.personaGiuridicaRadioButton.click();
    }

}
