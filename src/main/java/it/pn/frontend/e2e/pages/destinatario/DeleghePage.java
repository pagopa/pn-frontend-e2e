package it.pn.frontend.e2e.pages.destinatario;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleghePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePage");

    @FindBy(xpath = "//button[contains(text(),'Aggiungi una delega')]")
    WebElement addDelegaButton;

    @FindBy(xpath = "//li[contains(@data-testid,'menuItem-revokeDelegate')]")
    WebElement revocaButton;
    public DeleghePage(WebDriver driver) {
        super(driver);
    }

    public void waitDeleghePage() {
        try {
            By deleghePageTitle = By.xpath("//h4[contains(text(),'Deleghe')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(deleghePageTitle));
            this.logger.info("Deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Deleghe Page non caricata con errore : " + e.getMessage());
            Assert.fail("Deleghe Page non caricata con errore : " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton()  {
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.addDelegaButton));
        this.addDelegaButton.click();
    }


    public void waitNuovaDelegaSection() {
        try {
            By letuedeleghePageTitle = By.xpath("//h3[@id ='title-of-page']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(letuedeleghePageTitle));
            this.logger.info("Le tue deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Le tue deleghe page non caricata con errore :" + e.getMessage());
            Assert.fail(("Le tue deleghe page non caricata con errore :" + e.getMessage()));
        }
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
}
