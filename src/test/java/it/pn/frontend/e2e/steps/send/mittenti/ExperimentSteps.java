package it.pn.frontend.e2e.steps.send.mittenti;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.frontend.e2e.framework.web.WebPresentationGateway;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.steps.common.ListPage;
import it.pn.frontend.e2e.steps.send.login.component.OneTrustBanner;
import it.pn.frontend.e2e.steps.send.mittenti.pages.Dashboard;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationDetails;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExperimentSteps {

    private final WebPresentationGateway browser;
    private Page currentPage;

    @When("seleziona la prima notifica dalla Dashboard")
    public void navigateToDashboard() {
        Dashboard dashboardPage = browser.bind(Dashboard.class);
        currentPage = dashboardPage;
        dashboardPage.navigateTo();

        try {
            OneTrustBanner banner = browser.bind(OneTrustBanner.class);
            banner.accept();
        } catch (Exception e) {
            // banner non presente → ok
        }

        ((ListPage)dashboardPage).openFirstItem();

        NotificationDetails noticeDetailsPage = browser.bind(NotificationDetails.class);
        currentPage = noticeDetailsPage;
    }

    @Then("la pagina della notifica deve caricarsi correttamente")
    public void showSelectedNotification() {
       currentPage.assertLoaded();
    }

}
