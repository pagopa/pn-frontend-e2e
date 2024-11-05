package it.pn.frontend.e2e.model.delegate;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
public class DelegateRequestPF {
    private String dateto;
    private DelegatePF delegate;
    private String verificationCode;
    private List<String> visibilityIds;
}
