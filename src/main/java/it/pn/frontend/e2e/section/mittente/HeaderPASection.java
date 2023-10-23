package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
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
            logger.error("Il titolo nel Header: 'Sito di PagoPA S.p.A.' non è caricato con errore : "+e.getMessage());
            Assert.fail("Il titolo nel Header: 'Sito di PagoPA S.p.A.' non è caricato con errore : "+e.getMessage());
        }
    }

    public void selezionaEsciButton() {
        try {
            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(this.esciButton));
            this.js().executeScript("arguments[0].scrollIntoView(true);", this.esciButton);
            this.esciButton.click();
        } catch (TimeoutException | ElementClickInterceptedException e) {
            logger.error("TA_QA: Il bottone esci non è presente o non è cliccabile con errore: " + e.getMessage());
            Assert.fail("TA_QA: Il bottone esci non è presente o non è cliccabile con errore: " + e.getMessage());
        }

    }
}
