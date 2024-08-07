package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificheDestinatarioPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger("NotificheDestinatarioPage");

    @FindBy(id = "iunMatch")
    WebElement codiceIunTextField;
    @FindBy(xpath = "//button[contains(text(),'Ricevuta di consegna PEC')]")
    WebElement ricevutaDiConsegnaButton;


    public NotificheDestinatarioPage(WebDriver driver) {
        super(driver);
    }

    public void inserisciCodiceIUN(String codiceIUN) throws InterruptedException {
        getWebDriverWait(10).withMessage("L'input codice IUN non è visibile").until(ExpectedConditions.visibilityOf(this.codiceIunTextField));
        WebTool.waitTime(1);
        codiceIunTextField.sendKeys(codiceIUN);
    }
    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        try {
            By codiceIUNBy = By.xpath("//button[contains(text(),'"+codiceIUNInserito+"')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
            logger.info("il codice IUN "+codiceIUNInserito+" è stato trovato");
            return true;
        }catch (TimeoutException e){
            logger.error("Il codice IUN"+codiceIUNInserito+" non è stato trovato con errore:"+e.getMessage());
            Assert.fail("Il codice IUN"+codiceIUNInserito+" non è stato trovato con errore:"+e.getMessage());
        }
        return false;
    }

    public boolean isTextBoxInvalid(){
        getWebDriverWait(30).withMessage("L'input codice IUN non è visibile").until(ExpectedConditions.visibilityOf(codiceIunTextField));
        String ariaInvalid = codiceIunTextField.getAttribute("aria-invalid");
        final String isTextboxInvalid = "true";
        return isTextboxInvalid.equals(ariaInvalid);
    }

    public void checkRicevutaConsegnaCliccabile() {
        logger.info("controllo esistenza bottone per scaricare zip");
        getWebDriverWait(10).withMessage("Il bottone Ricevuta di consegna non cliccabile").until(ExpectedConditions.elementToBeClickable(ricevutaDiConsegnaButton));
        logger.info("Il bottone Ricevuta di consegna non cliccabile");
    }

}
