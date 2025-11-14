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
                .withMessage("Titolo nella pagina Attiva domicilio digitale su SEND non corretto o non presente Titolo PG")
                .until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'Attiva domicilio digitale su SEND')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'Trasferisci il domicilio digitale sulla piattaforma SEND')]")))
                ));

        getWebDriverWait(10)
                .withMessage("Testo nella pagina Attiva domicilio digitale su SEND non corretto o non presente Body PG")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul//li//p[contains(text(), 'Un ente invia una notifica SEND per')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul//li//p[contains(text(), 'La notifica viene ')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul//li//p[contains(text(), 'Quando arriva una notifica su SEND per la tua impresa, ti avvisiamo tramite email e, se lo desideri, anche via SMS')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul//li//p[contains(text(), 'Accedi alla notifica')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul//li//p[contains(text(), 'Apri il dettaglio della notifica digitale sulla piattaforma SEND')]")))
                        )
                );
    }

    public void verificaDellaPaginaLaEmailAziendalePerRicevereAvvisiSulleNotificheSEND() {
        getWebDriverWait(10)
                .withMessage("Titolo nella pagina La email aziendale per ricevere avvisi sulle notifiche SEND non corretto o non presente")
                .until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'Attiva domicilio digitale su SEND')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'Trasferisci il domicilio digitale sulla piattaforma SEND')]")))
                ));

        getWebDriverWait(10)
                .withMessage("Testo nella pagina La email aziendale per ricevere avvisi sulle notifiche SEND non corretto o non presente")
                .until(ExpectedConditions.and(
                                ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='emailSmsContactWizard']//p[contains(text(), 'L’email per ricevere avvisi sulle notifiche SEND')]"))),
                                ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='emailSmsContactWizard']//p[contains(text(), 'L’email aziendale dove avviseremo l’impresa quando c’è una nuova comunicazione a valore legale su SEND.')]")))
                        )
                );
    }

    public void verificaPresenzaModaleImportanzaAggiuntaContattiAziendali() {
        getWebDriverWait(10)
                .withMessage("Titolo nella modale Importanza aggiunta contatti aziendali non corretto o non presente")
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//h2[@id='confirmation-dialog-title' and contains(text(), 'Aggiungi un indirizzo email aziendale per gli avvisi!')]")))
                );
        getWebDriverWait(10)
                .withMessage("Testo nella modale Importanza aggiunta contatti non corretto o non presente")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'Per attivare il domicilio digitale SEND abbiamo bisogno di un indirizzo email aziendale, così possiamo')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//strong[contains(text(), 'inviare un avviso quando c’è una comunicazione a valore legale per la tua impresa.')]"))))
                );
    }

    public void verificaDellaPaginaStaiAttivandoIlDomicilioDigitaleSuSEND() {
        getWebDriverWait(10)
                .withMessage("Titolo nella pagina Stai attivando il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'Attiva domicilio digitale su SEND')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'Trasferisci il domicilio digitale sulla piattaforma SEND')]")))
                ));

        getWebDriverWait(10)
                .withMessage("Testo nella pagina Stai attivando il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.and(
                                ExpectedConditions.visibilityOfElementLocated((By.xpath("//h6[contains(text(), 'Stai attivando il domicilio digitale su SEND')]"))),
                                ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'impresa saranno recapitate a:')]"))),
                                ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(), 'dichiari di aver letto la ')]"))),
                                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@data-testid='tos-link' and contains(text(), 'Informativa privacy')]"))),
                                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@data-testid='tos-link' and contains(text(), 'Termini del servizio')]")))
                        )
                );
    }

    public void verificaDellaTYPHaiAttivatoIlDomicilioDigitaleSuSEND() {
        getWebDriverWait(10)
                .withMessage("Titolo nella TYP Hai attivato il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='wizard-feedback-step']//h4[contains(text(), 'Hai attivato il domicilio digitale su SEND')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella TYP Hai attivato il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.and(
                                ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='wizard-feedback-step']//p[contains(text(), 'impresa riceverà le comunicazioni a valore legale al domicilio digitale su SEND e un messaggio ai recapiti che hai scelto.')]"))),
                                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='wizard-feedback-button' and contains(text(),'Ok, ho capito')]")))
                        )
                );
    }

    public void verificaDellaPaginaGestisciIlDomicilioDigitalePerPG() {
        getWebDriverWait(10)
                .withMessage("Titolo nella pagina Gestisci il domicilio digitale per PG non corretto o non presente")
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='wizard-title']//p[contains(text(),'Gestisci domicilio digitale')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella pagina Gestisci il domicilio digitale per PG non corretto o non presente")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//h6[@data-testid='legalContactsTitle']"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(),'Personalizza il domicilio digitale per ente mittente')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(),'Scegli dove ricevere le notifiche inviate alla tua impresa da un ente specifico.')]"))),
                        ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@data-testid='legalContactManager']//button[contains(text(),'Personalizza per ente')]")))
                        )
                );
    }

    public void verificaDellaPaginaPersonalizzaIlTuoDomicilioDigitalePerEnteMittente() {
        getWebDriverWait(10)
                .withMessage("Titolo nella pagina Personalizza il tuo domicilio digitale per ente mittente non corretto o non presente")
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='wizard-title']//p[contains(text(),'Personalizza il domicilio digitale per ente mittente')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella pagina Personalizza il tuo domicilio digitale per ente mittente non corretto o non presente")
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='addSpecialContact']//p[contains(text(),'Il domicilio digitale personalizzato dove la tua impresa riceverà le notifiche SEND inviate da un ente specifico')]")))
                );
    }

    public void verificaDellaTYPHaiTrasferitoIlDomicilioDigitaleSuSEND() {
        getWebDriverWait(10)
                .withMessage("Titolo nella TYP Hai trasferito il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='wizard-feedback-step']//h4[contains(text(), 'Hai trasferito il domicilio digitale della tua impresa su SEND')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella TYP Hai trasferito il domicilio digitale su SEND per PG non corretto o non presente")
                .until(ExpectedConditions.and(
                                ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='wizard-feedback-step']//p[contains(text(), 'impresa riceverà le comunicazioni a valore legale al domicilio digitale su SEND e un messaggio ai recapiti che hai scelto.')]"))),
                                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='wizard-feedback-button' and contains(text(),'Ok, ho capito')]")))
                        )
                );
    }

    public void verificaDellaPaginaUsaUnaPECComeDomicilioDigitalePG() {
        getWebDriverWait(10)
                .withMessage("Titolo nella pagina Usa una PEC come domicilio digitale non corretto o non presente")
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='wizard-title']//p[contains(text(),'Usa una PEC come domicilio digitale')]")))
                );

        getWebDriverWait(10)
                .withMessage("Testo nella pagina Usa una PEC come domicilio digitale non corretto o non presente")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(),'Inserisci la PEC della tua impresa')]"))),
                        ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(),'Quando un ente invia una notifica SEND alla tua impresa, ricevi la comunicazione a valore legale sulla PEC che hai scelto.')]"))),
                        ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Indietro')]")))
                ));
    }

    public void verificaDellaPaginaHaiAggiornatoIlTuoDomicilioDigitale() {
        getWebDriverWait(10)
                .withMessage("Titolo nella TYP Hai aggiornato il tuo domicilio digitale non corretto o non presente")
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@data-testid='wizard-feedback-step']//h4[contains(text(), 'Hai aggiornato il domicilio digitale della tua impresa')]")))
                );
    }
}
