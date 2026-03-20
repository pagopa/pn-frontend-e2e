package it.pn.frontend.e2e.steps.send.mittenti;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.steps.common.ListPage;
import it.pn.frontend.e2e.steps.send.FakeAuthenticator;
import it.pn.frontend.e2e.steps.send.IAuthenticator;
import it.pn.frontend.e2e.steps.send.login.component.OneTrustBanner;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;

@RequiredArgsConstructor
public class MittentiSteps {

    private final WebPresentationGateway browser;
    private Page currentPage;

    @Given("l'utente è un {string} di {string}")
    public void login(String role, String pa) {
        //Inizializzare un bean Auth
        //Auth auth = Auth.of(role,pa,fake);
        IAuthenticator auth = new FakeAuthenticator(role, pa);
        Assertions.assertTrue(auth.isAuthenticated());
    }

    @When("naviga alla pagina {page}")
    public void navigateTo(Class<? extends Page> page) {
        currentPage = browser.bind(page);
        currentPage.navigateTo();

        try {
            OneTrustBanner banner = browser.bind(OneTrustBanner.class);
            banner.accept();
        } catch (Exception e) {
            // banner non presente → ok
        }
    }

    @When("naviga alla pagina {page} e va alla pagina {page} di dettaglio della prima istanza")
    public void navigateToDetails(Class<? extends Page> listPage, Class<? extends Page> detailsPage) {
        Page page = browser.bind(listPage);
        page.navigateTo();

        try {
            OneTrustBanner banner = browser.bind(OneTrustBanner.class);
            banner.accept();
        } catch (Exception e) {
            // banner non presente → ok
        }

        ((ListPage) page).openFirstItem();

        Page details = browser.bind(detailsPage);
        currentPage = details;
    }

    @Then("la pagina deve caricarsi correttamente")
    public void laPaginaDeveCaricarsiCorrettamente() {
       currentPage.assertLoaded();
    }

}
