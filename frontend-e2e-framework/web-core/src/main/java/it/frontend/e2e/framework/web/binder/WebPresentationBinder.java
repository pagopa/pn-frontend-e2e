package it.frontend.e2e.framework.web.binder;


import it.frontend.e2e.framework.core.presentation_binder.AbstractBinder;

import java.lang.reflect.InvocationHandler;

public class WebPresentationBinder extends AbstractBinder {

    @Override
    protected InvocationHandler getInvocationHandler(Class<?> boundType) {
        return null;
    }
}
