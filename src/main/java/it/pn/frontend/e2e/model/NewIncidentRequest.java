package it.pn.frontend.e2e.model;

import it.pn.frontend.e2e.model.enums.FunctionalityEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewIncidentRequest {
    private FunctionalityEnum functionality;
    private String sourceType;
    private String status;
    private String timestamp;
}
