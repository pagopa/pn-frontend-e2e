package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.model.recipients.PersoneFisiche;
import it.pn.frontend.e2e.model.recipients.PersoneGiuridiche;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class DestinatarioPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DestinatarioPASection.class);


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


    //    public void waitLoadDestinatarioPASection() {
//        try {
//            WebElement titleDestinatarioFieald = driver.findElement(By.id("title-heading-section"));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(titleDestinatarioFieald));
//            logger.info("Destinatario PA Section caricata ");
//        } catch (TimeoutException e) {
//            Assertions.fail("Destinatario PA Section non caricata con errore : " + e.getMessage());
//        }
//
//    }
    public void waitLoadDestinatarioPASection() {
        WebElement titleDestinatarioField = getWebDriverWait(30)
                .withMessage("Destinatario PA Section non caricata entro il timeout")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("title-heading-section")));

        logger.info("Destinatario PA Section caricata: {}", titleDestinatarioField.getText());
    }


    //    public void selezionarePersonaFisica() {
//        logger.info("selezione pf su checkbox");
//        personaFisicaCheckBox = driver.findElement(By.cssSelector("input[value='PF']"));
//        personaFisicaCheckBox.click();
//    }
    public void selezionarePersonaFisica() {
        logger.info("Selezione PF su checkbox");

        personaFisicaCheckBox = getWebDriverWait(15)
                .withMessage("La checkbox 'PF' non è visibile o cliccabile")
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='PF']")));

        personaFisicaCheckBox.click();
    }


    //    public void inserireNomeDestinatario(String nomeDestinatario) {
//        logger.info("inserimento nome destinatario");
//        nomeDestinatarioTextField = driver.findElement(By.id("recipients[0].firstName"));
//        scrollToElementClickAndInsertText(nomeDestinatarioTextField, nomeDestinatario);
//    }
    public void inserireNomeDestinatario(String nomeDestinatario) {
        logger.info("Inserimento nome destinatario");

        nomeDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Nome destinatario' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[0].firstName")));

        scrollToElementClickAndInsertText(nomeDestinatarioTextField, nomeDestinatario);
    }


    //    public void inserireAggiungiNomeDestinatario(String nomeDestinatario) {
//        logger.info("inserimento nome destinatario");
//        nomeDestinatarioTextField = driver.findElement(By.id("recipients[1].firstName"));
//        scrollToElementClickAndInsertText(nomeDestinatarioTextField, nomeDestinatario);
//    }
    public void inserireAggiungiNomeDestinatario(String nomeDestinatario) {
        logger.info("Inserimento nome destinatario aggiuntivo");

        nomeDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Nome destinatario aggiuntivo' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].firstName")));

        scrollToElementClickAndInsertText(nomeDestinatarioTextField, nomeDestinatario);
    }


    //    public void inserireCognomeDestinatario(String cognomeDestinatario) {
//        logger.info("inserimento cognome destinatario");
//        cognomeDestinatarioTextField = driver.findElement(By.id("recipients[0].lastName"));
//        scrollToElementClickAndInsertText(cognomeDestinatarioTextField, cognomeDestinatario);
//    }
    public void inserireCognomeDestinatario(String cognomeDestinatario) {
        logger.info("Inserimento cognome destinatario");

        cognomeDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Cognome destinatario' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[0].lastName")));

        scrollToElementClickAndInsertText(cognomeDestinatarioTextField, cognomeDestinatario);
    }


    //    public void inserireAggiungiCognomeDestinatario(String cognomeDestinatario) {
//        logger.info("inserimento cognome destinatario");
//        cognomeDestinatarioTextField = driver.findElement(By.id("recipients[1].lastName"));
//        scrollToElementClickAndInsertText(cognomeDestinatarioTextField, cognomeDestinatario);
//    }
    public void inserireAggiungiCognomeDestinatario(String cognomeDestinatario) {
        logger.info("Inserimento cognome destinatario aggiuntivo");

        cognomeDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Cognome destinatario aggiuntivo' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].lastName")));

        scrollToElementClickAndInsertText(cognomeDestinatarioTextField, cognomeDestinatario);
    }


    //    public void inserireCodiceFiscaleDestinatario(String codiceFiscale) {
//        logger.info("inserimento codice fiscale destinatario");
//        codiceFiscaleDestinatarioTextField = driver.findElement(By.id("recipients[0].taxId"));
//        scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextField, codiceFiscale);
//    }
    public void inserireCodiceFiscaleDestinatario(String codiceFiscale) {
        logger.info("Inserimento codice fiscale destinatario");

        codiceFiscaleDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Codice Fiscale destinatario' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[0].taxId")));

        scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextField, codiceFiscale);
    }


    //    public void inserireAggiungiCodiceFiscaleDestinatario(String codiceFiscale) {
//        logger.info("inserimento codice fiscale destinatario");
//        codiceFiscaleDestinatarioTextField = driver.findElement(By.id("recipients[1].taxId"));
//        scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextField, codiceFiscale);
//    }
    public void inserireAggiungiCodiceFiscaleDestinatario(String codiceFiscale) {
        logger.info("Inserimento codice fiscale destinatario aggiuntivo");

        codiceFiscaleDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Codice Fiscale destinatario aggiuntivo' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].taxId")));

        scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextField, codiceFiscale);
    }


    //    public boolean checkCampiDestinatarioPopolati() {
//        nomeDestinatarioTextField = driver.findElement(By.id("recipients[0].firstName"));
//        cognomeDestinatarioTextField = driver.findElement(By.id("recipients[0].lastName"));
//        codiceFiscaleDestinatarioTextField = driver.findElement(By.id("recipients[0].taxId"));
//        if (!nomeDestinatarioTextField.getAttribute("value").isEmpty() &&
//                !cognomeDestinatarioTextField.getAttribute("value").isEmpty() &&
//                !codiceFiscaleDestinatarioTextField.getAttribute("value").isEmpty()
//        ) {
//            logger.info("I campi sono popolati");
//            return true;
//        } else {
//            logger.info("I campi non sono popolati");
//            return false;
//        }
//
//    }
    public boolean checkCampiDestinatarioPopolati() {
        nomeDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Nome destinatario' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[0].firstName")));

        cognomeDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Cognome destinatario' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[0].lastName")));

        codiceFiscaleDestinatarioTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Codice Fiscale destinatario' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[0].taxId")));

        boolean tuttiPopolati = !nomeDestinatarioTextField.getAttribute("value").isEmpty() &&
                !cognomeDestinatarioTextField.getAttribute("value").isEmpty() &&
                !codiceFiscaleDestinatarioTextField.getAttribute("value").isEmpty();

        if (tuttiPopolati) {
            logger.info("I campi sono popolati");
        } else {
            logger.info("I campi non sono popolati");
        }

        return tuttiPopolati;
    }


//    public void selezionaAggiungiUnIndirizzoFisico() {
//        logger.info("click su scelta indirizzo fisico");
//        aggiungiUnIndirizzoFisicoCheckBox = driver.findElement(By.xpath("//div[@data-testid='recipients[0].physicalAddressCheckbox']"));
//        aggiungiUnIndirizzoFisicoCheckBox.click();
//    }

    //    public void selezionaAggiungiUnIndirizzoDigitale() {
