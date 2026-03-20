package it.pn.frontend.e2e.parameter_type;

import io.cucumber.java.ParameterType;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.steps.send.login.page.OneIdPage;
import it.pn.frontend.e2e.steps.send.mittenti.page.NotificationCreate;
import it.pn.frontend.e2e.steps.send.mittenti.page.APIKey;
import it.pn.frontend.e2e.steps.send.mittenti.page.APIKeyDetails;
import it.pn.frontend.e2e.steps.send.mittenti.page.Dashboard;
import it.pn.frontend.e2e.steps.send.mittenti.page.NewAPIKey;
import it.pn.frontend.e2e.steps.send.mittenti.page.NotificationDetails;
import it.pn.frontend.e2e.steps.send.mittenti.page.PlatformStatus;
import it.pn.frontend.e2e.steps.send.mittenti.page.Statistics;

public class PageType {

    @ParameterType("LoginPage|Dashboard|APIKey|NewAPIKey|Statistics|PlatformStatus|NotificationDetails|APIKeyDetails|NotificationCreate")
    public Class<? extends Page> page(String page) {
        return switch (page) {
            case "LoginPage" -> OneIdPage.class;
            case "Dashboard" -> Dashboard.class;
            case "APIKey" -> APIKey.class;
            case "NewAPIKey" -> NewAPIKey.class;
            case "Statistics" -> Statistics.class;
            case "PlatformStatus" -> PlatformStatus.class;
            case "NotificationDetails" -> NotificationDetails.class;
            case "APIKeyDetails" -> APIKeyDetails.class;
            case "NotificationCreate" -> NotificationCreate.class;
            default -> throw new IllegalArgumentException("Invalid page");
        };
    }
}
