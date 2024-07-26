package it.pn.frontend.e2e.rest;

import it.pn.frontend.e2e.config.CustomHttpClient;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.model.radd.CompleteTransaction.CompleteTransactionRequest;
import it.pn.frontend.e2e.model.radd.CompleteTransaction.CompleteTransactionResponse;
import it.pn.frontend.e2e.model.radd.StartTransaction.StartTransactionRequest;
import it.pn.frontend.e2e.model.radd.StartTransaction.StartTransactionResponse;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RestRaddAlternative{

    private static RestRaddAlternative instance;
    final CustomHttpClient<StartTransactionRequest, StartTransactionResponse> httpClientStart = CustomHttpClient.getInstance();
    final CustomHttpClient<CompleteTransactionRequest, CompleteTransactionResponse> httpClientComplete = CustomHttpClient.getInstance();
    NotificationSingleton notificationSingleton = NotificationSingleton.getInstance();
    private final Map<String, String> headers = new HashMap<>();
    final private String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlRBMi1raWQifQ.eyJhdWQiOiJodHRwczovL2FwaS5yYWRkLnRlc3Qubm90aWZpY2hlZGlnaXRhbGkuaXQiLCJpc3MiOiJUQS0yLWlzc3Vlci50ZXN0Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwianRpIjoiMTIzMTIxMzQxMTMxMzEyIiwiaWF0IjoxNzA5MjEyMTM5LCJleHAiOjE3NDA3Njk3Mzl9.an1y-p7ng37r2kJlNvlX8SsIqZBmZFNBxrMhrONGy3ClOT9RKHke9DSy2fkUj27D4Qkn-wPGlQ9pL7u__3Ym20PFJxhCE5eAUesLOETwlyR2157bbwf3lJX7dhNgamjgmufEROANC17gmR6oq8CeX5glZArDunnFGwxFiPsaHqKqXCKps5qbfZ888LcV576YPvpYfkhHqAiEVAa7fJFBX-V46Yy8zWwN-nssdQ6eYrB8TrOiw3fm9mhsTTvmuyBGmqGrXatA9QuEfN9a0hSCDkPdvsHsQg-7amxeQBjOgyOGJTdrRmgznPG5ZBbKuqYNtzIJJ0l5pMTav3D4cKgXQl0jwuGtAZXyiAQ0FoFev1lu0JnlxX_9YiAw1YYSDA0g91-UH1peLrv0W8F4-g7VTqfGBNfjXzRURNVDeMp4MYgfAPZeGNNcGNQKF4ez7sKydIlxiNmKYxXT2qx6Ve9bj6snCsCNYqT9lECJBT57xFbD9UyhUMsCdPj5MCVjcUb-Ni07ylheAWXY487n1Hjh6RnPfcy9nzTmjFjiwLtgwJmNN80URg2XO0MvJ1afNQtE9CaVGA2R1-J5EJcQMPS1MFErKY1ABgsL3uGWyk3aQI6qeDY3IQXivbAnQ_2hQHraefa5ZTbtFqufdOxKV3M5M0sy8KE848ViM7C2SKUD4kw";
    final private String env = System.getProperty("environment");
    private final String uid = "123456";
    private StartTransactionRequest startTransactionRequest = new StartTransactionRequest("",notificationSingleton.getIun(Hooks.getScenario()));
    private CompleteTransactionRequest completeTransactionRequest = new CompleteTransactionRequest();

    public static synchronized RestRaddAlternative getInstance() {
        if (instance == null) {
            instance = new RestRaddAlternative();
        }
        return instance;
    }

    public RestRaddAlternative() {
        this.httpClientStart.setBaseUrlApi("https://api.radd.notifichedigitali.it");
        if (token != null) {
            this.headers.put("Authorization", token);
            headers.put("uid", uid);
        } else {
            log.warn("Auth token non trovato, impossibile fare la richiesta HTTP in background!");
        }
    }

    public StartTransactionResponse startTransactionRaddAlternative(){
        try{
            StartTransactionResponse response = httpClientStart.sendHttpPostRequest("/radd-net/api/v1/act/transaction/start",headers,startTransactionRequest, StartTransactionResponse.class);
            log.info(response.toString());
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompleteTransactionResponse completeTransactionRaddAlternative(){
        try{
            CompleteTransactionResponse response = httpClientComplete.sendHttpPostRequest("/radd-net/api/v1/act/transaction/complete",headers,completeTransactionRequest, CompleteTransactionResponse.class);
            log.info(response.toString());
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
