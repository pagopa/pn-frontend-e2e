package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DeleghePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePage");

    @FindBy(id = "add-delegation-button")
    WebElement addDelegaButton;

    @FindBy(id = "revoke-delegation-button")
    WebElement revocaButton;

    @FindBy(id = "show-code-button")
    WebElement mostraCodiceOption;

    @FindBy(id = "dialog-close-button")
    WebElement annullaButton;


    public DeleghePage(WebDriver driver) {
        super(driver);
    }

    public void waitDeleghePage() {
        try {
            By deleghePageTitle = By.id("Deleghe-page");
            this.getWebDriverWait(10).withMessage("Il titolo  della pagina deleghe non è visibile").until(ExpectedConditions.visibilityOfElementLocated(deleghePageTitle));
            this.logger.info("Il titolo o il bottone aggiungi delega è visibile nella pagina aggiungiDeleghe");
        } catch (TimeoutException e) {
            logger.error("Il titolo o il bottone aggiungi delega non è visibile nella pagina aggiungiDeleghe con errore : " + e.getMessage());
            Assert.fail("Il titolo o il bottone aggiungi delega non è visibile nella pagina aggiungiDeleghe con errore : " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton() {

            getWebDriverWait(10).withMessage("Il bottone aggiungi delega non è visualizzato").until(ExpectedConditions.elementToBeClickable(addDelegaButton));
            logger.info("click sul bottone add deleghe");
            WebTool.waitTime(3);
            this.addDelegaButton.click();

    }

    public void controlloCreazioneDelega() {
        try {
            By delegaCreata = By.xpath("//span[contains(text(),'In attesa di conferma')]");
            this.getWebDriverWait(30).withMessage("Il test In attesa di conferma non è visibile").until(ExpectedConditions.visibilityOfElementLocated(delegaCreata));
            this.logger.info("Si visualizza la delega creata");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza la delega creata");
            Assert.fail("Non si visualizza la delega creata");
        }
    }

    public boolean cercaEsistenzaDelega(String nome, String cognome) {
        try {
            By nomeDelegato = By.xpath("//div[@data-testid='delegates-wrapper']//div/p[contains(text(),'" + nome + " " + cognome + "')]");
            this.getWebDriverWait(30).withMessage("Il nome del Delegato non è visibile").until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("nome del delegato non presente con errore" + e.getMessage());
            return false;
        }
    }

    public void clickRevocaButtonOnMenu(String nome, String cognome) {
        By menuDelega = By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + nome + " " + cognome + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']");
        this.getWebDriverWait(10).withMessage("Menù opzioni delega non visualizzato").until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
        this.js().executeScript("arguments[0].click()", this.element(menuDelega));
        this.getWebDriverWait(10).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(this.revocaButton));
        this.revocaButton.click();
    }

    public boolean siVisualizzaUnaDelega() {
        try {
            By menuDelega = By.xpath("//tr[contains(@class,'MuiTableRow-root css-g76qb5')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            logger.info("Trovato correttamente almeno una delega");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Deleghe NON trovate con errore: " + e.getMessage());
            return false;
        }
    }

    public void clickMenuDelegante(String fullName) {
        List<WebElement> tableRows = driver.findElements(By.id("notifications-table")); // Take all the rows of the table
        if (!tableRows.isEmpty()) {
            for (WebElement row : tableRows) {
                WebElement cellName = row.findElement(By.xpath(".//td//p[contains(text(), '" + fullName + "')]")); // Take the cell with the name of the delegate from the row
                getWebDriverWait(10).withMessage("Non è stato trovato la riga corrispondente della delega con il nome: " + fullName).until(ExpectedConditions.visibilityOf(cellName));
                WebElement menuButton = row.findElement(By.xpath(".//button[@data-testid='delegationMenuIcon']")); // take the menu button from the row
                getWebDriverWait(10).withMessage("Non è stato trovato il menu della delega con il nome: " + fullName).until(ExpectedConditions.visibilityOf(menuButton));
                menuButton.click();
                logger.info("Cliccato correttamente su menu delega button");
                return;
            }
        } else {
            logger.error("Non è stato trovato nessun delegato con il nome: " + fullName);
            Assert.fail("Non è stato trovato nessun delegato con il nome: " + fullName);
        }
    }

    public void clickMenuDelegato(String fullName) {
        WebElement tableRows = driver.findElement(By.id("notifications-table")); // Take all the rows of the table
        if (tableRows.isDisplayed()) {
                WebElement cellName = tableRows.findElement(By.xpath(".//td//p[contains(text(), '" + fullName + "')]")); // Take the cell with the name of the delegate from the row
                getWebDriverWait(10).withMessage("Non è stato trovato la riga corrispondente della delega con il nome: " + fullName).until(ExpectedConditions.visibilityOf(cellName));
                WebElement menuButton = tableRows.findElement(By.xpath(".//button[@data-testid='delegationMenuIcon']")); // take the menu button from the row
                getWebDriverWait(10).withMessage("Non è stato trovato il menu della delega con il nome: " + fullName).until(ExpectedConditions.visibilityOf(menuButton));
                menuButton.click();
                logger.info("Cliccato correttamente su menu delega button");
        } else {
            logger.error("Non è stato trovato nessun delegato con il nome: " + fullName);
            Assert.fail("Non è stato trovato nessun delegato con il nome: " + fullName);
        }
    }

    public void siSceglieOpzioneMostraCodice() {
        this.mostraCodiceOption.click();
    }

    public void siCliccaSulBottoneChiudi() {
        try {
            By closeCodiceButtonBy = By.id("code-cancel-button");
            getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(closeCodiceButtonBy));
            logger.info("Il bottone chiudi viene visualizzato correttamente");
            this.element(closeCodiceButtonBy).click();
        } catch (TimeoutException e) {
            logger.error("Il bottone chiudi viene visualizzato NON correttamente con errore:" + e.getMessage());
            Assert.fail("Il bottone chiudi viene visualizzato NON correttamente con errore:" + e.getMessage());
        }
    }

    public void clickOpzioneRevoca() {
        revocaButton.click();
    }

    public void clickMenuPerRifiuto(String nome, String cognome) {
        try {
            By menuDelegheBy = By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + nome + " " + cognome + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']");
            getWebDriverWait(this.loadComponentWaitTime).until(ExpectedConditions.visibilityOfElementLocated(menuDelegheBy));
            logger.info("Si clicca correttamente il menu della delega");
            this.element(menuDelegheBy).click();
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente il menu della delega con errore:" + e.getMessage());
            Assert.fail("Non si visualizza correttamente il menu della delega con errore:" + e.getMessage());
        }
    }

    public void clickRifiuta() {
        try {
            By rifiutaButtonBy = By.id("reject-delegation-button");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(rifiutaButtonBy));
            this.element(rifiutaButtonBy).click();
            logger.info("Si visualizza il bottone rifiuta");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente il bottone rifiuta con errore: " + e.getMessage());
            Assert.fail("Non si visualizza correttamente il bottone rifiuta con errore: " + e.getMessage());
        }
    }

    public void clickRifiutaPopUp() {
        try {
            By rifiutaButtonPopUpBy = By.xpath("//button[contains(text(),'Rifiuta la delega')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(rifiutaButtonPopUpBy));
            this.element(rifiutaButtonPopUpBy).click();
            logger.info("Si visualizza il bottone rifiuta nel pop-up");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente il bottone rifiuta pop-up con errore: " + e.getMessage());
            Assert.fail("Non si visualizza correttamente il bottone rifiuta pop-up errore: " + e.getMessage());
        }
    }

    public void clickAnnullaPopUp() {
        try {
            By annullaButtonPopUpBy = By.xpath("//button[contains(text(),'Annulla')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(annullaButtonPopUpBy));
            this.element(annullaButtonPopUpBy).click();
            logger.info("Si visualizza il bottone Annulla nel pop-up");
        } catch (TimeoutException e) {
            logger.error("Non si clicca correttamente sul bottone Annulla pop-up con errore: " + e.getMessage());
            Assert.fail("Non si clicca correttamente sul bottone Annulla pop-up errore: " + e.getMessage());
        }
    }

    public boolean verificaEsistenzaDelega(String nome, String cognome) {
        try {
            By nomeDelegato = By.xpath("//div[@data-testid='delegators-wrapper']//div/p[contains(text(),'" + nome + " " + cognome + "')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            logger.info("La delega è stata trovata correttamente");
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.info("La delega sta per essere creata");
            return false;
        }
    }

    public boolean siVisualizzaUnaDelegaConNome(String nome, String cognome) {
        try {
            WebTool.waitTime(5);
            By delegaBy = By.xpath("//tr[@id = 'delegatesTable.body.row']//p[contains(text(),'"+ nome +" "+ cognome +"')]");

            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(delegaBy));
            logger.info("Si trova una delega");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Non si trova una delega con il nome " + nome + " " + cognome);
            return false;
        }
    }

    public boolean siVisualizzaUnaDelegaConNomeDelegato(String nome, String cognome) {
        try {
            By delegaBy = By.xpath("//table[@id='notifications-table']//p[contains(text(),'" + nome + " " + cognome + "')]");
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(delegaBy));
            logger.info("Si trova una delega");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Non si trova una delega con il nome " + nome + " " + cognome);
            return false;
        }
    }

    public String vericaStatoDelega() {
        By statoDelegaBy = By.id("chip-status-success");
        getWebDriverWait(10).withMessage("Non si trova nessuno stato delega").until(ExpectedConditions.visibilityOfElementLocated(statoDelegaBy));
        WebElement statoDelega = this.element(statoDelegaBy);
        return statoDelega.getText();
    }


    public void checkModaleMostraCodice() {
        try {
            By titoloModale = By.id("dialog-title");
            By sottotitoloModale = By.id("dialog-description");
            int i = 4;

            getWebDriverWait(10).withMessage("Non si trova il titolo").until(ExpectedConditions.visibilityOfElementLocated(titoloModale));
            getWebDriverWait(10).withMessage("Non si trova il sottotitolo").until(ExpectedConditions.visibilityOfElementLocated(sottotitoloModale));
            while(i>=0){
                getWebDriverWait(10).withMessage("Non si trova codice verifica").until(ExpectedConditions.visibilityOfElementLocated(By.id("code-input-"+i)));
                i--;
            }
        }catch (TimeoutException e ){
            logger.error("modale mostra codice non caricata con errore: " + e.getMessage());
            Assert.fail("modale mostra codice non caricata con errore: " + e.getMessage());
        }
    }
    public void clickAnnullaRevoca(){
        getWebDriverWait(10).withMessage("bottone annulla delega non trovato").until(ExpectedConditions.visibilityOf(annullaButton));
        logger.info("click sul pulsante annulla revoca");

        annullaButton.click();
    }

    public void clickDelegheDelDelegante(String personaFisica) {

            By menuDelegheDelegante = By.id("menu-item" + personaFisica);
            getWebDriverWait(10).withMessage("Voce del menu laterale non visibile e non cliccabile").until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(menuDelegheDelegante),
                    ExpectedConditions.elementToBeClickable(menuDelegheDelegante)
            ));
            element(menuDelegheDelegante).click();
    }
}
