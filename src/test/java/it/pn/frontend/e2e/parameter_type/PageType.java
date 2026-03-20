package it.pn.frontend.e2e.parameter_type;

import io.cucumber.java.ParameterType;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.steps.send.login.page.OneIdPage;
import it.pn.frontend.e2e.steps.send.mittenti.CreateNotification;
import it.pn.frontend.e2e.steps.send.mittenti.Dashboard;
import it.pn.frontend.e2e.steps.send.mittenti.NewAPIKey;
import it.pn.frontend.e2e.steps.send.mittenti.NotificationDetails;
import it.pn.frontend.e2e.steps.send.mittenti.PlatformStatus;
import it.pn.frontend.e2e.steps.send.mittenti.Statistics;
import it.pn.frontend.e2e.steps.send.mittenti.APIKey;

public class PageType {

    @ParameterType("LoginPage|Dashboard|APIKey|NewAPIKey|Statistics|PlatformStatus|NotificationDetails|CreateNotification")
    public Class<? extends Page> page(String page) {
        return switch (page) {
            case "LoginPage" -> OneIdPage.class;
            case "Dashboard" -> Dashboard.class;
            case "NotificationDetails" -> NotificationDetails.class;
            case "CreateNotification" -> CreateNotification.class;
            case "APIKey" -> APIKey.class;
            case "NewAPIKey" -> NewAPIKey.class;
            case "Statistics" -> Statistics.class;
            case "PlatformStatus" -> PlatformStatus.class;
            default -> throw new IllegalArgumentException("Invalid page");
        };
    }
}
