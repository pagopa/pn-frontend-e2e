package it.frontend.e2e.framework.core.binder;

public record BindContext(String selector) {
    public static BindContext root() { return new BindContext(""); }
}
