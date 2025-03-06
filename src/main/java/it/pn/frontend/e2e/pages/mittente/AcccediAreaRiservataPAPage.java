package it.pn.frontend.e2e.pages.mittente;


import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.utility.WebTool;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
* Modifiche principali
Iniezione WebDriver con Spring: Il costruttore della classe utilizza @Autowired, permettendo a Spring di gestire automaticamente il driver.
Annotazione @Component: Aggiunta per registrare la classe come bean Spring, rendendo l'istanza accessibile in altre parti dell’applicazione.
Logging migliorato: La sintassi dei log logger.error  è stata modificata per sfruttare il placeholder {} di SLF4J, migliorando la leggibilità.*/



public class AcccediAreaRiservataPAPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger("AccediAreaRiservataPAPage");
    @FindBy(id = "spidButton")
    WebElement spidButton;

    @FindBy (id = "forward_prod-pn-test")
    WebElement testButton;

    private WebTool webTool;

    public AcccediAreaRiservataPAPage(WebDriver driver) {
        this.driver = driver;
        webTool = new WebTool(driver);
    }

    public void waitLoadLoginPageMittente(){
        try{
           // WebElement titoloLabel = driver.findElement(By.xpath("//h3[@class = 'MuiTypography-root MuiTypography-h3 css-ktw4ma']"));
            getWebDriverWait(45).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[@class = 'MuiTypography-root MuiTypography-h3 css-ktw4ma']"))));
            logger.info("Accedi Area Riservata Page caricata");
        }catch (TimeoutException e){
            Assertions.fail("Il titolo nella pagina  Accedi Area Riservata non è presente con errore : "+e.getMessage());
        }
    }

    public void selezionareSpidButton(){
        logger.info("Si seleziona il bottone Spid");
        try {
            getWebDriverWait(60).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("spidButton"))));
            spidButton = driver.findElement(By.id("spidButton"));
            spidButton.click();
            logger.info("click sul bottone SPID effettuato");
        }catch (TimeoutException e){
            Assertions.fail("Il bottone SPID non è cliccabile con errore : "+e.getMessage());
        }
    }

    public void clickTestBottone(){
        getWebDriverWait(5).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(driver.findElement(By.id("forward_prod-pn-test"))), ExpectedConditions.elementToBeClickable(driver.findElement(By.id("forward_prod-pn-test")))));
        testButton = driver.findElement(By.id("forward_prod-pn-test"));
        testButton.click();
    }
}
