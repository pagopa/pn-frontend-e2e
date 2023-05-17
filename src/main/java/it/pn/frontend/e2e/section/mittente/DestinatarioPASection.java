package it.pn.frontend.e2e.section.mittente;

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

public class DestinatarioPASection extends BasePage {

    private static  final Logger logger = LoggerFactory.getLogger("DestinatarioPASection");

    @FindBy(css = "input[value='PF']")
    WebElement personaFisicaCheckBox;

    @FindBy(xpath = "//input[contains(@id,'firstName')]" )
    WebElement nomeDestinatarioTextField;

    @FindBy(xpath = "//input[contains(@id,'lastName')]")
    WebElement cognomeDestinatarioTextField;

    @FindBy(xpath = "//input[contains(@id,'taxId')]")
    WebElement codiceFiscaleDestinatarioTextField;

    @FindBy(xpath = "//input[contains(@name,'showPhysicalAddress')]")
    WebElement aggiungiUnIndirizzoFisicoCheckBox;

    @FindBy(xpath = "//label[contains(@id,'address-label')]/following-sibling::div/input")
    WebElement indirizzoTextField;

    @FindBy(xpath = "//input[contains(@id,'houseNumber')]")
    WebElement numeroCivicoTextField;

    @FindBy(xpath = "//label[contains(@id,'municipalityDetails-label')]/following-sibling::div/input")
    WebElement localitaTextField;

    @FindBy(xpath = "//label[contains(@id,'municipality-label')]/following-sibling::div/input")
    WebElement comuneTextField;

    @FindBy(xpath = "//input[contains(@id,'province')]")
    WebElement provinciaTextField;

    @FindBy(xpath = "//input[contains(@id,'zip')]")
    WebElement codicePostaleTextField;

    @FindBy(xpath = "//input[contains(@id,'foreignState')]")
    WebElement statoTextField;

    public DestinatarioPASection(WebDriver driver) {
        super(driver);
    }

    public String ricercaInformazione(String[] dati, int posizioneDestinatario ){
        String datoDestianario = dati[posizioneDestinatario];
        if (posizioneDestinatario == 0){
            datoDestianario = datoDestianario.replace("[","");
        }else if (posizioneDestinatario == 4){
            datoDestianario = datoDestianario.replace("]","");
        }
        return datoDestianario;
    }

    public void waitLoadDestinatarioPASection() {
        try{
            By titleDestinatarioFieald = By.xpath("//h3[contains(text(),'Destinatario')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleDestinatarioFieald));
            logger.info("Destinatario PA Section caricata ");
        }catch (TimeoutException e){
            logger.error("Destinatario PA Section non caricata con errore : "+e.getMessage());
            Assert.fail("Destinatario PA Section non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionarePersonaFisica() {
        this.personaFisicaCheckBox.click();
    }

    public void inserireNomeDestinatario(String nomeDestinatario) {
        this.scrollToElementClickAndInsertText(this.nomeDestinatarioTextField, nomeDestinatario);
    }

    public void inserireCognomeDestinatario(String cognomeDestinatario) {
        this.scrollToElementClickAndInsertText(this.cognomeDestinatarioTextField, cognomeDestinatario);
    }

    public void inserireCodiceFiscaleDestinatario(String codiceFiscale) {
        this.scrollToElementClickAndInsertText(this.codiceFiscaleDestinatarioTextField,codiceFiscale);
    }
    
    private void scrollToElementClickAndInsertText(WebElement element, String text){
        if(!element.isDisplayed()){
            this.js().executeScript("arguments[0].scrollIntoView(true);",element);
        }
        element.click();
        element.sendKeys(text);
    }

    public void selezionaAggiungiUnIndirizzoFisico() {
        this.aggiungiUnIndirizzoFisicoCheckBox.click();
    }

    public void inserireIndirizzo(String indirizzo) {
        this.scrollToElementClickAndInsertText(this.indirizzoTextField, indirizzo);
    }

    public void inserireNumeroCivico(String numeroCivico) {
        this.scrollToElementClickAndInsertText(this.numeroCivicoTextField, numeroCivico);
    }

    public void inserireLocalita(String localita) {
        this.scrollToElementClickAndInsertText(this.localitaTextField, localita);
    }

    public void inserireComune(String comune) {
        this.scrollToElementClickAndInsertText(this.comuneTextField,comune);
    }

    public void inserireProvincia(String provincia) {
        this.scrollToElementClickAndInsertText(this.provinciaTextField, provincia);
    }

    public void inserireCodicePostale(String codicePostale) {
        this.scrollToElementClickAndInsertText(this.codicePostaleTextField,codicePostale);
    }

    public void inserireStato(String stato) {
        this.scrollToElementClickAndInsertText(this.statoTextField, stato);
    }

}