//        logger.info("click su scelta indirizzo digitale");
//        checkDomicilioDigitale = driver.findElement(By.id("add-digital-domicile"));
//        checkDomicilioDigitale.click();
//    }
    public void selezionaAggiungiUnIndirizzoDigitale() {
        logger.info("Click su scelta indirizzo digitale");

        checkDomicilioDigitale = getWebDriverWait(15)
                .withMessage("La checkbox 'Aggiungi un indirizzo digitale' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("add-digital-domicile")));

        checkDomicilioDigitale.click();
    }


    //    public void inserireIndirizzo(String indirizzo, int i) {
//        logger.info("inserimento indirizzo fisico destinatario: {}", i);
//        indirizzoTextField = driver.findElement(By.id("recipients[" + i + "].address"));
//        scrollToElementClickAndInsertText(indirizzoTextField, indirizzo);
//    }
    public void inserireIndirizzo(String indirizzo, int i) {
        logger.info("Inserimento indirizzo fisico destinatario: {}", i);

        indirizzoTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Indirizzo destinatario " + i + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[" + i + "].address")));

        scrollToElementClickAndInsertText(indirizzoTextField, indirizzo);
    }


    //    public void inserireNumeroCivico(String numeroCivico, int i) {
//        logger.info("inserimento numero civico destinatario: {}",i);
//        numeroCivicoTextField = driver.findElement(By.id("recipients[" + i + "].houseNumber"));
//        scrollToElementClickAndInsertText(numeroCivicoTextField, numeroCivico);
//    }
    public void inserireNumeroCivico(String numeroCivico, int i) {
        logger.info("Inserimento numero civico destinatario: {}", i);

        numeroCivicoTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Numero civico destinatario " + i + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[" + i + "].houseNumber")));

        scrollToElementClickAndInsertText(numeroCivicoTextField, numeroCivico);
    }


    //    public void inserireLocalita(String localita, int i) {
//        logger.info("inserimento localitá destinatario: {}", i);
//        localitaTextField = driver.findElement(By.id("recipients[" + i + "].municipalityDetails"));
//        scrollToElementClickAndInsertText(localitaTextField, localita);
//    }
    public void inserireLocalita(String localita, int i) {
        logger.info("Inserimento località destinatario: {}", i);

        localitaTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Località destinatario " + i + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[" + i + "].municipalityDetails")));

        scrollToElementClickAndInsertText(localitaTextField, localita);
    }


    //    public void inserireComune(String comune, int i) {
//        logger.info("inserimento comune destinatario: {}", i);
//        comuneTextField = driver.findElement(By.id("recipients[" + i + "].municipality"));
//        scrollToElementClickAndInsertText(comuneTextField, comune);
//    }
    public void inserireComune(String comune, int i) {
        logger.info("Inserimento comune destinatario: {}", i);

        comuneTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Comune destinatario " + i + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[" + i + "].municipality")));

        scrollToElementClickAndInsertText(comuneTextField, comune);
    }


    //    public void inserireProvincia(String provincia, int i) {
//        logger.info("inserimento provincia destinatario: {}", i);
//        provinciaTextField = driver.findElement(By.id("recipients[" + i + "].province"));
//        scrollToElementClickAndInsertText(provinciaTextField, provincia);
//    }
    public void inserireProvincia(String provincia, int i) {
        logger.info("Inserimento provincia destinatario: {}", i);

        provinciaTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Provincia destinatario " + i + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[" + i + "].province")));

        scrollToElementClickAndInsertText(provinciaTextField, provincia);
    }


    //    public void inserireCodicePostale(String codicePostale, int i) {
//        logger.info("inserimento codice postale destinatario: {}", i);
//        codicePostaleTextField = driver.findElement(By.id("recipients[" + i + "].zip"));
//        this.scrollToElementClickAndInsertText(this.codicePostaleTextField, codicePostale);
//    }
    public void inserireCodicePostale(String codicePostale, int i) {
        logger.info("Inserimento codice postale destinatario: {}", i);

        codicePostaleTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Codice postale destinatario " + i + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[" + i + "].zip")));

        scrollToElementClickAndInsertText(codicePostaleTextField, codicePostale);
    }


    //    public void cambiareCodicePostale(String codicePostale, int i) {
//        logger.info("cambio codice postale destinatario: {}", i);
//        codicePostaleTextField = driver.findElement(By.id("recipients[" + i + "].zip"));
//        clearWebElementField(codicePostaleTextField);
//        codicePostaleTextField.sendKeys(codicePostale);
//    }
    public void cambiareCodicePostale(String codicePostale, int i) {
        logger.info("Cambio codice postale destinatario: {}", i);

        codicePostaleTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Codice postale destinatario " + i + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[" + i + "].zip")));

        clearWebElementField(codicePostaleTextField);
        codicePostaleTextField.sendKeys(codicePostale);
    }


    //    public void inserireStato(String stato, int i) {
