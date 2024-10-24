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
import it.pn.frontend.e2e.stepDefinitions.mittente.NotificaMittentePagoPATest;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RecapitiPersonaFisicaTest {

    private static final Logger logger = LoggerFactory.getLogger("InserimentoOTPSbagliato");

    public static String OTP;
    private final WebDriver driver = Hooks.driver;
    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);
    private final DataPopulation dataPopulation = new DataPopulation();
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;

    private final NotificaMittentePagoPATest notificaMittentePagoPATest = new NotificaMittentePagoPATest();
   private final ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

    @When("Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti")
    public void nellaPaginaPiattaformaNotifichePersonaFisicaSiCliccaSulBottoneITuoiRecapiti() {
        logger.info("Si cerca di cliccare il bottone I Tuoi Recapiti");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);
        iTuoiRecapitiPage.iTuoiRecapitiButtonClick();
    }

    @And("Si visualizza correttamente la pagina I Tuoi Recapiti")
    public void siVisualizzaCorrettamenteLaPaginaITuoiRecapiti() {
        logger.info("Si controlla che si visualizza correttamente la pagina I Tuoi Recapiti");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione 'E-mail o numero di cellulare'")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteLaSezioneEmailONumeroDiCellulare() {
        logger.info("Si controlla che si visualizza correttamente la sezione 'E-mail o numero di cellulare'");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadCourtesyContacts();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce la PEC {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECDelDestinatario(String emailPec ) {
        logger.info("Si inserisce la email PEC");
        recapitiDestinatarioPage.insertEmailPEC(emailPec);
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email {string} per la PEC del destinatario")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailPerLaPECDelDestinatario(String emailPEC) {
        logger.info("Si inserisce la email PEC");
        recapitiDestinatarioPage.insertEmailPEC(emailPEC);
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'indirizzo della PEC {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLIndirizzoDellaPECDelDestinatario(String emailPEC) {
        logger.info("Si inserisce la email PEC");
        recapitiDestinatarioPage.insertEmailPEC(emailPEC);
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone conferma")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneConferma() {
        logger.info("Si cerca di cliccare sul bottone conferma");
        WebTool.waitTime(10);
        recapitiDestinatarioPage.confermaButtonClick();
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlPopUpDiInserimentoOTP() {
        logger.info("Si visualizza correttamente il pop-up di inserimento OTP");
        String url = WebTool.getApiBaseUrl() + "addresses";
        recapitiDestinatarioPage.waitLoadPopUp();
        WebTool.waitTime(3);
        if (verificaChiamataEmail(url)) {
            logger.info("La chiamata per inviare l'otp è stata effettuata");
        } else {
            logger.error("La chiamata per inviare l'otp NON è stata effettuata");
            Assert.fail("La chiamata per inviare l'otp NON è stata effettuata");
        }
    }

    private boolean verificaChiamataEmail(String url) {
        for (NetWorkInfo info : netWorkInfos) {
            if (info.getRequestUrl().contains(url) && info.getResponseStatus().equals("200")) {
                logger.info("La chiamata per inviare email é utilizzabile");
                return true;
            }
        }
        logger.info("La chiamata per inviare email non é utilizzabile");
        return false;
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceOTPSbagliato(String otp) {
        logger.info("Si inserisce l'otp sbagliato");
        recapitiDestinatarioPage.sendOTP(otp);
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceOTPSbagliato3Volte(String otp) {
        logger.info("Si inserisce l'otp sbagliato 3 volte");
        int attempts = 0;
        while (attempts < 3) {
            recapitiDestinatarioPage.sendOTP(otp);
            recapitiDestinatarioPage.confermaButtonClickPopUp();
            WebTool.waitTime(2);
            recapitiDestinatarioPage.clearOTP();
            attempts++;
        }
        recapitiDestinatarioPage.sendOTP(otp);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
        WebTool.waitTime(2);
    }

    @And("Si visualizza correttamente il messaggio di errore")
    public void siVisualizzaCorrettamenteIlMessaggioDiErrore() {
        logger.info("Si controlla che il messaggio di errore sia visibile");

        if (!recapitiDestinatarioPage.waitMessaggioErrore()) {
            logger.error("Il messaggio di errore non viene visualizzato");
            Assert.fail("Il messaggio di errore non viene visualizzato");
        }
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
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECErrata(String pecErrata) {
        logger.info("Si inserisce la PEC errata");
        recapitiDestinatarioPage.insertEmailPEC(pecErrata);
    }

    @Then("Si visualizza correttamente il messaggio di pec errata")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioDiPecErrata() {
        String errorMessageRead = recapitiDestinatarioPage.getPecErrorMessage();
        Assert.assertEquals("messaggio di errore letto : '" + errorMessageRead + "' non è uguale a : Indirizzo PEC non valido", "Indirizzo PEC non valido", errorMessageRead);
    }

    @And("Si controlla che il tasto conferma sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoConfermaSiaBloccato() {
        Assert.assertTrue("il buttone Conferma non è disabilitato", recapitiDestinatarioPage.verificaBottoneConfermaDisabilitato());
    }

    @And("Si controlla che il tasto avvisami via email sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoAvvisamiViaEmailSiaBloccato() {

        Assert.assertTrue("il bottone avvisami via email non è disabilitato", recapitiDestinatarioPage.avvisamiViaEmailIsDisabled());
    }

    @And("Si inserisce l'email della {string} e si clicca sul bottone avvisami via email")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailDelPFECliccaSulBottoneAvvisamiViaEmail(String dpFile) {
        logger.info("Si inserisce la email");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String email = personaFisica.get("mail").toString();

        recapitiDestinatarioPage.insertEmail(email);
        recapitiDestinatarioPage.clickAvvisamiViaEmail();
    }

    @And("Si inserisce l'email {string} e si clicca sul bottone avvisami via email")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailDelPFECliccaSulBottoneAvvisami(String email) {
        logger.info("Si inserisce la email");

        recapitiDestinatarioPage.insertEmail(email);
        recapitiDestinatarioPage.clickAvvisamiViaEmail();
    }

    @And("Si visualizza correttamente il pop-up e si clicca su conferma")
    public void siVisualizzaCorrettamenteIlPopUpESiCliccaSuConferma() {
        logger.info("click pop-up conferma email");

        //Assert.assertFalse("il popup Conferma email non si visualizza", recapitiDestinatarioPage.verificaPopUpConfermaEmail());
       // recapitiDestinatarioPage.clickHoCapitoCheckBoxPopup();
        recapitiDestinatarioPage.confermaEmailPopup();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono del PF {string} e clicca sul bottone avvisami via SMS")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoDelPF(String dpFile) {

        logger.info("Si inserisce il numero di telefono PF");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String phoneNumber = personaFisica.get("telefono").toString();

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertTelephoneNumber(phoneNumber);
        iTuoiRecapitiPage.clickAvvisamiViaSMS();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il numero di telefono PF {string} e clicca sul bottone avvisami via SMS")
    public void nellaPaginaITuoiRecapitiSiInserisceIlNumeroDiTelefonoPF(String phoneNumber) {

        logger.info("Si inserisce il numero di telefono PF");

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
        Assert.assertEquals("messaggio di errore letto : '" + errorMessageRead + "' non è uguale a : Numero di cellulare non valido", "Numero di cellulare non valido", errorMessageRead);

    }

    @And("Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via sms sia bloccato")
    public void nellaPaginaITuoiRecapitiSiControllaCheIlTastoAvvisamiViaSmsSiaBloccato() {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        Assert.assertTrue("il bottone avvisami via SMS non è disabilitato", iTuoiRecapitiPage.avvisamiViaSMSIsDisabled());
    }

    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequest(String dpFile) {

        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("emailPec");
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPpec", OTP);
            dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("emailPec");
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                String OTP = recuperoOTPRecapiti.getResponseBody();
                logger.info("OTTTPPP"+OTP);
                personaFisica.put("OTPpec", OTP);
                dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
            } else {
                logger.error("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
            }
        }
    }

    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request dell'email {string} e viene inserito")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPTramiteChiamataRequestDellEmailEVieneInserito(String email) {

        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + email;
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            String OTP = recuperoOTPRecapiti.getResponseBody();
            iTuoiRecapitiPage.sendOTP(OTP);
            recapitiDestinatarioPage.confermaButtonClickPopUp();
            if (recapitiDestinatarioPage.waitMessaggioErrore()) {
                logger.error("Il codice OTP inserito è sbagliato");
                Assert.fail("Il codice OTP inserito è sbagliato");
            }
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + email;
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                String OTP = recuperoOTPRecapiti.getResponseBody();
                iTuoiRecapitiPage.sendOTP(OTP);
                recapitiDestinatarioPage.confermaButtonClickPopUp();
                if (recapitiDestinatarioPage.waitMessaggioErrore()) {
                    logger.error("Il codice OTP inserito è sbagliato");
                    Assert.fail("Il codice OTP inserito è sbagliato");
                }
            } else {
                logger.error("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
            }
        }
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il codice OTP {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP(String dpFile) {
        logger.info("Si inserisce il codice OTP di verifica");

        String otp = dataPopulation.readDataPopulation(dpFile + ".yaml").get("OTPpec").toString();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.sendOTP(otp);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
        if (recapitiDestinatarioPage.waitMessaggioErrore()) {
            logger.error("Il codice OTP inserito è sbagliato");
            Assert.fail("Il codice OTP inserito è sbagliato");
        }

    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il codice OTP")
    public void nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTP() {
        logger.info("Si inserisce il codice OTP di verifica");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        WebTool.waitTime(2);
        iTuoiRecapitiPage.sendOTP(OTP);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
        if (recapitiDestinatarioPage.waitMessaggioErrore()) {
            logger.error("Il codice OTP inserito è sbagliato");
            Assert.fail("Il codice OTP inserito è sbagliato");
        }
    }

    @And("Si verifica se popup conferma presente")
    public void verificaPopupConferma(){
        if (recapitiDestinatarioPage.siVisualizzaPopUpConferma()) {
            logger.info("Si clicca su conferma nel pop-up");
            recapitiDestinatarioPage.clickConfermaButton();
            driver.navigate().refresh();
        }
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il codice OTP scaduto")
    public void nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTPScaduto() throws InterruptedException {
        logger.info("Si inserisce il codice OTP di verifica");
        Thread.sleep(910000);
        iTuoiRecapitiPage.sendOTP(OTP);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
    }

    @Then("Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLaPecSiaStataInseritaCorrettamente() {
        logger.info("Si controlla che la pec sia stata inserita correttamente");
        WebTool.waitTime(15);
        driver.navigate().refresh();
            if (!recapitiDestinatarioPage.verificaPecAssociata()) {
                logger.error("Pec non associata con errore");
                Assert.fail("Pec non associata con errore");
            }
    }

    @And("Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaLOTPDellaEmailTramiteRequestMethod(String dpFile) {
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("mail");
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPmail", OTP);
            dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("mail");
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                String OTP = recuperoOTPRecapiti.getResponseBody();
                personaFisica.put("OTPmail", OTP);
                dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
            } else {
                logger.error("La chiamata non ha risposto correttamente con codice:" + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata non ha risposto correttamentecon codice:" + recuperoOTPRecapiti.getResponseCode());
            }
        }
    }

    @And("Nella pagina I Tuoi Recapiti si recupera l'OTP della Email 'altri recapiti' tramite request method {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaLOTPDellaEmailAltriRecapitiTramiteRequestMethod(String dpFile) {
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("additionalEmail");
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPmail", OTP);
            dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("additionalEmail");
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                String OTP = recuperoOTPRecapiti.getResponseBody();
                personaFisica.put("OTPmail", OTP);
                dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
            } else {
                logger.error("La chiamata non ha risposto correttamente con codice:" + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata non ha risposto correttamentecon codice:" + recuperoOTPRecapiti.getResponseCode());
            }
        }
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLOTPRicevutoViaEmail(String dpFile) {
        logger.info("Si inserisce il codice OTP di verifica");

        String otp = dataPopulation.readDataPopulation(dpFile + ".yaml").get("OTPmail").toString();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.sendOTP(otp);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che la Email sia presente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLaEmailSiaPresente() {
        logger.info("Si controlla che la Email sia stata inserita correttamente");
        if (!recapitiDestinatarioPage.verificaMailAssociata()) {
            logger.error("Email non è stata inserita correttamente");
            Assert.fail("Email non è stata inserita correttamente");
        }
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email")
    public void nellaPaginaITuoiRecapitiSiControllaCheCiSiaGiaUnaEmail() {
        logger.info("Si controlla che che ci sia già una Email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);

        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");
        String email = personaFisica.get("mail").toString();

        BackgroundTest backgroundTest = new BackgroundTest();

        if (!recapitiDestinatarioPage.verificaMailAssociata()) {
            logger.info("verifica mail associata, step 1");
            backgroundTest.aggiuntaEmailPF();
        } else if (recapitiDestinatarioPage.controlloEmailAssociata(email)) {
            logger.info("verifica mail associata, step 2");
            iTuoiRecapitiPage.eliminaEmailEsistente();
            WebTool.waitTime(3);
            if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi e-mail")) {
                logger.info("verifica mail associata, step 3");

                recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
            } else {
                logger.info("verifica mail associata, step 4");
                recapitiDestinatarioPage.clickSuChiudiPopUp();
                recapitiDestinatarioPage.eliminaNuovaEmail();
                iTuoiRecapitiPage.eliminaEmailEsistente();
                recapitiDestinatarioPage.waitLoadPopUpElimina();
                recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
            }
            backgroundTest.aggiuntaEmailPF();
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

        String email = dataPopulation.readDataPopulation(dpFile + ".yaml").get("email").toString();
        iTuoiRecapitiPage.insertEmail(email);
        iTuoiRecapitiPage.clickSalvaEmail();

    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata")
    public void nellaPaginaITuoiRecapitiSiControllaCheLaEmailSiaStataModificata() {
        logger.info("Si controlla che la Email sia stata modificata");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.verificaEmailModificata();
    }

    @And("Nella pagina I Tuoi Recapiti si recupera l'OTP della nuova Email tramite request method {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaLOTPDellaNuovaEmailTramiteRequestMethod(String dpFile) {
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error("pausa con errore: " + e.getMessage());
            throw new RuntimeException(e);
        }

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("email");
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPmail", OTP);
            dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("email");
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                String OTP = recuperoOTPRecapiti.getResponseBody();
                personaFisica.put("OTPmail", OTP);
                dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
            } else {
                logger.error("La chiamata con url: " + url + " ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata  con url: " + url + " ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
            }
        }
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone elimina email e si conferma nel pop up")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneEliminaEmailESiConfermaNelPopUp() {
        logger.info("Si clicca sul bottone elimina email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

        iTuoiRecapitiPage.eliminaEmailEsistente();
        if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi email")) {
            recapitiDestinatarioPage.confermaButtonEliminaClick();
        } else {
            recapitiDestinatarioPage.clickSuChiudiPopUp();
            recapitiDestinatarioPage.eliminaNuovaPec();
            recapitiDestinatarioPage.waitLoadPopUpElimina();
            recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
        }
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone elimina email e si annulla nel pop up")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneEliminaEmailESiAnnullaNelPopUp() {
        logger.info("Si clicca sul bottone elimina email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

        iTuoiRecapitiPage.eliminaEmailEsistente();
        if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi email")) {
            recapitiDestinatarioPage.clickButtonAnnullaEliminazioneInPopUp();
        } else {
            recapitiDestinatarioPage.clickSuChiudiPopUp();
            recapitiDestinatarioPage.eliminaNuovaPec();
            recapitiDestinatarioPage.waitLoadPopUpElimina();
            recapitiDestinatarioPage.clickButtonAnnullaEliminazioneInPopUp();
        }
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non sia presente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoEmailNonSiaPresente() {
        logger.info("Si controlla che l'indirizzo Email non sia presente");
        if (recapitiDestinatarioPage.verificaMailField()) {
            logger.info("Email è stata eliminata correttamente");
        } else {
            logger.error("Email non è stata eliminata");
            Assert.fail("Email non è stata eliminata");
        }
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non è stata eleminata")
    public void nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoEmailNonEStataEleminata() {
        logger.info("Si controlla che l'indirizzo Email non stata eleminata");
        if (recapitiDestinatarioPage.verificaMailAssociata()) {
            logger.info("L'email non è stata eliminata");
        } else {
            logger.error("L'email è stata eliminata");
            Assert.fail("L'email è stata eliminata");
        }
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteLaSezioneAltriRecapiti() {
        logger.info("Si controlla che l'indirizzo Email non sia presente");

        recapitiDestinatarioPage.visualizzazioneSezioneAltriRecapiti();

    }

    @And("Nella pagina I Tuoi Recapiti di PF, si controlla che ci sia già una pec {string}")
    public void nellaPaginaITuoiRecapitiDiPfSiControllaCheCiSiaGiaUnaPec(String pec) {
        logger.info("Si controlla la presenza di una pec");
        //String pec = dataPopulation.readDataPopulation("personaFisica.yaml").get("emailPec").toString();
        BackgroundTest backgroundTest = new BackgroundTest();
        if (!recapitiDestinatarioPage.siVisualizzaPecInserita()) {
            backgroundTest.aggiungiPECPF();
        } else if (!recapitiDestinatarioPage.siControllaPECModificata(pec)) {
            recapitiDestinatarioPage.eliminaPecEsistente();
            backgroundTest.aggiungiPECPF();
        }
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce una nuova PEC {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceUnaNuovaPECDellaPersonaFisica(String pec) {
        logger.info("Si inserisce una nuova PEC");
        recapitiDestinatarioPage.cancellaTesto();
        recapitiDestinatarioPage.insertEmailPEC(pec);
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone salva")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneSalva() {
        logger.info("Si clicca sul bottone salva");
        recapitiDestinatarioPage.clickSuSalva();
    }

    @Then("Nella pagina I Tuoi Recapiti si verifica che la pec sia stata modificata {string}")
    public void nellaPaginaITuoiRecapitiSiVerificaCheLaPecSiaStataModificata(String pec) {
        logger.info("Si controlla che la PEC sia stata modificata");

        if (recapitiDestinatarioPage.siVisualizzaPopUpConferma()) {
            recapitiDestinatarioPage.clickConfermaButton();
            recapitiDestinatarioPage.visualizzaValidazione();
        } else {
            WebTool.waitTime(5);
            driver.navigate().refresh();
            if (recapitiDestinatarioPage.siControllaPECModificata(pec)) {
                logger.info("La PEC è stata modificata");
            } else {
                logger.error("La pec non è stata modificata");
                Assert.fail("La pec non è stata modificata");
            }
        }
    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneModificaPEC() {
        logger.info("Si clicca sul bottone modifica PEC");
        recapitiDestinatarioPage.clickSuModificaPEC();
        recapitiDestinatarioPage.verificaPecModificabile();

    }

    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC tramite chiamata request {string}")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaPECTramiteChiamataRequest(String dpFile) {
        logger.info("Si recupera il codice OTP della nuova pec");

        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
        String pec = personaFisica.get("pec").toString();
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + pec;
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            String OTP = recuperoOTPRecapiti.getResponseBody();
            personaFisica.put("OTPpec", OTP);
            dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + personaFisica.get("pec");
            logger.info("Chiamata verifica PEC :"+ url);
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                String OTP = recuperoOTPRecapiti.getResponseBody();
                personaFisica.put("OTPpec", OTP);
                dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
            } else {
                logger.error("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
            }
        }

    }

    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC {string} tramite chiamata request")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaEmailPECTramiteChiamataRequest(String emailPec) {
        logger.info("Si recupera il codice OTP della nuova pec");

        String pec = emailPec;
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + pec;
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            OTP = recuperoOTPRecapiti.getResponseBody();
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + emailPec;
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                OTP = recuperoOTPRecapiti.getResponseBody();
            } else {
                logger.error("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
            }
        }

    }
    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email {string} tramite chiamata request")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaEmailTramiteChiamataRequest(String mail) {
        logger.info("Si recupera il codice OTP della nuova email");
        RecuperoOTPRecapiti recuperoOTPRecapiti = new RecuperoOTPRecapiti();

        String startUrl = "http://localhost:8887/";
        String url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + mail;
        boolean results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
        if (results) {
            OTP = recuperoOTPRecapiti.getResponseBody();
        } else {
            String variabileAmbiente = System.getProperty("environment");
            if (variabileAmbiente.equalsIgnoreCase("test")) {
                startUrl = "http://internal-pn-ec-Appli-L4ZIDSL1OIWQ-1000421895.eu-south-1.elb.amazonaws.com:8080/";
            } else if (variabileAmbiente.equalsIgnoreCase("dev")) {
                startUrl = "http://internal-ecsa-20230409091221502000000003-2047636771.eu-south-1.elb.amazonaws.com:8080/";
            }
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + mail;
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                OTP = recuperoOTPRecapiti.getResponseBody();
            } else {
                logger.error("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
            }
        }

        logger.info("OTP Ricuperato:" + OTP);

    }

    @And("Nella pagina I Tuoi Recapiti si clicca sul bottone elimina pec")
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneEliminaPEC() {
        logger.info("Si clicca sul bottone elimina");
        recapitiDestinatarioPage.clickSuEliminaPec();
    }

    @And("Nel pop up elimina indirizzo pec si clicca sul bottone conferma")
    public void nelPopUpEliminaIndirizzoPecSiCliccaSulBottoneConferma() {
        logger.info("Si clicca sul bottone conferma");
        WebTool.waitTime(3);
        if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi PEC")) {
            recapitiDestinatarioPage.clickSuConfermaElimina();
        } else {
            recapitiDestinatarioPage.clickSuChiudiPopUp();
            recapitiDestinatarioPage.eliminaNuovaPec();
            recapitiDestinatarioPage.eliminaPecEsistente();
        }
    }


    @And("Nel pop up elimina indirizzo pec si clicca sul bottone annulla")
    public void nelPopUpEliminaIndirizzoPecSiCliccaSulBottoneAnnulla() {
        logger.info("Si clicca sul bottone annnulla");
        if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi PEC")) {
            recapitiDestinatarioPage.clickButtonAnnullaEliminazioneInPopUp();
        } else {
            recapitiDestinatarioPage.clickSuChiudiPopUp();
            recapitiDestinatarioPage.eliminaNuovaPec();
            recapitiDestinatarioPage.eliminaPecEsistente();
        }
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che l'indirizzo pec non sia presente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoPecNonSiaPresente() {
        logger.info("Si controlla che la PEC sia stata eliminata");

        if (recapitiDestinatarioPage.siControllaEliminazionePEC()) {
            logger.info("La PEC è stata eliminata correttamente");
        } else {
            logger.error("La PEC non è stata eliminata");
            Assert.fail("La PEC non è stata eliminata");
        }
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che l'indirizzo pec presente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoPecPresente() {
        logger.info("Si controlla che la PEC non stata eliminata");

        if (recapitiDestinatarioPage.siControllaPresenzaPEC()) {
            logger.info("La PEC non è stata eliminata");
        } else {
            logger.error("La PEC è stata eliminata");
            Assert.fail("La PEC è stata eliminata");
        }
    }

    @And("Nella sezione altri recapiti si seleziona l'ente {string}")
    public void nellaSezioneAltriRecapitiSiSelezionaLEnte(String dpFile) {
        logger.info("Si sceglie l'ente");

        Map<String, Object> mittente = dataPopulation.readDataPopulation(dpFile + ".yaml");

        recapitiDestinatarioPage.insertEnte(mittente.get("comune").toString());

    }

    @And("Nella pagina Recapiti si inserisce il numero di telefono {string} e clicca sul bottone avvisami via SMS")
    public void nellaPaginaRecapitiSiInserisceIlNumeroDiTelefono(String numero) {

        logger.info("Si inserisce il numero di telefono");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.insertTelephoneNumber(numero);
        iTuoiRecapitiPage.clickAvvisamiViaSMS();
    }

    @And("Nella sezione altri recapiti PG si seleziona l'ente {string}")
    public void nellaSezioneAltriRecapitiPGSiSelezionaLEnte(String ente) {
        logger.info("Si sceglie l'ente");
        recapitiDestinatarioPage.insertEnte(ente);
    }

    @And("Nella sezione altri recapiti si seleziona il tipo di indirizzo")
    public void nellaSezioneAltriRecapitiSiSelezionaIlTipoDiIndirizzo() {
        logger.info("Si selezione il tipo di indirizzo come PEC");

        recapitiDestinatarioPage.clickSuIndirizzoPEC();

    }

    @And("Nella sezione altri recapiti si inserisce la PEC aggiuntiva de persona fisica {string}")
    public void nellaSezioneAltriRecapitiSiInserisceLaPECAggiuntivaDePersonaFisica(String email) {
        recapitiDestinatarioPage.insertPECAggiuntiva(email);
        Map<String, Object> dataPersonaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");
        dataPersonaFisica.put("additionalEmail", email);
        dataPopulation.writeDataPopulation("personaFisica.yaml", dataPersonaFisica);
    }

    @And("Nella sezione altri recapiti si clicca sul bottone associa")
    public void nellaSezioneAltriRecapitiSiCliccaSulBottoneAssocia() {
        logger.info("Si clicca sul bottone associa");

        recapitiDestinatarioPage.clickSuAssocia();

    }

    @Then("Nella sezione altri recapiti si controlla che la pec aggiuntiva sia stata inserita correttamente")
    public void nellaSezioneAltriRecapitiSiControllaCheLaPecAggiuntivaSiaStataInseritaCorrettamente() {
        logger.info("Si controlla che sia stata aggiunta la PEC");

        if (recapitiDestinatarioPage.siVisualizzaPopUpConferma()) {
            recapitiDestinatarioPage.clickConfermaButton();
            recapitiDestinatarioPage.aggiornamentoPagina();
            recapitiDestinatarioPage.waitLoadPage();
        }
        String pec = dataPopulation.readDataPopulation("personaFisica.yaml").get("additionalEmail").toString();
        driver.navigate().refresh();
        WebTool.waitTime(10);
        if (!recapitiDestinatarioPage.verificaNuovaEmailEPEC(pec)) {
            logger.error("La email PEC non è stata associata correttamente");
            Assert.fail("La email PEC non è stata associata correttamente");
        }
    }

    @And("Nella sezione altri recapiti si inserisce la Email aggiuntiva della persona fisica {string}")
    public void nellaSezioneAltriRecapitiSiInserisceLaEmailAggiuntivaDellaPersonaFisica(String email) {
        recapitiDestinatarioPage.insertEmailAggiuntiva(email);
        Map<String, Object> dataPersonaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");
        dataPersonaFisica.put("additionalEmail", email);
        dataPopulation.writeDataPopulation("personaFisica.yaml", dataPersonaFisica);
    }

    @And("Nella sezione altri recapiti si seleziona il tipo di indirizzo scegliendo {string}")
    public void nellaSezioneAltriRecapitiSiSelezionaIlTipoDiIndirizzoScegliendoEmail(String tipoIndirizzo) {
        logger.info("Si seleziona il tipo di indirizzo scegliendo email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        if (tipoIndirizzo.equalsIgnoreCase("PEC"))
            iTuoiRecapitiPage.selezionaTipoPec();
        else {
            iTuoiRecapitiPage.selezionaTipoEmail();
        }
    }

    @Then("Nella sezione altri recapiti si controlla che la Email aggiuntiva sia stata inserita correttamente")
    public void nellaSezioneAltriRecapitiSiControllaCheLaEmailAggiuntivaSiaStataInseritaCorrettamente() {
        logger.info("Si controlla che sia stata aggiunta la email");
        recapitiDestinatarioPage.siControllaEmailAggiunta();
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce il codice OTP della email {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceIlCodiceOTPDellaEmail(String dpFile) {
        logger.info("Si inserisce il codice OTP di verifica");

        String otp = dataPopulation.readDataPopulation(dpFile + ".yaml").get("OTPmail").toString();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.sendOTP(otp);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec")
    public void nellaPaginaITuoiRecapitiSiControllaCheNonCiSiaGiaUnaPec() {
        logger.info("Si controlla che non ci sia una pec");
        BackgroundTest backgroundTest = new BackgroundTest();
        if (recapitiDestinatarioPage.verificaPecAssociata()) {
            backgroundTest.siEliminaPecEsistenteEAltriRecapitiAssociati();
        }
    }

    @And("Nella sezione altri recapiti si controlla l'esistenza di una email {string}")
    public void nellaSezioneAltriRecapitiSiControllaLEsistenzaDiUnaEmail(String dpFile) {
        logger.info("Si controlla l'esistenza di una altra email");

        Map<String, Object> personaFisica = this.dataPopulation.readDataPopulation(dpFile + ".yaml");
        String nuovaEmail = personaFisica.get("mail").toString();
        if (recapitiDestinatarioPage.verificaNuovaEmailEPEC(nuovaEmail)) {
            recapitiDestinatarioPage.eliminaNuovaEmail();
        }
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email")
    public void nellaPaginaITuoiRecapitiSiControllaCheNonCiSiaGiaUnaEmail() {
        logger.info("Si controlla che non ci sia già una email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        if (recapitiDestinatarioPage.verificaMailAssociata()) {
            iTuoiRecapitiPage.eliminaEmailEsistente();
            if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi e-mail")) {
                recapitiDestinatarioPage.confermaButtonEliminaClick();
            } else {
                recapitiDestinatarioPage.clickSuChiudiPopUp();
                recapitiDestinatarioPage.eliminaNuovaEmail();
                iTuoiRecapitiPage.eliminaEmailEsistente();
                recapitiDestinatarioPage.waitLoadPopUpElimina();
                recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
            }
        }
    }

    @And("Nella sezione altri recapiti si controlla l'esistenza di una PEC {string}")
    public void nellaSezioneAltriRecapitiSiControllaLEsistenzaDiUnaPEC(String dpFile) {
        logger.info("Si controlla esistenza di una PEC aggiuntiva");
        String pec = dataPopulation.readDataPopulation(dpFile + ".yaml").get("emailPec").toString();
        if (recapitiDestinatarioPage.verificaNuovaEmailEPEC(pec)) {
            recapitiDestinatarioPage.eliminaNuovaPec();
        }
    }

    @And("si verifica esistenza due pec")
    public void siVerificaEsistenzaDuePEC() {
        if (!recapitiDestinatarioPage.siVisualizzaPecInserita()) {
            BackgroundTest backgroundTest = new BackgroundTest();
            backgroundTest.aggiungiNuovaPECPF();
            backgroundTest.aggiungiPecSezioneGiaAssociati();
        }
    }

    @And("Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email diversa")
    public void nellaPaginaITuoiRecapitiSiControllaCheCiSiaGiaUnaEmailDiversa() {
        logger.info("Si controlla che che ci sia già una Email diversa");

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();

        BackgroundTest backgroundTest = new BackgroundTest();

        if (!recapitiDestinatarioPage.verificaMailAssociata()) {
            backgroundTest.aggiuntaEmailPF();
        }
    }

    @And("Si clicca sul bottone annulla per annullare la modifica della PEC e si verifica che non si possa modificare la PEC")
    public void siCliccaSulBottoneAnnullaPerAnnullareLaModificaDellaPEC() {
        logger.info("Si clicca sul bottone annulla");
        recapitiDestinatarioPage.clickSuAnnulla();
        recapitiDestinatarioPage.verificaPecNonModificabile();
    }

    @And("Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email")
    public void siVisualizzanoCorrettamenteGliElementiPostModifica() {
        logger.info("Si controlla che si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.checkPostModifica();
    }

    @When("Nella pagina I Tuoi Recapiti si visualizza correttamente il riquadro relativo alla PEC")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlRiquadroRelativoAllaPEC() {
        logger.info("Si controlla se il riquadro relativo alla PEC  visualizzata correttamente");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.checkRiquadroPEC();
    }

    @And("Nella sezione altri recapiti si clicca sul bottone conferma per inserire un recapito")
    public void nellaSezioneAltriRecapitiSiCliccaSulBottoneConfermaPerInserireUnRecapito() {
        logger.info("Si clicca su conferma");
        recapitiDestinatarioPage.clickConfermaRecapitoGiaPresente();
    }

    @And("Si verifica siano presenti recapiti digitali")
    public void siVerificaSianoPresentiRecapitiDigitali(Map<String, String> datiPF) {
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(driver);

        String email = datiPF.get("email");
        if (recapitiDestinatarioPage.siVisualizzaPecInserita()) {
            nellaPaginaITuoiRecapitiSiCliccaSulBottoneEliminaPEC();
            nelPopUpEliminaIndirizzoPecSiCliccaSulBottoneConferma();
            nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoPecNonSiaPresente();
        }
        if (recapitiDestinatarioPage.controlloEmailAssociata(email)) {
            iTuoiRecapitiPage.eliminaEmailEsistente();
            nellaPaginaITuoiRecapitiSiCliccaSulBottoneEliminaEmailESiConfermaNelPopUp();
            nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoEmailNonSiaPresente();
        }
    }

    @Then("Si clicca sul dropdown {string} di altri recapiti")
    public void siCliccaSulDropdownDiAltriRecapiti(String dropdown) {
        logger.info("Si clicca sul dropdown");
        recapitiDestinatarioPage.clickDropdownAltriRecapiti(dropdown);
    }

    @And("Si visualizza correttamente la lista degli enti")
    public void siVisualizzaCorrettamenteLaListaDegliEnti(List<String> enti) {
        logger.info("Si visualizza la lista degli enti");
        recapitiDestinatarioPage.visualizzaListaEnti(enti);
    }
}

