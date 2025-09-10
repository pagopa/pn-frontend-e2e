package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class DashboardPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    @Autowired
    private NotificationSingleton notificationSingleton;

    @FindBy(id = "menu-item(statistiche)")
    WebElement sezioneStatistiche;

    @FindBy(xpath = "//span[contains(text(),'Ultimi 6 mesi')]")
    List<WebElement> ultimi6MesiBottone;

    @FindBy(xpath = "//label[contains(text(),'Grafico')]")
    List<WebElement> graficoText;

    @FindBy(id = "startDate")
    List<WebElement> dateDa;

    @FindBy(id = "endDate")
    WebElement dateA;

    @FindBy(id = "filter-button")
    List<WebElement> bottoneFiltra;

    @FindBy(xpath = "//button[@data-testid='cancelButton']")
    List<WebElement> bottoneAnnullaFiltri;

    @FindBy(xpath = "//span[contains(text(),'Aggregato')]")
    List<WebElement> tipoAggregato;

    @FindBy(xpath = "//span[contains(text(),'Andamentale')]")
    List<WebElement> tipoAndamentale;

    @FindBy(xpath = "//span[contains(text(),'Settimane')]")
    WebElement bottoneSettimane;

    @FindBy(xpath = "//span[contains(text(),'Giorni')]")
    WebElement bottoneGiorni;

    @FindBy(xpath = "//h3[contains(text(),'Notifiche inviate per stato')]/..")
    WebElement sezioneNotificheInviatePerStato;

    @FindBy(xpath = "//h3[contains(text(),'Notifiche consegnate per modalità di invio')]/..")
    WebElement sezioneNotificheConsegnatePerModalitaInvio;

    @FindBy(xpath = "//div[@data-testid='digitalStateContainer']")
    WebElement graficoInviiDigitaliPerEsito;

    @FindBy(xpath = "//div[@data-testid='digitalMeanTimeContainer']")
    WebElement graficoTempoMedioInviiDigitali;

    @FindBy(xpath = "//div[@data-testid='digitalErrorsDetail']")
    WebElement graficoErroriTecnici;

    @FindBy(xpath = "//button[@data-testid='exportJpgButton']")
    WebElement esportaJpegBottone;

    @Autowired
    private WebDriverConfig webDriverConfig;

    private WebTool webTool;


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    //    public void waitLoadDashboardPAPage() {
//        WebElement statisticheTitle = driver.findElement(By.id("Statistiche-page"));
//        getWebDriverWait(10).withMessage("Il titolo non è visibile").until(ExpectedConditions.visibilityOf(statisticheTitle));
//        logger.info("Piattaforma Notifiche Page caricata");
//    }
    public void waitLoadDashboardPAPage() {
        getWebDriverWait(10)
                .withMessage("Titolo 'Statistiche' non visualizzato")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("Statistiche-page")));

        logger.info("Piattaforma Notifiche Dashboard PA Page caricata");
    }

    //    public void clickStatistiche() {
