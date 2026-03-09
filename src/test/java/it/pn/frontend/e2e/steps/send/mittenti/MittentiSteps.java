package it.pn.frontend.e2e.steps.send.mittenti;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.steps.send.FakeAuthenticator;
import it.pn.frontend.e2e.steps.send.IAuthenticator;
import it.pn.frontend.e2e.steps.send.login.component.OneTrustBanner;
import it.pn.frontend.e2e.steps.send.login.page.NuovaNotificaPage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;

@RequiredArgsConstructor
public class MittentiSteps {

    private final WebPresentationGateway uiGateway;
    private PageInfo currentPageInfo;

    @Given("l'utente è un {string} di {string}")
    public void login(String role, String pa) {
        // Inizializzare un bean Auth
        //Auth auth = Auth.of(role,pa,fake);
        IAuthenticator auth = new FakeAuthenticator(role, pa);
        Assertions.assertTrue(auth.isAuthenticated());
    }

    @When("naviga alla pagina {page}")
    public void navigateTo(PageInfo pageInfo) {
        this.currentPageInfo = pageInfo;
        uiGateway.navigateTo(pageInfo.location());
        OneTrustBanner banner = uiGateway.bind(OneTrustBanner.class);
        banner.accept();
    }

    @Then("la pagina deve caricarsi correttamente")
    public void laPaginaDeveCaricarsiCorrettamente() {
       Page page = (Page) uiGateway.bind(currentPageInfo.pageClass());
       page.assertLoaded();
    }

    @And("l'utente va indietro")
    public void lUtenteVaIndietro() {
        uiGateway.bind(NuovaNotificaPage.class).back();

        uiGateway.waitForAlert(5).ifPresent(text -> {
            System.out.println("Alert rilevato: " + text);
            uiGateway.acceptAlert();
        });
    }
}
