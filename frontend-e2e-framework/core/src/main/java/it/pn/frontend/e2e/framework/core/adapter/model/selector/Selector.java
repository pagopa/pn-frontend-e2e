package it.pn.frontend.e2e.framework.core.adapter.model.selector;

public interface Selector<T extends SelectorType> {
    String getValue();
    T getType();
}
