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

    //    public void waitLoadHeaderPGPage() {
//        try {
//            getWebDriverWait(70).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@title, 'PagoPA S.p.A.')]"))));
//            getWebDriverWait(70).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@title = 'Esci']"))));
//            logger.info("HeaderSectionPG caricata correttamente");
//        } catch (TimeoutException e) {
//            Assertions.fail("HeaderSectionPG non caricata correttamente con errore: " + e.getMessage());
//        }
//    }
    public void waitLoadHeaderPGPage() {
        getWebDriverWait(70)
                .withMessage("Logo PagoPA non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title, 'PagoPA S.p.A.')]")));

        getWebDriverWait(70)
                .withMessage("Bottone Esci non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Esci']")));

        logger.info("HeaderSectionPG caricata correttamente");
    }


    //    public void clickEsciButton() {
//        esciButton = getWebDriverWait(60)
//                .withMessage("Non Si Visualizza il tasto Esci sul Pop-Up")
//                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@title = 'Esci']"))));
//        esciButton.click();
//    }
    public void clickEsciButton() {
        esciButton = getWebDriverWait(60)
                .withMessage("Non si visualizza il tasto Esci sul Pop-Up")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Esci']")));
        esciButton.click();
    }


    public void clickEsciButtonPopUp() {
        WebElement esciButtonPopUp = getWebDriverWait(60)
                .withMessage("Il tasto 'Esci' nel popup non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(), 'Esci')]")
                ));

        esciButtonPopUp.click();
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