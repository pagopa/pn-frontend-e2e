package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DettaglioNotificaMittenteSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaSection");

    @FindBy(xpath = "//button[contains(text(),'Vedi più dettagli')]")
    WebElement vediDettagliButton;

    @FindBy (xpath = "//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-paddingNone MuiTableCell-sizeMedium css-11dv4ll')]")
    List<WebElement> infoNotifiche;

    @FindBy(xpath = "//button[contains(@data-testid,'documentButton')]" )
    List<WebElement> linkAllegati;
    @FindBy(xpath = "//button[contains(@data-testid,'breadcrumb-indietro-button')]")
    WebElement indietroButton;
    public DettaglioNotificaMittenteSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaSection(){
        try {
            By titleDettaglioNotificaField = By.xpath("//p[contains(text(),'Dettaglio notifica')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
            logger.info("Dettaglio Notifica Section caricata");
        }catch (TimeoutException e){
            logger.error("Dettaglio Notifica Section non caricata con errore: "+ e.getMessage());
            Assert.fail("Dettaglio Notifica Section non caricata con errore: "+ e.getMessage());
        }

    }

    public Map<String, String> recuperoInfoNotifiche() {
        Map<String,String> infoNotifica = new HashMap<>();
        String mittente = getInfoNotifica(0);
        infoNotifica.put("mittente",mittente);
        String destinatario = getInfoNotifica(1);
        String codiceFiscale = getInfoNotifica(2);
        if(destinatario.contains(" - ")){
            String[] splittedDestinatario = destinatario.split(" - ");
            String[] splittedDestinatarioName = splittedDestinatario[1].split("\n");
            destinatario = splittedDestinatarioName[0];
            codiceFiscale = splittedDestinatario[0];
            infoNotifica.put("destinatario",destinatario);
            infoNotifica.put("codiceFiscale",codiceFiscale);
            String data = getInfoNotifica(2);
            infoNotifica.put("data",data);
            if (controlloCodice()){
                String codiceIUN = getInfoNotifica(3);
                infoNotifica.put("codiceIUN", codiceIUN);
                infoNotifica.put("codiceAvviso","nd");
            }else {
                String codiceAvviso = getInfoNotifica(3);
                infoNotifica.put("codiceAvviso", codiceAvviso);
                infoNotifica.put("codiceIUN","nd");
            }
        }else {
            infoNotifica.put("destinatario",destinatario);
            infoNotifica.put("codiceFiscale",codiceFiscale);
            String data = getInfoNotifica(3);
            infoNotifica.put("data",data);
            if (controlloCodice()){
                String codiceIUN = getInfoNotifica(4);
                infoNotifica.put("codiceIUN", codiceIUN);
                infoNotifica.put("codiceAvviso","nd");
            }else {
                String codiceAvviso = getInfoNotifica(4);
                infoNotifica.put("codiceAvviso", codiceAvviso);
                infoNotifica.put("codiceIUN","nd");
            }
        }

        return infoNotifica;
    }


    private boolean controlloCodice() {
        try {
            By codiceIUNBy = By.xpath("//td[contains(text(),'Codice IUN')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    private String getInfoNotifica(int i) {
        return infoNotifiche.get(i).getText();
    }

    public boolean controlloTestoFile(String path, String testoDaControllare) {

        File file = new File(System.getProperty("user.dir")+path);
        try {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String testoFile = pdfStripper.getText(pdfFile).replaceAll ("\r\n|\r|\n", "");
            logger.info(testoFile);
            if (testoFile.contains(testoDaControllare)){
                pdfFile.close();
                return true;
            }
            pdfFile.close();
        } catch (IOException e) {
            logger.error("File non trovato con errore: "+e.getMessage());
            Assert.fail("File non trovato con errore: "+e.getMessage());
        }
        return false;
    }

    public void clickVediPiuDettaglio() {
        getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.vediDettagliButton));
        this.vediDettagliButton.click();
    }

    public int siVisualizzaPercosoNotifica() {
        By percorsoNotificaBy = By.xpath("//div[contains(@data-testid,'itemStatus')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(percorsoNotificaBy));
        return this.elements(percorsoNotificaBy).size();
    }

    public void clickIndietroButton() {this.indietroButton.click();}

    public boolean controlloTestoFileCodiceIUN(String path, String codiceIUN) {
        File file = new File(System.getProperty("user.dir")+path);
        try {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String testoFile = pdfStripper.getText(pdfFile).replaceAll ("\r\n|\r|\n", " ");
            String testoDaControllare = codiceIUN.split("-")[0];
            if (testoFile.contains(testoDaControllare)){
                pdfFile.close();
                return true;
            }
            pdfFile.close();
        } catch (IOException e) {
            logger.error("File non trovato con errore: "+e.getMessage());
            Assert.fail("File non trovato con errore: "+e.getMessage());
        }
        return false;

    }

    public boolean controlloTestoFileData(String path, String testoDaControllare) {
        File file = new File(System.getProperty("user.dir")+path);
        try {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String testoFile = pdfStripper.getText(pdfFile).replaceAll ("\r\n|\r|\n", " ");
            if (testoDaControllare.equals("Oggi")){
              String data =  LocalDate.now().toString();
              String[] date = data.split("-");
              testoDaControllare = date[2]+"/"+date[1]+"/"+date[0] ;
            }
            if (testoFile.contains(testoDaControllare)){
                pdfFile.close();
                return true;
            }
            pdfFile.close();
        } catch (IOException e) {
            logger.error("File non trovato con errore: "+e.getMessage());
            Assert.fail("File non trovato con errore: "+e.getMessage());
        }
        return false;
    }

    public void clickLinkDocumentiAllegati() {
     this.linkAllegati.get(0).click();
    }

    public void clickLinkAvvenutaRicezione(int i) {this.linkAllegati.get(i).click();}

    public void clickLinkAttestazioneOpponibile(String nomeFile) {
        try {
            By fileLinkBy = By.xpath("//button[contains(text(),'"+nomeFile+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(fileLinkBy));
            WebElement fileLink = this.element(fileLinkBy);
            if (fileLink.isDisplayed()){
                fileLink.click();
            }else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", fileLink);
                fileLink.click();
            }
        }catch (TimeoutException e){
            logger.error("Non riuscito ad trovare il link con errore: "+e.getMessage());
            Assert.fail("Non riuscito ad trovare il link con errore: "+e.getMessage());
        }
    }

    public int getLinkAvvenutaRicezione() {
        return linkAllegati.size();
    }

    public void clickLinkAttestazioneOpponibileAvvenutoAccesso() {
        try {
            By fileLinkBy = By.xpath("//button[contains(text(),'Attestazione opponibile a terzi: avvenuto accesso')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(fileLinkBy));
            List<WebElement> fileLink = this.elements(fileLinkBy);
            if (fileLink.get(fileLink.size()-1).isDisplayed()){
                fileLink.get(fileLink.size()-1).click();
            }else {
                this.js().executeScript("arguments[0].scrollIntoView(true);", fileLink);
                fileLink.get(fileLink.size()-1).click();
            }
        }catch (TimeoutException e){
            logger.error("Non riuscito ad trovare il link con errore: "+e.getMessage());
            Assert.fail("Non riuscito ad trovare il link con errore: "+e.getMessage());
        }
    }

    public void verificaInviPECInCorso() {
        try{
            By invioPec = By.xpath("//div/span[contains(text(),'Invio via PEC')]/following-sibling::div//p[contains(text(),'È in corso l')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(invioPec));
            logger.info("La pec è in stato invio in corso");
        }catch (TimeoutException e){
            logger.error("La pec NON è in stato invio in corso con errore: "+e.getMessage());
            Assert.fail("La pec NON è in stato invio in corso con errore: "+e.getMessage());
        }
    }
}
