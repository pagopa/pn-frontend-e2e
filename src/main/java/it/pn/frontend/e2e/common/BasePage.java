package it.pn.frontend.e2e.common;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class BasePage {

    protected int loadComponentWaitTime;
    private static final Logger loggerBase = LoggerFactory.getLogger(BasePage.class);

    @Autowired
    public WebDriver driver;


    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
        loadComponentWaitTime = Integer.parseInt(System.getProperty("loadComponentWaitTime", "10"));
    }

    protected void scrollToElementClickAndInsertText(WebElement element, String text) {
        try {
            if (!element.isDisplayed()) {
                loggerBase.info("Scrolling to element");
                js().executeScript("arguments[0].scrollIntoView(true);", element);
            }
            js().executeScript("arguments[0].click()", element);
            if (text != null) {
                loggerBase.info("Inserting text");
                element.sendKeys(text);
            }
        } catch (ElementNotInteractableException e) {
            loggerBase.error("Element not interactable", e);
            retryClickAndInsertText(element, text);
        }
    }

    private void retryClickAndInsertText(WebElement element, String text) {
        js().executeScript("arguments[0].click()", element);
        if (text != null) {
            element.sendKeys(text);
        }
    }

    public void scrollToElementAndClick(WebElement element) {
        try {
            if (!element.isDisplayed()) {
                loggerBase.info("Scrolling to element");
                js().executeScript("arguments[0].scrollIntoView(true);", element);
            }
            js().executeScript("arguments[0].click()", element);
        } catch (ElementNotInteractableException e) {
            loggerBase.error("elemento non interagibile", e);
            js().executeScript("arguments[0].click()", element);
        }
    }

    protected WebDriverWait getWebDriverWait(long timeout) {
        return new WebDriverWait(this.driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
    }

    protected WebElement element(By by) {
        return driver.findElement(by);
    }

    protected List<WebElement> elements(By by) {
        return driver.findElements(by);
    }

    protected JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }

    public void waitLoadPage() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for page load", e);
        }
    }

    public void vaiInFondoAllaPagina() {
        js().executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void aggiornamentoPagina() {
        driver.navigate().refresh();
    }

    public void waitLoadingSpinner() {
        By loadingSpinnerBy = By.xpath("//span[@role='loadingSpinner']");
        getWebDriverWait(60).withMessage("Page is still loading").until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinnerBy));
    }


    public void goBack() {
        driver.navigate().back();
    }

    /**
     * Clears text from a WebElement, compatible with React-controlled inputs.
     * Replaces the default <b>clear()</b> method for better compatibility.
     *
     * @param element WebElement to clear
     */
    public void clearWebElementField(WebElement element) {
        while (!element.getAttribute("value").isEmpty()) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public boolean checkURL(String url) {
        return driver.getCurrentUrl().contains(url);
    }
}
