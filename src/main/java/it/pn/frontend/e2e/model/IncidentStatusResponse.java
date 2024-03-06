package it.pn.frontend.e2e.model;

import it.pn.frontend.e2e.model.enums.FunctionalityEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IncidentStatusResponse {
    private List<FunctionalityEnum> functionalities;
    private List<Incident> openIncidents;
}