//        logger.info("inserimento stato destinatario: {}", i);
//        statoTextField = driver.findElement(By.id("recipients[" + i + "].foreignState"));
//        scrollToElementClickAndInsertText(statoTextField, stato);
//    }
    public void inserireStato(String stato, int i) {
        logger.info("Inserimento stato destinatario: {}", i);

        statoTextField = getWebDriverWait(15)
                .withMessage("Il campo 'Stato destinatario " + i + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[" + i + "].foreignState")));

        scrollToElementClickAndInsertText(statoTextField, stato);
    }


    //    public void selezionareAggiungiDestinatarioButton() {
//        logger.info("scelta aggiungi destinatario");
//        aggiungiDestinatarioButton = driver.findElement(By.id("add-recipient"));
//        aggiungiDestinatarioButton.click();
//    }
    public void selezionareAggiungiDestinatarioButton() {
        logger.info("Selezione pulsante 'Aggiungi destinatario'");

        aggiungiDestinatarioButton = getWebDriverWait(15)
                .withMessage("Il pulsante 'Aggiungi destinatario' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("add-recipient")));

        aggiungiDestinatarioButton.click();
    }

    //    public void selezionareRimuoviDestinatarioButton() {
//        logger.info("click su rimuovi destinatario");
//        rimuoviDestinatarioButtons = driver.findElements(By.xpath("//button[contains(@data-testid,'DeleteRecipientIcon')]"));
//        rimuoviDestinatarioButtons.get(1).click();
//    }
    public void selezionareRimuoviDestinatarioButton() {
        logger.info("Click su rimuovi destinatario");

        rimuoviDestinatarioButtons = getWebDriverWait(15)
                .withMessage("I pulsanti 'Rimuovi destinatario' non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[contains(@data-testid,'DeleteRecipientIcon')]")
                ));

        if (rimuoviDestinatarioButtons.size() > 1) {
            rimuoviDestinatarioButtons.get(1).click();
            logger.info("Rimosso il secondo destinatario");
        } else {
            logger.warn("Non è presente un secondo destinatario da rimuovere");
        }
    }


    public void inserimentoMultiDestinatario(PersoneFisiche destinatari, int nDestinatari) {
        int j = 1;
        for (int i = 0; i < nDestinatari; i++) {
            inserimentoInformazioniPreliminari(destinatari, i);
            selezionaRadionButtonInserimentoManualeSeEsiste(Integer.toString(j));
            inserimentoInformazioniAggiuntive(destinatari, i);
            if (i != nDestinatari - 1) {
                selezionareAggiungiDestinatarioButton();
            }
            j++;
        }
    }

    public void inserimentoInformazioniPreliminari(PersoneFisiche destinatari, int i) {
        selezionarePersonaFisica();
        inserireInfoMultiDestinatario("//input[contains(@id,'firstName')]", destinatari.getPersone().get(i).getName());
        inserireInfoMultiDestinatario("//input[contains(@id,'lastName')]", destinatari.getPersone().get(i).getFamilyName());
        inserireInfoMultiDestinatario("//input[contains(@id,'taxId')]", destinatari.getPersone().get(i).getCodiceFiscale());
    }

    public void inserimentoInformazioniAggiuntive(PersoneFisiche destinatari, int i) {

        logger.info("Si inseriscono i dati personali della persona giuridica");

        inserireInfoMultiDestinatario("//label[contains(@id,'address-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getIndirizzo());
        inserireInfoMultiDestinatario("//input[contains(@id,'houseNumber')]", destinatari.getPersone().get(i).getNumeroCivico());
        inserireInfoMultiDestinatario("//label[contains(@id,'municipalityDetails-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getLocalita());
        inserireInfoMultiDestinatario("//label[contains(@id,'municipality-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getComune());
        inserireInfoMultiDestinatario("//input[contains(@id,'province')]", destinatari.getPersone().get(i).getProvincia());
        inserireInfoMultiDestinatario("//input[contains(@id,'zip')]", destinatari.getPersone().get(i).getCodicePostale());

    }

    public void inserimentoInformazioniAggiuntive(PersoneGiuridiche destinatari, int i) {

        logger.info("Si inseriscono i dati personali della persona giuridica");

        this.inserireInfoMultiDestinatario("//label[contains(@id,'address-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getIndirizzo());
        this.inserireInfoMultiDestinatario("//input[contains(@id,'houseNumber')]", destinatari.getPersone().get(i).getNumeroCivico());
        this.inserireInfoMultiDestinatario("//label[contains(@id,'municipalityDetails-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getLocalita());
        this.inserireInfoMultiDestinatario("//label[contains(@id,'municipality-label')]/following-sibling::div/input", destinatari.getPersone().get(i).getComune());
        this.inserireInfoMultiDestinatario("//input[contains(@id,'province')]", destinatari.getPersone().get(i).getProvincia());
        this.inserireInfoMultiDestinatario("//input[contains(@id,'zip')]", destinatari.getPersone().get(i).getCodicePostale());


    }


//    private void selezionaAggiungiUnIndirizzoFisicoMulti(int i) {
//        List<WebElement> aggiungiIndirizzoBy = driver.findElements(By.xpath("//label[@data-testid='showPhysicalAddress" + i + "']"));
//        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(aggiungiIndirizzoBy));
//        scrollToElementClickAndInsertText(aggiungiIndirizzoBy.get(aggiungiIndirizzoBy.size() - 1), null);
//    }

    //    private void inserireInfoMultiDestinatario(String xpath, String nomeDestinatario) {
//        try {
//            List<WebElement> nomeDestinatarioBy = driver.findElements(By.xpath(xpath));
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(nomeDestinatarioBy));
//            scrollToElementClickAndInsertText(nomeDestinatarioBy.get(nomeDestinatarioBy.size() - 1), nomeDestinatario);
//        } catch (TimeoutException e) {
//            Assertions.fail("Xpath non trovato con errore: " + e.getMessage());
//        }
//    }
    private void inserireInfoMultiDestinatario(String xpath, String nomeDestinatario) {
        List<WebElement> elementi = getWebDriverWait(30)
                .withMessage("Gli elementi con xpath '" + xpath + "' non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));

        // Inserisce il testo nell'ultimo elemento della lista
        scrollToElementClickAndInsertText(elementi.get(elementi.size() - 1), nomeDestinatario);
    }


    //    public boolean inserireIlSestoDestinatario() {
//        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
//        List<WebElement> aggiungereDestinatarioButtonBy = driver.findElements(By.xpath("//button[contains(@data-testid,'add-recipient')]"));
//        return aggiungereDestinatarioButtonBy.isEmpty();
//    }
    public boolean inserireIlSestoDestinatario() {
        // Scroll fino in fondo alla pagina
        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");

        // Attende fino a 5 secondi che i bottoni siano visibili (se presenti)
        List<WebElement> aggiungereDestinatarioButtonBy = getWebDriverWait(5)
                .withMessage("I pulsanti 'Aggiungi destinatario' non sono visibili")
                .until(driver -> driver.findElements(By.xpath("//button[contains(@data-testid,'add-recipient')]")));

        return aggiungereDestinatarioButtonBy.isEmpty();
    }


    public void waitMessaggioErrore() {
        try {
            waitForErrorMessage("//p[@id='recipients[0].taxId-helper-text']", "primo destinatario");
            waitForErrorMessage("//p[@id='recipients[1].taxId-helper-text']", "secondo destinatario");
            logger.info("I messaggi di errore vengono visualizzati correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il messaggio di errore non viene visualizzato con errore: " + e.getMessage());
        }
    }

    private void waitForErrorMessage(String xpath, String destinatario) throws TimeoutException {
        getWebDriverWait(20)
                .withMessage("Non si visualizza il messaggio di errore del " + destinatario)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }


    public void inserimentoMultiDestinatarioPG(PersoneGiuridiche personeGiuridiche, int nDestinatari) {
        int j = 1;
        for (int i = 0; i < nDestinatari; i++) {
            inserimentoInformazioniPreliminariPG(personeGiuridiche, i);
            selezionaRadionButtonInserimentoManualeSeEsiste(Integer.toString(j));
            inserimentoInformazioniAggiuntive(personeGiuridiche, i + 1);
            if (i != nDestinatari - 1) {
                selezionareAggiungiDestinatarioButton();
            }
            j++;
        }
    }

    //    public void inserimentoSecondoDestinatarioPF(Map<String, String> destinatario) {
//
//        String soggettoGiuridico = destinatario.get("soggettoGiuridico");
//        if (soggettoGiuridico.equals("PF")) {
//            WebElement secondPGButton = driver.findElement(By.xpath("//input[@name='recipients[1].recipientType' and @value ='PF']"));
//            secondPGButton.click();
//        } else {
//            throw new IllegalStateException("soggettoGiuridico non è PF");
//        }
//        WebElement nome = driver.findElement(By.id("recipients[1].firstName"));
//        nome.sendKeys(destinatario.get("nome"));
//        WebElement cognome = driver.findElement(By.id("recipients[1].lastName"));
//        cognome.sendKeys(destinatario.get("cognome"));
//        WebElement secondCodiceFiscale = driver.findElement(By.id("recipients[1].taxId"));
//        secondCodiceFiscale.sendKeys(destinatario.get("codiceFiscale"));
//        //Check per casi di test con pec di piattaforma mancante (irreperibile o deceduto)
//        if (destinatario.get("pec") != null) {
//            WebElement secondPecField = driver.findElement(By.id("recipients[1].digitalDomicile"));
//            secondPecField.sendKeys(destinatario.get("pec"));
//        }
//        WebElement secondAddress = driver.findElement(By.id("recipients[1].address"));
//        secondAddress.sendKeys(destinatario.get("indirizzo"));
//        WebElement secondNumber = driver.findElement(By.id("recipients[1].houseNumber"));
//        secondNumber.sendKeys(destinatario.get("civico"));
//        WebElement secondMunicipalityDetails = driver.findElement(By.id("recipients[1].municipalityDetails"));
//        secondMunicipalityDetails.sendKeys(destinatario.get("localita"));
//        WebElement secondMunicipality = driver.findElement(By.id("recipients[1].municipality"));
//        secondMunicipality.sendKeys(destinatario.get("comune"));
//        WebElement secondProvince = driver.findElement(By.id("recipients[1].province"));
//        secondProvince.sendKeys(destinatario.get("provincia"));
//        WebElement secondZip = driver.findElement(By.id("recipients[1].zip"));
//        secondZip.sendKeys(destinatario.get("cap"));
//        WebElement secondCountry = driver.findElement(By.id("recipients[1].foreignState"));
//        secondCountry.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        secondCountry.sendKeys(Keys.DELETE);
//        secondCountry.sendKeys(destinatario.get("stato"));
//    }
    public void inserimentoSecondoDestinatarioPF(Map<String, String> destinatario) {
        String soggettoGiuridico = destinatario.get("soggettoGiuridico");
        if (!"PF".equals(soggettoGiuridico)) {
            throw new IllegalStateException("soggettoGiuridico non è PF");
        }

        // Seleziona il tipo PF
        WebElement secondPGButton = getWebDriverWait(15)
                .withMessage("Il radio button 'PF' per il secondo destinatario non è visibile")
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@name='recipients[1].recipientType' and @value ='PF']")
                ));
        secondPGButton.click();

        // Inserimento dei campi
        WebElement nome = getWebDriverWait(15)
                .withMessage("Il campo 'Nome' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].firstName")));
        nome.sendKeys(destinatario.get("nome"));

        WebElement cognome = getWebDriverWait(15)
                .withMessage("Il campo 'Cognome' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].lastName")));
        cognome.sendKeys(destinatario.get("cognome"));

        WebElement codiceFiscale = getWebDriverWait(15)
                .withMessage("Il campo 'Codice Fiscale' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].taxId")));
        codiceFiscale.sendKeys(destinatario.get("codiceFiscale"));

        // Pec opzionale
        if (destinatario.get("pec") != null) {
            WebElement pecField = getWebDriverWait(15)
                    .withMessage("Il campo 'PEC' del secondo destinatario non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].digitalDomicile")));
            pecField.sendKeys(destinatario.get("pec"));
        }

        WebElement address = getWebDriverWait(15)
                .withMessage("Il campo 'Indirizzo' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].address")));
        address.sendKeys(destinatario.get("indirizzo"));

        WebElement houseNumber = getWebDriverWait(15)
                .withMessage("Il campo 'Numero civico' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].houseNumber")));
        houseNumber.sendKeys(destinatario.get("civico"));

        WebElement municipalityDetails = getWebDriverWait(15)
                .withMessage("Il campo 'Località' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].municipalityDetails")));
        municipalityDetails.sendKeys(destinatario.get("localita"));

        WebElement municipality = getWebDriverWait(15)
                .withMessage("Il campo 'Comune' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].municipality")));
        municipality.sendKeys(destinatario.get("comune"));

        WebElement province = getWebDriverWait(15)
                .withMessage("Il campo 'Provincia' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].province")));
        province.sendKeys(destinatario.get("provincia"));

        WebElement zip = getWebDriverWait(15)
                .withMessage("Il campo 'CAP' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].zip")));
        zip.sendKeys(destinatario.get("cap"));

        WebElement country = getWebDriverWait(15)
                .withMessage("Il campo 'Stato' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].foreignState")));
        country.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        country.sendKeys(Keys.DELETE);
        country.sendKeys(destinatario.get("stato"));
    }


    //    public void inserimentoDestinatarioPGAggiuntivo(Map<String, String> destinatario) {
