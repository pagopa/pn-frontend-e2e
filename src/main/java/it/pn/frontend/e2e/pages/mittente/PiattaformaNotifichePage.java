package it.pn.frontend.e2e.pages.mittente;

import com.google.gson.internal.LinkedTreeMap;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.config.WebDriverManager;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.rest.RestNotification;
import it.pn.frontend.e2e.utility.WebTool;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;
/*
* Modifiche principali:
Autowired NotificationSingleton e WebDriverConfig: Ho integrato i componenti tramite annotazione @Autowired per sfruttare la gestione delle dipendenze di Spring Boot, eliminando la necessità di istanziare manualmente queste classi.
Component: Aggiunta dell’annotazione @Component per rendere la classe gestibile da Spring Boot.*/

public class PiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("PiattaformaNotifichePage");


    @FindBy(id = "recipientId")
    WebElement cfTextField;

    @FindBy(id = "filter-button")
    WebElement filtraNotificaButtonMittente;

    @FindBy(id = "filter-notifications-button")
    WebElement filtraNotificaButton;

    @FindBy(id = "iunMatch")
    WebElement codiceIUNTextField;

    @FindBy(id = "startDate")
    WebElement dataInizioField;

    @FindBy(id = "endDate")
    WebElement dataFineField;

    @FindBy(id = "status")
    WebElement statoNotificaField;

    @FindBy(id = "side-item-API Key")
    WebElement apiKeyButton;

    @FindBy(id = "rows-per-page")
    WebElement numeroNotificheButton;

    @FindBy(id = "next")
    WebElement frecciaPaginaSuccessiva;

    @FindBy(id = "page2")
    WebElement secondPage;

    @FindBy(id = "page3")
    WebElement pageNumberButton;

    @FindBy(id = "pageSize-50")
    WebElement numberElement;

    @FindBy(id = "step-submit")
    WebElement continuaButtonDisabled;

    @FindBy(id = "subject")
    WebElement oggettoDellaNotificaTextField;

    @FindBy(id = "subject-helper-text")
    WebElement errorMessage;

    @FindBy(id = "breadcrumb-indietro-button")
    WebElement indietroButton;

    @FindBy(id = "button-exit")
    WebElement esciButton;

    @FindBy(id = "file-upload-error")
    WebElement estenzioneSbagliataMessage;

    @FindBy(id = "new-notification-btn")
    WebElement inviaNuovaNotificaButton;

    @FindBy(xpath = "//form[contains(@data-testid,'preliminaryInformationsForm')]")
    WebElement preliminaryInformationsForm;

    @FindBy(xpath = "//input[@aria-invalid='true']")
    List<WebElement> inputsError;

    @FindBy(id = "notifications-table")
    WebElement notificationsTable;

    @FindBy(id = "notificationsTable.body.row")
    List<WebElement> notificationsTableLines;

    @FindBy(id = "message")
    WebElement erroreMessaggio;

    @Getter
    @Setter
    private List<NetWorkInfo> netWorkInfos = new ArrayList<>();
    @Setter
    @Getter
    private RestNotification restNotificationParam;
    @Setter
    @Getter
    private NotificationSingleton notificationSingletonParam;
    @Setter
    @Getter
    private String environment;

    private WebTool webTool;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Getter
    @Setter
    private WebDriverManager webDriverManager;

    @Getter
    @Setter
    private HooksNew hooksNew;


    public PiattaformaNotifichePage(WebDriver driver) {
        this.driver = driver;
        this.webTool = new WebTool(driver);

    }

    public void waitLoadPiattaformaNotifichePAPage() {
        try {
            getWebDriverWait(60).withMessage("Il bottone invia notifica non visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("new-notification-btn")));
//                    .until(ExpectedConditions.visibilityOf( driver.findElement(By.id("new-notification-btn"))));

            getWebDriverWait(60)
                    .withMessage("Il titolo non è visibile")
                    .until(ExpectedConditions.or(
                            ExpectedConditions.visibilityOfElementLocated(By.id("Notifiche-page")),
                            ExpectedConditions.visibilityOfElementLocated(By.id("Notifications-page")),
                            ExpectedConditions.visibilityOfElementLocated(By.id("Zustellungen-page")),
                            ExpectedConditions.visibilityOfElementLocated(By.id("Obvestila-page"))
                    ));
            logger.info("Piattaforma Notifiche Page caricata");
        } catch (TimeoutException e) {
            Assertions.fail("Piattaforma Notifiche Page non caricata con errore : " + e.getMessage());
        }
    }

    public void insertCodiceFiscale(String codiceFiscale) {
        try {
            getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("recipientId"))));
            cfTextField = driver.findElement(By.id("recipientId"));
            cfTextField.click();
            cfTextField.sendKeys(codiceFiscale);
            getWebDriverWait(3).until(ExpectedConditions.attributeToBe(cfTextField, "value", codiceFiscale));
            logger.info("Codice Fiscale inserito correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Codice Fiscale Non inserito con errore: " + e.getMessage());
        }

    }

    public void selectFiltraNotificaButtonMittente() {
        webTool.waitTime(2);
        WebElement buttonFiltraNotifica = getWebDriverWait(50)
                .withMessage("Il bottone 'Filtra' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("filter-button")));

        webTool.waitTime(1); // eventuale animazione

        try {
            buttonFiltraNotifica.click();
        } catch (Exception e) {
            js().executeScript("arguments[0].click();", buttonFiltraNotifica);
        }
    }

    public void clickBottoneFiltraNotifica(String xpath, String codiceIUN) {
//        "filter-button"
//        filter-notifications-button
        final int maxTentativi = 15;
        final int attesaSecondi = 10;
        boolean trovato = false;

        for (int i = 0; i <= maxTentativi && !trovato; i++) {
            try {
                inserimentoCodiceIUN(codiceIUN);

                WebElement bottoneFiltra = getWebDriverWait(20)
                        .withMessage("Il bottone 'Filtra' non è cliccabile")
                        .until(ExpectedConditions.elementToBeClickable(By.id(xpath)));
                webTool.waitTime(1);
                try {
                    bottoneFiltra.click();
                } catch (Exception e) {
                    webTool.waitTime(1);
                    js().executeScript("arguments[0].click();", bottoneFiltra);
                }
                webTool.waitTime(1);
                By selettoreIUN = By.xpath("//*[contains(@id, 'notifications-table')]//td[contains(text(), '" + codiceIUN + "')]");
                getWebDriverWait(10)
                        .withMessage("Codice IUN '" + codiceIUN + "' non trovato nella tabella notifiche")
                        .until(ExpectedConditions.visibilityOfElementLocated(selettoreIUN));

                trovato = true;
            } catch (Exception ex) {
                webTool.waitTime(attesaSecondi);
            }
            if (!trovato) {
                driver.navigate().refresh();
            }
        }
        if (!trovato) {
            Assertions.fail("Impossibile cliccare sul bottone 'Filtra': la tabella non è visibile o il bottone non è cliccabile dopo " + maxTentativi + " tentativi.");
        }
    }

    public void selectFiltraNotificaButtonDestinatario() {
        getWebDriverWait(20).withMessage("Il filtro non è cliccabile").until(elementToBeClickable(driver.findElement(By.id("filter-notifications-button"))));
        filtraNotificaButton = driver.findElement(By.id("filter-notifications-button"));
        filtraNotificaButton.click();
        logger.info("Bottone filtra, nella pagina notifiche del delegato, cliccato correttamente");
    }

    public int getListaCf(String cfInserito) {
        try {
            attesaCaricamentoPagina();
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//p[contains(text(),'" + cfInserito + "')]"))));
            List<WebElement> cfListBy = driver.findElements(By.xpath("//p[contains(text(),'" + cfInserito + "')]"));
            logger.info("Codici fiscali trovati correttamente");
            return cfListBy.size();
        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("Codici fiscali non trovati " + e.getMessage());
            return 0;
        }

    }

    public void attesaCaricamentoPagina() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaEsistenzaEPassaggioPagina() {
        js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
        try {
            // secondPage = driver.findElement(By.id("page2"));
            getWebDriverWait(10).withMessage("Il bottone pagina 2 non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("page2"))));
            logger.info("Bottone pagina 2 trovato");
            driver.findElement(By.id("page2")).click();
            return true;
        } catch (TimeoutException e) {
            logger.error("bottone pagina 2 non trovata con errore: {}", e.getMessage());
            return false;
        }
    }

    public void inserimentoCodiceIUN(String codiceIUN) {
//        logger.info("Si inserisce il codice IUN...." + codiceIUN);
//        webTool.waitTime(10);
//        getWebDriverWait(100).withMessage("Il campo per l'inserimento del codice IUN non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("iunMatch"))));
//        driver.findElement(By.id("iunMatch")).click();
//        driver.findElement(By.id("iunMatch")).sendKeys(codiceIUN);
//        logger.info("Codice IUN inserito");


        // Attendi che il campo per l'inserimento del codice IUN sia visibile
        WebElement iunField = getWebDriverWait(100)
                .withMessage("Il campo per l'inserimento del codice IUN non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("iunMatch")));

        // Clicca sul campo
        iunField.click();

        // Cancella il contenuto del campo
        iunField.clear();

        // Inserisci il nuovo codice IUN
        iunField.sendKeys(codiceIUN);

    }

    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        WebElement codiceIUN = getCodiceIUN(codiceIUNInserito);
        return codiceIUN != null;
    }

    private WebElement getCodiceIUN(String codiceIUNInserito) {
        try {
            getWebDriverWait(30).withMessage("Il codice IUN: " + codiceIUNInserito + " non è presente")
                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'" + codiceIUNInserito + "')]"))));
            WebElement codiceIUNBy = driver.findElement(By.xpath("//button[contains(text(),'" + codiceIUNInserito + "')]"));
            return codiceIUNBy;
        } catch (TimeoutException e) {
            return null;
        }
    }

    public boolean controlloDateInserite(String dataInerita) {
        String[] date = dataInerita.split("/");
        return date[0].length() == 2 && date[1].length() == 2 && date[2].length() == 4;
    }


    public void inserimentoArcoTemporale(String da, String a, boolean previousMonthAButtonEndDateClick) {

        List<WebElement> dataFieldList = driver.findElements(By.xpath("//button[@aria-label='Scegli data']"));

        /*CodeBuild carica il calendario sul campo di input invece del bottone con l'icona.
        Si cambia il selettore in base alla presenza del bottone di calendario.
         */
        if (dataFieldList.isEmpty()) {
            dataInizioField = driver.findElement(By.xpath("//input[@id='startDate']"));
            dataFineField = driver.findElement(By.xpath("//input[@id='endDate']"));
        }
        else {
            dataInizioField = dataFieldList.get(0);
            dataFineField = dataFieldList.get(1);
        }

        String[] arraySplitDateDa = da.split("/");

        // Step 2: Click on the input field to open the calendar pop-up
        dataInizioField.click();

        // Step 3: Wait for the calendar pop-up to appear
        WebElement calendar = getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".MuiDateCalendar-root"))));  // Adjust based on your app

        int dayDa = Integer.parseInt(arraySplitDateDa[0]);
        int monthDa = Integer.parseInt(arraySplitDateDa[1]);
        int yerarsDa = Integer.parseInt(arraySplitDateDa[2]);
        DateFormatSymbols DFSymbols = new DateFormatSymbols(new Locale("it", "IT"));
        webTool.waitTime(3);
        WebElement previousMonthButton = driver.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//button[@title='Previous month']"));

        int click = 0;
        WebElement monthDaToSelect = null;
        while (click < 36) {
            try {
                monthDaToSelect = calendar.findElement(By.xpath("//div[contains(text(), '" + DFSymbols.getMonths()[monthDa - 1] + " " + yerarsDa + "')]"));
                if (monthDaToSelect.isDisplayed()) {
                    break;
                }
            } catch (NoSuchElementException e) {
                previousMonthButton.click();
                click++;
            }
        }
        // Step 4: Select a date (e.g., the 15th day of the current month)
        webTool.waitTime(2);
        WebElement dateToSelect = getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(calendar.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayDa + "']"))));
        dateToSelect.click();

        String[] arraySplitDateA = a.split("/");

        int dayA = Integer.parseInt(arraySplitDateA[0]);
        int monthA = Integer.parseInt(arraySplitDateA[1]);
        int yerarsA = Integer.parseInt(arraySplitDateA[2]);

        webTool.waitTime(2);
        // Step 2: Click on the input field to open the calendar pop-up
        dataFineField.click();

        // Step 3: Wait for the calendar pop-up to appear
        WebElement calendar1 = getWebDriverWait(20).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".MuiDateCalendar-root"))));  // Adjust based on your app

        if (previousMonthAButtonEndDateClick) {
            WebElement previousMonthAButton = null;
            try {
                getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//button[@title='Previous month']"))));
                previousMonthAButton = driver.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//button[@title='Previous month']"));
                int clickA = 0;
                WebElement monthAToSelect = null;
                while (clickA < 36) {
                    try {
                        monthAToSelect = calendar1.findElement(By.xpath("//div[contains(text(), '" + DFSymbols.getMonths()[monthA - 1] + " " + yerarsA + "')]"));
                        if (monthAToSelect.isDisplayed()) {
                            break;
                        }
                    } catch (NoSuchElementException e) {
                        previousMonthAButton.click();
                        click++;
                    }
                }

                getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(calendar1.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayA + "']"))));
            } catch (ElementClickInterceptedException e) {
                logger.info("Previous month non cliccabile");
            }
        }else {
            getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(calendar1.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayA + "']"))));
        }

        webTool.waitTime(2);

        // Step 4: Select a date (e.g., the 15th day of the current month)
        // Adjust based on your app

        WebElement dateToSelect1 = calendar1.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayA + "']"));
        dateToSelect1.click();

        getWebDriverWait(3).until(ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//*[@id='endDate']")), "value", a));
    }

    public boolean inserimentoArcoTemporaleErrato(String da, String a) {

        boolean result = true;
        List<WebElement> dataFieldList = driver.findElements(By.xpath("//button[@aria-label='Scegli data']"));

        /*CodeBuild carica il calendario sul campo di input invece del bottone con l'icona.
        Si cambia il selettore in base alla presenza del bottone di calendario.
         */
        if (dataFieldList.isEmpty()) {
            dataInizioField = driver.findElement(By.xpath("//input[@id='startDate']"));
            dataFineField = driver.findElement(By.xpath("//input[@id='endDate']"));
        }
        else {
            dataInizioField = dataFieldList.get(0);
            dataFineField = dataFieldList.get(1);
        }

        webTool.waitTime(5);

        String[] arraySplitDateDa = da.split("/");

        // Step 2: Click on the input field to open the calendar pop-up
        dataInizioField.click();

        // Step 3: Wait for the calendar pop-up to appear
        WebElement calendar = getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".MuiDateCalendar-root"))));  // Adjust based on your app

        int dayDa = Integer.parseInt(arraySplitDateDa[0]);
        int monthDa = Integer.parseInt(arraySplitDateDa[1]);
        int yerarsDa = Integer.parseInt(arraySplitDateDa[2]);
        DateFormatSymbols DFSymbols = new DateFormatSymbols(new Locale("it", "IT"));

        WebElement previousMonthButton = driver.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//button[@title='Previous month']"));

        int click = 0;
        while (click < 36) {
            try {
                WebElement monthDaToSelect = calendar.findElement(By.xpath("//div[contains(text(), '" + DFSymbols.getMonths()[monthDa - 1] + " " + yerarsDa + "')]"));
                if (monthDaToSelect.isDisplayed()) {
                    break;
                }
            } catch (NoSuchElementException e) {
                previousMonthButton.click();
                click++;
            }
        }

        // Step 4: Select a date (e.g., the 15th day of the current month)
        webTool.waitTime(3);
        WebElement dateToSelect = calendar.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayDa + "']"));
        dateToSelect = getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(dateToSelect));
        dateToSelect.click();

        logger.info("DATA INIZIO FIELD: " + dataInizioField.getAttribute("value"));

        String[] arraySplitDateA = a.split("/");

        int dayA = Integer.parseInt(arraySplitDateA[0]);
        int monthA = Integer.parseInt(arraySplitDateA[1]);
        int yerarsA = Integer.parseInt(arraySplitDateA[2]);

        webTool.waitTime(3);
        // Step 2: Click on the input field to open the calendar pop-up
        dataFineField.click();

        // Step 3: Wait for the calendar pop-up to appear
        WebElement calendar1 = getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".MuiDateCalendar-root"))));  // Adjust based on your app

        WebElement previousMonthAButton = driver.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//button[@title='Previous month']"));

        try {
            int clickA = 0;
            while (clickA < 36) {
                try {
                    WebElement monthAToSelect = calendar1.findElement(By.xpath("//div[contains(text(), '" + DFSymbols.getMonths()[monthA - 1] + " " + yerarsA + "')]"));
                    if (monthAToSelect.isDisplayed()) {
                        break;
                    }
                } catch (NoSuchElementException e) {
                    previousMonthAButton.click();
                    click++;
                }
            }

            webTool.waitTime(3);

            // Step 4: Select a date (e.g., the 15th day of the current month)
            WebElement dateToSelect1 = calendar1.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayA + "']"));
            dateToSelect1 = getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(dateToSelect1));
            dateToSelect1.click();

            logger.info("DATA FINE FIELD: " + dataFineField.getAttribute("value"));

            getWebDriverWait(3).until(ExpectedConditions.attributeToBe(dataFineField, "value", a));
        } catch (ElementClickInterceptedException e) {
            logger.error("Non è possibile settare una data Fine precedente rispetto alla data Inizio: {}", e.getMessage());
            result = false;
        }
        return result;
    }

    public int getListDate() {
        attesaCaricamentoPagina();
        getWebDriverWait(30).withMessage("Nessuna data trovata").until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//tr[@id='notificationsTable.body.row']"))));
        List<WebElement> dataListBy = driver.findElements(By.xpath("//tr[@id='notificationsTable.body.row']"));
        logger.info("Date trovate correttamente");

        return dataListBy.size();
    }

    public void selezionareStatoNotifica(String statoInserito) {
        statoNotificaField = driver.findElement(By.id("status"));
        statoNotificaField.click();
        try {
            WebElement statoNotificaBy = driver.findElement(By.xpath("//li[contains(@data-value,'" + statoInserito + "')]"));
            getWebDriverWait(30).until(elementToBeClickable(statoNotificaBy));
            if (statoNotificaBy.isDisplayed()) {
                js().executeScript("arguments[0].click()", statoNotificaBy);
            } else {
                js().executeScript("arguments[0].scrollIntoView(true);", statoNotificaBy);
                js().executeScript("arguments[0].click()", statoNotificaBy);
            }
            logger.info("Stato notifica selezionato correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Stato notifica NON trovata con errore: " + e.getMessage());
        }
    }

    public int getListStato(String statoNotifica) {
        try {
            getWebDriverWait(60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'" + statoNotifica + "')]")));
            List<WebElement> statoNotificaBy = elements(By.xpath("//span[contains(text(),'" + statoNotifica + "')]"));
            return statoNotificaBy.size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public boolean IsAnAdvancedStatus() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'status-chip-')]"))));
        WebElement statoNotificaBy = driver.findElement(By.xpath("//div[contains(@id,'status-chip-')]"));
        String status = statoNotificaBy.getText();
        return !status.equalsIgnoreCase("Depositata");
    }

    public void selezionaNotifica120Giorni() {
        waitLoadPage();
        try {

            attesaCaricamentoPagina();
            verificaEsistenzaTabellaNotifiche();
            buttonRighePagine();
            selezionaPage50();

            webTool.waitTime(10);
            List<WebElement> notifiche = getWebDriverWait(10)
                    .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("notificationsTable.body.row"), 0));


            Calendar calendar = GregorianCalendar.getInstance();
            logger.info("CALENDAR: " + calendar);
            int index = calendar.get(Calendar.HOUR_OF_DAY);
            logger.info("index NOTIFICHE : " + index);

            logger.info("Scenario " + hooksNew.getScenario());

            if (notifiche != null) {
                int randomNumber = 0;
                if (notifiche.size()>1){
                    randomNumber = ThreadLocalRandom.current().nextInt(0, notifiche.size() - 1);
                }

                logger.info("ELENCO NOTIFICHE : " + notifiche.size());
                logger.info("NOTIFICA SELEZIONATA: " + randomNumber);
                clickRowNotificationIndex(notifiche.get(randomNumber));
                /**
                 if (notifiche.size() >= index) {
                 logger.info("NOTIFICA SELEZIONATA: " + index);
                 notifiche.get(index).click();
                 } else {
                 notifiche.get(notifiche.size() - 1).click();
                 }
                 **/
            } else {
                Assertions.fail("Non ci sono notifiche da selezionare nel arco temporale settato");
            }
        } catch (TimeoutException e) {
            Assertions.fail("Notifica non trovata con errore: " + e.getMessage());
        }
    }


    public void selezionaNotifica120Giorni(int rows) {
        waitLoadPage();
        try {

            attesaCaricamentoPagina();
            verificaEsistenzaTabellaNotifiche();
            buttonRighePagine();
            selezionaPage50();

            webTool.waitTime(10);
            List<WebElement> notifiche = getWebDriverWait(10)
                    .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("notificationsTable.body.row"), 0));


            logger.info("Scenario " + hooksNew.getScenario());

            if (notifiche != null) {

                int randomNumber = 0;
                if (notifiche.size()>1){
                    randomNumber = ThreadLocalRandom.current().nextInt(0, notifiche.size() - 1);
                }

                Calendar calendar = GregorianCalendar.getInstance();
                int index = calendar.get(Calendar.HOUR_OF_DAY) + rows;
                logger.info("HOUR..." + index);
                logger.info("SIZE ROWS TABLE..." + notifiche.size());
                logger.info("ROWS TABLE..." + randomNumber);
                logger.info("ROWS SELEZIONATA: " + randomNumber);

                clickRowNotificationIndex(notifiche.get(randomNumber));
                /**
                 if (notifiche.size() > index) {
                 logger.info("ROWS SELEZIONATA1: " + index);
                 notifiche.get(index).click();
                 } else {
                 logger.info("ROWS SELEZIONATA2: " + rows);
                 if ((rows + 1) < notifiche.size()) {
                 logger.info("ROWS SELEZIONATA3: " + (notifiche.size() - (rows + 1)));
                 notifiche.get(notifiche.size() - (rows + 1)).click();
                 ;
                 } else {
                 logger.info("ROWS SELEZIONATA4: " + (notifiche.size() - 1));
                 notifiche.get(notifiche.size() - 1).click();
                 ;
                 }
                 }**/
            } else {
                Assertions.fail("Non ci sono notifiche da selezionare nel arco temporale settato");
            }
        } catch (TimeoutException e) {
            Assertions.fail("Notifica non trovata con errore: " + e.getMessage());
        }
    }

    public void selezionaPrimaNotifica() {
        waitLoadPage();
        try {
            attesaCaricamentoPagina();
            verificaEsistenzaTabellaNotifiche();
            buttonRighePagine();
            selezionaPage50();

            List<WebElement> notifiche = getWebDriverWait(10)
                    .withMessage("Impossibile trovare notificationsTable.body.row")
                    .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("notificationsTable.body.row"), 0));

            if (!notifiche.isEmpty()) {
                WebElement primaNotifica = notifiche.get(0);

                clickRowNotificationIndex(primaNotifica);
                logger.info("Cliccato sul primo pulsante 'Vedi Dettaglio'");
            } else {
                Assertions.fail("Nessuna notifica trovata");
            }
        } catch (TimeoutException e) {
            Assertions.fail("Notifica non trovata con errore: " + e.getMessage());
        }
    }

    public void selezionaPrimaNotifica(String stato) {
        waitLoadPage();
        try {
            attesaCaricamentoPagina();
            verificaEsistenzaTabellaNotifiche();
            buttonRighePagine();
            selezionaPage50();

            List<WebElement> notificationsTables = getWebDriverWait(10)
                    .withMessage("Impossibile trovare notificationsTable")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("tr[id='notificationsTable.body.row']")) );

            for (WebElement row : notificationsTables) {
                WebElement statusChip = getWebDriverWait(10)
                        .withMessage("Impossibile la colonna con lo stato: "+stato)
                        .until(
                        ExpectedConditions.visibilityOf(row.findElement(By.cssSelector("div[id^='status-chip-']")))
                );
                if (statusChip.getText().trim().equals(stato)) {

                    WebElement vediDettaglioButton = getWebDriverWait(10)
                            .withMessage("Impossibile dettagli con lo stato: "+stato)
                            .until( ExpectedConditions.elementToBeClickable(row.findElement(By.cssSelector("button[data-testid='goToNotificationDetail']"))));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", vediDettaglioButton);

                    // Utilizza JavaScript per fare clic
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", vediDettaglioButton);

                    break;
                }
            }

        } catch (TimeoutException e) {
            Assertions.fail("Notifica non trovata con errore: " + e.getMessage());
        }
    }


    private void clickRowNotificationIndex(WebElement primaNotifica) {
        try {
            // Trova il pulsante "Vedi dettaglio" all'interno della riga specifica (primaNotifica)
            WebElement buttonVediDettaglio = getWebDriverWait(10)
                    .withMessage("Il pulsante 'Vedi Dettaglio' non è presente")
                    .until(ExpectedConditions.presenceOfNestedElementLocatedBy(
                            primaNotifica, By.cssSelector("button[data-testid='goToNotificationDetail']"))
                    );

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonVediDettaglio);

            buttonVediDettaglio = getWebDriverWait(10)
                    .withMessage("Il pulsante 'Vedi Dettaglio' non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(buttonVediDettaglio));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonVediDettaglio);
            logger.info("Cliccato sul pulsante 'Vedi Dettaglio'");
        } catch (Exception e) {
            Assertions.fail("Errore durante il clic sul pulsante 'Vedi Dettaglio': " + e.getMessage());
        }
    }



    public void selezionaPage50() {
        WebElement pageSize50 = getWebDriverWait(30)
                .withMessage("Impossibile trovare pageSize-50")
                .until(ExpectedConditions.elementToBeClickable(By.id("pageSize-50")));
        pageSize50.click();
    }

    public void buttonRighePagine() {
        WebElement buttonRighePagine = getWebDriverWait(10)
                .withMessage("Impossibile trovare Botton Righe per pagina ")
                .until(ExpectedConditions.elementToBeClickable(By.id("rows-per-page")));
        buttonRighePagine.click();
    }

    private void verificaEsistenzaTabellaNotifiche() {
        getWebDriverWait(60)
                .withMessage("La tabella delle notifiche non è caricata correttamente")
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("notificationsTable.body.row"), 0));
    }

    public void selezionaNotificaIUN(String IUN) {
        if (webDriverConfig != null) {
            environment = webDriverConfig.getEnvironment();
        }

        driver.navigate().to("https://selfcare." + environment + ".notifichedigitali.it/dashboard/" + IUN + "/dettaglio");
    }

    //    public void selectInviaUnaNuovaNotificaButton() {
