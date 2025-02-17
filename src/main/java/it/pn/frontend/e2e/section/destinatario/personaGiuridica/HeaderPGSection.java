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
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title = 'Sito di PagoPA S.p.A.']"))));
            getWebDriverWait(60).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@title = 'Esci']"))));
            logger.info("HeaderSectionPG caricata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("HeaderSectionPG non caricata correttamente con errore: " + e.getMessage());
        }
    }

    public void clickEsciButton() {
        esciButton = getWebDriverWait(60)
                .withMessage("Non Si Visualizza il tasto Esci sul Pop-Up")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@title = 'Esci']"))));
        esciButton.click();
    }

    public void clickEsciButtonPopUp() {
        WebElement esciButtonPopUp = getWebDriverWait(60)
                .withMessage("Non Si Visualizza il tasto Esci sul Pop-Up")
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@data-testid='dialog-actions']//button[contains(text(),'Esci')]"))));
        esciButtonPopUp.click();
    }
}