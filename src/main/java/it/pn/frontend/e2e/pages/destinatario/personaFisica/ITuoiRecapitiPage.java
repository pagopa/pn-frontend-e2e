package it.pn.frontend.e2e.pages.destinatario.personaFisica;

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

public class ITuoiRecapitiPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("ITuoiRecapitiPage");

    @FindBy(xpath = "//div[@data-testid='sideMenuItem-I tuoi recapiti']")
    WebElement iTuoiRecapitiButton;



    public ITuoiRecapitiPage(WebDriver driver) {
        super(driver);
    }

    public void ITuoiRecapitiButtonClick() {
        this.iTuoiRecapitiButton.click();
    }

    public void waitLoadITuoiRecapitiPage() {
        try {
            By titlePageBy = By.id("title-of-page");
            By subTitlePageBy = By.xpath("//div[contains(@class,'MuiTypography-root MuiTypography-body1 css-f4v438')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titlePageBy));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(subTitlePageBy));
            logger.info("La pagina I Tuoi Recapiti si vede correttamente");
        }catch (TimeoutException e){
            logger.error("La pagina I Tuoi Recapiti NON si vede correttamente con errori:"+e.getMessage());
            Assert.fail("La pagina I Tuoi Recapiti NON si vede correttamente con errori:"+e.getMessage());
        }
    }

}
