package it.pn.frontend.e2e.framework.core.binder;

public interface IPresentationBinder {
    <T> T bind(Class<T> type);
}
