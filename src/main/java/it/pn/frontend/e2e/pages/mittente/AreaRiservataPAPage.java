package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AreaRiservataPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AreaRiservataPAPage.class);

    @FindBy(xpath = "//p[contains(@class,'MuiTypography-root MuiTypography-body1 ShowDots')]")
    private List<WebElement> infoLabel;

    @FindBy(id = "forward_prod-pn-dev")
    private WebElement piattaformaNotificaDevGestisciButton;

    @FindBy(id = "forward_prod-pn-test")
    private WebElement piattaformaNotificaTestGestisciButton;

    @FindBy(id = "forward_prod-pn")
    private WebElement piattaformaNotificaUatGestisciButton;

    public AreaRiservataPAPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadAreaRiservataPAPage() {
        try {
            By panoramicaLabel = By.cssSelector(".MuiTypography-root.MuiTypography-h4");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(panoramicaLabel));
            logger.info("Titolo Panoramica dell'area Riservata PA Page caricato");
        } catch (TimeoutException e) {
            Assertions.fail("Titolo 'Panoramica' dell'area Riservata PA Page non caricato con errore: " + e.getMessage());
        }
    }

    public boolean verificaCodiceFiscale(String codiceFiscale) {
        List<WebElement> infoLabel = getWebDriverWait(10)
                .withMessage("Gli elementi con i codici fiscali non sono visibili")
                .until(driver -> driver.findElements(By.xpath("//p[contains(@class,'MuiTypography-root MuiTypography-body1 ShowDots')]")));

        for (WebElement element : infoLabel) {
            String testo = element.getText().trim();
            logger.info("Info ente presente nella pagina Area Riservata: {}", testo);
            if (testo.equals(codiceFiscale)) {
                logger.info("Codice fiscale {} trovato correttamente", codiceFiscale);
                return true;
            }
        }

        logger.warn("Codice fiscale {} non trovato", codiceFiscale);
        return false;
    }

    public void selezionaPiattaformaNotificaDev() {
        try {
            WebElement button = getWebDriverWait(15)
                    .withMessage("Bottone piattaforma notifica dev non trovato")
                    .until(ExpectedConditions.elementToBeClickable(By.id("forward_prod-pn-dev")));

            try {
                button.click();
                logger.info("Cliccato su piattaforma notifica dev");

            } catch (ElementClickInterceptedException e) {
                logger.warn("Bottone intercettato, effettuo scroll e riprovo");
                js().executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
                button.click();
                logger.info("Cliccato su piattaforma notifica dev dopo scroll");
            }

        } catch (TimeoutException e) {
            Assertions.fail("Il bottone piattaforma notifica dev non è cliccabile: " + e.getMessage());
        }
    }

    public void selezionaPiattaformaNotificaTest() {
        try {
            WebElement button = getWebDriverWait(15)
                    .withMessage("Bottone piattaforma notifica test non trovato")
                    .until(ExpectedConditions.elementToBeClickable(By.id("forward_prod-pn-test")));

            try {
                button.click();
                logger.info("Cliccato su piattaforma notifica test");

            } catch (ElementClickInterceptedException e) {
                logger.warn("Bottone intercettato, effettuo scroll e riprovo");
                js().executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
                button.click();
                logger.info("Cliccato su piattaforma notifica test dopo scroll");
            }

        } catch (TimeoutException e) {
            Assertions.fail("Il bottone piattaforma notifica test non è cliccabile: " + e.getMessage());
        }
    }

    public void selezionaPiattaformaNotificaUat() {
        try {
            WebElement button = getWebDriverWait(15)
                    .withMessage("Bottone piattaforma notifica UAT non trovato")
                    .until(ExpectedConditions.elementToBeClickable(By.id("forward_prod-pn")));

            try {
                button.click();
                logger.info("Cliccato su piattaforma notifica UAT");

            } catch (ElementClickInterceptedException e) {
                logger.warn("Bottone UAT intercettato, effettuo scroll e riprovo");
                js().executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
                button.click();
                logger.info("Cliccato su piattaforma notifica UAT dopo scroll");
            }

        } catch (TimeoutException e) {
            Assertions.fail("Il bottone piattaforma notifica UAT non è cliccabile: " + e.getMessage());
        }
    }

}
