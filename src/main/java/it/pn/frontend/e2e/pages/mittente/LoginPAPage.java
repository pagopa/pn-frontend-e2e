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


public class LoginPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("LoginPAPage");

    @FindBy(id = "username")
    WebElement userTextEdit;

    @FindBy(id = "password")
    WebElement pwdTextEdit;

    @FindBy(xpath = "//button[contains(text(),'Invia')]")
    WebElement inviaButton;

    public LoginPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadLoginPAPage(){
        try{
            By titlePage = By.cssSelector("h1.u-text-r-xl.u-margin-bottom-l");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            logger.info("Ta_QA: Login PA Page caricata");
        }catch (TimeoutException e){
            logger.error("TA_QA: il titolo della pagina Login PA non caricato con errore : "+e.getMessage());
            Assert.fail("TA_QA: il titolo della pagina Login PA non caricato con errore  : "+e.getMessage());
        }
    }

    public void inserisciUtenete(String user){
        logger.info("TA_QA: si inserisci il nome utente (user)");
        this.userTextEdit.sendKeys(user);
    }

    public void inserisciPassword(String pwd){
        logger.info("TA_QA: si inserisci la password dell'utente");
        this.pwdTextEdit.sendKeys(pwd);
    }

    public void selezionaInviaDati(){
        logger.info("TA_QA: si selezione il buttone invia");
        this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.inviaButton));
        this.inviaButton.click();
    }
}
