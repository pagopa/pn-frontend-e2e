package it.pn.frontend.e2e.framework.core.model.locator;

public interface Locator<T extends  LocatorType> {
    String getLocation();
    T getType();
}
