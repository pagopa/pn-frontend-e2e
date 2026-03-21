package it.pn.frontend.e2e.parameter_type;

import io.cucumber.java.ParameterType;
import it.frontend.e2e.framework.web.domain.Page;
import it.pn.frontend.e2e.steps.send.login.page.OneIdPage;
import it.pn.frontend.e2e.steps.send.mittenti.pages.APIKey;
import it.pn.frontend.e2e.steps.send.mittenti.pages.APIKeyDetails;
import it.pn.frontend.e2e.steps.send.mittenti.pages.Dashboard;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NewAPIKey;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationCreate;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationDebtPosition;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationDetails;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationDocumentation;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationRecipients;
import it.pn.frontend.e2e.steps.send.mittenti.pages.NotificationSuccess;
import it.pn.frontend.e2e.steps.send.mittenti.pages.PlatformStatus;
import it.pn.frontend.e2e.steps.send.mittenti.pages.Statistics;

public class PageType {

    @ParameterType(
        "LoginPage|Dashboard|APIKey"            + "|" +
        "NewAPIKey|Statistics|PlatformStatus"   + "|" +
        "NotificationDetail|APIKeyDetails"      + "|" +
        "NotificationCreate"           + "|" +
        "NotificationRecipients"       + "|" +
        "NotificationDebtPosition"     + "|" +
        "NotificationDocumentation"    + "|" +
        "NotificationSuccess"
)    public Class<? extends Page> page(String page) {
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
            case "NotificationDebtPosition" -> NotificationDebtPosition.class;
            case "NotificationDocumentation" -> NotificationDocumentation.class;
            case "NotificationRecipients" -> NotificationRecipients.class;
            case "NotificationSuccess" -> NotificationSuccess.class;
            default -> throw new IllegalArgumentException("Invalid page");
        };
    }
}
