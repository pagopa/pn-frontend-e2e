package it.pn.frontend.e2e.framework.web.adapter.model.locator;

import it.pn.frontend.e2e.framework.core.adapter.model.location.Location;
import lombok.RequiredArgsConstructor;

import java.net.URL;

@RequiredArgsConstructor
public class WebLocation implements Location {
    private final URL url;

    @Override
    public String getLocation() {
        return url.getPath();
    }
}
