package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class LeTueDelegheSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LeTueDelegheSection.class);

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}";

    private static final SecureRandom RANDOM = new SecureRandom();


    @FindBy(xpath = "//input[@value='PF']")
    WebElement personaFisicaRadioButton;

    @FindBy(id = "side-item-Notifiche")
    WebElement sideItemNotificheButton;

    @FindBy(id = "nome")
    WebElement inputNome;

    @FindBy(id = "cognome")
    WebElement inputCognome;

    @FindBy(id = "create-button")
    WebElement inviaLaRichiestaButton;

    @FindBy(id = "expirationDate")
    WebElement dataTermineDelegaInput;

    @FindBy(xpath = "//div[contains(@id, 'digit-')]")
    List<WebElement> codiceVerificaList;

    @FindBy(id = "codiceFiscale")
    WebElement codiceFiscaleInput;

    @FindBy(xpath = "//input[@value='entiSelezionati']")
    WebElement soloEntiSelezionatiRadioButton;

    @FindBy(id = "enti")
    WebElement enteElementInput;

    @FindBy(id = "courtesy-page-button")
    WebElement tornaDelegheButton;

    @FindBy(id = "accept-button")
    WebElement accettaButton;

    @FindBy(id = "code-confirm-button")
    WebElement accettaPopUpButton;

    @FindBy(id = "code-cancel-button")
    WebElement indietroPopUpButton;

    @FindBy(id = "Deleghe-page")
    WebElement deleghePageTitle;

    @FindBy(id = "subtitle-page")
    WebElement deleghePageSubtitle;

    @FindBy(id = "add-delegation-button")
    WebElement aggiungiDelegaButton;

    @FindBy(xpath = "//span[contains(text(),'Nome')]")
    WebElement nomeDelegaField;

    @FindBy(xpath = "//th[contains(text(),'Inizio delega')]")
    WebElement inizioDelegaField;

    @FindBy(xpath = "//span[contains(text(),'Fine delega')]")
    WebElement fineDelegaField;

    @FindBy(xpath = "//th[contains(text(),'Permessi')]")
    WebElement permessiDelegaField;

    // find all row with data-testid="delegatorsTable.body.row"
    @FindBy(xpath = "//tr[@data-testid='delegatorsTable.body.row']")
    List<WebElement> delegatorsTableRows;


    private WebTool webTool;

    public LeTueDelegheSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitNuovaDelegaSection() {
        getWebDriverWait(10)
                .withMessage("Il titolo della pagina non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Aggiungi una delega-page")));
        inputNome = getWebDriverWait(10)
                .withMessage("L'input nome non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));
        codiceFiscaleInput = getWebDriverWait(10)
                .withMessage("L'input codice fiscale non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("codiceFiscale")));
        inputCognome = getWebDriverWait(10)
                .withMessage("L'input cognome non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cognome")));
        logger.info("Le tue deleghe page caricata");
    }

    public void selectPersonaFisicaRadioButton() {
        logger.info("Click radio button persona fisica");
        personaFisicaRadioButton = getWebDriverWait(10)
                .withMessage("Il radio button Persona Fisica non è visibile")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='PF']")));
        personaFisicaRadioButton.click();
    }

    public void insertNomeCognome(String nome, String cognome) {
        logger.info("Inserimento nome");
        inputNome = getWebDriverWait(10)
                .withMessage("L'input Nome non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));
        inputNome.sendKeys(nome);

        logger.info("Inserimento cognome");
        inputCognome = getWebDriverWait(10)
                .withMessage("L'input Cognome non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cognome")));
        inputCognome.sendKeys(cognome);
    }

    public void clickSulBottoneInviaRichiesta() {
        inviaLaRichiestaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Invia richiesta' non è cliccabile o non trovato")
                .until(ExpectedConditions.elementToBeClickable(By.id("create-button")));
        logger.info("Click su Invia richiesta");
        inviaLaRichiestaButton.click();
        WebElement tornaDelegheButton = getWebDriverWait(40)
                .withMessage("Il bottone 'Torna deleghe' non è cliccabile o non trovato")
                .until(ExpectedConditions.elementToBeClickable(By.id("courtesy-page-button")));
        tornaDelegheButton.click();
    }

    public boolean verificareCheLaDataSiaCorretta() {
        dataTermineDelegaInput = getWebDriverWait(10)
                .withMessage("L'input 'expirationDate' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("expirationDate")));
        String dataDaVerificare = dataTermineDelegaInput.getAttribute("value");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInserita = LocalDate.parse(dataDaVerificare, formatter);
        LocalDate dataCorretta = LocalDate.now().plusDays(1);
        return dataCorretta.equals(dataInserita);
    }

    public String salvataggioCodiceVerifica() {
        codiceVerificaList = getWebDriverWait(10)
                .withMessage("Gli elementi del codice verifica non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@id, 'digit-')]")
                ));
        return codiceVerificaList.stream()
                .map(WebElement::getText)
                .collect(Collectors.joining());
    }

    public String getNome() {
        WebElement nomeInput = getWebDriverWait(10)
                .withMessage("Errore: il campo Nome non è visibile entro 10 secondi.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));
        return nomeInput.getAttribute("value");
    }

    public String getCognome() {
        WebElement cognomeInput = getWebDriverWait(10)
                .withMessage("Errore: il campo Cognome non è visibile entro 10 secondi.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cognome")));
        return cognomeInput.getAttribute("value");
    }


    public void inserireCF(String cf) {
        WebElement codiceFiscale = getWebDriverWait(10)
                .withMessage("Il campo Codice Fiscale non è visibile o non è pronto per l'interazione")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("codiceFiscale")));
        codiceFiscale.clear();
        codiceFiscale.sendKeys(cf);

    }

    public void selectSoloEntiSelezionati() {
        logger.info("Click checkbox solo enti selezionati");
        soloEntiSelezionatiRadioButton = getWebDriverWait(10)
                .withMessage("Il radio button 'Solo Enti Selezionati' non è visibile o cliccabile")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='entiSelezionati']")));
        soloEntiSelezionatiRadioButton.click();
    }

    public void selezionaUnEnte(String ente) {
        enteElementInput = getWebDriverWait(10)
                .withMessage("Input ente non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("enti")));
        logger.info("Inserimento dati ente");
        enteElementInput.click();
        enteElementInput.sendKeys(ente);
        WebElement menuEntiOptionBy = getWebDriverWait(10)
                .withMessage("Il menu della selezione ente non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation']")));
        js().executeScript("arguments[0].click()", menuEntiOptionBy);
        WebElement comuneOptionBy = getWebDriverWait(10)
                .withMessage("L'ente " + ente + " non è cliccabile o non è presente")
                .until(ExpectedConditions.elementToBeClickable(By.id("enti-option-0")));
        js().executeScript("arguments[0].click()", comuneOptionBy);
    }


    public void clickInviaRichiesta() {
        WebElement inviaRichiestaButton = getWebDriverWait(30)
                .withMessage("Il bottone 'Invia Richiesta' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("create-button")));
        inviaRichiestaButton.click();
    }

    public String messaggioDiErrore() {
        WebElement errorMessageBy = driver.findElement(By.xpath("//div[contains(@class,'MuiAlert-message')]/div"));
        getWebDriverWait(30).withMessage("l'alert message non è visibile").until(ExpectedConditions.visibilityOf(errorMessageBy));
        logger.info("Messaggio di errore trovato");
        return errorMessageBy.getText();
    }

    public void messaggioDiErroreDelegaPresente() {
//        webTool.waitTime(5);
        //TODO:Andrea
        webTool.waitTime(1);
        getWebDriverWait(10)
                .withMessage("Il messaggio di errore 'Delega già presente' NON viene visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Delega già presente')]")
                ));
        logger.info("Il messaggio di errore viene visualizzato");
    }


    public void clickOpzioneAccetta() {
        try {
            WebElement acceptButton = getWebDriverWait(40)
                    .withMessage("Il bottone 'Accetta' non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("accept-button")));
            acceptButton.click();
            logger.info("Bottone 'Accetta' cliccato con successo.");
        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("Bottone 'Accetta' per delega a tuo carico non cliccato con errore: " + e.getMessage());
        }
    }

    public void clickOpzioneAccettaDelegaATuoCaricoDa(String nome) {
        try {
            WebElement acceptButton = getWebDriverWait(40)
                    .withMessage("Il bottone 'Accetta' non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//td//p[contains(text(), '" + nome + "')]//..//..//button[@id='accept-button']")));

            acceptButton.click();
            logger.info("Bottone 'Accetta' cliccato con successo.");
        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("Bottone 'Accetta' per delega a tuo carico non cliccato con errore: " + e.getMessage());
        }
    }

    public void waitPopUpLoad() {
        // Attende fino a 15 secondi che il titolo del pop-up sia visibile
        getWebDriverWait(15)
                .withMessage("Il pop-up per accettare la delega NON visualizzato correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='dialog-title']")));
        logger.info("Il pop-up per accettare la delega visualizzato correttamente");
    }

    public void inserireCodiceDelega(String codiceDelega) {
        String[] codiciDelega = codiceDelega.split("");
        for (int i = 0; i < 5; i++) {
            WebElement codiceDelegaInput = driver.findElement(By.xpath("//div[@data-testid='dialog-content']//input"));
            codiceDelegaInput.sendKeys(codiciDelega[i]);
        }
    }

    public void clickNotificheDelegatePF() {
        sideItemNotificheButton = getWebDriverWait(10)
                .withMessage("Il pulsante 'Notifiche' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("side-item-Notifiche")));
        sideItemNotificheButton.click();
        WebElement notificheDelegateButton = getWebDriverWait(10)
                .withMessage("Il pulsante 'Notifiche delegate' non è cliccabile o non trovato")
                .until(ExpectedConditions.elementToBeClickable(By.id("side-item-Gaio Giulio Cesare")));
        js().executeScript("arguments[0].click()", notificheDelegateButton);
        logger.info("Si clicca correttamente sulla voce notifiche delegate");
    }

    public String getTextCodiceSbagliato() {
        WebElement errorMessageBy = getWebDriverWait(30)
                .withMessage("Il messaggio di errore per il codice sbagliato non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("codeModalErrorTitle")));
        return errorMessageBy.getText();
    }

    public void clickAccettaButton() {
        accettaPopUpButton = getWebDriverWait(10)
                .withMessage("Il pulsante 'Accetta' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("code-confirm-button")));
        accettaPopUpButton.click();
    }

    public void clickIndietroButton() {
        WebElement indietroButton = getWebDriverWait(5)
                .withMessage("Il bottone 'Indietro' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("code-cancel-button")));
        indietroButton.click();
    }

    public void controlloStatoAttiva(String nome, String cognome) {
        String xpathStatoAttiva = "//table[@id='notifications-table']//td[//p[contains(text(),'" + nome + " " + cognome + "')]]/following-sibling::td//div[@id='chip-status-success']";
        getWebDriverWait(30)
                .withMessage("La delega di " + nome + " " + cognome + " NON ha lo stato Attiva")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathStatoAttiva)));
        logger.info("La delega ha lo stato Attiva");
    }

    public void controlloEsistenzaDelega(String nome, String cognome) {
        delegatorsTableRows = getWebDriverWait(10)
                .withMessage("Le righe della tabella delega non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//tr[@data-testid='delegatorsTable.body.row']")
                ));
        WebElement delega = delegatorsTableRows.stream()
                .filter(row -> row.getText().contains(nome + " " + cognome))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        "La delega di " + nome + " " + cognome + " non è presente"
                ));
        getWebDriverWait(5)
                .withMessage("La riga della delega di " + nome + " " + cognome + " non è visibile")
                .until(ExpectedConditions.visibilityOf(delega));
    }

    public boolean siVisualizzaIlTitolo() {
        deleghePageTitle = getWebDriverWait(10)
                .withMessage("Il titolo della pagina Deleghe non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Deleghe-page")));
        logger.info("Check visualizzazione titolo pagina Deleghe");
        return deleghePageTitle.isDisplayed();
    }

    public boolean siVisualizzaIlSottotitolo() {
        deleghePageSubtitle = getWebDriverWait(10)
                .withMessage("Il sottotitolo della pagina Deleghe non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("subtitle-page")));

        logger.info("Check visualizzazione sottotitolo pagina Deleghe");
        return deleghePageSubtitle.isDisplayed();
    }

    public boolean siVisualizzaIlBottoneAggiungiUnaDelega() {
        aggiungiDelegaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Aggiungi una Delega' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("add-delegation-button")));
        logger.info("Check visualizzazione pulsante 'Aggiungi una delega'");
        return aggiungiDelegaButton.isDisplayed();
    }

    public boolean siVisualizzaIlNomeDelegato() {
        nomeDelegaField = getWebDriverWait(10)
                .withMessage("Il nome delegato non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Nome')]")));
        logger.info("Check visualizzazione nome delega");
        return nomeDelegaField.isDisplayed();
    }

    public boolean siVisualizzaDataInizioDelega() {
        inizioDelegaField = getWebDriverWait(30)
                .withMessage("Il campo 'Inizio delega' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'Inizio delega')]")));
        logger.info("Check visualizzazione inizio data delega");
        return inizioDelegaField.isDisplayed();
    }

    public boolean siVisualizzaDataFinoDelega() {
        fineDelegaField = getWebDriverWait(30)
                .withMessage("Il campo 'Fine delega' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Fine delega')]")));
        logger.info("Check visualizzazione data fine delega");
        return fineDelegaField.isDisplayed();
    }

    public boolean siVisualizzaPermessiDelega() {
        permessiDelegaField = getWebDriverWait(10)
                .withMessage("I permessi della delega non sono visualizzati correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'Permessi')]")));
        logger.info("Check visualizzazione permessi delega");
        return permessiDelegaField.isDisplayed();
    }


    public boolean controlloPresenzaBottoneAccetta() {
        try {
            // Aspetta fino a 30 secondi che il pulsante "Accetta" sia visibile
            getWebDriverWait(30)
                    .withMessage("Il pulsante 'Accetta' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("accept-button")));
            logger.info("Si visualizza il bottone 'Accetta'");
            return true;
        } catch (TimeoutException e) {
            logger.info("Non si visualizza il bottone 'Accetta'");
            return false;
        }
    }

    public boolean verificaEsistenzaErroreCodiceSbagliato() {
        try {
            getWebDriverWait(20)
                    .withMessage("Errore codice sbagliato non visibile entro il timeout")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("codeModalErrorTitle")));
            logger.info("Errore codice sbagliato trovato");
            return true;
        } catch (TimeoutException e) {
            logger.info("Errore codice sbagliato non trovato");
            return false;
        }
    }

    public void checkIndicatoreNumerico() {
//        webTool.waitTime(10);
        //TODO:Andrea
        webTool.waitTime(1);
        getWebDriverWait(15)
                .withMessage("L'indicatore numerico di notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='notifications']")));
        logger.info("Indicatore numerico di notifica visualizzato correttamente");
    }

    public void inserisciCredenzialiDelegato(Map<String, String> destinatario) {
        logger.info("Inserire le credenziali del delegato");
        logger.info("Selezione PF su checkbox (se applicabile)");
        logger.info("Inserisco Nome, Cognome e Codice Fiscale");
        // Inserimento Nome
        WebElement nomeField = getWebDriverWait(20)
                .withMessage("Il campo 'Nome' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));
        nomeField.clear();
        nomeField.sendKeys(destinatario.get("nome"));
        // Inserimento Cognome
        WebElement cognomeField = getWebDriverWait(20)
                .withMessage("Il campo 'Cognome' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cognome")));
        cognomeField.clear();
        cognomeField.sendKeys(destinatario.get("cognome"));
        // Inserimento Codice Fiscale
        WebElement codiceFiscaleField = getWebDriverWait(20)
                .withMessage("Il campo 'Codice Fiscale' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("codiceFiscale")));
        codiceFiscaleField.clear();
        codiceFiscaleField.sendKeys(destinatario.get("codiceFiscale"));
    }

    public static String generaCodificato(String nome, int lengthPrefix, int lengthSuffix) {
        return randomString(lengthPrefix) + nome + randomString(lengthSuffix);
    }

    private static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

}


