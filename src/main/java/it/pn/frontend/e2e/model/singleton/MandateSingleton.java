package it.pn.frontend.e2e.model.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MandateSingleton {
    private Map<String, String> scenarioMandateId = new ConcurrentHashMap<>();
    private Map<String, String> verificationCodeMandateId = new ConcurrentHashMap<>();
    private static MandateSingleton instance;

    private void MandateSingleton(){
    }

    public static synchronized MandateSingleton getInstance() {
        if (instance == null) {
            instance = new MandateSingleton();
        }
        return instance;
    }

    public void setScenarioMandateId(String scenarioName,String mandateId){
        scenarioMandateId.put(scenarioName,mandateId);
    }

    public String getMandateId(String scenarioName){
        return scenarioMandateId.get(scenarioName);
    }

    public void setScenarioVerificationCode(String mandateId,String verificationCode){
        verificationCodeMandateId.put(mandateId,verificationCode);
    }

    public String getVerificationCode(String mandateId){
        return verificationCodeMandateId.get(mandateId);
    }
}
