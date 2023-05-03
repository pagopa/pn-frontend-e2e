package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class PiattaformaNotifichePAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("notificaMittentePagoPA");

    @FindBy(xpath = "//input[contains(@id,'recipientId') and contains(@name,'recipientId')]")
    WebElement cfTextField;

    @FindBy(xpath = "//button[contains(text(),'Filtra')]")
    WebElement filtraButton;

    @FindBy(id = "iunMatch")
    WebElement codiceIUNTextField;

    @FindBy(id = "startDate")
    WebElement dataInizioField;

    @FindBy(id = "endDate")
    WebElement dataFineField;

    @FindBy(xpath = "//div[contains(@id,'status')]")
    WebElement statoNotificaField;


    public PiattaformaNotifichePAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPiattaformaNotifichePAPage(){
        try{
            By notificheTitle = By.cssSelector("h4.MuiTypography-root.MuiTypography-h4");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheTitle));
            logger.info("Piattaforma Notifiche Page caricata");
        }catch (TimeoutException e){
            logger.error("Piattaforma Notifiche Page non caricata con errore : "+e.getMessage());
            Assert.fail("Piattaforma Notifiche Page non caricata con errore : "+e.getMessage());
        }
    }

    public void insertCodiceFiscale(String codiceFiscale){
        this.cfTextField.sendKeys(codiceFiscale);
    }

    public void selectFiltraButton() {
        this.filtraButton.click();
    }

    public int getListaCf(String cfInserito) {
        By cfListBy = By.xpath("//p[contains(text(),'"+cfInserito+"')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(cfListBy));
        logger.info("Codici fiscali trovati correttamente");

        return this.elements(cfListBy).size();
    }

    private void attesaCaricamentoPagina() {
        try {
            TimeUnit.SECONDS.sleep(5);
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

    public void inserimentoCodiceIUN(String codiceIUN) {
        this.codiceIUNTextField.sendKeys(codiceIUN);
    }

    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        WebElement codiceIUN = getCodiceIUN(codiceIUNInserito);
        return codiceIUN != null;
    }

    private WebElement getCodiceIUN(String codiceIUNInserito) {
        try {
            By codiceIUNBy = By.xpath("//button[contains(text(),'"+codiceIUNInserito+"')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
            return this.element(codiceIUNBy);
        }catch (TimeoutException e){
            return null;
        }
    }

    public boolean controlloDateInserite(String dataInerita) {
        String[] date = dataInerita.split("/");
        return date[0].length() == 2 && date[1].length() ==2 && date[2].length() == 4;
    }

    public void inserimentoArcoTemporale(String da, String a) {
        this.dataInizioField.sendKeys(da);
        this.dataFineField.sendKeys(a);
    }

    public int getListDate() {
        By dataListBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-c3sxw2')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(dataListBy));
        logger.info("Date trovate correttamente");

        return this.elements(dataListBy).size() ;
    }

    public void selezionareStatoNotifica(String statoInserito) {
        this.statoNotificaField.click();
        try{
            By statoNotificaBy = By.xpath("//ul[@role='listbox']/li[@role='option' and div/span[contains(text(),'"+statoInserito+"')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
            this.element(statoNotificaBy).click();
            logger.info("Stato notifica selezionato correttamente");
        }catch (TimeoutException e){
            logger.error("Stato notifica NON trovata con errore: "+e.getMessage());
            Assert.fail("Stato notifica NON trovata con errore: "+e.getMessage());
        }
    }

    public int getListStato(String statoNotifica){
        By statoNotificaBy = By.xpath("//div[contains(@data-testid,'statusChip-"+statoNotifica+"')]");
        attesaCaricamentoPagina();
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
        logger.info("Stati notifiche trovati correttamente");
        return this.elements(statoNotificaBy).size();
    }
}
