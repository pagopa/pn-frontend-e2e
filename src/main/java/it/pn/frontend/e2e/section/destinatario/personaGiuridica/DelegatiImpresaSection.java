package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

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

public class DelegatiImpresaSection extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DelegatiImpresaSection");

    @FindBy(id= "show-code-button")
    WebElement mostraCodiceOption;
    @FindBy(id = "revoke-delegation-button")
    WebElement revocaMenuButton;

    @FindBy(id = "dialog-action-button")
    WebElement revocaButton;

    @FindBy(id = "dialog-close-button")
    WebElement annullaButton;

    public DelegatiImpresaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDelegatiImpresaPage() {
        try {
            By titlePageBy = By.id("tab-1");
            By addDelegheButton = By.id("add-deleghe");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(addDelegheButton));

            logger.info("Delegati dall'impresa caricata correttamente");

        }catch (TimeoutException e){

            logger.error("Delegati dall'impresa non caricata correttamente con errore: "+e.getMessage());
            Assert.fail("Delegati dall'impresa non caricata correttamente con errore: "+e.getMessage());

        }
    }

    public void clickAggiungiDelegaButton() {
        try{
            By addDelegheButton= By.id("add-deleghe");
            this.getWebDriverWait(30).withMessage("bottone aggiunta deleghe non caricato").until(ExpectedConditions.elementToBeClickable(addDelegheButton));
            logger.info("click sul bottone aggiunta delega");
            this.element(addDelegheButton).click();
        }catch (TimeoutException e){
           logger.error("bottone non trovato: "+e.getMessage());
           Assert.fail("bottone non trovato: "+e.getMessage());
        }


    }
    public void controlloCreazioneDelega(String ragioneSociale) {
        try{
            By delegaCreata = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@role='cell']//div/div/span[contains(text(),'In attesa di conferma')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegaCreata));
            this.logger.info("Si visualizza la delega creata");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza la delega creata");
            Assert.fail("Non si visualizza la delega creata");
        }
    }

    public boolean siVisualizzaUnaDelega() {
        try{
            By menuDelega = By.xpath("//tr[contains(@class,'MuiTableRow-root css-g76qb5')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            logger.info("Trovato correttamente almeno una delega");
            return true;
        }catch (TimeoutException e){
            logger.warn("Non trovata alcuna delega");
            return false;
        }
    }

    public void clickMenuDelega(String ragioneSociale) {
        try{
            By menuDelega = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuDelega));
            WebElement menuDelegaWebElement = this.driver.findElement(menuDelega);
            if (menuDelegaWebElement.isDisplayed()){
                this.js().executeScript("arguments[0].click()",menuDelegaWebElement);
            }else {
                this.js().executeScript("arguments[0].scrollIntoView(true);",menuDelegaWebElement);
                this.js().executeScript("arguments[0].click()",menuDelegaWebElement);
            }
            logger.info("cliccato correttamente su menu delega button");
        }catch (TimeoutException e){
            logger.error("Menu delega button NON trovata con errore: "+e.getMessage());
            Assert.fail("Menu delega button NON trovata con errore: "+e.getMessage());
        }
    }

    public void clickMostraCodice() {
        this.mostraCodiceOption.click();
    }
    public void clickRevocaMenuButtonPG(){
        try{
            logger.info("verifica esistenza bottone revoca");
            this.getWebDriverWait(30).withMessage("bottone non trovato").until(ExpectedConditions.elementToBeClickable(this.revocaMenuButton));
            logger.info("click sul bottone revoca");
            this.revocaMenuButton.click();
        }catch(TimeoutException e){
            logger.info("click sul bottone revoca non riuscito");
        }

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
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: "+e.getMessage());
            throw new RuntimeException(e);
        }
        this.revocaButton.click();
    }

    public void clickAnnulla() {
        this.annullaButton.click();
    }
}
