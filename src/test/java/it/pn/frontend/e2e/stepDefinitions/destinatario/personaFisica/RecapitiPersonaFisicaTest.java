package it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.personaFisica.RecuperoOTPRecapiti;
import it.pn.frontend.e2e.common.RecapitiDestinatarioPage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.ITuoiRecapitiPage;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.utility.DataPopulation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RecapitiPersonaFisicaTest {
    private static final Logger logger = LoggerFactory.getLogger("InserimentoOTPSbagliato");
    private final WebDriver driver = Hooks.driver;

    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);

    private final  DataPopulation dataPopulation = new DataPopulation();

    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;

    @When("Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti")
    public void ITuoiRecapitiButtonClick(){
        logger.info("Si cerca di cliccare il bottone I Tuoi Recapiti");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);
        iTuoiRecapitiPage.ITuoiRecapitiButtonClick();
    }

    @And("Si visualizza correttamente la pagina I Tuoi Recapiti")
    public void siVisualizzaCorrettamenteLaPaginaITuoiRecapiti() {
        logger.info("Si controlla che si visualizza correttamente la pagina I Tuoi Recapiti");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce la PEC del persona fisica {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECDelDestinatario(String dpFile) {
        logger.info("Si inserisce la email PEC");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> personaFisica = dataPopulation.readDataPopulation(dpFile +".yaml");
        String emailPEC = personaFisica.get("emailPec").toString();

        recapitiDestinatarioPage.insertEmailPEC(emailPEC);
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone conferma")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma() {
        logger.info("Si cerca di cliccare sul bottone conferma");

        recapitiDestinatarioPage.confermaButtonClick();
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP() {
        logger.info("Si visualizza correttamente il pop-up di inserimento OTP");
        String url = "https://webapi.test.notifichedigitali.it/address-book/v1/digital-address";
        recapitiDestinatarioPage.waitLoadPopUp();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (verificaChiamataEmail(url)){
            logger.info("La chiamata per inviare l'otp è stata effettuata");
        }else {
            logger.error("La chiamata per inviare l'otp NON è stata effettuata");
            Assert.fail("La chiamata per inviare l'otp NON è stata effettuata");
        }
    }

    private boolean verificaChiamataEmail(String url) {
        for (NetWorkInfo info: netWorkInfos) {
            if (info.getRequestUrl().contains(url) && info.getResponseStatus().equals("200")){
                return true;
            }
        }
        return false;
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceOTPSbagliato(String otp) {
        logger.info("Si inserisce l'otp sbagliato");

        recapitiDestinatarioPage.sendOTP(otp);
    }

    @And("Si visualizza correttamente il messaggio di errore")
    public void siVisualizzaCorrettamenteIlMessaggioDiErrore() {
        logger.info("Si controlla che il messaggio di errore sia visibile");

        recapitiDestinatarioPage.waitMessaggioErrore();
    }

    @And("Cliccare sul bottone Annulla")
    public void cliccareSulBottoneAnnulla() {
        logger.info("Si clicca sul bottone Annulla");

        recapitiDestinatarioPage.annullaButtonClick();
    }


    @And("Nella pagina I Tuoi Recapiti clicca sul bottone conferma")
    public void nellaPaginaITuoiRecapitiCliccaSulBottoneConferma() {
        logger.info("Si cerca di cliccare sul bottone conferma");

        recapitiDestinatarioPage.confermaButtonClickPopUp();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce la PEC errata {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECErrata(String emailPec) {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertEmailPEC(emailPec);
    }

    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di pec errata")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioDiPecErrata() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        String errorMessageRead = iTuoiRecapitiPage.getPecErrorMessage();
        Assert.assertEquals("messagio di errore letto : '" + errorMessageRead + "' non è uguale a : Indirizzo PEC non valido", "Indirizzo PEC non valido", errorMessageRead);
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che il tasto conferma sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoConfermaSiaBloccato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertTrue("il buttone Conferma non è disabilitato",iTuoiRecapitiPage.verificaBottoneConfermaDisabilitato());
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email errata {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailErrata(String emailErrata) {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertEmail(emailErrata);
    }

    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioEmailErrata() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        String errorMessageRead = iTuoiRecapitiPage.getEmailErrorMessage();
        Assert.assertEquals("messagio di errore letto : '" + errorMessageRead + "' non è uguale a : Indirizzo e-mail non valido", "Indirizzo e-mail non valido", errorMessageRead);
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via email sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoAvvisamiViaEmailSiaBloccato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertTrue("il buttone avvisami via email non è disabilitato",iTuoiRecapitiPage.avvisamiViaEmailIsDisabled());
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email del PF {string} e clicca sul bottone avvisami via email")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailDelPFECliccaSulBottoneAvvisamiViaEmail(String dpFile) {
        logger.info("Si inserisce la email");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> personaFisica = dataPopulation.readDataPopulation(dpFile +".yaml");
        String email = personaFisica.get("mail").toString();

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertEmail(email);
        iTuoiRecapitiPage.clickAvvisamiViaEmail();
    }

    @And("Si visualizza correttamente il pop-up e si clicca su conferma")
    public void siVisualizzaCorrettamenteIlPopUpESiCliccaSuConferma() {
        logger.info("click popup conferma email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertFalse("il popup Conferma email non si visualizza",iTuoiRecapitiPage.verificaPopupConfirmaEmail());

        iTuoiRecapitiPage.clickHoCapitoCheckBoxPopup();
        iTuoiRecapitiPage.confirmaEmailPopup();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono del PF {string} e clicca sul bottone avvisami via SMS")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoDelPF(String dpFile) {

        logger.info("Si inserisce il numero di telefono PF");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String,Object> personaFisica = dataPopulation.readDataPopulation(dpFile +".yaml");
        String phoneNumber = personaFisica.get("telefono").toString();

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertTelephoneNumber(phoneNumber);
        iTuoiRecapitiPage.clickAvvisamiViaSMS();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono errato {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoErrato(String numeroErrato) {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertTelephoneNumber(numeroErrato);
         }

    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di numero di telefono errato")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioDiNumeroDiTelefonoErrato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        String errorMessageRead = iTuoiRecapitiPage.getPhoneErrorMessage();
        Assert.assertEquals("messagio di errore letto : '" + errorMessageRead + "' non è uguale a : Numero di cellulare non valido", "Numero di cellulare non valido", errorMessageRead);

    }

    @And("Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via sms sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoAvvisamiViaSmsSiaBloccato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertTrue("il buttone avvisami via SMS non è disabilitato",iTuoiRecapitiPage.avvisamiViaSMSIsDisabled());
    }

    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequest(String dpFile) {

        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile+".yaml");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();

        String startUrl = "http://localhost:8887/";
        String url = startUrl+recuperoOTPRecapiti.getUrlEndPoint()+personaFisica.get("emailPec");
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results){
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPpec",OTP);
            dataPopulation.writeDataPopulation(dpFile+".yaml",personaFisica);
        }else {
            logger.error("La chiamata ha risposto con questo codice: "+recuperoOTPRecapiti.getResponseCode());
            Assert.fail("La chiamata ha risposto con questo codice: "+recuperoOTPRecapiti.getResponseCode());
        }
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il codice OTP {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP(String dpFile) {
        logger.info("Si inserisce il codice OTP di verifica");

        String otp = dataPopulation.readDataPopulation(dpFile+".yaml").get("OTPpec").toString();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.sendOTP(otp);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
    }

    @Then("Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLaPecSiaStataInseritaCorrettamente() {
        logger.info("Si controlla che la pec sia stata inserita correttamente");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();

        logger.info("Si clicca su conferma nel pop-up");

        if(recapitiDestinatarioPage.siVisualizzaPopUpConferma()){
            recapitiDestinatarioPage.clickConfermaButton();
            recapitiDestinatarioPage.visualizzaValidazione();
        }else {
            recapitiDestinatarioPage.verificaPecAssociata();
        }

    }


    @And("Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaLOTPDellaEmailTramiteRequestMethod(String dpFile) {
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile+".yaml");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String startUrl = "http://localhost:8887/";
        String url = startUrl+recuperoOTPRecapiti.getUrlEndPoint()+personaFisica.get("mail");
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results){
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPmail",OTP);
            dataPopulation.writeDataPopulation(dpFile+".yaml",personaFisica);
        }else {
            logger.error("La chiamata ha risposto con questo codice: "+recuperoOTPRecapiti.getResponseCode());
            Assert.fail("La chiamata ha risposto con questo codice: "+recuperoOTPRecapiti.getResponseCode());
        }
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLOTPRicevutoViaEmail(String dpFile) {
        logger.info("Si inserisce il codice OTP di verifica");

        String otp = dataPopulation.readDataPopulation(dpFile+".yaml").get("OTPmail").toString();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.sendOTP(otp);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che la Email sia presente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLaEmailSiaPresente() {
        logger.info("Si controlla che la Email sia stata inserita correttamente");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();

            recapitiDestinatarioPage.verificaMailAssociata();
        }

    @And("Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email")
    public void nellaPaginaITuoiRecapitiSiControllaCheCiSiaGiaUnaEmail() {
        logger.info("Si controlla che che ci sia già una Email");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();

        BackgroundTest backgroundTest = new BackgroundTest();

        if (!recapitiDestinatarioPage.verificaMailAssociata()){
            backgroundTest.aggiuntaEmail();
        }
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone modifica")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneModifica() {
        logger.info("Si clicca sul bottone modifica");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();

        recapitiDestinatarioPage.clickSuModifica();
    }
    @And("Nella pagina I Tuoi Recapiti si inserisce la nuova Email del PF {string} e clicca su salva")
    public void nellaPaginaITuoiRecapitiSiInserisceLaNuovaEmailDelPFECliccaSulBottoneAvvisamiViaEmail(String dpFile) {
        logger.info("Si inserisce la nuova Email e si clicca sul bottone avvisami via email");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();

        iTuoiRecapitiPage.cancellaTesto();

        String email = dataPopulation.readDataPopulation(dpFile+".yaml").get("email").toString();
        iTuoiRecapitiPage.insertEmail(email);
        iTuoiRecapitiPage.clickSalvaemail();

    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata")
    public void nellaPaginaITuoiRecapitiSiControllaCheLaEmailSiaStataModificata() {
        logger.info("Si controlla che la Email sia stata modificata");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.verificaEmailModificata();
    }
    @And("Nella pagina I Tuoi Recapiti si recupera l'OTP della nuova Email tramite request method {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaLOTPDellaNuovaEmailTramiteRequestMethod(String dpFile) {
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile+".yaml");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: "+e.getMessage());
            throw new RuntimeException(e);
        }

        String startUrl = "http://localhost:8887/";
        String url = startUrl+recuperoOTPRecapiti.getUrlEndPoint()+personaFisica.get("email");
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results){
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPmail",OTP);
            dataPopulation.writeDataPopulation(dpFile+".yaml",personaFisica);
        }else {
            logger.error("La chiamata ha risposto con questo codice: "+recuperoOTPRecapiti.getResponseCode());
            Assert.fail("La chiamata ha risposto con questo codice: "+recuperoOTPRecapiti.getResponseCode());
        }
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone elimina email e si conferma nel pop up")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneElimina() {
        logger.info("Si clicca sul bottone elimina email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

        iTuoiRecapitiPage.eliminaEmailEsistente();
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non sia presente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoEmailNonSiaPresente() {
        logger.info("Si controlla che l'indirizzo Email non sia presente");

        recapitiDestinatarioPage.verificaMailAssociata();
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteLaSezioneAltriRecapiti() {
        logger.info("Si controlla che l'indirizzo Email non sia presente");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.visualizzazioneSezioneAltriRecapiti();
                
    }
    @And("Nella pagina I Tuoi Recapiti si controlla che ci sia già una pec")
    public void nellaPaginaITuoiRecapitiSiControllaCheCiSiaGiaUnaPec() {
        logger.info("Si controlla la presenza di una pec");
        String pec = dataPopulation.readDataPopulation("personaFisica.yaml").get("emailPec").toString();
        BackgroundTest backgroundTest = new BackgroundTest();
        if (!recapitiDestinatarioPage.siVisulizzaPecInserita()){
            backgroundTest.aggiungiPECPF();
        }else if(!recapitiDestinatarioPage.siControllaPECModificata(pec)){
            recapitiDestinatarioPage.eliminaPecEsistente();
            backgroundTest.aggiungiPECPF();
        }
    }
    @And("Nella pagina I Tuoi Recapiti si inserisce una nuova PEC della persona fisica {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceUnaNuovaPECDellaPersonaFisica(String PEC) {
        logger.info("Si inserisce una nuova PEC");
        recapitiDestinatarioPage.cancellaTesto();
        recapitiDestinatarioPage.insertEmailPEC(PEC);
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone salva")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneSalva() {
        logger.info("Si clicca sul bottone salva");
        recapitiDestinatarioPage.clickSuSalva();
    }

    @Then("Nella pagina I Tuoi Recapiti si verifica che la pec sia stata modificata {string}")
    public void nellaPaginaITuoiRecapitiSiVerificaCheLaPecSiaStataModificata(String pec) {
        logger.info("Si controlla che la PEC sia stata modificata");
        if(recapitiDestinatarioPage.siVisualizzaPopUpConferma()){
            recapitiDestinatarioPage.clickConfermaButton();
            recapitiDestinatarioPage.visualizzaValidazione();
        }else{
            if(recapitiDestinatarioPage.siControllaPECModificata(pec)){
                logger.info("La PEC è stata modificata");
            }else {
                logger.error("La pec non è stata modificata");
                Assert.fail("La pec non è stata modificata");
            }
        }
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneModificaPEC() {
        logger.info("Si clicca sul bottone modifica PEC");

        recapitiDestinatarioPage.clickSuModificaPEC();
    }

    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC tramite chiamata request {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaPECTramiteChiamataRequest(String pec) {
        logger.info("Si recupera il codice OTP della nuova pec");

        Map<String, Object> personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();

        String startUrl = "http://localhost:8887/";
        String url = startUrl+recuperoOTPRecapiti.getUrlEndPoint()+pec;
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results){
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPpec",OTP);
            dataPopulation.writeDataPopulation("personaFisica.yaml",personaFisica);
        }else {
            logger.error("La chiamata ha risposto con questo codice: "+recuperoOTPRecapiti.getResponseCode());
            Assert.fail("La chiamata ha risposto con questo codice: "+recuperoOTPRecapiti.getResponseCode());
        }

    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone elimina pec")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneEliminaPEC() {
        logger.info("Si clicca sul bottone elimina");
        recapitiDestinatarioPage.clickSuEliminaPec();
    }

    @And("Nel pop up elimina indirizzo pec si clicca sul bottone conferma")
    public void nelPopUpEliminaIndirizzoPecSiCliccaSulBottoneConferma() {
        logger.info("Si clicca sul bottone conferma");
        recapitiDestinatarioPage.waitLoadPopUpElimina();
        recapitiDestinatarioPage.clickSuComefermaElimina();
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che l'indirizzo pec non sia presente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoPecNonSiaPresente() {
        logger.info("Si controlla che la PEC sia stata eliminata");

        if (recapitiDestinatarioPage.siControllaNonPresenzaPEC()){
            logger.info("La PEC è stata eliminata correttamente");
        }else {
            logger.error("La PEC non è stata eliminata");
            Assert.fail("La PEC non è stata eliminata");
        }
    }

    @And("Nella sezione altri recapiti si seleziona l'ente {string}")
    public void nellaSezioneAltriRecapitiSiSelezionaLEnte(String dpFile) {
        logger.info("Si sceglie l'ente");

        Map<String,Object> mittente = dataPopulation.readDataPopulation(dpFile+".yaml");

        recapitiDestinatarioPage.insertEnte(mittente.get("comune").toString());

    }

    @And("Nella sezione altri recapiti si seleziona il tipo di indirizzo")
    public void nellaSezioneAltriRecapitiSiSelezionaIlTipoDiIndirizzo() {
        logger.info("Si selezione il tipo di indirizzo come PEC");

        recapitiDestinatarioPage.clickSuIndirizzoPEC();

    }

    @And("Nella sezione altri recapiti si inserisce la PEC aggiuntiva de persona fisica {string}")
    public void nellaSezioneAltriRecapitiSiInserisceLaPECAggiuntivaDePersonaFisica(String dpFile) {

        Map<String,Object> personaFisica = dataPopulation.readDataPopulation(dpFile+".yaml");
        recapitiDestinatarioPage.insertPECAggiuntiva(personaFisica.get("emailPec").toString());

    }

    @And("Nella sezione altri recapiti si clicca sul bottone associa")
    public void nellaSezioneAltriRecapitiSiCliccaSulBottoneAssocia() {
        logger.info("Si clicca sul bottone associa");

        recapitiDestinatarioPage.clickSuAssocia();

    }

    @Then("Nella sezione altri recapiti si controlla che la pec aggiuntiva sia stata inserita correttamente")
    public void nellaSezioneAltriRecapitiSiControllaCheLaPecAggiuntivaSiaStataInseritaCorrettamente() {
        logger.info("Si controlla che sia stata aggiunta la PEC");

        if(recapitiDestinatarioPage.siVisualizzaPopUpConferma()){
            recapitiDestinatarioPage.clickConfermaButton();
        }

        recapitiDestinatarioPage.aggionamentoPagina();

        recapitiDestinatarioPage.siControllaPECAggiunta();

    }

    @And("Nella sezione altri recapiti si inserisce la Email aggiuntiva della persona fisica {string}")
    public void nellaSezioneAltriRecapitiSiInserisceLaEmailAggiuntivaDellaPersonaFisica(String dpFile) {
        Map<String,Object> personaFisica = dataPopulation.readDataPopulation(dpFile+".yaml");
        recapitiDestinatarioPage.insertEmailAggiuntiva(personaFisica.get("mail").toString());
    }

    @And("Nella sezione altri recapiti si seleziona il tipo di indirizzo scegliendo email")
    public void nellaSezioneAltriRecapitiSiSelezionaIlTipoDiIndirizzoScegliendoEmail() {
        logger.info("Si seleziona il tipo di indirizzo scegliendo email");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

        iTuoiRecapitiPage.selezionaTipoEmail();

    }

    @Then("Nella sezione altri recapiti si controlla che la Email aggiuntiva sia stata inserita correttamente")
    public void nellaSezioneAltriRecapitiSiControllaCheLaEmailAggiuntivaSiaStataInseritaCorrettamente() {
        logger.info("Si controlla che sia stata aggiunta la email");

       /* if(recapitiDestinatarioPage.siVisualizzaPopUpConferma()){
            recapitiDestinatarioPage.clickConfermaButton();
        }

        recapitiDestinatarioPage.aggionamentoPagina();*/

        recapitiDestinatarioPage.siControllaEmailAggiunta();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il codice OTP della email {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTPDellaEmail(String dpFile) {
        logger.info("Si inserisce il codice OTP di verifica");

        String otp = dataPopulation.readDataPopulation(dpFile+".yaml").get("OTPmail").toString();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.sendOTP(otp);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
    }
}

