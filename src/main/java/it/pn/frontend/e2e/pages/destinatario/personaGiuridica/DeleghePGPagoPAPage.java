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

public class DeleghePGPagoPAPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePGPagoPAPage");

    @FindBy(xpath = "//button[@data-testid='tab1']")
    WebElement delegatiImpresaButton;

    public DeleghePGPagoPAPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDeleghePage() {
        try{
            By titlePage = By.id("title-of-page");
            By delegheCarico = By.xpath("//button[@data-testid='tab2']");
            By nDelghe = By.xpath("//button[@data-testid='rows-per-page']");

            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegheCarico));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nDelghe));

            this.logger.info("Deleghe page si visualizza correttamente");

        }catch (TimeoutException e){

            this.logger.error("Deleghe page non si visualizza correttamente con errore: "+e.getMessage());
            Assert.fail("Deleghe page non si visualizza correttamente con errore: "+e.getMessage());

        }
    }

    public void clickDelegatiImpresa() {

        this.delegatiImpresaButton.click();

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
