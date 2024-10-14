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

public class SelezionaEntePAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("SelezionaEntePAPage");

    @FindBy(xpath = "//div[@role='button']//h6")
    List<WebElement> comuneButton;

    @FindBy(xpath = "//button[text()='Accedi']")
    WebElement accediButton;

    @FindBy(id = "search")
    WebElement comuneSearchField;


    public SelezionaEntePAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadSelezionaEntePAPage() {
        try {
            WebTool.waitTime(20);
            logger.info("HTML..: "+driver.getPageSource());
            WebElement titlePage = driver.findElement(By.xpath("//h3[text()='Seleziona il tuo ente']"));
            WebElement searchField =  driver.findElement(By.id("search"));
            this.getWebDriverWait(30).withMessage("Titolo 'Seleziona il tuo ente' della pagina non è visualizza").until(ExpectedConditions.visibilityOf(titlePage));
            this.getWebDriverWait(30).withMessage("Il campo cerca non è cliccabile nella pagina Seleziona Ente: ").until(ExpectedConditions.elementToBeClickable(searchField));
            logger.info("Seleziona Utente PA Page caricata");
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            logger.error("Seleziona Utente PA Page non caricata con errore : " + e.getMessage());
            Assertions.fail("Seleziona Utente PA Page non caricata con errore : " + e.getMessage());
        }
    }


    public void selezionareComune(String comune) {
        for (WebElement element : this.comuneButton) {
            if (element.getText().contains(comune)) {
                getWebDriverWait(30).withMessage("L'Ente " + comune + " non è cliccabile nella pagina seleziona Ente ")
                        .until(ExpectedConditions.elementToBeClickable(element));
                this.js().executeScript("arguments[0].click()", element);
                break;
            }
        }
    }

    public void selezionaAccedi() {
        Actions actions = new Actions(driver);
        getWebDriverWait(60).withMessage("il buttone Eccedi non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(this.accediButton));
        actions.moveToElement(this.accediButton).click().perform();
    }

    public void cercaComune(String comune) {
        this.getWebDriverWait(30).withMessage("Il campo Comune non è visibile nella pagina seleziona un Ente")
                .until(ExpectedConditions.visibilityOf(this.comuneSearchField));
        this.comuneSearchField.sendKeys(comune);
    }
}
