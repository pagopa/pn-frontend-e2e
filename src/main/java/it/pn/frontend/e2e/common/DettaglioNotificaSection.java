package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.utility.WebTool;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


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


    private WebTool webTool;

    public DettaglioNotificaSection(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }


    public void waitLoadDettaglioNotificaDESection() {
        // This check is due to the fact that the page is different if the user is logged in as a selfcare user
        WebElement titleDettaglioNotificaField = driver.findElement(By.id("title-of-page"));
        WebElement statoNotificaBy = driver.findElement(By.id("notification-state"));
        WebElement informazioniBy = driver.findElement(By.id("notification-detail-table"));
        WebElement allegatiSection = driver.findElement(By.id("notification-detail-document-attached"));
        WebElement aarDownload = driver.findElement(By.xpath("//div[@data-testid='notificationDetailDocuments']"));
        WebElement aarBox = driver.findElement(By.xpath("//div[@data-testid='aarBox']"));
        WebElement attestazione = driver.findElement(By.xpath("//button[@data-testid='download-legalfact']"));
        indietroButton = driver.findElement(By.id("breadcrumb-indietro-button"));
        getWebDriverWait(10).withMessage("il titolo Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOf(titleDettaglioNotificaField));
        getWebDriverWait(20).withMessage("il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButton));
        getWebDriverWait(10).withMessage("Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOf(informazioniBy));
        getWebDriverWait(10).withMessage("La sezione Documenti allegati non è visibile").until(ExpectedConditions.visibilityOf(allegatiSection));
        getWebDriverWait(10).withMessage("Lo stato della notificanon non è visibile").until(ExpectedConditions.visibilityOf(statoNotificaBy));
        getWebDriverWait(10).withMessage("La sezione recapiti non è visibile").until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(aarDownload),
                ExpectedConditions.visibilityOf(aarBox)));
        getWebDriverWait(20).withMessage("Il pulsante sezione attestazione opponibile non è visibile").until(ExpectedConditions.elementToBeClickable(attestazione));
        logger.info("Dettaglio Notifica Section caricata");

    }

    public void clickLinkAttestazioniOpponibile(int numeroLinkAttestazioniOpponibile) {
        webTool.waitTime(20);
        List<WebElement> attestazioniFile = driver.findElements(By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: notifica presa in carico')]"));
        if (attestazioniFile.get(numeroLinkAttestazioniOpponibile).isDisplayed()) {
            getWebDriverWait(10).withMessage("Il link non è cliccabile").until(elementToBeClickable(attestazioniFile.get(numeroLinkAttestazioniOpponibile)));
            attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
        } else {
            scrollToElementAndClick(attestazioniFile.get(numeroLinkAttestazioniOpponibile));
        }

    }

    public void toBeClickableLinkAttestazioniOpponibile(int numeroLinkAttestazioniOpponibile) {
        webTool.waitTime(20);
        List<WebElement> attestazioniFile = driver.findElements(By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: notifica presa in carico')]"));
        if (attestazioniFile.get(numeroLinkAttestazioniOpponibile).isDisplayed()) {
            getWebDriverWait(10).withMessage("Il link non è cliccabile").until(elementToBeClickable(attestazioniFile.get(numeroLinkAttestazioniOpponibile)));
        }
    }

    public void clickLinkDocumentiAllegati(int numeroLinkDocumentiAllegati) {
        documentiAllegati = driver.findElements(By.xpath("//button/div[contains(text(),'NOTIFICATION')]"));
        if (documentiAllegati.get(numeroLinkDocumentiAllegati).isDisplayed()) {
            documentiAllegati.get(numeroLinkDocumentiAllegati).click();
        } else {
            scrollToElementAndClick(documentiAllegati.get(numeroLinkDocumentiAllegati));
        }
    }

    public int getLinkAttestazioniOpponibili() {
        List<WebElement> attestazioniFile = driver.findElements(By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: ')]"));
        return attestazioniFile.size();
    }

    public int getLinkDocumentiAllegati() {
        documentiAllegati = driver.findElements(By.xpath("//button/div[contains(text(),'NOTIFICATION')]"));
        return documentiAllegati.size();
    }

    public void selezioneVediDettaglioButton() {
        vediDettagliButton =  driver.findElement(By.id("more-less-timeline-step"));
        scrollToElementAndClick(vediDettagliButton);
        tuttiStatiNotificaList = driver.findElements(By.xpath("//*[contains(@class, 'MuiTimelineItem-root')]"));
        if (!tuttiStatiNotificaList.isEmpty()) {
            logger.info("Tutti gli stati sono stati visualizzati correttamente");
        } else {
            logger.error("Tutti i stati non sono stati visualizzati correttamente");
            Assertions.fail("Tutti i stati non sono stati visualizzati correttamente");
        }
    }

    public String getTextLinkAttestazioniOpponibili(int i) {

        List<WebElement> attestazioniFile = driver.findElements(By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: ')]"));
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

        webTool.waitTime(15);
        WebElement titleDettaglioNotificaField = driver.findElement(By.id("title-of-page"));
        WebElement statoNotificaBy = driver.findElement(By.id("notification-state"));
        WebElement indietroButtonBy = driver.findElement(By.id("breadcrumb-indietro-button"));
        WebElement informazioniBy = driver.findElement(By.id("notification-detail-table"));
        WebElement allegatiSection = driver.findElement(By.id("notification-detail-document-attached"));
        WebElement aarDownload = driver.findElement(By.xpath("//div[@data-testid='notificationDetailDocuments']"));
        WebElement aarBox = driver.findElement(By.xpath("//div[@data-testid='aarBox']"));
        WebElement attestazione = driver.findElement(By.xpath("//button[@data-testid='download-legalfact']"));
        WebElement copyNotificaAnnullataDestinatario = driver.findElement(By.xpath("//div[@data-testid='cancelledAlertText']"));
       // WebElement copyNotificaAnnullataMittente = driver.findElement(By.xpath("//div[@data-testid='alert']"));
        WebElement chipAnnullataInTimeline = driver.findElement(By.id("Annullata-status"));

        getWebDriverWait(10).withMessage("il titolo Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOf(titleDettaglioNotificaField));
        getWebDriverWait(10).withMessage("il bottone indietro non è visibile").until(ExpectedConditions.visibilityOf(indietroButtonBy));
        getWebDriverWait(10).withMessage("Dettaglio notifica non è visibile").until(ExpectedConditions.visibilityOf(informazioniBy));
        getWebDriverWait(10).withMessage("La sezione Documenti allegati non è visibile").until(ExpectedConditions.visibilityOf(allegatiSection));
        getWebDriverWait(10).withMessage("Lo stato della notifica non è visibile").until(ExpectedConditions.visibilityOf(statoNotificaBy));

        getWebDriverWait(10).withMessage("La sezione recapiti non è visibile").until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(aarDownload),
                ExpectedConditions.visibilityOf(aarBox)));
        getWebDriverWait(10).withMessage("Il pulsante sezione attestazione opponibile non è visibile").until(ExpectedConditions.visibilityOf(attestazione));
        getWebDriverWait(10).withMessage("Il copy di notifica annullata non è visibile").until(ExpectedConditions.or(ExpectedConditions.visibilityOf(copyNotificaAnnullataDestinatario), ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-testid='alert']"))));
        getWebDriverWait(10).withMessage("La chip di notifica annullata non è visibile").until(ExpectedConditions.visibilityOf(chipAnnullataInTimeline));
        logger.info("Dettaglio Notifica Annullata Section caricata");
    }


    public void selezioneAvvisoPagoPa() {
        try {
            WebElement checkboxAvvisoPagoPa = driver.findElement(By.xpath("//span[@data-testid='radio-button']"));
            getWebDriverWait(10).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(checkboxAvvisoPagoPa), ExpectedConditions.elementToBeClickable(checkboxAvvisoPagoPa)));
            checkboxAvvisoPagoPa.click();
            logger.info("check su avviso pagopa avvenuto con successo");
        } catch (TimeoutException e) {
            logger.error("check su avviso pagopa non avvenuto con successo: " + e.getMessage());
            Assertions.fail("check su avviso pagopa non avvenuto con successo: " + e.getMessage());
        }
    }

    public void checkMessaggioScadenzaDownload() {
         //TODO Modificato il messaggio "Il documento sarà scaricabile tra pochi minuti"
         //webTool.waitTime(1);
         WebElement checkAvvisoDownloadScaduto = driver.findElement(By.xpath("//div[contains(text(), 'Al momento non è possibile scaricare il documento')]"));
         getWebDriverWait(10).withMessage("In messaggio Al momento non è possibile scaricare il documento non è visibile").until(ExpectedConditions.visibilityOf(checkAvvisoDownloadScaduto));
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
        webTool.waitTime(3);
        infoNotifiche = driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-paddingNone MuiTableCell-sizeMedium css-11dv4ll')]"));
        return infoNotifiche.get(i).getText();
    }
}
