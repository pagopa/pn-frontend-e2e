package it.pn.frontend.e2e.section.destinatario;

import com.sun.source.tree.TryTree;
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

public class HeaderDESection extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("HeaderDESection");

    @FindBy(css = "button[aria-label='party-menu-button']")
    WebElement profiloUtenteMenu;



    public HeaderDESection(WebDriver driver) {
        super(driver);
    }

    public void waitLoadHeaderDESection(){
        try{
            By titleLabel = By.cssSelector("a[title='Sito di PagoPA S.p.A.']");
            By menuProfilo = By.cssSelector("button[aria-label='party-menu-button']");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(titleLabel));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuProfilo));
            logger.info("Header DE Section caricata");
        }catch (TimeoutException e){
            logger.error("Header DE Section non caricata con errore : "+e.getMessage());
            Assert.fail("Header DE Section non caricata con errore : "+e.getMessage());
        }
    }

    public void selezionaprofiloUtenteMenu(){
        this.js().executeScript("arguments[0].scrollIntoView(true);",this.profiloUtenteMenu);
        //this.profiloUtenteMenu.click();
        this.js().executeScript("arguments[0].click()",this.profiloUtenteMenu);
    }

    public void selezionaVoceEsci(){
        By esciVoce = By.xpath("//span[contains(text(),'Esci')]");
        this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(esciVoce));
        this.element(esciVoce).click();
    }

    public void waitUrlToken() {
        try {
            this.getWebDriverWait(30).until(ExpectedConditions.urlContains("token"));
            logger.info("Url token ------------------------>"+driver.getCurrentUrl());
        }catch (TimeoutException e){
            logger.error("Url token non trovato con errore:"+e.getMessage());
            Assert.fail("Url token non trovato con errore:"+e.getMessage());
        }
    }
}
