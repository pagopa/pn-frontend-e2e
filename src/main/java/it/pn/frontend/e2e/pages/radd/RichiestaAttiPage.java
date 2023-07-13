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

public class RichiestaAttiPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RichiestaAttiPage");

    @FindBy(xpath = "//input[contains(@value,'PF')]")
    WebElement radioButtonPF;
    private int numeroFile = 0;

    public RichiestaAttiPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[contains(@id,'iun')]")
    WebElement iunInput;
    @FindBy(xpath = "//input[contains(@id,'recipientTaxId')]")
    WebElement codiceFiscaleInput;
    @FindBy(xpath = "//button[contains(text(),'Continua')]")
    WebElement continuaButton;

    @FindBy(xpath = "//span[contains(text(),'Caricamento documenti')]")
    WebElement caricamentoDocumentiSection;

    @FindBy(xpath = "//p[contains(@data-testid,'loadFrom')]")
    WebElement uploadDocumentLink;

    @FindBy(css = "div[data-testid='file-input-recipient-id'] > input[accept='image/jpeg,image/png']")
    WebElement selezionaloDalTuoComputerInput;

    @FindBy(css = "div[data-testid='file-input-delegate-act'] > input[accept='image/jpeg,image/png']")
    WebElement selezionaloDalTuoComputerInputModuloDelega;

    @FindBy(css = "div[data-testid='file-input-delegate-id'] > input[accept='image/jpeg,image/png']")
    WebElement selezionaloDalTuoComputerInputDelegato;

    @FindBy(xpath = "//button[contains(text(),'Continua')]")
    WebElement continua2Button;

    @FindBy(xpath = "//span[contains(text(),'Scarica')]")
    WebElement downloadButton;

    @FindBy(xpath = "//button[contains(text(),'Ho finito')]")
    WebElement hoFinitoButton;

    @FindBy(xpath = "//button[contains(text(),'Chiudi')]")
    WebElement chiudiButton;

    @FindBy(css = "input#delegateTaxId")
    WebElement codiceFiscaleDelegatoInput;

    @FindBy(xpath = "//h6[contains(text(),'Allegati e attestazioni')]")
    WebElement attestazioniPageTtile;

    @FindBy(xpath = "//div[contains(text(),'Prima di premere “Ho finito”, assicurati di avere scaricato tutti i documenti.')]")
    WebElement assicuratiText;

    @FindBy(xpath = "//a[contains(text(),'storico delle ricerche')]")
    WebElement storicoLink;

    @FindBy(xpath = "//button[contains(text(),'Torna alla home')]")
    WebElement tornaHomeButton;

    @FindBy(xpath = "//div[contains(text(),'Stampa già eseguita')]")
    WebElement errorMessage;

    @FindBy(xpath = "//div[contains(text(),'KO')]")
    WebElement uploadErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Risorsa non trovata')]")
    WebElement downloadMessageError;

    @FindBy(xpath = "//div[contains(text(),'Estensione file non supportata')]")
    WebElement estenzioneErrorMessage;

    @FindBy(xpath = "//button[contains(text(),'Indietro')]")
    WebElement indietroButton;

    @FindBy(xpath = "//input[contains(@value,'PG')]")
    WebElement personaGiuridicaRadioButton;

    @FindBy(id="taxId")
    WebElement partitaIvaTextField;

    public void insertCodiceIun(String codiceIun) {
        this.iunInput.sendKeys(codiceIun);
    }

    public boolean selectSoggettoGiuridico() {
        return this.radioButtonPF.isSelected();
    }

    public void insertCodiceFiscale(String codiceFiscale) {
        this.codiceFiscaleInput.sendKeys(codiceFiscale);
    }

    public void clickContinuaButton() {
        this.js().executeScript("arguments[0].click()",this.continuaButton);
        //this.continuaButton.click();
    }

    public void waitLoadCaricamentoDocumenti() {
        try {
            By caricamentoDocumentiBy = By.xpath("//span[contains(text(),'Caricamento documenti')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(caricamentoDocumentiBy));
            logger.info("La sezione carimento documenti si visualizza correttamente");
        } catch (Exception e) {
            logger.error("La sezione carimento documenti NON si visualizza correttamente:" + e.getMessage());
            Assert.fail("La sezione carimento documenti NON si visualizza correttamente:" + e.getMessage());
        }
    }

    public void uploadDocumentoDestinatario() {
        this.uploadDocumentLink.click();

    }

    public void uploadFilefromPC(String pathDocumentiFile) {
        if (!this.selezionaloDalTuoComputerInput.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true)", this.selezionaloDalTuoComputerInput);
            this.selezionaloDalTuoComputerInput.sendKeys(pathDocumentiFile);
        } else {
            this.selezionaloDalTuoComputerInput.sendKeys(pathDocumentiFile);
        }
    }


    public void clickContinua2Button() {
        this.continua2Button.click();
    }

    public void waitLoadAllegatiAttestazioni() {
        try {
            By allegatiAttestazioniBy = By.xpath("//h6[contains(text(),'Allegati')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(allegatiAttestazioniBy));
            logger.info("La sezione allegati e attestazioni si visualizza correttamente");
        } catch (Exception e) {
            logger.error("La allegati e attestazioni NON si visualizza correttamente:" + e.getMessage());
            Assert.fail("La allegati e attestazioni NON si visualizza correttamente:" + e.getMessage());
        }
    }


    public void clickDownload() {
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

    public void clickHoFinito() {
/*        if (this.hoFinitoButton.isDisplayed()){
            this.hoFinitoButton.click();
        }else {
            this.js().executeScript("arguments[0].scrollIntoView(true);",this.hoFinitoButton);
            this.hoFinitoButton.click();
        }*/
        try {
            By hoFinitoButton = By.xpath("//button[contains(text(),'Ho finito')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(hoFinitoButton));
            logger.info("Ho trovoto il bottone Ho Finito");
            this.element(hoFinitoButton).click();
        } catch (TimeoutException e) {
            logger.error("NON ho trovoto il bottone Ho Finito con errore:" + e.getMessage());
            Assert.fail("NON ho trovoto il bottone Ho Finito con errore:" + e.getMessage());
        }
    }

    public void waitLoadConfirmationPage() {
        By confirmationPageTitle = By.xpath("//h4[contains(text(),'Grazie!')]");
        try {
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(confirmationPageTitle));
            logger.info("La pagina di conferma viene visualizzata correttamente");
        } catch (TimeoutException e) {
            logger.error("La pagina di conferma NON viene visualizzata correttamente con errore:" + e.getMessage());
            Assert.fail("La pagina di conferma NON viene visualizzata correttamente con errore:" + e.getMessage());
        }
    }

    public void clickChiudiButton() {
        this.chiudiButton.click();
    }

    public void insertCodiceFiscaleDelegato(String codiceFiscale) {
        this.codiceFiscaleDelegatoInput.sendKeys(codiceFiscale);
    }

    public void uploadFilefromPC1(String pathDocumentiFile1) {
        if (!this.selezionaloDalTuoComputerInputDelegato.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true)", this.selezionaloDalTuoComputerInputDelegato);
            this.selezionaloDalTuoComputerInputDelegato.sendKeys(pathDocumentiFile1);
        } else {
            this.selezionaloDalTuoComputerInputDelegato.sendKeys(pathDocumentiFile1);
        }
    }

    public void uploadFilefromPC2(String pathDocumentiFile2) {
        if (!this.selezionaloDalTuoComputerInputModuloDelega.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true)", this.selezionaloDalTuoComputerInputModuloDelega);
            this.selezionaloDalTuoComputerInputModuloDelega.sendKeys(pathDocumentiFile2);
        } else {
            this.selezionaloDalTuoComputerInputModuloDelega.sendKeys(pathDocumentiFile2);
        }
    }


    public boolean attestazioniSectionIsDisplayed() {
        waitLoadPage();
        return this.attestazioniPageTtile.isDisplayed();
    }

    public void assicuratiIsDisplayed() {
        this.assicuratiText.isDisplayed();
    }

    public void clickLinkStorico() {
        this.storicoLink.click();
    }

    public void waitLoadLandingPage() {
        try {
            By landPageTitleBy = By.xpath("//h4[contains(text(),'Qualcosa è andato storto')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(landPageTitleBy));
            logger.info("La landing page si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("La landing page NON si visualizza correttamente con errore:" + e.getMessage());
            Assert.fail("La landing page NON si visualizza correttamente con erroe:" + e.getMessage());
        }

    }

    public void clickTornaHomeButton() {
        this.tornaHomeButton.click();
    }

    public boolean codiceFiscaleErrorMessage() {
        return this.errorMessage.isDisplayed();
    }

    public boolean upLoadErrorMessage() {
        return this.uploadErrorMessage.isDisplayed();
    }


    public boolean downloadErrorMessage() {
        return this.downloadMessageError.isDisplayed();
    }

    public void controlloDownload() {
        File partialPath = new File("src/test/resources/dataPopulation/downloadFileNotifica/RADD");
        File directory = new File(partialPath.getAbsolutePath());

        File[] fList = directory.listFiles(File::isFile);

        if (fList != null && fList.length > 0) {
            for (File file : fList) {
                boolean result = file.delete();
                if (result) {
                    logger.info("File scaricato e eliminato");
                }
            }
        } else {
            logger.error("File non scaricato");
            Assert.fail("File non scaricato");
        }
    }

    public boolean upLoadErrorMessageEstenzione() {
        return this.estenzioneErrorMessage.isDisplayed();
    }

    public boolean hoFinitoButtonEAbilitato() {
        return this.hoFinitoButton.getAttribute("disabled")==null;
    }

    public void clickHomePageButton() {
        this.indietroButton.click();
    }

    public void clickPersonaGiuridicaButton() {this.personaGiuridicaRadioButton.click();}

    public void insertPartitaIva(String partitaIVAPGDA) {
        this.partitaIvaTextField.sendKeys(partitaIVAPGDA);
    }
}





