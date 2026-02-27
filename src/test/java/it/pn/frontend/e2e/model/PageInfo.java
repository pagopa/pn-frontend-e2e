package it.pn.frontend.e2e.model;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.web.model.WebLocation;

public record PageInfo(WebLocation location, Class<? extends Capability> pageClass){}
