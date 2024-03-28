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

    public static String OTP;
    private final WebDriver driver = Hooks.driver;
    private final RecapitiDestinatarioPage recapitiDestinatarioPage = new RecapitiDestinatarioPage(this.driver);
    private final DataPopulation dataPopulation = new DataPopulation();
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;

    @When("Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti")
    public void ITuoiRecapitiButtonClick() {
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
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECDelDestinatario(String dpFile) {
        logger.info("Si inserisce la email PEC");

        DataPopulation dataPopulation = new DataPopulation();
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation(dpFile + ".yaml");
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
        String varabileAmbiente = System.getProperty("environment");
        String url = "https://webapi." + varabileAmbiente + ".notifichedigitali.it/address-book/v1/digital-address";
        recapitiDestinatarioPage.waitLoadPopUp();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (verificaChiamataEmail(url)) {
            logger.info("La chiamata per inviare l'otp è stata effettuata");
        } else {
            logger.error("La chiamata per inviare l'otp NON è stata effettuata");
            Assert.fail("La chiamata per inviare l'otp NON è stata effettuata");
        }
        DataPopulation.waitTime(45);
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
    public void nellaPaginaITuoiRecapitiSiInserisceLaPECErrata(String dpFile) {
        logger.info("Si inserisce la PEC errata");
        String pecErrata = dataPopulation.readDataPopulation(dpFile + ".yaml").get("pecErrore").toString();
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

    @And("Nella pagina I Tuoi Recapiti si inserisce l'email errata {string}")
    public void nellaPaginaITuoiRecapitiSiInserisceLEmailErrata(String emailErrata) {
        recapitiDestinatarioPage.insertEmail(emailErrata);
    }

    @And("Nella pagina I Tuoi Recapiti si inserisce un email maggiore di {int} caratteri")
    public void nellaPaginaITuoiRecapitiSiInserisceUnEmailMaggioreDiCaratteri(int numeroCaratteri) {
        String email = "test";
        for (int i = 0; i < numeroCaratteri; i++) {
            email += "a";
        }
        recapitiDestinatarioPage.insertEmail(email);
    }

    @Then("Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteIlMessaggioEmailErrata() {
        String errorMessageRead = recapitiDestinatarioPage.getEmailErrorMessage();
        if (!errorMessageRead.contains("Indirizzo e-mail non valido") && !errorMessageRead.contains("Scrivi massimo 254 caratteri")) {
            Assert.fail("messaggio di errore letto : '" + errorMessageRead + "' non è uguale a : Indirizzo e-mail non valido o Scrivi massimo 254 caratteri");
        }
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

    @And("Si visualizza correttamente il pop-up e si clicca su conferma")
    public void siVisualizzaCorrettamenteIlPopUpESiCliccaSuConferma() {
        logger.info("click pop-up conferma email");

        Assert.assertFalse("il popup Conferma email non si visualizza", recapitiDestinatarioPage.verificaPopUpConfermaEmail());
        recapitiDestinatarioPage.clickHoCapitoCheckBoxPopup();
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

            System.out.println("Sono nella sezione di sopra, devo ancora capire bene che cosa fa questa sezione");
            System.out.println("\n\n\n"+OTP+"\n\n\n");

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

                System.out.println("sono nella sezione di sotto, devo ancora capire bene che cosa fa questa sezione");
                System.out.println("\n\n\n"+OTP+"\n\n\n");


                personaFisica.put("OTPpec", OTP);
                dataPopulation.writeDataPopulation(dpFile + ".yaml", personaFisica);
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
        iTuoiRecapitiPage.sendOTP(OTP);
        recapitiDestinatarioPage.confermaButtonClickPopUp();
        if (recapitiDestinatarioPage.waitMessaggioErrore()) {
            logger.error("Il codice OTP inserito è sbagliato");
            Assert.fail("Il codice OTP inserito è sbagliato");
        }

    }

    @Then("Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLaPecSiaStataInseritaCorrettamente() {
        logger.info("Si controlla che la pec sia stata inserita correttamente");
        DataPopulation.waitTime(10);
        driver.navigate().refresh();
        if (recapitiDestinatarioPage.siVisualizzaPopUpConferma()) {
            logger.info("Si clicca su conferma nel pop-up");
            recapitiDestinatarioPage.clickConfermaButton();
            recapitiDestinatarioPage.visualizzaValidazione();
        } else {
            if (!recapitiDestinatarioPage.verificaPecAssociata()) {
                logger.error("Pec non associata con errore");
                Assert.fail("Pec non associata con errore");
            }
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

        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadITuoiRecapitiPage();
        Map<String, Object> personaFisica = dataPopulation.readDataPopulation("personaFisica.yaml");
        String email = personaFisica.get("email").toString();

        BackgroundTest backgroundTest = new BackgroundTest();

        if (!recapitiDestinatarioPage.verificaMailAssociata()) {
            backgroundTest.aggiuntaEmailPF();
        } else if (recapitiDestinatarioPage.controlloEmailAssociata(email)) {
            iTuoiRecapitiPage.eliminaEmailEsistente();
            if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi e-mail")) {
                recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
            } else {
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
    public void nellaPaginaITuoiRecapitiSiCliccaSulBottoneElimina() {
        logger.info("Si clicca sul bottone elimina email");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);

        iTuoiRecapitiPage.eliminaEmailEsistente();
        if (recapitiDestinatarioPage.waitLoadPopUpElimina().equalsIgnoreCase("Rimuovi e-mail")) {
            recapitiDestinatarioPage.confermaButtonEliminaClick();
        } else {
            recapitiDestinatarioPage.clickSuChiudiPopUp();
            recapitiDestinatarioPage.eliminaNuovaPec();
            recapitiDestinatarioPage.waitLoadPopUpElimina();
            recapitiDestinatarioPage.clickConfermaButtonEliminaPopUp();
        }
    }

    @Then("Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non sia presente")
    public void nellaPaginaITuoiRecapitiSiControllaCheLIndirizzoEmailNonSiaPresente() {
        logger.info("Si controlla che l'indirizzo Email non sia presente");
        if (!recapitiDestinatarioPage.verificaMailAssociata()) {
            logger.error("Email non è stata eliminata correttamente");
            Assert.fail("Email non è stata eliminata correttamente");
        }
    }

    @And("Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti")
    public void nellaPaginaITuoiRecapitiSiVisualizzaCorrettamenteLaSezioneAltriRecapiti() {
        logger.info("Si controlla che l'indirizzo Email non sia presente");

        recapitiDestinatarioPage.visualizzazioneSezioneAltriRecapiti();

    }

    @And("Nella pagina I Tuoi Recapiti di PF, si controlla che ci sia già una pec")
    public void nellaPaginaITuoiRecapitiDiPfSiControllaCheCiSiaGiaUnaPec() {
        logger.info("Si controlla la presenza di una pec");
        String pec = dataPopulation.readDataPopulation("personaFisica.yaml").get("emailPec").toString();
        BackgroundTest backgroundTest = new BackgroundTest();
        if (!recapitiDestinatarioPage.siVisualizzaPecInserita()) {
            backgroundTest.aggiungiPECPF();
        } else if (!recapitiDestinatarioPage.siControllaPECModificata(pec)) {
            recapitiDestinatarioPage.eliminaPecEsistente();
            backgroundTest.aggiungiPECPF();
        }
    }

    @And("Nella pagina I Tuoi Recapiti di PG, si controlla che ci sia già una pec")
    public void nellaPaginaITuoiRecapitiDiPgSiControllaCheCiSiaGiaUnaPec() {
        logger.info("Si controlla la presenza di una pec");
        String pec = dataPopulation.readDataPopulation("personaFisica.yaml").get("emailPec").toString();
        BackgroundTest backgroundTest = new BackgroundTest();
        if (!recapitiDestinatarioPage.siVisualizzaPecInserita()) {
            backgroundTest.aggiungiPECPG();
        } else if (!recapitiDestinatarioPage.siControllaPECModificata(pec)) {
            recapitiDestinatarioPage.eliminaPecEsistente();
            backgroundTest.aggiungiPECPG();
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
            DataPopulation.waitTime(5);
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
    public void     nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaPECTramiteChiamataRequest(String dpFile) {
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

    @And("Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC tramite chiamata request")
    public void nellaPaginaITuoiRecapitiSiRecuperaIlCodiceOTPDellaNuovaPECTramiteChiamataRequest() {
        logger.info("Si recupera il codice OTP della nuova pec");

        String pec = "prova@pec.it";
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
            url = startUrl + recuperoOTPRecapiti.getUrlEndPoint() + "prova@pec.it";
            results = recuperoOTPRecapiti.runRecuperoOTPRecapiti(url);
            if (results) {
                OTP = recuperoOTPRecapiti.getResponseBody();
            } else {
                logger.error("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
                Assert.fail("La chiamata ha risposto con questo codice: " + recuperoOTPRecapiti.getResponseCode());
            }
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

        if (!recapitiDestinatarioPage.siControllaPresenzaPEC()) {
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
            recapitiDestinatarioPage.aggionamentoPagina();
            recapitiDestinatarioPage.waitLoadPage();
        }
        String pec = dataPopulation.readDataPopulation("personaFisica.yaml").get("additionalEmail").toString();
        DataPopulation.waitTime(10);
        driver.navigate().refresh();
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
        if (recapitiDestinatarioPage.verificaPecAssociata()) {
            recapitiDestinatarioPage.eliminaPecEsistente();
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

    @Then("Si visualizzano correttamente tutti gli elementi della sezione altri recapiti")
    public void siVisualizzanoCorrettamenteTuttiGliElementiDellaSezioneAltriRecapiti() {
        logger.info("Si controlla che si visualizzano correttamente tutti gli elementi della sezione recapiti gia associati");
        DataPopulation.waitTime(20);
        this.driver.navigate().refresh();
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.waitLoadRecapitiGiaAssociatoSection();
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
        logger.info("Si visualizza correttamente il riquadro relativo alla PEC");
        ITuoiRecapitiPage iTuoiRecapitiPage = new ITuoiRecapitiPage(this.driver);
        iTuoiRecapitiPage.checkRiquadroPEC();
    }
}

