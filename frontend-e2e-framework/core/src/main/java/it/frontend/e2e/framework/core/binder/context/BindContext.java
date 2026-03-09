package it.frontend.e2e.framework.core.binder.context;

public record BindContext(String selector) {
    public static BindContext root() { return new BindContext(""); }
}
