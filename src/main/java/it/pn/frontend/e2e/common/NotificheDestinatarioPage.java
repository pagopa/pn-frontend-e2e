package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NotificheDestinatarioPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(NotificheDestinatarioPage.class);

    @FindBy(id = "iunMatch")
    WebElement codiceIunTextField;
    @FindBy(xpath = "//button[contains(text(),'Ricevuta di consegna PEC')]")
    WebElement ricevutaDiConsegnaButton;

    private WebTool webTool;

    public NotificheDestinatarioPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void inserisciCodiceIUN(String codiceIUN) throws InterruptedException {
        By iunInputLocator = By.id("iunMatch");
        WebElement iunInput = getWebDriverWait(10)
                .withMessage("L'input codice IUN non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(iunInputLocator));

        webTool.waitTime(1);
        iunInput.sendKeys(codiceIUN);
    }


    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        try {
            By codiceIUNBy = By.xpath("//td[contains(text(),'" + codiceIUNInserito + "')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
            logger.info("il codice IUN {} è stato trovato", codiceIUNInserito);
            return true;
        } catch (TimeoutException e) {
            Assertions.fail("Il codice IUN" + codiceIUNInserito + " non è stato trovato con errore:" + e.getMessage());
        }
        return false;
    }

    public boolean isTextBoxInvalid() {
        By iunInputLocator = By.id("iunMatch");
        WebElement iunInput = getWebDriverWait(30)
                .withMessage("L'input codice IUN non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(iunInputLocator));

        String ariaInvalid = iunInput.getAttribute("aria-invalid");
        return "true".equals(ariaInvalid);
    }

    public void checkRicevutaConsegnaCliccabile() {
        logger.info("Controllo esistenza bottone per scaricare zip");

        By ricevutaButtonLocator = By.xpath("//button[contains(text(),'Ricevuta di consegna PEC')]");
        webTool.waitTime(10);

        getWebDriverWait(10)
                .withMessage("Il bottone Ricevuta di consegna non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(ricevutaButtonLocator));

        logger.info("Il bottone Ricevuta di consegna è cliccabile");
    }

}
