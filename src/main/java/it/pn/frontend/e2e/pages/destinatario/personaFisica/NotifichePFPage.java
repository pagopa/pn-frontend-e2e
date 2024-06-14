package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotifichePFPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("NotifichePFPage");

    @FindBy(id = "Le tue notifiche-page")
    WebElement titleLabel;

    @FindBy(id = "notifications-table")
    WebElement tableNotifiche;

    @FindBy(id = "iunMatch")
    WebElement codiceIunTextField;

    @FindBy(id = "filter-notifications-button")
    WebElement filtraButton;

    @FindBy(id = "startDate")
    WebElement dataInizioField;

    @FindBy(id = "endDate")
    WebElement dataFineField;

    @FindBy(id = "side-item-Notifiche")
    WebElement notificheDeButton;

    @FindBy(id = "side-item-Gaio Giulio Cesare")
    WebElement nomeDeleganteButton;

    @FindBy(id = "next")
    WebElement paginaSuccessivaButton;

    @FindBy(id = "page3")
    WebElement numeroPaginaTreButton;

    @FindBy(id = "page2")
    WebElement paginaSeconda;

    @FindBy(id = "page1")
    WebElement paginaPrima;

    @FindBy(id = "rows-per-page")
    WebElement numeroPagineButton;

    @FindBy(id = "iunMatch-helper-text")
    WebElement notValidIunMessage;

    @FindBy(css = "[data-testid='cancelButton']")
    WebElement rimuoviFiltriButton;

    @FindBy(xpath = "//button[@data-testid='download-f24-button']")
    WebElement buttonDownloadF24;

    @FindBy(xpath = "//button[@data-testid='download-pagoPA-notice-button']")
    WebElement buttonDownloadAvvisoPagoPA;

    public NotifichePFPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadNotificheDEPage() {
        getWebDriverWait(10).withMessage("Notifiche DE Page non caricata correttamente: il titolo non è visibile").until(ExpectedConditions.visibilityOf(titleLabel));
        getWebDriverWait(10).withMessage("Notifiche DE Page non caricata correttamente: la tabella delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(tableNotifiche));
        logger.info("Notifiche DE Page caricata");
    }

    public boolean verificaPresenzaCodiceIunTextField() {
        try {
            getWebDriverWait(30).withMessage("Il campo codice iun non è visibile").until(ExpectedConditions.visibilityOf(codiceIunTextField));
            return codiceIunTextField.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("text field codice iun non visualizzato");
            return false;
        }
    }


    public void waitESelectDelegheButton() {
        By buttonDelegheBy = By.xpath("//div[contains(@data-testid,'menu-item(deleghe)')]");
        getWebDriverWait(10).withMessage("Non viene visualizzato il bottone deleghe nella sidebar").until(ExpectedConditions.visibilityOfElementLocated(buttonDelegheBy));
        WebElement buttonDelegheWebElement = driver.findElement(buttonDelegheBy);
        buttonDelegheWebElement.click();
        logger.info("cliccato correttamente su delega button");
    }

    public void selectFiltraButton() {
        getWebDriverWait(40).withMessage("Il bottone filtra sulla pagina notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(filtraButton));
        filtraButton.click();
    }


    public String controlloDateInserite(String data) {
        String[] date = data.split("-");
        return date[2] + "/" + date[1] + "/" + date[0];
    }

    public void inserimentoArcoTemporale(String dataDA, String dataA) {
        getWebDriverWait(10).withMessage("I campi di inserimento data non sono visibili").until(ExpectedConditions.visibilityOfAllElements(dataInizioField, dataFineField));
        dataInizioField.click();
        dataInizioField.sendKeys(dataDA);
        getWebDriverWait(3).withMessage("Il valore del campo inserimento data DA non corrisponde").until(ExpectedConditions.attributeToBe(dataInizioField, "value", dataDA));
        dataFineField.click();
        dataFineField.sendKeys(dataA);
        getWebDriverWait(3).withMessage("Il valore del campo inserimento data A non corrisponde").until(ExpectedConditions.attributeToBe(dataFineField, "value", dataA));
    }

    public boolean getListData() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]");
        getWebDriverWait(40).withMessage("La colonna Data nella pagina notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        return !elements(dataListBy).isEmpty();
    }

    public void clickNotificheButton() {
        getWebDriverWait(30).withMessage("Notifiche menu button non è cliccabile").until(ExpectedConditions.elementToBeClickable(notificheDeButton));
        js().executeScript("arguments[0].click()", notificheDeButton);
    }

    public void clickLeTueNotificheButton() {
        By leTueNotificheButtonBy = By.id("side-item-Le tue notifiche");
        getWebDriverWait(30).withMessage("Il bottone le tue notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(leTueNotificheButtonBy));
        js().executeScript("arguments[0].click()", element(leTueNotificheButtonBy));
        logger.info("Si clicca sul bottone le tue notifiche");
    }

    public void siVisualizzaPaginaNotifichePersonaFisica() {
        By bannerRecapiti = By.cssSelector("[data-testid='menu-item(i tuoi recapiti)']");
        By filtriDiRicerca = By.cssSelector("[data-testid='filter-form']");
        By elencoNotifiche = By.id("notificationsTable.body.row");
        getWebDriverWait(30).withMessage("Il titolo della pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(titleLabel));
        getWebDriverWait(30).withMessage("Il bottone 'i tuoi recapiti' della sidebar non è visibile").until(ExpectedConditions.visibilityOfElementLocated(bannerRecapiti));
        getWebDriverWait(30).withMessage("La sezione filtri per le notifiche non è visualizzabile").until(ExpectedConditions.visibilityOfElementLocated(filtriDiRicerca));
        getWebDriverWait(30).withMessage("La tabella delle notifiche non viene visualizzata correttamente").until(ExpectedConditions.visibilityOfElementLocated(elencoNotifiche));
        logger.info("Il titolo della pagina notifiche persona fisica si visualizza correttamente");
    }

    public void siVisualizzanoFiltriRicerca() {
        By filtroCodiceIunBy = By.id("iunMatch");
        getWebDriverWait(30).withMessage("il filtro Codice IUN non è visibile").until(ExpectedConditions.visibilityOfElementLocated(filtroCodiceIunBy));
        By filtroDataDaBy = By.id("startDate");
        getWebDriverWait(30).withMessage("il filtro Data Da non è visibile").until(ExpectedConditions.visibilityOfElementLocated(filtroDataDaBy));
        By filtroDataABy = By.id("endDate");
        getWebDriverWait(30).withMessage("il filtro Data A non è visibile").until(ExpectedConditions.visibilityOfElementLocated(filtroDataABy));
        logger.info("Si visualizzano correttamente i filtri ricerca");
    }

    public void siVisualizzaElencoNotifiche() {
        By elementoDellaListaBy = By.xpath("//tr[contains(@id,'notificationsTable.body.row')]");
        getWebDriverWait(30).withMessage("le righe della tabella notifiche non sono visibile").until(ExpectedConditions.visibilityOfElementLocated(elementoDellaListaBy));

        By nomeColonnaDataBy = By.xpath("//th[contains(text(),'Data')]");
        getWebDriverWait(30).withMessage("il nome della colonna Data non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaDataBy));

        By nomeColonnaOggettoBy = By.xpath("//th[contains(text(),'Oggetto')]");
        getWebDriverWait(30).withMessage("il nome della colonna Oggetto non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaOggettoBy));

        By nomeColonnaMittenteBy = By.xpath("//th[contains(text(),'Mittente')]");
        getWebDriverWait(30).withMessage("il nome della colonna Mittente non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaMittenteBy));

        By nomeColonnaCodiceIUNBy = By.xpath("//th[contains(text(),'Codice IUN')]");
        getWebDriverWait(30).withMessage("il nome della colonna Codice IUN non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaCodiceIUNBy));

        By nomeColonnaStatoBy = By.xpath("//th[contains(text(),'Stato')]");
        getWebDriverWait(30).withMessage("il nome della colonna Stato non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaStatoBy));
        logger.info("Si visualizza correttamente l'elenco delle notifiche");
    }

    public void clickNomeDelegante() {
        getWebDriverWait(30).withMessage("il nome delegante non è visibile").until(ExpectedConditions.visibilityOf(nomeDeleganteButton));
        nomeDeleganteButton.click();
    }

    public int siVisualizzaNotifichePresenti() {
        By rigaDelegaBy = By.id("notificationsTable.body.row");
        getWebDriverWait(30).withMessage("Nessuna notifica presente nella tabella notifiche").until(ExpectedConditions.visibilityOfElementLocated(rigaDelegaBy));
        return elements(rigaDelegaBy).size();
    }

    public List<WebElement> getDateNotifiche() {
        By dataCellBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]");
        getWebDriverWait(30).withMessage("la data della notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(dataCellBy));
        return elements(dataCellBy);
    }

    public boolean controllaNotifiche(List<WebElement> dateNotifiche) {
        for (int i = 0; i < dateNotifiche.size() - 1; i++) {
            String dataString1 = dateNotifiche.get(i).getText();
            String datastring2 = dateNotifiche.get(i + 1).getText();
            LocalDate data1;
            LocalDate data2;
            if (dataString1.equals("Oggi")) {
                data1 = LocalDate.now();
            } else {
                String[] date = dataString1.split("/");
                data1 = LocalDate.parse(date[2] + "-" + date[1] + "-" + date[0]);
            }
            if (datastring2.equals("Oggi")) {
                data2 = LocalDate.now();
            } else {
                String[] date = datastring2.split("/");
                data2 = LocalDate.parse(date[2] + "-" + date[1] + "-" + date[0]);
            }
            if (data1.isBefore(data2)) {
                return false;
            }
        }
        return true;
    }

    public void clickPaginaSuccessiva() {
        paginaSuccessivaButton.click();
    }

    public void waitLoadPaginaDifferente() {
        By paginaSuccessivaBy = By.id("page1");
        getWebDriverWait(30).withMessage("La prima pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(paginaSuccessivaBy));
        logger.info("Si visualizza una pagina differente dalla precedente");
    }

    public void waitLoadSecondaPagina() {
        String isPageSelected = paginaSeconda.getAttribute("aria-current");
        if (isPageSelected.equalsIgnoreCase("true")) {
            logger.info("Si visualizza una pagina differente dalla precedente");
        } else {
            logger.error("Non si visualizza una pagina differente dalla precedente");
            Assert.fail("Non si visualizza una pagina differente dalla precedente");
        }

    }

    public void siSceglieUnaPaginaDiversaConNumeroESiFiltra(String iun) {
        getWebDriverWait(30).withMessage("la terza pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(numeroPaginaTreButton));
        js().executeScript("arguments[0].click()", numeroPaginaTreButton);
        codiceIunTextField.click();
        codiceIunTextField.sendKeys(iun);
        clickFiltraButton();
        WebTool.waitTime(2);
        clickRimuoviFiltriButton();
    }

    public void modificaNumeroNotifichePagina() {
        waitLoadPage();
        getWebDriverWait(30).withMessage("Il menu per cambiare numero di pagine non è visibile").until(ExpectedConditions.visibilityOf(numeroPagineButton));
        numeroPagineButton.click();
        logger.info("Si clicca sul menu per cambiare numero di pagine visualizzate");
    }

    public void numeroDiversoPagine() {
        By numeroDiversoPagineBy = By.id("pageSize-20");
        getWebDriverWait(30).withMessage("Non viene cambiato il numero di pagine a 20").until(ExpectedConditions.visibilityOfElementLocated(numeroDiversoPagineBy));
        element(numeroDiversoPagineBy).click();
        logger.info("Cambiato il numero di pagine visualizzate a 20");

    }

    public int conteggioNotifiche() {
        By rigaDelegaBy = By.xpath("//tr[@data-testid='notificationsTable.body.row']");
        getWebDriverWait(30).withMessage("le notifiche non sono visibile").until(ExpectedConditions.visibilityOfElementLocated(rigaDelegaBy));
        return elements(rigaDelegaBy).size();
    }

    public void selezionaNotifica() {
        By notificaBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')]");
        WebTool.waitTime(2);
        getWebDriverWait(30).withMessage("Non viene visualizzata la prima riga della tabella delle notifiche").until(ExpectedConditions.elementToBeClickable(notificaBy));
        element(notificaBy).click();
    }

    public void waitLoadNotificheDEPageDelegante(String nome, String cognome) {
        By titleLabel = By.id("Le notifiche di " + nome + " " + cognome + "-page");
        By tableNotifiche = By.id("notifications-table");
        getWebDriverWait(40).withMessage("Il titolo della pagina notifiche delegante non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
        getWebDriverWait(40).withMessage("La tabella notifiche nella pagina notifiche delegante non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(tableNotifiche));
        logger.info("Notifiche DE Page caricata");
    }

    public void clickFiltraButton() {
        getWebDriverWait(30).withMessage("Il bottone filtra nella pagina ricerca Notifiche PF non è cliccabile").until(ExpectedConditions.elementToBeClickable(filtraButton));
        filtraButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        return getWebDriverWait(30).withMessage("Il messaggio di errore non e visibile").until(ExpectedConditions.visibilityOf(notValidIunMessage)).isDisplayed();
    }

    public boolean isTextBoxInvalid() {
        final String isTextboxInvalid;
        getWebDriverWait(30).withMessage("L'input codice IUN non è visibile").until(ExpectedConditions.visibilityOf(codiceIunTextField));
        String ariaInvalid = codiceIunTextField.getAttribute("aria-invalid");
        isTextboxInvalid = "true";
        return isTextboxInvalid.equals(ariaInvalid);
    }

    public void clickRimuoviFiltriButton() {
        getWebDriverWait(30).withMessage("Il bottone rimuovi filtri nella pagina ricerca Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(rimuoviFiltriButton));
        rimuoviFiltriButton.click();
    }

    public void firstPageDisplayed() {
        String isPageSelected = paginaPrima.getAttribute("aria-current");
        Assert.assertTrue(isPageSelected.equalsIgnoreCase("true"));
        logger.info("Si visualizza prima pagina");
    }

    public void clickScaricaF24Button() {
        getWebDriverWait(10).withMessage("Il bottone per scaricare l'F24 non è visibile").until(ExpectedConditions.visibilityOf(buttonDownloadF24));
        getWebDriverWait(10).withMessage("Il bottone per scaricare l'F24 non è cliccabile").until(ExpectedConditions.elementToBeClickable(buttonDownloadF24));
        scrollToElementAndClick(buttonDownloadF24);
    }

    public void checkFileF24IsOpen() {
        getWebDriverWait(10).withMessage("Il file del modello F24 non è stato aperto correttamente").until(ExpectedConditions.urlContains(".pdf"));
    }

    public void checkAvvisoPagoPaIsOpen() {
        getWebDriverWait(10).withMessage("Il file dell'avviso PagoPa non è stato aperto correttamente").until(ExpectedConditions.urlContains(".pdf"));
    }

    public void clickScaricaAvvisoPagoPAButton() {
        getWebDriverWait(10).withMessage("Il bottone per scaricare l'avviso PagoPA non è visibile").until(ExpectedConditions.visibilityOf(buttonDownloadAvvisoPagoPA));
        getWebDriverWait(10).withMessage("Il bottone per scaricare l'avviso PagoPA non è cliccabile").until(ExpectedConditions.elementToBeClickable(buttonDownloadAvvisoPagoPA));
        scrollToElementAndClick(buttonDownloadAvvisoPagoPA);
    }
}
