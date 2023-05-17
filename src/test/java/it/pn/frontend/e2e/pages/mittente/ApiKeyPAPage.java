package it.pn.frontend.e2e.pages.mittente;

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

public class ApiKeyPAPage  extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("ApiKeyPAPage");

    @FindBy(xpath = "//button[contains(@data-testid,'generateApiKey')]")
    WebElement generateApiKeyButton;

    @FindBy(xpath = "//input[contains(@id,'name')]")
    WebElement apiKeyNameInput;

    @FindBy(xpath = "//button[contains(text(),'Continua')]")
    WebElement apiContinuaButton;

    @FindBy(xpath = "//button[contains(text(),'Torna a API Key')]")
    WebElement tornaApiButton;

    public ApiKeyPAPage(WebDriver driver) {
        super(driver);
    }

    public void clickSulBottoneGeneraApiKey() {
        this.generateApiKeyButton.click();
    }

    public void inserireUnNomePerApiKey(String nomeApiKey) {
        this.apiKeyNameInput.sendKeys(nomeApiKey);
    }

    public void clickSulBottoneContinua() {
        this.apiContinuaButton.click();
    }

    public void siVisualizzaCorrettamenteConfermaPage() {
        try{
            By confirmationApiPageTitle = By.xpath("//h4[contains(text(),'API Key generata con successo!')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(confirmationApiPageTitle));
            logger.info("Api Key ConfirmationPage caricata");
        }catch (TimeoutException e){
            logger.error("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
            Assert.fail("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
        }
    }

    public void clickSulBottoneTornaApiKey() {
        this.tornaApiButton.click();
    }

    public void siVisualizzaNuovaApiAttiva(String nomeApiKey) {
        try{
            By statoAttivoField = By.xpath("//span[contains(text(),'Attiva')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivoField));
            By apiNameAttivoField = By.xpath("//p[contains(text(),'"+nomeApiKey+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(apiNameAttivoField));
            logger.info("Api Key ConfirmationPage caricata");
        }catch (TimeoutException e){
            logger.error("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
            Assert.fail("Api Key ConfirmationPage NON caricata con errore : "+e.getMessage());
        }
    }
}