//        getWebDriverWait(10).withMessage("Il bottone invia notifica non è cliccabile").until(elementToBeClickable(driver.findElement(By.id("new-notification-btn"))));
//        inviaNuovaNotificaButton = driver.findElement(By.id("new-notification-btn"));
//        inviaNuovaNotificaButton.click();
//    }
    public void selectInviaUnaNuovaNotificaButton() {

        WebElement button = getWebDriverWait(10)
                .withMessage("Il bottone invia notifica non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("new-notification-btn")));

        button.click();
    }


    public void waitLoadRefreshPage() {
        webTool.waitTime(5);
    }

    public String ricercaNotifica(String oggettoDellaNotifica, String statoNotifica) {

        String codiceIUN = "";

        int posizioneNotifica = verificaEsistenzaNotifica(oggettoDellaNotifica, statoNotifica);
        logger.info("la posizione della notifica è uguale: {}", posizioneNotifica);
        if (posizioneNotifica != -1) {

            List<WebElement> codiciIUNList = driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')]"));
            codiceIUN = codiciIUNList.get(posizioneNotifica).getText();
        }
        return codiceIUN;
    }

    public int verificaEsistenzaNotifica(String oggettoDellaNotifica, String statoNotifica) {

        List<WebElement> listaOggetti = ricercaListaOggetti();
        List<WebElement> listaStati = ricercaListaStati();

        if (listaOggetti != null && listaStati != null) {

            for (int i = 0; i < listaOggetti.size(); i++) {
                logger.info("ciclo for di verifica esistenza");
                logger.info(listaOggetti.get(i).getText() + " " + oggettoDellaNotifica);
                logger.info(listaStati.get(i).getText() + " " + statoNotifica);
                if (listaOggetti.get(i).getText().equals(oggettoDellaNotifica) &&
                        listaStati.get(i).getText().equals(statoNotifica)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public List<WebElement> ricercaListaOggetti() {
        try {
            return getWebDriverWait(30)
                    .withMessage("Impossibile trovare la Lista Oggetti")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[3]")));
//            return driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1sdct2p')]"));
        } catch (TimeoutException e) {
            logger.info("lista oggetti ancora non presenti");
            return null;
        }
    }

    public List<WebElement> ricercaListaStati() {
        try {
            return driver.findElements(By.xpath("//span[contains(@class,'MuiChip-label MuiChip-labelMedium css-11cvqrr')]"));
        } catch (TimeoutException e) {
            logger.info("lista stati ancora non presenti");
            return null;
        }
    }

    public void selezionareLaVoceApiKey() {
        getWebDriverWait(30).withMessage("la voce api key non è cliccabile").until(elementToBeClickable(driver.findElement(By.id("side-item-API Key"))));
        apiKeyButton = driver.findElement(By.id("side-item-API Key"));
        js().executeScript("arguments[0].click()", apiKeyButton);
    }


    public String conversioneFormatoDate(String date) {
        String[] dateInserita = date.split("-");
        return dateInserita[2] + "/" + dateInserita[1] + "/" + dateInserita[0];
    }

    public int controlloNumeroRisultatiDate() {
        attesaCaricamentoPagina();
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[1]"))));
        List<WebElement> dataListBy = driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[1]"));
        logger.info("Date trovate correttamente");

        return dataListBy.size();
    }

    public void inserimentoData(String dataInserita) {

        dataInizioField = getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(By.id("startDate")));
        dataInizioField = getWebDriverWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='startDate']")));

        dataInizioField = driver.findElement(By.id("startDate"));
        dataFineField = driver.findElement(By.id("endDate"));

        getWebDriverWait(10).until(ExpectedConditions.visibilityOfAllElements(dataInizioField, dataFineField));


        webTool.waitTime(10);

        String[] arraySplitDateDa = dataInserita.split("/");

        List<WebElement> dataFieldList = driver.findElements(By.cssSelector(".MuiInputBase-input"));
        int dayDa = Integer.parseInt(arraySplitDateDa[0]);

        // Step 2: Click on the input field to open the calendar pop-up
        dataFieldList.get(2).click();

        // Step 3: Wait for the calendar pop-up to appear
        WebElement calendar = getWebDriverWait(10).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".MuiDateCalendar-root"))));  // Adjust based on your app

        // Step 4: Select a date (e.g., the 15th day of the current month)
        WebElement dateToSelect = calendar.findElement(By.xpath("//div[contains(@class, 'MuiDateCalendar-root')]//div[contains(@class,'MuiDayCalendar-monthContainer')]//*[text()='" + dayDa + "']"));
        dateToSelect.click();

        getWebDriverWait(3).until(ExpectedConditions.attributeToBe(dataInizioField, "value", dataInserita));
    }

    public boolean verificaEsistenzaRisultati() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("call-to-action-first"))));
            logger.info("Messaggio visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void siVisualizzaCorrettamenteIlCFField() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("recipientId"))));
            logger.info("Il campo di codice fiscale si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il campo di codice fiscale NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteIlCodiceIUNField() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("iunMatch"))));
            logger.info("Il campo di codice iun si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il campo di codice iun NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLoStatoField() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("status"))));
            logger.info("Il campo dello stato si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il campo dello stato NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLaDataInzioField() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("startDate"))));
            logger.info("Il campo della data di inizio si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il campo della data di inizio NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteLaDataFineField() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("endDate"))));
            logger.info("Il campo della data di fine si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il campo della data di fine NON si visualizza correttamente : " + e.getMessage());
        }
    }

    public boolean verificaEsistenzaCFNotifiche() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[2]"))));
        List<WebElement> cfFiealdBy = driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[2]"));
        return !cfFiealdBy.isEmpty();
    }

    public boolean verificaEsistenzaCodiceIUNNotifiche() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[4]"))));
        List<WebElement> codiciIUNBy = driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[4]"));
        return !codiciIUNBy.isEmpty();
    }

    public boolean verificaEsistenzaGruppoNotifiche() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[5]"))));
        List<WebElement> gruppiBy = driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[5]"));
        return !gruppiBy.isEmpty();
    }

    public boolean verificaEsistenzaStatoNotifiche() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[6]"))));
        List<WebElement> statiBy = driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[6]"));
        return !statiBy.isEmpty();
    }


    public int getNRighe() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//tr[@id='notificationsTable.body.row']"))));
        List<WebElement> nRigheBy = driver.findElements(By.xpath("//tr[@id='notificationsTable.body.row']"));
        return nRigheBy.size();
    }

    public String numeroNotifiche() {
        getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("rows-per-page"))));
        WebElement nRigheBy = driver.findElement(By.id("rows-per-page"));
        return nRigheBy.getText();
    }

