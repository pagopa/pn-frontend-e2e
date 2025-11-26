package it.pn.frontend.e2e.presentation.binder;

public interface IPageBinder {
    <T> T create(Class<T> pageInterface);
}
