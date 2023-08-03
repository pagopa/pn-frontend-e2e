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

public class DelegatiImpresaSection extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DelegatiImpresaSection");

    @FindBy(xpath = "//button[@data-testid='addDeleghe']")
    WebElement addDelegheButton;

    @FindBy(xpath = "//li[contains(@tabindex,'0')]")
    WebElement mostraCodiceOption;

    public DelegatiImpresaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDelegatiImpresaPage() {
        try {
            By titlePageBy = By.xpath("//h6[contains(text(),'Delegati dall')]");
            By addDelegheButton = By.xpath("//button[@data-testid='addDeleghe']");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(addDelegheButton));

            logger.info("Delegati dall'impresa caricata correttamente");

        }catch (TimeoutException e){

            logger.error("Delegati dall'impresa non caricata correttamente con errore: "+e.getMessage());
            Assert.fail("Delegati dall'impresa non caricata correttamente con errore: "+e.getMessage());

        }
    }

    public void clickAggiungiDelegaButton() {

        this.addDelegheButton.click();

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

    public void clickMenuDelega(String ragioneSociale) {
        try{
            By menuDelega = By.xpath("//td[@role='cell' and div/p[contains(text(),'"+ragioneSociale+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            WebElement menuDelegaWebElement = this.driver.findElement(menuDelega);
            menuDelegaWebElement.click();
            logger.info("cliccato correttamente su menu delega button");
        }catch (TimeoutException e){
            logger.error("Menu delega button NON trovata con errore: "+e.getMessage());
            Assert.fail("Menu delega button NON trovata con errore: "+e.getMessage());
        }
    }

    public void clickMostraCodice() {
        this.mostraCodiceOption.click();
    }
}
