package it.frontend.e2e.framework.web.model;

import it.frontend.e2e.framework.core.model.Location;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.net.URL;

@Value
@AllArgsConstructor(staticName = "of")
public class WebLocation implements Location {
    String location;
}
