package it.pn.frontend.e2e.framework.core.capability.handler.impl;

import it.pn.frontend.e2e.framework.core.adapter.IPresentationApiAdapter;
import it.pn.frontend.e2e.framework.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.framework.core.capability.common.Readable;
import it.pn.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ReadableCapabilityHandler<Context extends BaseInvocationContext> implements ICapabilityHandler<Context> {
    private IPresentationApiAdapter adapter;

    @Override
    public boolean canHandle(Context context) {
        return context.getMethod().getDeclaringClass().equals(Readable.class);
    }

    @Override
    public Object handle(Context context) {
        return adapter.findElement(context.getSelector());
    }
}
