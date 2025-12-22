package it.pn.frontend.e2e.presentation.web.binder;

import it.pn.frontend.e2e.presentation.core.binder.PresentationBinder;
import it.pn.frontend.e2e.presentation.core.capability.dispatcher.CapabilityDispatcher;
import it.pn.frontend.e2e.presentation.web.adapter.IWebPresentationApiAdapter;
import it.pn.frontend.e2e.presentation.web.binder.invocation_handler.WebPresentationInvocationHandler;

import java.lang.reflect.Proxy;

public class WebPresentationBinder extends PresentationBinder<IWebPresentationApiAdapter> implements IWebPresentationBinder {

    public WebPresentationBinder(IWebPresentationApiAdapter adapter, CapabilityDispatcher dispatcher) {
        super(adapter, dispatcher);
    }

    @Override
    protected Object createProxy(Class type) {
        return Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                new WebPresentationInvocationHandler(
                        dispatcher,
                        adapter,
                        type,
                        ""
                )
        );
    }
}
