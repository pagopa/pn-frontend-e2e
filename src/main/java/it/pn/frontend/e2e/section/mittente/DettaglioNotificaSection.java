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
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DettaglioNotificaSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaSection");

    @FindBy(xpath = "//button[contains(text(),'Attestazione opponibile a terzi: ')]")
    List<WebElement> attestazioniFile;

    @FindBy(xpath = "//button[contains(text(),'Vedi più dettagli')]")
    WebElement vediDettagliButton;

    @FindBy (xpath = "//li[contains(@class,'MuiTimelineItem-root MuiTimelineItem-positionRight MuiTimelineItem-missingOppositeContent css-1y9sx96')]")
    List<WebElement> tuttiStatiNotificaList;

    @FindBy (xpath = "//div[contains(@class,'MuiBox-root css-35ezg3')]")
    List<WebElement> infoNotifiche;

    @FindBy(xpath = "//button[contains(@data-testid,'documentButton')]" )
    List<WebElement> linkAllegati;
    @FindBy(xpath = "//button[contains(@data-testid,'breadcrumb-indietro-button')]")
    WebElement indietroButton;
    public DettaglioNotificaSection(WebDriver driver) {
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
    public void controlloDownload(){
        File partialPath = new File("src/test/resources/dataPopulation/downloadFileNotifica/mittente");
        File directory = new File(partialPath.getAbsolutePath());

        File[] fList = directory.listFiles(File::isFile);

        if (fList != null && fList.length > 0){
            for (File file : fList) {
                boolean result = file.delete();
                if (result) {
                    logger.info("File scaricato e eliminato");
                }
            }
        }else {
            logger.error("File non scaricato");
            Assert.fail("File non scaricato");
        }
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

    public Map<String, String> recuperoInfoNotifiche() {
        Map<String,String> infoNotifica = new HashMap<>();
        String mittente = getInfoNotifica(0);
        infoNotifica.put("mittente",mittente);
        String destinatario = getInfoNotifica(1);
        String codiceFiscale = getInfoNotifica(2);
        if(destinatario.contains("-")){
            String[] splitedDestinatario = destinatario.split(" - ");
            destinatario = splitedDestinatario[1];
            codiceFiscale = splitedDestinatario[0];
        }
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

    public void downloadFileNotifica(String path, String url,int nDownload) {
        try {
            URL urlPDF = new URL(url);
            File pdf = new File(path+"/pdfNotificaN"+nDownload+".pdf");
            FileUtils.copyURLToFile(urlPDF,pdf,30000,30000);
        } catch (IOException e) {
            logger.error("Errore nel downolad del file : "+e.getMessage());
        }
    }

    public void download(String urlLink, File fileLoc) {
        try {
            byte[] buffer = new byte[1024];
            double TotalDownload = 0.00;
            int readbyte = 0; //Stores the number of bytes written in each iteration.
            double percentOfDownload = 0.00;

            URL url = new URL(urlLink);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            double filesize = (double)http.getContentLengthLong();

            BufferedInputStream input = new BufferedInputStream(http.getInputStream());
            //fileLoc.createNewFile();
            FileOutputStream ouputfile = new FileOutputStream(fileLoc);
            BufferedOutputStream bufferOut = new BufferedOutputStream(ouputfile, 1024);
            while((readbyte = input.read(buffer, 0, 1024)) >= 0) {
                //Writing the content onto the file.
                bufferOut.write(buffer,0,readbyte);
                //TotalDownload is the total bytes written onto the file.
                TotalDownload += readbyte;
                //Calculating the percentage of download.
                percentOfDownload = (TotalDownload*100)/filesize;
                //Formatting the percentage up to 2 decimal points.
                String percent = String.format("%.2f", percentOfDownload);
                System.out.println("Downloaded "+ percent + "%");
            }
            System.out.println("Your download is now complete.");
            bufferOut.close();
            input.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

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

    public void clickVediPiuDettaglio() { this.vediDettagliButton.click();}

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

    public void clickLinkAvvenutaRicezione() {this.linkAllegati.get(1).click();}

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

    public int controlloNumeroPec(String mailPEC) {
        By mailPecBy = By.xpath("//p[contains(text(),'"+mailPEC+"')]");
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(mailPecBy));
        return this.elements(mailPecBy).size();
    }

    public void clickAllLinkDownload() {
        clickLinkDocumentiAllegati();
        clickLinkAvvenutaRicezione();
        clickLinkAttestazioneOpponibile("Attestazione opponibile a terzi: notifica presa in carico");
    }

    public int getLinkAttestazioniOpponubili() {
        return attestazioniFile.size();
    }

    public void clickLinkAttestazionipponibile(int numeroLinkAttestazioniOpponibile) {
        attestazioniFile.get(numeroLinkAttestazioniOpponibile).click();
    }
}
