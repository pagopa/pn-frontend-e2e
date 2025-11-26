package it.pn.frontend.e2e.presentation.binder;

import it.pn.frontend.e2e.presentation.dom.interaction.IDOMInteraction;
import it.pn.frontend.e2e.presentation.dom.parser.IDOMParser;
import it.pn.frontend.e2e.presentation.dom.reader.IDOMReader;

import java.lang.reflect.Proxy;

public class PageBinder implements IPageBinder {

    private final IDOMReader IDOMReader;
    private final IDOMParser IDOMParser;
    private final IDOMInteraction IDOMInteraction;

    public PageBinder(IDOMReader IDOMReader,
                      IDOMParser IDOMParser,
                      IDOMInteraction IDOMInteraction) {
        this.IDOMReader = IDOMReader;
        this.IDOMParser = IDOMParser;
        this.IDOMInteraction = IDOMInteraction;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> pageInterface) {

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
                new PageInvocationHandler(IDOMReader, IDOMParser, IDOMInteraction, pageInterface)
        );
    }
}
