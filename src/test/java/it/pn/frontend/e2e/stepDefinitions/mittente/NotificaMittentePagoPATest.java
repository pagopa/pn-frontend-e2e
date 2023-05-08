package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.AreaRiservataPAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePAPage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificaMittentePagoPATest {

    private static final Logger logger = LoggerFactory.getLogger("NotificaMittenteTest");

    private final WebDriver driver = Hooks.driver;

    @When("Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV")
    public void nellaHomePageMittenteCliccareSuGestisciDiPiattaformaDEV(){
        logger.info("Cliccare sul bottone Gestisci di Piattaforma Notifiche DEV");

        AreaRiservataPAPage areaRiservataPAPage = new AreaRiservataPAPage(this.driver);
        areaRiservataPAPage.selezionaPiattaformaNotificaDev();
    }

    @And("Si visualizza correttamente la pagina Piattaforma Notifiche")
    public void siVisualizzaCorrettamenteLaPaginaPiattaformaNotifiche() {
        HeaderPASection headerPASection = new HeaderPASection(this.driver);
        headerPASection.waitLoadHeaderSection();

        CookiesSection cookiesSection = new CookiesSection(this.driver);
        cookiesSection.waitLoadCookiesPage();
        cookiesSection.selezionaAccettaTuttiButton();

        PiattaformaNotifichePAPage piattaformaNotifichePAPage = new PiattaformaNotifichePAPage(this.driver);
        piattaformaNotifichePAPage.waitLoadPiattaformaNotifichePAPage();
    }
}
