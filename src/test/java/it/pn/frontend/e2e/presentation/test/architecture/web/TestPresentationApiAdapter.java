package it.pn.frontend.e2e.presentation.test.architecture.web;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;

public class TestPresentationApiAdapter implements IPresentationApiAdapter {

    public String sayHello(String name) {
        return "Adapter says hello to " + name;
    }
}
