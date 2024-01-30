package it.pn.frontend.e2e.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected WebDriver driver;

    protected int loadComponentWaitTime = Integer.parseInt(System.getProperty("loadComponentWaitTime"));

    private static final Logger loggerBase = LoggerFactory.getLogger("BasePage");

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    protected void scrollToElementClickAndInsertText(WebElement element, String text){
        try {
            if (!element.isDisplayed()) {
                loggerBase.info("scroll elemento");
                this.js().executeScript("arguments[0].scrollIntoView(true);", element);
            }
            this.js().executeScript("arguments[0].click()", element);
            if (text != null) {
                loggerBase.info("inserimento testo");
                element.sendKeys(text);
            }
        }catch (ElementNotInteractableException e){
            loggerBase.error("elemento non interagibile");
            this.js().executeScript("arguments[0].click()", element);
            if (text != null) {
                element.sendKeys(text);
            }
        }
    }

    protected void scrollToElementClickAndReplaceText(WebElement element, String text){
         Runnable clearAndInsertNewValue = () -> {
            // first clear the current text
            // -----------------------------------------
            // tried with both of the following, neither one worked
            //    element.clear()
            //    new Actions(this.driver).keyDown(Keys.CONTROL).sendKeys("a")
            //      .keyUp(Keys.CONTROL).pause(200).sendKeys(Keys.BACK_SPACE);
            // About the first I guess the fact that we're dealing with React-controlled inputs
            // makes the .clear() ineffective; cfr. https://github.com/SeleniumHQ/selenium/issues/6741.
            // About the second one I have no clue about why it does not work.
            element.sendKeys(Keys.END);
            loggerBase.info("cancello testo");
            while (element.getAttribute("value").length() > 0) {
                element.sendKeys(Keys.BACK_SPACE);
            }
            // now that the input is clear, we insert the new value
             loggerBase.info("inserisco nuovo testo");
             element.sendKeys(text);
        };

        try {
            if (!element.isDisplayed()) {
                this.js().executeScript("arguments[0].scrollIntoView(true);", element);
            }
            this.js().executeScript("arguments[0].click()", element);
            if (text != null) {
                clearAndInsertNewValue.run();
            }
        }catch (ElementNotInteractableException e){
            this.js().executeScript("arguments[0].click()", element);
            if (text != null) {
                clearAndInsertNewValue.run();
            }
        }
    }

    protected WebDriverWait getWebDriverWait(long timeout){
        return new WebDriverWait(this.driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
    }

    protected WebElement element(By by){
        return this.driver.findElement(by);
    }

    protected List<WebElement> elements(By by){
        return this.driver.findElements(by);
    }



    protected JavascriptExecutor js(){
        return (JavascriptExecutor) driver;
    }

    public void waitLoadPage(){
        try {
            TimeUnit.SECONDS.sleep(7);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void vaiInFondoAllaPagina() {
        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    public void aggionamentoPagina(){
        this.driver.navigate().refresh();
    }
    public void waitLoadingSpinner(){
        By loadingSpinnerBy = By.xpath("//span[@role = 'loadingSpinner']");
        getWebDriverWait(60).withMessage("la pagina è ancora in caricamento").until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinnerBy));
    }

    public void goBack(){
        this.driver.navigate().back();
    }
}

