package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

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

public class AutorizzaInvioDatiPGPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("AutorizzaInvioDatiPGPage");
    public AutorizzaInvioDatiPGPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@name = 'confirm']")
    WebElement inviaButton;

    public void waitLoadAutorizzaInvioDatiPGPage() {
        try {
            By titlePageBy = By.xpath("//h1[contains(text(),'Autorizzi')]");

            this.getWebDriverWait(30).withMessage("Il titolo della pagina autorizzi invio dati PG non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).withMessage("Il bottone invia nella pagina autorizzi invio dati PG non è visibile").until(ExpectedConditions.elementToBeClickable(this.inviaButton));

            logger.info("AutorizzaInviaDatiPGPage caricata correttamente");

        }catch (TimeoutException e){
            logger.error("AutorizzaInviaDatiPGPage non caricata correttamente con errore: "+e.getMessage());
            Assert.fail("AutorizzaInviaDatiPGPage non caricata correttamente con errore: "+e.getMessage());
        }
    }

    public void clickInviaButton() {
        this.inviaButton.click();
    }
}
