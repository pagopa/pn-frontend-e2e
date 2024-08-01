package it.pn.frontend.e2e.model.incidents;

import it.pn.frontend.e2e.model.enums.FunctionalityEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Incident {
    private FunctionalityEnum functionality;
    private String status;
    private Date startDate;
    private Date endDate;
}
