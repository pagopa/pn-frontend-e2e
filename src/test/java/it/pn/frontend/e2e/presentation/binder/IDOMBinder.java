package it.pn.frontend.e2e.presentation.binder;

public interface IDOMBinder {
    <T> T bind(Class<T> uiElement);
}
