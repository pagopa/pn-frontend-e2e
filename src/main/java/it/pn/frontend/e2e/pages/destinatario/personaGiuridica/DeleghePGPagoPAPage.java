package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleghePGPagoPAPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePGPagoPAPage");

    @FindBy(xpath = "//button[@data-testid='tab1']")
    WebElement delegatiImpresaButton;

    @FindBy(xpath = "//button[@data-testid='tab2']")
    WebElement delegheCaricoImpresaButton;
    @FindBy(xpath = "//li[contains(text(),'Revoca')]")
    WebElement revocaMenuButton;

    @FindBy(xpath = "//button[@data-testid='dialogAction' and text()='Revoca la delega']")
    WebElement revocaButton;
    
    @FindBy(id = "taxId")
    WebElement cfTextField;

    @FindBy(xpath = "//button[@data-testid='confirmButton']")
    WebElement filtraButton;

    public DeleghePGPagoPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDeleghePage() {
        try{
            By titlePage = By.id("title-of-page");
            By delegheCarico = By.xpath("//button[@data-testid='tab2']");
            By cfFieldBy = By.id("taxId");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegheCarico));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(cfFieldBy));

            this.logger.info("Deleghe page si visualizza correttamente");

        }catch (TimeoutException e){

            this.logger.error("Deleghe page non si visualizza correttamente con errore: "+e.getMessage());
            Assert.fail("Deleghe page non si visualizza correttamente con errore: "+e.getMessage());

        }
    }

    public void clickDelegatiImpresa() {

        this.delegatiImpresaButton.click();

    }

/*    public boolean CercaEsistenzaDelega(String nome,String cognome) {
        try {
            By nomeDelegato = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+nome+" "+cognome+"')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }*/
/*    public void clickRevocaMenuButton(String nome, String cognome) {

        By menuButton = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+nome+" "+cognome+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
        this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuButton));
        this.js().executeScript("arguments[0].click()",this.element(menuButton));
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.revocaMenuButton));
        this.revocaMenuButton.click();
    }*/
    public boolean CercaEsistenzaDelegaPG(String ragioneSociale) {
        try {
            By nomeDelegato = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }
    public void clickRevocaMenuButtonPG(String ragioneSociale) {

        By menuButton = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
        this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuButton));
        this.js().executeScript("arguments[0].click()",this.element(menuButton));
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.revocaMenuButton));
        this.revocaMenuButton.click();
    }
    public void waitPopUpRevoca() {
        try {
            By titlePopUpBy = By.xpath("//h5[contains(text(),'Vuoi revocare la delega ')]");
            
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePopUpBy));

            logger.info("Il pop-up revoca si visualizza correttamente");
        }catch (TimeoutException e){
            logger.error("Il pop-up revoca NON si visualizza correttamente con errore: "+e.getMessage());
            Assert.fail("Il pop-up revoca NON si visualizza correttamente con errore: "+e.getMessage());
        }
    }

    public void clickRevocaButton() {
        this.revocaButton.click();
    }


    public void clickSuDelegheCaricoDellImpresa() {
            this.delegheCaricoImpresaButton.click();
    }

    public void verificaPresenzaElencoDeleghe() {
        try{
            By tableDelegheBy = By.xpath("//table[@data-testid='table(notifications)']");


            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(tableDelegheBy));

            this.logger.info("L'elenco delle deleghe si visualizza correttamente");

        }catch (TimeoutException e){

            this.logger.error("L'elenco delle deleghe non si visualizza correttamente con errore: "+e.getMessage());
            Assert.fail("L'elenco delle deleghe non si visualizza correttamente con errore: "+e.getMessage());

        }
    }

    public void insertCFDelegante(String codiceFiscale) {
        this.cfTextField.sendKeys(codiceFiscale);
    }

    public void clickFiltraButton() {
        this.filtraButton.click();
    }

    public boolean controlloDelegaRestituita(String nome, String cognome) {
          By delegaBy = By.xpath("//p[contains(text(),'"+nome+" "+cognome+"')]");
          this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegaBy));
          return this.elements(delegaBy).size() == 1;

/*        try{
            By delegheBy = By.xpath("//p[contains(text(),'"+nome+" "+cognome+"')]");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegheBy));

            return true;

        }catch (TimeoutException e){
            return false;
        }*/

    }
}
