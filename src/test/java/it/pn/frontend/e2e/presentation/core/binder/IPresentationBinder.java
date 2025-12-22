package it.pn.frontend.e2e.presentation.core.binder;

public interface IPresentationBinder {
    <T> T bind(Class<T> type);
}
