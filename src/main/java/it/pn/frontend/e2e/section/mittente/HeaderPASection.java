package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
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


public class HeaderPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HeaderPASection");

    @FindBy(xpath = "//button[contains(text(),'Esci')]")
    WebElement esciButton;

    private WebTool webTool;

    public HeaderPASection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadHeaderSection() {
        try {
            webTool.waitTime(10);
            getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Sito di PagoPA S.p.A.']")));
            logger.info("Header PA Section caricata");
        } catch (TimeoutException e) {
            logger.error("Il titolo nel Header: 'Sito di PagoPA S.p.A.' non è caricato con errore : " + e.getMessage());
            Assertions.fail("Il titolo nel Header: 'Sito di PagoPA S.p.A.' non è caricato con errore : " + e.getMessage());
        }
    }

    public void selezionaEsciButton() {
        try {
            esciButton = driver.findElement(By.xpath("//button[contains(text(),'Esci')]"));
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(esciButton));
            esciButton.click();
        } catch (TimeoutException e) {
            logger.error("Il bottone esci non cliccabile con errore: " + e.getMessage());
            Assertions.fail("Il bottone esci non cliccabile con errore: " + e.getMessage());
        }
    }
}
