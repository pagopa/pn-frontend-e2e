package it.pn.frontend.e2e.model.delegate;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DelegateRequestPG {
    private String dateto;
    private DelegatePG delegate;
    private String verificationCode;
    private List<String> visibilityIds;
}
