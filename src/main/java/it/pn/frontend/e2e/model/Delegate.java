package it.pn.frontend.e2e.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Delegate {
    private String displayName;
    private String firstName;
    private String fiscalCode;
    private String lastName;
    private boolean person;
}
