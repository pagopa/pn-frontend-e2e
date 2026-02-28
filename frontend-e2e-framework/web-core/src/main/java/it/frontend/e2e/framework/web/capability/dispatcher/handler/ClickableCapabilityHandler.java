package it.frontend.e2e.framework.web.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.capability.core.Clickable;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.AbstractCapabilityHandler;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.adapter.impl.SeleniumApiAdapter;
import it.frontend.e2e.framework.web.capability.core.Gettable;
import it.frontend.e2e.framework.web.model.WebSelector;

import java.lang.reflect.Method;

public class ClickableCapabilityHandler extends AbstractCapabilityHandler<Clickable> {

    private final IWebPresentationApiAdapter adapter;

    public ClickableCapabilityHandler() {
        super(Clickable.class);
        adapter = SeleniumApiAdapter.getInstance();
    }

    @Override
    public <T> T handle(Method method, Object[] args, String selector) {
        WebSelector webSelector = WebSelector.of(selector);

        switch (method.getName()) {
            case "click" -> {
                adapter.waitForElement(webSelector, 10);
                adapter.click(webSelector);
                return null;
            }
            default -> throw new UnsupportedOperationException("Method " + method.getName() + " is not supported by " + capabilityClass.getSimpleName());
        }
    }
}
