package it.pn.frontend.e2e.model.delegate;

import lombok.Data;

import java.util.List;

@Data
public class DelegateRequestPF {
    private String dateto;
    private DelegatePF delegate;
    private String verificationCode;
    private List<String> visibilityIds;
}
