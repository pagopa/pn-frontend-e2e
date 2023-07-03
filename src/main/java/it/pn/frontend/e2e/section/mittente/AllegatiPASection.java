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

public class AllegatiPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AllegatiSection");

    @FindBy(css = "div[data-testid='fileInput'] > input[accept='application/pdf']")
    WebElement selezionaloDalTuoComputerInput;

    @FindBy(xpath = "//*[contains(text(),'Codice hash')]")
    WebElement hashCodeTextField;

    @FindBy(xpath = "//label[contains(@id,'name-label')]/following-sibling::div/input")
    WebElement nomeAttoTextField;

    @FindBy(xpath = "//button[contains(text(),'Invia')]")
    WebElement inviaButton;

    public AllegatiPASection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadAllegatiPASection() {
        try{
            By allegatiTitleFieald = By.xpath("//h3[contains(text(),'Allegati')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(allegatiTitleFieald));
            logger.info("Allegati PA Section caricata");
        }catch (TimeoutException e){
            logger.error("Allegati PA Section non caricata con errore: "+e.getMessage());
            Assert.fail("Allegati PA Section non caricata con errore: "+e.getMessage());
        }
    }

    public void caricareNotificaPdfDalComputer(String pathNotificaFile) {
        if(!this.selezionaloDalTuoComputerInput.isDisplayed()){
            this.js().executeScript("arguments[0].scrollIntoView(true)", this.selezionaloDalTuoComputerInput);
            this.selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
        }else{
            this.selezionaloDalTuoComputerInput.sendKeys(pathNotificaFile);
        }
    }

    public boolean verificaCaricamentoNotificaPdf() {
        return this.hashCodeTextField.isDisplayed();
    }

    public void inserimentoNomeAllegato(String nomeAtto) {
        if (!this.nomeAttoTextField.isDisplayed()) {
            this.js().executeScript("arguments[0].scrollIntoView(true)", this.nomeAttoTextField);
        }
        this.nomeAttoTextField.click();
        this.nomeAttoTextField.sendKeys(nomeAtto);
    }

    public void selectInviaButton() {
        this.inviaButton.click();
    }
}
