package it.frontend.e2e.framework.web.model;

import it.frontend.e2e.framework.core.model.location.Location;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class Url implements Location {
    String url;

    @Override
    public void validate(String location) {

    }
}
