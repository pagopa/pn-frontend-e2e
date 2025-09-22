package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;


public class NotifichePFPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(NotifichePFPage.class);


    @FindBy(id = "Le tue notifiche-page")
    private WebElement titleLabel;

    @FindBy(id = "notifications-table")
    private WebElement tableNotifiche;

    @FindBy(id = "iunMatch")
    private WebElement codiceIunTextField;

    @FindBy(id = "filter-notifications-button")
    private WebElement filtraButton;

    @FindBy(id = "startDate")
    private WebElement dataInizioField;

    @FindBy(id = "endDate")
    private WebElement dataFineField;

    @FindBy(id = "side-item-Notifiche")
    private WebElement notificheDeButton;

    @FindBy(id = "side-item-Gaio Giulio Cesare")
    private WebElement nomeDeleganteButton;

    @FindBy(id = "next")
    private WebElement paginaSuccessivaButton;

    @FindBy(id = "page3")
    private WebElement numeroPaginaTreButton;

    @FindBy(id = "page2")
    private WebElement paginaSeconda;

    @FindBy(id = "page1")
    private WebElement paginaPrima;

    @FindBy(id = "rows-per-page")
    private WebElement numeroPagineButton;

    @FindBy(id = "iunMatch-helper-text")
    private WebElement notValidIunMessage;

    @FindBy(css = "[data-testid='cancelButton']")
    private WebElement rimuoviFiltriButton;

    @FindBy(xpath = "//button[@data-testid='download-f24-button']")
    private WebElement buttonDownloadF24;

    @FindBy(xpath = "//button[@data-testid='download-pagoPA-notice-button']")
    private WebElement buttonDownloadAvvisoPagoPA;


    private WebTool webTool;

    public NotifichePFPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadNotificheDEPage() {
        By[] titoliMultilingua = {
                By.id("Le tue notifiche-page"),
                By.id("Your notifications-page"),
                By.id("Vos notifications-page"),
                By.id("Deine Zustellungen-page"),
                By.id("Vaša obvestila-page")
        };
        // Costruisco la condizione "OR" dinamicamente
        ExpectedCondition<?>[] conditions = Arrays.stream(titoliMultilingua)
                .map(ExpectedConditions::visibilityOfElementLocated)
                .toArray(ExpectedCondition[]::new);
        // Attendo il titolo della pagina
        getWebDriverWait(60)
                .withMessage("Notifiche DE Page non caricata correttamente: il titolo non è visibile")
                .until(ExpectedConditions.or(conditions));
        // Attendo la tabella delle notifiche
        getWebDriverWait(80)
                .withMessage("Notifiche DE Page non caricata correttamente: la tabella delle notifiche non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("notifications-table")));
        logger.info("Notifiche DE Page caricata");
    }

    public boolean verificaPresenzaCodiceIunTextField() {
        try {
            codiceIunTextField = getWebDriverWait(30)
                    .withMessage("Il campo codice iun non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("iunMatch")));
            return codiceIunTextField.isDisplayed();
        } catch (TimeoutException e) {
            logger.error("Text field codice iun non visualizzato entro 30 secondi");
            return false;
        }
    }

    public void waitESelectDelegheButton() {
        By delegheButtonLocator = By.xpath("//div[contains(@data-testid,'menu-item(deleghe)')]");

        WebElement buttonDelegheWebElement = getWebDriverWait(10)
                .withMessage("Non viene visualizzato il bottone deleghe nella sidebar")
                .until(ExpectedConditions.visibilityOfElementLocated(delegheButtonLocator));
        buttonDelegheWebElement.click();
        logger.info("Cliccato correttamente su deleghe button");
    }

    public void selectFiltraButton() {
        webTool.waitTime(5); // eventuale attesa fissa, se davvero necessaria

        By filtraButtonLocator = By.id("filter-notifications-button");
        filtraButton = getWebDriverWait(40)
                .withMessage("Il bottone filtra sulla pagina notifiche non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(filtraButtonLocator));

        filtraButton.click();
        logger.info("Bottone filtra cliccato correttamente");
    }


    public String controlloDateInserite(String data) {
        String[] date = data.split("-");
        return date[2] + "/" + date[1] + "/" + date[0];
    }

    public void inserimentoArcoTemporale(String dataDA, String dataA) {
        By startDateLocator = By.id("startDate");
        By endDateLocator = By.id("endDate");
        // Attendo che il campo data inizio sia visibile
        dataInizioField = getWebDriverWait(20)
                .withMessage("Il campo di inserimento data DA non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(startDateLocator));
        // Attendo che il campo data fine sia visibile
        dataFineField = getWebDriverWait(20)
                .withMessage("Il campo di inserimento data A non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(endDateLocator));
        // Inserisco data DA
        dataInizioField.click();
        dataInizioField.clear();
        dataInizioField.sendKeys(dataDA);
        getWebDriverWait(3)
                .withMessage("Il valore del campo inserimento data DA non corrisponde")
                .until(ExpectedConditions.attributeToBe(dataInizioField, "value", dataDA));
        // Inserisco data A
        dataFineField.click();
        dataFineField.clear();
        dataFineField.sendKeys(dataA);
        getWebDriverWait(3)
                .withMessage("Il valore del campo inserimento data A non corrisponde")
                .until(ExpectedConditions.attributeToBe(dataFineField, "value", dataA));

        logger.info("Arco temporale inserito correttamente: DA " + dataDA + " A " + dataA);
    }

    public boolean getListData() {
        try {
            List<WebElement> elements = getWebDriverWait(60)
                    .withMessage("La colonna Data nella pagina notifiche non è visibile")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[contains(@class, 'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1wrlhv9')]")));
            return !elements.isEmpty();
        } catch (StaleElementReferenceException e) {
            return getListData(); // Richiama il metodo per riacquisire gli elementi
        }
    }

    public void clickNotificheButton() {
        By notificheButtonLocator = By.id("side-item-Notifiche");
        notificheDeButton = getWebDriverWait(40)
                .withMessage("Notifiche menu button non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(notificheButtonLocator));
        js().executeScript("arguments[0].click()", notificheDeButton);
    }

    public void clickLeTueNotificheButton() {
        By leTueNotificheLocator = By.id("side-item-Le tue notifiche");
        WebElement leTueNotificheButton = getWebDriverWait(30)
                .withMessage("Il bottone le tue notifiche non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(leTueNotificheLocator));
        js().executeScript("arguments[0].click()", leTueNotificheButton);
        logger.info("Si clicca sul bottone le tue notifiche");
    }

    public void siVisualizzaPaginaNotifichePersonaFisica() {
        getWebDriverWait(30)
                .withMessage("Il titolo della pagina delle notifiche non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Le tue notifiche-page")));
        getWebDriverWait(30)
                .withMessage("Il bottone 'i tuoi recapiti' della sidebar non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='menu-item(i tuoi recapiti)']")));
        getWebDriverWait(30)
                .withMessage("La sezione filtri per le notifiche non è visualizzabile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='filter-form']")));
        getWebDriverWait(30)
                .withMessage("La tabella delle notifiche non viene visualizzata correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("notificationsTable.body.row")));
        logger.info("Il titolo della pagina notifiche persona fisica si visualizza correttamente");
    }

    public void siVisualizzanoFiltriRicerca() {
        getWebDriverWait(30)
                .withMessage("Il filtro Codice IUN non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("iunMatch")));
        getWebDriverWait(30)
                .withMessage("Il filtro Data Da non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
        getWebDriverWait(30)
                .withMessage("Il filtro Data A non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
        logger.info("Si visualizzano correttamente i filtri ricerca");
    }

    public void siVisualizzaElencoNotifiche() {
        getWebDriverWait(30)
                .withMessage("Le righe della tabella notifiche non sono visibili")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@id,'notificationsTable.body.row')]")));
        getWebDriverWait(30)
                .withMessage("Il nome della colonna Data non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'Data')]")));
        getWebDriverWait(30)
                .withMessage("Il nome della colonna Oggetto non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'Oggetto')]")));
        getWebDriverWait(30)
                .withMessage("Il nome della colonna Mittente non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'Mittente')]")));
        getWebDriverWait(30)
                .withMessage("Il nome della colonna Codice IUN non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'Codice IUN')]")));
        getWebDriverWait(30)
                .withMessage("Il nome della colonna Stato non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'Stato')]")));
        logger.info("Si visualizza correttamente l'elenco delle notifiche");
    }

    public void clickNomeDelegante() {
        By nomeDeleganteLocator = By.id("side-item-Gaio Giulio Cesare");
        nomeDeleganteButton = getWebDriverWait(30)
                .withMessage("Il nome delegante non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(nomeDeleganteLocator));
        nomeDeleganteButton.click();
        logger.info("Cliccato sul nome delegante correttamente");
    }

    public int siVisualizzaNotifichePresenti() {
        By notificheRowLocator = By.id("notificationsTable.body.row");
        List<WebElement> notificheRows = getWebDriverWait(30)
                .withMessage("Nessuna notifica presente nella tabella notifiche")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(notificheRowLocator));
        return notificheRows.size();
    }

    public List<WebElement> getDateNotifiche() {
        return getWebDriverWait(30)
                .withMessage("La data della notifica non è visibile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//tr/td[2]")));
    }

    public boolean controllaNotifiche(List<WebElement> dateNotifiche) {
        for (int i = 0; i < dateNotifiche.size() - 1; i++) {
            LocalDate data1 = parseDate(dateNotifiche.get(i).getText());
            LocalDate data2 = parseDate(dateNotifiche.get(i + 1).getText());

            if (data1.isBefore(data2)) {
                return false;
            }
        }
        return true;
    }

    private LocalDate parseDate(String dateString) {
        if (dateString.equals("Oggi")) {
            return LocalDate.now();
        }
        try {
            String[] dateParts = dateString.split("/");
            if (dateParts.length == 3) {
                return LocalDate.parse(dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0]);
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            // Gestione dell'errore di parsing della data
            Assertions.fail("Errore nel parsing della data: " + dateString);
        }
        throw new IllegalArgumentException("Formato della data non valido: " + dateString);
    }

    public void clickPaginaSuccessiva() {
        By nextButtonLocator = By.id("next");
        paginaSuccessivaButton = getWebDriverWait(30)
                .withMessage("Il bottone pagina successiva non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
        paginaSuccessivaButton.click();
        logger.info("Cliccata pagina successiva");
    }


    public void waitLoadPaginaDifferente() {
        getWebDriverWait(30).withMessage("La prima pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("page1")));
        logger.info("Si visualizza una pagina differente dalla precedente");
    }

    public void waitLoadSecondaPagina() {
        By paginaSecondaLocator = By.id("page2");

        WebElement paginaSeconda = getWebDriverWait(10)
                .withMessage("La seconda pagina non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(paginaSecondaLocator));

        String isPageSelected = paginaSeconda.getAttribute("aria-current");

        if ("true".equalsIgnoreCase(isPageSelected)) {
            logger.info("Si visualizza una pagina differente dalla precedente");
        } else {
            Assertions.fail("Non si visualizza una pagina differente dalla precedente");
        }
    }

    public void waitLoadPagina(int pageNumber) {
        By paginaLocator = By.id("page" + pageNumber);
        getWebDriverWait(30)
                .withMessage("La pagina " + pageNumber + " non è stata selezionata correttamente")
                .until(driver -> {
                    WebElement pagina = driver.findElement(paginaLocator);
                    return "true".equalsIgnoreCase(pagina.getAttribute("aria-current"));
                });
        logger.info("Si visualizza correttamente la pagina " + pageNumber);
    }


    public void siSceglieUnaPaginaDiversaConNumeroESiFiltra(String iun) {

        WebElement numeroPaginaTreButton = getWebDriverWait(30)
                .withMessage("La terza pagina delle notifiche non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("page3")));

        js().executeScript("arguments[0].click()", numeroPaginaTreButton);

        WebElement codiceIunTextField = getWebDriverWait(30)
                .withMessage("Inpossibile trovare iunMatch")
                .until(ExpectedConditions.elementToBeClickable(By.id("iunMatch")));

        codiceIunTextField.click();
        codiceIunTextField.clear();
        codiceIunTextField.sendKeys(iun);

        clickFiltraButton();

        clickRimuoviFiltriButton();
    }

    public void modificaNumeroNotifichePagina() {
        waitLoadPage();
        By rowsPerPageLocator = By.id("rows-per-page");
        numeroPagineButton = getWebDriverWait(30)
                .withMessage("Il menu per cambiare numero di pagine non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(rowsPerPageLocator));
        numeroPagineButton.click();
        logger.info("Si clicca sul menu per cambiare numero di pagine visualizzate");
    }

    public void numeroDiversoPagine() {
        By pageSize20Locator = By.id("pageSize-20");
        WebElement numeroDiversoPagineBy = getWebDriverWait(30)
                .withMessage("Non viene cambiato il numero di pagine a 20")
                .until(ExpectedConditions.visibilityOfElementLocated(pageSize20Locator));
        numeroDiversoPagineBy.click();
        logger.info("Cambiato il numero di pagine visualizzate a 20");
    }

    public int conteggioNotifiche() {
        By notificheRowLocator = By.xpath("//tr[@data-testid='notificationsTable.body.row']");
        List<WebElement> righeNotifiche = getWebDriverWait(30)
                .withMessage("Le notifiche non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(notificheRowLocator));
        return righeNotifiche.size();
    }

    public void selezionaNotifica() {
        webTool.waitTime(2); // attesa fissa, se necessaria
        By primaNotificaLocator = By.xpath("(//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')])[1]");
        WebElement primaNotifica = getWebDriverWait(30)
                .withMessage("Non viene visualizzata la prima riga della tabella delle notifiche")
                .until(ExpectedConditions.elementToBeClickable(primaNotificaLocator));
        primaNotifica.click();
        logger.info("Prima notifica selezionata correttamente");
    }

    public void waitLoadNotificheDEPageDelegante(String nome, String cognome) {
        // Costruisco l'id dinamico del titolo
        String titleId = "Le notifiche di " + nome + " " + cognome + "-page";
        By titleLocator = By.id(titleId);
        titleLabel = getWebDriverWait(40)
                .withMessage("Il titolo della pagina notifiche delegante non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
        By tableLocator = By.id("notifications-table");
        getWebDriverWait(40)
                .withMessage("La tabella notifiche nella pagina notifiche delegante non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
        logger.info("Notifiche DE Page caricata");
    }


    public void clickFiltraButton() {
        WebElement filtraButton = getWebDriverWait(30)
                .withMessage("Il bottone filtra nella pagina ricerca Notifiche PF non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("filter-notifications-button")));

        filtraButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        By errorMessageLocator = By.id("iunMatch-helper-text");
        WebElement errorMessage = getWebDriverWait(30)
                .withMessage("Il messaggio di errore non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMessage.isDisplayed();
    }

    public boolean isTextBoxInvalid() {
        By codiceIunLocator = By.id("iunMatch");
        codiceIunTextField = getWebDriverWait(30)
                .withMessage("L'input codice IUN non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(codiceIunLocator));
        String ariaInvalid = codiceIunTextField.getAttribute("aria-invalid");
        return "true".equalsIgnoreCase(ariaInvalid);
    }


    public void clickRimuoviFiltriButton() {
        WebElement rimuoviFiltriButton = getWebDriverWait(35)
                .withMessage("Il bottone rimuovi filtri nella pagina ricerca Notifiche PG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='cancelButton']")));

        rimuoviFiltriButton.click();

    }

    public void firstPageDisplayed() {
        By paginaPrimaLocator = By.id("page1");
        getWebDriverWait(30)
                .withMessage("La prima pagina non è visualizzata correttamente")
                .until(driver -> {
                    WebElement pagina = driver.findElement(paginaPrimaLocator);
                    return "true".equalsIgnoreCase(pagina.getAttribute("aria-current"));
                });
        paginaPrima = driver.findElement(paginaPrimaLocator);
        Assertions.assertTrue("true".equalsIgnoreCase(paginaPrima.getAttribute("aria-current")));
        logger.info("Si visualizza prima pagina");
    }

    public void clickScaricaF24Button() {
        webTool.waitTime(5); // attesa fissa se necessaria
        By downloadF24Locator = By.xpath("//button[@data-testid='download-f24-button']");
        buttonDownloadF24 = getWebDriverWait(20)
                .withMessage("Il bottone per scaricare l'F24 non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(downloadF24Locator));
        getWebDriverWait(20)
                .withMessage("Il bottone per scaricare l'F24 non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(downloadF24Locator));
        scrollToElementAndClick(buttonDownloadF24);
        logger.info("Click sul bottone per scaricare l'F24 eseguito correttamente");
    }

    public void checkFileF24IsOpen() {
        getWebDriverWait(10).withMessage("Il file del modello F24 non è stato aperto correttamente").until(ExpectedConditions.urlContains(".pdf"));
    }

    public void checkAvvisoPagoPaIsOpen() {
        getWebDriverWait(10).withMessage("Il file dell'avviso PagoPa non è stato aperto correttamente").until(ExpectedConditions.urlContains(".pdf"));
    }

    public void clickScaricaAvvisoPagoPAButton() {
        webTool.waitTime(5); // attesa fissa se necessaria
        By avvisoPagoPALocator = By.xpath("//button[@data-testid='download-pagoPA-notice-button']");
        buttonDownloadAvvisoPagoPA = getWebDriverWait(10)
                .withMessage("Il bottone per scaricare l'avviso PagoPA non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(avvisoPagoPALocator));
        getWebDriverWait(10)
                .withMessage("Il bottone per scaricare l'avviso PagoPA non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(avvisoPagoPALocator));
        scrollToElementAndClick(buttonDownloadAvvisoPagoPA);
        logger.info("Click sul bottone per scaricare l'avviso PagoPA eseguito correttamente");
    }

    public void verificaPresenzaCodiciAvvisoEF24(int numeroAttesoCodiciAvviso, int numeroAttesoF24) {
        // Verifica codici avviso PagoPA
        List<WebElement> codiciAvviso = getWebDriverWait(15)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[data-testid='pagopa-item']")));

        for (int i = 0; i < codiciAvviso.size(); i++) {
            logger.info("codiceAvviso_{}: {}", i + 1, codiciAvviso.get(i).getText());
        }
        Assertions.assertEquals(numeroAttesoCodiciAvviso, codiciAvviso.size(),
                "Numero errato di codici avviso PagoPA, attesi: " + numeroAttesoCodiciAvviso);
        // Verifica modelli F24
        List<WebElement> modelliF24 = getWebDriverWait(15)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[data-testid='download-f24-button']")));
        Assertions.assertEquals(numeroAttesoF24, modelliF24.size(),
                "Numero errato di modelli F24, attesi: " + numeroAttesoF24);
    }

    public void verificaSezionePagamenti() {
        List<WebElement> elements = getWebDriverWait(15)
                .withMessage("Impossibile Verificare la Sezione Pagamenti")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='paymentInfoBox']"))
                );

        Assertions.assertFalse(elements.isEmpty(), "La sezione Pagamenti non è presente sulla pagina!");
    }

    public void verificaTesto(String testoAtteso) {

        try {
            WebElement element = getWebDriverWait(10).until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("p[data-testid='notification-payment-recipient-subtitle']")
                    )
            );

            String actualText = element.getText();
            String processedActualText = actualText.replaceAll("\\?", "");

            String normalizedActualText = Normalizer.normalize(processedActualText, Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .replaceAll("'", "")
                    .replaceAll("\\s+", " ");

            String normalizedTestoAtteso = Normalizer.normalize(testoAtteso, Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .replaceAll("'", "")
                    .replaceAll("\\s+", " ");
            Assertions.assertTrue(normalizedActualText.contains(normalizedTestoAtteso),
                    "Il testo trovato '" + normalizedActualText + "' non contiene la stringa attesa: '" + normalizedTestoAtteso + "'");

        } catch (Exception e) {
            Assertions.fail("Errore durante la verifica del testo dell'elemento: " + e.getMessage());
        }
    }
}
