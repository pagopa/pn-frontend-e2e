package it.pn.frontend.e2e.presentation.model.common.field;

public interface WritableField<T> extends Field {
    void set(T value);
}
