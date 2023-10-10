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

    @FindBy(xpath = "//div[@data-testid='sideMenuItem-Notifiche']")
    WebElement notificheDeButton;

    @FindBy(xpath = "//div[@data-testid='sideMenuItem-Gaio Giulio Cesare']")
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
            By tableNotifiche = By.xpath("//table[@data-testid='notificationsTable']");
            this.getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(tableNotifiche));
            logger.info("Notifiche DE Page caricata");
        }catch (TimeoutException e){
            logger.error("Notifiche DE Page non caricata con errore : "+e.getMessage());
            Assert.fail("Notifiche DE Page non caricata con errore : "+e.getMessage());
        }
    }

    public boolean verificaPresenzaCodiceIumTextField() {
        try{
            return codiceIunTextField.isDisplayed();
        }catch (NoSuchElementException e){
            logger.error("text field codice ium non visualizzato");
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
        this.filtraButton.click();
    }



    public String controlloDateInserite(String data) {
        String[] date = data.split("-");
        return date[2]+"/" + date[1]+ "/"+date[0];
    }

    public void inserimentoArcoTemporale(String dataDA, String dataA) {
        this.dataInizioField.sendKeys(dataDA);
        this.dataFineField.sendKeys(dataA);
    }

    public boolean getListData() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
        this.getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        return !this.elements(dataListBy).isEmpty();
    }

    public boolean verificaEsistenzaEPassaggioPagina() {
        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
        try {
            By numeroButtonBy = By.xpath("//button[contains(@aria-label,'pagina 2')]");
            this.getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(numeroButtonBy));
            logger.info("Bottone 2 trovato");

            WebElement numeroPagina = this.element(numeroButtonBy);
            numeroPagina.click();
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public void clickNotificheButton() {this.js().executeScript("arguments[0].click()",this.notificheDeButton);}

    public void clickTueNotificheButton(){
        try {
            By leTueNotificheButtonBy = By.xpath("//div[@data-testid='menu-item(le tue notifiche)']");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(leTueNotificheButtonBy));
            this.js().executeScript("arguments[0].click()",this.element(leTueNotificheButtonBy));
            logger.info("Si clicca sul bottone le tue notifiche");
        }catch (TimeoutException e){
            logger.error("Non si visualizza il bottone le tue notifiche con errore: "+e.getMessage());
            Assert.fail("Non si visualizza il bottone le tue notifiche con errore: "+e.getMessage());
        }
    }

    public void siVisualizzaPaginaNotifichePersonaFisica() {
        try {
            By notifichePageTitleBy = By.xpath("//h4[contains(@id,'Le tue notifiche-page')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notifichePageTitleBy));
            logger.info("La pagina notifiche persona fisica si visualizza correttamente");
        }catch (TimeoutException e){
            logger.error("La pagina notifiche persona fisica NON si visualizza correttamente con errore:"+e.getMessage());
            Assert.fail("La pagina notifiche persona fisica NON si visualizza correttamente con errore:"+e.getMessage());
        }
    }

    public void siVisualizzanoFiltriRicerca() {
        try {
            By filtroCodiceIunBy = By.id("iunMatch");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(filtroCodiceIunBy));
            By filtroDataDaBy = By.id("startDate");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(filtroDataDaBy));
            By filtroDataABy = By.id("endDate");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(filtroDataABy));
            logger.info("Si visualizzano correttamente i filtri ricerca");
        }catch (TimeoutException e){
            logger.error("Non si visualizzano correttamente i filtri ricerca con errore:"+e.getMessage());
            Assert.fail("Non si visualizzano correttamente i filtri ricerca con errore:"+e.getMessage());
        }
    }

    public void siVisualizzaElencoNotifiche() {
        try {
            By elementoDellaListaBy = By.xpath("//tr[contains(@data-testid,'notificationsTable.row')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(elementoDellaListaBy));
            By nomeColonnaDataBy = By.xpath("//th[contains(text(),'Data')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaDataBy));
            By nomeColonnaOggettoBy = By.xpath("//th[contains(text(),'Oggetto')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaOggettoBy));
            By nomeColonnaMittenteBy = By.xpath("//th[contains(text(),'Mittente')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaMittenteBy));
            logger.info("Si visualizza correttamente l'elenco delle notifiche");
        }catch (Exception e){
            logger.error("NON si visualizza correttamente l'elenco delle notifiche con errore:"+e.getMessage());
            Assert.fail("NON si visualizza correttamente l'elenco delle notifiche con errore:"+e.getMessage());
        }
    }

    public void clickNomeDelegante() {
        this.nomeDeleganteButton.click();
    }

    public int siVisualizzaNotifichePresenti() {
        By rigaDelegaBy = By.xpath("//tr[@data-testid='notificationsTable.row']");
        return elements(rigaDelegaBy).size();
    }

    public List<WebElement> getDateNotifiche() {
        By dataCellBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataCellBy));
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
            By paginaSuccessivaBy = By.xpath("//button[contains(@aria-label,'elemento selezionato')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(paginaSuccessivaBy));
            logger.info("Si visualizza una pagina differente dalla precedente");
        }catch (TimeoutException e){
            logger.error("NON si visualizza una");
        }
    }

    public void siSceglieUnaPaginaDiversaConNumero() {
        this.js().executeScript("arguments[0].click()",this.numeroPaginaTreButton);
        //this.numeroPaginaTreButton.click();
    }

    public void modificaNumeroNotifichePagina() {
        waitLoadPage();
        this.numeroPagineButton.click();
        logger.info("Si clicca sul menu per cambiare numero di pagine visualizzate");
    }

    public void numeroDiversoPagine() {
        try {
            By numeroDiversoPagineBy = By.xpath("//li[contains(@data-testid,'pageSize-20')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(numeroDiversoPagineBy));
            this.element(numeroDiversoPagineBy).click();
            logger.info("Cambia il numero di pagine visualizzate");
        }catch (TimeoutException e){
            logger.error("NON cambia il numero di pagine visualizzate con errore:"+e.getMessage());
            Assert.fail("NON cambia il numero di pagine visualizzate con errore:"+e.getMessage());
        }

    }

    public int conteggioNotifiche() {
        By rigaDelegaBy = By.xpath("//tr[@data-testid='notificationsTable.row']");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(rigaDelegaBy));
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
            By tableNotifiche = By.xpath("//table[@data-testid='notificationsTable']");
            this.getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(tableNotifiche));
            logger.info("Notifiche DE Page caricata");
        }catch (TimeoutException e){
            logger.error("Notifiche DE Page non caricata con errore : "+e.getMessage());
            Assert.fail("Notifiche DE Page non caricata con errore : "+e.getMessage());
        }

    }
}
