package it.pn.frontend.e2e.utility;

import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.model.enums.AppPortal;
import it.pn.frontend.e2e.pages.destinatario.personaFisica.NotifichePFPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.destinatario.personaFisica.HeaderPFSection;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.HeaderPGSection;
import it.pn.frontend.e2e.section.mittente.HeaderPASection;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WebTool {
    private static final Logger logger = LoggerFactory.getLogger("WebTool");
    private static final WebDriver driver = Hooks.driver;
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private final String env = System.getProperty("environment");

    public static String switchToPortal(AppPortal portal) {
        openNewTab();
        switch (portal) {
            case PA -> {
                driver.get(portal.url);
                HeaderPASection headerPASection = new HeaderPASection(driver);
                headerPASection.waitLoadHeaderSection();
                PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(driver);
                piattaformaNotifichePage.waitLoadPiattaformaNotifichePAPage();
            }
            case PF -> {
                driver.get(portal.url);
                HeaderPFSection headerPFSection = new HeaderPFSection(driver);
                headerPFSection.waitLoadHeaderDESection();
                NotifichePFPage notifichePFPage = new NotifichePFPage(driver);
                notifichePFPage.waitLoadNotificheDEPage();
            }
            case PG -> {
                driver.get(portal.url);
                HeaderPGSection headerPGSection = new HeaderPGSection(driver);
                headerPGSection.waitLoadHeaderPGPage();
            }
            case HELPDESK -> {
                driver.get(portal.url);
            }
            default -> {
                logger.error("Tipologia di portale non specificato o errato!");
                Assert.fail("Tipologia di portale non specificato o errato!");
            }
        }
        CookiesSection cookiesPage = new CookiesSection(driver);
        if (cookiesPage.waitLoadCookiesPage()) {
            cookiesPage.selezionaAccettaTuttiButton();
        }
        return driver.getWindowHandle();
    }

    public static void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        String newTab = driver.getWindowHandles().stream().reduce((first, second) -> second).orElse(null);
        driver.switchTo().window(newTab);
    }

    public static void closeTab() {
        driver.close();
        String newTab = driver.getWindowHandles().stream().reduce((first, second) -> second).orElse(null);
        driver.switchTo().window(newTab);
    }

    /**
     * Generate a random protocol number
     *
     * @return a random protocol number as a String
     */
    public static String generatePaProtocolNumber() {
        // Get the current date in the format "yyyyMMdd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(new Date());

        // Generate a random number between 0 and 100
        Random random = new Random();
        String randomNumber = String.valueOf(random.nextInt(10000));

        // Concatenate the current date and the random number
        String protocolNumber = "TA-FFSMRC-" + currentDate + "-" + randomNumber;
        logger.info("Protocol number generated: " + protocolNumber);

        return protocolNumber;
    }

    /**
     * Decode the NotificationRequestId from the response of the newNotification API
     * The NotificationRequestId is encoded in base64
     * The decoded NotificationRequestId is a String IUN
     *
     * @param NotificationRequestId the requestID to decode
     * @return String IUN
     */
    public static String decodeNotificationRequestId(String NotificationRequestId) {
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(NotificationRequestId);
        return new String(decodedBytes);
    }

    /**
     * Wait for the specified number of seconds
     *
     * @param seconds the number of seconds to wait
     */
    public static void waitTime(int seconds) {
        try {
            int minutes;
            int remainingSeconds;
            if (seconds >= 60) {
                minutes = seconds / 60;
                remainingSeconds = seconds % 60;
                logger.info("Si aspettano " + minutes + " minuto/i e " + remainingSeconds + " secondi... prima di proseguire oltre");
            } else {
                logger.info("Si aspettano " + seconds + " secondi... prima di proseguire oltre");
            }
            long millisecondsToWait = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(millisecondsToWait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Errore durante l'attesa.", e);
        }
    }
}