//
//        String soggettoGiuridico = destinatario.get("soggettoGiuridico");
//        if (soggettoGiuridico.equals("PG")) {
//            WebElement secondPGButton = driver.findElement(By.xpath("//input[@name='recipients[1].recipientType' and @value ='PG']"));
//            secondPGButton.click();
//        } else {
//            throw new IllegalStateException("soggettoGiuridico non è PG");
//        }
//        WebElement ragioneSociale = driver.findElement(By.id("recipients[1].firstName"));
//        ragioneSociale.sendKeys(destinatario.get("ragioneSociale"));
//        WebElement secondCodiceFiscale = driver.findElement(By.id("recipients[1].taxId"));
//        secondCodiceFiscale.sendKeys(destinatario.get("codiceFiscale"));
//        //Check per casi di test con pec di piattaforma mancante (irreperibile o deceduto)
//        if (destinatario.get("pec") != null) {
//            WebElement secondPecField = driver.findElement(By.id("recipients[1].digitalDomicile"));
//            secondPecField.sendKeys(destinatario.get("pec"));
//        }
//        WebElement secondAddress = driver.findElement(By.id("recipients[1].address"));
//        secondAddress.sendKeys(destinatario.get("indirizzo"));
//        WebElement secondNumber = driver.findElement(By.id("recipients[1].houseNumber"));
//        secondNumber.sendKeys(destinatario.get("civico"));
//        WebElement secondMunicipalityDetails = driver.findElement(By.id("recipients[1].municipalityDetails"));
//        secondMunicipalityDetails.sendKeys(destinatario.get("localita"));
//        WebElement secondMunicipality = driver.findElement(By.id("recipients[1].municipality"));
//        secondMunicipality.sendKeys(destinatario.get("comune"));
//        WebElement secondProvince = driver.findElement(By.id("recipients[1].province"));
//        secondProvince.sendKeys(destinatario.get("provincia"));
//        WebElement secondZip = driver.findElement(By.id("recipients[1].zip"));
//        secondZip.sendKeys(destinatario.get("cap"));
//        WebElement secondCountry = driver.findElement(By.id("recipients[1].foreignState"));
//        secondCountry.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        secondCountry.sendKeys(Keys.DELETE);
//        secondCountry.sendKeys(destinatario.get("stato"));
//    }
    public void inserimentoDestinatarioPGAggiuntivo(Map<String, String> destinatario) {
        String soggettoGiuridico = destinatario.get("soggettoGiuridico");
        if (!"PG".equals(soggettoGiuridico)) {
            throw new IllegalStateException("soggettoGiuridico non è PG");
        }

        // Seleziona il tipo PG
        WebElement tipoPG = getWebDriverWait(15)
                .withMessage("Il radio button 'PG' per il secondo destinatario non è visibile")
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@name='recipients[1].recipientType' and @value='PG']")
                ));
        tipoPG.click();

        // Inserimento campi
        inserisciCampo("recipients[1].firstName", destinatario.get("ragioneSociale"));
        inserisciCampo("recipients[1].taxId", destinatario.get("codiceFiscale"));

        // PEC opzionale
        if (destinatario.get("pec") != null) {
            inserisciCampo("recipients[1].digitalDomicile", destinatario.get("pec"));
        }

        inserisciCampo("recipients[1].address", destinatario.get("indirizzo"));
        inserisciCampo("recipients[1].houseNumber", destinatario.get("civico"));
        inserisciCampo("recipients[1].municipalityDetails", destinatario.get("localita"));
        inserisciCampo("recipients[1].municipality", destinatario.get("comune"));
        inserisciCampo("recipients[1].province", destinatario.get("provincia"));
        inserisciCampo("recipients[1].zip", destinatario.get("cap"));

        WebElement country = getWebDriverWait(15)
                .withMessage("Il campo 'Stato' del secondo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[1].foreignState")));
        country.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, destinatario.get("stato"));
    }

    private void inserisciCampo(String id, String value) {
        WebElement campo = getWebDriverWait(15)
                .withMessage("Il campo '" + id + "' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        campo.clear();
        campo.sendKeys(value);
    }


//    public void inserimentoTerzoDestinatarioPF(Map<String, String> destinatario) {
//
//        String soggettoGiuridico = destinatario.get("soggettoGiuridico");
//        if (soggettoGiuridico.equals("PF")) {
//            WebElement secondPGButton = driver.findElement(By.xpath("//input[@name='recipients[2].recipientType' and @value ='PF']"));
//            secondPGButton.click();
//        } else {
//            throw new IllegalStateException("soggettoGiuridico non è PF");
//        }
//        WebElement nome = driver.findElement(By.id("recipients[2].firstName"));
//        nome.sendKeys(destinatario.get("nome"));
//        WebElement cognome = driver.findElement(By.id("recipients[2].lastName"));
//        cognome.sendKeys(destinatario.get("cognome"));
//        WebElement secondCodiceFiscale = driver.findElement(By.id("recipients[2].taxId"));
//        secondCodiceFiscale.sendKeys(destinatario.get("codiceFiscale"));
//        //Check per casi di test con pec di piattaforma mancante (irreperibile o deceduto)
//        if (destinatario.get("pec") != null) {
//            WebElement secondPecField = driver.findElement(By.id("recipients[2].digitalDomicile"));
//            secondPecField.sendKeys(destinatario.get("pec"));
//        }
//        WebElement secondAddress = driver.findElement(By.id("recipients[2].address"));
//        secondAddress.sendKeys(destinatario.get("indirizzo"));
//        WebElement secondNumber = driver.findElement(By.id("recipients[2].houseNumber"));
//        secondNumber.sendKeys(destinatario.get("civico"));
//        WebElement secondMunicipalityDetails = driver.findElement(By.id("recipients[2].municipalityDetails"));
//        secondMunicipalityDetails.sendKeys(destinatario.get("localita"));
//        WebElement secondMunicipality = driver.findElement(By.id("recipients[2].municipality"));
//        secondMunicipality.sendKeys(destinatario.get("comune"));
//        WebElement secondProvince = driver.findElement(By.id("recipients[2].province"));
//        secondProvince.sendKeys(destinatario.get("provincia"));
//        WebElement secondZip = driver.findElement(By.id("recipients[2].zip"));
//        secondZip.sendKeys(destinatario.get("cap"));
//        WebElement secondCountry = driver.findElement(By.id("recipients[2].foreignState"));
//        secondCountry.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        secondCountry.sendKeys(Keys.DELETE);
//        secondCountry.sendKeys(destinatario.get("stato"));
//    }

//    public void inserimentoTerzoDestinatarioPG(Map<String, String> destinatario) {
//
//        String soggettoGiuridico = destinatario.get("soggettoGiuridico");
//        if (soggettoGiuridico.equals("PG")) {
//            WebElement secondPGButton = driver.findElement(By.xpath("//input[@name='recipients[2].recipientType' and @value ='PG']"));
//            secondPGButton.click();
//        } else {
//            throw new IllegalStateException("soggettoGiuridico non è PG");
//        }
//        WebElement ragioneSociale = driver.findElement(By.id("recipients[2].firstName"));
//        ragioneSociale.sendKeys(destinatario.get("ragioneSociale"));
//        WebElement secondCodiceFiscale = driver.findElement(By.id("recipients[2].taxId"));
//        secondCodiceFiscale.sendKeys(destinatario.get("codiceFiscale"));
//        //Check per casi di test con pec di piattaforma mancante (irreperibile o deceduto)
//        if (destinatario.get("pec") != null) {
//            WebElement secondPecField = driver.findElement(By.id("recipients[2].digitalDomicile"));
//            secondPecField.sendKeys(destinatario.get("pec"));
//        }
//        WebElement secondAddress = driver.findElement(By.id("recipients[2].address"));
//        secondAddress.sendKeys(destinatario.get("indirizzo"));
//        WebElement secondNumber = driver.findElement(By.id("recipients[2].houseNumber"));
//        secondNumber.sendKeys(destinatario.get("civico"));
//        WebElement secondMunicipalityDetails = driver.findElement(By.id("recipients[2].municipalityDetails"));
//        secondMunicipalityDetails.sendKeys(destinatario.get("localita"));
//        WebElement secondMunicipality = driver.findElement(By.id("recipients[2].municipality"));
//        secondMunicipality.sendKeys(destinatario.get("comune"));
//        WebElement secondProvince = driver.findElement(By.id("recipients[2].province"));
//        secondProvince.sendKeys(destinatario.get("provincia"));
//        WebElement secondZip = driver.findElement(By.id("recipients[2].zip"));
//        secondZip.sendKeys(destinatario.get("cap"));
//        WebElement secondCountry = driver.findElement(By.id("recipients[2].foreignState"));
//        secondCountry.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        secondCountry.sendKeys(Keys.DELETE);
//        secondCountry.sendKeys(destinatario.get("stato"));
//    }

    public void inserimentoTerzoDestinatarioPG(Map<String, String> destinatario) {
        String soggettoGiuridico = destinatario.get("soggettoGiuridico");
        if (!"PG".equals(soggettoGiuridico)) {
            throw new IllegalStateException("soggettoGiuridico non è PG");
        }

        // Seleziona il tipo PG
        WebElement tipoPG = getWebDriverWait(15)
                .withMessage("Il radio button 'PG' per il terzo destinatario non è visibile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@name='recipients[2].recipientType' and @value='PG']")
                ));
        tipoPG.click();

        // Inserimento campi
        WebElement ragioneSociale = getWebDriverWait(15)
                .withMessage("Il campo 'Ragione Sociale' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].firstName")));
        ragioneSociale.sendKeys(destinatario.get("ragioneSociale"));

        WebElement codiceFiscale = getWebDriverWait(15)
                .withMessage("Il campo 'Codice Fiscale' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].taxId")));
        codiceFiscale.sendKeys(destinatario.get("codiceFiscale"));

        // PEC opzionale
        if (destinatario.get("pec") != null) {
            WebElement pecField = getWebDriverWait(15)
                    .withMessage("Il campo 'PEC' del terzo destinatario non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].digitalDomicile")));
            pecField.sendKeys(destinatario.get("pec"));
        }

        WebElement address = getWebDriverWait(15)
                .withMessage("Il campo 'Indirizzo' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].address")));
        address.sendKeys(destinatario.get("indirizzo"));

        WebElement houseNumber = getWebDriverWait(15)
                .withMessage("Il campo 'Numero civico' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].houseNumber")));
        houseNumber.sendKeys(destinatario.get("civico"));

        WebElement municipalityDetails = getWebDriverWait(15)
                .withMessage("Il campo 'Località' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].municipalityDetails")));
        municipalityDetails.sendKeys(destinatario.get("localita"));

        WebElement municipality = getWebDriverWait(15)
                .withMessage("Il campo 'Comune' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].municipality")));
        municipality.sendKeys(destinatario.get("comune"));

        WebElement province = getWebDriverWait(15)
                .withMessage("Il campo 'Provincia' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].province")));
        province.sendKeys(destinatario.get("provincia"));

        WebElement zip = getWebDriverWait(15)
                .withMessage("Il campo 'CAP' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].zip")));
        zip.sendKeys(destinatario.get("cap"));

        WebElement country = getWebDriverWait(15)
                .withMessage("Il campo 'Stato' del terzo destinatario non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[2].foreignState")));
        country.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, destinatario.get("stato"));
    }

    private void inserimentoInformazioniPreliminariPG(PersoneGiuridiche personeGiuridiche, int i) {
        clickRadioButtonPersonaGiuridica(i + 1);
        inserireInfoMultiDestinatario("//input[contains(@id,'firstName')]", personeGiuridiche.getPersone().get(i).getName());
        inserireInfoMultiDestinatario("//input[contains(@id,'taxId')]", personeGiuridiche.getPersone().get(i).getCodiceFiscale());
    }

    //    private void clickRadioButtonPersonaGiuridica(int posizione) {
