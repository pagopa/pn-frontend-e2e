package it.pn.frontend.e2e.pages.mittente;

import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DashboardMittentePagoPA");
    private final List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
    private final NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();


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


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadDashboardPAPage() {
        By statisticheTitle = By.id("Statistiche-page");
        getWebDriverWait(10).withMessage("Il titolo non è visibile").until(ExpectedConditions.visibilityOfElementLocated(statisticheTitle));
        logger.info("Piattaforma Notifiche Page caricata");
    }

    public void clickStatistiche() {
        getWebDriverWait(10).withMessage("Il bottone Statistiche non visibile").until(ExpectedConditions.visibilityOf(sezioneStatistiche));
        logger.info("Si clicca bottone Statistiche");
        sezioneStatistiche.click();
    }

    public void clickUltimi6Mesi() {
        getWebDriverWait(10).withMessage("Il bottone ultimi 6 mesi non visibile").until(ExpectedConditions.visibilityOf(ultimi6MesiBottone.get(0)));
        logger.info("Si clicca bottone ultimi 6 mesi");
        ultimi6MesiBottone.get(0).click();
    }

    public void clickUltimi6MesiNotificheDigitali() {
        getWebDriverWait(10).withMessage("Il bottone ultimi 6 mesi non visibile").until(ExpectedConditions.visibilityOf(ultimi6MesiBottone.get(1)));
        logger.info("Si clicca bottone ultimi 6 mesi");
        ultimi6MesiBottone.get(1).click();
    }

    public void checkVisualizzaGrafico() {
        getWebDriverWait(10).withMessage("Il grafico non visibile").until(ExpectedConditions.visibilityOf(graficoText.get(0)));
        logger.info("Si visualizza il grafico");
    }

    public void checkVisualizzaGraficoInviiDigitaliPerEsito() {
        getWebDriverWait(10).withMessage("Il grafico Invii digitali per esito non visibile").until(ExpectedConditions.visibilityOf(graficoInviiDigitaliPerEsito));
        logger.info("Si visualizza il grafico Invii digitali per esito");
    }

    public void checkVisualizzaGraficoTempoMedioInviiDigitali() {
        getWebDriverWait(10).withMessage("Il grafico Errori tecnici per tipologia non visibile").until(ExpectedConditions.visibilityOf(graficoErroriTecnici));
        logger.info("Si visualizza il grafico Errori tecnici per tipologia");
    }

    public void checkVisualizzaGraficoErroriTecnici(){
        getWebDriverWait(10).withMessage("Il grafico Tempo medio degli invii digitali non visibile").until(ExpectedConditions.visibilityOf(graficoTempoMedioInviiDigitali));
        logger.info("Si visualizza il grafico Tempo medio degli invii digitali");
    }

    public void checkVisualizzaGraficoInviatePerStato(){
        getWebDriverWait(10).withMessage("la sezione Notifiche inviate per stato non visibile").until(ExpectedConditions.visibilityOf(sezioneNotificheInviatePerStato));
        logger.info("Si visualizza la sezione Notifiche inviate per stato e grafico");
    }

    public void checkVisualizzaGraficoConsegnatePerModalitaInvio(){
        getWebDriverWait(10).withMessage("la sezione Notifiche consegnate per modalità di invio non visibile").until(ExpectedConditions.visibilityOf(sezioneNotificheConsegnatePerModalitaInvio));
        logger.info("Si visualizza la sezione Notifiche consegnate per modalità di invio e grafico");
    }

   public void insertDataErrata(){
       getWebDriverWait(10).withMessage("Il filtro di data non visibile").until(ExpectedConditions.visibilityOf(dateDa.get(0)));
       logger.info("Si inserisce una data errata");
       dateDa.get(0).click();
       Actions action = new Actions(driver);
       action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
       WebTool.waitTime(1);
       dateDa.get(0).sendKeys("01/01/1111");
    }

    public void insertDataErrataNotificheDigitali(){
        getWebDriverWait(10).withMessage("Il filtro di data non visibile").until(ExpectedConditions.visibilityOf(dateDa.get(1)));
        logger.info("Si inserisce una data errata");
        dateDa.get(1).click();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        WebTool.waitTime(1);
        dateDa.get(1).sendKeys("01/01/1111");
    }
    public void insertDataCorretta(){
        getWebDriverWait(10).withMessage("Il filtro di data non visibile").until(ExpectedConditions.visibilityOf(dateDa.get(0)));
        logger.info("Si inserisce una data corretta");
        dateDa.get(0).click();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        LocalDate dataDaInserire = LocalDate.now().minusDays(90);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dataDaInserire.format(formatter);
        WebTool.waitTime(1);
        dateDa.get(0).sendKeys(formattedDate);
    }

    public void insertDataCorrettaNotificheDigitali(){
        getWebDriverWait(10).withMessage("Il filtro di data non visibile").until(ExpectedConditions.visibilityOf(dateDa.get(1)));
        logger.info("Si inserisce una data corretta");
        dateDa.get(1).click();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        LocalDate dataDaInserire = LocalDate.now().minusDays(90);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dataDaInserire.format(formatter);
        WebTool.waitTime(1);
        dateDa.get(1).sendKeys(formattedDate);
    }

    public void clickFiltraButton(){
        getWebDriverWait(10).withMessage("Il bottone Filtra non cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneFiltra.get(0)));
        logger.info("Si clicca sul bottone Filtra");
        bottoneFiltra.get(0).click();
    }

    public void clickFiltraButtonNotificheDigitali(){
        getWebDriverWait(10).withMessage("Il bottone Filtra non cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneFiltra.get(1)));
        logger.info("Si clicca sul bottone Filtra");
        bottoneFiltra.get(1).click();
    }

    public void clickAnnullaFiltriButton(){
        getWebDriverWait(10).withMessage("Il bottone Annulla Filtri non cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneAnnullaFiltri.get(0)));
        logger.info("Si clicca sul bottone Annulla Filtri");
        bottoneAnnullaFiltri.get(0).click();
    }

    public void clickAnnullaFiltriButtonNotificheDigitali(){
        getWebDriverWait(10).withMessage("Il bottone Annulla Filtri non cliccabile").until(ExpectedConditions.elementToBeClickable(bottoneAnnullaFiltri.get(1)));
        logger.info("Si clicca sul bottone Annulla Filtri");
        bottoneAnnullaFiltri.get(1).click();
    }

    public void checkTipoGrafico(String tipoGrafico){
        if(tipoGrafico.equalsIgnoreCase("aggregato")){
            getWebDriverWait(10).withMessage("Il tipo di grafico Aggregato non visibile").until(ExpectedConditions.visibilityOf(tipoAggregato.get(0)));
            logger.info("Il tipo di grafico è Aggregato");
        }else {
            getWebDriverWait(10).withMessage("Il tipo di grafico Andamentale non visibile").until(ExpectedConditions.visibilityOf(tipoAndamentale.get(0)));
            logger.info("Il tipo di grafico è Andamentale");
        }
    }

    public void checkTipoGraficoPerModalitaInvio(String tipoGrafico){
        if(tipoGrafico.equalsIgnoreCase("aggregato")){
            getWebDriverWait(10).withMessage("Il tipo di grafico Aggregato non visibile").until(ExpectedConditions.visibilityOf(tipoAggregato.get(1)));
            logger.info("Il tipo di grafico è Aggregato");
        }else {
            getWebDriverWait(10).withMessage("Il tipo di grafico Andamentale non visibile").until(ExpectedConditions.visibilityOf(tipoAndamentale.get(1)));
            logger.info("Il tipo di grafico è Andamentale");
        }
    }

    public void cambiaTipoGrafico(){
        getWebDriverWait(10).withMessage("Il tipo di grafico Aggregato non visibile").until(ExpectedConditions.visibilityOf(tipoAggregato.get(0)));
        tipoAggregato.get(0).click();
        WebTool.waitTime(1);
        tipoAndamentale.get(0).click();
    }

    public void cambiaTipoGraficoPerModalitaInvio(){
        getWebDriverWait(10).withMessage("Il tipo di grafico Aggregato non visibile").until(ExpectedConditions.visibilityOf(tipoAggregato.get(0)));
        tipoAggregato.get(1).click();
        WebTool.waitTime(1);
        tipoAndamentale.get(0).click();
    }

    public void sceglieOpzioneSettimane(){
        logger.info("Si sceglie l'opzione Settimane");
        getWebDriverWait(10).withMessage("Il bottone Settimane non visibile").until(ExpectedConditions.visibilityOf(bottoneSettimane));
        bottoneSettimane.click();
    }

    public void sceglieOpzioneGiorni(){
        logger.info("Si sceglie l'opzione Giorni");
        getWebDriverWait(10).withMessage("Il bottone Giorni non visibile").until(ExpectedConditions.visibilityOf(bottoneGiorni));
        bottoneGiorni.click();
    }

    public void clickJpegExport() throws AWTException {
        boolean headless = System.getProperty("headless").equalsIgnoreCase("true");
        if (!headless) {
            logger.info("controllo esistenza bottone per scaricare jpeg");
            getWebDriverWait(10).withMessage("Il bottone esporta jpeg non cliccabile").until(ExpectedConditions.elementToBeClickable(esportaJpegBottone));
            logger.info("Si clicca sul bottone Esporta JPEG");
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
        } else {

            String workingDirectory = System.getProperty("user.dir");
            File downloadDirectory = new File(workingDirectory + "/src/test/resources/dataPopulation/jpeg");

            // Generate a unique filename for the downloaded ZIP file
            String fileName = "downloaded_" + System.currentTimeMillis() + ".jpeg";

            logger.info("Si clicca sul bottone Esporta JPEG");
            esportaJpegBottone.click();
            WebTool.waitTime(2);
            File downloadedFile = new File(downloadDirectory + fileName);
            Assertions.assertTrue(downloadedFile.exists());
            logger.info("JPEG file downloaded successfully.");
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

    public void eliminaJpeg(){
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