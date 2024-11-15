package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
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
import org.springframework.stereotype.Component;


public class AutorizzaInvioDatiPGPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("AutorizzaInvioDatiPGPage");



    @FindBy(xpath = "//button[@name = 'confirm']")
    WebElement inviaButton;

    public AutorizzaInvioDatiPGPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadAutorizzaInvioDatiPGPage() {
        try {
            WebElement titlePageBy = driver.findElement(By.xpath("//h1[contains(text(),'Autorizzi')]"));
            getWebDriverWait(30).withMessage("Il titolo della pagina autorizzi invio dati PG non è visibile").until(ExpectedConditions.visibilityOf(titlePageBy));
            getWebDriverWait(30).withMessage("Il bottone invia nella pagina autorizzi invio dati PG non è visibile").until(ExpectedConditions.elementToBeClickable(this.inviaButton));
            logger.info("AutorizzaInviaDatiPGPage caricata correttamente");
        } catch (TimeoutException e){
            logger.error("AutorizzaInviaDatiPGPage non caricata correttamente con errore: "+e.getMessage());
            Assertions.fail("AutorizzaInviaDatiPGPage non caricata correttamente con errore: "+e.getMessage());
        }
    }

    public void clickInviaButton() {
        inviaButton = driver.findElement(By.xpath("//button[@name = 'confirm']"));
        inviaButton.click();
    }
}
