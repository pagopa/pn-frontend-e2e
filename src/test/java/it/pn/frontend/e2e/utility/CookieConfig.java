package it.pn.frontend.e2e.utility;

import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CookieConfig {
    private static final Logger logger = LoggerFactory.getLogger("CookieConfig");

    private static final String env = System.getProperty("environment");
    private final Map<String, Set<Cookie>> cookieMap;

    public CookieConfig() {
        this.cookieMap = new HashMap<>();
    }

    private boolean isCookieEnabled() {
        String isCookieEnabled = System.getProperty("cookie.enabled");
        if (isCookieEnabled == null) {
            logger.info("Cookie are disabled");
            return false;
        }
        logger.info("Cookie are enabled");
        return true;
    }

    public void addCookie() {
        // Check if the cookie property is enabled
        if (isCookieEnabled()) {
            // Based on the environment, the cookie is added to the map
            switch (env) {
                case "test":
                    this.setUpCookieTest();
                    break;
                case "dev":
                    this.setUpCookieDev();
                    break;
                default:
                    logger.error("No environment found!");
                    break;
            }
            logger.info("Cookies added!");
        }
    }

    // Set up cookie for dev environment
    private void setUpCookieDev() {
        Date currentDate = new Date();
        String optanonConsentValue = "isGpcEnabled=0&datestamp=Tue+Dec+19+2023+10%3A24%3A38+GMT%2B0100+(Ora+standard+dell%E2%80%99Europa+centrale)&version=202303.2.0&browserGpcFlag=0&isIABGlobal=false&hosts=&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A0";
        String OptanonAlertBoxClosedValue = currentDate.toString();
        cookieMap.put("https://selfcare.dev.notifichedigitali.it/", new HashSet<>(Arrays.asList(
                new Cookie("OptanonAlertBoxClosed", OptanonAlertBoxClosedValue, "selfcare.dev.notifichedigitali.it", "/", null),
                new Cookie("OptanonConsent", optanonConsentValue, "selfcare.dev.notifichedigitali.it", "/", null)
        )));
    }

    // Set up cookie for test environment
    private void setUpCookieTest() {
        Date currentDate = new Date();
        String optanonConsentValue = "isGpcEnabled=0&datestamp=Wed+Dec+20+2023+14%3A17%3A59+GMT%2B0100+(Ora+standard+dell%E2%80%99Europa+centrale)&version=202303.2.0&browserGpcFlag=0&isIABGlobal=false&hosts=&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A0";
        String OptanonAlertBoxClosedValue = currentDate.toString();
        cookieMap.put("https://selfcare.test.notifichedigitali.it/", new HashSet<>(Arrays.asList(
                new Cookie("OptanonAlertBoxClosed", OptanonAlertBoxClosedValue, "selfcare.test.notifichedigitali.it", "/", null),
                new Cookie("OptanonConsent", optanonConsentValue, "selfcare.test.notifichedigitali.it", "/", null)
        )));
    }

    public Set<Cookie> getCookies(String url) {
        if (cookieMap.get(url) != null) {
            return cookieMap.get(url);
        }
        return new HashSet<>();
    }
}
