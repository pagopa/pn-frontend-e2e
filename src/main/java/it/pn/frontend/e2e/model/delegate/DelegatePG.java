package it.pn.frontend.e2e.model.delegate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DelegatePG {
    private String displayName;
    private String companyName;
    private String fiscalCode;
    private boolean person;
}
