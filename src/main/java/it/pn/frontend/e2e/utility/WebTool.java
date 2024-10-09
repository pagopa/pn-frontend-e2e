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
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebTool {
    private static final Integer NOTICE_CODE_LENGTH = 18;
    private static final WebDriver driver = Hooks.driver;
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private final String env = System.getProperty("environment");

    @Getter
    private static final String apiBaseUrl = System.getProperty("apiBaseUrl");

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
                log.error("Tipologia di portale non specificato o errato!");
                Assertions.fail("Tipologia di portale non specificato o errato!");
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
        log.info("Si chiude la scheda corrente");
        driver.close();
        String newTab = driver.getWindowHandles().stream().reduce((first, second) -> second).orElse(null);
        driver.switchTo().window(newTab);
    }

    public static void switchToOtherTab(){
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
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
        log.info("Protocol number generated: " + protocolNumber);

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
                log.info("Si aspettano " + minutes + " minuto/i e " + remainingSeconds + " secondi... prima di proseguire oltre");
            } else {
                log.info("Si aspettano " + seconds + " secondi... prima di proseguire oltre");
            }
            long millisecondsToWait = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(millisecondsToWait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Errore durante l'attesa.", e);
        }
    }

    public static String generateNoticeCodeNumber() {
        String threadNumber = (Thread.currentThread().getId() + "");
        String numberOfThread = threadNumber.length() < 2 ? "0" + threadNumber : threadNumber.substring(0, 2);
        String timeNano = System.nanoTime() + "";
        String randomClassePagamento = new Random().nextInt(14) + "";
        randomClassePagamento = randomClassePagamento.length() < 2 ? "0" + randomClassePagamento : randomClassePagamento;
        String finalNumber = "" + String.format("302" + randomClassePagamento + numberOfThread + timeNano.substring(0, timeNano.length() - 4));
        if (finalNumber.length() > NOTICE_CODE_LENGTH) {
            finalNumber = finalNumber.substring(0, NOTICE_CODE_LENGTH);
        } else {
            int remainingLength = NOTICE_CODE_LENGTH - finalNumber.length();
            String paddingString = String.valueOf(new Random().nextInt(9)).repeat(remainingLength);
            finalNumber = finalNumber + paddingString;
        }
        return finalNumber;
    }


    public static String convertToLocalTime(String timeString, ZoneId targetZoneId) {
        // Parse la stringa del tempo nel formato hh:mm a LocalTime
        LocalTime localTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));

        // Ottieni la data corrente
        LocalDate currentDate = LocalDate.now();

        // Combina la data corrente con l'ora per ottenere LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.of(currentDate, localTime);

        // Converte LocalDateTime al fuso orario target
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(targetZoneId);

        // Ritorna l'ora locale nel formato hh:mm
        return zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
