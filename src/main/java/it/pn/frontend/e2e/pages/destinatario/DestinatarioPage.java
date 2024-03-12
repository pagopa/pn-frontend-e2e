package it.pn.frontend.e2e.pages.destinatario;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DestinatarioPage extends BasePage {

    //Questa classe è utilizzata per metodi in comune tra PF e PG

    private final Logger logger = LoggerFactory.getLogger("DestinatarioPage");

    public DestinatarioPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "startDate")
    WebElement dataInizioField;
    @FindBy(id = "endDate")
    WebElement dataFineField;

    public void inserimentoDataErrato() {
        String data = "01/01/1111";
        this.getWebDriverWait(10).withMessage("Il campo data inizio non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataInizioField));
        this.dataInizioField.click();
        this.dataInizioField.sendKeys(data);
        this.getWebDriverWait(3).withMessage("Il valore della data che si vuole inserire non corrisponde").until(ExpectedConditions.attributeToBe(this.dataInizioField, "value", data));
        this.getWebDriverWait(10).withMessage("Il campo data fine non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataFineField));
        this.dataFineField.click();
        this.dataFineField.sendKeys(data);
        this.getWebDriverWait(3).withMessage("Il valore della data che si vuole inserire non corrisponde").until(ExpectedConditions.attributeToBe(this.dataFineField, "value", data));
    }

    public void isDateBoxInvalid(){
        final String isTextboxInvalid = "true";
        try {
            getWebDriverWait(10).withMessage("Il campo data inizio non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataInizioField, this.dataFineField));
            String ariaInvalidInizio = dataInizioField.getAttribute("aria-invalid");
            String ariaInvalidFine = dataFineField.getAttribute("aria-invalid");
            if(isTextboxInvalid.equals(ariaInvalidInizio) || isTextboxInvalid.equals(ariaInvalidFine)){
                logger.info("Almeno un campo data è in stato invalido");
            }else{
                logger.error("Nessuno dei campi data è passato allo stato invalido");
                Assert.fail("Nessuno dei campi data è passato allo stato invalido");
            }
        } catch (TimeoutException e){
            logger.error("Campi data non visualizzati correttamente con errore: " + e.getMessage());
            Assert.fail("Campi data non visualizzati correttamente con errore: " + e.getMessage());
        }

    }


}
