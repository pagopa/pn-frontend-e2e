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

public class PiattaformaNotifichePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(PiattaformaNotifichePage.class);


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
            WebElement cfTextField = getWebDriverWait(10)
                    .withMessage("Il campo Codice Fiscale non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipientId")));

            cfTextField.click();
            cfTextField.clear(); // buona pratica: svuota sempre il campo prima
            cfTextField.sendKeys(codiceFiscale);

            // Attendi che il valore sia effettivamente inserito
            getWebDriverWait(3)
                    .withMessage("Il valore del Codice Fiscale non corrisponde")
                    .until(ExpectedConditions.attributeToBe(cfTextField, "value", codiceFiscale));

            logger.info("Codice Fiscale inserito correttamente: {}", codiceFiscale);

        } catch (TimeoutException e) {
            Assertions.fail("Codice Fiscale NON inserito entro il tempo limite: " + e.getMessage());
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
        WebElement filtraNotificaButton = getWebDriverWait(20)
                .withMessage("Il filtro non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("filter-notifications-button")));
        filtraNotificaButton.click();
    }

    public int getListaCf(String cfInserito) {
        try {
            attesaCaricamentoPagina();

            By locator = By.xpath("//p[contains(text(),'" + cfInserito + "')]");
            List<WebElement> cfListBy = getWebDriverWait(30)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

            logger.info("Codici fiscali trovati correttamente: " + cfListBy.size());
            return cfListBy.size();

        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("Codici fiscali non trovati: " + e.getMessage());
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
        js().executeScript("window.scrollBy(0, document.body.scrollHeight)");
        By page2ButtonLocator = By.id("page2");
        try {
            WebElement page2Button = getWebDriverWait(10)
                    .withMessage("Il bottone pagina 2 non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(page2ButtonLocator));

            logger.info("Bottone pagina 2 trovato");
            page2Button.click();
            return true;

        } catch (TimeoutException e) {
            logger.error("Bottone pagina 2 non trovato: {}", e.getMessage());
            return false;
        }
    }

    public void inserimentoCodiceIUN(String codiceIUN) {
        WebElement iunField = getWebDriverWait(100)
                .withMessage("Il campo per l'inserimento del codice IUN non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("iunMatch")));
        iunField.click();
        iunField.clear();
        iunField.sendKeys(codiceIUN);

    }

    public boolean verificaCodiceIUN(String codiceIUNInserito) {
        WebElement codiceIUN = getCodiceIUN(codiceIUNInserito);
        return codiceIUN != null;
    }

    private WebElement getCodiceIUN(String codiceIUNInserito) {
        By codiceIUNLocator = By.xpath("//button[contains(text(),'" + codiceIUNInserito + "')]");
        try {
            return getWebDriverWait(30)
                    .withMessage("Il codice IUN: " + codiceIUNInserito + " non è presente")
                    .until(ExpectedConditions.visibilityOfElementLocated(codiceIUNLocator));
        } catch (TimeoutException e) {
            logger.warn("Codice IUN non trovato entro 30s: {}", codiceIUNInserito);
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
        } else {
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
        } else {
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
        } else {
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

        logger.info("DATA INIZIO FIELD: {}", dataInizioField.getAttribute("value"));

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

            logger.info("DATA FINE FIELD: {}", dataFineField.getAttribute("value"));

            getWebDriverWait(3).until(ExpectedConditions.attributeToBe(dataFineField, "value", a));
        } catch (ElementClickInterceptedException e) {
            logger.error("Non è possibile settare una data Fine precedente rispetto alla data Inizio: {}", e.getMessage());
            result = false;
        }
        return result;
    }

    public int getListDate() {
        attesaCaricamentoPagina();
        By dateLocator = By.xpath("//tr[@id='notificationsTable.body.row']");
        List<WebElement> dataListBy = getWebDriverWait(30)
                .withMessage("Nessuna data trovata")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dateLocator));

        logger.info("Date trovate correttamente: {}", dataListBy.size());
        return dataListBy.size();
    }

    public void selezionareStatoNotifica(String statoInserito) {
        // Apro il menu a tendina
        WebElement statoNotificaField = getWebDriverWait(10)
                .until(ExpectedConditions.elementToBeClickable(By.id("status")));
        statoNotificaField.click();

        By statoNotificaLocator = By.xpath("//li[contains(@data-value,'" + statoInserito + "')]");
        try {
            WebElement statoNotificaBy = getWebDriverWait(30)
                    .withMessage("Stato notifica '" + statoInserito + "' non disponibile")
                    .until(ExpectedConditions.elementToBeClickable(statoNotificaLocator));

            js().executeScript("arguments[0].scrollIntoView(true);", statoNotificaBy);
            js().executeScript("arguments[0].click();", statoNotificaBy);

            logger.info("Stato notifica '{}' selezionato correttamente", statoInserito);
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
        WebElement statoNotificaBy = getWebDriverWait(30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'status-chip-')]")));

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
            logger.info("CALENDAR: {}", calendar);
            int index = calendar.get(Calendar.HOUR_OF_DAY);
            logger.info("index NOTIFICHE: {}", index);

            logger.info("Scenario : {}", hooksNew.getScenario());

            if (notifiche != null) {
                int randomNumber = 0;
                if (notifiche.size() > 1) {
                    randomNumber = ThreadLocalRandom.current().nextInt(0, notifiche.size() - 1);
                }

                logger.info("ELENCO NOTIFICHE : {}", notifiche.size());
                logger.info("NOTIFICA SELEZIONATA: {}", randomNumber);
                clickRowNotificationIndex(notifiche.get(randomNumber));
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


            logger.info("Scenario: {}", hooksNew.getScenario());

            if (notifiche != null) {

                int randomNumber = 0;
                if (notifiche.size() > 1) {
                    randomNumber = ThreadLocalRandom.current().nextInt(0, notifiche.size() - 1);
                }

                Calendar calendar = GregorianCalendar.getInstance();
                int index = calendar.get(Calendar.HOUR_OF_DAY) + rows;
                logger.info("HOUR...: {}", index);
                logger.info("SIZE ROWS TABLE...: {}", notifiche.size());
                logger.info("ROWS TABLE...: {}", randomNumber);
                logger.info("ROWS SELEZIONATA: {}", randomNumber);

                clickRowNotificationIndex(notifiche.get(randomNumber));
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
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("tr[id='notificationsTable.body.row']")));

            for (WebElement row : notificationsTables) {
                WebElement statusChip = getWebDriverWait(10)
                        .withMessage("Impossibile la colonna con lo stato: " + stato)
                        .until(
                                ExpectedConditions.visibilityOf(row.findElement(By.cssSelector("div[id^='status-chip-']")))
                        );
                if (statusChip.getText().trim().equals(stato)) {

                    WebElement vediDettaglioButton = getWebDriverWait(10)
                            .withMessage("Impossibile dettagli con lo stato: " + stato)
                            .until(ExpectedConditions.elementToBeClickable(row.findElement(By.cssSelector("button[data-testid='goToNotificationDetail']"))));
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
        logger.info("La posizione della notifica è: {}", posizioneNotifica);

        if (posizioneNotifica != -1) {
            By codiciIUNLocator = By.xpath("//td[contains(@class,'MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1cpwezh')]");

            List<WebElement> codiciIUNList = getWebDriverWait(20)
                    .withMessage("Nessun codice IUN trovato in tabella")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(codiciIUNLocator));

            if (posizioneNotifica < codiciIUNList.size()) {
                codiceIUN = codiciIUNList.get(posizioneNotifica).getText();
                logger.info("Codice IUN trovato: {}", codiceIUN);
            } else {
                logger.warn("Posizione notifica {} fuori dai limiti, lista disponibile: {}", posizioneNotifica, codiciIUNList.size());
            }
        }

        return codiceIUN;
    }


    public int verificaEsistenzaNotifica(String oggettoDellaNotifica, String statoNotifica) {

        List<WebElement> listaOggetti = ricercaListaOggetti();
        List<WebElement> listaStati = ricercaListaStati();

        if (listaOggetti != null && listaStati != null) {

            for (int i = 0; i < listaOggetti.size(); i++) {
                logger.info("ciclo for di verifica esistenza");
                logger.info("{} - {} ", listaOggetti.get(i).getText(), oggettoDellaNotifica);
                logger.info("{} - {} ", listaStati.get(i).getText(), statoNotifica);
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
        } catch (TimeoutException e) {
            logger.info("lista oggetti ancora non presenti");
            return null;
        }
    }

    public List<WebElement> ricercaListaStati() {
        By statiLocator = By.xpath("//span[contains(@class,'MuiChip-label MuiChip-labelMedium css-11cvqrr')]");
        try {
            List<WebElement> listaStati = getWebDriverWait(10)
                    .withMessage("La lista stati non è visibile entro il timeout")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(statiLocator));
            logger.info("Lista stati trovata, elementi: {}", listaStati.size());
            return listaStati;
        } catch (TimeoutException e) {
            logger.warn("Lista stati non trovata entro il timeout");
            return Collections.emptyList();
        }
    }

    public void selezionareLaVoceApiKey() {
        By apiKeyLocator = By.id("side-item-API Key");
        try {
            WebElement apiKeyButton = getWebDriverWait(30)
                    .withMessage("La voce API Key non è cliccabile")
                    .until(ExpectedConditions.elementToBeClickable(apiKeyLocator));
            js().executeScript("arguments[0].click()", apiKeyButton);
            logger.info("Voce API Key cliccata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Voce API Key NON cliccata con errore: " + e.getMessage());
        }
    }

    public String conversioneFormatoDate(String date) {
        String[] dateInserita = date.split("-");
        return dateInserita[2] + "/" + dateInserita[1] + "/" + dateInserita[0];
    }

    public int controlloNumeroRisultatiDate() {
        attesaCaricamentoPagina();
        By dateLocator = By.xpath("//*[@id='notificationsTable.body.row']/td[1]");
        List<WebElement> dataListBy = getWebDriverWait(30)
                .withMessage("Nessuna data trovata nella tabella notifiche")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dateLocator));
        logger.info("Date trovate correttamente: {}", dataListBy.size());
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
        By locator = By.id("call-to-action-first");
        try {
            getWebDriverWait(30)
                    .withMessage("Elemento con id='call-to-action-first' non visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Messaggio visualizzato correttamente");
            return true;
        } catch (TimeoutException e) {
            logger.warn("Messaggio NON visualizzato entro il timeout");
            return false;
        }
    }

    public void siVisualizzaCorrettamenteIlCFField() {
        By locator = By.id("recipientId");
        try {
            getWebDriverWait(30)
                    .withMessage("Il campo di codice fiscale non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Il campo di codice fiscale si visualizza correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Il campo di codice fiscale NON si visualizza correttamente: " + e.getMessage());
        }
    }

    public void siVisualizzaCorrettamenteIlCodiceIUNField() {
        WebElement iunField = getWebDriverWait(30)
                .withMessage("Il campo codice IUN non è visibile dopo 30 secondi")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("iunMatch")));

        // Verifiche aggiuntive per assicurarsi che il campo sia correttamente visualizzato
        getWebDriverWait(5)
                .withMessage("Il campo IUN non è interagibile")
                .until(ExpectedConditions.elementToBeClickable(iunField));
    }

    public void siVisualizzaCorrettamenteLoStatoField() {
        By statusLocator = By.id("status");
        WebElement statusField = getWebDriverWait(30)
                .withMessage("Il campo 'status' non è visibile dopo 30 secondi")
                .until(ExpectedConditions.visibilityOfElementLocated(statusLocator));
        Assertions.assertNotNull(statusField, "Il campo dello stato NON si visualizza correttamente");
        logger.info("Il campo dello stato si visualizza correttamente");
    }


    public void siVisualizzaCorrettamenteLaDataInzioField() {
        getWebDriverWait(30)
                .withMessage("Il campo 'data di inizio' non è visibile dopo 30 secondi")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));

        logger.info("Il campo della data di inizio si visualizza correttamente");
    }

    public void siVisualizzaCorrettamenteLaDataFineField() {
        WebElement endDateField = getWebDriverWait(30)
                .withMessage("Il campo 'data di fine' non è visibile dopo 30 secondi")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));

        Assertions.assertNotNull(endDateField, "Il campo della data di fine NON si visualizza correttamente");
        logger.info("Il campo della data di fine si visualizza correttamente");
    }

    public boolean verificaEsistenzaCFNotifiche() {
        List<WebElement> cfFields = getWebDriverWait(30)
                .withMessage("I campi CF nella tabella delle notifiche non sono visibili")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='notificationsTable.body.row']/td[2]"));
                    return elements.isEmpty() ? null : elements; // aspetta fino a che almeno uno sia presente
                });

        return cfFields != null && !cfFields.isEmpty();
    }

    public boolean verificaEsistenzaCodiceIUNNotifiche() {
        List<WebElement> codiciIUN = getWebDriverWait(30)
                .withMessage("I campi Codice IUN nella tabella delle notifiche non sono visibili")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='notificationsTable.body.row']/td[4]"));
                    return elements.isEmpty() ? null : elements; // aspetta finché almeno uno sia presente
                });

        return codiciIUN != null && !codiciIUN.isEmpty();
    }

    public boolean verificaEsistenzaGruppoNotifiche() {
        List<WebElement> gruppi = getWebDriverWait(30)
                .withMessage("I campi Gruppo nella tabella delle notifiche non sono visibili")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='notificationsTable.body.row']/td[5]"));
                    return elements.isEmpty() ? null : elements; // aspetta finché almeno uno sia presente
                });

        return gruppi != null && !gruppi.isEmpty();
    }

    public boolean verificaEsistenzaStatoNotifiche() {
        List<WebElement> stati = getWebDriverWait(30)
                .withMessage("I campi Stato nella tabella delle notifiche non sono visibili")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='notificationsTable.body.row']/td[6]"));
                    return elements.isEmpty() ? null : elements; // attende finché almeno uno sia presente
                });

        return stati != null && !stati.isEmpty();
    }

    public int getNRighe() {
        List<WebElement> righe = getWebDriverWait(30)
                .withMessage("Le righe della tabella delle notifiche non sono visibili")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//tr[@id='notificationsTable.body.row']"));
                    return elements.isEmpty() ? null : elements; // attende finché almeno una riga è presente
                });

        return righe != null ? righe.size() : 0;
    }

    public String numeroNotifiche() {
        WebElement nRigheBy = getWebDriverWait(30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("rows-per-page")));

        return nRigheBy.getText();
    }

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
        numeroNotificheButton = getWebDriverWait(30)
                .withMessage("Il bottone che indica il numero delle notifiche non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("rows-per-page")));

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
                if (element(By.id("page15")).isDisplayed()) {
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
        while (element(By.id("next")).isEnabled() && i <= pagina) {
            element(By.id("next")).click();
            webTool.waitTime(2);
            try {
                if (element(By.id("paginaString")).isDisplayed()) {
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.error("{} non visualizzata", paginaString);
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
        numeroNotificheButton = getWebDriverWait(10)
                .withMessage("Il pulsante 'righe per pagina' non è presente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("rows-per-page")));

        vaiInFondoAllaPagina();
        webTool.waitTime(1);
        numeroNotificheButton.click();

        numberElement = getWebDriverWait(10)
                .withMessage("Il pulsante '50' per assegnare il numero di notifiche per pagina non è presente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("pageSize-50")));

        webTool.waitTime(1);
        numberElement.click();
    }

    public void clickContinuaDisabled() {
        continuaButtonDisabled = getWebDriverWait(10)
                .withMessage("Il pulsante 'Continua' non è presente o non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("step-submit")));

        Assertions.assertFalse(continuaButtonDisabled.isEnabled(), "Il pulsante 'Continua' non dovrebbe essere abilitato");
    }

    public void inserimentoOggettoNotificaErrato(String oggettoDellaNotifica) {
        oggettoDellaNotificaTextField = getWebDriverWait(10)
                .withMessage("Il campo 'Oggetto della notifica' non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("subject")));

        scrollToElementClickAndInsertText(oggettoDellaNotificaTextField, oggettoDellaNotifica);
    }

    public boolean errorMessage() {
        try {
            errorMessage = getWebDriverWait(10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("subject-helper-text")));
            return errorMessage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickIndietroButton() {
        indietroButton = getWebDriverWait(30)
                .withMessage("Il bottone indietro non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("breadcrumb-indietro-button")));

        indietroButton.click();
    }

    public void vuoiUscirePopUp() {
        try {
            getWebDriverWait(30)
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h2[contains(text(),'Vuoi uscire?')]")
                    ));
            logger.info("Si visualizza il pop up 'Vuoi uscire'");
        } catch (TimeoutException e) {
            Assertions.fail("Non si visualizza il pop up 'Vuoi uscire' con errore: " + e.getMessage());
        }
    }

    public void clickSuEsci() {
        esciButton = getWebDriverWait(30)
                .withMessage("Il bottone 'Esci' non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("button-exit")));

        esciButton.click();
    }

    public boolean estensioneSbagliataErrore() {
        try {
            estenzioneSbagliataMessage = getWebDriverWait(10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("file-upload-error")));
            return estenzioneSbagliataMessage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<String> getCodiceIunPresenti() {
        List<WebElement> righeTabella = getWebDriverWait(10)
                .withMessage("Le celle Codice IUN nella tabella delle notifiche non sono visibili")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='notificationsTable.body.row']/td[4]"));
                    return elements.isEmpty() ? null : elements; // attende finché almeno una cella è presente
                });

        List<String> listaCodici = new ArrayList<>();
        if (righeTabella != null) {
            for (WebElement riga : righeTabella) {
                listaCodici.add(riga.getText());
            }
        }
        return listaCodici;
    }

    public List<String> getCodiceIunPresentiPF() {
        List<WebElement> righeTabella = getWebDriverWait(10)
                .withMessage("Le celle Codice IUN PF nella tabella delle notifiche non sono visibili")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='notificationsTable.body.row']/td[4]"));
                    return elements.isEmpty() ? null : elements; // attende finché almeno una cella è presente
                });

        List<String> listaCodici = new ArrayList<>();
        if (righeTabella != null) {
            for (WebElement riga : righeTabella) {
                listaCodici.add(riga.getText());
            }
        }
        return listaCodici;
    }

    public String getCodiceIunInserito() {
        codiceIUNTextField = getWebDriverWait(10)
                .withMessage("Impossibile trovare iunMatch nel metodo getCodiceIunInserito")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("iunMatch")));

        return codiceIUNTextField.getText();
    }

    public List<String> getCodiceIunPersonaGiuridica() {
        List<WebElement> righeTabella = getWebDriverWait(10)
                .withMessage("Le celle Codice IUN PG nella tabella delle notifiche non sono visibili")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='notificationsTable.body.row']/td[4]"));
                    return elements.isEmpty() ? null : elements; // attende finché almeno una cella è presente
                });

        List<String> listaCodici = new ArrayList<>();
        if (righeTabella != null) {
            for (WebElement riga : righeTabella) {
                listaCodici.add(riga.getText());
            }
        }
        return listaCodici;
    }

    public boolean controlloEsistenzaMessagioErroreCF() {
        try {
            WebElement errorMessageBy = getWebDriverWait(30)
                    .withMessage("Messaggio di errore 'Inserisci il codice per intero' non trovato")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("recipientId-helper-text")));
            return errorMessageBy.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean verificaBottoneFiltraDisabilitato() {
        try {
            filtraNotificaButtonMittente = getWebDriverWait(30)
                    .withMessage("Bottone 'Filtra' non è visibile")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("filter-button")));

            return filtraNotificaButtonMittente.getAttribute("disabled") != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean controlloEsistenzaMessagioErroreIUN() {
        try {
            WebElement errorMessageBy = getWebDriverWait(30)
                    .withMessage("Messaggio di errore 'Inserisci un codice IUN valido' non trovato")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("iunMatch-helper-text")));
            return errorMessageBy.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
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
        numeroNotificheButton = getWebDriverWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("rows-per-page")));

        if (numeroNotificheButton.getText().equals(defaultNumberOfPage)) {
            logger.info("Numero di default delle notifiche visualizzate corretto");
        } else {
            logger.error("Numero di default delle notifiche visualizzate non corretto");
            Assertions.fail("Numero di default delle notifiche visualizzate non corretto");
        }
    }

    public boolean isFiltraButtonDisabled() {
        // Trova eventuali input con errore
        List<WebElement> inputsError = getWebDriverWait(10)
                .withMessage("Non è stato possibile recuperare gli input con errore")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//input[@aria-invalid='true']"));
                    return elements.isEmpty() ? Collections.emptyList() : elements;
                });
        // Recupera il pulsante "Filtra"
        filtraNotificaButtonMittente = getWebDriverWait(10)
                .withMessage("Il pulsante filtra non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("filter-button")));
        // Il pulsante è considerato disabilitato se ci sono input con errore o se ha l'attributo disabled
        return !inputsError.isEmpty() || Boolean.parseBoolean(filtraNotificaButtonMittente.getAttribute("disabled"));
    }

    public boolean controlloDateErrate() {
        List<WebElement> inputsError = getWebDriverWait(10)
                .withMessage("Non sono stati trovati input con aria-invalid='true'")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//input[@aria-invalid='true']"));
                    return elements.isEmpty() ? Collections.emptyList() : elements;
                });

        return inputsError.stream()
                .map(input -> input.getAttribute("id"))
                .anyMatch(id -> "startDate".equals(id) || "endDate".equals(id));
    }

    public boolean controlloEsistenzaStato() {
        statoNotificaField = getWebDriverWait(10)
                .withMessage("Il campo 'Stato notifica' non è visibile")
                .until(ExpectedConditions.elementToBeClickable(By.id("status")));
        statoNotificaField.click();
        try {
            By opzioniMenu = By.xpath("//li[@data-value]");

            List<WebElement> statiNotifica = getWebDriverWait(10)
                    .withMessage("Il menu a tendina dello stato notifica del filtro non è visibile")
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(opzioniMenu));
            Set<String> testiStati = new HashSet<>();
            for (WebElement stato : statiNotifica) {
                String testo = stato.getText().trim();
                if (testo.isEmpty()) {
                    logger.error("Trovata un'opzione con testo vuoto");
                    return false;
                }
                if (!testiStati.add(testo)) {
                    logger.error("Stato duplicato trovato: {}", testo);
                    return false;
                }
            }
            logger.info("Tutti gli stati del menu a tendina sono validi e unici");
            return true;

        } catch (TimeoutException e) {
            Assertions.fail("Stato notifica NON trovata con errore: " + e.getMessage());
            return false; // opzionale, per compilazione
        } finally {
            try {
                this.element(By.id("menu-status")).click(); // chiude il menu
            } catch (Exception e) {
                logger.warn("Menu stato non chiuso perché non trovato o già chiuso");
            }
        }
    }

    public void clickPagina(int pagina) {
        String paginaString = "page" + pagina;

        WebElement paginaBy = getWebDriverWait(30)
                .withMessage("Il bottone pagina " + pagina + " non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id(paginaString)));

        js().executeScript("arguments[0].scrollIntoView(true);", paginaBy);
        paginaBy.click();
        logger.info("Bottone pagina {} cliccato correttamente", pagina);
    }

    public void checkPaginaNotificheDelegante(String nomeDelegante) {
        String idNotificationTitlePage = "Le notifiche di " + nomeDelegante + "-page";

        try {
            getWebDriverWait(20)
                    .withMessage("Il titolo della pagina non è caricato")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(idNotificationTitlePage)));

            getWebDriverWait(25)
                    .withMessage("Tabella notifiche non caricata")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("notifications-table")));

            logger.info("Pagina notifiche delegante caricata correttamente");
        } catch (TimeoutException e) {
            Assertions.fail("Pagina notifiche delegante non caricata con errore: " + e.getMessage());
        }
    }

    public void verificaPresenzaStato(String stato) {
        String xpathStato = "//div[@data-testid='itemStatus']//span[contains(text(),'" + stato + "')]";

        getWebDriverWait(32)
                .withMessage("Lo stato " + stato + " non è presente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathStato)));

        logger.info("Stato {} presente", stato);
    }

    public void verificaNotificaCreata() {
        String notificationRequestId = "";
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
        logger.info("IUN notifica {}", iun);
        String xpathNotifica = "//table[@id='notifications-table']//tr[.//button[contains(@aria-label,'" + iun + "')]]";
        WebElement notification = getWebDriverWait(35)
                .withMessage("Notifica non esistente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathNotifica)));
        clickRowNotificationIndex(notification);
    }

    public void checkStatoNotifica(String stato) {
        driver.navigate().refresh();
        webTool.waitTime(10);

        By firstRowLocator = By.cssSelector("#notificationsTable.body.row:first-child");
        WebElement notificationLine = getWebDriverWait(10)
                .withMessage("La tabella notifiche non contiene righe")
                .until(ExpectedConditions.visibilityOfElementLocated(firstRowLocator));

        By chipStatusLocator = By.id("status-chip-" + stato);
        getWebDriverWait(10)
                .withMessage("La notifica non ha lo stato '" + stato + "'")
                .until(ExpectedConditions.visibilityOf(notificationLine.findElement(chipStatusLocator)));

        logger.info("La notifica ha correttamente lo stato '{}'", stato);
    }

    public void clickSuNotifica(String iun) {
        logger.info("IUN notifica {}", iun);

        String xpathNotifica = "//table[@id='notifications-table']//tr[.//button[contains(@aria-label,'" + iun + "')]]";

        WebElement notification = getWebDriverWait(60)
                .withMessage("Notifica non esistente")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathNotifica)));
        clickRowNotificationIndex(notification);
    }

    public void selezionaNotificaConStato(String statoNotifica) {
        boolean testSuccess = false;
        int maxTentativi = 8;

        for (int i = 0; i < maxTentativi; i++) {
            try {
                List<WebElement> notificationsTableLines = getWebDriverWait(10)
                        .until(driver -> {
                            List<WebElement> elements = driver.findElements(By.id("notificationsTable.body.row"));
                            return elements.isEmpty() ? null : elements;
                        });

                WebElement chipStatus = getWebDriverWait(10)
                        .withMessage("Chip dello stato " + statoNotifica + " non visibile")
                        .until(driver -> {
                            for (WebElement row : notificationsTableLines) {
                                List<WebElement> chips = row.findElements(By.id("status-chip-" + statoNotifica));
                                if (!chips.isEmpty() && chips.get(0).isDisplayed()) {
                                    return chips.get(0);
                                }
                            }
                            return null;
                        });

                if (chipStatus != null) {
                    logger.info("La notifica è passata allo stato {} e si procede con il test", statoNotifica);
                    testSuccess = true;
                    break;
                }

            } catch (TimeoutException e) {
                logger.info("Tentativo {}: la notifica non è ancora passata allo stato: {}", i + 1, statoNotifica);
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
                if (i > 0) {
                    driver.navigate().refresh();
                }
                WebElement chipStatus = getWebDriverWait(10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.id(statoNotifica + "-status")));
                if (chipStatus.isDisplayed()) {
                    logger.info("La notifica è passata allo stato {} e si procede con il test", statoNotifica);
                    driver.navigate().refresh();
                    testSuccess = true;
                    break;
                }
            } catch (TimeoutException | NoSuchElementException e) {
                logger.info("Dopo {} tentativi la notifica non è ancora passata allo stato: {}", i, statoNotifica);
            }
            webTool.waitTime(15);

        }
        if (!testSuccess) {
            Assertions.fail("La notifica non è passata allo stato " + statoNotifica);
        }
    }

    public void clickAnnullaNotificaModale() {
        By locator = By.xpath("//button[@data-testid='modalCloseAndProceedBtnId']");

        WebElement bottoneAnnullaNotificaModale = getWebDriverWait(20)
                .until(ExpectedConditions.elementToBeClickable(locator));

        bottoneAnnullaNotificaModale.click();
    }

    public void checkPopUpConfermaAnnullamentoNotifica() {
        By locator = By.xpath("//div[@role='alert']/div[text()='La richiesta di annullamento è stata accettata.']");

        getWebDriverWait(10)
                .withMessage("Pop up NON visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void verificaInvioNotificaDiCortesia() {
        By locator = By.xpath("//span[contains(text(), 'Invio del messaggio di cortesia')]");

        getWebDriverWait(10)
                .withMessage("Voce nel dettaglio della notifica non trovata")
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickBottoneAnnullaNotifica() {
        By locator = By.xpath("//button[@data-testid='cancelNotificationBtn']");

        WebElement bottoneAnnullaNotifica = getWebDriverWait(30)
                .withMessage("Bottone annulla notifica non visibile e cliccabile")
                .until(ExpectedConditions.elementToBeClickable(locator));

        scrollToElementAndClick(bottoneAnnullaNotifica);
    }

    public void visualizzaTimelineTuttiDestinatari(Map<String, String> destinatari) {
        logger.info("Si clicca 'vedi più dettagli'");
        webTool.waitTime(10);

        List<WebElement> viewMore = getWebDriverWait(10)
                .withMessage("'Vedi più dettagli' non trovato")
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"));
                    return elements.isEmpty() ? null : elements;
                });

        if (viewMore.size() > 1) {
            viewMore.get(1).click();
        } else if (!viewMore.isEmpty()) {
            viewMore.get(0).click();
        } else {
            Assertions.fail("'Vedi più dettagli' non trovato");
        }

        List<WebElement> destinatarioPF = getWebDriverWait(30)
                .withMessage("Impossibile trovare PF: " + destinatari.get("PF"))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(String.format("//p[contains(text(),'(%s)')]", destinatari.get("PF")))
                ));
        logger.info("Lista PF: {}", destinatarioPF.toArray());

        List<WebElement> destinatarioPG = getWebDriverWait(30)
                .withMessage("Impossibile trovare PG: " + destinatari.get("PG"))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(String.format("//p[contains(text(),'(%s)')]", destinatari.get("PG")))
                ));
        logger.info("Lista PG: {}", destinatarioPG.toArray());

        if (!destinatarioPF.isEmpty() && destinatarioPF.get(0).isDisplayed() &&
                !destinatarioPG.isEmpty() && destinatarioPG.get(0).isDisplayed()) {
            logger.info("Si visualizzano gli eventi relativi a tutti i destinatari");
        } else {
            Assertions.fail("Non si visualizzano gli eventi relativi a tutti i destinatari");
        }

        logger.info("Timeline visualizzata correttamente per tutti i destinatari");
    }

    public void visualizzaTimeline(String check) {
        webTool.waitTime(2); // breve attesa iniziale

        if (StringUtils.isNotEmpty(check)) {
            // Attende fino a che almeno un pulsante 'Vedi più dettagli' sia presente
            List<WebElement> viewMore = getWebDriverWait(30)
                    .withMessage("Non trovato la scritta 'Vedi più dettagli'")
                    .until(driver -> {
                        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"));
                        return elements.isEmpty() ? null : elements;
                    });
            // Scroll e click sul primo pulsante
            js().executeScript("arguments[0].scrollIntoView(true);", viewMore.get(0));
            viewMore.get(0).click();
            // Se ci sono due pulsanti, clicca anche il secondo
            if (viewMore.size() == 2) {
                js().executeScript("arguments[0].scrollIntoView(true);", viewMore.get(1));
                viewMore.get(1).click();
            }
            // Attende la keyword nella timeline
            getWebDriverWait(30)
                    .withMessage("Non è stato trovato il messaggio nella Timeline: " + check)
                    .until(driver -> {
                        List<WebElement> elements = driver.findElements(By.xpath("//span[contains(text(),'" + check + "')]"));
                        return elements.isEmpty() ? null : elements;
                    });

            logger.info("Si visualizza la timeline correttamente per il messaggio '{}'", check);
        }
    }

    public void verificaTentativoSuccessivo(String check) {
        // Attende fino a trovare almeno un elemento con la keyword nella timeline
        List<WebElement> findKeyWord = getWebDriverWait(30)
                .withMessage("Non è stato trovato il messaggio nella Timeline: " + check)
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//span[contains(text(),'" + check + "')]"));
                    return elements.isEmpty() ? null : elements;
                });

        // Scroll e verifica visibilità del primo elemento
        js().executeScript("arguments[0].scrollIntoView(true);", findKeyWord.get(0));

        if (findKeyWord.get(0).isDisplayed()) {
            logger.info("Si visualizza la timeline correttamente per il messaggio '{}'", check);
        } else {
            Assertions.fail("Non si visualizza la timeline correttamente per il messaggio: " + check);
        }
    }

    public void verificaDestinatariNonRaggiungibili(Map<String, String> destinatari) {
        logger.info("Si clicca 'vedi più dettagli'");
        // Attende e recupera i pulsanti 'vedi più dettagli'
        List<WebElement> viewMore = getWebDriverWait(10)
                .until(driver -> {
                    List<WebElement> elements = driver.findElements(By.xpath("//*[@id='more-less-timeline-step']"));
                    return elements.isEmpty() ? null : elements;
                });
        // Click sui pulsanti presenti
        for (WebElement view : viewMore) {
            js().executeScript("arguments[0].scrollIntoView(true);", view);
            view.click();
        }
        webTool.waitTime(2);
        // Attende e verifica destinatari falliti
        WebElement destPF = getWebDriverWait(10)
                .withMessage("Destinatario PF non trovato come non raggiungibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(text(),'" + destinatari.get("PF") + " è fallito')]")));
        WebElement destPG = getWebDriverWait(10)
                .withMessage("Destinatario PG non trovato come non raggiungibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(text(),'" + destinatari.get("PG") + " è fallito')]")));
        if (destPF.isDisplayed() && destPG.isDisplayed()) {
            logger.info("Entrambi destinatari non raggiungibili al primo tentativo");
        } else {
            logger.error("Uno dei destinatari viene raggiunto al primo tentativo");
            Assertions.fail("Uno dei destinatari viene raggiunto al primo tentativo");
        }
    }

    public void verificaNotificheNonDisponibili() {
        By locator = By.xpath("//div[@data-testid='emptyState']");

        getWebDriverWait(5)
                .withMessage("Ci sono risultati disponibili per il filtro di ricerca")
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void checkAllegatoScaricabile(String descrizioneAllegato) {
        By locator = By.xpath("//button[contains(., '" + descrizioneAllegato + "') and @id='document-button']");

        try {
            getWebDriverWait(10)
                    .withMessage("Non esiste il bottone per il download degli allegati, si procede con il test")
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));

            logger.error("Non è corretto che il bottone per il download dell'allegato sia visibile");
            Assertions.fail("Non è corretto che il bottone per il download dell'allegato sia visibile");
        } catch (TimeoutException e) {
            logger.info("Non è visibile il bottone per il download dell'allegato: {}", e.getMessage());
        }
    }

    public void checkAARScaricabili() {
        By locator = By.xpath("//button[contains(., 'Avviso di avvenuta ricezione') and @id='document-button']");

        getWebDriverWait(10).withMessage("Il bottone per il download degli AAR non è visibile o non è disattivato")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(locator),
                        ExpectedConditions.attributeToBe(locator, "disabled", "true")
                ));
    }

    public void checkAttestazioniOpponibiliATerziScaricabili() {
        By locator = By.xpath("//button[contains(text(), 'Attestazione opponibile a terzi') and @data-testid='download-legalfact']");

        getWebDriverWait(10)
                .withMessage("Il bottone per il download delle attestazioni opponibili a terzi non è visibile o non è disattivato")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(locator),
                        ExpectedConditions.attributeToBe(locator, "disabled", "true")
                ));
    }

    public void checkRicevutePECScaricabili() {
        By ricevutaAccettazioneLocator = By.xpath("//button[contains(., 'Ricevuta di accettazione PEC') and @data-testid='download-legalfact']");
        By ricevutaConsegnaLocator = By.xpath("//button[contains(., 'Ricevuta di consegna PEC') and @data-testid='download-legalfact']");

        getWebDriverWait(10)
                .withMessage("Il bottone per scaricare la ricevuta di accettazione PEC non è visibile o non è disattivato")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(ricevutaAccettazioneLocator),
                        ExpectedConditions.attributeToBe(ricevutaAccettazioneLocator, "disabled", "true")
                ));

        getWebDriverWait(10)
                .withMessage("Il bottone per scaricare la ricevuta di consegna PEC non è visibile o non è disattivato")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(ricevutaConsegnaLocator),
                        ExpectedConditions.attributeToBe(ricevutaConsegnaLocator, "disabled", "true")
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
        By locator = By.xpath("//button[@data-testid='show-all-attachments']");

        WebElement vediTutti = getWebDriverWait(4)
                .withMessage("Il bottone 'vedi tutti' non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(locator));

        vediTutti.click();
    }

    public void checkClickDownloadRicevutePEC() {
        List<WebElement> downloadButtons = getWebDriverWait(10)
                .withMessage("Le ricevute PEC non sono visibili")
                .until(driver -> {
                    List<WebElement> buttons = driver.findElements(
                            By.xpath("//button[contains(@data-testid, 'download-legalfact') and contains(text(), 'PEC')]")
                    );
                    return buttons.size() >= 2 ? buttons : null; // attende almeno 2 elementi
                });
        // Verifica che entrambi siano cliccabili
        for (WebElement button : downloadButtons) {
            getWebDriverWait(10)
                    .withMessage("Il bottone non è cliccabile: " + button.getText())
                    .until(ExpectedConditions.elementToBeClickable(button));
        }
        logger.info("I pulsanti di download PEC sono visibili e cliccabili");
    }

    public void verificaMittente(String ente) {
        By locator = By.id("row-value-1");

        getWebDriverWait(10)
                .withMessage("Mittente non trovato")
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(locator),
                        ExpectedConditions.textToBePresentInElementLocated(locator, ente)
                ));
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
        By locator = By.xpath("//tr[@aria-rowindex='1']//button[@data-testid='contextMenuButton']");

        WebElement moreVertIconButton = getWebDriverWait(60)
                .until(ExpectedConditions.elementToBeClickable(locator));
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
                .withMessage("Voce menu laterale non trovata: " + testo)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'side-item-" + testo + "')]//span")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void sullaPaginaGruppiSelezionaVoceMenuLaterale(String testo) {

        WebElement element = getWebDriverWait(20)
                .withMessage("Voce menu laterale non trovata: " + testo)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button']//span[contains(text(),'" + testo + "')]")));
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
    }

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
            WebElement informativaPrivacyOrTerminiCondizioneLink = getWebDriverWait(10).withMessage("Link '" + privacy + "' non trovato o non visibile.").until(ExpectedConditions.visibilityOfElementLocated(By.linkText(privacy)));
            informativaPrivacyOrTerminiCondizioneLink.click();
            webTool.waitTime(1);
        } catch (TimeoutException e) {
            logger.info("Link Informativa Privacy  o Termini e Condizione non trovato o non visibile.");
        }
    }

    public void verificaFooterLingua(String lingua) {
        WebElement linguaElement = getWebDriverWait(20)
                .withMessage("Elemento della lingua nel footer non visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[aria-label='lingua'] span.MuiTypography-root")));
        Assertions.assertEquals(linguaElement.getText(), lingua, "La Lingua presente nel footer è diversa da: " + lingua);
    }

    public void verificaCampiVuoti() {
        // Attendo e recupero i campi
        WebElement oggettoNotificaField = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("subject")));
        WebElement descrizioneField = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("abstract")));
        WebElement numeroProtocolloField = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("paProtocolNumber")));
        WebElement codiceTassonomicoField = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("taxonomyCode")));
        WebElement raccomandata = getWebDriverWait(10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='AR_REGISTERED_LETTER']")));

        // Leggo i valori
        String oggettoNotifica = oggettoNotificaField.getAttribute("value");
        String descrizione = descrizioneField.getAttribute("value");
        String numeroProtocollo = numeroProtocolloField.getAttribute("value");
        String codiceTassonomico = codiceTassonomicoField.getAttribute("value");

        logger.info("Valori letti -> Oggetto: '{}', Descrizione: '{}', Numero Protocollo: '{}', Codice Tassonomico: '{}', Raccomandata selezionata: {}",
                oggettoNotifica, descrizione, numeroProtocollo, codiceTassonomico, raccomandata.isSelected());
        // Asserzioni multiple
        Assertions.assertAll("Verifica campi vuoti",
                () -> Assertions.assertTrue(oggettoNotifica.isEmpty(), "Il campo Oggetto Notifica non è vuoto"),
                () -> Assertions.assertTrue(descrizione.isEmpty(), "Il campo Descrizione non è vuoto"),
                () -> Assertions.assertTrue(numeroProtocollo.isEmpty(), "Il campo Numero Protocollo non è vuoto"),
                () -> Assertions.assertTrue(codiceTassonomico.isEmpty(), "Il campo Codice Tassonomico non è vuoto"),
                () -> Assertions.assertFalse(raccomandata.isSelected(), "Il radio Raccomandata risulta selezionato")
        );
    }

    public void verificaBanner(String banner) {
        By locator = By.xpath("//div[@data-testid='bannerAdditionalLanguages']//div[@class='MuiAlert-message css-cysxvc']");

        if (StringUtils.isEmpty(banner)) {
            // assenza di banner
            boolean nonPresente = getWebDriverWait(10)
                    .withMessage("Non si visualizza correttamente il Banner di linguismo")
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
            Assertions.assertTrue(nonPresente, "Banner Bilinguismo presente");
        } else {
            WebElement messaggioBanner = getWebDriverWait(10)
                    .withMessage("Non si visualizza correttamente il Banner di linguismo")
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            String testoMessaggioBanner = messaggioBanner.getText();
            Assertions.assertTrue(testoMessaggioBanner.contains(banner), "Banner bilinguismo non contiene il testo atteso!");
        }
    }

    public void verificaPopUpToastErrore(String verifica) {
        WebElement popup = getWebDriverWait(15)
                .withMessage("Impossibile Trovare alert-api-status")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("alert-api-status")));
        Assertions.assertTrue(popup.getText().contains(verifica));

    }
    public void verificaPopUpToastErrore() {
        //webTool.waitTime(5);
        WebElement popup = getWebDriverWait(15)
                .withMessage("Impossibile Trovare alert-api-status")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("alert-api-status")));
        Assertions.fail("Presenza Pop- UP -> Errore API rilevato: " + popup.getText());

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
        // Attende che il bottone per copiare il trace ID sia visibile e cliccabile
        WebElement traceIDCopyButton = getWebDriverWait(10)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Copia informazioni errore']")
                ));
        traceIDCopyButton.click();
        // Attende che il testo del TraceID sia visibile
        WebElement traceIDValue = getWebDriverWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='alert-api-status']/following-sibling::div/div/p[2]")
                ));
        // Usa getText() invece di getAttribute("value")
        return traceIDValue.getText();
    }

    public void clickChiudiToastErrore() {
        WebElement closeIcon = getWebDriverWait(10)
                .withMessage("Impossibile chiudere il toast di errore")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='snackBarCloseButton']")));
        closeIcon.click();
    }

    public void selezioneImpostazioneLingua() {
        WebElement impostazioneLingua = getWebDriverWait(20)
                .withMessage("Impossibile selezioneImpostazioneLingua")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='settingsLangBtn']")));
        impostazioneLingua.click();
    }

    public void verificaLinguaSelezionata(String lingua) {
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

        WebElement radioOther = getWebDriverWait(20)
                .withMessage("Il radio button 'Altra lingua' non è selezionabile")
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='other']")));
        radioOther.click();
    }

    public void selezioneLingua(String lingua) {
        WebElement selezionaLingua = getWebDriverWait(10)
                .withMessage("Impossibile trovare selezioneLingua //div[@id='additionalLang']")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='additionalLang']")));
        selezionaLingua.click();

        WebElement gruppoLingua = getWebDriverWait(10)
                .withMessage("Impossibile trovare la lista con la lingua: " + lingua)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'" + lingua + "')]")));

        logger.info("Lingua '{}' trovata con successo", lingua);
        gruppoLingua.click();
    }

    public void selezionareDaImpostazioneLingua(String lingua) {
        selezioneImpostazioneLingua();
        webTool.waitTime(3);
        if (lingua.equalsIgnoreCase("Italiano")) {
            WebElement radioItaliano = getWebDriverWait(30)
                    .withMessage("Impossibile trovare l'input radio per la lingua italiana")
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//label[span[contains(text(), 'Italiano') and not(contains(text(), 'Italiano e altra lingua'))]]//input[@name='lang']")
                    ));
            radioItaliano.click();
        } else {
            logger.info("Lingua: {}", lingua);
            selezioneItalianoAltralingua();
            webTool.waitTime(3);
            selezioneLingua(lingua);
        }

        webTool.waitTime(3);
        //chiusura della schermata tramite la X
        WebElement closeIcon = getWebDriverWait(30)
                .withMessage("Impossibile trovare //button[@aria-label='close']")
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='close']")));
        closeIcon.click();
        webTool.waitTime(3);
    }

    public void selezionareDaImpostazioneLinguaLaLingua(String lingua) {
        selezioneImpostazioneLingua();
        if (lingua.equalsIgnoreCase("Italiano")) {
            WebElement radioIt = getWebDriverWait(20)
                    .withMessage("Impossibile impostare la lingua su Italiano")
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='lang' and @value='it']/ancestor::label")));
            radioIt.click();
        } else {
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
        js.executeScript("document.body.style.zoom='" + size + "%'");

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
                            By.cssSelector("label[data-testid='physicalAddressLookupRadio." + posizione + "']")
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

    public boolean attesaNotificaConIUN(String iun) {
        int maxTentativi = 8;
        for (int tentativo = 0; tentativo <= maxTentativi; tentativo++) {
            try {
                List<WebElement> cellaCodiceIUN = getWebDriverWait(10)
                        .until(driver -> {
                            List<WebElement> elements = driver.findElements(
                                    By.xpath("//table[@id='notifications-table']//tr//td[contains(text(), '" + iun + "')]")
                            );
                            return elements.isEmpty() ? null : elements;
                        });

                if (!cellaCodiceIUN.isEmpty()) {
                    getNotificationSingletonParam().setScenarioIun(hooksNew.getScenario(), iun);
                    logger.info("Notifica trovata al tentativo {} con IUN: {}", tentativo, iun);
                    return true;
                }
            } catch (TimeoutException e) {
                logger.info("Tentativo n.{}: notifica non trovata, refresh in corso", tentativo);
            }

            webTool.waitTime(5); // breve attesa prima del refresh
            driver.navigate().refresh();
        }

        logger.warn("Notifica con IUN {} non trovata dopo {} tentativi", iun, maxTentativi);
        return false;
    }

    public void inserisciMaxCaratteriInputPecPortalePA(int maxCaratteri) {
        WebElement input = getWebDriverWait(10)
                .withMessage("Impossibile trovare il campo di input nell home page destinatari")
                .until(ExpectedConditions.visibilityOfElementLocated(
                By.id("recipients[0].digitalDomicile")
        ));

        String testoLungo = "A".repeat(maxCaratteri);
        input.clear();
        input.sendKeys(testoLungo);

    }

    public void verificaErrore(String erroreLabel) {
        WebElement errore = getWebDriverWait(10)
                .withMessage("Impossibile trovare la label di errroe pec nella home page destinatari")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("recipients[0].digitalDomicile-helper-text")
                ));

        String testoErrore = errore.getText().trim();
        Assertions.assertFalse(
                testoErrore.contains(erroreLabel),
                "Campo ha generato errore contenente '" + testoErrore );

    }


    public void verificaEsistenzaPaginaNonTrovata() {

        getWebDriverWait(10)
                .withMessage("Impossibile trovare il 'not-found-title'")
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='not-found-title']")));
        getWebDriverWait(10)
                .withMessage("Impossibile trovare il 'not-found-back-button'")
                .until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("[data-testid='not-found-back-button']")));
    }

    public void clickTornaAllaHome() {
        WebElement buttonTornaAllaHome = getWebDriverWait(10)
                .withMessage("Impossibile trovare il buttone 'TornaAllaHome'")
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.cssSelector("[data-testid='not-found-back-button']")));
        buttonTornaAllaHome.click();
    }
  
}