package it.pn.frontend.e2e.pages.radd;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoricoRichiestePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("StoricoRichiestePage");

    @FindBy(xpath = "//input[contains(@value, 'ACT')]")
    WebElement documentiAllegatiAttestazioniRadioButton;

    @FindBy(xpath = "//input[contains(@value, 'OPERATION_ID')]")
    WebElement IdOperazioneRadioButton;

    @FindBy(xpath = "//button[contains(text(),'Cerca')]")
    WebElement cercaButton;

    @FindBy(xpath = "//button[contains(text(),'Chiudi')]")
    WebElement chiudiButton;

    @FindBy(xpath = "//input[contains(@id, 'operationId')]")
    WebElement idOperazioneField;

    @FindBy(xpath = "//input[contains(@value,'IUN')]")
    WebElement IUNRadioButton;

    @FindBy(xpath = "//input[@id='iun']")
    WebElement IuntextField;

    @FindBy(xpath = "//button[contains(text(),'Nuova ricerca')]")
    WebElement nuovaRicercaButton;

    @FindBy(xpath = "//input[contains(@value,'TAX_ID')]")
    WebElement codiceFiscaleRadioButton;

    @FindBy(id = "taxId")
    WebElement codiceFiscaleDAAtextField;

    @FindBy(xpath = "//input[@id='operationId']")
    WebElement operationIdDAATextField;

    @FindBy(xpath = "//input[@id='from']")
    WebElement dataFromTextField;

    @FindBy(xpath = "//input[@id='to']")
    WebElement dataToTextField;

    @FindBy(xpath = "//input[contains(@value,'AOR')]")
    WebElement AARRadioButton;

    @FindBy(xpath = "//input[contains(@value,'PG')]")
    WebElement personaGiuridicaRadioButton;

    public StoricoRichiestePage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadStoricoRichieste(){
        try {
            By titlePageBy = By.xpath("//h4[contains(text(),'Richieste precedenti')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.logger.info("La pagina Storico delle Richieste è visualizzata correttamente");
        }catch (TimeoutException e){
            this.logger.error("La pagina Storico delle Richieste non è visualizzata correttamente con errore: "+e.getMessage());
            Assert.fail("La pagina Storico delle Richieste non è visualizzata correttamente con errore: "+e.getMessage());
        }
    }



    public void clickIdOperazioneRadioButton() {
        this.IdOperazioneRadioButton.click();
    }

    public void insertIdOperazione(String idOperazioneDAA) {
        this.idOperazioneField.sendKeys(idOperazioneDAA);
    }

    public void clickCercaButton() {
        this.cercaButton.click();
    }

    public int getNRighe(String idOperazioneDAA) {
        try {
            By rigaBy = By.xpath("//tr[td[contains(text(),'"+idOperazioneDAA+"')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(rigaBy));
            return this.elements(rigaBy).size();
        }catch (TimeoutException e){
            return 0;
        }
    }

    public void clickRichiestaRestituita(String idOperazione) {
      try {
          By richiestaBy = By.xpath("//td[contains(text(),'"+idOperazione+"')]");
          this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(richiestaBy));
          this.elements(richiestaBy).get(0).click();
      }catch (TimeoutException e){
          logger.error("Non sono riuscito a cliccare sulla richiesta con errore: "+e.getMessage());
          Assert.fail("Non sono riuscito a cliccare sulla richiesta con errore: "+e.getMessage());
      }
    }

    public void waitLoadDettaglioRichiesta() {
        try {
            By titleDettaglioRicheistaBy = By.xpath("//h2[contains(text(),'Dettaglio della richiesta')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleDettaglioRicheistaBy));
            logger.info("Il pop-up Dettaglio Richiesta viene visualizzato correttamente");
        }catch (TimeoutException e){
            logger.error("Il pop-up Dettaglio Richiesta viene visualizzato correttamente");
            Assert.fail("Il pop-up Dettaglio Richiesta viene visualizzato correttamente");
        }
    }

    public void clickChiudiButton() {
        this.chiudiButton.click();
    }

    public void waitLoadRisultatiRichiesste() {
        try {
            By titlePageBy = By.xpath("//h4[contains(text(),'Risultati della ricerca')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            logger.info("La pagina Risultati delle Richieste è visualizzata correttamente");
        }catch (TimeoutException e){
            logger.error("La pagina Risultati delle Richieste NON è visualizzata correttamente con errore: "+e.getMessage());
            Assert.fail("La pagina Risultati delle Richieste NON è visualizzata correttamente con errore: "+e.getMessage());
        }
    }

    public boolean selectIUNRadioButton() {
        return this.IUNRadioButton.isSelected(); }

    public boolean selectDocumentiAllegatiAttestazioRadioButton() {
        return this.documentiAllegatiAttestazioniRadioButton.isSelected();
    }

    public void insertCodiceIUN(String codiceIUNDAA) {this.IuntextField.sendKeys(codiceIUNDAA);}

    public void clickNuovaRicercaButton() {this.nuovaRicercaButton.click();}

    public void clickCodiceFiscaleRadioButton() {this.codiceFiscaleRadioButton.click();}

    public void insertCodiceFiscale(String codiceFiscaleDAA) {this.codiceFiscaleDAAtextField.sendKeys(codiceFiscaleDAA);}

    public String conversioneData(String data) {
        String [] date = data.split("-");
        return date[2]+"/"+date[1]+"/"+date[0];
    }

    public void insertDataInizio(String dataDA) {this.dataFromTextField.sendKeys(dataDA);}

    public void insertDataFine(String dataA) {this.dataToTextField.sendKeys(dataA);}

    public void waitErrorMessageIdOperazione(String errorText) {
        try {
            By messageErrorBy = By.xpath("//div[contains(text(),'"+errorText+"')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(messageErrorBy));
            logger.info("Si visualizza correttamente il messaggio di errore");
        }catch (TimeoutException e){
            logger.error("NON si visualizza correttamente il messaggio di errore con errore: "+e.getMessage());
            Assert.fail("NON si visualizza correttamente il messaggio di errore con errore: "+e.getMessage());
        }
    }
    public void clickAARRAdioButton() {this.AARRadioButton.click();}
    public void insertOperationID(String idOperazioneErroreAAR) {this.operationIdDAATextField.sendKeys(idOperazioneErroreAAR);}
    public void selectAvvisiAvvenutaRicezioneButton() { this.AARRadioButton.click();}
    public void clickPersonaGiuridicaRadioButton() {this.personaGiuridicaRadioButton.click();}

    public int getNRighe2(String partitaIVAPGAA) {
        try {
            By rigaBy = By.xpath("//tr[td[contains(text(),'MNZVMH95B09L084U')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(rigaBy));
            return this.elements(rigaBy).size();
        }catch (TimeoutException e){
            return 0;
        }
    }

    public void clickRichiestaRestituita1(String partitaIVAPGAA) {
        try {
            By richiestaBy = By.xpath("//tr[td[contains(text(),'MNZVMH95B09L084U')]]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(richiestaBy));
            this.elements(richiestaBy).get(0).click();
        }catch (TimeoutException e){
            logger.error("Non sono riuscito a cliccare sulla richiesta con errore: "+e.getMessage());
            Assert.fail("Non sono riuscito a cliccare sulla richiesta con errore: "+e.getMessage());
        }
    }
}
