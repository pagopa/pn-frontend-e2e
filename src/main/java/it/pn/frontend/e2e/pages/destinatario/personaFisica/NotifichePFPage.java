package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
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

    @FindBy(id = "rows-per-page")
    WebElement numeroPagineButton;

    public NotifichePFPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadNotificheDEPage() {
        try{
            By titleLabel = By.id("Le tue notifiche-page");
            By tableNotifiche = By.id("notifications-table");
            this.getWebDriverWait(40).withMessage("Il titolo della pagina Notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(40).withMessage("La tabella delle Notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(tableNotifiche));
            logger.info("Notifiche DE Page caricata");
        }catch (TimeoutException e){
            logger.error("Notifiche DE Page non caricata con errore : "+e.getMessage());
            Assert.fail("Notifiche DE Page non caricata con errore : "+e.getMessage());
        }
    }

    public boolean verificaPresenzaCodiceIunTextField() {
        try{
            getWebDriverWait(30).withMessage("Il campo codice iun non è visibile").until(ExpectedConditions.visibilityOf(this.codiceIunTextField));
            return codiceIunTextField.isDisplayed();
        }catch (NoSuchElementException e){
            logger.error("text field codice iun non visualizzato");
            return false;
        }
    }


    public void waitESelectDelegheButton() {
        try{
            By buttonDelegeBy = By.xpath("//div[contains(@data-testid,'menu-item(deleghe)')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(buttonDelegeBy));
            WebElement buttonDelegheWebElement = this.driver.findElement(buttonDelegeBy);
            buttonDelegheWebElement.click();
            logger.info("cliccato correttamente su delega button");
        }catch (TimeoutException e){
            logger.error("Delega button NON trovata con errore: "+e.getMessage());
            Assert.fail("Delega button NON trovata con errore: "+e.getMessage());
        }
    }

    public void selectFiltraButton() {
        this.getWebDriverWait(40).withMessage("Il bottone filtra sulla pagina notifiche non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.filtraButton));
        this.filtraButton.click();
    }



    public String controlloDateInserite(String data) {
        String[] date = data.split("-");
        return date[2]+"/" + date[1]+ "/"+date[0];
    }

    public void inserimentoArcoTemporale(String dataDA, String dataA) {
        this.getWebDriverWait(40).withMessage("Data Inizio input non è visibile").until(ExpectedConditions.visibilityOf(this.dataInizioField));
        this.getWebDriverWait(40).withMessage("Data Fine  input non è visibile").until(ExpectedConditions.visibilityOf(this.dataFineField));
        this.dataInizioField.sendKeys(dataDA);
        this.dataFineField.sendKeys(dataA);
    }

    public boolean getListData() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
        this.getWebDriverWait(40).withMessage("La colonna Data nella pagina notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        return !this.elements(dataListBy).isEmpty();
    }

    public boolean verificaEsistenzaEPassaggioPagina() {
        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
        try {
            By numeroButtonBy = By.id("page2");
            this.getWebDriverWait(20).withMessage("bottone pagina 2 non è visibile").until(ExpectedConditions.visibilityOfElementLocated(numeroButtonBy));
            logger.info("Bottone pagina 2 trovato");

            WebElement numeroPagina = this.element(numeroButtonBy);
            numeroPagina.click();
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public void clickNotificheButton() {
        this.getWebDriverWait(30).withMessage("Notifiche menu button non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.notificheDeButton));
        this.js().executeScript("arguments[0].click()",this.notificheDeButton);}

    public void clickTueNotificheButton(){
        try {
            By leTueNotificheButtonBy = By.id("side-item-Le tue notifiche");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(leTueNotificheButtonBy));
            this.js().executeScript("arguments[0].click()",this.element(leTueNotificheButtonBy));
            logger.info("Si clicca sul bottone le tue notifiche");
        }catch (TimeoutException e){
            logger.error("Il bottone le tue notifiche non è cliccabile con errore: "+e.getMessage());
            Assert.fail("Il bottone le tue notifiche non è cliccabile con errore: "+e.getMessage());
        }
    }

    public void siVisualizzaPaginaNotifichePersonaFisica() {
        try {
            By notifichePageTitleBy = By.id("Le tue notifiche-page");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notifichePageTitleBy));
            logger.info("Il titolo della pagina notifiche persona fisica si visualizza correttamente");
        }catch (TimeoutException e){
            logger.error("Il titolo della pagina notifiche persona fisica NON si visualizza correttamente con errore:"+e.getMessage());
            Assert.fail("Il titolo della pagina notifiche persona fisica NON si visualizza correttamente con errore:"+e.getMessage());
        }
    }

    public void siVisualizzanoFiltriRicerca() {
        try {
            By filtroCodiceIunBy = By.id("iunMatch");
            getWebDriverWait(30).withMessage("il filtro Codice IUN non è visibile").until(ExpectedConditions.visibilityOfElementLocated(filtroCodiceIunBy));
            By filtroDataDaBy = By.id("startDate");
            getWebDriverWait(30).withMessage("il filtro Data Da non è visibile").until(ExpectedConditions.visibilityOfElementLocated(filtroDataDaBy));
            By filtroDataABy = By.id("endDate");
            getWebDriverWait(30).withMessage("il filtro Data A non è visibile").until(ExpectedConditions.visibilityOfElementLocated(filtroDataABy));
            logger.info("Si visualizzano correttamente i filtri ricerca");
        }catch (TimeoutException e){
            logger.error("Non si visualizzano correttamente i filtri ricerca con errore:"+e.getMessage());
            Assert.fail("Non si visualizzano correttamente i filtri ricerca con errore:"+e.getMessage());
        }
    }

    public void siVisualizzaElencoNotifiche() {
        try {
            By elementoDellaListaBy = By.xpath("//tr[contains(@id,'notificationsTable.row')]");
            getWebDriverWait(30).withMessage("le righe della tabella notifiche non sono visibile").until(ExpectedConditions.visibilityOfElementLocated(elementoDellaListaBy));

            By nomeColonnaDataBy = By.xpath("//th[contains(text(),'Data')]");
            getWebDriverWait(30).withMessage("il nome della colonna Data non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaDataBy));

            By nomeColonnaOggettoBy = By.xpath("//th[contains(text(),'Oggetto')]");
            getWebDriverWait(30).withMessage("il nome della colonna Oggetto non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaOggettoBy));

            By nomeColonnaMittenteBy = By.xpath("//th[contains(text(),'Mittente')]");
            getWebDriverWait(30).withMessage("il nome della colonna Mittente non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaMittenteBy));
            logger.info("Si visualizza correttamente l'elenco delle notifiche");

        }catch (Exception e){
            logger.error("NON si visualizza correttamente l'elenco delle notifiche con errore:"+e.getMessage());
            Assert.fail("NON si visualizza correttamente l'elenco delle notifiche con errore:"+e.getMessage());
        }
    }

    public void clickNomeDelegante() {
        getWebDriverWait(30).withMessage("il nome delegante non è visibile").until(ExpectedConditions.visibilityOf(nomeDeleganteButton));
        this.nomeDeleganteButton.click();
    }

    public int siVisualizzaNotifichePresenti() {
        By rigaDelegaBy = By.xpath("//tr[@id='notificationsTable.row']");
        getWebDriverWait(30).withMessage("Nessuna notifica presente nella tabella notifiche").until(ExpectedConditions.visibilityOfElementLocated(rigaDelegaBy));
        return elements(rigaDelegaBy).size();
    }

    public List<WebElement> getDateNotifiche() {
        By dataCellBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
        getWebDriverWait(30).withMessage("la data della notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(dataCellBy));
        return elements(dataCellBy);
    }

    public boolean controllaNotifiche(List<WebElement> dateNotifiche) {
        for (int i = 0; i<dateNotifiche.size()-1;i++){
            String dataString1 = dateNotifiche.get(i).getText();
            String datastring2 = dateNotifiche.get(i+1).getText();
            LocalDate data1;
            LocalDate data2;
            if(dataString1.equals("Oggi")){
                data1 = LocalDate.now();
            }else {
                String[] date = dataString1.split("/");
                data1 = LocalDate.parse(date[2]+"-"+date[1]+"-"+date[0]);
            }
            if(datastring2.equals("Oggi")){
                data2 = LocalDate.now();
            }else {
                String[] date = datastring2.split("/");
                data2 = LocalDate.parse(date[2]+"-"+date[1]+"-"+date[0]);
            }
            if(data1.isBefore(data2)){
                return false;
            }
        }
        return true;
    }

    public void clickSullaFreccia() {this.paginaSuccessivaButton.click();}

    public void waitLoadPaginaDifferente() {
        try{
            By paginaSuccessivaBy = By.id("page1");
            getWebDriverWait(30).withMessage("la prima pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOfElementLocated(paginaSuccessivaBy));
            logger.info("Si visualizza una pagina differente dalla precedente");
        }catch (TimeoutException e){
            logger.error("NON si visualizza una");
        }
    }

    public void siSceglieUnaPaginaDiversaConNumero() {
        getWebDriverWait(30).withMessage("la terza pagina delle notifiche non è visibile").until(ExpectedConditions.visibilityOf(this.numeroPaginaTreButton));
        this.js().executeScript("arguments[0].click()",this.numeroPaginaTreButton);
    }

    public void modificaNumeroNotifichePagina() {
        waitLoadPage();
        getWebDriverWait(30).withMessage("Il menu per cambiare numero di pagine non è visibile").until(ExpectedConditions.visibilityOf(this.numeroPagineButton));
        this.numeroPagineButton.click();
        logger.info("Si clicca sul menu per cambiare numero di pagine visualizzate");
    }

    public void numeroDiversoPagine() {
        try {
            By numeroDiversoPagineBy = By.id("pageSize-20");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(numeroDiversoPagineBy));
            this.element(numeroDiversoPagineBy).click();
            logger.info("Cambiato il numero di pagine visualizzate a 20");
        }catch (TimeoutException e){
            logger.error("NON cambia il numero di pagine visualizzate a 20 con errore:"+e.getMessage());
            Assert.fail("NON cambia il numero di pagine visualizzate a 20 con errore:"+e.getMessage());
        }

    }

    public int conteggioNotifiche() {
        By rigaDelegaBy = By.xpath("//tr[@data-testid='notificationsTable.row']");
        this.getWebDriverWait(30).withMessage("le notifiche non sono visibile").until(ExpectedConditions.visibilityOfElementLocated(rigaDelegaBy));
        return elements(rigaDelegaBy).size();
    }

    public void selezionaNotifica() {
        try{
            By notificaBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-155o2nr')]");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(notificaBy));
            this.element(notificaBy).click();
        }catch (TimeoutException e){
            logger.error("Notifica non trovata con errore: "+e.getMessage());
            Assert.fail("Notifica non trovata con errore: "+e.getMessage());
        }
    }

    public void waitLoadNotificheDEPageDelegante(String nome, String cognome) {
        try{
            By titleLabel = By.id("Le notifiche di "+nome+" "+cognome+"-page");
            By tableNotifiche = By.id("notifications-table");
            this.getWebDriverWait(40).withMessage("Il titolo della pagina notifiche delegante non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(titleLabel));

            this.getWebDriverWait(40).withMessage("La tabella notifiche nella pagina notifiche delegante non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(tableNotifiche));
            logger.info("Notifiche DE Page caricata");

        }catch (TimeoutException e){
            logger.error("Notifiche DE Page non caricata con errore : "+e.getMessage());
            Assert.fail("Notifiche DE Page non caricata con errore : "+e.getMessage());
        }

    }
}
