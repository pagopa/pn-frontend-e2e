package it.frontend.e2e.framework.web.adapter.impl;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Optional;

public class SeleniumApiAdapter implements IWebPresentationApiAdapter {
    private WebDriver driver = new ChromeDriver();

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

    }

    @Override
    public void clickAndAssert(WebSelector selector, AssertionAction<WebPresentationElement> assertion) {

    }

    @Override
    public void sendText(WebSelector selector, String text) {

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
}