//        WebElement radioButtonPgBy = driver.findElement(By.xpath("//input[@name='recipients[" + posizione + "].recipientType' and @value ='PG']"));
//        radioButtonPgBy.click();
//    }
    private void clickRadioButtonPersonaGiuridica(int posizione) {
        WebElement radioButtonPgBy = getWebDriverWait(15)
                .withMessage("Il radio button 'PG' per il destinatario " + posizione + " non è visibile")
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@name='recipients[" + posizione + "].recipientType' and @value='PG']")
                ));
        radioButtonPgBy.click();
    }


    //    public void checkBoxAggiungiDomicilio() {
//        webTool.waitTime(3);
//        checkBoxAggiungiDomicilioDigitale = driver.findElement(By.xpath("//label[@id='recipients[0].digitalDomicile-label']"));
//        getWebDriverWait(10).withMessage("Il bottone chiudi non è cliccabile").until(ExpectedConditions.visibilityOf(checkBoxAggiungiDomicilioDigitale));
//
//    }
    public void checkBoxAggiungiDomicilio() {
        webTool.waitTime(3);
        checkBoxAggiungiDomicilioDigitale = getWebDriverWait(10)
                .withMessage("La checkbox 'Aggiungi Domicilio Digitale' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[@id='recipients[0].digitalDomicile-label']")
                ));
    }


    //    public void insertDomicilioDigitale(String emailPec) {
