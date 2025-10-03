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

import java.util.List;


public class HeaderPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HeaderPASection.class);

    @FindBy(xpath = "//button[contains(text(),'Esci')]")
    WebElement esciButton;

    private WebTool webTool;

    public HeaderPASection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadHeaderSection() {
        try {
            getWebDriverWait(60)
                    .withMessage("Non trovato title 'PagoPA S.p.A.' ")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title, 'PagoPA S.p.A.')]")));
            logger.info("Header PA Section caricata");
        } catch (TimeoutException e) {
            Assertions.fail("Il titolo nel Header: 'Sito di PagoPA S.p.A.' non è caricato con errore : " + e.getMessage());
        }
    }

    public void selezionaEsciButton() {
        try {
            WebElement selezionaEsciButton = getWebDriverWait(20)
                    .withMessage("Il primo bottone 'Esci' non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(), 'Esci')]")
                    ));

            // Clicca sul pulsante "Esci"
            selezionaEsciButton.click();
        } catch (TimeoutException e) {
            Assertions.fail("Il bottone esci non cliccabile con errore: " + e.getMessage());
        }
    }

    public void selezionaSecondoEsciButtonPA() {
        try {
            List<WebElement> esciButtons = getWebDriverWait(5)
                    .withMessage("Il bottone Esci PA non è presente")
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//button[contains(text(),'Esci')])[2]")));

            if (!esciButtons.isEmpty()) {
                esciButton = getWebDriverWait(10)
                        .withMessage("Impossibile cliccare sul secondo bottone Esci del Pop-up PA")
                        .until(ExpectedConditions.elementToBeClickable(esciButtons.get(0)));
                esciButton.click();
                logger.info("Cliccato sul secondo bottone Esci del Pop-up PG");
            } else {
                logger.warn("Il secondo bottone Esci non è presente, nessuna azione eseguita PA");
            }
        } catch (TimeoutException e) {
            Assertions.fail("Il secondo bottone Esci non cliccabile PA con errore: " + e.getMessage());
        }
    }
}
