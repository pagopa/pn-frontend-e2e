package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
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

public class DettaglioNotificaSection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DettaglioNotificaSection");
    @FindBy(xpath = "//button[contains(@data-testid,'documentButton')]" )
    List<WebElement> linkAllegati;

    @FindBy(xpath = "//button[contains(text(),'Attestazione opponibile a terzi: ')]")
    List<WebElement> attestazioniFile;

    public DettaglioNotificaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDettaglioNotificaSection() {

        try {
            By titleDettaglioNotificaField = By.xpath("//p[contains(text(),'Dettaglio notifica')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioNotificaField));
            logger.info("Dettaglio Notifica Section caricata");
        }catch (TimeoutException e){
            logger.error("Dettaglio Notifica Section non caricata con errore: "+ e.getMessage());
            Assert.fail("Dettaglio Notifica Section non caricata con errore: "+ e.getMessage());
        }
    }

    public void downloadFileNotifica(String path) {
        for ( WebElement link: this.linkAllegati) {
            link.click();
            String nomePdf = link.getText();
            for (int i = 0; i < 30; i++) {
                List<String> numTab = new ArrayList<>(this.driver.getWindowHandles());
                if (numTab.size() == 2){
                    this.driver.switchTo().window(numTab.get(1));
                    try {
                        URL urlPDF = new URL(this.driver.getCurrentUrl());
                        File partialPath = new File(path);
                        File pdf = new File(System.getProperty("user.dir")+partialPath+"/"+nomePdf+".pdf");
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

    public void downloadFileAttestazioni(String path) {
        for ( WebElement link: attestazioniFile) {
            link.click();
            String nomePdf = link.getText();
            for (int i = 0; i < 30; i++) {
                List<String> numTab = new ArrayList<>(this.driver.getWindowHandles());
                if (numTab.size() == 2) {
                    this.driver.switchTo().window(numTab.get(1));
                    try {
                        URL urlPDF = new URL(this.driver.getCurrentUrl());
                        File partialPath = new File(path);
                        File pdf = new File(System.getProperty("user.dir") + partialPath + "/" + nomePdf + ".pdf");
                        FileUtils.copyURLToFile(urlPDF, pdf, 1000, 1000);
                        this.driver.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.driver.switchTo().window(numTab.get(0));
                    break;
                } else {
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
}
