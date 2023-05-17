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
            logger.info("Header PA Section caricata");
        }catch (TimeoutException e){
            logger.error("Header PA Section non caricata con errore : "+e.getMessage());
            Assert.fail("Header PA Section non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionaEsciButton(){
        this.js().executeScript("arguments[0].scrollIntoView(true);",this.esciButton);
        this.esciButton.click();
    }
}