//    public void controlloOrdineNotifiche() {
//        List<WebElement> listaDate = getListaDate();
//        LocalDate dataSuccessiva;
//        final String dataOggi = "Oggi";
//        LocalDate dataPrecedente;
//        if (listaDate != null) {
//            for (int i = 0; i < listaDate.size() - 1; i++) {
//                String dataDopo = listaDate.get(i).getText();
//                String dataPrima = listaDate.get(i + 1).getText();
//                if (dataOggi.equals(dataDopo)) {
//                    dataSuccessiva = LocalDate.now();
//                } else {
//                    String[] dateA = dataDopo.split("/");
//                    dataDopo = dateA[2] + "-" + dateA[1] + "-" + dateA[0];
//                    dataSuccessiva = LocalDate.parse(dataDopo);
//                }
//                if (dataOggi.equals(dataPrima)) {
//                    dataPrecedente = LocalDate.now();
//                } else {
//                    String[] dateA = dataPrima.split("/");
//                    dataPrima = dateA[2] + "-" + dateA[1] + "-" + dateA[0];
//                    dataPrecedente = LocalDate.parse(dataPrima);
//                }
//                if (dataSuccessiva.isBefore(dataPrecedente)) {
//                    logger.error("Le date non sono ordinate dal più recente");
//                    Assertions.fail("Le date non sono ordinate dal più recente");
//                    return;
//                }
//            }
//        }
//        logger.info("Le date sono visualizzate correttamente");
//    }

    public void controlloOrdineNotifiche() {
        List<WebElement> listaDate = getListaDate();
        final String dataOggi = "Oggi";

        // Gestisci caso lista vuota o nulla
        if (listaDate == null || listaDate.isEmpty()) {
            logger.warn("La lista delle date è vuota o nulla.");
            return;
        }

        // Usa DateTimeFormatter per un parsing più robusto
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < listaDate.size() - 1; i++) {
            String dataDopo = listaDate.get(i).getText();
            String dataPrima = listaDate.get(i + 1).getText();

            LocalDate dataSuccessiva = dataOggi.equals(dataDopo) ? LocalDate.now() : LocalDate.parse(dataDopo, formatter);
            LocalDate dataPrecedente = dataOggi.equals(dataPrima) ? LocalDate.now() : LocalDate.parse(dataPrima, formatter);

            // Verifica se le date sono ordinate
            if (dataSuccessiva.isBefore(dataPrecedente)) {
                logger.error("Le date non sono ordinate dal più recente");
                Assertions.fail("Le date non sono ordinate dal più recente");
                return;
            }
        }

        logger.info("Le date sono visualizzate correttamente");
    }

    private List<WebElement> getListaDate() {
//        try {
//            attesaCaricamentoPagina();
//            getWebDriverWait(30).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]"))));
//            List<WebElement> dataListBy = driver.findElements(By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-164wyiq')]"));
//            logger.info("Date trovate correttamente");
//
//            return dataListBy;
//        } catch (TimeoutException e) {
//            Assertions.fail("Date NON trovate con errore: " + e.getMessage());
//            return null;
//        }
//        webTool.waitTime(5);
        List<WebElement> dataListBy = getWebDriverWait(30)
                .withMessage("Impossibile Estrarre la prima colonna inerente alle date")
                .until(d -> d.findElements(By.cssSelector("#notifications-table td:nth-child(1)")));

        if (dataListBy == null || dataListBy.isEmpty()) {
            logger.warn("Nessuna data trovata.");
            return null;
        }
        return dataListBy;

    }

    public void siScrollaFinoAllaFineDellaPagina() {
        getWebDriverWait(30).withMessage("il bottone che indica il numero delle notifiche non è visibile")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("rows-per-page"))));
        numeroNotificheButton = driver.findElement(By.id("rows-per-page"));
        if (!numeroNotificheButton.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
    }

    public void siCambiaPaginaUtilizzandoUnaFrecetta(Integer numPage) {
        Integer index = 1;
        getWebDriverWait(60).withMessage("il bottone pagina successiva non è cliccabile")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("next"))));
        frecciaPaginaSuccessiva = driver.findElement(By.id("next"));
        if (!frecciaPaginaSuccessiva.isDisplayed()) {
            numeroNotificheButton = driver.findElement(By.id("rows-per-page"));
            js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
        while (index <= numPage) {
            frecciaPaginaSuccessiva.click();
            index++;
            webTool.waitTime(2);
        }
    }

    public void selezionaUltimaPaginaUtilizzandoUnaFrecetta() {
        getWebDriverWait(60).withMessage("il bottone pagina successiva non è cliccabile ultima")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("next")));
        frecciaPaginaSuccessiva = driver.findElement(By.id("next"));
        if (!element(By.id("next")).isDisplayed()) {
            numeroNotificheButton = driver.findElement(By.id("rows-per-page"));
            js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
        int i = 0;
        while (element(By.id("next")).isEnabled()) {
            element(By.id("next")).click();
            webTool.waitTime(2);
            try {
                if (element(By.id("page15")).isDisplayed()){
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.error("page15 non visualizzato");
            }
        }

    }

    public void selezionaFrecettaFinoaPagina(int pagina) {
        String paginaString = "page" + pagina;
        getWebDriverWait(60).withMessage("il bottone pagina successiva non è cliccabile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("next")));
        frecciaPaginaSuccessiva = driver.findElement(By.id("next"));
        if (!element(By.id("next")).isDisplayed()) {
            numeroNotificheButton = driver.findElement(By.id("rows-per-page"));
            js().executeScript("arguments[0].scrollIntoView(true);", numeroNotificheButton);
        }
        int i = 0;
        while (element(By.id("next")).isEnabled() && i<=pagina ) {
            element(By.id("next")).click();
            webTool.waitTime(2);
            try {
                if (element(By.id("paginaString")).isDisplayed()){
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.error(paginaString +" non visualizzata");
            }
            i++;
        }

    }



    public void siCambiaPaginaUtilizzandoUnNumero() {
        pageNumberButton = driver.findElement(By.id("page3"));
        if (!pageNumberButton.isDisplayed()) {
            js().executeScript("arguments[0].scrollIntoView(true);", pageNumberButton);
        }
        js().executeScript("arguments[0].click()", pageNumberButton);
    }

    public void siCambiaIlNumeroElementiVisualizzatiAttraversoIlFiltro() {

        getWebDriverWait(10)
                .withMessage("Il pulsante 'righe per pagina' non è presente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("rows-per-page"))));
        numeroNotificheButton = driver.findElement(By.id("rows-per-page"));
        vaiInFondoAllaPagina();
        webTool.waitTime(1);
        numeroNotificheButton.click();

        getWebDriverWait(10)
                .withMessage("Il pulsante '50' per assegnare il numero di notifiche per pagina non è presente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("pageSize-50"))));
        numberElement = driver.findElement(By.id("pageSize-50"));
        webTool.waitTime(1);
        numberElement.click();
    }

    public void clickContinuaDisabled() {
        continuaButtonDisabled = driver.findElement(By.id("step-submit"));
        continuaButtonDisabled.isDisplayed();
    }

    public void inserimentoOggettoNotificaErrato(String oggettoDellaNotifica) {
        oggettoDellaNotificaTextField = driver.findElement(By.id("subject"));
        scrollToElementClickAndInsertText(oggettoDellaNotificaTextField, oggettoDellaNotifica);
    }

    public boolean errorMessage() {
        errorMessage = driver.findElement(By.id("subject-helper-text"));
        return errorMessage.isDisplayed();
    }

    public void clickIndietroButton() {
        indietroButton = driver.findElement(By.id("breadcrumb-indietro-button"));
        getWebDriverWait(30).withMessage("Il bottone indietro non è  cliccabile")
                .until(elementToBeClickable(indietroButton));
        indietroButton.click();
    }

    public void vuoiUscirePopUp() {
        try {
            getWebDriverWait(30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Vuoi uscire?')]"))));
            logger.info("Si visualizza il pop up vuoi uscire");
        } catch (TimeoutException e) {
            Assertions.fail("Non si visualizza il pop up vuoi uscire con errore:" + e.getMessage());
        }
    }

    public void clickSuEsci() {
        getWebDriverWait(30).withMessage("Il bottone esci non è  cliccabile")
                .until(elementToBeClickable(driver.findElement(By.id("button-exit"))));
        esciButton = driver.findElement(By.id("button-exit"));
        esciButton.click();
    }

    public boolean estensioneSbagliataErrore() {
        estenzioneSbagliataMessage = driver.findElement(By.id("file-upload-error"));
        return estenzioneSbagliataMessage.isDisplayed();
    }


    public List<String> getCodiceIunPresenti() {
        List<WebElement> righeTabella = driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[4]"));
        List<String> listaCodici = new ArrayList<>();
        for (WebElement rigaTabella : righeTabella) {
            String codiceIun = rigaTabella.getText();
            listaCodici.add(codiceIun);
        }
        return listaCodici;
    }

    public List<String> getCodiceIunPresentiPF() {
        List<WebElement> righeTabella = driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[4]"));
        List<String> listaCodici = new ArrayList<>();
        for (WebElement rigaTabella : righeTabella) {
            String codiceIun = rigaTabella.getText();
            listaCodici.add(codiceIun);
        }
        return listaCodici;
    }

    public String getCodiceIunInserito() {
        codiceIUNTextField = driver.findElement(By.id("iunMatch"));
        return codiceIUNTextField.getText();
    }

    public List<String> getCodiceIunPersonaGiuridica() {
        List<WebElement> righeTabella = driver.findElements(By.xpath("//*[@id=\"notificationsTable.body.row\"]/td[4]"));
        List<String> listaCodici = new ArrayList<>();
        for (WebElement rigaTabella : righeTabella) {
            String codiceIun = rigaTabella.getText();
            listaCodici.add(codiceIun);
        }
        return listaCodici;
    }

    public boolean controlloEsistenzaMessagioErroreCF() {
        getWebDriverWait(30).withMessage("Messagio di errore 'Inserisci il codice per intero' non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("recipientId-helper-text"))));
        WebElement errorMessageBy = driver.findElement(By.id("recipientId-helper-text"));
        return errorMessageBy.isDisplayed();
    }


    public boolean verificaBottoneFiltraDisabilitato() {
        try {
            getWebDriverWait(30).withMessage("buttone Filtra non è visibile").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("filter-button"))));
            filtraNotificaButtonMittente = driver.findElement(By.id("filter-button"));
            return Boolean.parseBoolean(filtraNotificaButtonMittente.getAttribute("disabled"));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean controlloEsistenzaMessagioErroreIUN() {
        getWebDriverWait(30).withMessage("Messagio di errore 'Inserisci un codice IUN valido' non trovato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("iunMatch-helper-text"))));
        WebElement errorMessageBy = driver.findElement(By.id("iunMatch-helper-text"));
        return errorMessageBy.isDisplayed();
    }

    public boolean verificaCampiPreliminariNotificaVuoti() {
        webTool.waitTime(10);
        preliminaryInformationsForm = getWebDriverWait(60)
                .withMessage("Il form preliminaryInformationsForm non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[contains(@data-testid,'preliminaryInformationsForm')]")));
        List<WebElement> inputElements = preliminaryInformationsForm.findElements(By.tagName("input"));
        for (WebElement inputElement : inputElements) {
            if (inputElement.getAttribute("type").equals("text") && !inputElement.getAttribute("value").isEmpty()) {
                if (!inputElement.getAttribute("value").equalsIgnoreCase("comune di verona")) {
                    return false;
                }
            }
            if (inputElement.getAttribute("type").equals("radio") && inputElement.isSelected() && !inputElement.getAttribute("value").equalsIgnoreCase("it") && !inputElement.getAttribute("value").equalsIgnoreCase("REGISTERED_LETTER_890")) {
                return false;
            }
        }
        return true;
    }

    public void checkDefaultPagination() {
        final String defaultNumberOfPage = "10";
        numeroNotificheButton = driver.findElement(By.id("rows-per-page"));
        if (numeroNotificheButton.getText().equals(defaultNumberOfPage)) {
            logger.info("numero di default delle notifiche visualizzate corretto");
        } else {
            logger.error("numero di default delle notifiche visualizzate non corretto");
            Assertions.fail("numero di default delle notifiche visualizzate non corretto");
        }
    }

    public boolean isFiltraButtonDisabled() {
        inputsError = driver.findElements(By.xpath("//input[@aria-invalid='true']"));
        filtraNotificaButtonMittente = driver.findElement(By.id("filter-button"));
        return !inputsError.isEmpty() || filtraNotificaButtonMittente.getAttribute("disabled") != null;
    }

    public boolean controlloDateErrate() {
        boolean isDateErrate = false;
        inputsError = driver.findElements(By.xpath("//input[@aria-invalid='true']"));
        for (WebElement input : inputsError) {
            if (input.getAttribute("id").equals("startDate") || input.getAttribute("id").equals("endDate")) {
                isDateErrate = true;
                break;
            }
        }
        return isDateErrate;
    }

    public boolean controlloEsistenzaStato() {
        statoNotificaField = getWebDriverWait(10)
                .withMessage("Il campo 'Stato notifica' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(By.id("status")));
        statoNotificaField.click();

        try {
            By opzioniMenu = By.xpath("//li[@data-value]");

            getWebDriverWait(10)
                    .withMessage("Il menu a tendina dello stato notifica del filtro non è visibile")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(opzioniMenu));

            List<WebElement> statiNotifica = driver.findElements(opzioniMenu);

            Set<String> testiStati = new HashSet<>();
            for (WebElement stato : statiNotifica) {
                String testo = stato.getText().trim();
                if (testo.isEmpty()) {
                    logger.error("Trovata un'opzione con testo vuoto");
                    return false;
                }
                if (!testiStati.add(testo)) {
                    logger.error("Stato duplicato trovato: " + testo);
                    return false;
                }
            }
            logger.info("Tutti gli stati del menu a tendina sono validi e unici");
            return true;

        } catch (TimeoutException e) {
            Assertions.fail("Stato notifica NON trovata con errore: " + e.getMessage());
            return false;
        } finally {
            this.element(By.id("menu-status")).click(); // chiude il menu
        }
    }

    public void clickPagina(int pagina) {
        String paginaString = "page" + pagina;
        getWebDriverWait(30).withMessage("Il bottone pagina " + pagina + " non è cliccabile")
                .until(elementToBeClickable(driver.findElement(By.id(paginaString))));
        WebElement paginaBy = driver.findElement(By.id(paginaString));
        js().executeScript("arguments[0].scrollIntoView(true);", paginaBy);
        paginaBy.click();
        logger.info("Bottone pagina " + pagina + " cliccato correttamente");
    }

    public void checkPaginaNotificheDelegante(String nomeDelegante) {
        String idNotificationTitlePage = "Le notifiche di " + nomeDelegante + "-page";
        try {
            getWebDriverWait(20).withMessage("il titolo della pagina non é caricato").until(ExpectedConditions.visibilityOf(driver.findElement(By.id(idNotificationTitlePage))));
            getWebDriverWait(25).withMessage("tabella notifiche non caricata").until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notifications-table"))));

            logger.info("Pagina notifiche delegante caricata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Pagina notifiche delegante non caricata con errore: " + e.getMessage());
        }
    }


    public void verificaPresenzaStato(String stato) {
        getWebDriverWait(32).withMessage("Lo stato " + stato + " non è presente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='itemStatus']//span[contains(text(),'" + stato + "')]"))));
        logger.info("Stato {} presente", stato);
    }

    public void verificaNotificaCreata() {
        String notificationRequestId = "";
        //netWorkInfos = getNetWorkInfos();
        for (NetWorkInfo netWorkInfo : webDriverManager.getNetworkInfo()) {
            if (netWorkInfo.getRequestUrl().contains("bff/v1/notifications/sent") && netWorkInfo.getRequestMethod().equals("POST")) {
                if (netWorkInfo.getResponseStatus().equals("202") && !netWorkInfo.getResponseBody().isEmpty()) {
                    notificationRequestId = netWorkInfo.getResponseBody().split("\"notificationRequestId\":\"")[1].split("\"")[0];
                    logger.info("NotificationRequestId: {}", notificationRequestId);
                    break;
                }
            }
        }
        Assertions.assertFalse(notificationRequestId.isEmpty(), "NotificationRequestId non trovato, il codice della risposta al url bff/v1/notifications/sent è diverso di 202 ");
        LinkedTreeMap<String, Object> notificationData;
        String notificationStatus;
        String notificationIUN;
        int maximumRetry = 0;
        do {
            Assertions.assertTrue(maximumRetry <= 8, "La notifica risulta ancora in stato WAITING dopo 8 tentativi");

            notificationData = getRestNotificationParam().getNotificationStatus(notificationRequestId);
            notificationStatus = notificationData.get("notificationRequestStatus").toString();
            if (notificationStatus.equals("ACCEPTED")) {
                notificationIUN = notificationData.get("iun").toString();
                getNotificationSingletonParam().setScenarioIun(hooksNew.getScenario(), notificationIUN);
                return;
            } else {
                webTool.waitTime(90);
                logger.info("Tentativo n.{} - Stato notifica: {}", maximumRetry, notificationStatus);
                maximumRetry++;
            }
        } while (notificationStatus.equals("WAITING"));
        driver.navigate().refresh();
        logger.info("La notifica è stata creata correttamente");
    }


    public void clickSuNotifica() {

        String iun = getNotificationSingletonParam().getIun(hooksNew.scenario);
        logger.info("iun notifica {}", iun);
        WebElement notification =getWebDriverWait(35)
                .withMessage("notifica non esistente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//tr[.//button[contains(@aria-label,'" + iun + "')]]"))));
        clickRowNotificationIndex(notification);
    }

    public void clickSuNotifica(String iun) {

        logger.info("iun notifica {}", iun);
        WebElement notification = getWebDriverWait(60)
                .withMessage("notifica non esistente")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='notifications-table']//tr[.//button[contains(@aria-label,'" + iun + "')]]"))));
        clickRowNotificationIndex(notification);
    }

    public void checkStatoNotifica(String stato) {
        driver.navigate().refresh();
        webTool.waitTime(10);
        notificationsTableLines = driver.findElements(By.id("notificationsTable.body.row"));
        WebElement notificationLine = notificationsTableLines.get(0);
        WebElement chipStatus = notificationLine.findElement(By.id("status-chip-" + stato));
        getWebDriverWait(10).withMessage("La notifica non ha lo stato " + stato).until(ExpectedConditions.visibilityOf(chipStatus));
    }

    public void selezionaNotificaConStato(String statoNotifica) {
        boolean testSuccess = false;
        notificationsTableLines = driver.findElements(By.id("notificationsTable.body.row"));
        for (int i = 0; i < 8; i++) {
            try {
                WebElement notificationLine = notificationsTableLines.get(0);
                WebElement chipStatus = notificationLine.findElement(By.id("status-chip-" + statoNotifica));
                if (chipStatus != null) {
                    logger.info("La notifica è passata allo stato " + statoNotifica + " e si procede con il test");
                    testSuccess = true;
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.info("Dopo " + i + " tentativi la notifica non è ancora passata allo stato: " + statoNotifica);
            }
            webTool.waitTime(15);
            driver.navigate().refresh();
        }
        if (!testSuccess) {
            Assertions.fail("La notifica non è passata allo stato " + statoNotifica);
        }
    }

    public void pollingSuStatoNotificaPerCompletamento(String statoNotifica) {
        boolean testSuccess = false;
        for (int i = 0; i < 20; i++) {
            try {
                WebElement chipStatus = getWebDriverWait(10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.id(statoNotifica + "-status")));
                if (chipStatus.isDisplayed()) {
                    logger.info("La notifica è passata allo stato " + statoNotifica + " e si procede con il test");
                    driver.navigate().refresh();
                    testSuccess = true;
                    break;
                }
            } catch (TimeoutException | NoSuchElementException  e) {
                logger.info("Dopo " + i + " tentativi la notifica non è ancora passata allo stato: " + statoNotifica);
            }
            webTool.waitTime(15);
            driver.navigate().refresh();
        }
        if (!testSuccess) {
            Assertions.fail("La notifica non è passata allo stato " + statoNotifica);
        }
    }

    public void clickAnnullaNotificaModale() {
        getWebDriverWait(20).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid='modalCloseAndProceedBtnId']"))),
                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='modalCloseAndProceedBtnId']")))));
        WebElement bottoneAnnullaNotificaModale = driver.findElement(By.xpath("//button[@data-testid='modalCloseAndProceedBtnId']"));
        bottoneAnnullaNotificaModale.click();
    }


    public void checkPopUpConfermaAnnullamentoNotifica() {
        getWebDriverWait(10).withMessage("Pop up NON visualizzato").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='alert']/div[text()='La richiesta di annullamento è stata accettata.']"))));

    }

    public void verificaInvioNotificaDiCortesia() {
        getWebDriverWait(10).withMessage("Voce nel dettaglio della notifica non trovata").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(), 'Invio del messaggio di cortesia')]"))));
    }

    public void clickBottoneAnnullaNotifica() {
        getWebDriverWait(30).withMessage("Bottone annulla notifica non visibile e cliccabile").until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-testid='cancelNotificationBtn']"))), ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='cancelNotificationBtn']")))));
        WebElement bottoneAnnullaNotifica = driver.findElement(By.xpath("//button[@data-testid='cancelNotificationBtn']"));
        scrollToElementAndClick(bottoneAnnullaNotifica);
    }


    public void visualizzaTimelineTuttiDestinatari(Map<String, String> destinatari) {
        logger.info("Si clicca vedi piu dettagli");
        webTool.waitTime(10);
        List<WebElement> viewMore = driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"));
        //Equals() method utilizzato per String. Per confrontare int variabile dobbiamo usare ==
        String size = Integer.toString(viewMore.size());
        if (size.equals("2")) {
            viewMore.get(1).click();
        } else {
            viewMore.get(0).click();
        }
//        List<WebElement> destinatarioPF = driver.findElements(By.xpath("//p[contains(text(),'(" + destinatari.get("PF") + ")')]"));
//        logger.info("Lista PF {}", destinatarioPF.toArray());
//        List<WebElement> destinatarioPG = driver.findElements(By.xpath("//p[contains(text(),'(" + destinatari.get("PG") + ")')]"));
//        logger.info("Lista PG {}", destinatarioPG.toArray());
        List<WebElement> destinatarioPF = getWebDriverWait(30)
                .withMessage("Impossibile trovare PF:  "+destinatari.get("PF"))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(String.format("//p[contains(text(),'(%s)')]", destinatari.get("PF")))
        ));
        logger.info("Lista PF: {}", destinatarioPF.toArray());

        List<WebElement> destinatarioPG = getWebDriverWait(30)
                .withMessage("Impossibile trovare PG:  "+destinatari.get("PG"))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(String.format("//p[contains(text(),'(%s)')]", destinatari.get("PG")))
        ));
        logger.info("Lista PG: {}", destinatarioPG.toArray());


        if (destinatarioPF.get(0).isDisplayed() && destinatarioPG.get(0).isDisplayed()) {
            logger.info("Si visualizza  gli eventi relativi a tutti i destinatari");
        } else {
            Assertions.fail("Non si visualizza  gli eventi relativi a tutti i destinatari");
        }

        logger.info("Si visualizza correttamente la timeline relativi a tutti i destinatari");
    }

    public void visualizzaTimeline(String check) {
        webTool.waitTime(10);
        if (StringUtils.isNotEmpty(check)) {
            List<WebElement> viewMore = getWebDriverWait(30).withMessage("Non trovato la scritta Vedi poiu dettagli")
                    .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"))));
            viewMore.get(0).click();
            String size = Integer.toString(viewMore.size());
            if (size.equals("2")) {
                viewMore.get(1).click();
            }

            List<WebElement> findKeyWord = getWebDriverWait(30).withMessage("Non è stato trovato il messaggio nella Timeline: " + check)
                    .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//span[contains(text(),'" + check + "')]"))));

            if (findKeyWord.get(0).isDisplayed()) {
                logger.info("Si visualizza la timeline correttamente");
            } else {
                Assertions.fail("Non si visualizza  la timeline correttamente");
            }
        }
    }

    public void verificaTentativoSuccessivo(String check) {
        List<WebElement> findKeyWord = driver.findElements(By.xpath("//span[contains(text(),'" + check + "')]"));
        logger.info("//span[contains(text(),'" + check + "')]");
        logger.info("findkey " + findKeyWord.size());
        if (findKeyWord.get(0).isDisplayed()) {
            logger.info("Si visualizza la timeline correttamente");
        } else {
            Assertions.fail("Non si visualizza  la timeline correttamente");
        }
    }

    public void verificaDestinatariNonRaggiungibili(Map<String, String> destinatari) {
        logger.info("Si clicca vedi piu dettagli");
        List<WebElement> viewMore = driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"));
        viewMore.get(0).click();
        String size = Integer.toString(viewMore.size());
        if (size.equals("2")) {
            viewMore.get(1).click();
        }
        webTool.waitTime(2);
        WebElement destPF = driver.findElement(By.xpath("//p[contains(text(),'" + destinatari.get("PF") + " è fallito')]"));
        webTool.waitTime(2);
        WebElement destPG = driver.findElement(By.xpath("//p[contains(text(),'" + destinatari.get("PG") + " è fallito')]"));

        if (destPG.isDisplayed() && destPF.isDisplayed()) {
            logger.info("Entrambi destinatari non raggiungibili al primo tentativo");
        } else {
            logger.error("Uno dei destinatari viene raggiunto al primo tentativo");
            Assertions.fail("Uno dei destinatari viene raggiunto al primo tentativo");
        }
    }

    public void verificaNotificheNonDisponibili() {
        getWebDriverWait(5).withMessage("Ci sono risultati disponibili per il filtro di ricerca").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='emptyState']"))));
    }

    public void checkAllegatoScaricabile(String descrizioneAllegato) {
        try {
            getWebDriverWait(10).withMessage("Non esiste il bottone per il download degli allegati, si procede con il test").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(., '" + descrizioneAllegato + "') and @id='document-button']"))));
            logger.error("Non è corretto che il bottone per il download dell'allegato sia visibile");
            Assertions.fail("Non è corretto che il bottone per il download dell'allegato sia visibile");
        } catch (TimeoutException e) {
            logger.info("Non è visibile il bottone per il download dell'allegato: " + e.getMessage());
        }
    }

    public void checkAARScaricabili() {
        getWebDriverWait(10).withMessage("Il bottone per il download degli AAR non è visibile e non è disattivato").until(ExpectedConditions.and(
                ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//button[contains(., 'Avviso di avvenuta ricezione') and @id='document-button']")), "disabled", "true"),
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(., 'Avviso di avvenuta ricezione') and @id='document-button']")))
        ));
    }

    public void checkAttestazioniOpponibiliATerziScaricabili() {

        getWebDriverWait(10).withMessage("Il bottone per il download delle attestazioni opponibili a terzi non è visibile e non è disattivato").until(ExpectedConditions.and(
                ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//button[contains(text(), 'Attestazione opponibile a terzi') and @data-testid='download-legalfact']")), "disabled", "true"),
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(), 'Attestazione opponibile a terzi') and @data-testid='download-legalfact']")))
        ));

    }

    public void checkRicevutePECScaricabili() {

        getWebDriverWait(10).withMessage("Il bottone per scaricare la ricevuta di accettazione PEC non è visibile e non è disattivato").until(ExpectedConditions.and(
                ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//button[contains(., 'Ricevuta di accettazione PEC') and @data-testid='download-legalfact']")), "disabled", "true"),
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(., 'Ricevuta di accettazione PEC') and @data-testid='download-legalfact']")))
        ));
        getWebDriverWait(10).withMessage("Il bottone per scaricare la ricevuta di consegna PEC non è visibile e non è disattivato").until(ExpectedConditions.and(
                ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//button[contains(., 'Ricevuta di consegna PEC') and @data-testid='download-legalfact']")), "disabled", "true"),
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(., 'Ricevuta di consegna PEC') and @data-testid='download-legalfact']")))
        ));

    }

    public void checkMessaggioErroreConCodice(int code) {
        webTool.waitTime(3);
        erroreMessaggio = driver.findElement(By.id("message"));
        switch (code) {
            case 19 -> {
                if (erroreMessaggio.getText().contains("inserito troppe volte un nome")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    Assertions.fail("Non si visualizza il messaggio di errore 19");
                }
            }
            case 20 -> {
                if (erroreMessaggio.getText().contains("richiesto un login con un secondo fattore di autenticazione")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    Assertions.fail("Non si visualizza il messaggio di errore 20");
                }
            }
            case 21 -> {
                if (erroreMessaggio.getText().contains("passato troppo tempo da quando hai iniziato")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    Assertions.fail("Non si visualizza il messaggio di errore 21");
                }
            }
            case 22 -> {
                if (erroreMessaggio.getText().contains("devi acconsentire all’invio di alcuni dati")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    Assertions.fail("Non si visualizza il messaggio di errore 22");
                }
            }
            case 23 -> {
                if (erroreMessaggio.getText().contains("tua identità SPID risulta sospesa o revocata")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    Assertions.fail("Non si visualizza il messaggio di errore 23");
                }
            }
            case 25 -> {
                if (erroreMessaggio.getText().contains("annullato l’operazione di login")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    Assertions.fail("Non si visualizza il messaggio di errore 25");
                }
            }
            case 30 -> {
                if (erroreMessaggio.getText().contains("tipologia di identità SPID che hai usato")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    Assertions.fail("Non si visualizza il messaggio di errore 30");
                }
            }
            case 1001 -> {
                if (erroreMessaggio.getText().contains("non hai l’età minima richiesta per usare")) {
                    logger.info("Si visualizza correttamente il messaggio di errore");
                } else {
                    Assertions.fail("Non si visualizza il messaggio di errore 1001");
                }
            }
        }
    }

    public void clickVediTutti() {
        getWebDriverWait(4).withMessage("Il bottone vedi tutti non cliccabile").until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@data-testid='show-all-attachments']"))));
        WebElement vediTutti = driver.findElement(By.xpath("//button[@data-testid='show-all-attachments']"));
        vediTutti.click();
    }

    public void checkClickDownloadRicevutePEC() {
        getWebDriverWait(10).withMessage("Le ricevute PEC non sono visibili").until(ExpectedConditions.and(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//button[contains(@data-testid, 'download-legalfact') and contains(text(), 'PEC')]"))), ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//button[contains(@data-testid, 'download-legalfact') and contains(text(), 'PEC')]")).get(0)), ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//button[contains(@data-testid, 'download-legalfact') and contains(text(), 'PEC')]")).get(1))));

    }

    public void verificaMittente(String ente) {
        // webTool.waitTime(2);
        getWebDriverWait(10).withMessage("Mittente non trovato").until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.id("row-value-1"))),
                ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("row-value-1")), ente)));
    }

    public void checkBottoneAnnullaNotifica() {
        boolean isDisplayed = false;
        try {
            isDisplayed = driver.findElement(By.xpath("//button[@data-testid='cancelNotificationBtn']")).isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false; // Elemento non trovato
        }
        Assertions.assertFalse(isDisplayed, "Il bottone è visualizzabile");
    }

    private void verificaDestinatario(String tipo, String cf, String messaggioErrore) {
        List<WebElement> destinatario = getWebDriverWait(10)
                .withMessage(messaggioErrore)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'(" + cf + ") all')]")));

        if (!destinatario.isEmpty() && destinatario.get(0).isDisplayed()) {
            logger.info("Si visualizza correttamente il destinatario {} con CF {}", tipo, cf);
        } else {
            Assertions.fail("Non si visualizza il destinatario " + tipo + " con CF " + cf);
        }
    }



    public void clickDelete() {
        WebElement clickDelete = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("action-modal-button")));
        clickDelete.click();
    }

    public void selezionaElimina() {
        WebElement clickElimina = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("button-delete")));
        clickElimina.click();
    }

    public void clickBlocca() {
        WebElement clickBlocca = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("action-modal-button")));
        clickBlocca.click();
    }

    public void selezionaBlocca() {
        WebElement buttontornaApiKey = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("button-block")));
        buttontornaApiKey.click();
    }

    public void clickRuota() {
        WebElement clickRuota = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("action-modal-button")));
        clickRuota.click();
    }

    public void selezionaRuota() {
        WebElement buttontornaApiKey = getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(By.id("button-rotate")));
        buttontornaApiKey.click();
    }

    public void premereTrePuntini() {
        WebElement moreVertIconButton = getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
                "//tr[@aria-rowindex='1']//button[@data-testid='contextMenuButton']"))));
        moreVertIconButton.click();
    }

    public void tornaApiKey() {
        WebElement buttontornaApiKey = getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(By.id("go-to-api-keys")));
        buttontornaApiKey.click();
    }

    public void inserisciNomeApiKey() {
        WebElement nameInputField = getWebDriverWait(30).withMessage("Il Nome Api Key non è presente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']")));
        nameInputField.sendKeys("Name-" + UUID.randomUUID());
        webTool.waitTime(2);
        WebElement buttonContinua = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("continue-button")));
        buttonContinua.click();
        logger.info("buttonContinua.click()");
    }

    public void clickGeneraApiKey() {
        WebElement generateApiKeyButton = getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.id("generate-api-key")));
        generateApiKeyButton.click();
    }

    public void selezionaVoceMenuLaterale(String testo) {

        WebElement element = getWebDriverWait(20)
                .withMessage("Voce menu laterale non trovata: "+testo)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'" + testo + "')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        element.click();
    }

    public void cambiaLinguaFooter(String lingua) {

        WebElement menuLingua = getWebDriverWait(30)
                .withMessage("Menu lingua: '" + lingua + "' non trovato")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='lingua']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuLingua);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuLingua);

        WebElement opzioneLingua = getWebDriverWait(30)
                .withMessage("Opzione  Lingua: '" + lingua + "' non trovato")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'" + lingua + "')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opzioneLingua);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opzioneLingua);
