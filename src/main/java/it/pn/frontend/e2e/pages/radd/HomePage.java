package it.pn.frontend.e2e.pages.radd;

import it.pn.frontend.e2e.common.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("HomePage");

    @FindBy(xpath = "//button[contains(@aria-label,'Vai alla pagina Ottenere documenti allegati della notifica e attestazioni opponibili a terzi')]")
    WebElement documentiAllegatuButton;

    @FindBy(xpath = "//button[contains(@aria-label,'Vai alla pagina Ottenere avvisi di avvenuta ricezione')]")
    WebElement avvenutoRicezioneButton;

    @FindBy(xpath = "//button[contains(@aria-label,'Vai alla pagina Consultare le richieste precedenti')]")
    WebElement storicoRichiesteButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void siVisualizzaCorrettamenteHomePage() {
        try {
            By homePageTitleBy = By.xpath("//h3[contains(text(),'Cosa vuoi fare?')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(homePageTitleBy));
            this.element(homePageTitleBy).isDisplayed();
            logger.info("La Homepage si visualizza correttamente");
        }catch (TimeoutException e){
            logger.error("La Homepage NON si visualizza correttamente con errore:"+e.getMessage());
            Assert.fail("La Homepage NON si visualizza correttamente con errore:"+e.getMessage());
        }
    }

    public void clickSuDocumentiAllegati() {this.documentiAllegatuButton.click();}

    public void richiestaAttipageSiVisualizzaCorretamente() {
        try {
            By richiestaPageTitle = By.xpath("//h4[contains(text(),'Documenti allegati e attestazioni opponibili a terzi')]");
            getWebDriverWait(30).until(ExpectedConditions.visibilityOfElementLocated(richiestaPageTitle));
            logger.info("Richiesta atti si visualizza correttamente");
        }catch (Exception e){
            logger.error("Richiesta atti NON si visualizza correttamente con errore:"+e.getMessage());
            Assert.fail("Richiesta atti NON si visualizza correttamente con errore:"+e.getMessage());
        }

    }

    public void clickSuAvvenutaRecezione() {
        this.avvenutoRicezioneButton.click();
    }

    public void clickSuStoricoRichieste() {
        this.storicoRichiesteButton.click();
    }
}

