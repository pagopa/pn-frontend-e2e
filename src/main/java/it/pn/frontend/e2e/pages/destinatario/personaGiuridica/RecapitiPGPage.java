package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;


public class RecapitiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RecapitiPGPage");

    RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);

    public RecapitiPGPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadRecapitiPage() {
        try {
            By racapitiPageTitle = By.id("Recapiti-page");
            By titoloEmail = By.id("E-mail o numero di cellulare-page");
            By sottoTitolo = By.xpath("//p[contains(text(),'viene inviato un messaggio')]");
            By indirizzoEmail = By.id("email");
            By avvisamoViaEmailButton = By.id("courtesy-email-button");
            By alertInformativo = By.xpath("//span[contains(text(),'non è raggiungibile tramite ')]");
            getWebDriverWait(10).withMessage("il titolo Ricapiti della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(racapitiPageTitle));
            getWebDriverWait(10).withMessage("il titolo Email della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titoloEmail));
            getWebDriverWait(10).withMessage("il sottotitolo della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(sottoTitolo));
            getWebDriverWait(10).withMessage("'l'indirizzio email della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(indirizzoEmail));
            getWebDriverWait(10).withMessage("'l'indirizzio email della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(avvisamoViaEmailButton));
            getWebDriverWait(10).withMessage("'l'indirizzio email della pagina recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(alertInformativo));
            logger.info("Si visualizza correttamente recapiti page");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente recapiti page con errore:"+e.getMessage());
        }
    }
    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di errore limite caratteri")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioDiErroreLimite() {

        String errorMessageRead = recapitiDestinatarioPage.getEmailErrorMessage();
        Assert.assertEquals("messaggio di errore letto : '" + errorMessageRead + "' non è uguale a : Scrivi massimo 254 caratteri", "Scrivi massimo 254 caratteri", errorMessageRead);
    }
}
