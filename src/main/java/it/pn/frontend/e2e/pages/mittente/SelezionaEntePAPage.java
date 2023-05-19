package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SelezionaEntePAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("SelezionaEntePAPage");

    @FindBy(xpath = "//div[contains(@data-testid,'PartyItemContainer: Comune di Palermo')]")
    List<WebElement> comuneButton;

    @FindBy(xpath = "//button[text()='Accedi']")
    WebElement accediButton;

    public SelezionaEntePAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadSelezionaEntePAPage(){
        try{
            By titlePage = By.xpath("//h3[text()='Seleziona il tuo ente']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("Seleziona Utente PA Page caricata");
        }catch (TimeoutException e){
            logger.error("Seleziona Utente PA Page non caricata con errore : "+e.getMessage());
            Assert.fail("Seleziona Utente PA Page non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionareComune(String comune){
        for (WebElement element : this.comuneButton){
            if(element.getText().contains(comune)){
                element.click();
                break;
            }
        }
    }

    public void selezionaAccedi(){
        Actions actions = new Actions(driver);
        actions.moveToElement(this.accediButton).click().perform();
    }
}
