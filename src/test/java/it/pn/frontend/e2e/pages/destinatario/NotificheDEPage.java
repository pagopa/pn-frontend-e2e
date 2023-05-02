package it.pn.frontend.e2e.pages.destinatario;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificheDEPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("NotificheDEPage");

    @FindBy(id = "iunMatch")
    WebElement codiceIunTextField;
    public NotificheDEPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadNotificheDEPage() {
        try{
            By titleLabel = By.cssSelector("h4.MuiTypography-root.MuiTypography-h4");
            By tableNotifiche = By.cssSelector("table[aria-label='Tabella di item']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(tableNotifiche));
            logger.info("Notifiche DE Page non caricata");
        }catch (TimeoutException e){
            logger.error("Notifiche DE Page non caricata con errore : "+e.getMessage());
            Assert.fail("Notifiche DE Page non caricata con errore : "+e.getMessage());
        }
    }

    public boolean verificaPresenzaCodiceIumTextField() {
        try{
            return codiceIunTextField.isDisplayed();
        }catch (NoSuchElementException e){
            logger.error("text field codice ium non visualizzato");
            return false;
        }
    }


    public void waitESelectDelegheButton() {
        try{
            //By buttonDelegeBy = By.xpath("//div[contains(@data-testid,'sideMenuItem-Deleghe')]");
            By buttonDelegeBy = By.xpath("//div[contains(@class,'MuiButtonBase-root MuiListItemButton-root MuiListItemButton-gutters MuiListItemButton-root MuiListItemButton-gutters')][2]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(buttonDelegeBy));
            WebElement buttonDelegheWebElement = this.driver.findElement(buttonDelegeBy);
            buttonDelegheWebElement.click();
            logger.info("cliccato correttamente su delega button");
        }catch (TimeoutException e){
            logger.error("Delega button NON trovata con errore: "+e.getMessage());
            Assert.fail("Delega button NON trovata con errore: "+e.getMessage());
        }
    }
}