//        sezioneStatistiche = driver.findElement(By.id("menu-item(statistiche)"));
//        getWebDriverWait(10).withMessage("Il bottone Statistiche non visibile").until(ExpectedConditions.visibilityOf(sezioneStatistiche));
//        logger.info("Si clicca bottone Statistiche");
//        sezioneStatistiche.click();
//    }
    public void clickStatistiche() {
        logger.info("Click sul bottone Statistiche");

        getWebDriverWait(10)
                .withMessage("Bottone Statistiche non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(By.id("menu-item(statistiche)")))
                .click();
    }

//    public void clickUltimi6Mesi() {
//        ultimi6MesiBottone = driver.findElements(By.xpath("//span[contains(text(),'Ultimi 6 mesi')]"));
//        getWebDriverWait(10).withMessage("Il bottone ultimi 6 mesi non visibile").until(ExpectedConditions.visibilityOf(ultimi6MesiBottone.get(0)));
//        logger.info("Si clicca bottone ultimi 6 mesi");
//        ultimi6MesiBottone.get(0).click();
//    }

    public void clickUltimi6Mesi() {
        List<WebElement> buttons = getWebDriverWait(10)
                .withMessage("Bottoni 'Ultimi 6 mesi' non trovati")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//span[contains(text(),'Ultimi 6 mesi')]")));

        Assertions.assertFalse(buttons.isEmpty(), "Deve esserci almeno un bottone 'Ultimi 6 mesi'");

        logger.info("Click sul bottone Ultimi 6 mesi");
        buttons.get(0).click();
    }

    //    public void clickUltimi6MesiNotificheDigitali() {
//        ultimi6MesiBottone = driver.findElements(By.xpath("//span[contains(text(),'Ultimi 6 mesi')]"));
//        getWebDriverWait(10).withMessage("Il bottone ultimi 6 mesi non visibile").until(ExpectedConditions.visibilityOf(ultimi6MesiBottone.get(1)));
//        logger.info("Si clicca bottone ultimi 6 mesi");
//        ultimi6MesiBottone.get(1).click();
//    }
    public void clickUltimi6MesiNotificheDigitali() {
        List<WebElement> buttons = getWebDriverWait(10)
                .withMessage("Bottoni 'Ultimi 6 mesi' non trovati")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//span[contains(text(),'Ultimi 6 mesi')]")));

        Assertions.assertTrue(buttons.size() > 1, "Deve esserci almeno un secondo bottone 'Ultimi 6 mesi'");

        logger.info("Click sul secondo bottone Ultimi 6 mesi");
        buttons.get(1).click();
    }


    //    public void checkVisualizzaGrafico() {
//        graficoText = driver.findElements(By.xpath("//label[contains(text(),'Grafico')]"));
//        getWebDriverWait(10).withMessage("Il grafico non visibile").until(ExpectedConditions.visibilityOf(graficoText.get(0)));
//        logger.info("Si visualizza il grafico");
//    }
    public void checkVisualizzaGrafico() {
        List<WebElement> graficoText = getWebDriverWait(10)
                .withMessage("Elemento 'Grafico' non trovato o non visibile")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//label[contains(text(),'Grafico')]")));

        Assertions.assertFalse(graficoText.isEmpty(), "Nessun elemento 'Grafico' trovato");

        logger.info("Si visualizza il grafico");
    }

    //    public void checkVisualizzaGraficoInviiDigitaliPerEsito() {
//        graficoInviiDigitaliPerEsito = driver.findElement(By.xpath("//div[@data-testid='digitalStateContainer']"));
//        getWebDriverWait(10).withMessage("Il grafico Invii digitali per esito non visibile").until(ExpectedConditions.visibilityOf(graficoInviiDigitaliPerEsito));
//        logger.info("Si visualizza il grafico Invii digitali per esito");
//    }
    public void checkVisualizzaGraficoInviiDigitaliPerEsito() {
        getWebDriverWait(10)
                .withMessage("Il grafico Invii digitali per esito non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='digitalStateContainer']")));

        logger.info("Si visualizza il grafico Invii digitali per esito");
    }

    //    public void checkVisualizzaGraficoTempoMedioInviiDigitali() {
//        graficoTempoMedioInviiDigitali = driver.findElement(By.xpath("//div[@data-testid='digitalMeanTimeContainer']"));
//        getWebDriverWait(10).withMessage("Il grafico Tempo medio degli invii digitali non visibile").until(ExpectedConditions.visibilityOf(graficoTempoMedioInviiDigitali));
//        logger.info("Si visualizza il grafico Tempo medio degli invii digitali");
//    }
    public void checkVisualizzaGraficoTempoMedioInviiDigitali() {
        getWebDriverWait(10)
                .withMessage("Il grafico Tempo medio degli invii digitali non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='digitalMeanTimeContainer']")));

        logger.info("Si visualizza il grafico Tempo medio degli invii digitali");
    }

    //    public void checkVisualizzaGraficoErroriTecnici() {
//        graficoErroriTecnici = driver.findElement(By.xpath("//div[@data-testid='digitalErrorsDetail']"));
//        getWebDriverWait(10).withMessage("Il grafico Errori tecnici non visibile").until(ExpectedConditions.visibilityOf(graficoErroriTecnici));
//        logger.info("Si visualizza il grafico Errori tecnici");
//    }
    public void checkVisualizzaGraficoErroriTecnici() {
        getWebDriverWait(10)
                .withMessage("Il grafico Errori tecnici non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='digitalErrorsDetail']")));

        logger.info("Si visualizza il grafico Errori tecnici");
    }

    //    public void checkVisualizzaGraficoInviatePerStato() {
