package it.frontend.e2e.framework.web.capability.impl;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.core.capability.context.CapabilityContext;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.capability.core.Readable;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadableImpl<T extends WebPresentationElement> implements Readable<T> {

    private final IWebPresentationApiAdapter adapter;

    @Override
    @SuppressWarnings("unchecked")
    public T read() {
        WebSelector webSelector = WebSelector.of(CapabilityContext.selector());
        return (T) adapter.findElement(webSelector).orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T readAndAssert() {
        WebSelector webSelector = WebSelector.of(CapabilityContext.selector());
        return (T) adapter.findElement(webSelector).orElseThrow(
                () -> {
                    String errorMsg = String.format(
                            "Element not found for selector: %s. " +
                            "Check if selector is correct or element is present in DOM. " +
                            "Consider increasing wait timeout if page loads slowly.",
                            webSelector
                    );
                    return new RuntimeException(errorMsg);
                }
        );
    }

    @Override
    public T readAndAssert(AssertionAction<WebPresentationElement> assertionAction) {
        T element = readAndAssert();
        assertionAction.assertOn(element);
        return element;
    }
}
