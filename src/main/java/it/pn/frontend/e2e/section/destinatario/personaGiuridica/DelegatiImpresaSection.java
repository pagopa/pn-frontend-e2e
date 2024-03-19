package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DelegatiImpresaSection extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DelegatiImpresaSection");

    @FindBy(id = "show-code-button")
    WebElement mostraCodiceOption;

    @FindBy(id = "revoke-delegation-button")
    WebElement revocaMenuButton;

    @FindBy(id = "dialog-action-button")
    WebElement revocaButton;

    @FindBy(id = "dialog-close-button")
    WebElement annullaButton;

    @FindBy(id = "tab-1")
    WebElement titlePageBy;
    @FindBy(id = "add-deleghe")
    WebElement addDelegheButton;
    @FindBy(xpath = "//button[@data-testid='delegationMenuIcon']")
    WebElement menuDelegaButton;

    @FindBy(id = "delegatesBodyRowDesktop")
    List<WebElement> nomeDelegato;

    @FindBy(id = "notifications-table")
    WebElement tabelleDelleDelegheDellImpresa;

    public DelegatiImpresaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDelegatiImpresaPage() {
        try {
            this.getWebDriverWait(10).withMessage("il titolo della sezione delegati dall'impresa non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
            this.getWebDriverWait(10).withMessage("il bottone aggiungi delega non è visibile").until(ExpectedConditions.visibilityOf(addDelegheButton));
            logger.info("Delegati dall'impresa caricata correttamente");
        } catch (TimeoutException e) {
            logger.error("Delegati dall'impresa non caricata correttamente con errore: " + e.getMessage());
            Assert.fail("Delegati dall'impresa non caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton() {
        try {
            this.getWebDriverWait(10).withMessage("bottone aggiunta deleghe non caricato").until(ExpectedConditions.elementToBeClickable(this.addDelegheButton));
            logger.info("click sul bottone aggiunta delega");
            this.addDelegheButton.click();
        } catch (TimeoutException e) {
            logger.error("bottone non trovato: " + e.getMessage());
            Assert.fail("bottone non trovato: " + e.getMessage());
        }
    }

    public void controlloEsistenzaDelega(String ragioneSociale) {
        try {
            getWebDriverWait(10).withMessage("tabella deleghe non trovata").until(ExpectedConditions.visibilityOf(tabelleDelleDelegheDellImpresa));
            getWebDriverWait(10).withMessage("nome delegato non trovato").until(ExpectedConditions.visibilityOfAllElements(nomeDelegato));
            for (WebElement delegato : nomeDelegato) {
                if (delegato.getText().contains(ragioneSociale)) {
                    logger.info("Delega trovata correttamente");
                    this.getWebDriverWait(30).until(ExpectedConditions.textToBePresentInElement(delegato, ragioneSociale));
                    By statusChip = By.id("chip-status-warning");
                    this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statusChip));
                    this.getWebDriverWait(30).until(ExpectedConditions.textToBePresentInElementLocated(statusChip, "In attesa di conferma"));
                }
            }
            logger.info("Si visualizza la delega creata");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la delega creata:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente la delega creata:"+e.getMessage());
        }
    }

    public boolean siVisualizzaUnaDelega() {
        try {
            By menuDelega = By.xpath("//tr[contains(@class,'MuiTableRow-root css-g76qb5')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            logger.info("Trovato correttamente almeno una delega");
            return true;
        } catch (TimeoutException e) {
            logger.info("Non trovata alcuna delega");
            return false;
        }
    }

    public void clickMenuDelega(String ragioneSociale) {
        try {
            By menuDelega = By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']");
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuDelega));
            WebElement menuDelegaWebElement = this.driver.findElement(menuDelega);
            if (menuDelegaWebElement.isDisplayed()) {
                this.js().executeScript("arguments[0].click()", menuDelegaWebElement);
            } else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", menuDelegaWebElement);
                this.js().executeScript("arguments[0].click()", menuDelegaWebElement);
            }
            logger.info("cliccato correttamente su menu delega button");
        } catch (TimeoutException e) {
            logger.error("Menu delega button NON trovata con errore: " + e.getMessage());
            Assert.fail("Menu delega button NON trovata con errore: " + e.getMessage());
        }
    }

    public void clickMostraCodice() {
        this.menuDelegaButton.click();
        this.mostraCodiceOption.click();
    }

    public void clickRevocaMenuButtonPG() {
        try {
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.menuDelegaButton));
            this.menuDelegaButton.click();
            logger.info("verifica esistenza bottone revoca");
            this.getWebDriverWait(30).withMessage("bottone non trovato").until(ExpectedConditions.elementToBeClickable(this.revocaMenuButton));
            logger.info("click sul bottone revoca");
            this.revocaMenuButton.click();
        } catch (TimeoutException e) {
            logger.error("click sul bottone revoca non riuscito");
            Assert.fail("click sul bottone revoca non riuscito");
        }

    }

    public void waitPopUpRevoca(String ragionSociale) {
        try {
            By titlePopUpBy = By.id("dialog-title");
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(titlePopUpBy));
            this.getWebDriverWait(10).until(ExpectedConditions.textToBe(titlePopUpBy, "Vuoi revocare la delega a " + ragionSociale + "?"));
            logger.info("Il pop-up revoca si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il pop-up revoca NON si visualizza correttamente con errore: " + e.getMessage());
            Assert.fail("Il pop-up revoca NON si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void esistenzaRevocaButton() {
        try{
            this.getWebDriverWait(10).withMessage("Bottone di revoca non visualizzato").until(ExpectedConditions.and(ExpectedConditions.visibilityOf(this.revocaMenuButton), ExpectedConditions.elementToBeClickable(this.revocaMenuButton)));
            logger.info("Bottone revoca visualizzato e cliccabile");
            Actions actions = new Actions(driver);
            actions.moveByOffset(100, 200).click().perform();
        } catch(TimeoutException e){
            logger.error("Bottone revoca non visualizzato e non cliccabile con errore: " + e.getMessage());
            Assert.fail("Bottone revoca non visualizzato e non cliccabile con errore: " + e.getMessage());
        }
    }

    //analizzare metodo ridontante con quello di riga 106
    public void clickRevocaButton() {
        logger.info("Click su revoca delega");
        this.revocaButton.click();
    }

    public void clickAnnulla() {
        this.annullaButton.click();
    }

    public void checkTabellaDelegheDellImpresa() {
        By menuDelega = By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']");
        By colonnaNome = By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Nome')]]");
        By colonnaInizioDelega = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]");
        By colonnaFineDelega = By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Fine delega')]]");
        By colonnaPermessi = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]");
        By colonnaStato = By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]");
        try {
        getWebDriverWait(10).withMessage("tabella deleghe dell impresa non caricata correttamente").until(ExpectedConditions.visibilityOf(tabelleDelleDelegheDellImpresa));
        getWebDriverWait(10).withMessage("colonna nome non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaNome));
        getWebDriverWait(10).withMessage("colonna inizio delega non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaInizioDelega));
        getWebDriverWait(10).withMessage("colonna fine delega non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaFineDelega));
        getWebDriverWait(10).withMessage("colonna permessi non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaPermessi));
        getWebDriverWait(10).withMessage("colonna stato non caricata correttamente").until(ExpectedConditions.visibilityOfElementLocated(colonnaStato));
        getWebDriverWait(10).withMessage("menu non caricato correttamente").until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
    }catch (TimeoutException e){
            logger.error("tabella delegati dall imprese non caricata correttamente" + e.getMessage());
            Assert.fail("tabella delegati dall imprese non caricata correttamente" + e.getMessage());
        }
    }

}