//        sezioneNotificheInviatePerStato = driver.findElement(By.xpath("//h3[contains(text(),'Notifiche inviate per stato')]/.."));
//        getWebDriverWait(10).withMessage("la sezione Notifiche inviate per stato non visibile").until(ExpectedConditions.visibilityOf(sezioneNotificheInviatePerStato));
//        logger.info("Si visualizza la sezione Notifiche inviate per stato e grafico");
//    }
    public void checkVisualizzaGraficoInviatePerStato() {
        getWebDriverWait(10)
                .withMessage("La sezione Notifiche inviate per stato non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(text(),'Notifiche inviate per stato')]/..")));

        logger.info("Si visualizza la sezione Notifiche inviate per stato e grafico");
    }

    //    public void checkVisualizzaGraficoConsegnatePerModalitaInvio() {
//        sezioneNotificheConsegnatePerModalitaInvio = driver.findElement(By.xpath("//h3[contains(text(),'Notifiche consegnate per modalità di invio')]/.."));
//        getWebDriverWait(10).withMessage("la sezione Notifiche consegnate per modalità di invio non visibile").until(ExpectedConditions.visibilityOf(sezioneNotificheConsegnatePerModalitaInvio));
//        logger.info("Si visualizza la sezione Notifiche consegnate per modalità di invio e grafico");
//    }
    public void checkVisualizzaGraficoConsegnatePerModalitaInvio() {
        getWebDriverWait(10)
                .withMessage("La sezione Notifiche consegnate per modalità di invio non è visibile")
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(text(),'Notifiche consegnate per modalità di invio')]/..")));

        logger.info("Si visualizza la sezione Notifiche consegnate per modalità di invio e grafico");
    }

    //    public void insertDataErrata() {
