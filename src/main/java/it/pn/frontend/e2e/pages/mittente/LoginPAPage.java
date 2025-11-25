package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPAPage.class);

    @FindBy(id = "username")
    WebElement userTextEdit;

    @FindBy(id = "password")
    WebElement pwdTextEdit;

    @FindBy(xpath = "//button[contains(text(),'Invia')]")
    WebElement inviaButton;

    public LoginPAPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadLoginPAPage() {
        getWebDriverWait(30)
                .withMessage("Il titolo della pagina Login PA non è visibile dopo 30 secondi")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1.u-text-r-xl.u-margin-bottom-l")));
    }

    public void inserisciUtenete(String user) {
        logger.info("Si inserisce il nome utente: {}", user);
        WebElement userTextEdit = getWebDriverWait(40)
                .withMessage("Il campo username non è visibile dopo 40 secondi")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        userTextEdit.clear();
        userTextEdit.sendKeys(user);

    }

    public void inserisciPassword(String pwd) {
        logger.info("Si inserisce la password dell'utente");
        WebElement pwdTextEdit = getWebDriverWait(40)
                .withMessage("Il campo password non è visibile dopo 40 secondi")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        pwdTextEdit.clear();
        pwdTextEdit.sendKeys(pwd);
        logger.info("Password inserita correttamente");

    }

    public void bottoneAccedi() {
        WebElement accediButton = getWebDriverWait(10)
                .withMessage("Il bottone 'Accedi' non è cliccabile entro il timeout")
                .until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Accedi']]")
        ));
        accediButton.click();

    }

    public void bottoneDoIlConsenso() {
        WebElement consensoButton =
                getWebDriverWait(10)
                .withMessage("Il bottone 'Do il consenso' non è cliccabile entro il timeout")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[.//span[text()='Do il consenso']]")
                ));

        consensoButton.click();
    }

    public void selezionaInviaDati() {
        logger.info("Si seleziona il bottone Invia");
        WebElement inviaButton = getWebDriverWait(30)
                .withMessage("Il bottone Invia non è cliccabile dopo 30 secondi")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Invia')]")));
        inviaButton.click();
        logger.info("Bottone Invia cliccato con successo");

    }

    public void entraConSpid() {
        WebElement bottoneSpid = getWebDriverWait(10)
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.italia-it-button.button-spid")
                ));
        bottoneSpid.click();
    }

}
