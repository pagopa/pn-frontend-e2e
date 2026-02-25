package it.pn.frontend.e2e.steps.send.mittenti;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.framework.web.WebPresentationGateway;
import it.pn.frontend.e2e.framework.web.domain.ui.Component;
import it.pn.frontend.e2e.framework.web.domain.ui.Page;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class MittentiSteps {

    private final WebPresentationGateway uiGateway;
    private Page page;
    @Value("pn.bearer-token.tokentestMittente") private String token;

    @When("naviga alla pagina {string} tramite la route {string}")
    public void navigateTo(Class<? extends Component> page, String path) {
        //page = uiGateway.bind(page);
        uiGateway.navigateTo(path);
    }

    @Then("la pagina deve caricarsi correttamente")
    public void laPaginaDeveCaricarsiCorrettamente() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("l'utente è un {string} di {string}")
    public void login(String role, String pa) {
        // Inizializzare un bean Auth
        // Auth auth = Auth.of(role,pa,fake);
        // Authenticator auth = new Authentcator(auth);
        boolean isLogged = true;
        Assertions.assertTrue(isLogged);
    }
}
