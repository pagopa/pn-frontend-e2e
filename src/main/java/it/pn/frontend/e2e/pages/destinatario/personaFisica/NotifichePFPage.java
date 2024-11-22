package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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


    private  WebTool webTool;

    public NotifichePFPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadNotificheDEPage() {
        webTool.waitTime(10);
        titleLabel = driver.findElement(By.id("Le tue notifiche-page"));
        tableNotifiche = driver.findElement(By.id("notifications-table"));
        getWebDriverWait(10).withMessage("Notifiche DE Page non caricata correttamente: il titolo non è visibile").until(ExpectedConditions.visibilityOf(titleLabel));
        getWebDriverWait(10).withMessage("Notifiche DE Page non caricata correttamente: la tabella delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(tableNotifiche));
        logger.info("Notifiche DE Page caricata");
    }

    public boolean verificaPresenzaCodiceIunTextField() {
        try {
            codiceIunTextField = driver.findElement(By.id("iunMatch"));
            getWebDriverWait(30).withMessage("Il campo codice iun non è visibile").until(ExpectedConditions.visibilityOf(codiceIunTextField));
            return codiceIunTextField.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("text field codice iun non visualizzato");
            return false;
        }
    }


    public void waitESelectDelegheButton() {
        WebElement buttonDelegheWebElement = driver.findElement(By.xpath("//div[contains(@data-testid,'menu-item(deleghe)')]"));
        getWebDriverWait(10).withMessage("Non viene visualizzato il bottone deleghe nella sidebar").until(ExpectedConditions.visibilityOf(buttonDelegheWebElement));
        buttonDelegheWebElement.click();
        logger.info("cliccato correttamente su delega button");
    }

    public void selectFiltraButton() {
        webTool.waitTime(5);
        filtraButton = driver.findElement(By.id("filter-notifications-button"));
        getWebDriverWait(40).withMessage("Il bottone filtra sulla pagina notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(filtraButton));
        filtraButton.click();
    }


    public String controlloDateInserite(String data) {
        String[] date = data.split("-");
        return date[2] + "/" + date[1] + "/" + date[0];
    }

    public void inserimentoArcoTemporale(String dataDA, String dataA) {
        dataInizioField = driver.findElement(By.id("startDate"));
        dataFineField = driver.findElement(By.id("endDate"));
        getWebDriverWait(10).withMessage("I campi di inserimento data non sono visibili").until(ExpectedConditions.visibilityOfAllElements(dataInizioField, dataFineField));
        dataInizioField.click();
        dataInizioField.sendKeys(dataDA);
        getWebDriverWait(3).withMessage("Il valore del campo inserimento data DA non corrisponde").until(ExpectedConditions.attributeToBe(dataInizioField, "value", dataDA));
        dataFineField.click();
        dataFineField.sendKeys(dataA);
        getWebDriverWait(3).withMessage("Il valore del campo inserimento data A non corrisponde").until(ExpectedConditions.attributeToBe(dataFineField, "value", dataA));
    }

    public boolean getListData() {
        List<WebElement> dataListBy = driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]"));
        getWebDriverWait(40).withMessage("La colonna Data nella pagina notifiche non è visibile").until(ExpectedConditions.visibilityOfAllElements(dataListBy));
        return dataListBy.isEmpty();
    }

    public void clickNotificheButton() {
        notificheDeButton = driver.findElement(By.id("side-item-Notifiche"));
        getWebDriverWait(30).withMessage("Notifiche menu button non è cliccabile").until(ExpectedConditions.elementToBeClickable(notificheDeButton));
        js().executeScript("arguments[0].click()", notificheDeButton);
    }

    public void clickLeTueNotificheButton() {
        WebElement leTueNotificheButtonBy = driver.findElement(By.id("side-item-Le tue notifiche"));
        getWebDriverWait(30).withMessage("Il bottone le tue notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(leTueNotificheButtonBy));
        js().executeScript("arguments[0].click()", leTueNotificheButtonBy);
        logger.info("Si clicca sul bottone le tue notifiche");
    }

    public void siVisualizzaPaginaNotifichePersonaFisica() {
        WebElement bannerRecapiti = driver.findElement(By.cssSelector("[data-testid='menu-item(i tuoi recapiti)']"));
        WebElement filtriDiRicerca = driver.findElement(By.cssSelector("[data-testid='filter-form']"));
        WebElement elencoNotifiche =driver.findElement( By.id("notificationsTable.body.row"));
        getWebDriverWait(30).withMessage("Il titolo della pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(titleLabel));
        getWebDriverWait(30).withMessage("Il bottone 'i tuoi recapiti' della sidebar non è visibile").until(ExpectedConditions.visibilityOf(bannerRecapiti));
        getWebDriverWait(30).withMessage("La sezione filtri per le notifiche non è visualizzabile").until(ExpectedConditions.visibilityOf(filtriDiRicerca));
        getWebDriverWait(30).withMessage("La tabella delle notifiche non viene visualizzata correttamente").until(ExpectedConditions.visibilityOf(elencoNotifiche));
        logger.info("Il titolo della pagina notifiche persona fisica si visualizza correttamente");
    }

    public void siVisualizzanoFiltriRicerca() {
        WebElement filtroCodiceIunBy = driver.findElement(By.id("iunMatch"));
        getWebDriverWait(30).withMessage("il filtro Codice IUN non è visibile").until(ExpectedConditions.visibilityOf(filtroCodiceIunBy));
        WebElement filtroDataDaBy = driver.findElement(By.id("startDate"));
        getWebDriverWait(30).withMessage("il filtro Data Da non è visibile").until(ExpectedConditions.visibilityOf(filtroDataDaBy));
        WebElement filtroDataABy = driver.findElement(By.id("endDate"));
        getWebDriverWait(30).withMessage("il filtro Data A non è visibile").until(ExpectedConditions.visibilityOf(filtroDataABy));
        logger.info("Si visualizzano correttamente i filtri ricerca");
    }

    public void siVisualizzaElencoNotifiche() {
        WebElement elementoDellaListaBy = driver.findElement(By.xpath("//tr[contains(@id,'notificationsTable.body.row')]"));
        getWebDriverWait(30).withMessage("le righe della tabella notifiche non sono visibile").until(ExpectedConditions.visibilityOf(elementoDellaListaBy));

        WebElement nomeColonnaDataBy = driver.findElement(By.xpath("//th[contains(text(),'Data')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Data non è visibile").until(ExpectedConditions.visibilityOf(nomeColonnaDataBy));

        WebElement nomeColonnaOggettoBy = driver.findElement(By.xpath("//th[contains(text(),'Oggetto')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Oggetto non è visibile").until(ExpectedConditions.visibilityOf(nomeColonnaOggettoBy));

        WebElement nomeColonnaMittenteBy = driver.findElement(By.xpath("//th[contains(text(),'Mittente')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Mittente non è visibile").until(ExpectedConditions.visibilityOf(nomeColonnaMittenteBy));

        WebElement nomeColonnaCodiceIUNBy = driver.findElement(By.xpath("//th[contains(text(),'Codice IUN')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Codice IUN non è visibile").until(ExpectedConditions.visibilityOf(nomeColonnaCodiceIUNBy));

        WebElement nomeColonnaStatoBy = driver.findElement(By.xpath("//th[contains(text(),'Stato')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Stato non è visibile").until(ExpectedConditions.visibilityOf(nomeColonnaStatoBy));
        logger.info("Si visualizza correttamente l'elenco delle notifiche");
    }

    public void clickNomeDelegante() {
        nomeDeleganteButton = driver.findElement(By.id("side-item-Gaio Giulio Cesare"));
        getWebDriverWait(30).withMessage("il nome delegante non è visibile").until(ExpectedConditions.visibilityOf(nomeDeleganteButton));
        nomeDeleganteButton.click();
    }

    public int siVisualizzaNotifichePresenti() {
        List<WebElement> rigaDelegaBy = driver.findElements(By.id("notificationsTable.body.row"));
        getWebDriverWait(30).withMessage("Nessuna notifica presente nella tabella notifiche").until(ExpectedConditions.visibilityOfAllElements(rigaDelegaBy));
        return rigaDelegaBy.size();
    }

    public List<WebElement> getDateNotifiche() {
        List<WebElement> dataCellBy = driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]"));
        getWebDriverWait(30).withMessage("la data della notifica non è visibile").until(ExpectedConditions.visibilityOfAllElements(dataCellBy));
        return dataCellBy;
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
        WebElement paginaSuccessivaBy = driver.findElement(By.id("page1"));
        getWebDriverWait(30).withMessage("La prima pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(paginaSuccessivaBy));
        logger.info("Si visualizza una pagina differente dalla precedente");
    }

    public void waitLoadSecondaPagina() {
        paginaSeconda = driver.findElement(By.id("page2"));
        String isPageSelected = paginaSeconda.getAttribute("aria-current");
        if (isPageSelected.equalsIgnoreCase("true")) {
            logger.info("Si visualizza una pagina differente dalla precedente");
        } else {
            logger.error("Non si visualizza una pagina differente dalla precedente");
            Assertions.fail("Non si visualizza una pagina differente dalla precedente");
        }

    }

    public void siSceglieUnaPaginaDiversaConNumeroESiFiltra(String iun) {
        numeroPaginaTreButton = driver.findElement(By.id("page3"));

        getWebDriverWait(30).withMessage("la terza pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(numeroPaginaTreButton));
        js().executeScript("arguments[0].click()", numeroPaginaTreButton);
        codiceIunTextField = driver.findElement(By.id("iunMatch"));
        codiceIunTextField.click();
        codiceIunTextField.sendKeys(iun);
        clickFiltraButton();
        webTool.waitTime(2);
        clickRimuoviFiltriButton();
    }

    public void modificaNumeroNotifichePagina() {
        waitLoadPage();
        numeroPagineButton = driver.findElement(By.id("rows-per-page"));
        getWebDriverWait(30).withMessage("Il menu per cambiare numero di pagine non è visibile").until(ExpectedConditions.visibilityOf(numeroPagineButton));
        numeroPagineButton.click();
        logger.info("Si clicca sul menu per cambiare numero di pagine visualizzate");
    }

    public void numeroDiversoPagine() {
        WebElement numeroDiversoPagineBy = driver.findElement(By.id("pageSize-20"));
        getWebDriverWait(30).withMessage("Non viene cambiato il numero di pagine a 20").until(ExpectedConditions.visibilityOf(numeroDiversoPagineBy));
        numeroDiversoPagineBy.click();
        logger.info("Cambiato il numero di pagine visualizzate a 20");

    }

    public int conteggioNotifiche() {
        List<WebElement> rigaDelegaBy = driver.findElements(By.xpath("//tr[@data-testid='notificationsTable.body.row']"));
        getWebDriverWait(30).withMessage("le notifiche non sono visibile").until(ExpectedConditions.visibilityOfAllElements(rigaDelegaBy));
        return rigaDelegaBy.size();
    }

    public void selezionaNotifica() {
        List<WebElement> notificaBy = driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')]"));
        webTool.waitTime(2);
        getWebDriverWait(30).withMessage("Non viene visualizzata la prima riga della tabella delle notifiche").until(ExpectedConditions.elementToBeClickable(notificaBy.get(0)));
        notificaBy.get(0).click();
    }

    public void waitLoadNotificheDEPageDelegante(String nome, String cognome) {
        titleLabel = driver.findElement(By.id("Le notifiche di " + nome + " " + cognome + "-page"));
        tableNotifiche = driver.findElement(By.id("notifications-table"));
        getWebDriverWait(40).withMessage("Il titolo della pagina notifiche delegante non è visibile")
                .until(ExpectedConditions.visibilityOf(titleLabel));
        getWebDriverWait(40).withMessage("La tabella notifiche nella pagina notifiche delegante non è visibile")
                .until(ExpectedConditions.visibilityOf(tableNotifiche));
        logger.info("Notifiche DE Page caricata");
    }

    public void clickFiltraButton() {
        filtraButton = driver.findElement(By.id("filter-notifications-button"));
        getWebDriverWait(30).withMessage("Il bottone filtra nella pagina ricerca Notifiche PF non è cliccabile").until(ExpectedConditions.elementToBeClickable(filtraButton));
        filtraButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        notValidIunMessage = driver.findElement(By.id("iunMatch-helper-text"));
        return getWebDriverWait(30).withMessage("Il messaggio di errore non e visibile").until(ExpectedConditions.visibilityOf(notValidIunMessage)).isDisplayed();
    }

    public boolean isTextBoxInvalid() {
        final String isTextboxInvalid;
        codiceIunTextField = driver.findElement(By.id("iunMatch"));
        getWebDriverWait(30).withMessage("L'input codice IUN non è visibile").until(ExpectedConditions.visibilityOf(codiceIunTextField));
        String ariaInvalid = codiceIunTextField.getAttribute("aria-invalid");
        isTextboxInvalid = "true";
        return isTextboxInvalid.equals(ariaInvalid);
    }

    public void clickRimuoviFiltriButton() {
        rimuoviFiltriButton = driver.findElement(By.cssSelector("[data-testid='cancelButton']"));
        getWebDriverWait(30).withMessage("Il bottone rimuovi filtri nella pagina ricerca Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(rimuoviFiltriButton));
        rimuoviFiltriButton.click();
    }

    public void firstPageDisplayed() {
        paginaPrima = driver.findElement(By.id("page1"));
        String isPageSelected = paginaPrima.getAttribute("aria-current");
        Assertions.assertTrue(isPageSelected.equalsIgnoreCase("true"));
        logger.info("Si visualizza prima pagina");
    }

    public void clickScaricaF24Button() {
        buttonDownloadF24 = driver.findElement(By.xpath("//button[@data-testid='download-f24-button']"));
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
        buttonDownloadAvvisoPagoPA = driver.findElement(By.xpath("//button[@data-testid='download-pagoPA-notice-button']"));
        getWebDriverWait(10).withMessage("Il bottone per scaricare l'avviso PagoPA non è visibile").until(ExpectedConditions.visibilityOf(buttonDownloadAvvisoPagoPA));
        getWebDriverWait(10).withMessage("Il bottone per scaricare l'avviso PagoPA non è cliccabile").until(ExpectedConditions.elementToBeClickable(buttonDownloadAvvisoPagoPA));
        scrollToElementAndClick(buttonDownloadAvvisoPagoPA);
    }
}
