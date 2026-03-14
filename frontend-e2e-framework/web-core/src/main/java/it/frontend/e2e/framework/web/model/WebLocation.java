package it.frontend.e2e.framework.web.model;

import it.frontend.e2e.framework.core.model.location.Location;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class WebLocation implements Location {
    String location;

    @Override
    public void validate(String location) {

    }
}
