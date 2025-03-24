package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

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


public class HeaderPGSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HeaderPGSection.class);


    @FindBy(xpath = "//button[@title = 'Esci']")
    private WebElement esciButton;

    private WebTool webTool;

    public HeaderPGSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadHeaderPGPage() {
        try {
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@title, 'PagoPA S.p.A.')]"))));
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@title = 'Esci']"))));
            logger.info("HeaderSectionPG caricata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("HeaderSectionPG non caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void clickEsciButton() {
        esciButton = driver.findElement(By.xpath("//button[@title = 'Esci']"));
        esciButton.click();
    }

    public void selezionaSecondoEsciButtonPG() {
        try {
            List<WebElement> esciButtons = getWebDriverWait(25)
                    .withMessage("Il bottone Esci PG non è presente")
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//button[contains(text(),'Esci')])[2]")));

            if (!esciButtons.isEmpty()) {
                esciButton = getWebDriverWait(10)
                        .withMessage("Impossibile cliccare sul secondo bottone Esci del Pop-up PG")
                        .until(ExpectedConditions.elementToBeClickable(esciButtons.get(0)));
                esciButton.click();
                logger.info("Cliccato sul secondo bottone Esci del Pop-up PG");
            } else {
                logger.warn("Il secondo bottone Esci non è presente, nessuna azione eseguita PG");
            }
        } catch (TimeoutException e) {
            Assertions.fail("Il secondo bottone Esci non cliccabile PG con errore: " + e.getMessage());
        }
    }
}