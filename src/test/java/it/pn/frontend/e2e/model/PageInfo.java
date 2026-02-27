package it.pn.frontend.e2e.model;

import it.frontend.e2e.framework.web.capability.core.Gettable;

public record PageInfo(String url, Class<? extends Gettable> pageClass){}
