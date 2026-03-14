package it.frontend.e2e.framework.core.binder.context;

import it.frontend.e2e.framework.core.capability.context.CapabilityScope;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BindContext {
    private final CapabilityScope scope;
    public static BindContext root() { return new BindContext(new CapabilityScope("", "")); }

    @Override
    public String toString() {
        String parent = (scope == null) ? "" : scope.toString().trim();
        return parent.isEmpty() ? "" : parent;
    }
}
