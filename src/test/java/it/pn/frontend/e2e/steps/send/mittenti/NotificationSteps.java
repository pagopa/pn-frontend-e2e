package it.pn.frontend.e2e.steps.send.mittenti;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.config.ScenarioContext;
import it.pn.frontend.e2e.steps.common.ListPage;
import it.pn.frontend.e2e.steps.send.FakeAuthenticator;
import it.pn.frontend.e2e.steps.send.IAuthenticator;
import it.pn.frontend.e2e.steps.send.login.component.OneTrustBanner;
import it.pn.frontend.e2e.steps.send.mittenti.page.NotificationCreate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;

@RequiredArgsConstructor
public class NotificationSteps {

    private final WebPresentationGateway browser;
    private Page currentPage;
    private final ScenarioContext scenarioContext;

    @When("compila il form con i dati della notifica")
    public void fillNotificationForm() {
        NotificationCreate page = scenarioContext.getObject("currentPage", NotificationCreate.class);
        currentPage = page;

        // Store group name in ScenarioContext so ${group.name} is resolved
        scenarioContext.set("group.name", "test-TA-FE-TEST");


        page.fillAndSubmit(
            "Test notification subject",
            "PROT-2026-001",
            "010101P",
            "test-TA-FE-TEST"
        );
    }
}
