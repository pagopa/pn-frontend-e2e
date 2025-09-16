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

    private final Logger logger = LoggerFactory.getLogger(DelegatiImpresaSection.class);


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

    //    public void waitLoadDelegatiImpresaPage() {
//        try {
//            titlePageBy = driver.findElement(By.id("tab-1"));
//            addDelegheButton = driver.findElement(By.id("add-deleghe"));
//            getWebDriverWait(10).withMessage("il titolo della sezione delegati dall'impresa non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
//            getWebDriverWait(10).withMessage("il bottone aggiungi delega non è visibile").until(ExpectedConditions.visibilityOf(addDelegheButton));
//            logger.info("Delegati dall'impresa caricata correttamente");
//        } catch (TimeoutException e) {
//            Assertions.fail("Delegati dall'impresa non caricata correttamente con errore: " + e.getMessage());
//        }
//    }
    public void waitLoadDelegatiImpresaPage() {
        titlePageBy = getWebDriverWait(10)
                .withMessage("Il titolo della sezione delegati dall'impresa non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("tab-1")));
        addDelegheButton = getWebDriverWait(10)
                .withMessage("Il bottone aggiungi delega non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("add-deleghe")));
        logger.info("Delegati dall'impresa caricata correttamente");
    }


    //    public void clickAggiungiDelegaButton() {
//        try {
//            getWebDriverWait(10).withMessage("bottone aggiunta deleghe non caricato").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("add-deleghe"))));
//            addDelegheButton = driver.findElement(By.id("add-deleghe"));
//            logger.info("click sul bottone aggiunta delega");
//            addDelegheButton.click();
//        } catch (TimeoutException e) {
//            Assertions.fail("bottone non trovato: " + e.getMessage());
//        }
//    }
    public void clickAggiungiDelegaButton() {
        // Attende che il bottone sia cliccabile
        addDelegheButton = getWebDriverWait(10)
                .withMessage("Il bottone aggiungi delega non è caricato")
                .until(ExpectedConditions.elementToBeClickable(By.id("add-deleghe")));

        logger.info("Click sul bottone aggiungi delega");
        addDelegheButton.click();
    }


    public boolean controlloEsistenzaDelega(String ragioneSociale) {
        try {
            getWebDriverWait(10).withMessage("Tabella deleghe non trovata").until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("notifications-table"))
            );

            List<WebElement> nomeDelegati = getWebDriverWait(20).withMessage("Nomi delegati non trovati").until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("delegatesBodyRowDesktop"))
            );

            for (WebElement delegato : nomeDelegati) {
                if (delegato.getText().contains(ragioneSociale)) {
                    logger.info("Delega trovata correttamente per la ragione sociale: {}", ragioneSociale);

                    getWebDriverWait(30).until(ExpectedConditions.textToBePresentInElement(delegato, ragioneSociale));

                    WebElement chipStatus = getWebDriverWait(40).until(
                            ExpectedConditions.visibilityOfElementLocated(By.id("chip-status-warning"))
                    );
                    getWebDriverWait(40).until(ExpectedConditions.textToBePresentInElement(chipStatus, "In attesa di conferma"));

                    logger.info("Si visualizza la delega creata correttamente");
                    return true;
                }
            }
        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("Non si visualizza correttamente la delega creata: " + e.getMessage());
        }
        return false;
    }


    public boolean siVisualizzaUnaDelega() {
        try {
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
            getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(element(By.xpath("//table[@id='notifications-table']//td[p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']"))));
            if (element(By.xpath("//table[@id='notifications-table']//td[p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")).isDisplayed()) {
                js().executeScript("arguments[0].click()", element(By.xpath("//table[@id='notifications-table']//td[p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));
            } else {
                js().executeScript("arguments[0].scrollIntoView(true);", element(By.xpath("//table[@id='notifications-table']//td[p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));
                js().executeScript("arguments[0].click()", element(By.xpath("//table[@id='notifications-table']//td[p[contains(text(),'" + ragioneSociale + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));
            }
            logger.info("cliccato correttamente su menu delega button");
        } catch (TimeoutException e) {
            Assertions.fail("Menu delega button NON trovata con errore: " + e.getMessage());
        }
    }

//    public void verificaRemoveMenuDelega(String displayName, String delegheCarico) {
//        logger.info("DisplayName: {}", displayName);
//        try {
//
//            if (driver.findElement(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + displayName + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")).isDisplayed()) {
//                logger.info("Esiste il duplicato: {}", displayName);
//                js().executeScript("arguments[0].click()", element(By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'" + displayName + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']")));
//
//                if (StringUtils.isEmpty(delegheCarico)) {
//                    logger.info("Deleghe Carico e null");
//
//                    boolean isVisible = isVisibleAttesa();
//                    if (isVisible) {
//                        //bottone remove
//                        getWebDriverWait(30).withMessage("bottone revoca delega  con Deleghe Carico = null non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("revoke-delegation-button"))));
//                        driver.findElement(By.id("revoke-delegation-button")).click();
//                        getWebDriverWait(30).withMessage("bottone rifiuta delega con Deleghe Carico = null non cliccabile pop-up").until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
//                        driver.findElement(By.id("dialog-action-button")).click();
//                    } else {
//                        getWebDriverWait(35).withMessage("bottone rifiuta delega con Deleghe Carico = null non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("reject-delegation-button"))));
//                        driver.findElement(By.id("reject-delegation-button")).click();
//                        //Rifiuta la Delega pop-up
//                        getWebDriverWait(35).withMessage("bottone rifiuta delega con Deleghe Carico = null non cliccabile pop-up").until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
//                        driver.findElement(By.id("dialog-action-button")).click();
//                    }
//
//                } else {
//                    logger.info("DelegheCarico != null");
//                    getWebDriverWait(40).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("revoke-delegation-button"))));
//                    driver.findElement(By.id("revoke-delegation-button")).click();
//                    driver.navigate().refresh();
//                }
//
//                logger.info("Rimosso il duplicato: {}", displayName);
//            }
//        } catch (NoSuchElementException e) {
//            logger.info("L'elemento non esiste. Continuo l'esecuzione.");
//        }
//    }

    public void verificaRemoveMenuDelega(String displayName, String delegheCarico) {
        logger.info("DisplayName: {}", displayName);
        By menuButtonBy = By.xpath("//table[@id='notifications-table']//td[div/p[contains(text(),'"
                + displayName + "')]]/following-sibling::td//button[@data-testid='delegationMenuIcon']");
        try {
            WebElement menuButton = getWebDriverWait(10)
                    .withMessage("Il menu delega per " + displayName + " non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(menuButtonBy));
            if (menuButton.isDisplayed()) {
                logger.info("Esiste il duplicato: {}", displayName);
                js().executeScript("arguments[0].click()", menuButton);
                if (StringUtils.isEmpty(delegheCarico)) {
                    logger.info("Deleghe Carico è null o vuoto");
                    if (isVisibleAttesa()) {
                        // Bottone revoke delega
                        WebElement revokeButton = getWebDriverWait(30)
                                .withMessage("Bottone revoca delega non cliccabile")
                                .until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                        revokeButton.click();

                        WebElement dialogButton = getWebDriverWait(30)
                                .withMessage("Bottone conferma revoca delega non cliccabile")
                                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
                        dialogButton.click();
                    } else {
                        WebElement rejectButton = getWebDriverWait(35)
                                .withMessage("Bottone rifiuta delega non cliccabile")
                                .until(ExpectedConditions.elementToBeClickable(By.id("reject-delegation-button")));
                        rejectButton.click();

                        WebElement dialogButton = getWebDriverWait(35)
                                .withMessage("Bottone conferma rifiuto delega non cliccabile")
                                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-action-button")));
                        dialogButton.click();
                    }
                } else {
                    logger.info("DelegheCarico != null");

                    WebElement revokeButton = getWebDriverWait(40)
                            .withMessage("Bottone revoca delega non cliccabile")
                            .until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                    revokeButton.click();

                    driver.navigate().refresh();
                }

                logger.info("Rimosso il duplicato: {}", displayName);
            }

        } catch (TimeoutException e) {
            logger.info("L'elemento per {} non esiste o non è cliccabile. Continuo l'esecuzione.", displayName);
        }
    }


    public void verificaRemoveMenuDelega() {
        try {
            List<WebElement> menuIcons = getWebDriverWait(10)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@data-testid='delegationMenuIcon']")));

            if (menuIcons.isEmpty()) {
                logger.info("Nessuna delega duplicata trovata, nessuna azione necessaria.");
                return;
            }

            logger.info("Esistono {} duplicati da rimuovere", menuIcons.size());

            for (WebElement menuIcon : menuIcons) {
                try {
                    js().executeScript("arguments[0].click();", menuIcon);

                    // Attesa e click su "Revoca delega"
                    WebElement revokeButton = getWebDriverWait(40)
                            .withMessage("Bottone Revoca Delega non cliccabile")
                            .until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                    revokeButton.click();

                    // Attesa e click sul pulsante di conferma nel pop-up
                    WebElement dialogButton = getWebDriverWait(40)
                            .withMessage("Bottone Revoca la Delega non cliccabile nel Pop-UP")
                            .until(ExpectedConditions.elementToBeClickable(By.id("dialog-confirm-button")));
                    dialogButton.click();

                    // Attendere la scomparsa della delega eliminata
                    getWebDriverWait(40).until(ExpectedConditions.invisibilityOf(revokeButton));

                    logger.info("Duplicato rimosso con successo");
                } catch (TimeoutException e) {
                    Assertions.fail("Timeout durante la rimozione di una delega: " + e.getMessage());
                } catch (StaleElementReferenceException e) {
                    Assertions.fail("Elemento non più valido nel DOM, probabilmente già rimosso: " + e.getMessage());
                }
            }
        } catch (TimeoutException e) {
            logger.info("Non sono presenti i tre puntini per eliminare la delega. Continuo l'esecuzione.");
        } catch (Exception e) {
            Assertions.fail("Errore inaspettato durante la gestione delle deleghe: " + e.getMessage());
        }
    }


    //    public void clickMostraCodice() {
//        menuDelegaButton = driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"));
//        menuDelegaButton.click();
//        mostraCodiceOption = driver.findElement(By.id("show-code-button"));
//        mostraCodiceOption.click();
//    }
    public void clickMostraCodice() {
        menuDelegaButton = getWebDriverWait(10)
                .withMessage("Il menu delega non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='delegationMenuIcon']")));
        menuDelegaButton.click();
        mostraCodiceOption = getWebDriverWait(10)
                .withMessage("L'opzione 'Mostra codice' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("show-code-button")));
        mostraCodiceOption.click();
    }


//    public void clickRevocaMenuButtonPG() {
//        try {
//
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"))));
//            menuDelegaButton = driver.findElement(By.xpath("//button[@data-testid='delegationMenuIcon']"));
//            menuDelegaButton.click();
//            logger.info("verifica esistenza bottone revoca");
//            getWebDriverWait(30).withMessage("bottone non trovato").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("revoke-delegation-button"))));
//            revocaMenuButton = driver.findElement(By.id("revoke-delegation-button"));
//            logger.info("click sul bottone revoca");
//            revocaMenuButton.click();
//        } catch (TimeoutException e) {
//            Assertions.fail("click sul bottone revoca non riuscito");
//        }
//
//    }

    public void clickRevocaMenuButtonPG() {
        menuDelegaButton = getWebDriverWait(30)
                .withMessage("Il menu delega non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='delegationMenuIcon']")));
        menuDelegaButton.click();

        revocaMenuButton = getWebDriverWait(30)
                .withMessage("Bottone Revoca delega non trovato o non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
        revocaMenuButton.click();

        logger.info("Click sul bottone Revoca effettuato con successo");
    }


    //    public void waitPopUpRevoca(String ragionSociale) {
//        try {
//            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("confirmation-dialog-title"))));
//            getWebDriverWait(10).until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("confirmation-dialog-title")), "Vuoi revocare la delega a " + ragionSociale + "?"));
//            logger.info("Il pop-up revoca si visualizza correttamente");
//        } catch (TimeoutException e) {
//            Assertions.fail("Il pop-up revoca NON si visualizza correttamente con errore: " + e.getMessage());
//        }
//    }
    public void waitPopUpRevoca(String ragionSociale) {
        WebElement confirmationTitle = getWebDriverWait(10)
                .withMessage("Il titolo del pop-up revoca non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation-dialog-title")));

        getWebDriverWait(10)
                .withMessage("Il testo del pop-up revoca non corrisponde a: Vuoi revocare la delega a " + ragionSociale + "?")
                .until(ExpectedConditions.textToBePresentInElement(confirmationTitle, "Vuoi revocare la delega a " + ragionSociale + "?"));

        logger.info("Il pop-up revoca si visualizza correttamente");
    }


    //    public void esistenzaRevocaButton() {
//        try {
//            getWebDriverWait(10).withMessage("Bottone di revoca non visualizzato").until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.id("revoke-delegation-button"))), ExpectedConditions.elementToBeClickable(driver.findElement(By.id("revoke-delegation-button")))));
//            revocaMenuButton = driver.findElement(By.id("revoke-delegation-button"));
//            logger.info("Bottone revoca visualizzabile e cliccabile");
//            revocaMenuButton.click();
//            driver.navigate().refresh();
//        } catch (TimeoutException e) {
//            Assertions.fail("Bottone revoca non visualizzabile e non cliccabile con errore: " + e.getMessage());
//        }
//    }
    public void esistenzaRevocaButton() {
        revocaMenuButton = getWebDriverWait(10)
                .withMessage("Bottone di revoca non visualizzato o non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));

        logger.info("Bottone revoca visualizzabile e cliccabile");
        revocaMenuButton.click();
        driver.navigate().refresh();
    }


    //    public void clickRevocaButton() {
//        logger.info("Click su revoca delega");
//        revocaButton = driver.findElement(By.id("dialog-confirm-button"));
//        revocaButton.click();
//    }
    public void clickRevocaButton() {
        logger.info("Click su revoca delega");

        revocaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Revoca' nel pop-up non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-confirm-button")));

        revocaButton.click();
    }


    //    public void clickAnnulla() {
//        annullaButton = driver.findElement(By.id("dialog-close-button"));
//        annullaButton.click();
//    }
    public void clickAnnulla() {
        annullaButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Annulla' nel pop-up non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("dialog-close-button")));

        annullaButton.click();
    }


//    public void checkTabellaDelegheDellImpresa() {
//        try {
//            getWebDriverWait(10).withMessage("tabella deleghe dell impresa non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));
//            getWebDriverWait(10).withMessage("colonna nome non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Nome')]]"))));
//            getWebDriverWait(10).withMessage("colonna inizio delega non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]"))));
//            getWebDriverWait(10).withMessage("colonna fine delega non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Fine delega')]]"))));
//            getWebDriverWait(10).withMessage("colonna permessi non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]"))));
//            getWebDriverWait(10).withMessage("colonna stato non caricata correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]"))));
//            getWebDriverWait(10).withMessage("menu non caricato correttamente").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']"))));
//        } catch (TimeoutException e) {
//            Assertions.fail("tabella delegati dall imprese non caricata correttamente" + e.getMessage());
//        }
//    }

    public void checkTabellaDelegheDellImpresa() {
        getWebDriverWait(10)
                .withMessage("Tabella deleghe dell'impresa non caricata correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("notifications-table")));

        getWebDriverWait(10)
                .withMessage("Colonna 'Nome' non caricata correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Nome')]]")));

        getWebDriverWait(10)
                .withMessage("Colonna 'Inizio delega' non caricata correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Inizio delega')]")));

        getWebDriverWait(10)
                .withMessage("Colonna 'Fine delega' non caricata correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notifications-table']//th[span[contains(text(),'Fine delega')]]")));

        getWebDriverWait(10)
                .withMessage("Colonna 'Permessi' non caricata correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Permessi')]")));

        getWebDriverWait(10)
                .withMessage("Colonna 'Stato' non caricata correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notifications-table']//th[contains(text(),'Stato')]")));

        getWebDriverWait(10)
                .withMessage("Menu non caricato correttamente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notifications-table']//following-sibling::td//button[@data-testid='delegationMenuIcon']")));

        logger.info("Tabella deleghe dell'impresa caricata correttamente");
    }


    //    private boolean isVisibleAttesa() {
//        boolean isVisible;
//        try {
//            isVisible = driver.findElement(By.xpath("//span[contains(text(), 'attesa')]")).isDisplayed();
//        } catch (NoSuchElementException e) {
//            isVisible = false; // L'elemento non esiste
//        }
//        return isVisible;
//    }
    private boolean isVisibleAttesa() {
        try {
            getWebDriverWait(5)
                    .withMessage("L'elemento 'attesa' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'attesa')]")));
            return true;
        } catch (TimeoutException e) {
            return false; // L'elemento non è visibile entro il timeout
        }
    }


}
