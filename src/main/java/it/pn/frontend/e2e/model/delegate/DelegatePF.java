package it.pn.frontend.e2e.model.delegate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DelegatePF {
    private String displayName;
    private String firstName;
    private String fiscalCode;
    private String lastName;
    private boolean person;
}
