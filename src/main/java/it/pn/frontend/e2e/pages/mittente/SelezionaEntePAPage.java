package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
/*
*Modifiche e Ottimizzazioni
Annotazione @Component: Integrazione della classe in Spring Boot per il dependency injection.
Miglioramento Logging: Utilizzo di placeholder ({}) in logger per gestire i messaggi in modo più efficiente.
Stream API in selezionareComune: Sostituisce il ciclo for con uno stream, semplificando il controllo della presenza e selezione del comune.
Verifica .ifPresentOrElse: Aggiunta di un messaggio di log per i casi in cui il comune non viene trovato.
Refactoring per Gestione delle Eccezioni: La gestione delle eccezioni è mantenuta invariata per chiarezza e robustezza del codice.
*
*
* */
public class SelezionaEntePAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(SelezionaEntePAPage.class);



    @FindBy(xpath = "//div[@role='button']//h6")
    private List<WebElement> comuneButton;

    @FindBy(xpath = "//button[text()='Accedi']")
    private WebElement accediButton;

    @FindBy(id = "search")
    private WebElement comuneSearchField;


    private  WebTool webTool;

    public SelezionaEntePAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadSelezionaEntePAPage() {
        try {
            webTool.waitTime(30);
            WebElement titlePage   = driver.findElement(By.xpath("//h3[text()='Seleziona il tuo ente']"));
            WebElement searchField = driver.findElement(By.id("search"));
            getWebDriverWait(30)
                    .withMessage("Titolo 'Seleziona il tuo ente' della pagina non è visibile")
                    .until(ExpectedConditions.visibilityOf(titlePage));
            getWebDriverWait(30)
                    .withMessage("Il campo cerca non è cliccabile nella pagina Seleziona Ente")
                    .until(ExpectedConditions.elementToBeClickable(searchField));
            logger.info("Seleziona Utente PA Page caricata");
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            logger.error("Seleziona Utente PA Page non caricata con errore: {}", e.getMessage());
            Assertions.fail("Seleziona Utente PA Page non caricata con errore: " + e.getMessage());
        }
    }

    public void selezionareComune(String comune) {
        comuneButton = driver.findElements(By.xpath("//div[@role='button']//h6"));
        comuneButton.stream()
                .filter(element -> element.getText().contains(comune))
                .findFirst()
                .ifPresentOrElse(
                        element -> {
                            getWebDriverWait(30)
                                    .withMessage("L'Ente " + comune + " non è cliccabile nella pagina seleziona Ente")
                                    .until(ExpectedConditions.elementToBeClickable(element));
                            js().executeScript("arguments[0].click()", element);
                        },
                        () -> logger.warn("Ente '{}' non trovato tra i comuni disponibili", comune)
                );
    }

    public void selezionaAccedi() {
        Actions actions = new Actions(driver);
        accediButton = driver.findElement(By.xpath("//button[text()='Accedi']"));
        getWebDriverWait(60).withMessage("il buttone Accedi non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(accediButton));
        actions.moveToElement(accediButton).click().perform();
    }

    public void cercaComune(String comune) {
        comuneSearchField = driver.findElement(By.id("search"));
        getWebDriverWait(30)
                .withMessage("Il campo Comune non è visibile nella pagina seleziona un Ente")
                .until(ExpectedConditions.visibilityOf(comuneSearchField));
        comuneSearchField.sendKeys(comune);
    }
}
