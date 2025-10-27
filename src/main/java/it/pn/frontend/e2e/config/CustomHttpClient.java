package it.pn.frontend.e2e.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.pn.frontend.e2e.common.DataPopulationValue;
import it.pn.frontend.e2e.model.address.DigitalAddress;
import it.pn.frontend.e2e.model.delegate.DelegateResponsePF;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CustomHttpClient<RequestType, ResponseType> {

    private static CustomHttpClient<?, ?> instance;
    private final Gson gson = new Gson();
    @Autowired
    private WebDriverConfig webDriverConfig;

    @Setter
    @Getter
    private String baseUrlApi;

    @Setter
    @Getter
    private String apiKey;

    private final CloseableHttpClient httpClient;
    private ClassicHttpRequest httpRequest;


    public CustomHttpClient() {
        System.out.println("CustomHttpClient.......");
        this.baseUrlApi = "https://api.test.notifichedigitali.it";
        this.httpClient = HttpClients.createDefault();
        this.apiKey = DataPopulationValue.getDefaultValue(DataPopulationValue.API_KEY_TEST.key);
    }

    public CustomHttpClient(String baseUrlApi, String apiKeyTest) {
        System.out.println("CustomHttpClient1.......");

        this.baseUrlApi = baseUrlApi;
        this.httpClient = HttpClients.createDefault();
        this.apiKey = apiKeyTest;
    }

    public CustomHttpClient(CustomHttpClient<?, ?> customHttpClient) {
        System.out.println("CustomHttpClient2.......");
        instance = customHttpClient;
        this.baseUrlApi = "https://api.test.notifichedigitali.it";
        this.httpClient = HttpClients.createDefault();
        this.apiKey = DataPopulationValue.getDefaultValue(DataPopulationValue.API_KEY_TEST.key);
    }

    public CustomHttpClient(String apiKeyTest) {
        System.out.println("CustomHttpClient2.......");
        this.baseUrlApi = "https://api.test.notifichedigitali.it";
        this.httpClient = HttpClients.createDefault();
        this.apiKey = apiKeyTest;
    }


    public ResponseType sendHttpPostRequest(String endpoint, Map<String, String> headers, RequestType requestObject, Class<ResponseType> responseType) throws IOException {
        String apiUrl = baseUrlApi + endpoint;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(requestObject);
            this.httpRequest = ClassicRequestBuilder
                    .post(apiUrl)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addHeader("x-api-key", this.apiKey)
                    .setEntity(new StringEntity(jsonBody))
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            log.info("request headers {}", headers);
            log.info("request httprequest {}", httpRequest);
            log.info("request endpoint {}", endpoint);
            log.info("request requestObject {}", requestObject.toString());
            return client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    ResponseType responseObject = gson.fromJson(responseString, responseType);
                    log.info("Response entity {}", entity);
                    log.info("Response body: {}", responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.info("Response entity {}", entity);
                    log.error("Response code: {}", response.getCode());
                    log.error("Response body: {}", responseString);
                    throw new IOException("Error in HTTP request to " + apiUrl + ": " + response.getCode());
                }
            });
        }
    }

    public void sendHttpPatchRequest(String endpoint, Map<String, String> headers) throws IOException {
        String apiUrl = baseUrlApi + endpoint;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            this.httpRequest = ClassicRequestBuilder
                    .patch(apiUrl)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addHeader("x-api-key", this.apiKey)
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            client.execute(httpRequest, response -> {
                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201 || response.getCode() == 204) {
                    log.info("Response code: " + response.getCode());
                } else {
                    log.error("Response code: " + response.getCode());
                    throw new IOException("Error in HTTP request to " + apiUrl + ": " + response.getCode());
                }
                return null;
            });
        }
    }

    public List<ResponseType> sendHttpGetRequestListDigitalAddress(String endpoint, Map<String, String> headers, Class<ResponseType> responseType) throws IOException {
        String apiUrl = baseUrlApi + endpoint;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            this.httpRequest = ClassicRequestBuilder
                    .get(apiUrl)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addHeader("x-api-key", this.apiKey)
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            log.info("Request headers {}", Arrays.toString(this.httpRequest.getHeaders()));
            return client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    Type listType = new TypeToken<ArrayList<DigitalAddress>>() {
                    }.getType();
                    List<ResponseType> responseObject = gson.fromJson(responseString, listType);
                    log.info("Response body: " + responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.error("Response code: " + response.getCode());
                    log.error("Response body: " + responseString);
                    throw new IOException("Error in HTTP request to " + apiUrl + ": " + response.getCode());
                }
            });
        }
    }

    public List<ResponseType> sendHttpGetRequestListDelegate(String endpoint, Map<String, String> headers, Class<ResponseType> responseType) throws IOException {
        String apiUrl = baseUrlApi + endpoint;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            this.httpRequest = ClassicRequestBuilder
                    .get(apiUrl)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addHeader("x-api-key", this.apiKey)
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            return client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    Type listType = new TypeToken<ArrayList<DelegateResponsePF>>() {
                    }.getType();
                    List<ResponseType> responseObject = gson.fromJson(responseString, listType);
                    log.info("Response body: " + responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.error("Response code: " + response.getCode());
                    log.error("Response body: " + responseString);
                    throw new IOException("Error in HTTP request to " + apiUrl + ": " + response.getCode());
                }
            });
        }
    }

    public ResponseType sendHttpGetRequest(String endpoint, Map<String, String> headers, Class<ResponseType> responseType) throws IOException {
        String apiUrl = baseUrlApi + endpoint;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            this.httpRequest = ClassicRequestBuilder
                    .get(apiUrl)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addHeader("x-api-key", this.apiKey)
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            return client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    ResponseType responseObject = gson.fromJson(responseString, responseType);
                    log.info("Response body: " + responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.error("Response code: " + response.getCode());
                    log.error("Response body: " + responseString);
                    throw new IOException("Error in HTTP request to " + apiUrl + ": " + response.getCode());
                }
            });
        }
    }

    public String getJwtToken(String TokenExchange) throws IOException {
        String env = webDriverConfig.getEnvironment();
        CloseableHttpClient client = HttpClients.createDefault();
        this.httpRequest = ClassicRequestBuilder
                .post("https://webapi." + env + ".notifichedigitali.it/token-exchange")
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .addHeader("Origin", "https://cittadini." + env + ".notifichedigitali.it")
                .setEntity(new StringEntity("{\"authorizationToken\":\"" + TokenExchange + "\"}"))
                .build();
        return client.execute(httpRequest, response -> {
            final HttpEntity entity;
            final String responseString;

            if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                entity = response.getEntity();
                // convert this response json and get the attribute "sessionToken" as string
                responseString = EntityUtils.toString(entity);
                Map<String, String> map = gson.fromJson(responseString, Map.class);
                log.info("Response body: " + map.get("sessionToken"));
                return map.get("sessionToken");
            } else {
                entity = response.getEntity();
                responseString = EntityUtils.toString(entity);
                log.error("Response code: " + response.getCode());
                log.error("Response body: " + responseString);
                throw new IOException("Error in HTTP request to " + "https://webapi." + env + ".notifichedigitali.it/token-exchange" + ": " + response.getCode());
            }
        });
    }

    public ResponseType sendHttpDeleteRequest(String endpoint, Map<String, String> headers, Class<ResponseType> responseType) throws IOException {
        String env = webDriverConfig.getEnvironment();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            this.httpRequest = ClassicRequestBuilder
                    .delete(endpoint)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addHeader("Origin", "https://cittadini." + env + ".notifichedigitali.it")
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            return client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 204) {
                    entity = response.getEntity();
                    if (entity != null) {
                        responseString = EntityUtils.toString(entity);
                        ResponseType responseObject = gson.fromJson(responseString, responseType);
                        log.info("Response body: " + responseObject);
                        return responseObject;
                    }
                    return null;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.error("Response code: " + response.getCode());
                    log.error("Response body: " + responseString);
                    throw new IOException("Error in HTTP request to " + endpoint + ": " + response.getCode());
                }
            });
        }
    }

    public List<ResponseType> sendHttpPreloadPostRequest(String endpoint, Map<String, String> headers, List<RequestType> requestObject, Class<ResponseType> responseType) throws IOException {
        String apiUrl = baseUrlApi + endpoint;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(requestObject);
            this.httpRequest = ClassicRequestBuilder
                    .post(apiUrl)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addHeader("x-api-key", this.apiKey)
                    .setEntity(new StringEntity(jsonBody))
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            return client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    List<ResponseType> responseObject = objectMapper.readValue(responseString, objectMapper.getTypeFactory().constructCollectionType(List.class, responseType));
                    log.info("Response body: {}", responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.error("Response code: {}", response.getCode());
                    log.error("Response body: {}", responseString);
                    throw new IOException("Error in HTTP request to " + apiUrl + ": " + response.getCode());
                }
            });
        }
    }

    public void sendHttpUpLoadPutRequest(String url, String secret, String sha256, Map<String, String> headers) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            this.httpRequest = ClassicRequestBuilder
                    .put(url)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .addHeader("x-amz-meta-secret", secret)
                    .addHeader("x-amz-checksum-sha256", sha256)
                    .setEntity(Files.readAllBytes(Path.of("src/test/resources/dataPopulation/fileUpload/sample.pdf")), ContentType.parse("application/pdf"))
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.info("Response upload body: {}", responseString);
                    return null;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.error("Response upload code: {}", response.getCode());
                    log.error("Response upload body: {}", responseString);
                    throw new IOException("Error in HTTP request to " + url + ": " + response.getCode());
                }
            });
        }
    }

    public void sendHttpUpLoadf24PutRequest(String url, String secret, String sha256, Map<String, String> headers, File metaDatiDocument) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            this.httpRequest = ClassicRequestBuilder
                    .put(url)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addHeader("x-amz-meta-secret", secret)
                    .addHeader("x-amz-checksum-sha256", sha256)
                    .setEntity(Files.readAllBytes(Path.of(metaDatiDocument.getPath())), ContentType.parse("application/json"))
                    .build();
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.info("Response upload F24 body: {}", responseString);
                    return null;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    log.error("Response upload F24 code: {}", response.getCode());
                    log.error("Response upload F24 body: {}", responseString);
                    throw new IOException("Error in HTTP request to " + url + ": " + response.getCode());
                }
            });
        }
    }
}
