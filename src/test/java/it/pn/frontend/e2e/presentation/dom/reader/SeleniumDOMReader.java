package it.pn.frontend.e2e.presentation.dom.reader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class SeleniumDOMReader implements IDOMReader {

    private final WebDriver driver;

    public SeleniumDOMReader(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebElement readBySelector(String selector) {
        try {
            return driver.findElement(By.cssSelector(selector));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<WebElement> readAllBySelector(String selector) {
        return driver.findElements(By.cssSelector(selector));
    }
}

