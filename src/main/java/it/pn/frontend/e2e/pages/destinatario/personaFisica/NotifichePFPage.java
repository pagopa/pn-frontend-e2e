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


    private WebTool webTool;

    public NotifichePFPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadNotificheDEPage() {
        getWebDriverWait(60).withMessage("Notifiche DE Page non caricata correttamente: il titolo non è visibile").until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("Le tue notifiche-page")),
                ExpectedConditions.visibilityOfElementLocated(By.id("Your notifications-page")),
                ExpectedConditions.visibilityOfElementLocated(By.id("Vos notifications-page")),
                ExpectedConditions.visibilityOfElementLocated(By.id("Deine Zustellungen-page")),
                ExpectedConditions.visibilityOfElementLocated(By.id("Vaša obvestila-page"))
        ));
        getWebDriverWait(80).withMessage("Notifiche DE Page non caricata correttamente: la tabella delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));
        logger.info("Notifiche DE Page caricata");
    }

    public boolean verificaPresenzaCodiceIunTextField() {
        try {
            getWebDriverWait(30).withMessage("Il campo codice iun non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("iunMatch"))));
            codiceIunTextField = driver.findElement(By.id("iunMatch"));
            return codiceIunTextField.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("text field codice iun non visualizzato");
            return false;
        }
    }


    public void waitESelectDelegheButton() {

        getWebDriverWait(10).withMessage("Non viene visualizzato il bottone deleghe nella sidebar").until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//div[contains(@data-testid,'menu-item(deleghe)')]"))));
        WebElement buttonDelegheWebElement = driver.findElement(By.xpath("//div[contains(@data-testid,'menu-item(deleghe)')]"));
        buttonDelegheWebElement.click();
        logger.info("cliccato correttamente su delega button");
    }

    public void selectFiltraButton() {
        webTool.waitTime(5);
        getWebDriverWait(40).withMessage("Il bottone filtra sulla pagina notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable( driver.findElement(By.id("filter-notifications-button"))));
        filtraButton = driver.findElement(By.id("filter-notifications-button"));
        filtraButton.click();
    }


    public String controlloDateInserite(String data) {
        String[] date = data.split("-");
        return date[2] + "/" + date[1] + "/" + date[0];
    }

    public void inserimentoArcoTemporale(String dataDA, String dataA) {
        getWebDriverWait(20).withMessage("I campi di inserimento data non sono visibili").until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("startDate")),  driver.findElement(By.id("endDate"))));
        dataInizioField = driver.findElement(By.id("startDate"));
        dataFineField = driver.findElement(By.id("endDate"));
        dataInizioField.click();
        dataInizioField.sendKeys(dataDA);
        getWebDriverWait(3).withMessage("Il valore del campo inserimento data DA non corrisponde").until(ExpectedConditions.attributeToBe(dataInizioField, "value", dataDA));
        dataFineField.click();
        dataFineField.sendKeys(dataA);
        getWebDriverWait(3).withMessage("Il valore del campo inserimento data A non corrisponde").until(ExpectedConditions.attributeToBe(dataFineField, "value", dataA));
    }

    public boolean getListData() {
        //By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]");
        getWebDriverWait(60).withMessage("La colonna Data nella pagina notifiche non è visibile").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]")));
        return !elements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]")).isEmpty();
    }

    public void clickNotificheButton() {
        getWebDriverWait(30).withMessage("Notifiche menu button non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("side-item-Notifiche"))));
        notificheDeButton = driver.findElement(By.id("side-item-Notifiche"));
        js().executeScript("arguments[0].click()", notificheDeButton);
    }

    public void clickLeTueNotificheButton() {
        getWebDriverWait(30).withMessage("Il bottone le tue notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("side-item-Le tue notifiche"))));
        WebElement leTueNotificheButtonBy = driver.findElement(By.id("side-item-Le tue notifiche"));
        js().executeScript("arguments[0].click()", leTueNotificheButtonBy);
        logger.info("Si clicca sul bottone le tue notifiche");
    }

    public void siVisualizzaPaginaNotifichePersonaFisica() {
        getWebDriverWait(30).withMessage("Il titolo della pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(By.id("Le tue notifiche-page")));
        getWebDriverWait(30).withMessage("Il bottone 'i tuoi recapiti' della sidebar non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-testid='menu-item(i tuoi recapiti)']"))));
        getWebDriverWait(30).withMessage("La sezione filtri per le notifiche non è visualizzabile").until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-testid='filter-form']"))));
        getWebDriverWait(30).withMessage("La tabella delle notifiche non viene visualizzata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notificationsTable.body.row"))));
        logger.info("Il titolo della pagina notifiche persona fisica si visualizza correttamente");
    }

    public void siVisualizzanoFiltriRicerca() {
       // WebElement filtroCodiceIunBy = driver.findElement(By.id("iunMatch"));
        getWebDriverWait(30).withMessage("il filtro Codice IUN non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("iunMatch"))));
        //WebElement filtroDataDaBy = driver.findElement(By.id("startDate"));
        getWebDriverWait(30).withMessage("il filtro Data Da non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("startDate"))));
       // WebElement filtroDataABy = driver.findElement(By.id("endDate"));
        getWebDriverWait(30).withMessage("il filtro Data A non è visibile").until(ExpectedConditions.visibilityOf( driver.findElement(By.id("endDate"))));
        logger.info("Si visualizzano correttamente i filtri ricerca");
    }

    public void siVisualizzaElencoNotifiche() {
      //  WebElement elementoDellaListaBy = driver.findElement(By.xpath("//tr[contains(@id,'notificationsTable.body.row')]"));
        getWebDriverWait(30).withMessage("le righe della tabella notifiche non sono visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[contains(@id,'notificationsTable.body.row')]"))));

        //WebElement nomeColonnaDataBy = driver.findElement(By.xpath("//th[contains(text(),'Data')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Data non è visibile").until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//th[contains(text(),'Data')]"))));

        //WebElement nomeColonnaOggettoBy = driver.findElement(By.xpath("//th[contains(text(),'Oggetto')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Oggetto non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//th[contains(text(),'Oggetto')]"))));

        //WebElement nomeColonnaMittenteBy = driver.findElement(By.xpath("//th[contains(text(),'Mittente')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Mittente non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//th[contains(text(),'Mittente')]"))));

        //WebElement nomeColonnaCodiceIUNBy = driver.findElement(By.xpath("//th[contains(text(),'Codice IUN')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Codice IUN non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//th[contains(text(),'Codice IUN')]"))));

        //WebElement nomeColonnaStatoBy = driver.findElement(By.xpath("//th[contains(text(),'Stato')]"));
        getWebDriverWait(30).withMessage("il nome della colonna Stato non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//th[contains(text(),'Stato')]"))));
        logger.info("Si visualizza correttamente l'elenco delle notifiche");
    }

    public void clickNomeDelegante() {
        getWebDriverWait(30).withMessage("il nome delegante non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("side-item-Gaio Giulio Cesare"))));
        nomeDeleganteButton = driver.findElement(By.id("side-item-Gaio Giulio Cesare"));
        nomeDeleganteButton.click();
    }

    public int siVisualizzaNotifichePresenti() {
       // List<WebElement> rigaDelegaBy = driver.findElements(By.id("notificationsTable.body.row"));
        getWebDriverWait(30).withMessage("Nessuna notifica presente nella tabella notifiche").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id("notificationsTable.body.row"))));
        return driver.findElements(By.id("notificationsTable.body.row")).size();
    }

    public List<WebElement> getDateNotifiche() {
        getWebDriverWait(30).withMessage("la data della notifica non è visibile").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]"))));
        return driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]"));
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
        paginaSuccessivaButton = driver.findElement(By.id("next"));
        paginaSuccessivaButton.click();
    }

    public void waitLoadPaginaDifferente() {
        getWebDriverWait(30).withMessage("La prima pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("page1")));
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

        getWebDriverWait(30).withMessage("la terza pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("page3"))));
        numeroPaginaTreButton = driver.findElement(By.id("page3"));
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
        getWebDriverWait(30).withMessage("Il menu per cambiare numero di pagine non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("rows-per-page"))));
        numeroPagineButton = driver.findElement(By.id("rows-per-page"));
        numeroPagineButton.click();
        logger.info("Si clicca sul menu per cambiare numero di pagine visualizzate");
    }

    public void numeroDiversoPagine() {
        getWebDriverWait(30).withMessage("Non viene cambiato il numero di pagine a 20").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("pageSize-20"))));
        WebElement numeroDiversoPagineBy = driver.findElement(By.id("pageSize-20"));
        numeroDiversoPagineBy.click();
        logger.info("Cambiato il numero di pagine visualizzate a 20");

    }

    public int conteggioNotifiche() {
        getWebDriverWait(30).withMessage("le notifiche non sono visibile").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//tr[@data-testid='notificationsTable.body.row']"))));
        List<WebElement> rigaDelegaBy = driver.findElements(By.xpath("//tr[@data-testid='notificationsTable.body.row']"));
        return rigaDelegaBy.size();
    }

    public void selezionaNotifica() {
        webTool.waitTime(2);
        getWebDriverWait(30).withMessage("Non viene visualizzata la prima riga della tabella delle notifiche").until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')]")).get(0)));
        List<WebElement> notificaBy = driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')]"));
        notificaBy.get(0).click();
    }

    public void waitLoadNotificheDEPageDelegante(String nome, String cognome) {
      //  webTool.waitTime(40);
        getWebDriverWait(40).withMessage("Il titolo della pagina notifiche delegante non è visibile")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Le notifiche di " + nome + " " + cognome + "-page"))));
        titleLabel = driver.findElement(By.id("Le notifiche di " + nome + " " + cognome + "-page"));
        getWebDriverWait(40).withMessage("La tabella notifiche nella pagina notifiche delegante non è visibile")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));
        //tableNotifiche = driver.findElement(By.id("notifications-table"));
        logger.info("Notifiche DE Page caricata");
    }

    public void clickFiltraButton() {
        getWebDriverWait(30).withMessage("Il bottone filtra nella pagina ricerca Notifiche PF non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("filter-notifications-button"))));
        filtraButton = driver.findElement(By.id("filter-notifications-button"));
        filtraButton.click();
    }

    public boolean isErrorMessageDisplayed() {
       // notValidIunMessage = driver.findElement(By.id("iunMatch-helper-text"));
        return getWebDriverWait(30).withMessage("Il messaggio di errore non e visibile").until(ExpectedConditions.visibilityOf( driver.findElement(By.id("iunMatch-helper-text")))).isDisplayed();
    }

    public boolean isTextBoxInvalid() {
        final String isTextboxInvalid;
        getWebDriverWait(30).withMessage("L'input codice IUN non è visibile").until(ExpectedConditions.visibilityOf( driver.findElement(By.id("iunMatch"))));
        codiceIunTextField = driver.findElement(By.id("iunMatch"));
        String ariaInvalid = codiceIunTextField.getAttribute("aria-invalid");
        isTextboxInvalid = "true";
        return isTextboxInvalid.equals(ariaInvalid);
    }

    public void clickRimuoviFiltriButton() {
        getWebDriverWait(30).withMessage("Il bottone rimuovi filtri nella pagina ricerca Notifiche PG non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[data-testid='cancelButton']"))));
        rimuoviFiltriButton = driver.findElement(By.cssSelector("[data-testid='cancelButton']"));
        rimuoviFiltriButton.click();
    }

    public void firstPageDisplayed() {
        paginaPrima = driver.findElement(By.id("page1"));
        String isPageSelected = paginaPrima.getAttribute("aria-current");
        Assertions.assertTrue(isPageSelected.equalsIgnoreCase("true"));
        logger.info("Si visualizza prima pagina");
    }

    public void clickScaricaF24Button() {
        webTool.waitTime(5);
        buttonDownloadF24 = driver.findElement(By.xpath("//button[@data-testid='download-f24-button']"));
        getWebDriverWait(20).withMessage("Il bottone per scaricare l'F24 non è visibile").until(ExpectedConditions.visibilityOf(buttonDownloadF24));
        getWebDriverWait(20).withMessage("Il bottone per scaricare l'F24 non è cliccabile").until(ExpectedConditions.elementToBeClickable(buttonDownloadF24));
        scrollToElementAndClick(buttonDownloadF24);
    }

    public void checkFileF24IsOpen() {
        getWebDriverWait(10).withMessage("Il file del modello F24 non è stato aperto correttamente").until(ExpectedConditions.urlContains(".pdf"));
    }

    public void checkAvvisoPagoPaIsOpen() {
        getWebDriverWait(10).withMessage("Il file dell'avviso PagoPa non è stato aperto correttamente").until(ExpectedConditions.urlContains(".pdf"));
    }

    public void clickScaricaAvvisoPagoPAButton() {
        webTool.waitTime(5);
        buttonDownloadAvvisoPagoPA = driver.findElement(By.xpath("//button[@data-testid='download-pagoPA-notice-button']"));
        getWebDriverWait(10).withMessage("Il bottone per scaricare l'avviso PagoPA non è visibile").until(ExpectedConditions.visibilityOf(buttonDownloadAvvisoPagoPA));
        getWebDriverWait(10).withMessage("Il bottone per scaricare l'avviso PagoPA non è cliccabile").until(ExpectedConditions.elementToBeClickable(buttonDownloadAvvisoPagoPA));
        scrollToElementAndClick(buttonDownloadAvvisoPagoPA);
    }

    public void verificaPresenzaCodiciAvvisoEF24(int numeroAttesoCodiciAvviso, int numeroAttesoF24) {
        // Verifica codici avviso PagoPA
        List<WebElement> codiciAvviso = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[data-testid='pagopa-item']")));

        Assertions.assertEquals(numeroAttesoCodiciAvviso, codiciAvviso.size(),
                "Numero errato di codici avviso PagoPA, attesi: " + numeroAttesoCodiciAvviso);

        for (int i = 0; i < codiciAvviso.size(); i++) {
            logger.info("codiceAvviso_{}: {}", i + 1, codiciAvviso.get(i).getText());
        }

        // Verifica modelli F24
        List<WebElement> modelliF24 = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[data-testid='download-f24-button']")));

        Assertions.assertEquals(numeroAttesoF24, modelliF24.size(),
                "Numero errato di modelli F24, attesi: " + numeroAttesoF24);
    }
}
