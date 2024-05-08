package it.pn.frontend.e2e.model.singleton;

import it.pn.frontend.e2e.rest.RestContact;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationSingleton {

    private Map<String, String> scenarioIun = new ConcurrentHashMap<>();
    private static NotificationSingleton instance;

    private void NotificationSingleton(){
    }

    public static synchronized NotificationSingleton getInstance() {
        if (instance == null) {
            instance = new NotificationSingleton();
        }
        return instance;
    }

    public void setScenarioIun(String scenarioName,String iun){
        scenarioIun.put(scenarioName,iun);
    }

    public String getIun(String scenarioName){
        return scenarioIun.get(scenarioName);
    }
}