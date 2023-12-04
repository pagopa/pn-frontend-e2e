package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

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

public class HeaderPGSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HeaderPGSection");

    public HeaderPGSection(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@title = 'Esci']")
    WebElement esciButton;

    public void waitLoadHeaderPGPage() {

        try {
            By titlePageBy = By.xpath("//a[@title = 'Sito di PagoPA S.p.A.']");

            this.getWebDriverWait(30).withMessage("Il titolo 'sito di PagoPA S.p.A' non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).withMessage("Il bottone esci non è cliccabile").until(ExpectedConditions.elementToBeClickable(esciButton));

            logger.info("HeaderSectionPG caricata correttamente");

        }catch (TimeoutException e){
            logger.error("HeaderSectionPG non caricata correttamente con errrore: "+e.getMessage());
            Assert.fail("HeaderSectionPG non caricata correttamente con errrore: "+e.getMessage());
        }

    }

    public void clickEsciButton() {
        this.esciButton.click();
    }
}
