package it.pn.frontend.e2e.section.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DettaglioNotificaFRSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaPFSection");

    @FindBy(xpath = "//button[contains(text(),'Attestazione opponibile a terzi: ')]")
    List<WebElement> attestazioniFile;

    public DettaglioNotificaFRSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaDESection() {
        try {
            By titleDettaglioNotificaField = By.xpath("//p[contains(text(),'Dettaglio notifica')]");
            By statoNotifcaBy = By.xpath("//h5[contains(text(),'Stato della notifica')]");
            By indietroButtonBy = By.xpath("//button[contains(text(),'Indietro')]");
            By informazioniBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-paddingNone MuiTableCell-sizeMedium css-cfu1vj')]");
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

    public void downloadFileNotifica(String path, String url, int nDownload){
        try {
            URL urlPDF = new URL(url);
            File pdf = new File(path+"/pdfNotificaN"+nDownload+".pdf");
            FileUtils.copyURLToFile(urlPDF,pdf,30000,30000);
        } catch (IOException e) {
            logger.error("Errore nel downolad del file : "+e.getMessage());
        }
    }

    public int getLinkAttestazioniOpponubili() {
        return attestazioniFile.size();
    }
}
