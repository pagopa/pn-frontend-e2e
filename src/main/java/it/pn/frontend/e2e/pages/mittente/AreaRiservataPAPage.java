package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/*
*Iniezione Spring: La classe è ora annotata con @Component per permettere a Spring Boot di gestirla come un bean, e il costruttore AreaRiservataPAPage riceve WebDriver tramite iniezione automatica.
Gestione WebElement: Gli elementi WebElement definiti con @FindBy vengono iniettati automaticamente grazie a Spring e Selenium, senza ulteriori istanze di driver nella classe.
* */

public class AreaRiservataPAPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("AreaRiservataPAPage");




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
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(panoramicaLabel));
            logger.info("Titolo Panoramica dell'area Riservata PA Page caricato");
        } catch (TimeoutException e) {
            logger.error("Titolo 'Panoramica' dell'area Riservata PA Page non caricato con errore: " + e.getMessage());
            Assertions.fail("Titolo 'Panoramica' dell'area Riservata PA Page non caricato con errore: " + e.getMessage());
        }
    }

    public boolean verificaCodiceFiscale(String codiceFiscale) {
        boolean codiceFiscaleFound = false;
        for (WebElement element : infoLabel) {
            logger.info("Info ente presente nella pagina Area Riservata: " + element.getText());
            if (element.getText().equals(codiceFiscale)) {
                codiceFiscaleFound = true;
                break;
            }
        }
        return codiceFiscaleFound;
    }

    public void selezionaPiattaformaNotificaDev() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(piattaformaNotificaDevGestisciButton));
            if (piattaformaNotificaDevGestisciButton.isDisplayed()) {
                piattaformaNotificaDevGestisciButton.click();
            } else {
                js().executeScript("arguments[0].scrollIntoView(true);", piattaformaNotificaDevGestisciButton);
                piattaformaNotificaDevGestisciButton.click();
            }
        } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException e) {
            logger.error("Il bottone piattaforma notifica dev non è presente o non è cliccabile: " + e.getMessage());
            Assertions.fail("Il bottone piattaforma notifica dev non è presente o non è cliccabile: " + e.getMessage());
        }
    }

    public void selezionaPiattaformaNotificaTest() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(piattaformaNotificaTestGestisciButton));
            if (piattaformaNotificaTestGestisciButton.isDisplayed()) {
                piattaformaNotificaTestGestisciButton.click();
            } else {
                js().executeScript("arguments[0].scrollIntoView(true);", piattaformaNotificaTestGestisciButton);
                piattaformaNotificaTestGestisciButton.click();
            }
        } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException e) {
            logger.error("Il bottone piattaforma notifica test non è presente o non è cliccabile: " + e.getMessage());
            Assertions.fail("Il bottone piattaforma notifica test non è presente o non è cliccabile: " + e.getMessage());
        }
    }

    public void selezionaPiattaformaNotificaUat() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(piattaformaNotificaUatGestisciButton));
            if (piattaformaNotificaUatGestisciButton.isDisplayed()) {
                piattaformaNotificaUatGestisciButton.click();
            } else {
                js().executeScript("arguments[0].scrollIntoView(true);", piattaformaNotificaUatGestisciButton);
                piattaformaNotificaUatGestisciButton.click();
            }
        } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException e) {
            logger.error("Il bottone piattaforma notifica uat non è presente o non è cliccabile: " + e.getMessage());
            Assertions.fail("Il bottone piattaforma notifica uat non è presente o non è cliccabile: " + e.getMessage());
        }
    }

}
