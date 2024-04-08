package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.AccediAPiattaformaNotifichePage;
import it.pn.frontend.e2e.pages.mittente.DisserviziAppPAPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisserviziAppPATest {
    private static final Logger logger = LoggerFactory.getLogger("DisserviziAppPATest");
    private final WebDriver driver = Hooks.driver;
    private final AccediAPiattaformaNotifichePage notifichePage = new AccediAPiattaformaNotifichePage(this.driver);
    private final DisserviziAppPAPage disserviziAppPAPage = new DisserviziAppPAPage(this.driver);

    @When("Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'")
    public void nellaPaginaPiattaformaNotificheSelezionareLaVoceStatoDellaPiattaforma() {
        logger.info("Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'");
        notifichePage.clickStatoDellaPiattaforma();
    }

    @Then("Si visualizza correttamente la pagina dello 'stato della piattaforma' di mittente")
    public void siVisualizzaCorrettamenteLaPaginaDelloStatoDellaPiattaformaDiMittente() {
        logger.info("Si visualizza correttamente la Pagina dello Stato della piattaforma di mittente");
        disserviziAppPAPage.waitLoadStatoDellaPiattaformaPage();
    }

    @And("Si visualizza correttamente la tabella dei disservizi")
    public void siVisualizzaCorrettamenteLaTabellaDeiDisservizi() {
        logger.info("Si visualizza correttamente la tabella dei disservizi");
        disserviziAppPAPage.waitLoadDisserviziTable();
    }

    @And("Si visualizza un record in elenco relativo ad un disservizio ancora in corso")
    public void siVisualizzaUnRecordInElencoRelativoAdUnDisservizioAncoraInCorso() {
        logger.info("Si visualizza un record in elenco relativo ad un disservizio ancora in corso");
        disserviziAppPAPage.checkDisserviziInCorso();
    }

    @And("Si visualizza un record in elenco relativo ad un disservizio risolto")
    public void siVisualizzaUnRecordInElencoRelativoAdUnDisservizioRisolto() {
        logger.info("Si visualizza un record in elenco relativo ad un disservizio risolto");
        disserviziAppPAPage.checkDisservizioRisolto();
    }

    @And("Si visualizzano tutti i record in elenco relativi a disservizi risolti")
    public void siVisualizzanoTuttiIRecordInElencoRelativiADisserviziRisolti() {
        logger.info("Si visualizzano tutti i record in elenco relativi a disservizi risolti");
        disserviziAppPAPage.checkDisserviziDisponibili();
    }
}