//        opzioneLingua.click();
    }

//    public boolean isTextPresent(String testo) {
//        try {
//            return getWebDriverWait(30)
//                    .withMessage("Il testo '" + testo + "' non è stato trovato sulla pagina entro il tempo previsto")
//                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + testo + "')]"))) != null;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public boolean isTextPresent(String testo) {
        try {
            // Attendi che l'elemento contenente il testo sia visibile
            return getWebDriverWait(30)
                    .withMessage("Il testo '" + testo + "' non è stato trovato sulla pagina entro il tempo previsto")
                    .until(ExpectedConditions.textToBePresentInElementLocated(
                            By.xpath("//*"), testo));

        } catch (TimeoutException e) {
            logger.error("Timeout durante la ricerca del testo '" + testo + "': " + e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Errore durante la ricerca del testo '" + testo + "': " + e.getMessage());
            return false;
        }
    }

    public void verificaClickFooterPrivacyOrTerminiCondizione(String privacy) {
        try {
            WebElement informativaPrivacyOrTerminiCondizioneLink = getWebDriverWait(10).withMessage("Link '"+privacy+"' non trovato o non visibile.").until(ExpectedConditions.visibilityOfElementLocated(By.linkText(privacy)));
            informativaPrivacyOrTerminiCondizioneLink.click();
            webTool.waitTime(1);
        } catch (TimeoutException e) {
            logger.info("Link Informativa Privacy  o Termini e Condizione non trovato o non visibile.");
        }
    }

    public void verificaFooterLingua(String lingua) {
//        WebElement linguaElement = driver.findElement(By.cssSelector("button[aria-label='lingua'] span.MuiTypography-root"));
        WebElement linguaElement = getWebDriverWait(20)
                .withMessage("Elemento della lingua nel footer non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[aria-label='lingua'] span.MuiTypography-root")));
        Assertions.assertEquals(linguaElement.getText(), lingua, "La Lingua presente nel footer è diversa da: " + lingua);
    }

    public void verificaCampiVuoti() {
        String oggettoNotifica = driver.findElement(By.id("subject")).getAttribute("value");
        String descrizione = driver.findElement(By.id("abstract")).getAttribute("value");
        String numeroProtocollo = driver.findElement(By.id("paProtocolNumber")).getAttribute("value");
        String codiceTassonomico = driver.findElement(By.id("taxonomyCode")).getAttribute("value");
        WebElement  raccomandata = driver.findElement(By.xpath("//input[@value='AR_REGISTERED_LETTER']"));

        // Asserzioni
        Assertions.assertTrue(oggettoNotifica.isEmpty(), "Il campo Oggetto Notifica non è vuoto");
        Assertions.assertTrue(descrizione.isEmpty(), "Il campo Descrizione non è vuoto");
        Assertions.assertTrue(numeroProtocollo.isEmpty(), "Il campo Numero Protocollo non è vuoto");
        Assertions.assertTrue(codiceTassonomico.isEmpty(), "Il campo Codice Tassonomico non è vuoto");
        Assertions.assertFalse(raccomandata.isSelected(), "Il campo Raccomandata non è vuoto");
    }

    public void verificaBanner(String banner) {
        String xPathBanner = "//div[@data-testid='bannerAdditionalLanguages']//div[@class='MuiAlert-message css-cysxvc']";

        if (StringUtils.isEmpty(banner)){
            //assenza di banner
            boolean nonPresente = getWebDriverWait(10).withMessage("Non si visualizza correttamente il Banner dilinguismo").until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathBanner)));
            Assertions.assertTrue(nonPresente, "Banner Bilinguismo presente");
        }
        else {
            WebElement messaggioBanner = getWebDriverWait(10).withMessage("Non si visualizza correttamente il Banner dilinguismo").until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xPathBanner))));
            String testoMessaggioBanner = messaggioBanner.getText();
            Assertions.assertTrue(testoMessaggioBanner.contains(banner), "Banner bilinguismo non contiene il testo atteso!");
        }
    }

    public void verificaPopUpToastErrore(String verifica) {
        //webTool.waitTime(5);
        WebElement popup = getWebDriverWait(15)
                .withMessage("Impossibile Trovare alert-api-status")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("alert-api-status")));
        Assertions.assertTrue(popup.getText().contains(verifica));

    }

    public void verificaMessaggioToastErrore(String verifica) {
        WebElement toastMessage = getWebDriverWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='alert-api-status']/parent::div[contains(text, '')]")
                ));
        Assertions.assertTrue(toastMessage.getText().contains(verifica));
    }

    public void verificaCodiceToastErrore(String verifica) {
        WebElement toastErrorCode = getWebDriverWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='alert-api-status']/following-sibling::div/div/p[contains(text(),'')][1]")
                ));
        Assertions.assertTrue(toastErrorCode.getText().contains(verifica));
    }

    public String copiaTraceIDToastErrore() {
        WebElement traceIDCopyButton = getWebDriverWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='alert-api-status']/following-sibling::div//a[@role='button']")
                ));
        traceIDCopyButton.click();
        WebElement traceIDValue = driver.findElement(By.xpath("//div[@id='alert-api-status']/following-sibling::div/div/p[contains(text(),'')][2]"));
        return traceIDValue.getAttribute("value");
    }

    public void clickChiudiToastErrore() {
        WebElement closeIcon = getWebDriverWait(10)
                .withMessage("Impossibile chiudere il toast di errore")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='alert-api-status']/parent::div/parent::div//button[@title='Close']")));
        closeIcon.click();

    }

    public void selezioneImpostazioneLingua() {
        WebElement impostazioneLingua = getWebDriverWait(20)
                .withMessage("Impossibile selezioneImpostazioneLingua")
//                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='settingsLangBtn']")));
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='settingsLangBtn']")));
        impostazioneLingua.click();
    }

    public void verificaLinguaSelezionata(String lingua) {
//        if (lingua.equalsIgnoreCase("Italiano")) {
//            WebElement radioIt = driver.findElement(By.xpath("//input[@value='it']"));
//            Assertions.assertTrue(radioIt.isSelected(), "La lingua selezionata non è quella " + lingua);
//        } else {
//            WebElement radioOther = driver.findElement(By.xpath("//input[@value='other']"));
//            Assertions.assertTrue(radioOther.isSelected(), "La lingua selezionata non è quella " + lingua);
//            //verifica che la label ci sia scritto la lingua scelta
//            Assertions.assertEquals(driver.findElement(By.xpath("//div[@id='additionalLang']")).getText(), lingua);
//            webTool.waitTime(5);
//        }
        if (lingua.equalsIgnoreCase("Italiano")) {
            WebElement radioIt = getWebDriverWait(30)
                    .withMessage("Impossibile trovare //input[@value='it'] con la lingua: " + lingua)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='lang' and @value='it']")));
            Assertions.assertTrue(radioIt.isSelected(), "La lingua selezionata non è quella attesa: " + lingua);
        } else {
            WebElement radioOther = getWebDriverWait(30)
                    .withMessage("Impossibile trovare //input[@value='other'] con la lingua: " + lingua)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='lang' and @value='other']")));
            Assertions.assertTrue(radioOther.isSelected(), "La lingua selezionata non è quella attesa: " + lingua);

            // Verifica che la label contenga la lingua scelta
            WebElement additionalLangDiv = getWebDriverWait(30)
                    .withMessage("Impossibile trovare //div[@id='additionalLang'] con la lingua: " + lingua)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='additionalLang']")));
            Assertions.assertEquals(additionalLangDiv.getText(), lingua);
        }
    }

    public void selezioneItalianoAltralingua() {
//        WebElement radioOther = driver.findElement(By.xpath("//input[@value='other']"));
//        radioOther.click();

        WebElement radioOther = getWebDriverWait(20)
                .withMessage("Il radio button 'Altra lingua' non è selezionabile")
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='other']")));
        radioOther.click();
    }

    public void selezioneLingua(String lingua) {
//        WebElement selezionaLingua = driver.findElement(By.xpath("//div[@id='additionalLang']"));
//        selezionaLingua.click();
//
//        WebElement gruppoLingua = driver.findElement(By.xpath("//li[contains(text(),'" + lingua + "')]"));
//        getWebDriverWait(40).until(ExpectedConditions.visibilityOf(gruppoLingua));
//        logger.info("gruppo " + gruppoLingua + " trovato con successo");
//        gruppoLingua.click();
        WebElement selezionaLingua = getWebDriverWait(10)
                .withMessage("Impossibile trovare selezioneLingua //div[@id='additionalLang']")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='additionalLang']")));
        selezionaLingua.click();

        WebElement gruppoLingua = getWebDriverWait(10)
                .withMessage("Impossibile trovare la lista con la lingua: "+lingua)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'" + lingua + "')]")));

        logger.info("Lingua '" + lingua + "' trovata con successo");
        gruppoLingua.click();
    }

    public void selezionareDaImpostazioneLingua(String lingua) {
        selezioneImpostazioneLingua();
        webTool.waitTime(3);
        if (lingua.equalsIgnoreCase("Italiano")) {
//            WebElement radioIt = driver.findElement(By.cssSelector("input[name='lang'][value='it']"));
//            radioIt.click();
//            WebElement radioIt = getWebDriverWait(30)
//                    .withMessage("Impossibile trovare input[name='lang'][value='it']")
//                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='lang'][value='it']")));
////            radioIt.click();
//            js().executeScript("arguments[0].click();", radioIt);
            WebElement radioItaliano = getWebDriverWait(30)
                    .withMessage("Impossibile trovare l'input radio per la lingua italiana")
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//label[span[contains(text(), 'Italiano') and not(contains(text(), 'Italiano e altra lingua'))]]//input[@name='lang']")
                    ));
            radioItaliano.click();
        } else {
            logger.info("Lingua: "+lingua);
            selezioneItalianoAltralingua();
            webTool.waitTime(3);
            selezioneLingua(lingua);
        }

        webTool.waitTime(3);
        //chiusura della schermata tramite la X
