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

public class RicercaNotifichePGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("RicercaNotifichePGPage");
    public RicercaNotifichePGPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id ="filter-notifications-button")
    WebElement filtraButton;


    public void clickNotificheImpresa() {
        try {
            By notificheImpresaButton = By.xpath("//div[@data-testid=\"sideMenuItem-Notifiche dell'impresa\"]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificheImpresaButton));
            logger.info("Si clicca sulla voce notifiche dell'impresa");
        }catch (Exception e){
            logger.error("Non si clicca sulla voce notifiche dell'impresa con errore:"+e.getMessage());
            Assert.fail("Non si clicca sulla voce notifiche dell'impresa con errore:"+e.getMessage());
        }
    }

    public void cliccaNotificaRestituita() {
        try {
            By notificaBy = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-155o2nr')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(notificaBy));
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(notificaBy));
            this.js().executeScript("arguments[0].click()",this.element(notificaBy));
        } catch (TimeoutException e) {
            logger.error("Notifica non trovata con errore: " + e.getMessage());
            Assert.fail("Notifica non trovata con errore: " + e.getMessage());
        }
    }

    public void waitLoadDettaglioNotificaPGSection() {
        try {
           By pageTitleDettaglioNotifica = By.id("title-of-page");
           getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(pageTitleDettaglioNotifica));
           logger.info("La pagina dettaglio notifica si è caricata correttamente");
        }catch (TimeoutException e){
            logger.error("La pagina dettaglio notifica NON si è caricata correttamente con errore:"+e.getMessage());
            Assert.fail("La pagina dettaglio notifica NON si è caricata correttamente con errore:"+e.getMessage());
        }
    }

    public void clickFiltraButton() {
        this.filtraButton.click();
    }
}
