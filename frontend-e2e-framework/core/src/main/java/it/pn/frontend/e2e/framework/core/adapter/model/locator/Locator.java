package it.pn.frontend.e2e.framework.core.adapter.model.locator;

public interface Locator<T extends  LocatorType> {
    String getLocation();
    T getType();
}
