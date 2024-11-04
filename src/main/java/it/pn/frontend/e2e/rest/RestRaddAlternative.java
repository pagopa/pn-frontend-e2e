package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.listeners.Hooks;
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
/*
 *Dettagli delle modifiche
Iniezione di NotificationSingleton e WebDriverConfig con @Autowired: Elimina la necessità di gestire manualmente le istanze, rendendo la classe più gestibile e scalabile.

Logging degli errori: Per un'analisi degli errori migliorata, è stato aggiunto il logging dettagliato per gestire le eccezioni durante le richieste HTTP.

Refactoring del costruttore: La variabile token è ora impostata tramite il costruttore e mantenuta come final per garantire che venga impostata solo una volta all'inizializzazione.
 *
 */

@Slf4j
@Component
public class RestRaddAlternative {


    private final Map<String, String> headers = new HashMap<>();
    private final String uid = UUID.randomUUID().toString();
    private final String token;

    @Autowired
    private NotificationSingleton notificationSingleton;

    @Autowired
    private WebDriverConfig webDriverConfig;

    public RestRaddAlternative(String token) {
        this.token = token;
    }

    public StartTransactionResponse startTransactionRaddAlternative(String tipoDestinatario, String codiceFiscale, String operationId) {
        CustomHttpClient<StartTransactionRequest, StartTransactionResponse> httpClientStart = new CustomHttpClient<>();
        httpClientStart.setBaseUrlApi("https://api.radd." + webDriverConfig.getEnvironment() + ".notifichedigitali.it");
        StartTransactionRequest startTransactionRequest = new StartTransactionRequest(codiceFiscale, tipoDestinatario, notificationSingleton.getIun(HooksNew.getScenario()), operationId);

        headers.put("Authorization", this.token);
        headers.put("uid", uid);

        try {
            return httpClientStart.sendHttpPostRequest("/radd-net/api/v1/act/transaction/start", headers, startTransactionRequest, StartTransactionResponse.class);
        } catch (IOException e) {
            log.error("Errore nella richiesta di Start Transaction RADD", e);
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
            log.error("Errore nella richiesta di Complete Transaction RADD", e);
            throw new RuntimeException("Errore nella richiesta di Complete Transaction RADD", e);
        }
    }
}