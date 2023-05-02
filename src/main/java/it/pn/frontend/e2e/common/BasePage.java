package it.pn.frontend.e2e.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
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

}
