package it.pn.frontend.e2e.pages.mittente;

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

import java.util.List;

public class AreaRiservataPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AreaRiservataPAPage");

    @FindBy(xpath = "//p[contains(@class,'MuiTypography-root MuiTypography-body1 ShowDots')]")
    List<WebElement> infoLabel;

    @FindBy(xpath = "//button[contains(@id,'forward_prod-pn-dev')]")
    WebElement piattaformaNotificaDevGestisciButton;

    @FindBy(xpath = "//button[contains(@id,'forward_prod-pn-svil')]")
    WebElement svilLButton;

    public AreaRiservataPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAreaRiservataPAPage(){
        try{
            By panoramicaLabel = By.cssSelector(".MuiTypography-root.MuiTypography-h4");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(panoramicaLabel));
            logger.info("Area Riservata PA Page caricata");
        }catch (TimeoutException e){
            logger.error("Area Riservata PA Page non caricata con errore : "+e.getMessage());
            Assert.fail("Area Riservata PA Page non caricata con errore : "+e.getMessage());
        }
    }

    public boolean verificaCodiceFiscale(String codiceFiscale){
        boolean codiceFiscaleFound = false;
        for(WebElement element : infoLabel){
            logger.info("info ente: "+element.getText());
            if(element.getText().equals(codiceFiscale)){
                codiceFiscaleFound = true;
                break;
            }
        }
        return codiceFiscaleFound;
    }

    public void selezionaPiattaformaNotificaDev(){
        if(this.piattaformaNotificaDevGestisciButton.isDisplayed()){
            this.piattaformaNotificaDevGestisciButton.click();
        }else{
            this.js().executeScript("arguments[0].scrollIntoView(true);",this.piattaformaNotificaDevGestisciButton);
            this.piattaformaNotificaDevGestisciButton.click();
        }
    }

    public void selezionaPiattaformaNotificaSvil() {
        if(this.svilLButton.isDisplayed()){
            this.svilLButton.click();
        }else{
            this.js().executeScript("arguments[0].scrollIntoView(true);",this.piattaformaNotificaDevGestisciButton);
            this.svilLButton.click();
        }
    }
}
