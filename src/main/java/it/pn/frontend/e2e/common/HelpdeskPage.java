package it.pn.frontend.e2e.common;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class HelpdeskPage extends BasePage {

    @FindBy(id = "buttonLogin")
    WebElement loginButton;

    @FindBy(id = "Email")
    WebElement emailInput;

    @FindBy(id = "Password")
    WebElement passwordInput;

    @FindBy(xpath = "//button[@aria-label='more']")
    WebElement buttonMenu;

    @FindBy(id = "logout")
    WebElement buttonLogout;

    @FindBy(id = "Numero Ticket")
    WebElement numeroTicketInput;

    @FindBy(id = "Codice Fiscale")
    WebElement codiceFiscaleInput;

    @FindBy(id = "ricerca")
    WebElement buttonRicerca;

    @FindBy(xpath = "//p[contains(text(),'Codice Univoco')]")
    WebElement Uid;

    @FindBy(xpath = "//p[contains(text(),'Codice Fiscale')]")
    WebElement CfPersonaFisica;


    @FindBy(id = "Tipo Estrazione")
    WebElement selectTypeOfEstrazioneDati;

    @FindBy(id = "Codice Univoco (uid)")
    WebElement inputUid;


    private final Logger logger = LoggerFactory.getLogger("Helpdesk Page");

    private String codiceFiscale;
    private String codiceIdentificativoPF;

    public HelpdeskPage(WebDriver driver) {
        super(driver);
    }


    public void changePage(String HelpdeskURL) {
        this.driver.get(HelpdeskURL);
    }

    public void checkForm() {
        try {
            logger.info("Check form in corso");
            this.getWebDriverWait(40).withMessage("email non presente").until(ExpectedConditions.visibilityOf(this.emailInput));
            this.getWebDriverWait(40).withMessage("password non presente").until(ExpectedConditions.visibilityOf(this.passwordInput));
            this.getWebDriverWait(40).withMessage("submit non presente").until(ExpectedConditions.visibilityOf(this.loginButton));
        } catch (TimeoutException e) {
            logger.error("Form non presente errore: " + e.getMessage());
            Assert.fail("Form non presente errore: " + e.getMessage());
        }
    }

    public void checkHome() {
        try {
            By titlePage = By.id("cardTitle-Monitoraggio Piattaforma Notifiche");
            this.getWebDriverWait(40).withMessage("home non presente").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("pagina home carica");
        } catch (TimeoutException e) {
            logger.error("errore caricamento home helpdesk: " + e.getMessage());
            Assert.fail("errore caricamento home helpdesk: " + e.getMessage());
        }
    }

    public void checkMonitoraggio() {
        try {
            TimeUnit.SECONDS.sleep(5);
            this.getWebDriverWait(30).withMessage("elenco monitoraggio non trovato").until(ExpectedConditions.visibilityOf(this.buttonMenu));
            logger.info("pagina monitoraggio caricata");
        } catch (TimeoutException | InterruptedException e) {
            logger.error("errore caricamento home monitoraggio: " + e.getMessage());
            Assert.fail("errore caricamento home monitoraggio: " + e.getMessage());
        }
    }

    public void insertUsername(String user) {
        logger.info("inserisco email");
        this.emailInput.sendKeys(user);
    }


    public void insertPassword(String pwd) {
        logger.info("inserisco password");
        this.passwordInput.sendKeys(pwd);
    }


    public void clickInviaButton() {
        logger.info("clicco il bottone login");
        this.loginButton.click();
    }

    public void clickMonitoraggio() {
        try {
            By monitoraggioButton = By.id("cardTitle-Monitoraggio Piattaforma Notifiche");
            logger.info("clicco sulla card monitoraggio piattaforma notifiche");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(monitoraggioButton));
            this.elements(monitoraggioButton).get(0).click();
        } catch (TimeoutException e) {
            logger.error("Card monitoraggio non cliccabile: " + e.getMessage());
            Assert.fail("Card monitoraggio non cliccabile: " + e.getMessage());
        }
    }

    public void handleDisservizio(String type) {
        logger.info("clicco sul menu della tabella");
        this.js().executeScript("arguments[0].click()", buttonMenu);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            logger.info("Click su button 'Inserisci X'".replace("X", type));
            By buttonPreCreateDisservizio = By.id("X-insert".replace("X", type));
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(buttonPreCreateDisservizio));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.error("pausa con errore: " + e.getMessage());
                throw new RuntimeException(e);
            }
            this.elements(buttonPreCreateDisservizio).get(0).click();
        } catch (TimeoutException e) {
            logger.error(" button 'Inserisci X' non trovato: ".replace("X", type) + e.getMessage());
            Assert.fail("button 'Inserisci X' non trovato: ".replace("X", type) + e.getMessage());
        }
        try {
            logger.info("Click su button 'Inserisci'");
            By buttonCreateDisservizio = By.id("buttonInserisciDisservizio");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(buttonCreateDisservizio));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.error("pausa con errore: " + e.getMessage());
                throw new RuntimeException(e);
            }
            this.element(buttonCreateDisservizio).click();
        } catch (TimeoutException e) {
            logger.error("button 'Inserisci' non trovato: " + e.getMessage());
            Assert.fail("button 'Inserisci' non trovato: " + e.getMessage());
        }

    }

    public boolean checkIsCreatedDisservizio() {
        try {
            WebElement dateDisservizio = this.elements(By.xpath("//div[@data-field='data']")).get(1);
            this.getWebDriverWait(10).until(ExpectedConditions.visibilityOf(dateDisservizio));
            if (dateDisservizio.getText() != null && !dateDisservizio.getText().isEmpty()) {
                logger.info("disservizio creato correttamente");
                return true;
            }
            return false;
        } catch (TimeoutException e) {
            logger.error("disservizio non creato: " + e.getMessage());
            Assert.fail("disservizio non creato: " + e.getMessage());
            return false;
        }
    }

    public void clickSezioneRicerca() {
        try {
            By ricercaButton = By.id("cardTitle-Ricerca ed estrazione dati");
            logger.info("clicco sulla card ricerca ed estrazione dati");
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(ricercaButton));
            this.elements(ricercaButton).get(0).click();
        } catch (TimeoutException e) {
            logger.error("Card ricerca non cliccabile: " + e.getMessage());
            Assert.fail("Card ricerca non cliccabile: " + e.getMessage());
        }
    }

    public void checkRicercaPage() {
        logger.info("check pagina ricerca ed estrazione dati");
        try {
            By buttonResetFiltri = By.id("resetFilter");
            this.getWebDriverWait(30).withMessage("Tipo estrazione non trovato").until(ExpectedConditions.visibilityOf(selectTypeOfEstrazioneDati));
            this.getWebDriverWait(30).withMessage("numero ticket input non trovato").until(ExpectedConditions.visibilityOf(numeroTicketInput));
            this.getWebDriverWait(30).withMessage("codice fiscale input non trovato").until(ExpectedConditions.visibilityOf(codiceFiscaleInput));
            this.getWebDriverWait(30).withMessage("button ricerca non trovato").until(ExpectedConditions.visibilityOf(buttonRicerca));
            this.getWebDriverWait(30).withMessage("button reset filtri non trovato").until(ExpectedConditions.visibilityOfElementLocated(buttonResetFiltri));
        } catch (TimeoutException e) {
            logger.error("home ricerca non caricata correttamente: " + e.getMessage());
            Assert.fail("home ricerca non caricata correttamente: " + e.getMessage());
        }
    }

    public void logout() {
        try {
            logger.info("controllo esistenza bottone logout");
            this.getWebDriverWait(30).withMessage("bottone logout non trovato").until(ExpectedConditions.visibilityOf(buttonLogout));
            logger.info("click sul bottone logout");
            buttonLogout.click();
            logger.info("apertura dialog di conferma logout");
            By buttonConfermaLogout = By.xpath("//button[contains(text(),'Esci')]");
            logger.info("controllo esistenza pulsante conferma logout");
            this.getWebDriverWait(30).withMessage("bottone conferma logout non trovato").until(ExpectedConditions.visibilityOfElementLocated(buttonConfermaLogout));
            logger.info("click conferma logout");
            this.elements(buttonConfermaLogout).get(0).click();
        } catch (TimeoutException e) {
            logger.error("logout non riuscito correttamente: " + e.getMessage());
            Assert.fail("logout non riuscito correttamente: " + e.getMessage());
        }
    }

    public void insertCfAndRicercaOnPage(String codiceFiscale) {
        logger.info("inserisco numero ticket");
        numeroTicketInput.sendKeys("testTAFE01");
        logger.info("inserisco codice fiscale");
        setCodiceFiscale(codiceFiscale);
        codiceFiscaleInput.sendKeys(codiceFiscale);
        logger.info("clicco sul bottone di ricerca");
        try {
            this.getWebDriverWait(30).withMessage("bottone per la ricerca non trovato").until(ExpectedConditions.elementToBeClickable(buttonRicerca));
            buttonRicerca.click();
        } catch (TimeoutException e) {
            logger.error("bottone non cliccabile:" + e.getMessage());
            Assert.fail("bottone non cliccabile:" + e.getMessage());

        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void checkUid() {
        try {
            logger.info("controllo esistenza codice univoco");
            this.getWebDriverWait(30).withMessage("Codice univoco non trovato").until(ExpectedConditions.visibilityOf(Uid));
            setCodiceIdentificativoPF(Uid.getText().replace("Codice Univoco: ", ""));
        } catch (TimeoutException e) {
            logger.error("codice univoco non trovato: " + e.getMessage());
            Assert.fail("codice univoco non trovato: " + e.getMessage());
        }
    }

    public void setCodiceIdentificativoPF(String codiceIdentificativoPF) {
        this.codiceIdentificativoPF = codiceIdentificativoPF;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void changeOption() {
        logger.info("click su tipo estrazione dati");
        By inputTipoEstrazione = By.xpath("//div[@data-testid='select-Tipo Estrazione']");
        this.element(inputTipoEstrazione).click();
        try {
            logger.info("selezione ottieni codice fiscale");
            By selectTypeOfOttieniCF = By.xpath("//li[contains(text(),'Ottieni CF')]");
            logger.info("controllo esistenza selezione");
            this.getWebDriverWait(30).withMessage("opzione ottieni cf non trovata").until(ExpectedConditions.visibilityOfElementLocated(selectTypeOfOttieniCF));
            this.element(selectTypeOfOttieniCF).click();
            By checkUidIsDisplayed = By.id("Codice Univoco (uid)");
            this.getWebDriverWait(30).withMessage("input uid non trovato").until(ExpectedConditions.visibilityOfElementLocated(checkUidIsDisplayed));
        } catch (TimeoutException e) {
            logger.error("opzione ottieni cf non trovata: " + e.getMessage());
            Assert.fail("opzione ottieni cf non trovata: " + e.getMessage());
        }
    }


    public void checkCodiceFiscale() {
        inputUid.sendKeys(this.codiceIdentificativoPF);
        buttonRicerca.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }
        String cfResult = CfPersonaFisica.getText().replace("Codice Fiscale: ", "");
        if (cfResult.equals(this.codiceFiscale)) {
            logger.info("codice fiscale corrispondente");
        } else {
            logger.error("codice fiscale diverso");
            Assert.fail("dopo ricerca di corrispondenza il codice fiscale risulta differente");
        }
    }
}
