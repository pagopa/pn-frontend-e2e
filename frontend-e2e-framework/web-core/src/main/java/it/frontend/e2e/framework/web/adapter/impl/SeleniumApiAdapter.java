package it.frontend.e2e.framework.web.adapter.impl;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SeleniumApiAdapter implements IWebPresentationApiAdapter {

    private final WebDriver driver;

    public SeleniumApiAdapter() {
        ChromeOptions options = new ChromeOptions();

        // Disable password manager + weak/compromised password warnings
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        // Disable various Chrome UI interruptions
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");

        // (optional) keep test profile isolated
        // options.addArguments("--incognito");
        // options.addArguments("--user-data-dir=/tmp/selenium-chrome-profile");

        this.driver = new ChromeDriver(options);
    }

    @Override
    public Optional<WebPresentationElement> findElement(WebSelector selector) {
        return Optional.empty();
    }

    @Override
    public Optional<WebPresentationElement> findElementAndAssert(WebSelector selector, AssertionAction<WebPresentationElement> assertion) {
        return Optional.empty();
    }

    @Override
    public Optional<List<WebPresentationElement>> findElements(WebSelector selector) {
        return Optional.empty();
    }

    @Override
    public Optional<List<WebPresentationElement>> findElementsAndAssert(WebSelector selector, AssertionAction<WebPresentationElement> assertion) {
        return Optional.empty();
    }

    @Override
    public void click(WebSelector selector) {
        findWebElement(selector).click();
    }

    @Override
    public void clickAndAssert(WebSelector selector, AssertionAction<WebPresentationElement> assertion) {

    }

    @Override
    public void sendText(WebSelector selector, String text) {
        WebElement element = findWebElement(selector);
        element.sendKeys(text);
    }

  @Override
  public void sendTextAndAssert(WebSelector selector, String text, AssertionAction<WebPresentationElement> assertion) {

  }

    @Override
    public void clear(WebSelector selector) {

    }

    @Override
    public void clearAndAssert(WebSelector selector, AssertionAction<WebPresentationElement> assertion) {

    }

    @Override
    public boolean isDisplayed(WebSelector selector) {
        return false;
    }

    @Override
    public boolean isEnabled(WebSelector selector) {
        return false;
    }

    @Override
    public Optional<String> getText(WebSelector selector) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getTextAndAssert(WebSelector selector, AssertionAction<String> assertion) {
        return Optional.empty();
    }

    @Override
    public void waitForElement(WebSelector selector, long timeoutSeconds) {

        String selectorValue = selector.getSelector();

        var by = switch (selector.getSelectorType()) {
            case ID -> new By.ById(selectorValue);
            case XPATH -> new By.ByXPath(selectorValue);
            case CSS_QUERY, CSS_SELECTOR -> new By.ByCssSelector(selectorValue);
        };

        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(driver -> driver.findElement(by));
    }

    @Override
    public void waitUntilElementDisappears(WebSelector selector, long timeoutSeconds) {

    }

    @Override
    public WebLocation getLocation() {
        return null;
    }

    @Override
    public WebLocation getLocationAndAssert(AssertionAction<WebLocation> assertion) {
        return null;
    }

    @Override
    public void navigateTo(WebLocation locator) {
        driver.get(locator.getLocation());
    }

    @Override
    public void navigateToAndAssert(WebLocation locator, AssertionAction<WebLocation> assertion) {

    }

    private WebElement findWebElement(WebSelector selector) {
        String selectorValue = selector.getSelector();

        return switch (selector.getSelectorType()) {
            case ID -> driver.findElement(new By.ById(selectorValue));
            case XPATH -> driver.findElement(new By.ByXPath(selectorValue));
            case CSS_QUERY, CSS_SELECTOR -> driver.findElement(new By.ByCssSelector(selectorValue));
        };
    }
}
