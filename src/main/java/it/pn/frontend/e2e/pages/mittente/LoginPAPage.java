package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
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
        this.driver = driver;
    }

    public void waitLoadLoginPAPage(){
        try{
            WebElement titlePage = driver.findElement(By.cssSelector("h1.u-text-r-xl.u-margin-bottom-l"));
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(titlePage));
            logger.info("Login PA Page caricata");
        }catch (TimeoutException e){
            logger.error("Il titolo della pagina Login PA non caricato con errore : "+e.getMessage());
            Assertions.fail("Il titolo della pagina Login PA non caricato con errore  : "+e.getMessage());
        }
    }

    public void inserisciUtenete(String user){
        logger.info("Si inserisci il nome utente");
        userTextEdit = driver.findElement(By.id("username"));
        getWebDriverWait(30).withMessage("Il campo user non è presente").until(ExpectedConditions.visibilityOf(userTextEdit));
        userTextEdit.sendKeys(user);
    }

    public void inserisciPassword(String pwd){
        logger.info("Si inserisce la password dell'utente");
        pwdTextEdit = driver.findElement(By.id("password"));
        getWebDriverWait(30).withMessage("Il campo password non è presente").until(ExpectedConditions.visibilityOf(this.pwdTextEdit));
        pwdTextEdit.sendKeys(pwd);
    }

    public void selezionaInviaDati(){
        logger.info("Si selezione il bottone invia");
        try {
            inviaButton = driver.findElement(By.xpath("//button[contains(text(),'Invia')]"));
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(inviaButton));
            inviaButton.click();
        }catch (TimeoutException e){
        logger.error("Il bottone invia nella pagina Login PA non è stato cliccato con errore : "+e.getMessage());
            Assertions.fail("Il bottone invia nella pagina Login PA non è stato cliccato con errore : "+e.getMessage());
        }
    }
}
