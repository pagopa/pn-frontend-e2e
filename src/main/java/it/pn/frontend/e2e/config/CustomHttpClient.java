package it.pn.frontend.e2e.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.pn.frontend.e2e.exceptions.CustomHttpException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class CustomHttpClient<RequestType, ResponseType> {
    private static CustomHttpClient<?, ?> instance;

    private static final Logger logger = LoggerFactory.getLogger("CustomHttpClient");
    private String baseUrlApi;

    private String apiKey;


    private final CloseableHttpClient httpClient;
    private ClassicHttpRequest httpRequest;

    private CustomHttpClient() {
        this.baseUrlApi = System.getProperty("baseUrlApi");
        this.httpClient = HttpClients.createDefault();
        this.apiKey = "2b3d47f4-44c1-4b49-b6ef-54dc1c531311";
    }

    public CustomHttpClient(String baseUrlApi) {
        this.baseUrlApi = baseUrlApi;
        this.httpClient = HttpClients.createDefault();
        this.apiKey = "2b3d47f4-44c1-4b49-b6ef-54dc1c531311";
    }

    public CustomHttpClient(String baseUrlApi, String apiKeyTest) {
        this.baseUrlApi = baseUrlApi;
        this.httpClient = HttpClients.createDefault();
        this.apiKey = apiKeyTest;
    }


    public static <R, S> CustomHttpClient<R, S> getInstance() {
        if (instance == null) {
            synchronized (CustomHttpClient.class) {
                if (instance == null) {
                    instance = new CustomHttpClient<>();
                }
            }
        }
        return (CustomHttpClient<R, S>) instance;
    }

    public ResponseType sendHttpPostRequest(String endpoint, Map<String, String> headers, RequestType requestObject, Class<ResponseType> responseType) throws IOException {
        String apiUrl = baseUrlApi + endpoint;

        // Convert NewNotification to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(requestObject);

        this.httpRequest = ClassicRequestBuilder
                .post(apiUrl)
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .addHeader("x-api-key", this.apiKey)
                .setEntity(new StringEntity(jsonBody))
                .build();
        return this.httpClient.execute(httpRequest, response -> {
            if (response.getCode() == 200 || response.getCode() == 202) {
                final HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity);
                logger.info("Response body: " + responseString);
                return convertJsonToObjectType(responseString, responseType);
            } else {
                logger.error("Response code: " + response.getCode());
                throw new CustomHttpException("Error in HTTP request: " + response.getCode());
            }
        });
    }

    // Esempio di un metodo di conversione da JSON a un tipo specificato
    private <R> R convertJsonToObjectType(String jsonString, Class<R> responseType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            logger.info("Converting JSON to object: " + jsonString);
            return objectMapper.readValue(jsonString, responseType);
        } catch (IOException e) {
            logger.error("Error converting JSON to object: " + e.getMessage());
            throw new CustomHttpException("Error in HTTP request: " + e.getMessage());
        }
    }

    public String getBaseUrlApi() {
        return baseUrlApi;
    }

    public void setBaseUrlApi(String baseUrlApi) {
        this.baseUrlApi = baseUrlApi;
    }

    public String getxApikey() {
        return apiKey;
    }
}
