package it.pn.frontend.e2e.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
    protected void scrollToElementClickAndInsertText(WebElement element, String text){
        if(!element.isDisplayed()){
            this.js().executeScript("arguments[0].scrollIntoView(true);",element);
        }
        this.js().executeScript("arguments[0].click();",element);
        if(text != null){
            element.sendKeys(text);
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
}
