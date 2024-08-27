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

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    protected void scrollToElementClickAndInsertText(WebElement element, String text) {
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
        } catch (ElementNotInteractableException e) {
            loggerBase.error("elemento non interagibile");
            this.js().executeScript("arguments[0].click()", element);
            if (text != null) {
                element.sendKeys(text);
            }
        }
    }

    public void scrollToElementAndClick(WebElement element) {
        try {
            if (!element.isDisplayed()) {
                loggerBase.info("scroll elemento");
                this.js().executeScript("arguments[0].scrollIntoView(true);", element);
            }
            this.js().executeScript("arguments[0].click()", element);
        } catch (ElementNotInteractableException e) {
            loggerBase.error("elemento non interagibile");
            this.js().executeScript("arguments[0].click()", element);
        }
    }

    protected WebDriverWait getWebDriverWait(long timeout) {
        return new WebDriverWait(this.driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
    }

    protected WebElement element(By by) {
        return this.driver.findElement(by);
    }

    protected List<WebElement> elements(By by) {
        return this.driver.findElements(by);
    }

    protected JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }

    public void waitLoadPage() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void vaiInFondoAllaPagina() {
        this.js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void aggiornamentoPagina() {
        this.driver.navigate().refresh();
    }

    public void waitLoadingSpinner() {
        By loadingSpinnerBy = By.xpath("//span[@role = 'loadingSpinner']");
        getWebDriverWait(60).withMessage("la pagina è ancora in caricamento").until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinnerBy));
    }

    public void goBack() {
        this.driver.navigate().back();
    }

    /**
     * Clear the text field of a WebElement
     * Note: this method replace <b>clear()</b> because
     * it does not work with React-controlled inputs
     *
     * @param element WebElement to clear the text field
     */
    public void clearWebElementField(WebElement element) {
        while (!element.getAttribute("value").isEmpty()) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public boolean checkURL(String url){
        return driver.getCurrentUrl().contains(url);
    }

    public  WebElement getWebElement(String element) {
        WebElement webElement= null;
        if (element.contains("\\")){
            webElement =  driver.findElement(By.xpath(element));
        }else {
            webElement =  driver.findElement(By.id(element));
        }
        return webElement;
    }



}