package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;
import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;


public class RecapitiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RecapitiPGPage");

    public RecapitiPGPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadRecapitiPage() {
        try {
            By racapitiPageTitle = By.id("Recapiti-page");
            By recapitiPageSubtitle = By.xpath("//p[contains(text(),'Quando c’è una notifica per l’impresa, l’avviso di avvenuta ricezione viene inviato a questo indirizzo')]");
            By pecTextBox = By.id("pec");
            By confermationButton = By.id("add-contact");
            By infoAlert = By.cssSelector("[data-testid='legal-contact-disclaimer']");
            getWebDriverWait(30).withMessage("il titolo Ricapiti della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(racapitiPageTitle));
            getWebDriverWait(30).withMessage("il Sottotitolo della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(recapitiPageSubtitle));
            getWebDriverWait(30).withMessage("il textbox pec della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(pecTextBox));
            getWebDriverWait(30).withMessage("il bottone conferma della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(confermationButton));
            getWebDriverWait(30).withMessage("l'alert informativo della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(infoAlert));
            logger.info("Si visualizza correttamente recapiti page");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
        }
    }
}
