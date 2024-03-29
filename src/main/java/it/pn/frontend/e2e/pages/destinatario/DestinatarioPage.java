package it.pn.frontend.e2e.pages.destinatario;

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

import java.util.List;

public class DestinatarioPage extends BasePage {

    //Questa classe è utilizzata per metodi in comune tra PF e PG

    private final Logger logger = LoggerFactory.getLogger("DestinatarioPage");

    public DestinatarioPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "startDate")
    WebElement dataInizioField;
    @FindBy(id = "endDate")
    WebElement dataFineField;
    @FindBy(id = "side-item-Notifiche")
    WebElement sideItemNotificheButton;
    @FindBy(id = "notificationsTable.body.row")
    List<WebElement> listaNotificheDelegante;

    public void inserimentoDataErrato() {
        String data = "01/01/1111";
        getWebDriverWait(10).withMessage("Il campo data inizio non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataInizioField));
        dataInizioField.click();
        dataInizioField.sendKeys(data);
        getWebDriverWait(3).withMessage("Il valore della data che si vuole inserire non corrisponde").until(ExpectedConditions.attributeToBe(this.dataInizioField, "value", data));
        getWebDriverWait(10).withMessage("Il campo data fine non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataFineField));
        dataFineField.click();
        dataFineField.sendKeys(data);
        getWebDriverWait(3).withMessage("Il valore della data che si vuole inserire non corrisponde").until(ExpectedConditions.attributeToBe(this.dataFineField, "value", data));
    }

    public boolean isDateBoxInvalid(){
        final String isTextboxInvalid = "true";
        boolean invalidBoxDate = true;
        try {
            getWebDriverWait(10).withMessage("Il campo data inizio non è visibile").until(ExpectedConditions.visibilityOfAllElements(this.dataInizioField, this.dataFineField));
            String ariaInvalidInizio = dataInizioField.getAttribute("aria-invalid");
            String ariaInvalidFine = dataFineField.getAttribute("aria-invalid");
            if(isTextboxInvalid.equals(ariaInvalidInizio) || isTextboxInvalid.equals(ariaInvalidFine)){
                logger.info("Almeno un campo data è in stato invalido");
            }else{
                logger.error("Nessuno dei campi data è passato allo stato invalido");
                Assert.fail("Nessuno dei campi data è passato allo stato invalido");
                invalidBoxDate = false;
            }
        } catch (TimeoutException e){
            logger.error("Campi data non visualizzati correttamente con errore: " + e.getMessage());
            Assert.fail("Campi data non visualizzati correttamente con errore: " + e.getMessage());
        }
        return invalidBoxDate;
    }

    public void clickButtonNotificheDelegateOnSideMenu(String nomeDelegante){
        logger.info("verifica bottone notifiche nel layout");

        getWebDriverWait(10).until(ExpectedConditions.visibilityOf(this.sideItemNotificheButton));
        sideItemNotificheButton.click();

        String id = "side-item-" + nomeDelegante;
        By buttonNotificheOnSideMenu = By.id(id);
        getWebDriverWait(10).withMessage("bottone notifiche nel layout non visibile").until(ExpectedConditions.visibilityOfElementLocated(buttonNotificheOnSideMenu));
        this.js().executeScript("arguments[0].click()", this.element(buttonNotificheOnSideMenu));

    }

    public void clickSulDettaglioNotificaDelegante(){
        WebElement singolaNotificaDelegante = listaNotificheDelegante.get(0);
        getWebDriverWait(10).withMessage("la prima notifica della tabella non è visibile").until(ExpectedConditions.visibilityOf(singolaNotificaDelegante));
        logger.info("Si clicca sulla prima notifica del delegante");
        singolaNotificaDelegante.click();
    }

}
