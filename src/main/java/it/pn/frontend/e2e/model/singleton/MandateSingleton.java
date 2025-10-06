package it.pn.frontend.e2e.model.singleton;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class MandateSingleton {

    private final Map<String, String> scenarioMandateId = new ConcurrentHashMap<>();
    private final Map<String, String> verificationCodeMandateId = new ConcurrentHashMap<>();

    // Spring Boot gestisce automaticamente l'istanza Singleton per i componenti
    public void setScenarioMandateId(String scenarioName, String mandateId) {
        scenarioMandateId.put(scenarioName, mandateId);
    }

    public String getMandateId(String scenarioName) {
        return scenarioMandateId.get(scenarioName);
    }

    public void setScenarioVerificationCode(String mandateId, String verificationCode) {
        verificationCodeMandateId.put(mandateId, verificationCode);
    }

    public String getVerificationCode(String mandateId) {
        return verificationCodeMandateId.get(mandateId);
    }
}
