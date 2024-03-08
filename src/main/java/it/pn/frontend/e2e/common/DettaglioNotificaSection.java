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

public class DettaglioNotificaSection extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaSection");

    @FindBy(xpath = "//button[contains(text(),'Attestazione opponibile a terzi: ')]")
    List<WebElement> attestazioniFile;

    @FindBy(xpath = "//button/div[contains(text(),'NOTIFICATION')]")
    List<WebElement> documentiAllegati;

    @FindBy(id = "more-less-timeline-step")
    WebElement vediDettagliButton;

    @FindBy(xpath = "//*[contains(@class, 'MuiTimelineItem-root')]")
    List<WebElement> tuttiStatiNotificaList;

    public DettaglioNotificaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaDESection() {
        try {
            // This check is due to the fact that the page is different if the user is logged in as a selfcare user
            boolean isSelfcare = driver.getCurrentUrl().contains("selfcare");
            By titleDettaglioNotificaField = By.id("title-of-page");
            By statoNotificaBy = By.id("notification-state");
            By indietroButtonBy = By.id("breadcrumb-indietro-button");
            By informazioniBy = By.id("notification-detail-table");
            By allegatiSection = By.id("notification-detail-document-attached");
            By sezioneRecapiti = By.id("side-item-I tuoi recapiti");
            By altriDocumenti = isSelfcare ? By.xpath("//div[@data-testid='aarDownload']") : By.xpath("//div[@data-testid='aarBox']");
            By attestazione = By.xpath("//button[@data-testid='download-legalfact']");
            this.getWebDriverWait(30).withMessage("il titolo Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
            this.getWebDriverWait(30).withMessage("il bottone indietro non è visibile").until(ExpectedConditions.visibilityOfElementLocated(indietroButtonBy));
            this.getWebDriverWait(30).withMessage("Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(informazioniBy));
            this.getWebDriverWait(30).withMessage("La sezione Documenti allegati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(allegatiSection));
            this.getWebDriverWait(30).withMessage("Lo stato della notificanon non è visibile").until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
            if (!isSelfcare) {
                this.getWebDriverWait(30).withMessage("Il sezione recapiti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(sezioneRecapiti));
            }
            this.getWebDriverWait(30).withMessage("Il sezione altri documenti non è visibile").until(ExpectedConditions.visibilityOfElementLocated(altriDocumenti));
            this.getWebDriverWait(30).withMessage("Il pulsante sezione attestazione opponibile non è visibile").until(ExpectedConditions.elementToBeClickable(attestazione));
            logger.info("Dettaglio Notifica Section caricata");
        } catch (TimeoutException e) {
            logger.error("Dettaglio Notifica Section non caricata con errore: " + e.getMessage());
            Assert.fail("Dettaglio Notifica Section non caricata con errore: " + e.getMessage());
        }
    }


    public void clickLinkAttestazioniOpponibile(int numeroLinkAttestazioniOpponibile) {
        if (attestazioniFile.get(numeroLinkAttestazioniOpponibile).isDisplayed()) {
            attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", attestazioniFile.get(numeroLinkAttestazioniOpponibile));
            attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
        }
    }

    public void clickLinkDocumentiAllegati(int numeroLinkDocumentiAllegati) {
        if (documentiAllegati.get(numeroLinkDocumentiAllegati).isDisplayed()) {
            documentiAllegati.get(numeroLinkDocumentiAllegati).click();
        } else {
            this.js().executeScript("arguments[0].scrollIntoView(true);", attestazioniFile.get(numeroLinkDocumentiAllegati));
            documentiAllegati.get(numeroLinkDocumentiAllegati).click();
        }
    }

    public int getLinkAttestazioniOpponibili() {
        return attestazioniFile.size();
    }

    public int getLinkDocumentiAllegati() {
        return documentiAllegati.size();
    }

    public void selezioneVediDettaglioButton() {
        getWebDriverWait(30).withMessage("bottone vedi dettaglio non è cliccabile").until(ExpectedConditions.elementToBeClickable(this.vediDettagliButton));
        vediDettagliButton.click();
        if (!tuttiStatiNotificaList.isEmpty()) {
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