//        domicilioDigitaleTextField = driver.findElement(By.xpath("//input[@id='recipients[0].digitalDomicile']"));
//        domicilioDigitaleTextField.sendKeys(emailPec);
//    }
    public void insertDomicilioDigitale(String emailPec) {
        domicilioDigitaleTextField = getWebDriverWait(10)
                .withMessage("Il campo 'Domicilio Digitale' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@id='recipients[0].digitalDomicile']")
                ));
        domicilioDigitaleTextField.sendKeys(emailPec);
    }


    //    public void insertDomicilioDigitaleErrato(String emailPec) {
//        if (StringUtils.isEmpty(emailPec)) {
//            throw new IllegalArgumentException("L'emailPec è vuoto");
//        }
//        domicilioDigitaleTextField = driver.findElement(By.xpath("//input[@id='recipients[0].digitalDomicile']"));
//        domicilioDigitaleTextField.sendKeys(emailPec);
//    }
    public void insertDomicilioDigitaleErrato(String emailPec) {
        if (StringUtils.isEmpty(emailPec)) {
            throw new IllegalArgumentException("L'emailPec è vuoto");
        }

        domicilioDigitaleTextField = getWebDriverWait(10)
                .withMessage("Il campo 'Domicilio Digitale' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@id='recipients[0].digitalDomicile']")
                ));

        domicilioDigitaleTextField.sendKeys(emailPec);
    }


    //    public void insertRagioneSociale(String ragioneSociale) {
//        ragioneSocialeTextField = driver.findElement(By.xpath("//input[@id='recipients[0].firstName']"));
//        ragioneSocialeTextField.sendKeys(ragioneSociale);
//    }
    public void insertRagioneSociale(String ragioneSociale) {
        ragioneSocialeTextField = getWebDriverWait(10)
                .withMessage("Il campo 'Ragione Sociale' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@id='recipients[0].firstName']")
                ));
        ragioneSocialeTextField.sendKeys(ragioneSociale);
    }


    //    public void insertPartitaIva(String codiceFiscale) {
//        partitaIvaTextField = driver.findElement(By.id("recipients[0].taxId"));
//        partitaIvaTextField.sendKeys(codiceFiscale);
//    }
    public void insertPartitaIva(String codiceFiscale) {
        partitaIvaTextField = getWebDriverWait(10)
                .withMessage("Il campo 'Partita IVA / Codice Fiscale' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[0].taxId")));
        partitaIvaTextField.sendKeys(codiceFiscale);
    }


    //    public void insertRagioneSociale(String ragioneSociale, int posizione) {
//        ragioneSocialeTextField = driver.findElement(By.xpath("//input[@id='recipients[" + posizione + "].firstName']"));
//        ragioneSocialeTextField.sendKeys(ragioneSociale);
//    }
    public void insertRagioneSociale(String ragioneSociale, int posizione) {
        ragioneSocialeTextField = getWebDriverWait(10)
                .withMessage("Il campo 'Ragione Sociale' del destinatario " + posizione + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@id='recipients[" + posizione + "].firstName']")
                ));
        ragioneSocialeTextField.sendKeys(ragioneSociale);
    }


    //    public void insertPartitaIva(String codiceFiscale, int posizione) {
//        partitaIvaTextField = driver.findElement(By.id("recipients[" + posizione + "].taxId"));
//        partitaIvaTextField.sendKeys(codiceFiscale);
//    }
    public void insertPartitaIva(String codiceFiscale, int posizione) {
        partitaIvaTextField = getWebDriverWait(10)
                .withMessage("Il campo 'Partita IVA / Codice Fiscale' del destinatario " + posizione + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("recipients[" + posizione + "].taxId")
                ));
        partitaIvaTextField.sendKeys(codiceFiscale);
    }


    //    public void clickSuTornaInformazioniPreliminari() {
//        informazioniPreliminariButton = driver.findElement(By.xpath("//button[@data-testid='previous-step']"));
//        informazioniPreliminariButton.click();
//    }
    public void clickSuTornaInformazioniPreliminari() {
        informazioniPreliminariButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Torna a Informazioni Preliminari' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-testid='previous-step']")
                ));
        informazioniPreliminariButton.click();
    }


    //    public void clickRadioButtonPersonaGiuridica() {
//        personaGiuridicaRadioButton = driver.findElement(By.xpath("//input[@value='PG']"));
//        personaGiuridicaRadioButton.click();
//    }
    public void clickRadioButtonPersonaGiuridica() {
        personaGiuridicaRadioButton = getWebDriverWait(10)
                .withMessage("Il radio button 'PG' non è visibile o cliccabile")
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@value='PG']")
                ));
        personaGiuridicaRadioButton.click();
    }


    //    public void clickRadioButtonPersonaGiuridicaPosizionale(int posizione) {
