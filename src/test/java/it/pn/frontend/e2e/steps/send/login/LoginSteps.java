package it.pn.frontend.e2e.steps.send.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.pn.frontend.e2e.enums.User;
import it.pn.frontend.e2e.steps.send.login.page.DashboardPartySelectionPage;
import it.pn.frontend.e2e.steps.send.login.page.OneIdPage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class LoginSteps {

    private final WebPresentationGateway uiGateway;
    private User user;

    @Value("${url.selfcare.notifiche.base}")
    private String slefcareUrl;

    @Given("l'utente {user} effettua l'accesso a SelfCare con autenticazione SPID")
    public void spidAuth(User user) {
        // Setto l'utente corrente originario dallo step per poterlo utilizzare nei passaggi successivi
        this.user = user;

        // L'utente digita l'url di selfcare nel browser e preme invio
        uiGateway.navigateTo(WebLocation.of(slefcareUrl));

        // Viene effettuato il binding "logico" tra la pagina attuale caricata nel browser
        OneIdPage oneId = uiGateway.bind(OneIdPage.class);
        oneId.loginWithSpid(user);
    }

    @When("l'utente accede alla dashboard selezionando {string}")
    public void selectPa(String comune) {
        DashboardPartySelectionPage partyPage = uiGateway.bind(DashboardPartySelectionPage.class);
        partyPage.selectComune(comune);
    }
}
