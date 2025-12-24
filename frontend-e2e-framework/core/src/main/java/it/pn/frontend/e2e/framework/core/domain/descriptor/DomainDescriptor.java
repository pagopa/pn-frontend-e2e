package it.pn.frontend.e2e.framework.core.domain.descriptor;

import java.util.List;
import java.util.Map;

public final class DomainDescriptor {
    public int schemaVersion;
    public String domainId;
    public Map<String, String> entities;
    public Map<String, ScopeDescriptor> scopes;
    public List<ConstraintDescriptor> constraints;
}

