package it.pn.frontend.e2e.presentation.binder;

import it.pn.frontend.e2e.presentation.IPresentationHandler;
import it.pn.frontend.e2e.presentation.dom.interaction.IDOMInteraction;
import it.pn.frontend.e2e.presentation.dom.parser.IDOMParser;
import it.pn.frontend.e2e.presentation.dom.reader.IDOMReader;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Proxy;

@RequiredArgsConstructor
public class DOMBinder implements IDOMBinder {

    private final IPresentationHandler presentationHandler;

    @SuppressWarnings("unchecked")
    public <T> T bind(Class<T> pageInterface) {

        // 1) Verifica che sia davvero un'interfaccia
        if (!pageInterface.isInterface()) {
            throw new IllegalArgumentException(
                    "PageBinder.create() richiede un'interfaccia, non una classe concreta."
            );
        }

        // 2) Crea un proxy dinamico che implementa l'interfaccia
        return (T) Proxy.newProxyInstance(
                pageInterface.getClassLoader(),
                new Class[]{pageInterface},
                new DOMInvocationHandler(presentationHandler, pageInterface)
        );
    }
}
