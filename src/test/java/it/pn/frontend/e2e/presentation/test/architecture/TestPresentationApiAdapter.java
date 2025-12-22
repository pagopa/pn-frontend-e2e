package it.pn.frontend.e2e.presentation.test.architecture;

import it.pn.frontend.e2e.presentation.core.adapter.IPresentationApiAdapter;

class TestPresentationApiAdapter implements IPresentationApiAdapter {

    public String sayHello(String name) {
        return "Adapter says hello to " + name;
    }
}
