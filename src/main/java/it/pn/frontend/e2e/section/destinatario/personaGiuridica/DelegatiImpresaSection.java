package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
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

    private WebTool webTool;

    public DelegatiImpresaSection(WebDriver driver) {

        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadDelegatiImpresaPage() {
        try {
            titlePageBy = driver.findElement(By.id("tab-1"));
            addDelegheButton = driver.findElement(By.id("add-deleghe"));
            getWebDriverWait(10).withMessage("il titolo della sezione delegati dall'impresa non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
            getWebDriverWait(10).withMessage("il bottone aggiungi delega non è visibile").until(ExpectedConditions.visibilityOf(addDelegheButton));
            logger.info("Delegati dall'impresa caricata correttamente");
        } catch (TimeoutException e) {
            logger.error("Delegati dall'impresa non caricata correttamente con errore: " + e.getMessage());
            Assertions.fail("Delegati dall'impresa non caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton() {
        try {
            addDelegheButton = driver.findElement(By.id("add-deleghe"));
            getWebDriverWait(10).withMessage("bottone aggiunta deleghe non caricato").until(ExpectedConditions.elementToBeClickable(addDelegheButton));
            logger.info("click sul bottone aggiunta delega");
            addDelegheButton.click();
        } catch (TimeoutException e) {
            logger.error("bottone non trovato: " + e.getMessage());
            Assertions.fail("bottone non trovato: " + e.getMessage());
        }
    }

    public void controlloEsistenzaDelega(String ragioneSociale) {
        try {
            tabelleDelleDelegheDellImpresa = driver.findElement(By.id("notifications-table"));
            nomeDelegato = driver.findElements(By.id("delegatesBodyRowDesktop"));
            getWebDriverWait(10).withMessage("tabella deleghe non trovata").until(ExpectedConditions.visibilityOf(tabelleDelleDelegheDellImpresa));
            getWebDriverWait(10).withMessage("nome delegato non trovato").until(ExpectedConditions.visibilityOfAllElements(nomeDelegato));
            for (WebElement delegato : nomeDelegato) {
                if (delegato.getText().contains(ragioneSociale)) {
                    logger.info("Delega trovata correttamente");
                    logger.info("Delega trovata correttamente" +ragioneSociale);
                    getWebDriverWait(30).until(ExpectedConditions.textToBePresentInElement(delegato, ragioneSociale));
                    getWebDriverWait(40).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("chip-status-warning"))));
                    getWebDriverWait(40).until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("chip-status-warning")), "In attesa di conferma"));
                }
            }
            logger.info("Si visualizza la delega creata");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza correttamente la delega creata:" + e.getMessage());
            Assertions.fail("Non si visualizza correttamente la delega creata:" + e.getMessage());
        }
    }

    public boolean siVisualizzaUnaDelega() {
        try {
           // WebElement menuDelega = driver.findElement(By.xpath("//tr[contains(@class,'MuiTableRow-root css-g76qb5')]"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[contains(@class,'MuiTableRow-root css-g76qb5')]")));
            logger.info("Trovato correttamente almeno una delega");
            return true;
        } catch (TimeoutException e) {
            logger.info("Non trovata alcuna delega");
            return false;
        }
    }

    public void clickMenuDelega(String ragioneSociale) {
        try {
           //WebElement menuDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']"));
            getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(element(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']"))));
            if (element(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")).isDisplayed()) {
                js().executeScript("arguments[0].click()", element(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));
            } else {
                js().executeScript("arguments[0].scrollIntoView(true);", element(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));
                js().executeScript("arguments[0].click()", element(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));
            }
            logger.info("cliccato correttamente su menu delega button");
        } catch (TimeoutException e) {
            logger.error("Menu delega button NON trovata con errore: " + e.getMessage());
            Assertions.fail("Menu delega button NON trovata con errore: " + e.getMessage());
        }
    }

    public void verificaRemoveMenuDelega(String displayName,String delegheCarico) {
        logger.info("DisplayName: " + displayName);
        try {
            if (driver.findElement(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + displayName + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")).isDisplayed()) {
                logger.info("Esiste il duplicato: " + displayName);
                js().executeScript("arguments[0].click()", element(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + displayName + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));

                if(StringUtils.isEmpty(delegheCarico)){
                    getWebDriverWait(30).withMessage("bottone rifiuta delega non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("reject-delegation-button")));
                    driver.findElement(By.id("reject-delegation-button")).click();
                    //Rifiuta la Delega pop-up
                    getWebDriverWait(30).withMessage("bottone rifiuta delega non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
                    driver.findElement(By.id("dialog-action-button")).click();

                }

                else{
                    logger.info("DelegheCarico != null");
                    // Bottone rifiuta revoke-delegation-button
                    getWebDriverWait(30).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                    driver.findElement(By.id("revoke-delegation-button")).click();
                    driver.navigate().refresh();


                }

                logger.info("Rimosso il duplicato: " + displayName);
            }
        } catch (NoSuchElementException e) {
            System.out.println("L'elemento non esiste. Continuo l'esecuzione.");
        }
    }

    public void verificaRemoveMenuDelega() {
        try {
            if (driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']")).isDisplayed()) {
                logger.info("Esiste il duplicato: ");
                js().executeScript("arguments[0].click()", element(By.xpath("//button[@data-testid='delegationMenuIcon']")));

                // Bottone rifiuta revoke-delegation-button
                getWebDriverWait(30).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                driver.findElement(By.id("revoke-delegation-button")).click();
                // Bottone rifiuta revoke-delegation-button
                getWebDriverWait(30).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
                driver.findElement(By.id("dialog-action-button")).click();
                driver.navigate().refresh();


                logger.info("Rimosso il duplicato: ");
            }
        } catch (NoSuchElementException e) {
            System.out.println("L'elemento non esiste. Continuo l'esecuzione.");
        }
    }

    public void clickMostraCodice() {
        menuDelegaButton = driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"));
        menuDelegaButton.click();
        mostraCodiceOption = driver.findElement(By.id("show-code-button"));
        mostraCodiceOption.click();
    }

    public void clickRevocaMenuButtonPG() {
        try {
            menuDelegaButton = driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(menuDelegaButton));
            menuDelegaButton.click();
            logger.info("verifica esistenza bottone revoca");
            revocaMenuButton = driver.findElement(By.id("revoke-delegation-button"));
            getWebDriverWait(30).withMessage("bottone non trovato").until(ExpectedConditions.elementToBeClickable(revocaMenuButton));
            logger.info("click sul bottone revoca");
            revocaMenuButton.click();
        } catch (TimeoutException e) {
            logger.error("click sul bottone revoca non riuscito");
            Assertions.fail("click sul bottone revoca non riuscito");
        }

    }

    public void waitPopUpRevoca(String ragionSociale) {
        try {
            WebElement titlePopUpBy = driver.findElement(By.id("dialog-title"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(titlePopUpBy));
            getWebDriverWait(10).until(ExpectedConditions.textToBePresentInElement(titlePopUpBy, "Vuoi revocare la delega a " + ragionSociale + "?"));
            logger.info("Il pop-up revoca si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il pop-up revoca NON si visualizza correttamente con errore: " + e.getMessage());
            Assertions.fail("Il pop-up revoca NON si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void esistenzaRevocaButton() {
        try {
            revocaMenuButton = driver.findElement(By.id("revoke-delegation-button"));
            getWebDriverWait(10).withMessage("Bottone di revoca non visualizzato").until(ExpectedConditions.and(ExpectedConditions.visibilityOf(revocaMenuButton), ExpectedConditions.elementToBeClickable(revocaMenuButton)));
            logger.info("Bottone revoca visualizzabile e cliccabile");
            revocaMenuButton.click();
            driver.navigate().refresh();
        } catch (TimeoutException e) {
            logger.error("Bottone revoca non visualizzabile e non cliccabile con errore: " + e.getMessage());
            Assertions.fail("Bottone revoca non visualizzabile e non cliccabile con errore: " + e.getMessage());
        }
    }

    //analizzare metodo ridontante con quello di riga 106
    public void clickRevocaButton() {
        logger.info("Click su revoca delega");
        revocaButton = driver.findElement(By.id("dialog-action-button"));
        revocaButton.click();
    }

    public void clickAnnulla() {
        annullaButton = driver.findElement(By.id("dialog-close-button"));
        annullaButton.click();
    }

    public void checkTabellaDelegheDellImpresa() {
        WebElement menuDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']"));
        WebElement colonnaNome = driver.findElement(By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Nome')]]"));
        WebElement colonnaInizioDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]"));
        WebElement colonnaFineDelega = driver.findElement(By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Fine delega')]]"));
        WebElement colonnaPermessi = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]"));
        WebElement colonnaStato = driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]"));
        try {
            tabelleDelleDelegheDellImpresa = driver.findElement(By.id("notifications-table"));
            getWebDriverWait(10).withMessage("tabella deleghe dell impresa non caricata correttamente").until(ExpectedConditions.visibilityOf(tabelleDelleDelegheDellImpresa));
            getWebDriverWait(10).withMessage("colonna nome non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaNome));
            getWebDriverWait(10).withMessage("colonna inizio delega non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaInizioDelega));
            getWebDriverWait(10).withMessage("colonna fine delega non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaFineDelega));
            getWebDriverWait(10).withMessage("colonna permessi non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaPermessi));
            getWebDriverWait(10).withMessage("colonna stato non caricata correttamente").until(ExpectedConditions.visibilityOf(colonnaStato));
            getWebDriverWait(10).withMessage("menu non caricato correttamente").until(ExpectedConditions.visibilityOf(menuDelega));
        } catch (TimeoutException e) {
            logger.error("tabella delegati dall imprese non caricata correttamente" + e.getMessage());
            Assertions.fail("tabella delegati dall imprese non caricata correttamente" + e.getMessage());
        }
    }

}
