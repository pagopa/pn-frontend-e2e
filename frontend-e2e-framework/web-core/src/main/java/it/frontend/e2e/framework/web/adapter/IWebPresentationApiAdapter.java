package it.frontend.e2e.framework.web.adapter;

import it.frontend.e2e.framework.core.adapter.IPresentationApiAdapter;
import it.frontend.e2e.framework.web.adapter.enums.Browser;
import it.frontend.e2e.framework.web.adapter.model.BrowserSettings;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.frontend.e2e.framework.web.model.WebPresentationElement;
import it.frontend.e2e.framework.web.model.WebSelector;

import java.util.Optional;

public interface IWebPresentationApiAdapter extends IPresentationApiAdapter<WebSelector, WebLocation, WebPresentationElement> {

}
