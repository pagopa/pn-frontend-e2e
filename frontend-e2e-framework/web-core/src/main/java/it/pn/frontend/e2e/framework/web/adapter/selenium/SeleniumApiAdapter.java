package it.pn.frontend.e2e.framework.web.adapter.selenium;

import it.pn.frontend.e2e.framework.core.adapter.model.AssertionAction;
import it.pn.frontend.e2e.framework.core.adapter.model.PresentationElement;
import it.pn.frontend.e2e.framework.core.adapter.model.locator.Locator;
import it.pn.frontend.e2e.framework.core.adapter.model.locator.LocatorType;
import it.pn.frontend.e2e.framework.core.adapter.model.selector.Selector;
import it.pn.frontend.e2e.framework.core.adapter.model.selector.SelectorType;
import it.pn.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumApiAdapter implements IWebPresentationApiAdapter {
    private WebDriver driver = new ChromeDriver();

    @Override
    public void navigateTo(String url) {
        driver.get(url);
    }

    @Override
    public <T extends PresentationElement> T findElement(Selector<? extends SelectorType> selector) {
        return null;
    }

    @Override
    public <T extends PresentationElement> T findElementAndAssert(Selector<? extends SelectorType> selector, AssertionAction<PresentationElement> assertion) {
        return null;
    }

    @Override
    public <T extends PresentationElement> List<T> findElements(Selector<? extends SelectorType> selector) {
        return List.of();
    }

    @Override
    public <T extends PresentationElement> List<T> findElementsAndAssert(Selector<? extends SelectorType> selector, AssertionAction<PresentationElement> assertion) {
        return List.of();
    }

    @Override
    public void click(Selector<? extends SelectorType> selector) {

    }

    @Override
    public void clickAndAssert(Selector<? extends SelectorType> selector, AssertionAction<PresentationElement> assertion) {

    }

    @Override
    public void sendText(Selector<? extends SelectorType> selector, String text) {

    }

    @Override
    public void sendTextAndAssert(Selector<? extends SelectorType> selector, String text, AssertionAction<PresentationElement> assertion) {

    }

    @Override
    public void clear(Selector<? extends SelectorType> selector) {

    }

    @Override
    public void clearAndAssert(Selector<? extends SelectorType> selector, AssertionAction<PresentationElement> assertion) {

    }

    @Override
    public boolean isDisplayed(Selector<? extends SelectorType> selector) {
        return false;
    }

    @Override
    public boolean isEnabled(Selector<? extends SelectorType> selector) {
        return false;
    }

    @Override
    public String getText(Selector<? extends SelectorType> selector) {
        return "";
    }

    @Override
    public String getTextAndAssert(Selector<? extends SelectorType> selector, AssertionAction<String> assertion) {
        return "";
    }

    @Override
    public void waitForElement(Selector<? extends SelectorType> selector, long timeoutSeconds) {

    }

    @Override
    public void waitUntilElementDisappears(Selector<? extends SelectorType> selector, long timeoutSeconds) {

    }

    @Override
    public Locator<? extends LocatorType> getLocation() {
        return null;
    }

    @Override
    public Locator<? extends LocatorType> getLocationAndAssert(AssertionAction<Locator<? extends LocatorType>> assertion) {
        return null;
    }

    @Override
    public void navigateTo(Locator<? extends LocatorType> locator) {

    }

    @Override
    public void navigateToAndAssert(Locator<? extends LocatorType> locator, AssertionAction<Locator<? extends LocatorType>> assertion) {

    }
}
