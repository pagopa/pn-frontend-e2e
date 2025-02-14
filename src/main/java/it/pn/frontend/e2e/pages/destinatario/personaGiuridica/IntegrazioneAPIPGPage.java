package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.List;

public class IntegrazioneAPIPGPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("IntegrazioneAPIPG");

    private WebTool webTool;

    @FindBy(xpath = "//table[@data-testid='publicKeysTableDesktop']")
    private WebElement chiaviPubblicheTable;

    public IntegrazioneAPIPGPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadIntegrazioneAPIPage() {
        try {
            webTool.waitTime(5);
            getWebDriverWait(10).withMessage("Il titolo della pagina Notifiche PG non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Integrazione API-page"))));
            logger.info("La pagina Piattaforma Integrazione API si carica correttamente");
        } catch (TimeoutException e) {
            Assertions.fail(MessageFormat.format("La pagina Integrazione API non si carica correttamente con errore: {0}", e.getMessage()));
        }
    }

    public boolean generaChiavePubblicaDisplayed() {
        try {
            return getWebDriverWait(5).withMessage("Il bottone Genera chiave pubblica non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("generate-public-key")))).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il bottone Genera chiave pubblica non è visibile");
            return false;
        }
    }

    public void clickBottoneGeneraChiavePubblica() {
        WebElement generaChiavePubblicaButton = getWebDriverWait(10).withMessage("Bottone Genera chiave pubblica non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("generate-public-key"))));
        generaChiavePubblicaButton.click();
    }

    public void checkConfermaCreazioneChiavePubblica() {
        webTool.waitTime(5);
        getWebDriverWait(10).withMessage("Il label Stato per la chiave pubblica non è su 'Attiva' o non è visibile")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@data-testid,'statusChip-Attiva')]"))));
        //TO DO: Attiva se si conferma che il pop up deve essere nella pagina Integrazione API (ora è su Registra Chiave Pubblica -> Controlla i parametri)
        //getWebDriverWait(30).withMessage("Pop up NON visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='alert']/div[text()='Chiave pubblica registrata con successo.']"))));
    }

    public void verificaPresenzaChiavePubblicheCensite() {
        webTool.waitTime(5);
        getWebDriverWait(10).withMessage("Non ci sono chiavi pubbliche censite sulla pagina Integrazione API")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//table[@data-testid='publicKeysTableDesktop']"))));
    }

    public void verificaTabellaChiaviPubbliche () {
        try {
            webTool.waitTime(5);

            getWebDriverWait(10).withMessage("Non si visualizza correttamente la tabella delle chiavi pubbliche censite sulla pagina Integrazione API")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//table[@data-testid='publicKeysTableDesktop']"))));
            chiaviPubblicheTable = element(By.xpath("//table[@data-testid='publicKeysTableDesktop']"));
            // check if the table header is present
            WebElement chiaviPubblicheTableHeader = chiaviPubblicheTable.findElement(By.xpath("//table[@data-testid='publicKeysTableDesktop']//thead[@role='rowgroup']"));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'header della tabella delle chiavi pubbliche")
                    .until(ExpectedConditions.visibilityOf(chiaviPubblicheTableHeader));
            // check if the header titles are present
            List<WebElement> tableHeaderTitles = chiaviPubblicheTableHeader.findElements(By.xpath("//table[@data-testid='publicKeysTableDesktop']//th[@scope='col']"));
            getWebDriverWait(10).withMessage("Non si visualizza correttamente il title dell'header della tabella delle chiave pubbliche")
                    .until(ExpectedConditions.visibilityOfAllElements(tableHeaderTitles));
            // specific check for the header titles of the table
            if (tableHeaderTitles.size() == 5) {
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(0), "Nome"));
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(1), "Valore"));
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(2), "Scadenza"));
                getWebDriverWait(3).until(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(3), "Stato"));
                // controllo su quarta colonna con menu cliccabile
                getWebDriverWait(3).until(ExpectedConditions.and(ExpectedConditions.textToBePresentInElement(tableHeaderTitles.get(4), ""), ExpectedConditions.elementToBeClickable(tableHeaderTitles.get(4))));
            } else {
                Assertions.fail("Non si visualizza correttamente l'header della tabella delle chiavi pubbliche");
            }
            getWebDriverWait(10).withMessage("Non si visualizza correttamente l'header della tabella delle chiavi pubbliche")
                    .until(ExpectedConditions.visibilityOf(chiaviPubblicheTableHeader));
            logger.info("Si visualizza correttamente la tabella delle chiavi pubbliche");
        } catch (TimeoutException e) {
            Assertions.fail("Non si visualizza correttamente la tabella delle chiavi pubbliche con errore" + e.getMessage());
        }
    }

    public void nellaPaginaIntegrazioneAPISiControllaSiaPresenteIlBottoneGeneraChiavePersonale() {
        getWebDriverWait(10).withMessage("Il tasto Genera chiave personale Non presente").until(ExpectedConditions.visibilityOfElementLocated(By.id("generate-virtual-key")));
    }
    public String visualizzaCopiaPublicKey() {
        webTool.waitTime(7);
        WebElement visualizzaCodiceButton = driver.findElement(By.xpath("//li[@data-testid='buttonView']"));
        visualizzaCodiceButton.click();
        webTool.waitTime(7);
        getWebDriverWait(15).withMessage("il bottone copia del campo Chiave Personale non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div[@data-testid='dialog-content']//..//button[@type='button' and @role='button']")).get(0)));
        List<WebElement> formFields = driver.findElements(By.xpath("//div[@data-testid='dialog-content']//..//button[@type='button' and @role='button']"));
        formFields.get(0).click();
        WebElement publicKey = driver.findElement(By.xpath("//input[@aria-invalid='false']"));
        return publicKey.getAttribute("value");
    }

}
