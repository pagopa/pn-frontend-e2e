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

    //    public void waitLoadRegistraChiavePubblicaPGPage() {
//        try {
//            getWebDriverWait(10).withMessage("Il titolo della pagina Registra chiave pubblica non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Registra chiave pubblica-page"))));
//            logger.info("La pagina Registra chiave pubblica si carica correttamente");
//        } catch (TimeoutException e) {
//            Assertions.fail(MessageFormat.format("La pagina Registra chiave pubblica non si carica correttamente con errore: {0}", e.getMessage()));
//        }
//    }
    public void waitLoadRegistraChiavePubblicaPGPage() {
        getWebDriverWait(10)
                .withMessage("Il titolo della pagina Registra chiave pubblica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Registra chiave pubblica-page")));

        logger.info("La pagina Registra chiave pubblica si carica correttamente");
    }


    //    public void insertNome(String nome) {
//        inputNome = driver.findElement(By.id("name"));
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
//        String dateFormat = df.format(new Date());
//        logger.info("inserimento nome");
//        nome = nome + dateFormat;
//        inputNome.sendKeys(Keys.chord(Keys.CONTROL, "a"), nome);
//    }
    public void insertNome(String nome) {
        inputNome = getWebDriverWait(10)
                .withMessage("Il campo nome non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
        String dateFormat = df.format(new Date());
        logger.info("Inserimento nome");
        nome = nome + dateFormat;

        inputNome.sendKeys(Keys.chord(Keys.CONTROL, "a"), nome);
    }


    //    public void insertPublicKey(String publicKey) {
//        logger.info("inserimento chiave pubblica");
//        inputPublicKey = driver.findElement(By.id("publicKey"));
//        inputPublicKey.sendKeys(publicKey);
//    }
    public void insertPublicKey(String publicKey) {
        logger.info("Inserimento chiave pubblica");

        inputPublicKey = getWebDriverWait(10)
                .withMessage("Il campo chiave pubblica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("publicKey")));

        inputPublicKey.sendKeys(publicKey);
    }


    //    public void insertPublicKey() {
//        logger.info("inserimento chiave pubblica");
//        inputPublicKey = driver.findElement(By.id("publicKey"));
//        String encodedPublicKey = UUID.randomUUID().toString();
//        inputPublicKey.sendKeys(encodedPublicKey);
//    }
    public void insertPublicKey() {
        logger.info("Inserimento chiave pubblica");

        inputPublicKey = getWebDriverWait(10)
                .withMessage("Il campo chiave pubblica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("publicKey")));

        String encodedPublicKey = UUID.randomUUID().toString();
        inputPublicKey.sendKeys(encodedPublicKey);
    }


    //    public void selezionareRegistraButton() {
//        registraButton = getWebDriverWait(10).withMessage("Il bottone Registra nella pagina Registra chiave pubblica non è cliccabile")
//                .until(ExpectedConditions.elementToBeClickable(By.id("step-submit")));
//        scrollToElementAndClick(registraButton);
//    }
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

    //    public String ottieniParametriCopiaKIDRegistraChiavePubblica() {
//        try {
//            String copyButtonXPath = "//button[@type='button' and @role='button']";
//            getWebDriverWait(15).withMessage("il bottone copia del campo KID non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath(copyButtonXPath)).get(0)));
//            List<WebElement> formFields = driver.findElements(By.xpath(copyButtonXPath));
//            formFields.get(0).click();
//            // Chrome webdriver non riesce a estrarre il valore dal pulsante di copia, pertanto si prenderà il valore dal campo di input collegato a esso
//
//            List<WebElement> inputFormField = driver.findElements(By.xpath("//input[@type='text']"));
//            return inputFormField.get(0).getAttribute("value");
//        } catch (TimeoutException e) {
//            Assertions.fail("Reference sul campo del form Registra chiave pubblica non valida. Deve essere compreso 0 o 1");
//            return null;
//        }
//    }
    public String ottieniParametriCopiaKIDRegistraChiavePubblica() {
        // Attesa che tutti i pulsanti copia siano visibili
        List<WebElement> copyButtons = getWebDriverWait(15)
                .withMessage("Il bottone copia del campo KID non è cliccabile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@type='button' and @role='button']")));

        // Clic sul primo pulsante copia
        copyButtons.get(0).click();

        // Attesa che tutti i campi input di testo siano visibili
        List<WebElement> inputFields = getWebDriverWait(15)
                .withMessage("Il campo input collegato al KID non è visibile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='text']")));

        // Restituisci il valore del primo campo input
        return inputFields.get(0).getAttribute("value");
    }


    //    public String ottieniParametriCopiaIssuerRegistraChiavePubblica() {
//        try {
//            String copyButtonXPath = "//button[@type='button' and @role='button']";
//            getWebDriverWait(15).withMessage("il bottone copia del campo Issuer non è cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath(copyButtonXPath)).get(1)));
//            List<WebElement> formFields = driver.findElements(By.xpath(copyButtonXPath));
//            formFields.get(1).click();
//            // Chrome webdriver non riesce a estrarre il valore dal pulsante di copia, pertanto si prenderà il valore dal campo di input collegato a esso
//
//            List<WebElement> inputFormField = driver.findElements(By.xpath("//input[@type='text']"));
//            return inputFormField.get(1).getAttribute("value");
//        } catch (TimeoutException e) {
//            Assertions.fail("Reference sul campo del form Registra chiave pubblica non valida. Deve essere compreso 0 o 1");
//            return null;
//        }
//    }
    public String ottieniParametriCopiaIssuerRegistraChiavePubblica() {
        List<WebElement> copyButtons = getWebDriverWait(15)
                .withMessage("Il bottone copia del campo Issuer non è cliccabile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@type='button' and @role='button']")));
        copyButtons.get(1).click();
        List<WebElement> inputFields = getWebDriverWait(15)
                .withMessage("Il campo input collegato all'Issuer non è visibile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='text']")));
        return inputFields.get(1).getAttribute("value");
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

    //    public void verificaTastoRegistraDisabilitato() {
//        boolean bottoneRegistraNonPresente = getWebDriverWait(25).withMessage("Bottone registra non presente")
//                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("step-submit")))).isEnabled();
//        Assertions.assertFalse(bottoneRegistraNonPresente, "Il bottone 'Registra' non è disabilitato");
//    }
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
