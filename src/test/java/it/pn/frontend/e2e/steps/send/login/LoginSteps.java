package it.pn.frontend.e2e.steps.send.login;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.pn.frontend.e2e.enums.User;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.steps.send.login.page.DashboardPartySelectionPage;
import it.pn.frontend.e2e.steps.send.login.page.OneIdPage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class LoginSteps {

    private final WebPresentationGateway uiGateway;
    private User user;

    @Value("${url.test.selfcare.notifiche}")
    private String slefcareUrl;

    @Given("l'utente {user} effettua l'accesso a SelfCare con autenticazione SPID")
    public void spidAuth(User user) {
        this.user = user;
        uiGateway.navigateTo(WebLocation.of(slefcareUrl));

        OneIdPage oneId = uiGateway.bind(OneIdPage.class);
        oneId.loginWithSpid(user);
    }

    @When("l'utente accede alla dashboard selezionado {string}")
    public void selectPa(String comune) {
        DashboardPartySelectionPage partyPage = uiGateway.bind(DashboardPartySelectionPage.class);
        partyPage.selectComune(comune);
    }
}
