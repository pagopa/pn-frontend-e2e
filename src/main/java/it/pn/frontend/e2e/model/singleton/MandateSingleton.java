package it.pn.frontend.e2e.model.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/*
*Modifiche Apportate:
Annotazione @Component: La classe è annotata con @Component, permettendo a Spring di gestire l'istanza come un Singleton automaticamente.
Rimozione del metodo getInstance: Con l'uso di @Component, non è necessario implementare il pattern Singleton manualmente. Spring gestirà una sola istanza di questa classe nel contesto dell'applicazione.
Costruttore privato rimosso: Non è necessario bloccare il costruttore dato che Spring garantisce l’unicità dell’istanza.
* */

@Component
public class MandateSingleton {

    private static final Logger logger = LoggerFactory.getLogger(MandateSingleton.class);

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
        logger.info("verificationCodeMandateId {}", verificationCodeMandateId.values());
        return verificationCodeMandateId.get(mandateId);
    }
}
