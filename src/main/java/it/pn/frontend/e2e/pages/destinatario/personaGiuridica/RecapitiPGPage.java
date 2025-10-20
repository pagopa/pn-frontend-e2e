package it.pn.frontend.e2e.pages.destinatario.personaGiuridica;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RecapitiPGPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(RecapitiPGPage.class);


    public RecapitiPGPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadRecapitiPage() {
        getWebDriverWait(10)
                .withMessage("Il titolo Recapiti della pagina non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Recapiti-page")));
        logger.info("Si visualizza correttamente Recapiti page");
    }

    public void verificaDellaPaginaAttivaDomicilioDigitaleSuSEND() {
        getWebDriverWait(10)
                .withMessage("Titolo nella pagina Attiva domicilio digitale su SEND non corretto o non presente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'Attiva domicilio digitale su SEND')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella pagina Attiva domicilio digitale su SEND non corretto o non presente")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul//li//p[contains(text(), 'Un ente invia una notifica SEND per')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul//li//p[contains(text(), 'La notifica ti viene ')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul//li//a[contains(text(), 'consegnata')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul//li//p[contains(text(), 'Accedi alla notifica')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul//li//p[contains(text(), 'Apri il dettaglio della notifica digitale sulla piattaforma SEND')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='pec-section']//p[contains(text(), 'Preferisci usare la PEC aziendale?')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='pec-section']//p[contains(text(), 'In alternativa, puoi usare una PEC come domicilio digitale per le notifiche di SEND.')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='pec-section']//button[contains(text(), 'Inserisci PEC')]")))
                        )
                );
    }

    public void verificaDellaPaginaLaEmailAziendalePerRicevereAvvisiSulleNotificheSEND() {
        getWebDriverWait(10)
                .withMessage("Titolo nella pagina La email aziendale per ricevere avvisi sulle notifiche SEND non corretto o non presente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'Attiva domicilio digitale su SEND')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella pagina La email aziendale per ricevere avvisi sulle notifiche SEND non corretto o non presente")
                .until(ExpectedConditions.and(
                                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='emailSmsContactWizard']//p[contains(text(), 'L’email per ricevere avvisi sulle notifiche SEND')]"))),
                                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='emailSmsContactWizard']//p[contains(text(), 'L’email aziendale dove avviseremo l’impresa quando c’è una nuova comunicazione a valore legale su SEND.')]")))
                        )
                );
    }

    public void verificaPresenzaModaleImportanzaAggiuntaContattiAziendali() {
        getWebDriverWait(10)
                .withMessage("Titolo nella modale Importanza aggiunta contatti aziendali non corretto o non presente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[@id='confirmation-dialog-title' and contains(text(), 'Aggiungere un indirizzo email aziendale per gli avvisi!')]")))
                );
        getWebDriverWait(10)
                .withMessage("Testo nella modale Importanza aggiunta contatti non corretto o non presente")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'Per attivare il domicilio digitale SEND abbiamo bisogno di un indirizzo email aziendale, così possiamo')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//strong[contains(text(), 'avvisarti quando c’è una comunicazione a valore legale per la tua impresa.')]"))),
                        ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'Ricorda che, se la notifica non viene letta in tempo, l’impresa potrebbe non essere al corrente di eventuali scadenze e incorrere in sanzioni.')]"))))
                );
    }

    public void verificaDellaPaginaStaiAttivandoIlDomicilioDigitaleSuSEND() {
        getWebDriverWait(10)
                .withMessage("Titolo nella pagina Stai attivando il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'Attiva domicilio digitale su SEND')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella pagina Stai attivando il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.and(
                                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h6[contains(text(), 'Stai attivando il domicilio digitale su SEND')]"))),
                                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'impresa saranno recapitate a:')]"))),
                                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(), 'Premendo “Attiva domicilio digitale” dichiari di aver letto la ')]"))),
                                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@data-testid='tos-link' and contains(text(), 'Informativa privacy')]"))),
                                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@data-testid='tos-link' and contains(text(), 'Termini del servizio')]")))
                        )
                );
    }

    public void verificaDellaTYPHaiAttivatoIlDomicilioDigitaleSuSEND() {
        getWebDriverWait(10)
                .withMessage("Titolo nella TYP Hai attivato il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='wizard-feedback-step']//h4[contains(text(), 'Hai attivato il domicilio digitale su SEND')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella TYP Hai attivato il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.and(
                                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='wizard-feedback-step']//p[contains(text(), 'impresa riceverà le comunicazioni a valore legale al Domicilio digitale su SEND e un messaggio ai recapiti che hai scelto.')]"))),
                                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='wizard-feedback-button' and contains(text(),'Ok, ho capito')]")))
                        )
                );
    }
}
