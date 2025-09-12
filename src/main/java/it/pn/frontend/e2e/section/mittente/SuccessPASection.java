package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SuccessPASection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(SuccessPASection.class);

    @FindBy(id = "go-to-notifications")
    WebElement successButton;

    @FindBy(id = "title-sync-feedback")
    WebElement successCheckBy;

    private WebTool webTool;

    public SuccessPASection(WebDriver driver) {

        this.driver = driver;
        webTool = new WebTool(driver);
    }


    public void waitLoadSuccessPASection() {
        try {
            getWebDriverWait(60)
                    .withMessage("Non è presente il testo 'La notifica è stata creata con successo'")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("title-sync-feedback")));

            logger.info("TA_QA: La notifica è stata creata con successo, PA section caricata correttamente");

        } catch (TimeoutException e) {
            Assertions.fail("TA_QA: Il titolo della Success PA section non è stato caricato. Errore: " + e.getMessage());
        }
    }

    //    public void vaiAlleNotifiche() {
//        logger.info("click pulsante vai alle notifiche");
//        logger.info("Tentativo di cliccare il pulsante 'Vai alle notifiche'...");
//
//        getWebDriverWait(80)
//                .withMessage("Il pulsante con id 'go-to-notifications' non è presente nel DOM.")
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("go-to-notifications")));
//
//        WebElement vaiAlleNotifiche = driver.findElement(By.id("go-to-notifications"));
//
//        try {
//            getWebDriverWait(10).until(ExpectedConditions.invisibilityOfElementLocated(
//                    By.cssSelector(".MuiBackdrop-root, .spinner, .overlay-loader"))); // personalizza se usi altri overlay
//        } catch (TimeoutException ignored) {
//            logger.warn("Overlay ancora presente, si tenta comunque il click.");
//        }
//
//        js().executeScript("arguments[0].scrollIntoView({block: 'center'});", vaiAlleNotifiche);
//        webTool.waitTime(1); // Attesa per stabilizzare
//
//        getWebDriverWait(10)
//                .withMessage("Il pulsante 'Vai alle notifiche' non è cliccabile.")
//                .until(ExpectedConditions.elementToBeClickable(vaiAlleNotifiche));
//
//        try {
//            vaiAlleNotifiche.click();
//            logger.info("Clic sul pulsante 'Vai alle notifiche' eseguito con successo.");
//        } catch (Exception e) {
//            logger.warn("Click normale fallito. Si tenta il click via JavaScript.");
//            js().executeScript("arguments[0].click();", vaiAlleNotifiche);
//            logger.info("Click via JavaScript eseguito correttamente.");
//        }
//    }
    public void vaiAlleNotifiche() {
        logger.info("click pulsante vai alle notifiche");
        logger.info("Tentativo di cliccare il pulsante 'Vai alle notifiche'...");

        By vaiAlleNotificheLocator = By.id("go-to-notifications");

        WebElement vaiAlleNotifiche = getWebDriverWait(80)
                .withMessage("Il pulsante con id 'go-to-notifications' non è presente nel DOM.")
                .until(ExpectedConditions.presenceOfElementLocated(vaiAlleNotificheLocator));

        try {
            getWebDriverWait(10).until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".MuiBackdrop-root, .spinner, .overlay-loader")));
        } catch (TimeoutException ignored) {
            logger.warn("Overlay ancora presente, si tenta comunque il click.");
        }

        js().executeScript("arguments[0].scrollIntoView({block: 'center'});", vaiAlleNotifiche);
        webTool.waitTime(1); // stabilizzazione

        vaiAlleNotifiche = getWebDriverWait(10)
                .withMessage("Il pulsante 'Vai alle notifiche' non è cliccabile.")
                .until(ExpectedConditions.elementToBeClickable(vaiAlleNotificheLocator));

        try {
            vaiAlleNotifiche.click();
            logger.info("Clic sul pulsante 'Vai alle notifiche' eseguito con successo.");
        } catch (Exception e) {
            logger.warn("Click normale fallito. Si tenta il click via JavaScript.");
            js().executeScript("arguments[0].click();", vaiAlleNotifiche);
            logger.info("Click via JavaScript eseguito correttamente.");
        }
    }

}
