package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

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

public class SelezionaImpresaPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("SelezionaImpresaPage");

    @FindBy(xpath = "//button[contains(text(),'Accedi')]")
    WebElement accediButton;

    public SelezionaImpresaPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadSelezionaImpresaPage() {
        try{
            By titlePageBy = By.xpath("//h3[contains(text(),'Seleziona la tua impresa')]");
            By accediButtonBy = By.xpath("//button[contains(text(),'Accedi')]");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(accediButtonBy));

            logger.info("SelezionaImpresaPage caricata correttamente");

        }catch (TimeoutException e){
            logger.error("SelezionaImpresaPage non caricata correttamente con errore: "+e.getMessage());
            Assert.fail("SelezionaImpresaPage non caricata correttamente con errore: "+e.getMessage());
        }
    }

    public void clickAccediButton() {
        this.accediButton.click();
    }
}
