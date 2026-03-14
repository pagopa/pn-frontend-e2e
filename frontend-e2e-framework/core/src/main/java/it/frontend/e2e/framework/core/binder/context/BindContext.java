package it.frontend.e2e.framework.core.binder.context;

import it.frontend.e2e.framework.core.capability.context.CapabilityScope;

public record BindContext(CapabilityScope scope) {
    public static BindContext root() { return new BindContext(new CapabilityScope("", "")); }
}