//        dateDa = driver.findElements(By.id("startDate"));
//        getWebDriverWait(10).withMessage("Il filtro di data non visibile").until(ExpectedConditions.visibilityOf(dateDa.get(0)));
//        logger.info("Si inserisce una data errata");
//        dateDa.get(0).click();
//        new Actions(driver)
//                .keyDown(Keys.CONTROL)
//                .sendKeys("a")
//                .keyUp(Keys.CONTROL)
//                .sendKeys(Keys.BACK_SPACE)
//                .perform();
//        webTool.waitTime(1);
//        dateDa.get(0).sendKeys("01/01/1111");
//    }
    public void insertDataErrata() {
        List<WebElement> dateFields = getWebDriverWait(10)
                .withMessage("Nessun campo data trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("startDate")));

        if (dateFields.isEmpty()) {
            throw new RuntimeException("Nessun campo data con ID 'startDate' trovato");
        }
        WebElement dateDaField = dateFields.get(0);
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        webTool.waitTime(1);
        dateDaField.sendKeys("01/01/1111");
    }

    //    public void insertDataErrataNotificheDigitali() {
//        dateDa = driver.findElements(By.id("startDate"));
//        getWebDriverWait(10).withMessage("Il filtro di data non visibile").until(ExpectedConditions.visibilityOf(dateDa.get(1)));
//        logger.info("Si inserisce una data errata");
//        dateDa.get(1).click();
//        Actions action = new Actions(driver);
//        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
//        webTool.waitTime(1);
//        dateDa.get(1).sendKeys("01/01/1111");
//    }
    public void insertDataErrataNotificheDigitali() {
        List<WebElement> dateDa = getWebDriverWait(10)
                .withMessage("Nessun campo data trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("startDate")));
        if (dateDa.size() < 2) {
            throw new RuntimeException("Solo " + dateDa.size() + " campi data trovati, ne servono almeno 2");
        }
        getWebDriverWait(5)
                .withMessage("Il secondo campo data non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(dateDa.get(1)));
        logger.info("Si inserisce una data errata nel secondo campo");
        dateDa.get(1).click();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        webTool.waitTime(1);
        dateDa.get(1).sendKeys("01/01/1111");

    }

    //    public void insertDataCorretta() {
//        dateDa = driver.findElements(By.id("startDate"));
//        getWebDriverWait(10).withMessage("Il filtro di data non visibile").until(ExpectedConditions.visibilityOf(dateDa.get(0)));
//        logger.info("Si inserisce una data corretta");
//        dateDa.get(0).click();
//        new Actions(driver)
//                .keyDown(Keys.CONTROL)
//                .sendKeys("a")
//                .keyUp(Keys.CONTROL)
//                .sendKeys(Keys.BACK_SPACE)
//                .perform();
//        LocalDate dataDaInserire = LocalDate.now().minusDays(90);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = dataDaInserire.format(formatter);
//        webTool.waitTime(1);
//        dateDa.get(0).sendKeys(formattedDate);
//    }
    public void insertDataCorretta() {
        List<WebElement> dateDa = getWebDriverWait(10)
                .withMessage("Nessun campo data trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("startDate")));
        if (dateDa.isEmpty()) {
            throw new RuntimeException("Nessun campo data con ID 'startDate' trovato");
        }
        WebElement dateField = dateDa.get(0);
        getWebDriverWait(5)
                .withMessage("Il campo data non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(dateField));
        logger.info("Si inserisce una data corretta");
        dateField.click();
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        LocalDate dataDaInserire = LocalDate.now().minusDays(90);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dataDaInserire.format(formatter);
        webTool.waitTime(1);
        dateField.sendKeys(formattedDate);
        logger.info("Inserita data: {}", formattedDate);

    }

//    public void insertDataCorrettaNotificheDigitali() {
//        dateDa = driver.findElements(By.id("startDate"));
//        getWebDriverWait(10).withMessage("Il filtro di data non visibile").until(ExpectedConditions.visibilityOf(dateDa.get(1)));
//        logger.info("Si inserisce una data corretta");
//        dateDa.get(1).click();
//        Actions action = new Actions(driver);
//        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
//        LocalDate dataDaInserire = LocalDate.now().minusDays(90);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = dataDaInserire.format(formatter);
//        webTool.waitTime(1);
//        dateDa.get(1).sendKeys(formattedDate);
//    }

    public void insertDataCorrettaNotificheDigitali() {
        List<WebElement> dateDa = getWebDriverWait(10)
                .withMessage("Nessun campo data trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("startDate")));
        if (dateDa.size() < 2) {
            throw new RuntimeException("Solo " + dateDa.size() + " campi data trovati, ne servono almeno 2");
        }
        WebElement dateField = dateDa.get(1);
        getWebDriverWait(5)
                .withMessage("Il secondo campo data non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(dateField));

        logger.info("Si inserisce una data corretta nel secondo campo");
        dateField.click();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        LocalDate dataDaInserire = LocalDate.now().minusDays(90);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dataDaInserire.format(formatter);
        webTool.waitTime(1);
        dateField.sendKeys(formattedDate);

        logger.info("Inserita data corretta nel secondo campo: " + formattedDate);

    }

    //    public void clickFiltraButton() {
//        bottoneFiltra = driver.findElements(By.id("filter-button"));
//        getWebDriverWait(10).withMessage("Il bottone Filtra non cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneFiltra.get(0)));
//        logger.info("Si clicca sul bottone Filtra");
//        bottoneFiltra.get(0).click();
//    }
    public void clickFiltraButton() {
        List<WebElement> bottoneFiltra = getWebDriverWait(10)
                .withMessage("Nessun bottone Filtra trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("filter-button")));
        if (bottoneFiltra.isEmpty()) {
            throw new RuntimeException("Nessun bottone Filtra con ID 'filter-button' trovato");
        }
        getWebDriverWait(5)
                .withMessage("Il bottone Filtra non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(bottoneFiltra.get(0)));
        logger.info("Si clicca sul bottone Filtra");
        bottoneFiltra.get(0).click();
    }

    //    public void clickFiltraButtonNotificheDigitali() {
//        bottoneFiltra = driver.findElements(By.id("filter-button"));
//        getWebDriverWait(10).withMessage("Il bottone Filtra non cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneFiltra.get(1)));
//        logger.info("Si clicca sul bottone Filtra");
//        bottoneFiltra.get(1).click();
//    }
    public void clickFiltraButtonNotificheDigitali() {
        List<WebElement> bottoneFiltra = getWebDriverWait(10)
                .withMessage("Nessun bottone Filtra trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("filter-button")));
        if (bottoneFiltra.size() < 2) {
            throw new RuntimeException("Solo " + bottoneFiltra.size() + " bottoni Filtra trovati, ne servono almeno 2");
        }
        getWebDriverWait(5)
                .withMessage("Il secondo bottone Filtra non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(bottoneFiltra.get(1)));
        logger.info("Si clicca sul secondo bottone Filtra");
        bottoneFiltra.get(1).click();

    }

    //    public void clickAnnullaFiltriButton() {
//        bottoneAnnullaFiltri = driver.findElements(By.xpath("//button[@data-testid='cancelButton']"));
//        getWebDriverWait(10).withMessage("Il bottone Annulla Filtri non cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneAnnullaFiltri.get(0)));
//        logger.info("Si clicca sul bottone Annulla Filtri");
//        bottoneAnnullaFiltri.get(0).click();
//    }
    public void clickAnnullaFiltriButton() {
        List<WebElement> bottoneAnnullaFiltri = getWebDriverWait(10)
                .withMessage("Nessun bottone Annulla Filtri trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[@data-testid='cancelButton']")));
        if (bottoneAnnullaFiltri.isEmpty()) {
            throw new RuntimeException("Nessun bottone Annulla Filtri trovato con data-testid='cancelButton'");
        }
        getWebDriverWait(5)
                .withMessage("Il bottone Annulla Filtri non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(bottoneAnnullaFiltri.get(0)));

        logger.info("Si clicca sul bottone Annulla Filtri");
        bottoneAnnullaFiltri.get(0).click();
    }

    //
//    public void clickAnnullaFiltriButtonNotificheDigitali() {
//        bottoneAnnullaFiltri = driver.findElements(By.xpath("//button[@data-testid='cancelButton']"));
//        getWebDriverWait(10).withMessage("Il bottone Annulla Filtri non cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneAnnullaFiltri.get(1)));
//        logger.info("Si clicca sul bottone Annulla Filtri");
//        bottoneAnnullaFiltri.get(1).click();
//    }
    public void clickAnnullaFiltriButtonNotificheDigitali() {
        List<WebElement> bottoneAnnullaFiltri = getWebDriverWait(10)
                .withMessage("Nessun bottone Annulla Filtri trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[@data-testid='cancelButton']")));
        if (bottoneAnnullaFiltri.size() < 2) {
            throw new RuntimeException("Solo " + bottoneAnnullaFiltri.size() + " bottoni Annulla Filtri trovati, ne servono almeno 2");
        }
        getWebDriverWait(5)
                .withMessage("Il secondo bottone Annulla Filtri non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(bottoneAnnullaFiltri.get(1)));
        logger.info("Si clicca sul secondo bottone Annulla Filtri");
        bottoneAnnullaFiltri.get(1).click();
    }

    //    public void checkTipoGrafico(String tipoGrafico) {
//        if (tipoGrafico.equalsIgnoreCase("aggregato")) {
//            tipoAggregato = driver.findElements(By.xpath("//span[contains(text(),'Aggregato')]"));
//            getWebDriverWait(10).withMessage("Il tipo di grafico Aggregato non visibile").until(ExpectedConditions.visibilityOf(tipoAggregato.get(0)));
//            logger.info("Il tipo di grafico è Aggregato");
//        } else {
//            tipoAndamentale = driver.findElements(By.xpath("//span[contains(text(),'Andamentale')]"));
//            getWebDriverWait(10).withMessage("Il tipo di grafico Andamentale non visibile").until(ExpectedConditions.visibilityOf(tipoAndamentale.get(0)));
//            logger.info("Il tipo di grafico è Andamentale");
//        }
//    }
    public void checkTipoGrafico(String tipoGrafico) {
        String xpath;
        String tipo;

        if (tipoGrafico.equalsIgnoreCase("aggregato")) {
            xpath = "//span[contains(text(),'Aggregato')]";
            tipo = "Aggregato";
        } else if (tipoGrafico.equalsIgnoreCase("andamentale")) {
            xpath = "//span[contains(text(),'Andamentale')]";
            tipo = "Andamentale";
        } else {
            throw new IllegalArgumentException("Tipo di grafico non valido: " + tipoGrafico + ". Usare 'aggregato' o 'andamentale'");
        }
        List<WebElement> elements = getWebDriverWait(10)
                .withMessage("Nessun elemento per il tipo di grafico " + tipo + " trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        if (elements.isEmpty()) {
            throw new RuntimeException("Nessun elemento trovato per il tipo di grafico: " + tipo);
        }

        logger.info("Il tipo di grafico è " + tipo);

    }

    //    public void checkTipoGraficoPerModalitaInvio(String tipoGrafico) {
//        if (tipoGrafico.equalsIgnoreCase("aggregato")) {
//            tipoAggregato = driver.findElements(By.xpath("//span[contains(text(),'Aggregato')]"));
//            getWebDriverWait(10).withMessage("Il tipo di grafico Aggregato non visibile").until(ExpectedConditions.visibilityOf(tipoAggregato.get(1)));
//            logger.info("Il tipo di grafico è Aggregato");
//        } else {
//            tipoAndamentale = driver.findElements(By.xpath("//span[contains(text(),'Andamentale')]"));
//            getWebDriverWait(10).withMessage("Il tipo di grafico Andamentale non visibile").until(ExpectedConditions.visibilityOf(tipoAndamentale.get(1)));
//            logger.info("Il tipo di grafico è Andamentale");
//        }
//    }
    public void checkTipoGraficoPerModalitaInvio(String tipoGrafico) {
        String expectedText;
        int index;

        if (tipoGrafico.equalsIgnoreCase("aggregato")) {
            expectedText = "Aggregato";
            index = 1;
        } else if (tipoGrafico.equalsIgnoreCase("andamentale")) {
            expectedText = "Andamentale";
            index = 1;
        } else {
            throw new IllegalArgumentException("Tipo di grafico non valido: " + tipoGrafico + ". Usare 'aggregato' o 'andamentale'");
        }
        String xpath = "//span[contains(text(),'" + expectedText + "')]";
        List<WebElement> elements = getWebDriverWait(10)
                .withMessage("Nessun elemento per il tipo di grafico " + expectedText + " trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        if (elements.size() <= index) {
            throw new RuntimeException("Trovati " + elements.size() + " elementi, ma ne servono almeno " + (index + 1) + " per il tipo: " + expectedText);
        }
        logger.info("Il tipo di grafico è " + expectedText + " (elemento all'indice " + index + ")");

    }

    //    public void cambiaTipoGrafico() {
//        tipoAggregato = driver.findElements(By.xpath("//span[contains(text(),'Aggregato')]"));
//        getWebDriverWait(10).withMessage("Il tipo di grafico Aggregato non visibile").until(ExpectedConditions.visibilityOf(tipoAggregato.get(0)));
//        tipoAggregato.get(0).click();
//        webTool.waitTime(1);
//        tipoAndamentale = driver.findElements(By.xpath("//span[contains(text(),'Andamentale')]"));
//        tipoAndamentale.get(0).click();
//    }
    public void cambiaTipoGrafico() {
        List<WebElement> tipoAggregato = getWebDriverWait(10)
                .withMessage("Nessun elemento Aggregato trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//span[contains(text(),'Aggregato')]")));
        if (tipoAggregato.isEmpty()) {
            throw new RuntimeException("Nessun elemento Aggregato trovato");
        }
        getWebDriverWait(5)
                .withMessage("Elemento Aggregato non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(tipoAggregato.get(0)));
        logger.info("Clicco sul tipo di grafico Aggregato");
        tipoAggregato.get(0).click();
        webTool.waitTime(1);
        List<WebElement> tipoAndamentale = getWebDriverWait(10)
                .withMessage("Nessun elemento Andamentale trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//span[contains(text(),'Andamentale')]")));
        if (tipoAndamentale.isEmpty()) {
            throw new RuntimeException("Nessun elemento Andamentale trovato");
        }
        getWebDriverWait(5)
                .withMessage("Elemento Andamentale non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(tipoAndamentale.get(0)));

        logger.info("Clicco sul tipo di grafico Andamentale");
        tipoAndamentale.get(0).click();
    }

    //    public void cambiaTipoGraficoPerModalitaInvio() {
//        tipoAggregato = driver.findElements(By.xpath("//span[contains(text(),'Aggregato')]"));
//        getWebDriverWait(10).withMessage("Il tipo di grafico Aggregato non visibile").until(ExpectedConditions.visibilityOf(tipoAggregato.get(0)));
//        tipoAggregato.get(1).click();
//        webTool.waitTime(1);
//        tipoAndamentale.get(0).click();
//    }
    public void cambiaTipoGraficoPerModalitaInvio() {
        List<WebElement> tipoAggregato = getWebDriverWait(10)
                .withMessage("Nessun elemento Aggregato trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//span[contains(text(),'Aggregato')]")));
        if (tipoAggregato.size() < 2) {
            throw new RuntimeException("Solo " + tipoAggregato.size() + " elementi Aggregato trovati, ne servono almeno 2");
        }
        getWebDriverWait(5)
                .withMessage("Secondo elemento Aggregato non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(tipoAggregato.get(1)));

        logger.info("Clicco sul secondo elemento Aggregato");
        tipoAggregato.get(1).click();
        webTool.waitTime(1);
        List<WebElement> tipoAndamentale = getWebDriverWait(10)
                .withMessage("Nessun elemento Andamentale trovato")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//span[contains(text(),'Andamentale')]")));

        if (tipoAndamentale.isEmpty()) {
            throw new RuntimeException("Nessun elemento Andamentale trovato");
        }
        getWebDriverWait(5)
                .withMessage("Elemento Andamentale non cliccabile")
                .until(ExpectedConditions.elementToBeClickable(tipoAndamentale.get(0)));
        logger.info("Clicco sul primo elemento Andamentale");
        tipoAndamentale.get(0).click();

    }

    //    public void sceglieOpzioneSettimane() {
//        logger.info("Si sceglie l'opzione Settimane");
//        bottoneSettimane = driver.findElement(By.xpath("//span[contains(text(),'Settimane')]"));
//        getWebDriverWait(10).withMessage("Il bottone Settimane non visibile").until(ExpectedConditions.visibilityOf(bottoneSettimane));
//        bottoneSettimane.click();
//    }
    public void sceglieOpzioneSettimane() {
        logger.info("Si sceglie l'opzione Settimane");
        WebElement bottoneSettimane = getWebDriverWait(10)
                .withMessage("Il bottone Settimane non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[contains(text(),'Settimane')]")));
        bottoneSettimane.click();
        logger.info("Cliccato sul bottone Settimane");
    }

    //    public void sceglieOpzioneGiorni() {
//        logger.info("Si sceglie l'opzione Giorni");
//        bottoneGiorni = driver.findElement(By.xpath("//span[contains(text(),'Giorni')]"));
//        getWebDriverWait(10).withMessage("Il bottone Giorni non visibile").until(ExpectedConditions.visibilityOf(bottoneGiorni));
//        bottoneGiorni.click();
//    }
    public void sceglieOpzioneGiorni() {
        logger.info("Si sceglie l'opzione Giorni");
        WebElement bottoneGiorni = getWebDriverWait(10)
                .withMessage("Il bottone Giorni non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[contains(text(),'Giorni')]")));
        bottoneGiorni.click();
        logger.info("Cliccato sul bottone Giorni");
    }

//    public void clickJpegExport() throws AWTException {
//        boolean headless = webDriverConfig.getHeadless().equalsIgnoreCase("true");
//        if (!headless) {
//            logger.info("controllo esistenza bottone per scaricare jpeg");
//            esportaJpegBottone = driver.findElement(By.xpath("//button[@data-testid='exportJpgButton']"));
//            getWebDriverWait(10).withMessage("Il bottone esporta jpeg non cliccabile").until(ExpectedConditions.elementToBeClickable(esportaJpegBottone));
//            logger.info("Si clicca sul bottone Esporta JPEG");
//            esportaJpegBottone.click();
//            Robot robot = new Robot();
//            robot.setAutoDelay(100);
//            robot.delay(2000);
//            String workingDirectory = System.getProperty("user.dir");
//            String path = workingDirectory + "/src/test/resources/dataPopulation/jpeg";
//
//            pressTabKey(robot, 6);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            typeFilePath(robot, path);
//
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            robot.delay(1000);
//
//            pressTabKey(robot, 8);
//
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            logger.info("JPEG scaricato");
//        } else {
//
//            String workingDirectory = System.getProperty("user.dir");
//            File downloadDirectory = new File(workingDirectory + "/src/test/resources/dataPopulation/jpeg");
//
//            // Generate a unique filename for the downloaded ZIP file
//            String fileName = "downloaded_" + System.currentTimeMillis() + ".jpeg";
//
//            logger.info("Si clicca sul bottone Esporta JPEG");
//            esportaJpegBottone = driver.findElement(By.xpath("//button[@data-testid='exportJpgButton']"));
//            esportaJpegBottone.click();
//            webTool.waitTime(2);
//            File downloadedFile = new File(downloadDirectory + fileName);
//            Assertions.assertTrue(downloadedFile.exists());
//            logger.info("JPEG file downloaded successfully.");
//        }
//    }

    public void clickJpegExport() throws AWTException {
        boolean headless = webDriverConfig.getHeadless().equalsIgnoreCase("true");

        logger.info("Controllo esistenza bottone per scaricare JPEG");

        WebElement esportaJpegBottone = getWebDriverWait(10)
                .withMessage("Il bottone esporta JPEG non è cliccabile")
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-testid='exportJpgButton']")));

        if (!headless) {
            handleNonHeadlessDownload(esportaJpegBottone);
        } else {
            handleHeadlessDownload(esportaJpegBottone);
        }

    }

    private void handleNonHeadlessDownload(WebElement esportaJpegBottone) throws AWTException {
        logger.info("Si clicca sul bottone Esporta JPEG (modalità non headless)");
        esportaJpegBottone.click();

        Robot robot = new Robot();
        robot.setAutoDelay(100);
        robot.delay(2000);

        String workingDirectory = System.getProperty("user.dir");
        String path = workingDirectory + "/src/test/resources/dataPopulation/jpeg";

        pressTabKey(robot, 6);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        typeFilePath(robot, path);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(1000);

        pressTabKey(robot, 8);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        logger.info("JPEG scaricato");
    }

    private void handleHeadlessDownload(WebElement esportaJpegBottone) {
        logger.info("Si clicca sul bottone Esporta JPEG (modalità headless)");
        esportaJpegBottone.click();
        webTool.waitTime(2);

        String workingDirectory = System.getProperty("user.dir");
        File downloadDirectory = new File(workingDirectory + "/src/test/resources/dataPopulation/jpeg");

        // Verifica che la directory esista
        if (!downloadDirectory.exists()) {
            downloadDirectory.mkdirs();
        }

        // Cerca file JPEG nella directory
        File[] jpegFiles = downloadDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpeg"));

        if (jpegFiles != null && jpegFiles.length > 0) {
            // Prendi l'ultimo file modificato (presumibilmente quello appena scaricato)
            Arrays.sort(jpegFiles, Comparator.comparingLong(File::lastModified).reversed());
            File downloadedFile = jpegFiles[0];

            Assertions.assertTrue(downloadedFile.exists(), "File JPEG non trovato nella directory di download");
            logger.info("JPEG file downloaded successfully: " + downloadedFile.getName());
        } else {
            throw new RuntimeException("Nessun file JPEG trovato nella directory di download");
        }
    }

    private static void pressTabKey(Robot robot, int times) {
        for (int i = 0; i < times; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(500);
        }
    }

    private void typeFilePath(Robot robot, String filePath) {
        for (char c : filePath.toCharArray()) {
            typeCharacter(robot, c);
        }
    }

    private void typeCharacter(Robot robot, char character) {
        switch (character) {
            case ':':
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_SEMICOLON);
                robot.keyRelease(KeyEvent.VK_SEMICOLON);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case '\\':
                robot.keyPress(KeyEvent.VK_BACK_SLASH);
                robot.keyRelease(KeyEvent.VK_BACK_SLASH);
                break;
            case '/':
                robot.keyPress(KeyEvent.VK_SLASH);
                robot.keyRelease(KeyEvent.VK_SLASH);
                break;
            case '.':
                robot.keyPress(KeyEvent.VK_PERIOD);
                robot.keyRelease(KeyEvent.VK_PERIOD);
                break;
            default:
                if (Character.isUpperCase(character)) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(Character.toUpperCase(character));
                    robot.keyRelease(Character.toUpperCase(character));
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress(Character.toUpperCase(character));
                    robot.keyRelease(Character.toUpperCase(character));
                }
        }
    }

    public void eliminaJpeg() {
        String workingDirectory = System.getProperty("user.dir");
        File downloadDirectory = new File(workingDirectory + "/src/test/resources/dataPopulation/jpeg");
        File directory = new File(String.valueOf(downloadDirectory));
        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        }
    }
}