//        WebElement closeIcon = driver.findElement(By.xpath("//button[@aria-label='close']"));
//        closeIcon.click();
        WebElement closeIcon = getWebDriverWait(30)
                .withMessage("Impossibile trovare //button[@aria-label='close']")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='close']")));
        closeIcon.click();
        webTool.waitTime(3);
    }

    public void selezionareDaImpostazioneLinguaLaLingua(String lingua) {
        selezioneImpostazioneLingua();
        if(lingua.equalsIgnoreCase("Italiano")) {
            WebElement radioIt = getWebDriverWait(20)
                    .withMessage("Impossibile impostare la lingua su Italiano")
//                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='it']")));
//                    .until(elementToBeClickable(By.xpath("//input[@name='lang' and @value='it']/ancestor::label")));
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='lang' and @value='it']/ancestor::label")));
            radioIt.click();
        }else {
            selezioneItalianoAltralingua();
            if (lingua.equalsIgnoreCase("Francese")) {
                WebElement selezionaLingua = getWebDriverWait(20)
                        .withMessage("Impossibile impostare la lingua su Francese")
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='additionalLang']")));
                selezionaLingua.click();


                WebElement gruppoLingua = getWebDriverWait(20)
                        .withMessage("Impossibile selezionare la lingua da menu a discesa")
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'" + lingua + "')]")));
                getWebDriverWait(40).until(ExpectedConditions.visibilityOf(gruppoLingua));
                gruppoLingua.click();

            }
        }

        webTool.waitTime(3);
        //chiusura della schermata tramite la X
        WebElement closeIcon = getWebDriverWait(10)
                .withMessage("Impossibile Chiudere la finestra laterale")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='close']")));
        closeIcon.click();
        webTool.waitTime(3);
    }

    public void riduciZoomPaginaAl(String size) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='"+size+"%'");

    }

    public void verificaAbilitazioneTastoContinua() {
        try {
             getWebDriverWait(10)
                    .withMessage("Il bottone 'Continua' non è cliccabile entro il timeout")
                    .until(ExpectedConditions.elementToBeClickable(By.id("step-submit")));

            logger.info("Il bottone 'Continua' è  cliccabile.");
        } catch (TimeoutException e) {
            Assertions.fail("Timeout: il bottone 'Continua' non è diventato cliccabile.");
        }

    }

    public void verificaPaginaInviaUnaNuovaNotificaLaSezionePosizioneDebitoria() {
        // verifica lo step 3
        getWebDriverWait(15)
                .withMessage("Lo step 'Posizione debitoria' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".MuiStepLabel-label.Mui-active")
                ));
        // Verifica il form dove poter selezione il tipo di pagamento
        getWebDriverWait(15)
                .withMessage("Header della sezione 'Posizione debitoria' non trovato")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("form[data-testid='debtPositionForm'] h6")
                ));


    }

    public void verificaDisibilitatoTastoContinua() {
        WebElement continuaButton = getWebDriverWait(10).until(ExpectedConditions.presenceOfElementLocated(By.id("step-submit")));
        Assertions.assertFalse(continuaButton.isEnabled(), "Il pulsante 'Continua' NON è disabilitato come previsto");
    }

    public void verificaPresenzaRadionButtonInserimentoAutomaticoAbilitatoDiDefault() {

        List<WebElement> radioLabels = getWebDriverWait(15)
                .withMessage("Impossibile trovare nel metodo verificaPresenzaRadionButtonInserimentoAutomaticoAbilitatoDiDefault")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("label[data-testid='physicalAddressLookupRadio.0']")
                ));

        WebElement automaticoInput = null;
        WebElement automaticoLabel = null;

        for (WebElement label : radioLabels) {
            WebElement input = label.findElement(By.cssSelector("input[type='radio']"));
            if ("NATIONAL_REGISTRY".equals(input.getAttribute("value"))) {
                automaticoInput = input;
                automaticoLabel = label;
                break;
            }
        }

        Assertions.assertNotNull(automaticoInput, "Radio button 'Inserimento automatico' non trovato");
        Assertions.assertTrue(automaticoLabel.isDisplayed(), "Il radio button 'Inserimento automatico' non è visibile");
        Assertions.assertTrue(automaticoInput.isSelected(), "Il radio button 'Inserimento automatico' non è selezionato");

    }

    public void verificaPresenzaRadionButtonIserimentoManualeDisabilitato() {
        List<WebElement> radioLabels = getWebDriverWait(15)
                .withMessage("Impossibile trovare nel metodo verificaPresenzaRadionButtonIserimentoManualeDisabilitato")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("label[data-testid='physicalAddressLookupRadio.0']")
                ));

        WebElement manualeInput = null;
        WebElement manualeLabel = null;

        for (WebElement label : radioLabels) {
            WebElement input = label.findElement(By.cssSelector("input[type='radio']"));
            if ("MANUAL".equals(input.getAttribute("value"))) {
                manualeInput = input;
                manualeLabel = label;
                break;
            }
        }

        Assertions.assertNotNull(manualeInput, "Radio button 'Inserimento manuale' non trovato");
        Assertions.assertTrue(manualeLabel.isDisplayed(), "Il radio button 'Inserimento manuale' non è visibile");


    }


    public void verificaAssenzaRadionButtonIserimentoAutomatico() {
            try {
                List<WebElement> radioLabels = getWebDriverWait(15)
                        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.cssSelector("label[data-testid='physicalAddressLookupRadio.0']")
                        ));
                for (WebElement label : radioLabels) {
                    WebElement input = label.findElement(By.cssSelector("input[type='radio']"));
                    if ("NATIONAL_REGISTRY".equals(input.getAttribute("value"))) {
                        Assertions.fail("Il radio button 'Inserimento automatico' è presente, ma non dovrebbe esserlo.");
                    }
                }
            } catch (Exception e) {
                // Se il radio button non è trovato o non è cliccabile, non fa nulla
                logger.info("Il radio button in verificaAssenzaRadionButtonIserimentoAutomatico  'Inserimento Automatico' non è presente, si passa oltre.");
            }
    }

    public void verificaAssenzaRadionButtonIserimentoManuale() {

        selezionaRadionButtonInserimentoManualeSeEsiste("0");

    }


    public void selezionaRadionButtonInserimentoManualeSeEsiste(String posizione) {

        //posizione 1...n si vuole aggiungere un destinatario
        try {

            List<WebElement> radioLabels = getWebDriverWait(15)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.cssSelector("label[data-testid='physicalAddressLookupRadio."+posizione+"']")
                    ));

            WebElement manualeInput = null;

            for (WebElement label : radioLabels) {
                WebElement input = label.findElement(By.cssSelector("input[type='radio']"));
                if ("MANUAL".equals(input.getAttribute("value"))) {
                    manualeInput = input;
                    break;
                }
            }
            manualeInput.click();
        } catch (Exception e) {
           logger.info("Il radio button in PiattaformaNotifichePage metodo selezionaRadionButtonInserimentoManuale  'Inserimento manuale' non è presente, si passa oltre.");
        }
    }


    public void verificaBannerAttivoEInserimentoManualeSelezionato() {

        WebElement radioManuale = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("input[type='radio'][value='MANUAL']")));

        Assertions.assertTrue(radioManuale.isSelected(), "Il radio button 'Inserimento manuale' NON è selezionato");

        WebElement radioAutomatico = driver.findElement(
                By.cssSelector("input[type='radio'][value='NATIONAL_REGISTRY']"));

        Assertions.assertFalse(radioAutomatico.isEnabled(), "Il radio 'Inserimento automatico' NON è disabilitato");


        // 3. Verifica che il messaggio di errore sia presente
        WebElement alertMessaggio = driver.findElement(
                By.cssSelector("[data-testid='alert-physicalAddressLookupDown'] .MuiAlert-message"));

        Assertions.assertTrue(alertMessaggio.isDisplayed(),
                "Il messaggio di alert per l'indirizzo manuale NON è visibile");

        logger.info("Messaggio di alert correttamente visualizzato e coerente con quello atteso");

    }

    public int getPageMeseCorrente() {
        int meseCorrente = LocalDate.now().getMonthValue();
        return switch (meseCorrente) {
            case 1, 2, 3 -> 17;
            case 4, 5, 6 -> 18;
            case 7, 8, 9 -> 19;
            case 10, 11, 12 -> 20;
            default -> throw new IllegalStateException("Unexpected value: " + meseCorrente);
        };
    }
}