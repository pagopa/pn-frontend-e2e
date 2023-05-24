package it.pn.frontend.e2e.pages.destinatario;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class NotificheDEPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("NotificheDEPage");

    @FindBy(id = "iunMatch")
    WebElement codiceIunTextField;

    @FindBy(xpath = "//button[contains(text(),'Filtra')]")
    WebElement filtraButton;

    @FindBy(id = "startDate")
    WebElement dataInizioField;

    @FindBy(id = "endDate")
    WebElement dataFineField;

    @FindBy(xpath = "//div[contains(@data-testid,'sideMenuItem-Notifiche')]")
    WebElement notificheDeButton;

    public NotificheDEPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadNotificheDEPage() {
        try{
            By titleLabel = By.cssSelector("h4.MuiTypography-root.MuiTypography-h4");
            By tableNotifiche = By.cssSelector("table[aria-label='Tabella di item']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(tableNotifiche));
            logger.info("Notifiche DE Page non caricata");
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
            //By buttonDelegeBy = By.xpath("//div[contains(@data-testid,'sideMenuItem-Deleghe')]");
            By buttonDelegeBy = By.xpath("//div[contains(@class,'MuiButtonBase-root MuiListItemButton-root MuiListItemButton-gutters MuiListItemButton-root MuiListItemButton-gutters')][2]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(buttonDelegeBy));
            WebElement buttonDelegheWebElement = this.driver.findElement(buttonDelegeBy);
            buttonDelegheWebElement.click();
            logger.info("cliccato correttamente su delega button");
        }catch (TimeoutException e){
            logger.error("Delega button NON trovata con errore: "+e.getMessage());
            Assert.fail("Delega button NON trovata con errore: "+e.getMessage());
        }
    }

    public void inserisciCodiceIUN(String codiceIUN) {
        this.codiceIunTextField.sendKeys(codiceIUN);
    }

    public void selectFiltraButton() {
        this.filtraButton.click();
    }

    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        try {
            By codiceIUNBy = By.xpath("//button[contains(text(),'"+codiceIUNInserito+"')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
            return true;
        }catch (TimeoutException e){
            logger.error("Il codice IUN"+codiceIUNInserito+" non è stato trovato con errore:"+e.getMessage());
            Assert.fail("Il codice IUN"+codiceIUNInserito+" non è stato trovato con errore:"+e.getMessage());
        }
        return false;
    }

    public boolean controlloDateInserite(String data) {
        String[] date = data.split("/");
        return date[0].length() == 2 && date[1].length()==2 && date[2].length() == 4;
    }

    public void inserimentoArcoTemporale(String dataDA, String dataA) {
        this.dataInizioField.sendKeys(dataDA);
        this.dataFineField.sendKeys(dataA);
    }

    public boolean getListData() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        return this.elements(dataListBy).size() != 0;
    }
    private void attesaCaricamentoPagina() {
        try {                                   // Questo sleep l'abbiamo messo perché
            TimeUnit.SECONDS.sleep(5);   // il sito ci mette un pò a caricarsi
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public void clickNotificheButton() {this.notificheDeButton.click();}

    public void siVisualizzaPaginaNotificheDestinatario() {
        try {
            By notifichePageTitleBy = By.xpath("//h4[contains(@id,'title-of-page')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notifichePageTitleBy));
            logger.info("La pagina notifiche destinatario si visualizza correttamente");
        }catch (TimeoutException e){
            logger.error("La pagina notifiche destinatario NON si visualizza correttamente con errore:"+e.getMessage());
            Assert.fail("La pagina notifiche destinatario NON si visualizza correttamente con errore:"+e.getMessage());
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
            By elementoDellaListaBy = By.xpath("//tr[contains(@data-testid,'table(notifications).row')]");
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
}
