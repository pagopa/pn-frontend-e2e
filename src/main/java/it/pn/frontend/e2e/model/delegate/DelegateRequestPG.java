package it.pn.frontend.e2e.model.delegate;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DelegateRequestPG {
    private String dateto;
    private DelegatePG delegate;
    private String verificationCode;
    private List<String> visibilityIds;
}
