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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
            logger.info("ATTNEDIAMO 15 SEC...");
            webTool.waitTime(20);
            logger.info("HTML..."+driver.getPageSource());
            titleLabel = driver.findElement(By.xpath("//a[@title='Sito di PagoPA S.p.A.']"));
            buttonProfile = driver.findElement(By.xpath("//button[@aria-label='party-menu-button']"));
            getWebDriverWait(30).withMessage("il titolo del header non è visibile").until(ExpectedConditions.visibilityOf(titleLabel));
            getWebDriverWait(30).withMessage("menu dell'utente non è visibile").until(ExpectedConditions.visibilityOf(buttonProfile));
            logger.info("Header DE Section caricata");
        } catch (TimeoutException e) {
            logger.error("Header DE Section non caricata con errore : " + e.getMessage());
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
        menuProfileItems = driver.findElements(By.xpath("//ul[@role='menu']//li"));
        WebElement esciVoce = menuProfileItems.get(1);
        getWebDriverWait(30).withMessage("la voce esci non è visibile").until(ExpectedConditions.visibilityOf(esciVoce));
        logger.info("click su voce esci");
        esciVoce.click();
    }

    public void waitUrlToken() {
        try {
            getWebDriverWait(10).until(ExpectedConditions.urlContains("token"));
            logger.info("Url token ------------------------>" + driver.getCurrentUrl());
        } catch (TimeoutException e) {
            logger.error("Url token non trovato con errore:" + e.getMessage());
        }
    }
}
