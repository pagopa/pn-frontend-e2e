package it.frontend.e2e.framework.web.binder;

import it.frontend.e2e.framework.annotation.location.web.Url;
import it.frontend.e2e.framework.core.binder.DefaultBinder;
import it.frontend.e2e.framework.core.binder.context.BindContext;
import it.frontend.e2e.framework.web.binder.context.WebBinderContext;

import java.lang.reflect.InvocationHandler;

public class WebBinder extends DefaultBinder {
    @Override
    protected InvocationHandler getInvocationHandler(Class<?> type, BindContext bindContext) {
        WebBinderContext webBinderContext = buildWebBindContext(type, bindContext);
        return new WebBinderInvocationHandler(webBinderContext);
    }

    private WebBinderContext buildWebBindContext(Class<?> type, BindContext bindContext) {
        return new WebBinderContext(bindContext);
    }

    @Override
    protected String resolveTypeLocation(Class<?> iface) {
        Url onType = iface.getAnnotation(Url.class);
        if (onType != null) return onType.value();

        return "";
    }
}
