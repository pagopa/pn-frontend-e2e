package it.frontend.e2e.framework.web.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.assertion.AssertionAction;
import it.frontend.e2e.framework.web.adapter.IWebPresentationApiAdapter;
import it.frontend.e2e.framework.web.capability.core.Writable;
import it.frontend.e2e.framework.web.model.WebSelector;

import java.lang.reflect.Method;
import java.util.Optional;

public class WritableCapabilityHandler extends AbstractWebCapabilityHandler<Writable> {

    public WritableCapabilityHandler(IWebPresentationApiAdapter adapter) {
        super(adapter);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public <T> T handle(Method method, Object[] args, String selector) {
        WebSelector webSelector = WebSelector.of(selector);

        String textToWrite = args[0].toString();
        Optional<? extends AssertionAction> assertion =
                args.length > 1 && args[1] instanceof AssertionAction ? Optional.of((AssertionAction) args[1]) : Optional.empty();

        adapter.waitForElement(webSelector, 10);

        switch (method.getName()) {
            case "writeAndAssert" -> {

                assertion.ifPresentOrElse(
                        a -> adapter.sendTextAndAssert(webSelector, textToWrite, a),
                        () ->  adapter.sendTextAndAssert(webSelector, textToWrite)
                );

                return null;
            }

            case "write" -> {
                adapter.sendText(webSelector, textToWrite);
                return null;
            }
            default -> throw new UnsupportedOperationException("Method " + method.getName() + " is not supported by " + capabilityClass.getSimpleName());
        }
    }
}
