package it.pn.frontend.e2e.utility;

import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class CookieConfig {
    private static final Logger logger = LoggerFactory.getLogger("CookieConfig");

    private static final String env = System.getProperty("environment");
    private final Map<String, Set<Cookie>> cookieMap;

    public CookieConfig() {
        this.cookieMap = new HashMap<>();
    }

    public static boolean isCookieEnabled() {
        String isCookieEnabled = System.getProperty("cookie.config");
        if (isCookieEnabled == null || isCookieEnabled.equals("false")) {
            logger.info("Cookies are disabled");
            return false;
        }
        logger.info("Cookies are enabled");
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

        // Cookie values
        String optanonConsentValue = "isGpcEnabled=0&datestamp=" + this.getCurrentDateFormatted() + "+GMT%2B0100+(Ora+standard+dell%E2%80%99Europa+centrale)&version=202303.2.0&browserGpcFlag=0&isIABGlobal=false&hosts=&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A0";
        String OptanonAlertBoxClosedValue = currentDate.toString();
        cookieMap.put("https://selfcare.dev.notifichedigitali.it/", new HashSet<>(Arrays.asList(
                new Cookie("OptanonAlertBoxClosed", OptanonAlertBoxClosedValue, "selfcare.dev.notifichedigitali.it", "/", null, false),
                new Cookie("OptanonConsent", optanonConsentValue, "selfcare.dev.notifichedigitali.it", "/", null, false)
        )));
        cookieMap.put("https://imprese.dev.notifichedigitali.it/", new HashSet<>(Arrays.asList(
                new Cookie("OptanonAlertBoxClosed", OptanonAlertBoxClosedValue, "imprese.dev.notifichedigitali.it", "/", null, false),
                new Cookie("OptanonConsent", optanonConsentValue, "imprese.dev.notifichedigitali.it", "/", null, false)
        )));
        cookieMap.put("https://cittadini.dev.notifichedigitali.it/", new HashSet<>(Arrays.asList(
                new Cookie("OptanonAlertBoxClosed", OptanonAlertBoxClosedValue, "cittadini.dev.notifichedigitali.it", "/", null, false),
                new Cookie("OptanonConsent", optanonConsentValue, "cittadini.dev.notifichedigitali.it", "/", null, false)
        )));
    }

    // Set up cookie for test environment
    private void setUpCookieTest() {
        Date currentDate = new Date();

        // Cookie values
        String optanonConsentValue = "isGpcEnabled=0&datestamp=" + this.getCurrentDateFormatted() + "+GMT%2B0100+(Ora+standard+dell%E2%80%99Europa+centrale)&version=202303.2.0&browserGpcFlag=0&isIABGlobal=false&hosts=&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A0";
        String OptanonAlertBoxClosedValue = currentDate.toString();
        cookieMap.put("https://selfcare.test.notifichedigitali.it/", new HashSet<>(Arrays.asList(
                new Cookie("OptanonAlertBoxClosed", OptanonAlertBoxClosedValue, "selfcare.test.notifichedigitali.it", "/", null, false),
                new Cookie("OptanonConsent", optanonConsentValue, "selfcare.test.notifichedigitali.it", "/", null, false)
        )));
        cookieMap.put("https://imprese.test.notifichedigitali.it/", new HashSet<>(Arrays.asList(
                new Cookie("OptanonAlertBoxClosed", OptanonAlertBoxClosedValue, "imprese.test.notifichedigitali.it", "/", null, false),
                new Cookie("OptanonConsent", optanonConsentValue, "imprese.test.notifichedigitali.it", "/", null, false)
        )));
        cookieMap.put("https://cittadini.test.notifichedigitali.it/", new HashSet<>(Arrays.asList(
                new Cookie("OptanonAlertBoxClosed", OptanonAlertBoxClosedValue, "cittadini.test.notifichedigitali.it", "/", null, false),
                new Cookie("OptanonConsent", optanonConsentValue, "cittadini.test.notifichedigitali.it", "/", null, false)
        )));
    }

    private String getCurrentDateFormatted() {
        Date currentDate = new Date();

        // Format the current date in the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
        String formattedDate = dateFormat.format(currentDate);

        // Remove spaces and replace them with "+"
        formattedDate = formattedDate.replace(" ", "+");
        formattedDate = formattedDate.replace(":", "%3A");

        return formattedDate;
    }

    public Set<Cookie> getCookies(String url) {
        if (cookieMap.get(url) != null) {
            return cookieMap.get(url);
        }
        return new HashSet<>();
    }
}
