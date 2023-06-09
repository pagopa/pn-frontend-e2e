package it.pn.frontend.e2e.pages.radd;

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

public class AvvisiAvvenutaRicezionePage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("AvvisiAvvenutaRicezionePage");

    @FindBy(xpath = "//input[contains(@value,'PF')]")
    WebElement personaFiscaRadioButton;

    @FindBy(xpath = "//input[@id='recipientTaxId']")
    WebElement codiceFiscaleInput;

    @FindBy(xpath = "//input[@id='delegateTaxId']")
    WebElement codiceFiscaleDelegatoInput;

    @FindBy(css = "div[data-testid='file-input-recipient-id'] > input[accept='image/jpeg,image/png']")
    WebElement uploadBox;

    @FindBy(css = "div[data-testid='file-input-delegate-id'] > input[accept='image/jpeg,image/png']")
    WebElement uploadBoxDelegato;

    @FindBy(css = "div[data-testid='file-input-delegate-act'] > input[accept='image/jpeg,image/png']")
    WebElement uploadBoxModulo;

    @FindBy(xpath = "//button[contains(text(),'Continua')]")
    WebElement continuaButton;

    private int numeroFile = 0;

    @FindBy(xpath = "//button[contains(text(),'Ho finito')]")
    WebElement hoFinitoButton;

    @FindBy(xpath = "//button[contains(text(),'Indietro')]")
    WebElement indietroButton;

    public AvvisiAvvenutaRicezionePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAvvenutaRicezione() {
        try {
            By titlePageBy = By.xpath("//h4[contains(@class,'MuiTypography-root MuiTypography-h4 ')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            logger.info("La pagina Avvisi di avvenuta ricezione caricata");
        }catch (TimeoutException e){
            logger.error("La pagina Avvisi di avvenuta ricezione NON caricata con errore: "+e.getMessage());
            Assert.fail("La pagina Avvisi di avvenuta ricezione NON caricata con errore: "+e.getMessage());
        }
    }

    public boolean checkPersonaFisica() {
        return this.personaFiscaRadioButton.isSelected();
    }

    public void inserimentoCFDDestinatario(String cfAAR) {
        this.codiceFiscaleInput.sendKeys(cfAAR);
    }

    public void inserimentoCFDelegato(String cfDAAV) {
        this.codiceFiscaleDelegatoInput.sendKeys(cfDAAV);
    }

    public void waitLoadCaricamentoDocumenti() {
        try {
            By titlePage = By.xpath("//h6[contains(@class,'MuiTypography-root MuiTypography-h6')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("Caricamento Documenti section caricata correttamente");
        }catch (TimeoutException e){
            logger.error("Caricamento Documenti section non caricata correttamente con errore: "+ e.getMessage());
            Assert.fail("Caricamento Documenti section non caricata correttamente con errore: "+ e.getMessage());
        }
    }

    public void uploadDocumentoDelegato(String path) {

        if (this.uploadBoxDelegato.isDisplayed()){
            this.uploadBoxDelegato.sendKeys(path);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true)",this.uploadBoxDelegato);
            this.uploadBoxDelegato.sendKeys(path);
        }

    }

    public void uploadDocumento(String path) {

        if (this.uploadBox.isDisplayed()){
            this.uploadBox.sendKeys(path);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true)",this.uploadBox);
            this.uploadBox.sendKeys(path);
        }

    }

    public void uploadDocumentoModulo(String path) {

        if (this.uploadBoxModulo.isDisplayed()){
            this.uploadBoxModulo.sendKeys(path);
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true)",this.uploadBoxModulo);
            this.uploadBoxModulo.sendKeys(path);
        }

    }


    public void clickContinuaButton() {
        this.continuaButton.click();
    }

    public void waitLoadAvvenutaRicezioneUltimoStep() {
        try {
            By terzoStepARBy = By.xpath("//h6[contains(text(),'Avvisi di avvenuta ricezione')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(terzoStepARBy));
            logger.info("La section avvisi avvenuta ricezione si visualizza correttamente");
        }catch (TimeoutException e){
            logger.error("La section avvisi avvenuta ricezione si visualizza correttamente con errore:"+e.getMessage());
            Assert.fail("La section avvisi avvenuta ricezione si visualizza correttamente con errore:"+e.getMessage());
        }
    }

    private void closeTab(String url) {
        List<String> numTab = null;
        boolean tabOpen = false;
        for (int i = 0; i < 30; i++) {
            numTab = new ArrayList<>(this.driver.getWindowHandles());

            if (numTab.size() >= 2) {
                tabOpen = true;
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (tabOpen) {
                int index = 1;
                while (index < numTab.size()) {
                    this.driver.switchTo().window(numTab.get(index));
                    this.downloadFile(url);
                    this.driver.close();
                    this.driver.switchTo().window(numTab.get(0));
                    index++;
                }
            } else {
                Assert.fail("tab per download non si è aperto");
            }
        }

    }
    private void downloadFile(String url) {
        try {
            URL urlPDF = new URL(url);
            File pdf = new File(System.getProperty("user.dir") + "/src/test/resources/dataPopulation/downloadFileNotifica/RADD/fileN" + this.numeroFile);
            numeroFile++;
            FileUtils.copyURLToFile(urlPDF, pdf, 1000, 1000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void scaricaTuttiDocumenti() {
        try {
            By scaricaButtonby = By.xpath("//a[span[contains(text(),'Scarica')]]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(scaricaButtonby));
            List<WebElement> downloadLinks = this.elements(scaricaButtonby);
            for (int i = 0; i < downloadLinks.size(); i++) {
                String url = downloadLinks.get(i).getAttribute("href");
                downloadLinks.get(i).click();
                this.closeTab(url);
            }
            logger.info("ha cliccato quello che doveva");
        } catch (TimeoutException e) {
            logger.error("Non ha cliccato");
            Assert.fail("Non ha cliccato");
        }
    }

    public boolean hoFinitoButtonAbilitato() {
        return this.hoFinitoButton.getAttribute("disabled")==null;
    }

    public void clickArrowBack() {
        this.indietroButton.click();
    }
}