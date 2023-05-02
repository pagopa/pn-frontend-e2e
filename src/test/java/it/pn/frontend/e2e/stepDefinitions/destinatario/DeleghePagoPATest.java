package it.pn.frontend.e2e.stepDefinitions.destinatario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.DeleghePage;
import it.pn.frontend.e2e.pages.destinatario.NotificheDEPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleghePagoPATest {

    private final WebDriver driver = Hooks.driver;
    private final Logger logger = LoggerFactory.getLogger("AggiuntaNuovaDelegaPagoPA");

    @When("Nella pagina Piattaforma Notifiche Destinatario click sul bottone Deleghe")
    public void wait_deleghe_Button() {
        logger.info("Si clicca sul bottone deleghe");
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.waitESelectDelegheButton();
    }

    @And("Nella pagina Piattaforma Notifiche Destinatario si vede la sezione Deleghe")
    public void visualizzaDelegheSection() {
        logger.info("Si visualizza la sezione deleghe");
        DeleghePage deleghePage = new DeleghePage(this.driver);
        deleghePage.waitDeleghePage();
    }

    @And("Nella sezione Deleghe click sul bottone aggiungi nuova delega")
    public void nellaSezioneDelegheClickSulBottoneAggiungiNuovaDelega() {
        logger.info("Click sul bottone aggiungi nuova delega");
        DeleghePage deleghePage = new DeleghePage(this.driver);
        deleghePage.clickAggiungiDelegaButton();
    }

    @And("Si visualizza la sezione Le Tue Deleghe")
    public void siVisualizzaLaSezioneLeTueDeleghe() {
        logger.info("Si visualizza la sezione Le Tue Deleghe");
    }

    @And("Nella sezione Le Tue Deleghe inserire i dati {string}")
    public void nellaSezioneLeTueDelegheInserireIDati(String arg0) {
    }

    @And("Nella sezione Le Tue Deleghe verificare che la data sia corretta")
    public void nellaSezioneLeTueDelegheVerificareCheLaDataSiaCorretta() {
    }

    @And("Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file {string}")
    public void nellaSezioneLeTueDelegheSalvareIlCodiceVerificaAllInternoDelFile(String arg0) {
    }

    @And("Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe")
    public void nellaSezioneLeTueDelegheClickSulBottoneInviaRichiestaESulBottoneTornaAlleDeleghe() {
    }

    @And("Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma")
    public void nellaSezioneDelegheSiVisualizzaLaDelegaInStatoDiAttesaDiConferma() {
    }
}
