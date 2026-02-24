package it.pn.frontend.e2e.framework.core.adapter;

import it.pn.frontend.e2e.framework.core.adapter.model.AssertionAction;
import it.pn.frontend.e2e.framework.core.adapter.model.PresentationElement;
import it.pn.frontend.e2e.framework.core.adapter.model.locator.Locator;
import it.pn.frontend.e2e.framework.core.adapter.model.locator.LocatorType;
import it.pn.frontend.e2e.framework.core.adapter.model.selector.Selector;
import it.pn.frontend.e2e.framework.core.adapter.model.selector.SelectorType;

import java.util.List;

public interface IPresentationApiAdapter {

    // Operazioni di ricerca
    <T extends PresentationElement> T findElement(Selector<? extends SelectorType> selector);
    <T extends PresentationElement> T findElementAndAssert(Selector<? extends SelectorType> selector, AssertionAction<PresentationElement> assertion);
    <T extends PresentationElement> List<T> findElements(Selector<? extends SelectorType> selector);
    <T extends PresentationElement> List<T> findElementsAndAssert(Selector<? extends SelectorType> selector, AssertionAction<PresentationElement> assertion);

    // Operazioni di interazione
    void click(Selector<? extends SelectorType> selector);
    void clickAndAssert(Selector<? extends SelectorType> selector, AssertionAction<PresentationElement> assertion);
    void sendText(Selector<? extends SelectorType> selector, String text);
    void sendTextAndAssert(Selector<? extends SelectorType> selector, String text, AssertionAction<PresentationElement> assertion);
    void clear(Selector<? extends SelectorType> selector);
    void clearAndAssert(Selector<? extends SelectorType> selector, AssertionAction<PresentationElement> assertion);

    default void sendTextAndAssert(Selector<? extends SelectorType> selector, String text) {
        sendText(selector, text);
        getTextAndAssert(selector, actualText -> {
            if (!actualText.equals(text)) {
                throw new AssertionError("Expected text: " + text + ", but got: " + actualText);
            }
        });
    }

    default void clearAndAssert(Selector<? extends SelectorType> selector) {
        clear(selector);
        getTextAndAssert(selector, actualText -> {
            if (!actualText.isEmpty()) {
                throw new AssertionError("Expected text to be empty, but got: " + actualText);
            }
        });
    }

    // Operazioni di verifica
    boolean isDisplayed(Selector<? extends SelectorType> selector);
    boolean isEnabled(Selector<? extends SelectorType> selector);
    String getText(Selector<? extends SelectorType> selector);
    String getTextAndAssert(Selector<? extends SelectorType> selector, AssertionAction<String> assertion);

    // Operazioni di attesa
    void waitForElement(Selector<? extends SelectorType> selector, long timeoutSeconds);
    void waitUntilElementDisappears(Selector<? extends SelectorType> selector, long timeoutSeconds);

    // Operazioni di navigazione
    Locator<? extends  LocatorType> getLocation();
    Locator<? extends  LocatorType> getLocationAndAssert(AssertionAction<Locator<? extends  LocatorType>> assertion);
    void navigateTo(Locator<? extends LocatorType> locator);
    void navigateToAndAssert(Locator<? extends LocatorType> locator, AssertionAction<Locator<? extends  LocatorType>> assertion);

    default void navigateToAndCheck(Locator<? extends LocatorType> locator) {
        navigateToAndAssert(locator, actualLocator -> {
            if (!actualLocator.getLocation().equals(locator.getLocation())) {
                throw new AssertionError("Expected location: " + locator.getLocation() + ", but got: " + actualLocator.getLocation());
            }
        });
    }
}
