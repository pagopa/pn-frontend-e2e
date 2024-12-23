package it.pn.frontend.e2e.model.singleton;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/*
* Modifiche Effettuate:
Annotazione @Component: Per rendere la classe un bean Spring, in modo che venga automaticamente gestita come singleton.
Rimozione del Metodo getInstance: Non è più necessario gestire manualmente l’istanza; Spring gestirà la creazione e l’unicità del bean.
Documentazione: Ho aggiunto i JavaDoc ai metodi setScenarioIun e getIun per migliorare la comprensibilità.
* */
@Component
public class NotificationSingleton {

    private final Map<String, String> scenarioIun = new ConcurrentHashMap<>();

    /**
     * Setta un identificatore unico di notifica (IUN) per uno specifico scenario.
     *
     * @param scenarioName nome dello scenario
     * @param iun identificatore unico della notifica
     */
    public void setScenarioIun(String scenarioName, String iun) {
        scenarioIun.put(scenarioName, iun);
    }

    /**
     * Recupera l'identificatore unico di notifica (IUN) per uno specifico scenario.
     *
     * @param scenarioName nome dello scenario
     * @return l'identificatore unico della notifica, o null se non trovato
     */
    public String getIun(String scenarioName) {
        return scenarioIun.get(scenarioName);
    }
}