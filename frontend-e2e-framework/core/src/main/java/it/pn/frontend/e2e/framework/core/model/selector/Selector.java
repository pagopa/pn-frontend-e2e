package it.pn.frontend.e2e.framework.core.model.selector;

public interface Selector<T extends SelectorType> {
    String getValue();
    T getType();
}
