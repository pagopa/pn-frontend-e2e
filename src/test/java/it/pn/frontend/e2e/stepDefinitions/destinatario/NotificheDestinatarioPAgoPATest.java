package it.pn.frontend.e2e.stepDefinitions.destinatario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.destinatario.NotificheDEPage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.HeaderDESection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class NotificheDestinatarioPAgoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotificheDestinatarioTest");

    private final WebDriver driver = Hooks.driver;

    @When("Nella pagina Piattaforma Notifiche Destinatario si clicca sul bottone Notifiche")
    public void nella_piattaforma_destinatario_cliccare_sul_bottone_notifiche() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.clickNotificheButton();
    }

    @Then("pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente")
    public void paginaPiattaformaNotificheDestinatarioVieneVisualizzataCorrettamente() {
            HeaderDESection headerDESection = new HeaderDESection(this.driver);
            headerDESection.waitLoadHeaderDESection();

            CookiesSection cookiesSection = new CookiesSection(this.driver);
            if (cookiesSection.waitLoadCookiesPage()){
                cookiesSection.selezionaAccettaTuttiButton();
            }

            NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
            notificheDEPage.waitLoadNotificheDEPage();
            if (notificheDEPage.verificaPresenzaCodiceIumTextField()) {
                logger.info("text field codice ium presente");
            } else {
                logger.info("text field codice ium non presente");
                Assert.fail("text field codice ium non presente");
            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    @And("Si visualizza correttamente la Pagina Notifiche Destinatario")
    public void siVisualizzaCorrettamenteLaPaginaNotificheDestinatario() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siVisualizzaPaginaNotificheDestinatario();
    }

    @And("Nella Pagina Notifiche Destinatario si visualizzano correttamente i filtri di ricerca")
    public void nellaPaginaNotificheDestinatarioSiVisualizzanoCorrettamenteIFiltriDiRicerca() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siVisualizzanoFiltriRicerca();
    }

    @And("Nella Pagina Notifiche Destinatario si visualizza correttamente l elenco delle notifiche")
    public void nellaPaginaNotificheDestinatarioSiVisualizzaCorrettamenteLElencoDelleNotifiche() {
        NotificheDEPage notificheDEPage = new NotificheDEPage(this.driver);
        notificheDEPage.siVisualizzaElencoNotifiche();
    }
}



