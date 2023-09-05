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

public class NotificheDestinatarioPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger("NotificheDestinatarioPage");

    @FindBy(id = "iunMatch")
    WebElement codiceIunTextField;

    public NotificheDestinatarioPage(WebDriver driver) {
        super(driver);
    }

    public void inserisciCodiceIUN(String codiceIUN) {
        this.codiceIunTextField.sendKeys(codiceIUN);
    }
    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        try {
            By codiceIUNBy = By.xpath("//button[contains(text(),'"+codiceIUNInserito+"')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(codiceIUNBy));
            return true;
        }catch (TimeoutException e){
            logger.error("Il codice IUN"+codiceIUNInserito+" non è stato trovato con errore:"+e.getMessage());
            Assert.fail("Il codice IUN"+codiceIUNInserito+" non è stato trovato con errore:"+e.getMessage());
        }
        return false;
    }

}
