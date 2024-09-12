package it.pn.frontend.e2e.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.section.mittente.DettaglioNotificaMittenteSection;
import it.pn.frontend.e2e.utility.NotificationBuilder;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DettaglioNotificaSection extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaSection");

    @FindBy(xpath = "//button[contains(text(),'Attestazione opponibile a terzi: ')]")
    List<WebElement> attestazioniFile;

    @FindBy(xpath = "//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-paddingNone MuiTableCell-sizeMedium css-11dv4ll')]")
    List<WebElement> infoNotifiche;

    @FindBy(xpath = "//button/div[contains(text(),'NOTIFICATION')]")
    List<WebElement> documentiAllegati;

    @FindBy(id = "more-less-timeline-step")
    WebElement vediDettagliButton;

    @FindBy(xpath = "//*[contains(@class, 'MuiTimelineItem-root')]")
    List<WebElement> tuttiStatiNotificaList;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    public DettaglioNotificaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaDESection() {
            // This check is due to the fact that the page is different if the user is logged in as a selfcare user
            By titleDettaglioNotificaField = By.id("title-of-page");
            By statoNotificaBy = By.id("notification-state");
            By informazioniBy = By.id("notification-detail-table");
            By allegatiSection = By.id("notification-detail-document-attached");
            By aarDownload = By.xpath("//div[@data-testid='aarDownload']");
            By aarBox = By.xpath("//div[@data-testid='aarBox']");
            By attestazione = By.xpath("//button[@data-testid='download-legalfact']");
            getWebDriverWait(10).withMessage("il titolo Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
            getWebDriverWait(10).withMessage("il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
            getWebDriverWait(10).withMessage("Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(informazioniBy));
            getWebDriverWait(10).withMessage("La sezione Documenti allegati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(allegatiSection));
          getWebDriverWait(10).withMessage("Lo stato della notificanon non è visibile").until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));
            getWebDriverWait(10).withMessage("La sezione recapiti non è visibile").until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(aarDownload),
                    ExpectedConditions.visibilityOfElementLocated(aarBox)));
            getWebDriverWait(20).withMessage("Il pulsante sezione attestazione opponibile non è visibile").until(ExpectedConditions.elementToBeClickable(attestazione));
            logger.info("Dettaglio Notifica Section caricata");

    }

    public void clickLinkAttestazioniOpponibile(int numeroLinkAttestazioniOpponibile) {
        WebTool.waitTime(1);
        if (attestazioniFile.get(numeroLinkAttestazioniOpponibile).isDisplayed()) {
            attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
        } else {
            scrollToElementAndClick(attestazioniFile.get(numeroLinkAttestazioniOpponibile));
        }
    }

    public void clickLinkDocumentiAllegati(int numeroLinkDocumentiAllegati) {
        if (documentiAllegati.get(numeroLinkDocumentiAllegati).isDisplayed()) {
            documentiAllegati.get(numeroLinkDocumentiAllegati).click();
        } else {
            scrollToElementAndClick(documentiAllegati.get(numeroLinkDocumentiAllegati));
        }
    }

    public int getLinkAttestazioniOpponibili() {
        return attestazioniFile.size();
    }

    public int getLinkDocumentiAllegati() {
        return documentiAllegati.size();
    }

    public void selezioneVediDettaglioButton() {
        scrollToElementAndClick(vediDettagliButton);
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


    public boolean isFieldDisplayed(By xpath) {
        getWebDriverWait(10).withMessage("Campo non trovato").until(ExpectedConditions.visibilityOfElementLocated(xpath));
        return element(xpath).isDisplayed();
    }

    public boolean isFieldNotDisplayed(By xpath) {
        List<WebElement> elements = driver.findElements(xpath);
        if (!elements.isEmpty()) {
            return false;
        }
        return true;

    }

    public void waitLoadDettaglioNotificaAnnullataDESection() {

            By titleDettaglioNotificaField = By.id("title-of-page");
            By statoNotificaBy = By.id("notification-state");
            By indietroButtonBy = By.id("breadcrumb-indietro-button");
            By informazioniBy = By.id("notification-detail-table");
            By allegatiSection = By.id("notification-detail-document-attached");
            By aarDownload = By.xpath("//div[@data-testid='aarDownload']");
            By aarBox = By.xpath("//div[@data-testid='aarBox']");
            By attestazione = By.xpath("//button[@data-testid='download-legalfact']");
            By copyNotificaAnnullataDestinatario = By.xpath("//div[@data-testid='cancelledAlertText']");
        By copyNotificaAnnullataMittente = By.xpath("//div[@data-testid='alert']");
        By chipAnnullataInTimeline = By.id("Annullata-status");

            getWebDriverWait(10).withMessage("il titolo Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
            getWebDriverWait(10).withMessage("il bottone indietro non è visibile").until(ExpectedConditions.visibilityOfElementLocated(indietroButtonBy));
            getWebDriverWait(10).withMessage("Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(informazioniBy));
            getWebDriverWait(10).withMessage("La sezione Documenti allegati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(allegatiSection));
            getWebDriverWait(10).withMessage("Lo stato della notifica non è visibile").until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));

            getWebDriverWait(10).withMessage("La sezione recapiti non è visibile").until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(aarDownload),
                    ExpectedConditions.visibilityOfElementLocated(aarBox)));
            getWebDriverWait(10).withMessage("Il pulsante sezione attestazione opponibile non è visibile").until(ExpectedConditions.visibilityOfElementLocated(attestazione));
            getWebDriverWait(10).withMessage("Il copy di notifica annullata non è visibile").until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(copyNotificaAnnullataDestinatario),ExpectedConditions.visibilityOfElementLocated(copyNotificaAnnullataMittente)));
            getWebDriverWait(10).withMessage("La chip di notifica annullata non è visibile").until(ExpectedConditions.visibilityOfElementLocated(chipAnnullataInTimeline));
            logger.info("Dettaglio Notifica Annullata Section caricata");
    }


    public void selezioneAvvisoPagoPa() {
        try {
            By checkboxAvvisoPagoPa = By.xpath("//span[@data-testid='radio-button']");
            getWebDriverWait(10).until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(checkboxAvvisoPagoPa), ExpectedConditions.elementToBeClickable(checkboxAvvisoPagoPa)));
            element(checkboxAvvisoPagoPa).click();
            logger.info("check su avviso pagopa avvenuto con successo");
        } catch (TimeoutException e) {
            logger.error("check su avviso pagopa non avvenuto con successo: " + e.getMessage());
            Assert.fail("check su avviso pagopa non avvenuto con successo: " + e.getMessage());
        }
    }

    public void checkMessaggioScadenzaDownload() {
        By checkAvvisoDownloadScaduto = By.xpath("//div[contains(text(), 'Il documento sarà scaricabile tra pochi minuti')]");
        getWebDriverWait(10).withMessage("Il pulsante sezione attestazione opponibile non è visibile").until(ExpectedConditions.visibilityOfElementLocated(checkAvvisoDownloadScaduto));
    }

    public Map<String, String> recuperoInfoNotificheDestinatario() {
        Map<String, String> infoNotifica = new HashMap<>();
        String mittente = getInfoNotifica(0);
        infoNotifica.put("mittente", mittente);
        String destinatario = getInfoNotifica(1);
        infoNotifica.put("destinatario", destinatario);

        return infoNotifica;
    }

    public String getInfoNotifica(int i) {
        return infoNotifiche.get(i).getText();
    }
}
