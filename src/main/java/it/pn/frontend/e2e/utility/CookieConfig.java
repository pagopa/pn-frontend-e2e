package it.pn.frontend.e2e.utility;

import it.pn.frontend.e2e.config.WebDriverConfig;
import lombok.Getter;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CookieConfig {
    private static final Logger logger = LoggerFactory.getLogger("CookieConfig");

    private final Map<String, Set<Cookie>> cookieMap;
    @Value("${environment}")
    private String environment;

    @Value("${cookie.config}")
    private String cookie;

    @Getter
    private static final ThreadLocal<Set<Cookie>> threadLocalCookies = ThreadLocal.withInitial(HashSet::new);


    @Autowired
    public CookieConfig() {
        this.cookieMap = new HashMap<>();
    }



    public boolean isCookieEnabled() {
        logger.info("COOOKIE....: "+ cookie);
        logger.info("COOOKIE....ENVIROMENT: "+ environment);
        String isCookieEnabled = cookie;
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
            String env = environment;
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
           // threadLocalCookies.set(cookieMap.get(url));
            return cookieMap.get(url);
        }
        return new HashSet<>();
    }
}
