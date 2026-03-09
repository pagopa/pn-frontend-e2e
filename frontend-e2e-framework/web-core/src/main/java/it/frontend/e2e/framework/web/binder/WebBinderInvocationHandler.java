package it.frontend.e2e.framework.web.binder;

import it.frontend.e2e.framework.core.binder.context.BindContext;
import it.frontend.e2e.framework.core.binder.DefaultBinderInvocationHandler;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;

public class WebBinderInvocationHandler extends DefaultBinderInvocationHandler {
    public WebBinderInvocationHandler(ICapabilityDispatcher dispatcher) {
        super(dispatcher);
    }

    public WebBinderInvocationHandler(ICapabilityDispatcher dispatcher, BindContext ctx) {
        super(dispatcher, ctx);
    }

    @Override
    public String compose(String parent, String child) {
        return super.compose(parent, child);
    }
}
