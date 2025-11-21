package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RegistraChiavePubblicaPGSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(RegistraChiavePubblicaPGSection.class);

    @FindBy(id = "name")
    private WebElement inputNome;

    @FindBy(id = "publicKey")
    private WebElement inputPublicKey;

    @FindBy(id = "step-submit")
    WebElement registraButton;

    private WebTool webTool;

    public RegistraChiavePubblicaPGSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadRegistraChiavePubblicaPGPage() {
        getWebDriverWait(10)
                .withMessage("Il titolo della pagina Registra chiave pubblica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Registra chiave pubblica-page")));
        logger.info("La pagina Registra chiave pubblica si carica correttamente");
    }

    public void insertNome(String nome) {
        inputNome = getWebDriverWait(10)
                .withMessage("Il campo nome non è visibile nel metodo insertNome")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
        String dateFormat = df.format(new Date());
        logger.info("Inserimento nome");
        nome = nome + dateFormat;
        inputNome.sendKeys(Keys.chord(Keys.CONTROL, "a"), nome);
    }

    public void insertPublicKey(String publicKey) {
        logger.info("Inserimento chiave pubblica");

        inputPublicKey = getWebDriverWait(10)
                .withMessage("Il campo chiave pubblica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("publicKey")));
        inputPublicKey.sendKeys(publicKey);
    }

    public void insertPublicKey() {
        logger.info("Inserimento chiave pubblica");
        inputPublicKey = getWebDriverWait(10)
                .withMessage("Il campo chiave pubblica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("publicKey")));
        String encodedPublicKey = UUID.randomUUID().toString();
        inputPublicKey.sendKeys(encodedPublicKey);
    }

    public void selezionareRegistraButton() {
        registraButton = getWebDriverWait(10)
                .withMessage("Il bottone Registra nella pagina Registra chiave pubblica non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("step-submit")));
        scrollToElementAndClick(registraButton);
    }

    public void waitLoadOttieniParametriSection() {
        try {
            getWebDriverWait(25)
                    .withMessage("Il titolo della sezione Ottieni parametri non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@data-testid,'title')]")));
            getWebDriverWait(25)
                    .withMessage("Il bottone Kid della sezione Ottieni parametri non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("kid")));
            getWebDriverWait(25)
                    .withMessage("Il campo Issuer della sezione Ottieni parametri non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-invalid='false']")));
            getWebDriverWait(25)
                    .withMessage("Il bottone Fine della sezione Ottieni parametri non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(By.id("step-submit")));
            logger.info("La sezione Ottieni parametri è visualizzata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("La sezione Ottieni parametri non è visualizzata correttamente con errore: " + e.getMessage());
        }

    }

    public String ottieniParametriCopiaKIDRegistraChiavePubblica() {
        By copyButtonLocator = By.xpath("//button[@type='button' and @role='button']");
        By inputLocator = By.xpath("//input[@type='text']");
        // Attendo che ci sia almeno un bottone copia
        List<WebElement> copyButtons = getWebDriverWait(15)
                .withMessage("Nessun bottone copia trovato per il campo KID")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(copyButtonLocator);
                    return elements.isEmpty() ? null : elements;
                });
        WebElement firstCopyButton = copyButtons.get(0);
        firstCopyButton.click();
        // Attendo che ci sia almeno un input disponibile
        List<WebElement> inputFields = getWebDriverWait(10)
                .withMessage("Nessun campo input trovato dopo il click sul bottone copia")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(inputLocator);
                    return elements.isEmpty() ? null : elements;
                });
        String valore = inputFields.get(0).getAttribute("value");
        logger.info("Valore KID ottenuto: {}", valore);
        return valore;
    }

    public String ottieniParametriCopiaIssuerRegistraChiavePubblica() {
        By copyButtonLocator = By.xpath("//button[@type='button' and @role='button']");
        By inputLocator = By.xpath("//input[@type='text']");
        // Attendo che ci sia almeno un bottone copia
        List<WebElement> copyButtons = getWebDriverWait(15)
                .withMessage("Nessun bottone copia trovato per il campo KID")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(copyButtonLocator);
                    return elements.isEmpty() ? null : elements;
                });
        WebElement firstCopyButton = copyButtons.get(1);
        firstCopyButton.click();
        // Attendo che ci sia almeno un input disponibile
        List<WebElement> inputFields = getWebDriverWait(10)
                .withMessage("Nessun campo input trovato dopo il click sul bottone copia")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(inputLocator);
                    return elements.isEmpty() ? null : elements;
                });
        String valore = inputFields.get(1).getAttribute("value");
        logger.info("Valore KID ottenuto: {}", valore);

        return valore;
    }

    public void verificaMessaggioNomeDiErrore(String testo) {
        WebElement errorMessage = getWebDriverWait(15).withMessage("Il messaggio di errone del campo nome: " + testo + " non presente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("name-helper-text")));
        Assertions.assertTrue(errorMessage.getText().contains(testo),
                "Il messaggio di errore del campo Nome non contiene il testo corretto: " + testo);
    }

    public void verificaMessaggioPublicKeyDiErrore(String testo) {
        WebElement errorMessage = getWebDriverWait(15).withMessage("Il messaggio di errone del campo PublicKey: " + testo + " non presente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("publicKey-helper-text")));
        Assertions.assertTrue(errorMessage.getText().contains(testo),
                "Il messaggio di errore del campo PublicKey non contiene il testo corretto: " + testo);
    }

    public void verificaTastoRegistraDisabilitato() {
        WebElement registraButton = getWebDriverWait(25)
                .withMessage("Bottone 'Registra' non presente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("step-submit")));
        Assertions.assertFalse(registraButton.isEnabled(), "Il bottone 'Registra' non è disabilitato");
    }

    public void cliccareSuiTrePuntiniPublicKeyConStato(String testo) {
        logger.info("cliccareSuiTrePuntiniPublicKeyConStato: {}", testo);
        webTool.waitTime(15);
        WebElement menuButton = getWebDriverWait(10).withMessage("Il tasto Tre Puntini NON VISIBILE con stato: " + testo)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@data-testid='publicKeysTableDesktop']//tr[.//span[contains(text(), '" + testo + "')]]//button[@data-testid='contextMenuButton']")));
        menuButton.click();
    }

    public void verificaStato(String stato) {
        getWebDriverWait(10).withMessage("NON VISIBILE con stato: " + stato)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@data-testid='publicKeysTableDesktop']//tr[.//span[contains(text(), '" + stato + "')]]//button[@data-testid='contextMenuButton']")));
    }

    public boolean verificaStatoChiavePersonale(String stato) {
        try {
            return getWebDriverWait(20).withMessage("NON VISIBILE con stato: " + stato)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@data-testid='virtualKeysTableDesktop']//tr[.//span[contains(text(), '" + stato + "')]]//button[@data-testid='contextMenuButton']")))
                    .isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Il bottone Genera chiave personale non è visibile");
            return false;
        }
    }

    public void cliccareSuiTrePuntiniVirtualKeyConStato(String stato) {
        logger.info("cliccareSuiTrePuntiniVirtualKeyConStato: {}", stato);
        WebElement menuButton = getWebDriverWait(5).withMessage("Il tasto Tre Puntini NON VISIBILE con stato: " + stato)
                .until(ExpectedConditions.
                        elementToBeClickable(By.xpath("//table[@data-testid='virtualKeysTableDesktop']//tr[.//span[contains(text(), '" + stato + "')]]//button[@data-testid='contextMenuButton']")));
        menuButton.click();
    }
}
