package it.pn.frontend.e2e.section.destinatario;

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

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LeTueDelegheSection extends BasePage {

    private Logger logger = LoggerFactory.getLogger("LeTueDelegheSection");

    @FindBy(xpath = "//input[contains(@name,'selectPersonaFisicaOrPersonaGiuridica')]")
    WebElement personaFisicaRadioButton;

    @FindBy(xpath = "//input[contains(@id,'nome')]")
    List<WebElement> nomeCognomeList;

    @FindBy(xpath = "//button[contains(@data-testid,'createButton')]")
    WebElement inviaLaRichiestaButton;

    @FindBy(xpath = "//input[contains(@id,'expirationDate')]")
    WebElement dataTermineDelegaInput;

    @FindBy(xpath = "//div[contains(@data-testid, 'codeDigit')]")
    List<WebElement> codiceVerificaList;

    @FindBy(xpath = "//input[contains(@id,'codiceFiscale')]")
    WebElement codiceFiscaleInput;
    
    @FindBy(xpath = "//input[contains(@value,'entiSelezionati')]")
    WebElement SoloEntiSelezionatiRadioButton;
    //@FindBy(id ="enti-select")
    @FindBy(id ="ente-select")
    WebElement enteElementInput;

    @FindBy(xpath = "//button[contains(text(),'Torna alle deleghe')]")
    WebElement tornaDelegheButton;

    /*@FindBy(xpath = "//button[contains(@data-testid,'delegationMenuIcon')]")
    WebElement delegaMenuButton;*/

    @FindBy(xpath = "//li[contains(@tabindex,'-1')]")
    WebElement revocaDelegaOption;

    @FindBy(xpath = "//li[contains(@tabindex,'0')]")
    WebElement mostraCodiceOption;

    @FindBy(xpath = "//button[contains(text(),'Revoca la delega')]")
    WebElement revocaDialogAction;

    @FindBy(xpath = "//button[contains(@data-testid,'code cancel button')]")
    WebElement closeMostraCodiceButton;

    @FindBy(xpath = "//button[contains(@data-testid, 'createButton')]")
    WebElement inviaRichiestaButton;

    @FindBy(xpath = "//h4[contains(text(),'Deleghe')]")
    WebElement deleghePageTitle;

    @FindBy(xpath = "//p[contains(text(),'Qui puoi gestire')]")
    WebElement deleghePageSubtitle;

    @FindBy(xpath = "//button[contains(@data-testid,'add-delegation')]")
    WebElement aggiungiDelegaButton;

    @FindBy(xpath = "//span[contains(text(),'Nome')]")
    WebElement nomeDelegaField;

    @FindBy(xpath = "//th[contains(text(),'Inizio delega')]")
    WebElement inizioDelegaField;

    @FindBy(xpath = "//span[contains(text(),'Fine delega')]")
    WebElement fineDelegaField;

    @FindBy(xpath = "//th[contains(text(),'Permessi')]")
    WebElement permessiDelegaField;

    @FindBy(xpath = "//th[contains(text(),'Stato')]")
    WebElement statoDelegaField;

    public LeTueDelegheSection(WebDriver driver) {
        super(driver);
    }

    public void selectpersonaFisicaRadioButton() {
        this.personaFisicaRadioButton.click();
    }

    public void insertNomeCognome(String nome, String cognome) {
        this.nomeCognomeList.get(0).sendKeys(nome);
        this.nomeCognomeList.get(1).sendKeys(cognome);
    }
    


    public void clickSulBottoneInviaRichiesta() {
        this.inviaLaRichiestaButton.click();
        this.tornaDelegheButton.click();
    }

    public boolean verificareCheLaDataSiaCorretta() {
        String dataDaVerificare = this.dataTermineDelegaInput.getAttribute("value");
        dataDaVerificare = dataDaVerificare.replace("/", "-");
        String[] date = dataDaVerificare.split("-");
        dataDaVerificare = date[2] + "-" + date[1] + "-" + date[0];
        LocalDate dataInserita = LocalDate.parse(dataDaVerificare);
        LocalDate dataCorretta = LocalDate.now();
        dataCorretta = dataCorretta.plusDays(1);
        return dataCorretta.equals(dataInserita);
    }

    public String salvataggioCodiceVerifica() {
        String codiceVerifica = "";
        for (int i = 0; i < this.codiceVerificaList.size(); i++) {
            codiceVerifica = codiceVerifica + this.codiceVerificaList.get(i).getText();
        }
        return codiceVerifica;
    }

    public void inserireCF(String cf) {
        this.codiceFiscaleInput.sendKeys(cf);
    }

    public void selectSoloEntiSelezionati() {
        this.SoloEntiSelezionatiRadioButton.click();
    }

    public void selezionaUnEnte(String ente) {
        this.enteElementInput.sendKeys(ente);

    }


    public void clickOpzioneRevoca() {this.revocaDelegaOption.click();}


    public void clickRevocaLaDelega() {this.revocaDialogAction.click();}

    public void siVisualizzaUnaDelega() {
        try{
            By menuDelega = By.xpath("//tr[contains(@class,'MuiTableRow-root css-g76qb5')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            logger.info("Trovato correttamente almeno una delega");
        }catch (TimeoutException e){
            logger.error("Deleghe NON trovate con errore: "+e.getMessage());
            Assert.fail("Deleghe NON trovate con errore: "+e.getMessage());
        }
    }

    public void clickMenuDelega() {
        try{
            By menuDelega = By.xpath("//button[contains(@data-testid,'delegationMenuIcon')]");
            try {                                   // Questo sleep l'abbiamo messo perché
                TimeUnit.SECONDS.sleep(5);   // il sito ci mette un pò a caricarsi
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            WebElement menuDelegaWebElement = this.driver.findElement(menuDelega);
            menuDelegaWebElement.click();
            logger.info("cliccato correttamente su menu delega button");
        }catch (TimeoutException e){
            logger.error("Menu delega button NON trovata con errore: "+e.getMessage());
            Assert.fail("Menu delega button NON trovata con errore: "+e.getMessage());
        }
    }

    public void clickInviaRichiesta() {this.inviaLaRichiestaButton.click();}

    public void MessaggioDiErroreDelegaASeStessi() {
        try {
            By ErrorMessage = By.xpath("//div[contains(text(),'Non è possibile delegare se stessi')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(ErrorMessage));
            logger.info("Messaggio di errore trovato");
        }catch (TimeoutException e) {
            logger.error("Messaggio di errore non trovato"+e.getMessage());
            Assert.fail("Messaggio di errore non trovato"+e.getMessage());
        }
    }

    public void siSceglieLOpzioneMostraCodice() {this.mostraCodiceOption.click();}

    public void siCliccaSulBottoneChiudi() {this.closeMostraCodiceButton.click();}

    public void siVisualizzaIlTitolo() {this.deleghePageTitle.isDisplayed();}

    public void siVisualizzaIlSottotitolo() {this.deleghePageSubtitle.isDisplayed();}

    public void siVisualizzaIlBottoneAggiungiUnaDelega() {this.aggiungiDelegaButton.isDisplayed();}

    public void siVisualizzanoTuttiICampiDellElencoDeiDelegati() {
        this.nomeDelegaField.isDisplayed();
        this.inizioDelegaField.isDisplayed();
        this.fineDelegaField.isDisplayed();
        this.permessiDelegaField.isDisplayed();
        this.permessiDelegaField.isDisplayed();
    }
}


