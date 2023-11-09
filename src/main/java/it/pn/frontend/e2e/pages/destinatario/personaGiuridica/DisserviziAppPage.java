package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisserviziAppPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger("Disservizi Page");

    public DisserviziAppPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadStatoDellaPiattaformaPage(){
        try{
            By DisserviziPageTitle = By.id("Stato della piattaforma-page");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(DisserviziPageTitle));
            logger.info("Si visualizza correttamente la sezione disservizi");
        }catch (TimeoutException e){
            logger.error("Non si visualizza correttamente la sezione disservizi con errore:"+e.getMessage());
            Assert.fail("Non si visualizza correttamente la sezione disservizi con errore"+e.getMessage());
        }
    }

    public void checkDatiPaginaDisservizi(){
        try {
            By titlePage = By.id("Stato della piattaforma-page");
            By subtitlePage = By.id("subtitle-page");
            By boxStatus = By.xpath("//div[@data-testid='app-status-bar']");
            By dateLastCheck = By.xpath("//div[@data-testid='appStatus-lastCheck']");
            this.getWebDriverWait(40).withMessage("titolo non presente").until(ExpectedConditions.visibilityOfElementLocated(titlePage));
            this.getWebDriverWait(40).withMessage("sottotitolo pagina non presente").until(ExpectedConditions.visibilityOfElementLocated(subtitlePage));
            this.getWebDriverWait(40).withMessage("stato dell'applicazione non presente").until(ExpectedConditions.visibilityOfElementLocated(boxStatus));
            this.getWebDriverWait(60).withMessage("ultimo aggiornamento stato piattaforma non presente").until(ExpectedConditions.visibilityOfElementLocated(dateLastCheck));
        }catch (TimeoutException e){
            logger.error("Dati presenti nella pagina stato della piattaforma non corretti: "+e.getMessage());
            Assert.fail("Dati presenti nella pagina stato della piattaforma non corretti: "+e.getMessage());
        }
    }

    public void checkElencoDisservizi() {
        try {
            By elementoDellaListaBy = By.id("tableDowntimeLog.row");
            this.getWebDriverWait(30).withMessage("tabella non trovata").until(ExpectedConditions.visibilityOfElementLocated(elementoDellaListaBy));
            By nomeColonnaDataInizioBy = By.xpath("//th[contains(text(),'Data di inizio')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaDataInizioBy));
            By nomeColonnaDataFineBy = By.xpath("//th[contains(text(),'Data di fine')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaDataFineBy));
            By nomeColonnaServizioBy = By.xpath("//th[contains(text(),'Servizio coinvolto')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaServizioBy));
            By nomeColonnaAttestazioniBy = By.xpath("//th[contains(text(),'Attestazioni opponibili a terzi')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaAttestazioniBy));
            By nomeColonnaStatoBy = By.xpath("//th[contains(text(),'Stato')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeColonnaStatoBy));
            logger.info("Si visualizza correttamente l'elenco dei disservizi");
        }catch (Exception e){
            logger.error("NON si visualizza correttamente l'elenco dei disservizi con errore:"+e.getMessage());
            Assert.fail("NON si visualizza correttamente l'elenco dei disservizi con errore:"+e.getMessage());
        }
    }

    public boolean checkDisservizioInCorso(){
        try{

            WebElement dateDisservizioCreato =  this.elements(By.xpath("//td[@data-testid='tableBodyCell']")).get(1);
            return this.getWebDriverWait(60).withMessage("disservizio non creato").until(ExpectedConditions.textToBePresentInElement(dateDisservizioCreato,"-"));
        }catch (TimeoutException e){
            logger.error("creazione disservizio con errore:"+e.getMessage());
            return false;
        }
    }
}
