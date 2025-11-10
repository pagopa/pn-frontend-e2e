package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class DeleghePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(DeleghePage.class);


    @Autowired
    private WebDriverConfig webDriverConfig;

    @FindBy(id = "add-delegation-button")
    WebElement addDelegaButton;

    @FindBy(id = "revoke-delegation-button")
    WebElement revocaButton;

    @FindBy(id = "show-code-button")
    WebElement mostraCodiceOption;

    @FindBy(id = "dialog-close-button")
    WebElement annullaButton;


    private WebTool webTool;

    public DeleghePage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitDeleghePage() {
        getWebDriverWait(10)
                .withMessage("Il titolo della pagina deleghe non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Deleghe-page")));

        logger.info("Il titolo o il bottone aggiungi delega è visibile nella pagina aggiungiDeleghe");
    }


    public void clickAggiungiDelegaButton() {
        WebElement aggiungiDelegaButton = getWebDriverWait(30)
                .withMessage("Il bottone aggiungi delega non è visualizzato o non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("add-delegation-button")));

        aggiungiDelegaButton.click();

    }

    public void controlloCreazioneDelega() {
        webTool.waitTime(30);

        getWebDriverWait(30)
                .withMessage("Il test 'In attesa di conferma' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'In attesa di conferma')]")));

        logger.info("Si visualizza la delega creata");
    }

    public boolean cercaEsistenzaDelega(String nome, String cognome) {
        List<WebElement> delegati = driver.findElements(
                By.xpath("//div[@data-testid='delegates-wrapper']//div/p[contains(text(),'" + nome + " " + cognome + "')]")
        );

        if (delegati.isEmpty()) {
            logger.info("Nome del delegato non presente: {} {}", nome, cognome);
            return false;
        }

        getWebDriverWait(30)
                .withMessage("Il nome del delegato non è visibile")
                .until(ExpectedConditions.visibilityOf(delegati.get(0)));

        return true;
    }

    public void clickRevocaButtonOnMenu(String nome, String cognome) {
        // Trova il pulsante del menu delega per il delegato specificato
        By menuButtonBy = By.xpath(
                "//table[@id='notifications-table']//td[div/p[contains(text(),'" + nome + " " + cognome + "')]]" +
                        "/following-sibling::td//button[@data-testid='delegationMenuIcon']"
        );

        WebElement menuButton = getWebDriverWait(10)
                .withMessage("Menù opzioni delega non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(menuButtonBy));

        js().executeScript("arguments[0].click()", menuButton);

        // Attende che il pulsante revoca sia cliccabile e cliccalo
        By revokeButtonBy = By.id("revoke-delegation-button");
        revocaButton = getWebDriverWait(10)
                .withMessage("Bottone revoca delega non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(revokeButtonBy));

        revocaButton.click();
    }

    public void clickMenuDelegante(String fullName) {
        try {
            // Trova la riga della tabella corrispondente al delegato
            By rowBy = By.xpath("//table[@id='notifications-table']//td//p[contains(text(), '" + fullName + "')]/ancestor::tr");
            WebElement row = getWebDriverWait(10)
                    .withMessage("Non è stata trovata la riga della delega con il nome: " + fullName)
                    .until(ExpectedConditions.visibilityOfElementLocated(rowBy));

            // Trova il pulsante del menu nella riga trovata
            WebElement menuButton = row.findElement(By.xpath(".//button[@data-testid='delegationMenuIcon']"));
            getWebDriverWait(10)
                    .withMessage("Non è stato trovato il menu della delega con il nome: " + fullName)
                    .until(ExpectedConditions.elementToBeClickable(menuButton));

            // Click sul pulsante
            js().executeScript("arguments[0].click()", menuButton);
            logger.info("Cliccato correttamente su menu delega button per: {}", fullName);

        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("Errore nel cliccare sul menu delega per " + fullName + ": " + e.getMessage());
        }
    }

    public void clickMenuDelegato(String fullName) {
        try {
            // Trova la riga della tabella corrispondente al delegato
            By rowBy = By.xpath("//table[@id='notifications-table']//td//p[contains(text(), '" + fullName + "')]/ancestor::tr");
            WebElement row = getWebDriverWait(10)
                    .withMessage("Non è stata trovata la riga della delega con il nome: " + fullName)
                    .until(ExpectedConditions.visibilityOfElementLocated(rowBy));

            // Trova il pulsante del menu nella riga trovata
            WebElement menuButton = row.findElement(By.xpath(".//button[@data-testid='delegationMenuIcon']"));
            getWebDriverWait(10)
                    .withMessage("Non è stato trovato il menu della delega con il nome: " + fullName)
                    .until(ExpectedConditions.elementToBeClickable(menuButton));

            // Click sicuro tramite JS
            js().executeScript("arguments[0].click()", menuButton);
            logger.info("Cliccato correttamente su menu delega button per: {}", fullName);

        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("Errore nel cliccare sul menu delega per " + fullName + ": " + e.getMessage());
        }
    }

    public void siSceglieOpzioneMostraCodice() {
        // Attesa che il pulsante sia visibile e cliccabile
        mostraCodiceOption = getWebDriverWait(10)
                .withMessage("Il pulsante 'Mostra codice' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("show-code-button")));

        try {
            // Click "normale"
            mostraCodiceOption.click();
        } catch (ElementClickInterceptedException e) {
            // Se il click normale fallisce (ad esempio overlay o scroll), usiamo JavaScript
            js().executeScript("arguments[0].click();", mostraCodiceOption);
        }

        logger.info("Opzione 'Mostra codice' selezionata correttamente");
    }

    public void siCliccaSulBottoneChiudi() {
        WebElement closeCodiceButtonBy = getWebDriverWait(40)
                .withMessage("Il bottone 'Chiudi' non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("code-cancel-button")));

        closeCodiceButtonBy.click();
        logger.info("Il bottone 'Chiudi' è stato cliccato correttamente");
    }

    public void clickOpzioneRevoca() {
        WebElement clickOpzioneRevoca = getWebDriverWait(20)
                .withMessage("Il pulsante 'Revoca' con id 'revoke-delegation-button' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));

        clickOpzioneRevoca.click();

    }

    public void clickMenuPerRifiuto(String nome, String cognome) {
        WebElement menuButton = getWebDriverWait(200)
                .withMessage("Impossibile trovare i tre puntini per selezionare il rifiuto")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//tr[.//p[contains(text(), 'Gaio Giulio Cesare')]]//button[@data-testid='delegationMenuIcon']"))
                );
        menuButton.click();

    }

    public void clickRifiuta() {
        WebElement rifiutaButtonBy = getWebDriverWait(30)
                .withMessage("Non trovato bottone 'Rifiuta'")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("reject-delegation-button")));
        rifiutaButtonBy.click();
    }

    public void clickRifiutaPopUp() {
        WebElement rifiutaButtonPopUp = getWebDriverWait(30)
                .withMessage("Il bottone 'Rifiuta la delega' nel pop-up non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Rifiuta la delega')]")));

        rifiutaButtonPopUp.click();
        logger.info("Si visualizza correttamente il bottone 'Rifiuta' nel pop-up");
    }

    public void clickAnnullaPopUp() {
        WebElement annullaButtonPopUp = getWebDriverWait(30)
                .withMessage("Il bottone 'Annulla' nel pop-up non è visibile o cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Annulla')]")));

        annullaButtonPopUp.click();
        logger.info("Si visualizza correttamente il bottone 'Annulla' nel pop-up");
    }

    public boolean verificaEsistenzaDelega(String nome, String cognome) {
        try {
            By delegaLocator = By.xpath("//div[@data-testid='delegators-wrapper']//div/p[contains(text(),'" + nome + " " + cognome + "')]");
            getWebDriverWait(30) // attende fino a 10 secondi
                    .withMessage("La delega per " + nome + " " + cognome + " non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(delegaLocator));
            logger.info("La delega è stata trovata correttamente");
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.info("La delega sta per essere creata");
            return false;
        }
    }

    public boolean siVisualizzaUnaDelegaConNome(String nome, String cognome) {
        try {
            webTool.waitTime(5);
            By delegaLocator = By.xpath("//tr[@id='delegatesTable.body.row']//p[contains(text(),'" + nome + " " + cognome + "')]");
            getWebDriverWait(10)
                    .withMessage("Non si trova una delega con il nome " + nome + " " + cognome)
                    .until(ExpectedConditions.visibilityOfElementLocated(delegaLocator));
            logger.info("Si trova una delega");
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.warn("Non si trova una delega con il nome " + nome + " " + cognome);
            return false;
        }
    }

    public boolean siVisualizzaUnaDelegaConNomeDelegato(String nome, String cognome) {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notifications-table']//p[contains(text(),'" + nome + " " + cognome + "')]")));
            logger.info("Si trova una delega");
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.warn("Non si trova una delega con il nome " + nome + " " + cognome);
            return false;
        }
    }

    public String vericaStatoDelega() {
        By statoDelegaLocator = By.id("chip-status-success");
        WebElement statoDelega = getWebDriverWait(10)
                .withMessage("Non si trova nessuno stato delega")
                .until(ExpectedConditions.visibilityOfElementLocated(statoDelegaLocator));
        return statoDelega.getText();
    }

    public void checkModaleMostraCodice() {
        try {
            WebElement titoloModale = driver.findElement(By.id("dialog-title"));
            WebElement sottotitoloModale = driver.findElement(By.id("dialog-description"));
            int i = 5;

            getWebDriverWait(10).withMessage("Non si trova il titolo").until(ExpectedConditions.visibilityOf(titoloModale));
            getWebDriverWait(10).withMessage("Non si trova il sottotitolo").until(ExpectedConditions.visibilityOf(sottotitoloModale));
            while (i >= 1) {
                getWebDriverWait(10).withMessage("Non si trova codice verifica").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@data-testid='dialog-content']//input//..//span)[" + i + "]"))));
                i--;
            }
        } catch (TimeoutException e) {
            Assertions.fail("modale mostra codice non caricata con errore: " + e.getMessage());
        }
    }

    public void clickAnnullaRevoca() {
        By annullaButtonLocator = By.id("dialog-close-button");
        WebElement annullaButton = getWebDriverWait(10)
                .withMessage("bottone annulla delega non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(annullaButtonLocator));
        logger.info("click sul pulsante annulla revoca");
        annullaButton.click();
    }

    public void clickDelegheDelDelegante(String personaFisica) {

        webTool.waitTime(1);
        By menuLocator = By.id("menu-item" + personaFisica.toLowerCase());

        WebElement menuDelegheDelegante = getWebDriverWait(20)
                .withMessage("Voce del menu laterale non visibile e non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(menuLocator));
        menuDelegheDelegante.click();
    }

    public void selezionaPGRadioButton(String portale) {

        String xpath;
        if (portale.equalsIgnoreCase("PF"))
            xpath = "//label[@id='recipent-pg']";
        else
            xpath = "//label[@id='select-pg']";

        WebElement labelElement = getWebDriverWait(10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        labelElement.click();
    }

    public void verificaMessaggioErroreDeleghe(String messaggio) {

        WebElement messaggioErrore = getWebDriverWait(5)
                .withMessage("Il messaggio di errore sul Codice Fiscale non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("codiceFiscale-helper-text")));

        String testoErrore = messaggioErrore.getText();

        Assertions.assertTrue(testoErrore.contains(messaggio), "Messaggio atteso non trovato. Messaggio rilevato: " + testoErrore);
    }


    public void nellaSezioneLeTueDelegheVerificaEsistenzaNomeECognomiErrati(String nomeCompletoAtteso, String persona) {
        String xpath= "//tr[@data-testid='delegatesBodyRowDesktop']";
        if(persona.equalsIgnoreCase("PF"))
            xpath="//tr[@data-testid='delegatesTable.body.row']";
        List<WebElement> righe = driver.findElements(By.xpath(xpath));

        boolean trovato = righe.stream()
                .map(r -> r.findElement(By.xpath(".//td[1]//p")).getText().trim())
                .anyMatch(nome -> nome.equalsIgnoreCase(nomeCompletoAtteso));

        Assertions.assertTrue(trovato, "Nome '" + nomeCompletoAtteso + "' NON trovato nella tabella.");
    }

    public void rimuoviDelegatiPF() {
        List<WebElement> delegati = driver.findElements(By.id("delegatesTable.body.row")); // Take all the rows of the table
        if (!delegati.isEmpty()) {
            for (WebElement row : delegati) {
                WebElement menuButton = row.findElement(By.xpath(".//button[@data-testid='delegationMenuIcon']")); // take the menu button from the row
                menuButton.click();
                logger.info("Cliccato correttamente su menu delega button");
                getWebDriverWait(10).withMessage("bottone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(By.id("revoke-delegation-button")));
                revocaButton = driver.findElement(By.id("revoke-delegation-button"));
                revocaButton.click();
                WebElement revocaDialogAction = driver.findElement(By.id("dialog-confirm-button"));
                getWebDriverWait(10).withMessage("Non è possibile cliccare il bottone").until(ExpectedConditions.elementToBeClickable(revocaDialogAction));
                logger.info("click revoca delega");
                revocaDialogAction.click();
            }
        } else {
            logger.info("Non è stato trovato nessun delegato");
        }

    }
}
