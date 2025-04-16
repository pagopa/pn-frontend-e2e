package it.pn.frontend.e2e.section.mittente;

import io.cucumber.java.en.And;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.model.recipients.PersoneFisiche;
import it.pn.frontend.e2e.model.recipients.PersoneGiuridiche;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
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

    @FindBy(id = "add-digital-domicile")
    WebElement checkDomicilioDigitale;

    private WebTool webTool;

    public DestinatarioPASection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
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
        datoDestianario = datoDestianario.replace("]", "");
        return datoDestianario;
    }


    public void waitLoadDestinatarioPASection() {
        try {
            WebElement titleDestinatarioFieald = driver.findElement(By.id("title-heading-section"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(titleDestinatarioFieald));
            logger.info("Destinatario PA Section caricata ");
        } catch (TimeoutException e) {
            logger.error("Destinatario PA Section non caricata con errore : " + e.getMessage());
            Assertions.fail("Destinatario PA Section non caricata con errore : " + e.getMessage());
        }

    }

    public void selezionarePersonaFisica() {
        logger.info("selezione pf su checkbox");
        personaFisicaCheckBox = driver.findElement(By.cssSelector("input[value='PF']"));
        personaFisicaCheckBox.click();
    }


    public void inserireNomeDestinatario(String nomeDestinatario) {
        logger.info("inserimento nome destinatario");
        nomeDestinatarioTextField = driver.findElement(By.id("recipients[0].firstName"));
        scrollToElementClickAndInsertText(nomeDestinatarioTextField, nomeDestinatario);
    }

    public void inserireCognomeDestinatario(String cognomeDestinatario) {
        logger.info("inserimento cognome destinatario");
        cognomeDestinatarioTextField = driver.findElement(By.id("recipients[0].lastName"));
        scrollToElementClickAndInsertText(cognomeDestinatarioTextField, cognomeDestinatario);
    }

    public void inserireCodiceFiscaleDestinatario(String codiceFiscale) {
        logger.info("inserimento codice fiscale destinatario");
        codiceFiscaleDestinatarioTextField = driver.findElement(By.id("recipients[0].taxId"));
        scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextField, codiceFiscale);
    }

    public boolean checkCampiDestinatarioPopolati() {
        nomeDestinatarioTextField = driver.findElement(By.id("recipients[0].firstName"));
        cognomeDestinatarioTextField = driver.findElement(By.id("recipients[0].lastName"));
        codiceFiscaleDestinatarioTextField = driver.findElement(By.id("recipients[0].taxId"));
        if (!nomeDestinatarioTextField.getAttribute("value").isEmpty() &&
                !cognomeDestinatarioTextField.getAttribute("value").isEmpty() &&
                !codiceFiscaleDestinatarioTextField.getAttribute("value").isEmpty()
        ) {
            logger.info("I campi sono popolati");
            return true;
        } else {
            logger.info("I campi non sono popolati");
            return false;
        }

    }

    public void selezionaAggiungiUnIndirizzoFisico() {
        logger.info("click su scelta indirizzo fisico");
        aggiungiUnIndirizzoFisicoCheckBox = driver.findElement(By.xpath("//div[@data-testid='recipients[0].physicalAddressCheckbox']"));
        aggiungiUnIndirizzoFisicoCheckBox.click();
    }

    public void selezionaAggiungiUnIndirizzoDigitale() {
        logger.info("click su scelta indirizzo digitale");
        checkDomicilioDigitale = driver.findElement(By.id("add-digital-domicile"));
        checkDomicilioDigitale.click();
    }

    public void inserireIndirizzo(String indirizzo, int i) {
        logger.info("inserimento indirizzo fisico destinatario " +i);
        indirizzoTextField = driver.findElement(By.id("recipients["+i+"].address"));
        scrollToElementClickAndInsertText(indirizzoTextField, indirizzo);
    }

    public void inserireNumeroCivico(String numeroCivico, int i) {
        logger.info("inserimento numero civico destinatario " +i);
        numeroCivicoTextField = driver.findElement(By.id("recipients["+i+"].houseNumber"));
        scrollToElementClickAndInsertText(numeroCivicoTextField, numeroCivico);
    }

    public void inserireLocalita(String localita, int i) {
        logger.info("inserimento localitá destinatario " +i);
        localitaTextField = driver.findElement(By.id( "recipients["+i+"].municipalityDetails"));
        scrollToElementClickAndInsertText(localitaTextField, localita);
    }

    public void inserireComune(String comune, int i) {
        logger.info("inserimento comune destinatario " +i);
        comuneTextField = driver.findElement(By.id("recipients["+i+"].municipality"));
        scrollToElementClickAndInsertText(comuneTextField, comune);
    }

    public void inserireProvincia(String provincia, int i) {
        logger.info("inserimento provincia destinatario " +i);
        provinciaTextField = driver.findElement(By.id("recipients["+i+"].province"));
        scrollToElementClickAndInsertText(provinciaTextField, provincia);
    }

    public void inserireCodicePostale(String codicePostale, int i) {
        logger.info("inserimento codice postale destinatario " +i);
        codicePostaleTextField = driver.findElement(By.id("recipients["+i+"].zip"));
        this.scrollToElementClickAndInsertText(this.codicePostaleTextField, codicePostale);
    }

    public void cambiareCodicePostale(String codicePostale, int i) {
        logger.info("cambio codice postale destinatario " +i);
        codicePostaleTextField = driver.findElement(By.id("recipients["+i+"].zip"));
        clearWebElementField(codicePostaleTextField);
        codicePostaleTextField.sendKeys(codicePostale);
    }

    public void inserireStato(String stato, int i) {
        logger.info("inserimento stato destinatario " +i);
        statoTextField = driver.findElement(By.id("recipients["+i+"].foreignState"));
        scrollToElementClickAndInsertText(statoTextField, stato);
    }

    public void selezionareAggiungiDestinatarioButton() {
        logger.info("scelta aggiungi destinatario");
        aggiungiDestinatarioButton = driver.findElement(By.id("add-recipient"));
        aggiungiDestinatarioButton.click();
    }

    public void selezionareRimuoviDestinatarioButton() {
        logger.info("click su rimuovi destinatario");
        rimuoviDestinatarioButtons = driver.findElements(By.xpath("//button[contains(@data-testid,'DeleteRecipientIcon')]"));
        rimuoviDestinatarioButtons.get(1).click();
    }

    public void inserimentoMultiDestinatario(PersoneFisiche destinatari, int nDestinatari) {
        for (int i = 0; i < nDestinatari; i++) {
            inserimentoInformazioniPreliminari(destinatari, i);
            inserimentoInformazioniAggiuntive(destinatari, i+1);
           if (i != nDestinatari - 1) {
                selezionareAggiungiDestinatarioButton();
            }
        }
    }

    public void inserimentoInformazioniPreliminari(PersoneFisiche destinatari, int i) {
        selezionarePersonaFisica();
        inserireInfoMultiDestinatario("//input[contains(@id,'firstName')]", destinatari.getPersone().get(i).getName());
        inserireInfoMultiDestinatario("//input[contains(@id,'lastName')]", destinatari.getPersone().get(i).getFamilyName());
        inserireInfoMultiDestinatario("//input[contains(@id,'taxId')]", destinatari.getPersone().get(i).getCodiceFiscale());
       // selezionaAggiungiUnIndirizzoFisicoMulti(i + 1);
    }

    public void inserimentoInformazioniAggiuntive(PersoneFisiche destinatari, int i) {

        logger.info("Si inseriscono i dati personali della persona giuridica");

        inserireInfoMultiDestinatario("//label[contains(@id,'address-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getIndirizzo());
        inserireInfoMultiDestinatario("//input[contains(@id,'houseNumber')]", destinatari.getPersone().get(i).getNumeroCivico());
        inserireInfoMultiDestinatario("//label[contains(@id,'municipalityDetails-label')]/following-sibling::div/input", destinatari.getPersone().get(i). getLocalita());
        inserireInfoMultiDestinatario("//label[contains(@id,'municipality-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getComune());
        inserireInfoMultiDestinatario("//input[contains(@id,'province')]", destinatari.getPersone().get(i).getProvincia());
        inserireInfoMultiDestinatario("//input[contains(@id,'zip')]", destinatari.getPersone().get(i).getCodicePostale());



/**
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
 **/
    }

    public void inserimentoInformazioniAggiuntive(PersoneGiuridiche destinatari, int i) {

        logger.info("Si inseriscono i dati personali della persona giuridica");

        this.inserireInfoMultiDestinatario("//label[contains(@id,'address-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getIndirizzo());
        this.inserireInfoMultiDestinatario("//input[contains(@id,'houseNumber')]", destinatari.getPersone().get(i).getNumeroCivico());
        this.inserireInfoMultiDestinatario("//label[contains(@id,'municipalityDetails-label')]/following-sibling::div/input", destinatari.getPersone().get(i). getLocalita());
        this.inserireInfoMultiDestinatario("//label[contains(@id,'municipality-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getComune());
        this. inserireInfoMultiDestinatario("//input[contains(@id,'province')]", destinatari.getPersone().get(i).getProvincia());
        this.inserireInfoMultiDestinatario("//input[contains(@id,'zip')]", destinatari.getPersone().get(i).getCodicePostale());



/**
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
 **/
    }


    private void selezionaAggiungiUnIndirizzoFisicoMulti(int i) {
        List<WebElement> aggiungiIndirizzoBy = driver.findElements(By.xpath("//label[@data-testid='showPhysicalAddress" + i + "']"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(aggiungiIndirizzoBy));
        scrollToElementClickAndInsertText(aggiungiIndirizzoBy.get(aggiungiIndirizzoBy.size() - 1), null);
    }

    private void inserireInfoMultiDestinatario(String xpath, String nomeDestinatario) {
        try {
            List<WebElement> nomeDestinatarioBy = driver.findElements(By.xpath(xpath));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(nomeDestinatarioBy));
            scrollToElementClickAndInsertText(nomeDestinatarioBy.get(nomeDestinatarioBy.size() - 1), nomeDestinatario);
        } catch (TimeoutException e) {
            logger.error("Xpath non trovato con errore: " + e.getMessage());
            Assertions.fail("Xpath non trovato con errore: " + e.getMessage());
        }
    }

    public boolean inserireIlSestoDestinatario() {
        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
        List<WebElement> aggiungereDestinatarioButtonBy = driver.findElements(By.xpath("//button[contains(@data-testid,'add-recipient')]"));
        return aggiungereDestinatarioButtonBy.isEmpty();
    }

    public void waitMessaggioErrore() {
        try {
            WebElement errorMessagePrimoDestinatario = driver.findElement(By.xpath("//p[@id='recipients[0].taxId-helper-text']"));
            WebElement errorMessageSecondoDestinatario = driver.findElement(By.xpath("//p[@id='recipients[1].taxId-helper-text']"));
            getWebDriverWait(10).withMessage("Non si visualizza il messaggio di errore del primo destinatario").until(ExpectedConditions.visibilityOf(errorMessagePrimoDestinatario));
            getWebDriverWait(10).withMessage("Non si visualizza il messaggio di errore del secondo destinatario").until(ExpectedConditions.visibilityOf(errorMessageSecondoDestinatario));
            logger.info("I messaggi di errore vengono visualizzati correttamente");
        } catch (TimeoutException e) {
            logger.error("Il messaggio di errore non viene visualizzato con errore: " + e.getMessage());
            Assertions.fail("Il messaggio di errore non viene visualizzato con errore: " + e.getMessage());
        }

    }

    public void inserimentoMultiDestinatarioPG(PersoneGiuridiche personeGiuridiche, int nDestinatari) {
        for (int i = 0; i < nDestinatari; i++) {
            inserimentoInformazioniPreliminariPG(personeGiuridiche, i);
            inserimentoInformazioniAggiuntive(personeGiuridiche, i+1);
            if (i != nDestinatari - 1) {
                selezionareAggiungiDestinatarioButton();
           }
        }
    }



    public void inserimentoDestinatarioPGAggiuntivo(Map<String, String> destinatario) {

        String soggettoGiuridico = destinatario.get("soggettoGiuridico");
        if (soggettoGiuridico.equals("PG")) {
            WebElement secondPGButton = driver.findElement(By.xpath("//input[@name='recipients[1].recipientType' and @value ='PG']"));
            secondPGButton.click();
        } else {
            throw new IllegalStateException("soggettoGiuridico non è PG");
        }
        WebElement ragioneSociale = driver.findElement(By.id("recipients[1].firstName"));
        ragioneSociale.sendKeys(destinatario.get("ragioneSociale"));
        WebElement secondCodiceFiscale = driver.findElement(By.id("recipients[1].taxId"));
        secondCodiceFiscale.sendKeys(destinatario.get("codiceFiscale"));
//        WebElement addSecondPec = driver.findElement(By.xpath("//*[@data-testid='recipients[1].digitalDomicileCheckbox']"));
//        addSecondPec.click();
        //Check per casi di test con pec di piattaforma mancante (irreperibile o deceduto)
        if (destinatario.get("pec") != null) {
            WebElement secondPecField = driver.findElement(By.id("recipients[1].digitalDomicile"));
            secondPecField.sendKeys(destinatario.get("pec"));
        }
//        WebElement addSecondAddress = driver.findElement(By.xpath("//label[@data-testid='showPhysicalAddress1']"));
//        addSecondAddress.click();
        WebElement secondAddress = driver.findElement(By.id("recipients[1].address"));
        secondAddress.sendKeys(destinatario.get("indirizzo"));
        WebElement secondNumber = driver.findElement(By.id("recipients[1].houseNumber"));
        secondNumber.sendKeys(destinatario.get("civico"));
        WebElement secondMunicipalityDetails = driver.findElement(By.id("recipients[1].municipalityDetails"));
        secondMunicipalityDetails.sendKeys(destinatario.get("localita"));
        WebElement secondMunicipality = driver.findElement(By.id("recipients[1].municipality"));
        secondMunicipality.sendKeys(destinatario.get("comune"));
        WebElement secondProvince = driver.findElement(By.id("recipients[1].province"));
        secondProvince.sendKeys(destinatario.get("provincia"));
        WebElement secondZip = driver.findElement(By.id("recipients[1].zip"));
        secondZip.sendKeys(destinatario.get("cap"));
        WebElement secondCountry = driver.findElement(By.id("recipients[1].foreignState"));
        secondCountry.sendKeys(destinatario.get("stato"));
    }

    private void inserimentoInformazioniPreliminariPG(PersoneGiuridiche personeGiuridiche, int i) {
        clickRadioButtonPersonaGiuridica(i + 1);
        //String nomeDestinatario = personeGiuridiche.getPersone().get(i).getName();
        inserireInfoMultiDestinatario("//input[contains(@id,'firstName')]", personeGiuridiche.getPersone().get(i).getName());
       // String cfDestinatario = personeGiuridiche.getPersone().get(i).getCodiceFiscale();
      //  cfDestinatario = cfDestinatario.replace(" ", "");
        inserireInfoMultiDestinatario("//input[contains(@id,'taxId')]", personeGiuridiche.getPersone().get(i).getCodiceFiscale());
       // selezionaAggiungiUnIndirizzoFisicoMulti(i + 1);
    }

    private void clickRadioButtonPersonaGiuridica(int posizione) {
        WebElement radioButtonPgBy = driver.findElement(By.xpath("//input[@name='recipients[" + posizione + "].recipientType' and @value ='PG']"));
        radioButtonPgBy.click();
    }

    public void checkBoxAggiungiDomicilio() {
        webTool.waitTime(3);
        checkBoxAggiungiDomicilioDigitale = driver.findElement(By.xpath("//label[@id='recipients[0].digitalDomicile-label']"));
        //checkBoxAggiungiDomicilioDigitale.click();
        getWebDriverWait(10).withMessage("Il bottone chiudi non è cliccabile").until(ExpectedConditions.visibilityOf(checkBoxAggiungiDomicilioDigitale));

    }

    public void insertDomicilioDigitale(String emailPec) {
        domicilioDigitaleTextField = driver.findElement(By.xpath("//input[@id='recipients[0].digitalDomicile']"));
        domicilioDigitaleTextField.sendKeys(emailPec);
    }

    public void insertDomicilioDigitaleErrato(String emailPec) {
        if(StringUtils.isEmpty(emailPec)){
            throw new IllegalArgumentException("L'emailPec è vuoto");
        }
        domicilioDigitaleTextField = driver.findElement(By.xpath("//input[@id='recipients[0].digitalDomicile']"));
        domicilioDigitaleTextField.sendKeys(emailPec);
    }

    public void insertRagioneSociale(String ragioneSociale) {
        ragioneSocialeTextField = driver.findElement(By.xpath("//input[@id='recipients[0].firstName']"));
        ragioneSocialeTextField.sendKeys(ragioneSociale);
    }

    public void insertPartitaIva(String codiceFiscale) {
        partitaIvaTextField = driver.findElement(By.id("recipients[0].taxId"));
        partitaIvaTextField.sendKeys(codiceFiscale);
    }

    public void clickSuTornaInformazioniPreliminari() {
        informazioniPreliminariButton = driver.findElement(By.xpath("//button[@data-testid='previous-step']"));
        informazioniPreliminariButton.click();
    }

    public void clickRadioButtonPersonaGiuridica() {
        personaGiuridicaRadioButton = driver.findElement(By.xpath("//input[@value='PG']"));
        personaGiuridicaRadioButton.click();
    }

    public void insertCodiceFiscaleErrato(String codiceFiscale) {
        logger.info("TA_QA: si inserisci codice fiscale errato");
        WebElement valoreErratoBy = driver.findElement(By.id("recipients[0].taxId"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(valoreErratoBy));
        valoreErratoBy.sendKeys(codiceFiscale);
    }

    public String getCodiceFiscaleError() {
        logger.info("TA_QA: si legge il messagio di errore del Codice fiscale");
        WebElement valoreCFErratoBy = driver.findElement(By.id("recipients[0].taxId-helper-text"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(valoreCFErratoBy));
        return valoreCFErratoBy.getText();
    }

    public String getDomicilioDigitaleError() {
        logger.info("TA_QA: si legge il messagio di errore del digital domicile");
        WebElement valoreErratoBy = driver.findElement(By.id("recipients[0].digitalDomicile-helper-text"));
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(valoreErratoBy));
        return valoreErratoBy.getText();
    }

    public boolean verificaNumeroDestinatari() {
        logger.info("TA_QA: si verifica il numero dei destinatari");
        rimuoviDestinatarioButtons = driver.findElements(By.id("//button[contains(@data-testid,'DeleteRecipientIcon')]"));
        return this.rimuoviDestinatarioButtons.isEmpty();
    }

    public void compilazioneDestinario(Map<String, String> datiNotificaMap) {
        if (datiNotificaMap.get("destinatario").equalsIgnoreCase("pf")) {
            selezionarePersonaFisica();
            inserireNomeDestinatario(datiNotificaMap.get("nome"));
            inserireCognomeDestinatario(datiNotificaMap.get("cognome"));
        } else {
            clickRadioButtonPersonaGiuridica();
            insertRagioneSociale(datiNotificaMap.get("ragioneSociale"));
        }

        inserireCodiceFiscaleDestinatario(datiNotificaMap.get("codiceFiscale"));
        if (datiNotificaMap.get("email") != null && !datiNotificaMap.get("email").isBlank() || datiNotificaMap.get("pec") != null && !datiNotificaMap.get("pec").isBlank()) {
            selezionaAggiungiUnIndirizzoDigitale();
            insertDomicilioDigitale(datiNotificaMap.get("pec"));
        }
       // selezionaAggiungiUnIndirizzoFisico();
        inserireIndirizzo(datiNotificaMap.get("indirizzo"),0);
        inserireNumeroCivico(datiNotificaMap.get("numeroCivico"),0);
        inserireComune(datiNotificaMap.get("comune"),0);
        inserireProvincia(datiNotificaMap.get("provincia"),0);
        inserireCodicePostale(datiNotificaMap.get("codicepostale"),0);
        inserireStato(datiNotificaMap.get("stato"),0);
        vaiInFondoAllaPagina();
    }

    public void selezionarePersonaFisicaMultiDestinatario(int numeroDestinatario) {
        logger.info("selezione pf su checkbox del destinatario numero: " + (numeroDestinatario + 1));
        List<WebElement> personaFisicaCheckBox = driver.findElements(By.xpath("//label[@id='recipient-pf' and @data-testid='recipientType" + numeroDestinatario + "']/span"));
        js().executeScript("arguments[0].scrollIntoView(true);", personaFisicaCheckBox.get(0));
        getWebDriverWait(10).withMessage("Checkbox di persona fisica non visibile del destinatario numero " + (numeroDestinatario + 1)).until(ExpectedConditions.visibilityOf(personaFisicaCheckBox.get(0)));
        personaFisicaCheckBox.get(0).click();
    }

    public void inserireNomeMultiDestinatario(int numeroDestinatario, String nomeDestinatario) {
        logger.info("inserimento nome del destinatario numero " + (numeroDestinatario + 1));
        WebElement nomeDestinatarioTextFieldBy = driver.findElement(By.id("recipients[" + numeroDestinatario + "].firstName"));
        scrollToElementClickAndInsertText(nomeDestinatarioTextFieldBy, nomeDestinatario);
    }

    public void inserireCognomeMultiDestinatario(int numeroDestinatario, String nomeDestinatario) {
        logger.info("inserimento cognome del destinatario numero " + (numeroDestinatario + 1));
        WebElement cognomeDestinatarioTextFieldBy = driver.findElement(By.id("recipients[" + numeroDestinatario + "].lastName"));
        scrollToElementClickAndInsertText(cognomeDestinatarioTextFieldBy, nomeDestinatario);
    }

    public void inserireCodiceFiscaleMultiDestinatario(int numeroDestinatario, String codiceFiscale) {
        logger.info("inserimento codice fiscale del destinatario numero " + (numeroDestinatario + 1));
        WebElement codiceFiscaleDestinatarioTextFieldBy = driver.findElement(By.id("recipients[" + numeroDestinatario + "].taxId"));
        scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextFieldBy, codiceFiscale);
    }

    public void clickTornaAlleDeleghe() {
        WebElement generateApiKeyButton = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("courtesy-page-button")));
        generateApiKeyButton.click();
    }

    public void verificaPresenzaSezionePagamenti(int numeroAvvisi) {


            getWebDriverWait(10)
                    .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[contains(@class, 'css-kwxqgy')]"),numeroAvvisi )
            );






    }

    public void clickSuElimina() {
        WebElement eliminaButton = getWebDriverWait(10)
                .withMessage("Impossibile trovare il bottone 'Elimina'")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='pagopa-delete-button']")
                ));

        eliminaButton.click();
    }
}
