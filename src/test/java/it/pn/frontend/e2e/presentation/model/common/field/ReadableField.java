package it.pn.frontend.e2e.presentation.model.common.field;

public interface ReadableField<T> extends Field {
    T get();
}