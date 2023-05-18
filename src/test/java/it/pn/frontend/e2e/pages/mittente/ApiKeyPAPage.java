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

import java.util.List;

public class ApiKeyPAPage  extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("ApiKeyPAPage");

    public ApiKeyPAPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@data-testid,'generateApiKey')]")
    WebElement generateApiKeyButton;

    @FindBy(xpath = "//input[contains(@id,'name')]")
    WebElement apiKeyNameInput;

    @FindBy(xpath = "//button[contains(text(),'Continua')]")
    WebElement apiContinuaButton;

    @FindBy(xpath = "//button[contains(text(),'Torna a API Key')]")
    WebElement tornaApiButton;

    @FindBy(id = "groups")
    WebElement apiGruppoButton;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonBlock')]")
    WebElement blockButton;

    @FindBy(xpath = "//button[contains(@data-testid,'action-modal-button')]")
    WebElement blockButtonPopUp;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonEnable')]")
    WebElement attivaButtonNelMenu;

    @FindBy(xpath = "//button[@data-testid='action-modal-button']")
    WebElement confermaButtonNelPopUp;

    @FindBy(xpath = "//li[contains(@data-testid,'buttonRotate')]")
    WebElement ruotaButtonNelMenu;

    @FindBy(xpath = "//button[@data-testid='close-modal-button']")
    WebElement annullaButtonNelPopUp;

    public void inserireUnNomePerApiKey(String nomeApiKey) {
        this.apiKeyNameInput.sendKeys(nomeApiKey);
    }

    public void clickSulBottoneContinua() {
        this.apiContinuaButton.click();
    }

    public void clickSulBottoneGeneraApiKey() {
        this.generateApiKeyButton.click();
    }

    public void siVisualizzaCorrettamenteConfermaPage() {
        try {
            By confirmationApiPageTitle = By.xpath("//h4[contains(text(),'API Key generata con successo!')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(confirmationApiPageTitle));
            logger.info("Api Key ConfirmationPage caricata");
        } catch (TimeoutException e) {
            logger.error("Api Key ConfirmationPage NON caricata con errore : " + e.getMessage());
            Assert.fail("Api Key ConfirmationPage NON caricata con errore : " + e.getMessage());
        }
    }

    public void clickSulBottoneTornaApiKey() {
        this.tornaApiButton.click();
    }

    public void siVisualizzaNuovaApiAttiva(String nomeApiKey) {
        try {
            By statoAttivoField = By.xpath("//span[contains(text(),'Attiva')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoAttivoField));
            By apiNameAttivoField = By.xpath("//p[contains(text(),'" + nomeApiKey + "')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(apiNameAttivoField));
            logger.info("Api Key ConfirmationPage caricata");
        } catch (TimeoutException e) {
            logger.error("Api Key ConfirmationPage NON caricata con errore : " + e.getMessage());
            Assert.fail("Api Key ConfirmationPage NON caricata con errore : " + e.getMessage());
        }

    }

    public void waitLoadGeneraApiKey() {
        try {
            By generaApiPageTitle = By.xpath("//h4[contains(text(),'Genera una API Key')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(generaApiPageTitle));
            logger.info("Api Key caricata");
        } catch (TimeoutException e) {
            logger.error("Genera Api Key NON caricata con errore : " + e.getMessage());
            Assert.fail("Genera Api KeyNON caricata con errore : " + e.getMessage());
        }
    }

    public void inserireGruppoApi(String gruppo) {
        this.apiGruppoButton.click();
        try {
            By zeroGroupby = By.xpath("//input[contains(text(),'" + gruppo + "')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(zeroGroupby));
            logger.info("Gruppo inserito correttamente");
            element(zeroGroupby).click();
        } catch (TimeoutException e) {
            logger.error("Gruppo non inserito correttamente con errore " + e.getMessage());
            Assert.fail("Gruppo non inserito correttamente con errore " + e.getMessage());
        }
    }

    public void clickMenuButton() {

        By attivaButtonBy = By.xpath("//div[@role='button' and @data-testid='statusChip-Attiva']");
        List<WebElement> attivaButtonList = this.elements(attivaButtonBy);

        if (attivaButtonList.size() == 0) {
            logger.error("nessun attiva button");
            Assert.fail("nessun attiva button");
        }

        By menuAttivaButtonBy = By.xpath("//td[div/div/div/div[@role='button' and @data-testid='statusChip-Attiva']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']");
        List<WebElement> menuAttivaButton = this.elements(menuAttivaButtonBy);

        if (menuAttivaButton.size() == 0) {
            logger.error("nessun menu attiva button");
            Assert.fail("nessun menu attiva button");
        }

        menuAttivaButton.get(0).click();

    }

    public void clickSuBlocca() {
        this.blockButton.click();
    }

    public void siVisualizzaPopUp() {
        try {
            By popupTitleBy = By.xpath("//h5[contains(text(),'Blocca API Key')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popupTitleBy));
            logger.info("Il popup si visualizza correttamente");
        } catch (TimeoutException e) {
            logger.error("Il popup NON si visualizza correttamente con errore:" + e.getMessage());
            Assert.fail("Il popup NON si visualizza correttamente con errore:" + e.getMessage());
        }
    }

    public void clickBloccaPU() {
        this.blockButtonPopUp.click();
    }

    public void notificaSelezionataStatoBloccata() {
        try {
            By statoNotificaBloccata = By.xpath("//span[contains(text(),'Bloccata')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBloccata));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            logger.error("La notifica è in stato bloccata con errore:" + e.getMessage());
            Assert.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickMenuApiBloccata() {
        By bloccataButtonBy = By.xpath("//span[contains(text(),'Bloccata')]");
        List<WebElement> attivaButtonList = this.elements(bloccataButtonBy);

        if (attivaButtonList.size() == 0) {
            logger.error("nessun bloccata button");
            Assert.fail("nessun bloccata button");
        }

        By menuAttivaButtonBy = By.xpath("//td[div/div/div/div[@role='button' and @data-testid='statusChip-Bloccata']]/following-sibling::td//button[@type='button' and @data-testid='contextMenuButton' and @aria-label='Opzioni su API Key']");
        List<WebElement> menuAttivaButton = this.elements(menuAttivaButtonBy);

        if (menuAttivaButton.size() == 0) {
            logger.error("nessun menu bloccata button");
            Assert.fail("nessun menu bloccata button");
        }

        menuAttivaButton.get(0).click();

    }

    public void clickAttivaSulMenu() {
        this.attivaButtonNelMenu.click();
    }

    public void siVisualizzaPoPUpAttiva() {
        try {
            By popUpAttiva = By.xpath("//h5[contains(text(),'Attiva API Key')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpAttiva));
            logger.info("Si visualizza correttamente il popup attiva");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup attiva con errore:" + e.getMessage());
            Assert.fail("NON si visualizza correttamente il popup attiva con errore:" + e.getMessage());
        }
    }

    public void clickSuConfermaNelPopUp() {
        this.confermaButtonNelPopUp.click();
    }

    public void siVisualizzaNotificaSelezionataBloccata() {
        try {
            By statoNotificaBloccata = By.xpath("//span[contains(text(),'Attiva')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(statoNotificaBloccata));
            logger.info("La notifica è in stato bloccata");
        } catch (TimeoutException e) {
            logger.error("La notifica è in stato bloccata con errore:" + e.getMessage());
            Assert.fail("La notifica è in stato bloccata con errore:" + e.getMessage());
        }
    }

    public void clickRuotaSulMenu() {this.ruotaButtonNelMenu.click();}

    public void siVisualizzaPoPUpRuota() {
        try {
            By popUpRuota = By.xpath("//h5[contains(text(),'Ruota API Key')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpRuota));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
            Assert.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public void siVisualizzaNotificaSelezionataRuotata() {
        try {
            By popUpRuotataBy = By.xpath("//span[contains(text(),'Ruotata')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(popUpRuotataBy));
            logger.info("Si visualizza correttamente il popup ruota");
        } catch (TimeoutException e) {
            logger.error("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
            Assert.fail("NON si visualizza correttamente il popup ruota con errore:" + e.getMessage());
        }
    }

    public void clickSuAnnulla() {this.annullaButtonNelPopUp.click();}
}
