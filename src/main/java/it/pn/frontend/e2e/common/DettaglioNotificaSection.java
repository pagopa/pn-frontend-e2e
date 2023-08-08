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

    @FindBy(xpath = "//button[contains(text(),'Vedi più dettagli')]")
    WebElement vediDettagliButton;

    @FindBy (xpath = "//li[contains(@class,'MuiTimelineItem-root MuiTimelineItem-positionRight MuiTimelineItem-missingOppositeContent css-1y9sx96')]")
    List<WebElement> tuttiStatiNotificaList;
    public DettaglioNotificaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaDESection() {
        try {
            By titleDettaglioNotificaField = By.xpath("//p[contains(text(),'Dettaglio notifica')]");
            By statoNotifcaBy = By.xpath("//h5[contains(text(),'Stato della notifica')]");
            By indietroButtonBy = By.xpath("//button[contains(text(),'Indietro')]");
            By informazioniBy = By.xpath("//table[@aria-label='Dettaglio notifica']");
            By allegatiSection = By.xpath("//span[contains(text(),'Documenti allegati')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(indietroButtonBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(informazioniBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(allegatiSection));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotifcaBy));
            logger.info("Dettaglio Notifica Section caricata");
        }catch (TimeoutException e){
            logger.error("Dettaglio Notifica Section non caricata con errore: "+ e.getMessage());
            Assert.fail("Dettaglio Notifica Section non caricata con errore: "+ e.getMessage());
        }
    }


    public void clickLinkAttestazionipponibile(int numeroLinkAttestazioniOpponibile) {
        attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
    }

    public int getLinkAttestazioniOpponubili() {
        return attestazioniFile.size();
    }

    public void selezioneVediDettaglioButton() {
        vediDettagliButton.click();
        if (tuttiStatiNotificaList.size() >= 1){
            logger.info("Tutti gli stati sono stati visualizzati correttamente");
        } else {
            logger.error("Tutti i stati non sono stati visualizzati correttamente");
            Assert.fail("Tutti i stati non sono stati visualizzati correttamente");
        }
    }

}
