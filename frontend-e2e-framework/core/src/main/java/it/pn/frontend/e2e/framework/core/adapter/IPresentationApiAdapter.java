package it.pn.frontend.e2e.framework.core.adapter;

import it.pn.frontend.e2e.framework.core.adapter.model.AssertionAction;
import it.pn.frontend.e2e.framework.core.adapter.model.PresentationElement;
import it.pn.frontend.e2e.framework.core.adapter.model.locator.Locator;
import it.pn.frontend.e2e.framework.core.adapter.model.locator.LocatorType;
import it.pn.frontend.e2e.framework.core.adapter.model.selector.Selector;
import it.pn.frontend.e2e.framework.core.adapter.model.selector.SelectorType;

import java.util.List;

public interface IPresentationApiAdapter<
        E extends PresentationElement,
        S extends Selector<? extends SelectorType>,
        L extends Locator<? extends LocatorType>
        >  {

    // Operazioni di ricerca
    E findElement(S selector);
    E findElementAndAssert(S selector, AssertionAction<PresentationElement> assertion);
    List<E> findElements(S selector);
    List<E> findElementsAndAssert(S selector, AssertionAction<PresentationElement> assertion);

    // Operazioni di interazione
    void click(S selector);
    void clickAndAssert(S selector, AssertionAction<PresentationElement> assertion);
    void sendText(S selector, String text);
    void sendTextAndAssert(S selector, String text, AssertionAction<PresentationElement> assertion);
    void clear(S selector);
    void clearAndAssert(S selector, AssertionAction<PresentationElement> assertion);

    default void sendTextAndAssert(S selector, String text) {
        sendText(selector, text);
        getTextAndAssert(selector, actualText -> {
            if (!actualText.equals(text)) {
                throw new AssertionError("Expected text: " + text + ", but got: " + actualText);
            }
        });
    }

    default void clearAndAssert(S selector) {
        clear(selector);
        getTextAndAssert(selector, actualText -> {
            if (!actualText.isEmpty()) {
                throw new AssertionError("Expected text to be empty, but got: " + actualText);
            }
        });
    }

    // Operazioni di verifica
    boolean isDisplayed(S selector);
    boolean isEnabled(S selector);
    String getText(S selector);
    String getTextAndAssert(S selector, AssertionAction<String> assertion);

    // Operazioni di attesa
    void waitForElement(S selector, long timeoutSeconds);
    void waitUntilElementDisappears(S selector, long timeoutSeconds);

    // Operazioni di navigazione
    Locator<? extends  LocatorType> getLocation();
    Locator<? extends  LocatorType> getLocationAndAssert(AssertionAction<Locator<? extends  LocatorType>> assertion);
    void navigateTo(L locator);
    void navigateToAndAssert(L locator, AssertionAction<Locator<? extends  LocatorType>> assertion);

    default void navigateToAndCheck(L locator) {
        navigateToAndAssert(locator, actualLocator -> {
            if (!actualLocator.getLocation().equals(locator.getLocation())) {
                throw new AssertionError("Expected location: " + locator.getLocation() + ", but got: " + actualLocator.getLocation());
            }
        });
    }
}
