package it.pn.frontend.e2e.pages.destinatario.personaFisica;

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

@Component
public class ComeVuoiAccederePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("ComeVuoiAccederePage");

    @FindBy(id = "spidButton")
    WebElement spidButton;


    public void waitLoadComeVuoiAccederePage(){
        try{
            By titoloLabel = By.id("login-mode-page-title");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titoloLabel));
            logger.info("Come Vuoi Accedere Page caricata");
        }catch (TimeoutException e){
            logger.error("Come Vuoi Accedere Page non caricata con errore : "+e.getMessage());
            Assertions.fail("Come Vuoi Accedere Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionaSpidButton(){
        getWebDriverWait(60).withMessage("Il bottone SPID non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.spidButton));
        this.spidButton.click();
    }

    public boolean verificaPresenzaSpidButton(){
        this.getWebDriverWait(60).withMessage("spid button non è visibile").until(ExpectedConditions.elementToBeClickable(this.spidButton));
        return  this.spidButton.isDisplayed();
    }
}
