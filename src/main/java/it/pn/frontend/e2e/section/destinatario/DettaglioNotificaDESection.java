package it.pn.frontend.e2e.section.destinatario;

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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DettaglioNotificaDESection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaDESection");

    @FindBy(xpath = "//button[contains(text(),'Attestazione opponibile a terzi: ')]")
    List<WebElement> attestazioniFile;

    public DettaglioNotificaDESection(WebDriver driver) {
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

    public void downloandFileAttestazioni(String path){
        int count = 1;
        for ( WebElement link: attestazioniFile) {
            link.click();

            for (int i = 0; i < 30; i++) {
                List<String> numTab = new ArrayList<>(this.driver.getWindowHandles());
                if (numTab.size() == 2){
                    this.driver.switchTo().window(numTab.get(1));
                    try {
                        URL urlPDF = new URL(this.driver.getCurrentUrl());
                        File partialPath = new File(path);
                        File pdf = new File(System.getProperty("user.dir")+partialPath+"/attestazione n° "+count+".pdf");
                        FileUtils.copyURLToFile(urlPDF,pdf,1000,1000);
                        this.driver.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.driver.switchTo().window(numTab.get(0));
                    break;
                }else {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void controlloDownload() {
        File partialPath = new File("/src/test/resources/dataPopulation/downloadFileNotifica/destinatario");
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
}
