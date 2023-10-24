package it.pn.frontend.e2e.pages.destinatario.personaFisica;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeleghePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("DeleghePage");

    @FindBy(xpath = "//button[@data-testid ='add-delegation']")
    WebElement addDelegaButton;

    @FindBy(xpath = "//li[@data-testid = 'menuItem-revokeDelegate']")
    WebElement revocaButton;

    @FindBy(xpath = "//li[@data-testid='menuItem-showCode']")
    WebElement mostraCodiceOption;

    @FindBy(xpath = "//li[contains(@data-testid,'menuItem-revokeDelegate')]")
    WebElement revocaDelegaOption;

    public DeleghePage(WebDriver driver) {
        super(driver);
    }

    public void waitDeleghePage() {
        try {
            By deleghePageTitle = By.id("Deleghe-page");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(deleghePageTitle));
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOf(this.addDelegaButton));
            this.logger.info("Il titolo o il bottone aggiungi delega è visibile nella pagina aggiuDeleghe");
        } catch (TimeoutException e) {
            logger.error("Il titolo o il bottone aggiungi delega non è visibile nella pagina aggiuDeleghe con errore : " + e.getMessage());
            Assert.fail("Il titolo o il bottone aggiungi delega non è visibile nella pagina aggiuDeleghe con errore : " + e.getMessage());
        }
    }

    public void clickAggiungiDelegaButton()  {
        try {
            this.getWebDriverWait(30).until(ExpectedConditions.elementToBeClickable(this.addDelegaButton));
            this.addDelegaButton.click();
        }catch(TimeoutException e){
            logger.error("Il bottone aggiungi delega non è cliccabile con errore : " + e.getMessage());
            Assert.fail("Il bottone aggiungi delega non è cliccabile con errore : " + e.getMessage());
        }
    }

    public void controlloCreazioneDelega() {
        try{
            By delegaCreata = By.xpath("//span[contains(text(),'In attesa di conferma')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegaCreata));
            this.logger.info("Si visualizza la delega creata");
        } catch (TimeoutException e) {
            logger.error("Non si visualizza la delega creata");
            Assert.fail("Non si visualizza la delega creata");
        }
    }

    public boolean CercaEsistenzaDelega(String nome,String cognome) {
        try {
            By nomeDelegato = By.xpath("//div[@data-testid='delegates-wrapper']//div/p[contains(text(),'"+nome+" "+cognome+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public void clickRevocaButton(String nome, String cognome) {

        By menuButton = By.xpath("//div[@data-testid='delegates-wrapper']//td[@role='cell' and div/p[contains(text(),'"+nome+" "+cognome+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
        this.getWebDriverWait(40).withMessage("menu delega non cliccabile ").until(ExpectedConditions.elementToBeClickable(menuButton));
        this.js().executeScript("arguments[0].click()",this.element(menuButton));
        this.getWebDriverWait(30).withMessage("buttone revoca delega non cliccabile").until(ExpectedConditions.elementToBeClickable(this.revocaButton));
        this.revocaButton.click();


    }
    public boolean siVisualizzaUnaDelega() {
        try{
            By menuDelega = By.xpath("//tr[contains(@class,'MuiTableRow-root css-g76qb5')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelega));
            logger.info("Trovato correttamente almeno una delega");
            return true;
        }catch (TimeoutException e){
            logger.warn("Deleghe NON trovate con errore: "+e.getMessage());
            return false;
        }
    }

    public void clickMenuDelega(String nome, String cognome) {
        try{
            By menuDelega = By.xpath("//div[@data-testid='delegates-wrapper']//td[@role='cell' and div/p[contains(text(),'"+nome+" "+cognome+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuDelega));
            WebElement menuDelegaWebElement = this.driver.findElement(menuDelega);
            this.js().executeScript("arguments[0].click()",menuDelegaWebElement);
            logger.info("cliccato correttamente su menu delega button");
        }catch (TimeoutException e){
            logger.error("Menu delega button NON trovata con errore: "+e.getMessage());
            Assert.fail("Menu delega button NON trovata con errore: "+e.getMessage());
        } catch (StaleElementReferenceException e){
            By menuDelega = By.xpath("//div[@data-testid='delegates-wrapper']//td[@role='cell' and div/p[contains(text(),'"+nome+" "+cognome+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
            this.getWebDriverWait(40).until(ExpectedConditions.elementToBeClickable(menuDelega));
            WebElement menuDelegaWebElement = this.driver.findElement(menuDelega);
            this.js().executeScript("arguments[0].click()",menuDelegaWebElement);
        }
    }

    public void siSceglieLOpzioneMostraCodice() {this.mostraCodiceOption.click();}

    public void siCliccaSulBottoneChiudi() {
        try{
            By closeCodiceButtonBy = By.xpath("//button[@data-testid='codeCancelButton']");
            getWebDriverWait(40).until(ExpectedConditions.visibilityOfElementLocated(closeCodiceButtonBy));
            logger.info("Il bottone chiudi viene visualizzato correttamente");
            this.element(closeCodiceButtonBy).click();
        }catch (TimeoutException e){
            logger.error("Il bottone chiudi viene visualizzato NON correttamente con errore:"+e.getMessage());
            Assert.fail("Il bottone chiudi viene visualizzato NON correttamente con errore:"+e.getMessage());
        }
    }

    public void clickOpzioneRevoca() {
        this.revocaDelegaOption.click();
    }

    public void clickMenuPerRifiuto(String nome, String cognome) {
        try{
            By menuDelegheBy = By.xpath("//div[@data-testid='delegators-wrapper']//td[@role='cell' and div/p[contains(text(),'"+nome+" "+cognome+"')]]/following-sibling::td[@role='cell']//button[@data-testid='delegationMenuIcon']");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(menuDelegheBy));
            this.element(menuDelegheBy).click();
            logger.info("Si clicca correttamente sul menu della delega");
        }catch (TimeoutException e){
            logger.error("Non si clicca correttamente sul menu della delega con errore:"+e.getMessage());
            Assert.fail("Non si clicca correttamente sul menu della delega con errore:"+e.getMessage());
        }
    }

    public void clickRifiuta() {
        try{
            By rifiutaButtonBy = By.xpath("//li[contains(text(),'Rifiuta')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(rifiutaButtonBy));
            this.element(rifiutaButtonBy).click();
            logger.info("Si visualizza il bottone rifiuta");
        }catch(TimeoutException e){
            logger.error("Non si clicca correttamente sul bottone rifiuta con errore: "+e.getMessage());
            Assert.fail("Non si clicca correttamente sul bottone rifiuta con errore: "+e.getMessage());
        }
    }

    public void clickRiufitaPopUp() {
        try{
            By rifiutaButtonPopUpBy = By.xpath("//button[contains(text(),'Rifiuta la delega')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(rifiutaButtonPopUpBy));
            this.element(rifiutaButtonPopUpBy).click();
            logger.info("Si visualizza il bottone rifiuta nel pop-up");
        }catch(TimeoutException e){
            logger.error("Non si clicca correttamente sul bottone rifiuta pop-up con errore: "+e.getMessage());
            Assert.fail("Non si clicca correttamente sul bottone rifiuta pop-up errore: "+e.getMessage());
        }
    }

    public void clickAnnullaPopUp() {
        try{
            By annullaButtonPopUpBy = By.xpath("//button[contains(text(),'Annulla')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(annullaButtonPopUpBy));
            this.element(annullaButtonPopUpBy).click();
            logger.info("Si visualizza il bottone rifiuta nel pop-up");
        }catch(TimeoutException e){
            logger.error("Non si clicca correttamente sul bottone rifiuta pop-up con errore: "+e.getMessage());
            Assert.fail("Non si clicca correttamente sul bottone rifiuta pop-up errore: "+e.getMessage());
        }
    }

    public boolean verificaEsistenzaDelega(String nome, String cognome) {
        try {
            By nomeDelegato = By.xpath("//div[@data-testid='delegators-wrapper']//div/p[contains(text(),'"+nome+" "+cognome+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(nomeDelegato));
            logger.info("TA_QA: La delega è stata trovata correttamente");
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            logger.info("TA_QA: La delega sta per essere creata");
            return false;
        }
    }

    public boolean siVisualizzaUnaDelegaConNome(String nome, String cognome) {
        try {
            By delegaBy = By.xpath("//tr[@id = 'delegatesTable.row']//p[contains(text(),'"+ nome+" "+ cognome+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegaBy));
            logger.info("Si trova una delega");
            return true;
        }catch (TimeoutException e){
            logger.warn("Non si trova una delega con il nome "+nome + " " + cognome);
            return false;
        }
    }
    public boolean siVisualizzaUnaDelegaConNomeDelegato(String nome, String cognome) {
        try {
            By delegaBy = By.xpath("//tr[@id = 'delegatorsTable.row']//p[contains(text(),'"+ nome+" "+ cognome+"')]");
            this.getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(delegaBy));
            logger.info("Si trova una delega");
            return true;
        }catch (TimeoutException e){
            logger.warn("Non si trova una delega con il nome "+nome + " " + cognome);
            return false;
        }
    }

    public String vericaStatoDelega(){
        By statoDelegaBy = By.xpath("//div[@id='chip-status-success']/span");
        this.getWebDriverWait(30).withMessage("Non si trova nessuno stato delega").until(ExpectedConditions.visibilityOfElementLocated(statoDelegaBy));
        WebElement statoDelega = this.element(statoDelegaBy);
        return statoDelega.getText();

    }


}
