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

import java.util.List;

public class HomePagePG extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HomePagePG");

    public HomePagePG(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class = 'MuiButtonBase-root MuiFab-root MuiFab-circular MuiFab-sizeSmall MuiFab-primary css-ngdrb8']")
    List<WebElement> notificheDigitaliCardList;


    public void waitLoadHomePagePGPage() {
        try {
            By titlePageBy = By.xpath("//h4[contains(text(),'Panoramica')]");
            By subtitlePageBy = By.xpath("//h4[contains(text(),'Notifiche digitali')]");
            By cardNotificheDevBy = By.xpath("//h6[@aria-label='SEND - Notifiche Digitali DEV']");
            this.getWebDriverWait(30).withMessage("il titolo Panoramica nella pagina home page riepilogo dati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).withMessage("il sottotitolo nella pagina home page riepilogo dati non è visibile").until(ExpectedConditions.visibilityOfElementLocated(subtitlePageBy));
            this.getWebDriverWait(30).withMessage("la card SEND - Notifiche Digitali DEV non è visibile ").until(ExpectedConditions.visibilityOfElementLocated(cardNotificheDevBy));
            logger.info("HomePagePG caricata correttamente");
        } catch (TimeoutException e){
            logger.error("HomePagePG non caricata correttamente con errore: "+e.getMessage());
            Assert.fail("HomePagePG non caricata correttamente con errore: "+e.getMessage());
        }
    }

    public void clickSendNotificheDigitali(int i) {
        notificheDigitaliCardList.get(i).click();
    }
}
