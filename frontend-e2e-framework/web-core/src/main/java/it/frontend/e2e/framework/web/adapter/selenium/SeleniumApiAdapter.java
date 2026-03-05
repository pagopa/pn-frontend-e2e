package it.frontend.e2e.framework.web.adapter.selenium;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.adapter.model.BrowserSettings;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public final class SeleniumApiAdapter implements IWebPresentationApiAdapter {

    private static final long DEFAULT_WAIT_TIMEOUT_SECONDS = 50;
    private final WebDriver driver;

    public SeleniumApiAdapter() {
        this(BrowserSettings.defaults());
    }

    public SeleniumApiAdapter(BrowserSettings settings) {
        BrowserSettings safeSettings = settings == null ? BrowserSettings.defaults() : settings;
        this.driver = WebDriverFactory.create(safeSettings.browser(), safeSettings);
    }

    @Override
    public Optional<WebPresentationElement> findElement(WebSelector selector) {
        try {
            WebElement webElement = findWebElement(selector);
            WebPresentationElement element = new WebPresentationElement(selector, null);
            element.setText(webElement.getText());
            element.setTag(webElement.getTagName());
            //element.setAttributes(webElement.getDomAttributes());
            return Optional.of(element);
        } catch (Exception e) {
            return Optional.empty();
        }
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
        try {
            WebElement element = findWebElement(selector);
            return Optional.of(element.getText());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> getTextAndAssert(WebSelector selector, AssertionAction<String> assertion) {
        return Optional.empty();
    }

    @Override
    public void waitForElement(WebSelector selector, long timeoutSeconds) {
        findWebElement(selector, timeoutSeconds);
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

    private WebElement findWebElement(WebSelector selector, long timeoutSeconds) {
        String selectorValue = selector.getSelector();

        var by = switch (selector.getSelectorType()) {
            case ID -> new By.ById(selectorValue);
            case XPATH -> new By.ByXPath(selectorValue);
            case CSS_QUERY, CSS_SELECTOR -> new By.ByCssSelector(selectorValue);
        };

        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement findWebElement(WebSelector selector) {
        return findWebElement(selector, DEFAULT_WAIT_TIMEOUT_SECONDS);
    }
}
