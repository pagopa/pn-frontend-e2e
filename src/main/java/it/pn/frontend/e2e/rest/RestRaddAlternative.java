package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.HooksNew;
import it.pn.frontend.e2e.model.radd.CompleteTransaction.CompleteTransactionRequest;
import it.pn.frontend.e2e.model.radd.CompleteTransaction.CompleteTransactionResponse;
import it.pn.frontend.e2e.model.radd.StartTransaction.StartTransactionRequest;
import it.pn.frontend.e2e.model.radd.StartTransaction.StartTransactionResponse;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class RestRaddAlternative {


    private final Map<String, String> headers = new HashMap<>();
    private final String uid = UUID.randomUUID().toString();
    private final String token;

    @Autowired
    private NotificationSingleton notificationSingleton;

    @Autowired
    private HooksNew hooksNew;

    @Autowired
    private WebDriverConfig webDriverConfig;

    public RestRaddAlternative(String token) {
        this.token = token;
    }

    public StartTransactionResponse startTransactionRaddAlternative(String tipoDestinatario, String codiceFiscale, String operationId) {
        CustomHttpClient<StartTransactionRequest, StartTransactionResponse> httpClientStart = new CustomHttpClient<>();
        httpClientStart.setBaseUrlApi("https://api.radd." + webDriverConfig.getEnvironment() + ".notifichedigitali.it");
        StartTransactionRequest startTransactionRequest = new StartTransactionRequest(codiceFiscale, tipoDestinatario, notificationSingleton.getIun(hooksNew.getScenario()), operationId);

        headers.put("Authorization", this.token);
        headers.put("uid", uid);

        try {
            return httpClientStart.sendHttpPostRequest("/radd-net/api/v1/act/transaction/start", headers, startTransactionRequest, StartTransactionResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Errore nella richiesta di Start Transaction RADD", e);
        }
    }

    public CompleteTransactionResponse completeTransactionRaddAlternative(String operationId) {
        CustomHttpClient<CompleteTransactionRequest, CompleteTransactionResponse> httpClientComplete = new CustomHttpClient<>();
        CompleteTransactionRequest completeTransactionRequest = new CompleteTransactionRequest(operationId);
        httpClientComplete.setBaseUrlApi("https://api.radd." + webDriverConfig.getEnvironment() + ".notifichedigitali.it");

        try {
            return httpClientComplete.sendHttpPostRequest("/radd-net/api/v1/act/transaction/complete", headers, completeTransactionRequest, CompleteTransactionResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Errore nella richiesta di Complete Transaction RADD", e);
        }
    }
}