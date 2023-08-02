package it.pn.frontend.e2e.section.destinatario.personaGiuridica;

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

public class DelegatiImpresaSection extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DelegatiImpresaSection");

    @FindBy(xpath = "//button[@data-testid='addDeleghe']")
    WebElement addDelegheButton;

    public DelegatiImpresaSection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDelegatiImpresaPage() {
        try {
            By titlePageBy = By.xpath("//h6[contains(text(),'Delegati dall')]");
            By addDelegheButton = By.xpath("//button[@data-testid='addDeleghe']");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(addDelegheButton));

            logger.info("Delegati dall'impresa caricata correttamente");

        }catch (TimeoutException e){

            logger.error("Delegati dall'impresa non caricata correttamente con errore: "+e.getMessage());
            Assert.fail("Delegati dall'impresa non caricata correttamente con errore: "+e.getMessage());

        }
    }

    public void clickAggiungiDelegaButton() {

        this.addDelegheButton.click();

    }
}