//        personaGiuridicaRadioButton = driver.findElement(By.xpath("//input[@name='recipients[" + posizione + "].recipientType' and @value ='PG']"));
//        personaGiuridicaRadioButton.click();
//    }
    public void clickRadioButtonPersonaGiuridicaPosizionale(int posizione) {
        personaGiuridicaRadioButton = getWebDriverWait(10)
                .withMessage("Il radio button 'PG' per il destinatario " + posizione + " non è visibile o cliccabile")
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@name='recipients[" + posizione + "].recipientType' and @value='PG']")
                ));
        personaGiuridicaRadioButton.click();
    }


    //    public void insertCodiceFiscaleErrato(String codiceFiscale) {
//        logger.info("TA_QA: si inserisci codice fiscale errato");
//        WebElement valoreErratoBy = driver.findElement(By.id("recipients[0].taxId"));
//        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(valoreErratoBy));
//        valoreErratoBy.sendKeys(codiceFiscale);
//    }
    public void insertCodiceFiscaleErrato(String codiceFiscale) {
        logger.info("TA_QA: si inserisci codice fiscale errato");
        WebElement valoreErratoBy = getWebDriverWait(30)
                .withMessage("Il campo 'Codice Fiscale' del destinatario 0 non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipients[0].taxId")));
        valoreErratoBy.sendKeys(codiceFiscale);
    }


    //    public String getCodiceFiscaleError() {
//        logger.info("TA_QA: si legge il messagio di errore del Codice fiscale");
//        WebElement valoreCFErratoBy = driver.findElement(By.id("recipients[0].taxId-helper-text"));
//        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(valoreCFErratoBy));
//        return valoreCFErratoBy.getText();
//    }
    public String getCodiceFiscaleError() {
        logger.info("TA_QA: si legge il messaggio di errore del Codice Fiscale");
        WebElement valoreCFErratoBy = getWebDriverWait(30)
                .withMessage("Il messaggio di errore del Codice Fiscale non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("recipients[0].taxId-helper-text")
                ));
        return valoreCFErratoBy.getText();
    }


    //    public String getDomicilioDigitaleError() {
//        logger.info("TA_QA: si legge il messagio di errore del digital domicile");
//        WebElement valoreErratoBy = driver.findElement(By.id("recipients[0].digitalDomicile-helper-text"));
//        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(valoreErratoBy));
//        return valoreErratoBy.getText();
//    }
    public String getDomicilioDigitaleError() {
        logger.info("TA_QA: si legge il messaggio di errore del digital domicile");
        WebElement valoreErratoBy = getWebDriverWait(30)
                .withMessage("Il messaggio di errore del Domicilio Digitale non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("recipients[0].digitalDomicile-helper-text")
                ));
        return valoreErratoBy.getText();
    }


    //    public boolean verificaNumeroDestinatari() {
//        logger.info("TA_QA: si verifica il numero dei destinatari");
//        rimuoviDestinatarioButtons = driver.findElements(By.id("//button[contains(@data-testid,'DeleteRecipientIcon')]"));
//        return this.rimuoviDestinatarioButtons.isEmpty();
//    }
    public boolean verificaNumeroDestinatari() {
        logger.info("TA_QA: si verifica il numero dei destinatari");

        rimuoviDestinatarioButtons = driver.findElements(By.xpath("//button[contains(@data-testid,'DeleteRecipientIcon')]"));
        return rimuoviDestinatarioButtons.isEmpty();
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
        inserireIndirizzo(datiNotificaMap.get("indirizzo"), 0);
        inserireNumeroCivico(datiNotificaMap.get("numeroCivico"), 0);
        inserireComune(datiNotificaMap.get("comune"), 0);
        inserireProvincia(datiNotificaMap.get("provincia"), 0);
        inserireCodicePostale(datiNotificaMap.get("codicepostale"), 0);
        inserireStato(datiNotificaMap.get("stato"), 0);
        vaiInFondoAllaPagina();
    }

    //    public void selezionarePersonaFisicaMultiDestinatario(int numeroDestinatario) {
//        logger.info("selezione pf su checkbox del destinatario numero: {}", (numeroDestinatario + 1));
//        List<WebElement> personaFisicaCheckBox = driver.findElements(By.xpath("//label[@id='recipient-pf' and @data-testid='recipientType" + numeroDestinatario + "']/span"));
//        js().executeScript("arguments[0].scrollIntoView(true);", personaFisicaCheckBox.get(0));
//        getWebDriverWait(10).withMessage("Checkbox di persona fisica non visibile del destinatario numero " + (numeroDestinatario + 1)).until(ExpectedConditions.visibilityOf(personaFisicaCheckBox.get(0)));
//        personaFisicaCheckBox.get(0).click();
//    }
    public void selezionarePersonaFisicaMultiDestinatario(int numeroDestinatario) {
        logger.info("selezione PF su checkbox del destinatario numero: {}", (numeroDestinatario + 1));

        WebElement personaFisicaCheckBox = getWebDriverWait(10)
                .withMessage("Checkbox di persona fisica non visibile del destinatario numero " + (numeroDestinatario + 1))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[@id='recipient-pf' and @data-testid='recipientType" + numeroDestinatario + "']/span")
                ));

        js().executeScript("arguments[0].scrollIntoView(true);", personaFisicaCheckBox);
        personaFisicaCheckBox.click();
    }


    //    public void inserireNomeMultiDestinatario(int numeroDestinatario, String nomeDestinatario) {
//        logger.info("inserimento nome del destinatario numero: {}", (numeroDestinatario + 1));
//        WebElement nomeDestinatarioTextFieldBy = driver.findElement(By.id("recipients[" + numeroDestinatario + "].firstName"));
//        scrollToElementClickAndInsertText(nomeDestinatarioTextFieldBy, nomeDestinatario);
//    }
    public void inserireNomeMultiDestinatario(int numeroDestinatario, String nomeDestinatario) {
        logger.info("inserimento nome del destinatario numero: {}", (numeroDestinatario + 1));

        WebElement nomeDestinatarioTextFieldBy = getWebDriverWait(10)
                .withMessage("Il campo 'Nome' del destinatario numero " + (numeroDestinatario + 1) + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("recipients[" + numeroDestinatario + "].firstName")
                ));

        scrollToElementClickAndInsertText(nomeDestinatarioTextFieldBy, nomeDestinatario);
    }


    //    public void inserireCognomeMultiDestinatario(int numeroDestinatario, String nomeDestinatario) {
//        logger.info("inserimento cognome del destinatario numero: {}", (numeroDestinatario + 1));
//        WebElement cognomeDestinatarioTextFieldBy = driver.findElement(By.id("recipients[" + numeroDestinatario + "].lastName"));
//        scrollToElementClickAndInsertText(cognomeDestinatarioTextFieldBy, nomeDestinatario);
//    }
    public void inserireCognomeMultiDestinatario(int numeroDestinatario, String cognomeDestinatario) {
        logger.info("inserimento cognome del destinatario numero: {}", (numeroDestinatario + 1));

        WebElement cognomeDestinatarioTextFieldBy = getWebDriverWait(10)
                .withMessage("Il campo 'Cognome' del destinatario numero " + (numeroDestinatario + 1) + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("recipients[" + numeroDestinatario + "].lastName")
                ));

        scrollToElementClickAndInsertText(cognomeDestinatarioTextFieldBy, cognomeDestinatario);
    }


    //    public void inserireCodiceFiscaleMultiDestinatario(int numeroDestinatario, String codiceFiscale) {
