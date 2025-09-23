package it.pn.frontend.e2e.pages.mittente;


import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AcccediAreaRiservataPAPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(AcccediAreaRiservataPAPage.class);
    @FindBy(id = "spidButton")
    WebElement spidButton;

    @FindBy(id = "forward_prod-pn-test")
    WebElement testButton;

    private WebTool webTool;

    public AcccediAreaRiservataPAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadLoginPageMittente() {
        getWebDriverWait(45)
                .withMessage("Titolo 'Accedi Area Riservata' non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[@class='MuiTypography-root MuiTypography-h3 css-ktw4ma']")));

        logger.info("Accedi Area Riservata Page caricata");
    }

    public void selezionareSpidButton() {
        logger.info("Selezione del bottone SPID");

        spidButton = getWebDriverWait(60)
                .withMessage("Bottone SPID non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("spidButton")));

        spidButton.click();
        logger.info("Click sul bottone SPID effettuato");
    }

    public void clickScegliAmbienteSendBottoneMittente(String idButton) {
        WebElement forwardButton = getWebDriverWait(20)
                .withMessage("Il bottone con id '" + idButton + "' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id(idButton)));

        forwardButton.click();
    }

    public void bottoneConImgPagoPA() {

        WebElement bottoneConImgPagoPA = getWebDriverWait(10)
                .withMessage("Impossibile cliccare su Immagine PAGOPA")
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='idp-button-https://validator.dev.oneid.pagopa.it/demo']")
                ));
        bottoneConImgPagoPA.click();
    }
}
