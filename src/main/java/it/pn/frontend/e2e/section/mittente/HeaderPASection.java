package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HeaderPASection");

    @FindBy(css = "button[title='Esci']")
    WebElement esciButton;

    public HeaderPASection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadHeaderSection(){
        try{
            By titleLabel = By.cssSelector("a[title='Sito di PagoPA S.p.A.']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            logger.info("TA_QA: Header PA Section caricata");
        }catch (TimeoutException e){
            logger.error("TA_QA: Il titolo nel Header: 'Sito di PagoPA S.p.A.' non è caricato con errore : "+e.getMessage());
            Assert.fail("TA_QA: Il titolo nel Header: 'Sito di PagoPA S.p.A.' non è caricato con errore : "+e.getMessage());
        }
    }

    public void selezionaEsciButton(){
        getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(this.esciButton));
        this.js().executeScript("arguments[0].scrollIntoView(true);",this.esciButton);
        this.esciButton.click();
    }
}
