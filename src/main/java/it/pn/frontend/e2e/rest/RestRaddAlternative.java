package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.radd.CompleteTransaction.CompleteTransactionRequest;
import it.pn.frontend.e2e.model.radd.CompleteTransaction.CompleteTransactionResponse;
import it.pn.frontend.e2e.model.radd.StartTransaction.StartTransactionRequest;
import it.pn.frontend.e2e.model.radd.StartTransaction.StartTransactionResponse;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.utility.DataPopulation;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class RestRaddAlternative {

    NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();
    private final Map<String, String> headers = new HashMap<>();
    private final String token;
    private final String env = System.getProperty("environment");
    private final String uid = UUID.randomUUID().toString();


    public RestRaddAlternative(String token) {
        this.token = token;
    }

    public StartTransactionResponse startTransactionRaddAlternative(String tipoDestinatario, String codiceFiscale, String operationId) {
        final CustomHttpClient<StartTransactionRequest, StartTransactionResponse> httpClientStart = new CustomHttpClient<>();
        httpClientStart.setBaseUrlApi("https://api.radd."+ env + ".notifichedigitali.it");
        final StartTransactionRequest startTransactionRequest = new StartTransactionRequest(codiceFiscale,tipoDestinatario, notificationSingleton.getIun(Hooks.getScenario()),operationId);

        try {
            headers.put("Authorization", this.token);
            headers.put("uid", uid);
            StartTransactionResponse response = httpClientStart.sendHttpPostRequest("/radd-net/api/v1/act/transaction/start", headers, startTransactionRequest, StartTransactionResponse.class);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompleteTransactionResponse completeTransactionRaddAlternative(String operationId) {
        final CustomHttpClient<CompleteTransactionRequest, CompleteTransactionResponse> httpClientComplete = new CustomHttpClient<>();
        final CompleteTransactionRequest completeTransactionRequest = new CompleteTransactionRequest(operationId);

        httpClientComplete.setBaseUrlApi("https://api.radd."+ env + ".notifichedigitali.it");
        try {
            CompleteTransactionResponse response = httpClientComplete.sendHttpPostRequest("/radd-net/api/v1/act/transaction/complete", headers, completeTransactionRequest, CompleteTransactionResponse.class);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
