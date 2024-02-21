package it.pn.frontend.e2e.model.delegate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class DelegateResponsePG {
    private String mandateId;
    private Delegator delegator;
    private DelegatePG delegate;
    private String status;
    private List<String> visibilityIds;
    private String verificationCode;
    private String datefrom;
    private String dateto;
    private Map<String, String> groups;

}

