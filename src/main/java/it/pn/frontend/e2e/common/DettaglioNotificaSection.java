package it.pn.frontend.e2e.common;

import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DettaglioNotificaSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DettaglioNotificaSection.class);

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
        By titleDettaglioNotificaField = By.id("title-of-page");
        By statoNotificaBy = By.id("notification-state");
        By informazioniBy = By.id("notification-detail-table");
        By allegatiSection = By.id("notification-detail-document-attached");
        By aarDownload = By.xpath("//div[@data-testid='notificationDetailDocuments']");
        By aarBox = By.xpath("//div[@data-testid='aarBox']");
        By attestazione = By.xpath("//button[@data-testid='download-legalfact']");
        By indietroButton = By.id("breadcrumb-indietro-button");

        getWebDriverWait(10).withMessage("il titolo Dettaglio notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));

        getWebDriverWait(20).withMessage("il bottone indietro non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(indietroButton));

        getWebDriverWait(10).withMessage("Dettaglio notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(informazioniBy));

        getWebDriverWait(10).withMessage("La sezione Documenti allegati non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(allegatiSection));

        getWebDriverWait(10).withMessage("Lo stato della notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBy));

        getWebDriverWait(10).withMessage("La sezione recapiti non è visibile")
                .until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(aarDownload),
                        ExpectedConditions.visibilityOfElementLocated(aarBox)));

        getWebDriverWait(20).withMessage("Il pulsante sezione attestazione opponibile non è visibile")
                .until(ExpectedConditions.elementToBeClickable(attestazione));

        logger.info("Dettaglio Notifica Section caricata");
    }


    public void clickLinkAttestazioniOpponibile(int numeroLinkAttestazioniOpponibile) {
        // Locator del bottone attestazione
        By attestazioniLocator = By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: notifica presa in carico')]");
        // Attendo che ci siano abbastanza link caricati
        getWebDriverWait(15).withMessage("Non sono stati trovati abbastanza link di attestazioni opponibili")
                .until(driver -> driver.findElements(attestazioniLocator).size() > numeroLinkAttestazioniOpponibile);
        // Recupero tutti i link
        List<WebElement> attestazioniFile = driver.findElements(attestazioniLocator);
        WebElement linkDaClickare = attestazioniFile.get(numeroLinkAttestazioniOpponibile);

        try {
            // Provo a cliccare direttamente se è cliccabile
            getWebDriverWait(15).withMessage("Il link non è cliccabile").until(ExpectedConditions.elementToBeClickable(linkDaClickare));
            linkDaClickare.click();
        } catch (TimeoutException e) {
            // Se non cliccabile, faccio scroll e click con JS o metodo custom
            scrollToElementAndClick(linkDaClickare);
        }
    }

    public void toBeClickableLinkAttestazioniOpponibile(int numeroLinkAttestazioniOpponibile) {

        // Locator dei bottoni
        By attestazioniLocator = By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: notifica presa in carico')]");

        // Attendo che ci siano abbastanza bottoni nel DOM
        getWebDriverWait(15).withMessage("Non sono stati trovati abbastanza link di attestazioni opponibili in toBeClickableLinkAttestazioniOpponibile")
                .until(driver -> driver.findElements(attestazioniLocator).size() > numeroLinkAttestazioniOpponibile);

        // Recupero il link desiderato
        WebElement linkDaControllare = driver.findElements(attestazioniLocator).get(numeroLinkAttestazioniOpponibile);

        // Attendo che sia cliccabile
        getWebDriverWait(15).withMessage("Il link non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(linkDaControllare));
    }

    public int getLinkAttestazioniOpponibili() {
        By attestazioniLocator = By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: ')]");

        List<WebElement> attestazioniFile = getWebDriverWait(10)
                .withMessage("Nessun link di attestazione opponibile trovato")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(attestazioniLocator);
                    return elements.isEmpty() ? null : elements;
                });

        return attestazioniFile.size();
    }

    public void selezioneVediDettaglioButton() {
        By vediDettagliLocator = By.id("more-less-timeline-step");
        By statiNotificaLocator = By.xpath("//*[contains(@class, 'MuiTimelineItem-root')]");

        // aspetta e clicca sul pulsante "Vedi dettagli"
        WebElement vediDettagliButton = getWebDriverWait(10)
                .withMessage("Il pulsante 'Vedi dettagli' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(vediDettagliLocator));

        scrollToElementAndClick(vediDettagliButton);

        // aspetta che compaiano gli stati della notifica
        List<WebElement> tuttiStatiNotificaList = getWebDriverWait(10)
                .withMessage("Gli stati della notifica non sono stati caricati")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(statiNotificaLocator);
                    return elements.isEmpty() ? null : elements;
                });

        if (!tuttiStatiNotificaList.isEmpty()) {
            logger.info("Tutti gli stati sono stati visualizzati correttamente");
        } else {
            Assertions.fail("Gli stati della notifica non sono stati visualizzati correttamente");
        }
    }

    public String getTextLinkAttestazioniOpponibili(int index) {
        By attestazioniLocator = By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: ')]");

        // Attende che ci sia almeno un elemento
        List<WebElement> attestazioniFile = getWebDriverWait(10)
                .withMessage("Nessun link 'Attestazione opponibile a terzi' trovato")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(attestazioniLocator);
                    return elements.isEmpty() ? null : elements;
                });

        if (index >= attestazioniFile.size()) {
            throw new IllegalArgumentException("Indice " + index + " fuori dal range. Trovati: " + attestazioniFile.size());
        }

        return attestazioniFile.get(index).getText();
    }


    public boolean isFieldDisplayed(By xpath) {
        getWebDriverWait(10).withMessage("Campo non trovato").until(ExpectedConditions.visibilityOfElementLocated(xpath));
        return element(xpath).isDisplayed();
    }

    public boolean isFieldNotDisplayed(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            // Se non ci sono elementi → sicuramente non è visibile
            if (elements.isEmpty()) {
                return true;
            }
            // Se ci sono elementi → controlla che nessuno sia displayed
            return elements.stream().noneMatch(WebElement::isDisplayed);
        } catch (NoSuchElementException e) {
            return true; // non trovato = non visibile
        }
    }

    public void waitLoadDettaglioNotificaAnnullataDESection() {

        By titleDettaglioNotifica = By.id("title-of-page");
        By statoNotifica = By.id("notification-state");
        By indietroButton = By.id("breadcrumb-indietro-button");
        By informazioni = By.id("notification-detail-table");
        By allegatiSection = By.id("notification-detail-document-attached");

        // AAR ha id diversi per portale mittente/destinatario
        By aarDownload = By.xpath("//div[@data-testid='notificationDetailDocuments']");
        By aarBox = By.xpath("//div[@data-testid='aarBox']");

        By attestazione = By.xpath("//button[@data-testid='download-legalfact']");

        // Banner notifica annullata ha id diversi
        By copyNotificaAnnullataDestinatario = By.xpath("//div[@data-testid='cancelledAlertText']");
        By copyNotificaAnnullataMittente = By.xpath("//div[@data-testid='alert']");

        By chipAnnullataInTimeline = By.id("Annullata-status");
        By linkAnnullamentoNotifica = By.xpath("//button[@data-testid='download-legalfact' and contains(., 'Dichiarazione annullamento notifica')]");

        getWebDriverWait(10).withMessage("il titolo Dettaglio notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotifica));

        getWebDriverWait(10).withMessage("il bottone indietro non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(indietroButton));

        getWebDriverWait(10).withMessage("Dettaglio notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(informazioni));

        getWebDriverWait(10).withMessage("La sezione Documenti allegati non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(allegatiSection));

        getWebDriverWait(10).withMessage("Lo stato della notifica non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(statoNotifica));

        getWebDriverWait(10).withMessage("La sezione recapiti non è visibile")
                .until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(aarDownload),
                        ExpectedConditions.visibilityOfElementLocated(aarBox)
                ));

        getWebDriverWait(10).withMessage("Il pulsante attestazione opponibile non è visibile")
                .until(ExpectedConditions.elementToBeClickable(attestazione));

        getWebDriverWait(10).withMessage("Il copy di notifica annullata non è visibile")
                .until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(copyNotificaAnnullataDestinatario),
                        ExpectedConditions.visibilityOfElementLocated(copyNotificaAnnullataMittente)
                ));

        getWebDriverWait(10).withMessage("La chip di notifica annullata non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(chipAnnullataInTimeline));

        getWebDriverWait(10).withMessage("Il link del documento di annullamento non è cliccabile")
                .until(ExpectedConditions.visibilityOfElementLocated(linkAnnullamentoNotifica));

        logger.info("Dettaglio Notifica Annullata Section caricata");
    }

    public void selezioneAvvisoPagoPa() {
        By checkboxAvvisoPagoPa = By.xpath("//span[@data-testid='radio-button']");
        try {
            WebElement element = getWebDriverWait(10).withMessage("Checkbox avviso PagoPA non trovata o non cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(checkboxAvvisoPagoPa));
            element.click();
            logger.info("Check su avviso PagoPA avvenuto con successo");
        } catch (TimeoutException e) {
            Assertions.fail("Check su avviso PagoPA non avvenuto con successo: " + e.getMessage());
        }
    }

    public void checkMessaggioScadenzaDownload() {
        getWebDriverWait(10)
                .withMessage("In messaggio Al momento non è possibile scaricare il documento non è visibile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Al momento non è possibile scaricare il documento')]")));

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
        By infoNotificheLocator = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-paddingNone MuiTableCell-sizeMedium css-11dv4ll')]");
        webTool.waitTime(3);

        List<WebElement> infoNotifiche = getWebDriverWait(10)
                .withMessage("Le info della notifica non sono visibili")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(infoNotificheLocator));

        if (i >= infoNotifiche.size()) {
            throw new IndexOutOfBoundsException("Indice " + i + " maggiore del numero di elementi trovati: " + infoNotifiche.size());
        }

        return infoNotifiche.get(i).getText();
    }

}
