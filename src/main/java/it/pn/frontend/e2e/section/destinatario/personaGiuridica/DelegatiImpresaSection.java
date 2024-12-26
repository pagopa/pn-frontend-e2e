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
            getWebDriverWait(10).withMessage("il titolo della sezione delegati dall'impresa non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("tab-1"))));
            getWebDriverWait(10).withMessage("il bottone aggiungi delega non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("add-deleghe"))));
            titlePageBy = driver.findElement(By.id("tab-1"));
            addDelegheButton = driver.findElement(By.id("add-deleghe"));
            logger.info("Delegati dall'impresa caricata correttamente");
        } catch (TimeoutException e) {
            logger.error("Delegati dall'impresa non caricata correttamente con errore: " + e.getMessage());
            Assertions.fail("Delegati dall'impresa non caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton() {
        try {
            getWebDriverWait(15).withMessage("bottone aggiunta deleghe non caricato").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("add-deleghe"))));
            addDelegheButton = driver.findElement(By.id("add-deleghe"));
            logger.info("click sul bottone aggiunta delega");
            addDelegheButton.click();
        } catch (TimeoutException e) {
            logger.error("bottone non trovato: " + e.getMessage());
            Assertions.fail("bottone non trovato: " + e.getMessage());
        }
    }

    public void controlloEsistenzaDelega(String ragioneSociale) {
        try {
         //   tabelleDelleDelegheDellImpresa = driver.findElement(By.id("notifications-table"));
         //   nomeDelegato = driver.findElements(By.id("delegatesBodyRowDesktop"));
            getWebDriverWait(10).withMessage("tabella deleghe non trovata").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));
            getWebDriverWait(10).withMessage("nome delegato non trovato").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id("delegatesBodyRowDesktop"))));
            nomeDelegato = driver.findElements(By.id("delegatesBodyRowDesktop"));
            for (WebElement delegato : nomeDelegato) {
                if (delegato.getText().contains(ragioneSociale)) {
                    logger.info("Delega trovata correttamente");
                    logger.info("Delega trovata correttamente" + ragioneSociale);
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

    public void verificaRemoveMenuDelega(String displayName, String delegheCarico) {
        logger.info("DisplayName: " + displayName);
        try {
            webTool.waitTime(15);
            if (driver.findElement(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + displayName + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")).isDisplayed()) {
                logger.info("Esiste il duplicato: " + displayName);
                js().executeScript("arguments[0].click()", element(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + displayName + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));

                if (StringUtils.isEmpty(delegheCarico)) {
                    logger.info("Deleghe Carico e null");

                    boolean isVisible = isVisibleAttesa();
                    if(isVisible){
                        //bottone remove
                        getWebDriverWait(40).withMessage("bottone revoca delega  con Deleghe Carico = null non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                        driver.findElement(By.id("revoke-delegation-button")).click();
                        getWebDriverWait(40).withMessage("bottone rifiuta delega con Deleghe Carico = null non cliccabile pop-up").until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
                        driver.findElement(By.id("dialog-action-button")).click();
                    }

                    else {
                        getWebDriverWait(40).withMessage("bottone rifiuta delega con Deleghe Carico = null non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("reject-delegation-button"))));
                        driver.findElement(By.id("reject-delegation-button")).click();
                        //Rifiuta la Delega pop-up
                        getWebDriverWait(40).withMessage("bottone rifiuta delega con Deleghe Carico = null non cliccabile pop-up").until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
                        driver.findElement(By.id("dialog-action-button")).click();
                    }

                } else {
                    logger.info("DelegheCarico != null");
                    getWebDriverWait(40).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable( driver.findElement(By.id("revoke-delegation-button"))));
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
                logger.info("Esiste il duplicato");
                WebElement menuIcon = driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"));
                js().executeScript("arguments[0].click();", menuIcon);
                WebElement revokeButton = getWebDriverWait(40).withMessage("Bottone revoca delega non cliccabile")
                        .until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                revokeButton.click();
                WebElement dialogButton = getWebDriverWait(40).withMessage("Bottone dialog-action-button non cliccabile")
                        .until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
                dialogButton.click();
                driver.navigate().refresh();
                logger.info("Rimosso il duplicato");
            }
        } catch (NoSuchElementException e) {
            logger.info("L'elemento non esiste. Continuo l'esecuzione.");
        } catch (TimeoutException e) {
            logger.warn("Timeout raggiunto durante l'attesa di un elemento: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            logger.error("Elemento non valido o DOM modificato: " + e.getMessage());
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

            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"))));
            menuDelegaButton = driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"));
            menuDelegaButton.click();
            logger.info("verifica esistenza bottone revoca");
            getWebDriverWait(30).withMessage("bottone non trovato").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("revoke-delegation-button"))));
            revocaMenuButton = driver.findElement(By.id("revoke-delegation-button"));
            logger.info("click sul bottone revoca");
            revocaMenuButton.click();
        } catch (TimeoutException e) {
            logger.error("click sul bottone revoca non riuscito");
            Assertions.fail("click sul bottone revoca non riuscito");
        }

    }

    public void waitPopUpRevoca(String ragionSociale) {
        try {
           // WebElement titlePopUpBy = driver.findElement(By.id("dialog-title"));
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dialog-title"))));
            getWebDriverWait(10).until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("dialog-title")), "Vuoi revocare la delega a " + ragionSociale + "?"));
            logger.info("Il pop-up revoca si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il pop-up revoca NON si visualizza correttamente con errore: " + e.getMessage());
            Assertions.fail("Il pop-up revoca NON si visualizza correttamente con errore: " + e.getMessage());
        }
    }

    public void esistenzaRevocaButton() {
        try {
            getWebDriverWait(10).withMessage("Bottone di revoca non visualizzato").until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.id("revoke-delegation-button"))), ExpectedConditions.elementToBeClickable(driver.findElement(By.id("revoke-delegation-button")))));
            revocaMenuButton = driver.findElement(By.id("revoke-delegation-button"));
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
            //tabelleDelleDelegheDellImpresa = driver.findElement(By.id("notifications-table"));
            getWebDriverWait(10).withMessage("tabella deleghe dell impresa non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));
            getWebDriverWait(10).withMessage("colonna nome non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Nome')]]"))));
            getWebDriverWait(10).withMessage("colonna inizio delega non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]"))));
            getWebDriverWait(10).withMessage("colonna fine delega non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Fine delega')]]"))));
            getWebDriverWait(10).withMessage("colonna permessi non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]"))));
            getWebDriverWait(10).withMessage("colonna stato non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]"))));
            getWebDriverWait(10).withMessage("menu non caricato correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']"))));
        } catch (TimeoutException e) {
            logger.error("tabella delegati dall imprese non caricata correttamente" + e.getMessage());
            Assertions.fail("tabella delegati dall imprese non caricata correttamente" + e.getMessage());
        }
    }


    private boolean isVisibleAttesa() {
        boolean isVisible;
        try {
            isVisible = driver.findElement(By.xpath("//span[contains(text(), 'attesa')]")).isDisplayed();
        } catch (NoSuchElementException e) {
            isVisible = false; // L'elemento non esiste
        }
        return isVisible;
    }

}
