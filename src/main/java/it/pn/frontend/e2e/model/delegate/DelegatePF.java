package it.pn.frontend.e2e.model.delegate;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class DelegatePF {
    private String displayName;
    private String firstName;
    private String fiscalCode;
    private String lastName;
    private boolean person;


}