//        logger.info("inserimento codice fiscale del destinatario numero: {}", (numeroDestinatario + 1));
//        WebElement codiceFiscaleDestinatarioTextFieldBy = driver.findElement(By.id("recipients[" + numeroDestinatario + "].taxId"));
//        scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextFieldBy, codiceFiscale);
//    }
    public void inserireCodiceFiscaleMultiDestinatario(int numeroDestinatario, String codiceFiscale) {
        logger.info("inserimento codice fiscale del destinatario numero: {}", (numeroDestinatario + 1));

        WebElement codiceFiscaleDestinatarioTextFieldBy = getWebDriverWait(10)
                .withMessage("Il campo 'Codice Fiscale' del destinatario numero " + (numeroDestinatario + 1) + " non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("recipients[" + numeroDestinatario + "].taxId")
                ));

        scrollToElementClickAndInsertText(codiceFiscaleDestinatarioTextFieldBy, codiceFiscale);
    }


    public void clickTornaAlleDeleghe() {
        WebElement generateApiKeyButton = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("courtesy-page-button")));
        generateApiKeyButton.click();
    }

    public void verificaPresenzaSezionePagamenti(int numeroAvvisi) {
        getWebDriverWait(30)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[contains(@class, 'css-kwxqgy')]"), numeroAvvisi)
                );
    }

    public void clickSuEliminaAvvisoPagoPa() {
        WebElement eliminaButton = getWebDriverWait(10)
                .withMessage("Impossibile trovare il bottone Elimina AvvisoPagoPa")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='pagopa-delete-button']")
                ));

        eliminaButton.click();
    }

    public void clickSuEliminaModelloF24() {
        WebElement eliminaButton = getWebDriverWait(10)
                .withMessage("Impossibile trovare il bottone Elimina ModelloF24")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='f24-delete-button']")
                ));

        eliminaButton.click();
    }


    public void verificaPresenzaSezionePagamentiNelMenuACascata(int numeroDiPagamenti) {
        WebElement comboBox = getWebDriverWait(30)
                .withMessage("Impossibile cliccare sul menu a discesa")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[role='combobox']")));
        comboBox.click();
        webTool.waitTime(1);
        getWebDriverWait(30)
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.cssSelector("ul[role='listbox'] > li[role='option']"), numeroDiPagamenti
                ));
    }

    public void verificaPresenzaSezionePagamentiNumeroModuliF(int numeroModuli) {

        getWebDriverWait(10)
                .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("span[data-testid='f24']"), numeroModuli)
                );
    }

    public void verificaDisabilitatoTastoContinua() {
        By bottoneContinua = By.id("step-submit");
        WebElement continuaBtn = getWebDriverWait(10)
                .withMessage("Il bottone 'Continua' non è presente")
                .until(ExpectedConditions.presenceOfElementLocated(bottoneContinua));

        // Verifica che sia disabilitato (non cliccabile)
        Assertions.assertFalse(
                continuaBtn.isEnabled(),
                "Il bottone 'Continua' dovrebbe essere disabilitato, ma risulta cliccabile."
        );
    }

    public void verificaErroriCodiciAvvisoNonValidi(int numeroAttesi) {
        List<WebElement> errori = getWebDriverWait(10)
                .withMessage("Messaggi di errore per i codici avviso non trovati.")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("noticeCode-helper-text")));

        List<WebElement> erroriVisibili = errori.stream()
                .filter(WebElement::isDisplayed)
                .toList();

        Assertions.assertEquals(
                numeroAttesi,
                erroriVisibili.size(),
                "Il numero di messaggi di errore visibili non corrisponde a quello atteso."
        );
    }

    public void verificaErroriCodiceFiscaleEnteNonValidi(int numeroAttesi) {
        List<WebElement> errori = getWebDriverWait(10)
                .withMessage("Messaggi di errore per i codici fiscali ente creditore non trovati.")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("creditorTaxId-helper-text")));

        // Filtra solo quelli visibili
        List<WebElement> erroriVisibili = errori.stream()
                .filter(WebElement::isDisplayed)
                .toList();

        Assertions.assertEquals(
                numeroAttesi,
                erroriVisibili.size(),
                "Il numero di messaggi di errore visibili per i codici fiscali ente creditore non corrisponde a quello atteso."
        );
    }

    //    public boolean verificaAssenzaPopUpErrorePerInviaPosizioneDebitoria() {
//        try {
//            getWebDriverWait(10).withMessage("Alert di posizione debitoria non visualizzato correttamente")
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'alert')]")));
//            logger.info("Testo Alert di posizione debitoria: {}", driver.findElement(By.xpath("//*[contains(@id, 'alert')]")).getText());
//            return true;
//        } catch (TimeoutException e) {
//            return false;
//        }
//    }
    public boolean verificaAssenzaPopUpErrorePerInviaPosizioneDebitoria() {
        try {
            WebElement alertElement = getWebDriverWait(10)
                    .withMessage("Alert di posizione debitoria non visualizzato correttamente")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'alert')]")));

            logger.info("Testo Alert di posizione debitoria: {}", alertElement.getText());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void verificaNumeroCaricamentoFile(int numeroFile) {
        int tentativi = 1;

        for (int i = 0; i < numeroFile; i++) {

            List<WebElement> iconeAllegati = getWebDriverWait(60)
                    .withMessage("Le icone degli allegati non sono state trovate entro il tempo previsto")
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("svg[data-testid='AttachFileIcon']")));

            if (iconeAllegati != null && iconeAllegati.size() == numeroFile) {
                return;
            }
            webTool.waitTime(2);
            tentativi++;

        }

        Assertions.fail("Numero icone allegato diverso da: " + numeroFile + " dopo " + tentativi + " tentativi.");
    }

    public void verificaEsistenzaTabellaNotifiche() {
        getWebDriverWait(60)
                .withMessage("Tabella Notifiche Non Trovata")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("notifications-table")));
    }

    public void selezionaRadionButtonInserimentoManualeSeEsiste(String posizione) {

        //posizione 1 ...n  si vuole aggiungere un destinatario
        try {

            List<WebElement> radioLabels = getWebDriverWait(15)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.cssSelector("label[data-testid='physicalAddressLookupRadio." + posizione + "']")
                    ));

            WebElement manualeInput = null;

            for (WebElement label : radioLabels) {
                WebElement input = label.findElement(By.cssSelector("input[type='radio']"));
                if ("MANUAL".equals(input.getAttribute("value"))) {
                    manualeInput = input;
                    break;
                }
            }

            manualeInput.click();
        } catch (Exception e) {
            // Se il radio button non è trovato o non è cliccabile, non fa nulla
            logger.info("Il radio button in selezionaRadionButtonInserimentoManuale DestinatarioPASection  'Inserimento manuale' non è presente, si passa oltre.");
        }
    }
}
