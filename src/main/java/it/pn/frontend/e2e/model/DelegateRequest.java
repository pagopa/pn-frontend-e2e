package it.pn.frontend.e2e.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DelegateRequest {
    private String dateto;
    private Delegate delegate;
    private String verificationCode;
    private List<String> visibilityIds;
}
