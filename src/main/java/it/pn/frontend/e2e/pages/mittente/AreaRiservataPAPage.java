package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AreaRiservataPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AreaRiservataPAPage");

    @FindBy(xpath = "//p[contains(@class,'MuiTypography-root MuiTypography-body1 ShowDots')]")
    List<WebElement> infoLabel;

    @FindBy(id = "forward_prod-pn-dev")
    WebElement piattaformaNotificaDevGestisciButton;

    @FindBy(id = "forward_prod-pn-test")
    WebElement piattaformaNotificaTestGestisciButton;

    @FindBy(id = "forward_prod-pn")
    WebElement piattaformaNotificaUatGestisciButton;


    public AreaRiservataPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAreaRiservataPAPage(){
        try{
            By panoramicaLabel = By.cssSelector(".MuiTypography-root.MuiTypography-h4");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(panoramicaLabel));
            logger.info("Titolo Panoramica dell'area Riservata PA Page caricato");
        }catch (TimeoutException e){
            logger.error("Titolo 'Panoramica' dell'area Riservata PA Page non caricato con errore : "+e.getMessage());
            Assert.fail("Titolo 'Panoramica' dell'area Riservata PA Page non caricato con errore : "+e.getMessage());
        }
    }

    public boolean verificaCodiceFiscale(String codiceFiscale){
        boolean codiceFiscaleFound = false;
        for(WebElement element : infoLabel){
            logger.info("Info ente presente nella pagina Aria Riservata: "+element.getText());
            if(element.getText().equals(codiceFiscale)){
                codiceFiscaleFound = true;
                break;
            }
        }
        return codiceFiscaleFound;
    }

    public void selezionaPiattaformaNotificaDev(){
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(piattaformaNotificaDevGestisciButton));
            if (this.piattaformaNotificaDevGestisciButton.isDisplayed()) {
                this.piattaformaNotificaDevGestisciButton.click();
            } else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", this.piattaformaNotificaDevGestisciButton);
                this.piattaformaNotificaDevGestisciButton.click();
            }
        }catch(TimeoutException | NoSuchElementException | ElementClickInterceptedException e){
            logger.error("TA_QA: il buttone piattaforma notifica dev non è presente o non è cliccabile, "+e.getMessage());
            Assert.fail("TA_QA: il buttone piattaforma notifica dev non è presente o non è cliccabile. "+e.getMessage());
        }
    }
    public void selezionaPiattaformaNotificaTest() {
        try{
        getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(piattaformaNotificaTestGestisciButton));

        if(this.piattaformaNotificaTestGestisciButton.isDisplayed()){
            this.piattaformaNotificaTestGestisciButton.click();
        }else{
            this.js().executeScript("arguments[0].scrollIntoView(true);",this.piattaformaNotificaDevGestisciButton);
            this.piattaformaNotificaTestGestisciButton.click();
        }
        }catch(TimeoutException | NoSuchElementException | ElementClickInterceptedException e){
            logger.error("TA_QA: il buttone piattaforma notifica test non è presente o non è cliccabile, "+e.getMessage());
            Assert.fail("TA_QA: il buttone piattaforma notifica test non è presente o non è cliccabile. "+e.getMessage());
        }
    }

    public void selezionaPiattaformaNotificaUat() {
        try{
        getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(piattaformaNotificaUatGestisciButton));

        if(this.piattaformaNotificaUatGestisciButton.isDisplayed()){
            this.piattaformaNotificaUatGestisciButton.click();
        }else{
            this.js().executeScript("arguments[0].scrollIntoView(true);",this.piattaformaNotificaDevGestisciButton);
            this.piattaformaNotificaUatGestisciButton.click();
        }
        }catch(TimeoutException | NoSuchElementException | ElementClickInterceptedException e){
            logger.error("TA_QA: il buttone piattaforma notifica uat non è presente o non è cliccabile, "+e.getMessage());
            Assert.fail("TA_QA: il buttone piattaforma notifica uat non è presente o non è cliccabile. "+e.getMessage());
        }
    }

}
