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

    private Logger logger = LoggerFactory.getLogger("DeleghePage");

    @FindBy(xpath = "//button[contains(text(),'Aggiungi una delega')]")
    WebElement addDelegaButton;
    public DeleghePage(WebDriver driver) {
        super(driver);
    }

    public void waitDeleghePage() {
        try {
            By deleghePageTitle = By.xpath("//h4[contains(text(),'Deleghe')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(deleghePageTitle));
            this.logger.info("Deleghe page caricata");
        } catch (TimeoutException e) {
            logger.error("Deleghe Page non caricata con errore : " + e.getMessage());
            Assert.fail("Deleghe Page non caricata con errore : " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton()  {this.addDelegaButton.click();}


}
