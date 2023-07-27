package it.pn.frontend.e2e.pages.destinatario.personaFisica;

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

public class ComeVuoiAccederePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("ComeVuoiAccederePage");

    @FindBy(id = "spidButton")
    WebElement spidButton;

    public ComeVuoiAccederePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadComeVuoiAccederePage(){
        try{
            By titoloLabel = By.xpath("//h2[contains(@id,'login-mode-page-title')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titoloLabel));
            logger.info("Come Vuoi Accedere Page caricata");
        }catch (TimeoutException e){
            logger.error("Come Vuoi Accedere Page non caricata con errore : "+e.getMessage());
            Assert.fail("Come Vuoi Accedere Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionareSpidButton(){
        this.spidButton.click();
    }

    public boolean verificaPresenzaSpidButton(){
        return  this.spidButton.isDisplayed();
    }
}
