package it.pn.frontend.e2e.section.destinatario.personaFisica;

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

import java.text.MessageFormat;
import java.util.List;


public class HeaderPFSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HeaderPFSection");


    @FindBy(xpath = "//a[@title='Sito di PagoPA S.p.A.']")
    WebElement titleLabel;

    @FindBy(xpath = "//button[@aria-label='party-menu-button']")
    WebElement buttonProfile;

    @FindBy(xpath = "//ul[@role='menu']//li")
    List<WebElement> menuProfileItems;

    private  WebTool webTool;

    public HeaderPFSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    public void waitLoadHeaderDESection() {
        try {
            getWebDriverWait(60).withMessage("il titolo del header non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@title, 'PagoPA S.p.A.')]"))));
            getWebDriverWait(60).withMessage("menu dell'utente non è visibile").until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[@aria-label='party-menu-button']"))));
            logger.info("Header DE Section caricata");
        } catch (TimeoutException e) {
            Assertions.fail("Header DE Section non caricata con errore : " + e.getMessage());
        }
    }

    public void selezionaProfiloUtenteMenu() {
        buttonProfile = driver.findElement(By.xpath("//button[@aria-label='party-menu-button']"));
        js().executeScript("arguments[0].scrollIntoView(true);", buttonProfile);
        logger.info("click sul profilo utente");
        buttonProfile.click();
    }

    public void selezionaVoceEsci() {
        getWebDriverWait(35).withMessage("il titolo del header non è visibile").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//ul[@role='menu']//li"))));
        menuProfileItems = driver.findElements(By.xpath("//ul[@role='menu']//li"));
        WebElement esciVoce = menuProfileItems.get(1);
        getWebDriverWait(30).withMessage("la voce esci non è visibile").until(ExpectedConditions.visibilityOf(esciVoce));
        logger.info("click su voce esci");
        esciVoce.click();
    }

    public void waitUrlToken() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.urlContains("token"));
            logger.info(MessageFormat.format("Url token ------>{0}", driver.getCurrentUrl()));
        } catch (TimeoutException e) {
            logger.error(MessageFormat.format("Url token non trovato con errore:{0}", e.getMessage()));
        }
    }

    public void selezionaSecondoEsciButtonPF() {
        try {
            List<WebElement> esciButtons = getWebDriverWait(5)
                    .withMessage("Il bottone Esci PF non è presente")
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Esci')]")));

            if (!esciButtons.isEmpty()) {
                WebElement esciButton = getWebDriverWait(10)
                        .withMessage("Impossibile cliccare sul secondo bottone Esci del Pop-up PF")
                        .until(ExpectedConditions.elementToBeClickable(esciButtons.get(0)));
                esciButton.click();
                logger.info("Cliccato sul secondo bottone Esci del Pop-up PF");
            } else {
                logger.warn("Il secondo bottone Esci non è presente PF, nessuna azione eseguita");
            }
        } catch (TimeoutException e) {
            Assertions.fail("Il secondo bottone Esci non cliccabile PF con errore: " + e.getMessage());
        }
    }
}
