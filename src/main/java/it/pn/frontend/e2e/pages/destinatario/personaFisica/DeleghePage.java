package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeleghePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePage");

    @FindBy(xpath = "//button[@data-testid ='add-delegation']")
    WebElement addDelegaButton;

    @FindBy(xpath = "//li[@data-testid = 'menuItem-revokeDelegate']")
    WebElement revocaButton;

    @FindBy(xpath = "//li[@data-testid='menuItem-showCode']")
    WebElement mostraCodiceOption;

    @FindBy(xpath = "//li[contains(@data-testid,'menuItem-revokeDelegate')]")
    WebElement revocaDelegaOption;

    public DeleghePage(WebDriver driver) {
        super(driver);
    }

    public void waitDeleghePage() {
        try {
            By deleghePageTitle = By.id("Deleghe-page");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(deleghePageTitle));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.addDelegaButton));
            this.logger.info("Deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Deleghe Page non caricata con errore : " + e.getMessage());
            Assert.fail("Deleghe Page non caricata con errore : " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton()  {
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.addDelegaButton));
        this.addDelegaButton.click();
    }

    public void controlloCreazioneDelega() {
        try{
            By delegaCreata = By.xpath("//span[contains(text(),'In attesa di conferma')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegaCreata));
            this.logger.info("Si visualizza la delega creata");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza la delega creata");
            Assert.fail("Non si visualizza la delega creata");
        }
    }

    public boolean CercaEsistenzaDelega(String nome,String cognome) {
        try {
            By nomeDelegato = By.xpath("//div[@data-testid='delegates-wrapper']//div/p[contains(text(),'"+nome+" "+cognome+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public void clickRevocaButton(String nome, String cognome) {

        By menuButton = By.xpath("//div[@data-testid='delegates-wrapper']//td[@role='cell' and div/p[contains(text(),'"+nome+" "+cognome+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
        this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuButton));
        this.js().executeScript("arguments[0].click()",this.element(menuButton));
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.revocaButton));
        this.revocaButton.click();
    }
    public void siVisualizzaUnaDelega() {
        try{
            By menuDelega = By.xpath("//tr[contains(@class,'MuiTableRow-root css-g76qb5')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            logger.info("Trovato correttamente almeno una delega");
        }catch (TimeoutException e){
            logger.error("Deleghe NON trovate con errore: "+e.getMessage());
            Assert.fail("Deleghe NON trovate con errore: "+e.getMessage());
        }
    }

    public void clickMenuDelega(String nome, String cognome) {
        try{
            By menuDelega = By.xpath("//div[@data-testid='delegates-wrapper']//td[@role='cell' and div/p[contains(text(),'"+nome+" "+cognome+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuDelega));
            WebElement menuDelegaWebElement = this.driver.findElement(menuDelega);
            this.js().executeScript("arguments[0].click()",menuDelegaWebElement);
            logger.info("cliccato correttamente su menu delega button");
        }catch (TimeoutException e){
            logger.error("Menu delega button NON trovata con errore: "+e.getMessage());
            Assert.fail("Menu delega button NON trovata con errore: "+e.getMessage());
        }
    }

    public void siSceglieLOpzioneMostraCodice() {this.mostraCodiceOption.click();}

    public void siCliccaSulBottoneChiudi() {
        try{
            By closeCodiceButtonBy = By.xpath("//button[@data-testid='codeCancelButton']");
            getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(closeCodiceButtonBy));
            logger.info("Il bottone chiudi viene visualizzato correttamente");
            this.element(closeCodiceButtonBy).click();
        }catch (TimeoutException e){
            logger.error("Il bottone chiudi viene visualizzato NON correttamente con errore:"+e.getMessage());
            Assert.fail("Il bottone chiudi viene visualizzato NON correttamente con errore:"+e.getMessage());
        }
    }

    public void clickOpzioneRevoca() {
        this.revocaDelegaOption.click();
    }
}
