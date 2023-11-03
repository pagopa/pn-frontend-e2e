package it.pn.frontend.e2e.common;

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

public class DettaglioNotificaSection extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaSection");

    @FindBy(xpath = "//button[contains(text(),'Attestazione opponibile a terzi: ')]")
    List<WebElement> attestazioniFile;

    @FindBy(id = "more-less-timeline-step")
    WebElement vediDettagliButton;

    @FindBy (xpath = "//li[contains(@class,'MuiTimelineItem-root MuiTimelineItem-positionRight MuiTimelineItem-missingOppositeContent css-1y9sx96')]")
    List<WebElement> tuttiStatiNotificaList;
    public DettaglioNotificaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaDESection() {
        try {
            By titleDettaglioNotificaField = By.id("title-of-page");
            By statoNotifcaBy = By.id("notification-state");
            By indietroButtonBy = By.id("breadcrumb-indietro-button");
            By informazioniBy = By.id("notification-detail-table");
            By allegatiSection = By.id("notification-detail-document-attached");
            this.getWebDriverWait(30).withMessage("il titolo Dettaglio notofica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
            this.getWebDriverWait(30).withMessage("il buttone indietro non è visibile").until(ExpectedConditions.visibilityOfElementLocated(indietroButtonBy));
            this.getWebDriverWait(30).withMessage("Dettaglio notofica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(informazioniBy));
            this.getWebDriverWait(30).withMessage("La sezione Documenti allegati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(allegatiSection));
            this.getWebDriverWait(30).withMessage("Lo stato della notificanon non è visibile").until(ExpectedConditions.visibilityOfElementLocated(statoNotifcaBy));
            logger.info("Dettaglio Notifica Section caricata");
        }catch (TimeoutException e){
            logger.error("Dettaglio Notifica Section non caricata con errore: "+ e.getMessage());
            Assert.fail("Dettaglio Notifica Section non caricata con errore: "+ e.getMessage());
        }
    }


    public void clickLinkAttestazionipponibile(int numeroLinkAttestazioniOpponibile) {
       if(attestazioniFile.get(numeroLinkAttestazioniOpponibile).isDisplayed()){
           attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
       }else{
           this.js().executeScript("arguments[0].scrollIntoView(true);",attestazioniFile.get(numeroLinkAttestazioniOpponibile));
           attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
       }
    }

    public int getLinkAttestazioniOpponubili() {
        return attestazioniFile.size();
    }

    public void selezioneVediDettaglioButton() {
        getWebDriverWait(30).withMessage("buttone vedi dettaglio non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.vediDettagliButton));
        vediDettagliButton.click();
        if (tuttiStatiNotificaList.size() >= 1){
            logger.info("Tutti gli stati sono stati visualizzati correttamente");
        } else {
            logger.error("Tutti i stati non sono stati visualizzati correttamente");
            Assert.fail("Tutti i stati non sono stati visualizzati correttamente");
        }
    }

    public String getTextLinkAttestazioniOpponibili(int i) {
        return attestazioniFile.get(i).getText();
    }
}
