package it.pn.frontend.e2e.pages.destinatario;

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

public class DeleghePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePage");

    @FindBy(xpath = "//button[contains(@data-testid,'add-delegation')]")
    WebElement addDelegaButton;
    public DeleghePage(WebDriver driver) {
        super(driver);
    }

    public void waitDeleghePage() {
        try {
            By deleghePageTitle = By.xpath("//h4[contains(@id,'title-of-page')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(deleghePageTitle));
            this.logger.info("Deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Deleghe Page non caricata con errore : " + e.getMessage());
            Assert.fail("Deleghe Page non caricata con errore : " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton()  {this.addDelegaButton.click();}


    public void waitNuovaDelegaSection() {
        try {
            By letuedeleghePageTitle = By.xpath("//h3[@id ='title-of-page']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(letuedeleghePageTitle));
            this.logger.info("Le tue deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Le tue deleghe page non caricata con errore :" + e.getMessage());
            Assert.fail(("Le tue deleghe page non caricata con errore :" + e.getMessage()));
        }
    }

    public void controlloCreazioneDelega() {
        try{
            By delegaCreata = By.xpath("//span[contains(text(),'In attesa di conferma')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegaCreata));
            this.logger.info("Si visualizza la delega creata");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza la delega creata");
            Assert.fail("Non si visualizza la delega creata");
        }
    }
}
