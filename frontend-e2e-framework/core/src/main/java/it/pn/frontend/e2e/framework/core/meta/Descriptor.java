package it.pn.frontend.e2e.framework.core.meta;

import it.pn.frontend.e2e.framework.core.meta.model.Constraint;
import it.pn.frontend.e2e.framework.core.meta.model.Scope;

import java.util.List;
import java.util.Map;

public final class Descriptor {
    public int schemaVersion;
    public String domainId;
    public Map<String, String> entities;
    public Map<String, Scope> scopes;
    public List<Constraint> constraints;
}

