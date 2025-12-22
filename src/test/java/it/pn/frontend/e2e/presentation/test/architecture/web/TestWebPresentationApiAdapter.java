package it.pn.frontend.e2e.presentation.test.architecture.web;

import it.pn.frontend.e2e.presentation.web.adapter.IWebPresentationApiAdapter;

public class TestWebPresentationApiAdapter implements IWebPresentationApiAdapter {

    public String sayHello(String name) {
        return "Adapter says hello to " + name;
    }
}
