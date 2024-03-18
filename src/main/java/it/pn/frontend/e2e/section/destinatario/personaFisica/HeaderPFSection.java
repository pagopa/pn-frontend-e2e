package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HeaderPFSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HeaderPFSection");

    @FindBy(xpath = "//a[@title='Sito di PagoPA S.p.A.']")
    WebElement titleLabel;

    @FindBy(xpath = "//button[@aria-label='party-menu-button']")
    WebElement buttonProfile;

    @FindBy(xpath = "//ul[@role='menu']//li")
    List<WebElement> menuProfileItems;

    public HeaderPFSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadHeaderDESection() {
        try {
            this.getWebDriverWait(10).withMessage("il titolo del header non è visibile").until(ExpectedConditions.visibilityOf(titleLabel));
            this.getWebDriverWait(10).withMessage("menu dell'utente non è visibile").until(ExpectedConditions.visibilityOf(buttonProfile));
            logger.info("Header DE Section caricata");
        } catch (TimeoutException e) {
            logger.error("Header DE Section non caricata con errore : " + e.getMessage());
            Assert.fail("Header DE Section non caricata con errore : " + e.getMessage());
        }
    }

    public void selezionaProfiloUtenteMenu() {
        this.js().executeScript("arguments[0].scrollIntoView(true);", this.buttonProfile);
        logger.info("click sul profilo utente");
        this.js().executeScript("arguments[0].click()", this.buttonProfile);
    }

    public void selezionaVoceEsci() {
        WebElement esciVoce = this.menuProfileItems.get(1);
        this.getWebDriverWait(10).withMessage("la voce esci non è visibile").until(ExpectedConditions.visibilityOf(esciVoce));
        logger.info("click su voce esci");
        esciVoce.click();
    }

    public void waitUrlToken() {
        try {
            this.getWebDriverWait(10).until(ExpectedConditions.urlContains("token"));
            logger.info("Url token ------------------------>" + driver.getCurrentUrl());
        } catch (TimeoutException e) {
            logger.error("Url token non trovato con errore:" + e.getMessage());
        }
    }
}
