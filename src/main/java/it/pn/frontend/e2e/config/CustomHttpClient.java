package it.pn.frontend.e2e.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomHttpClient<RequestType, ResponseType> {
    private static CustomHttpClient<?, ?> instance;
    private final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger("CustomHttpClient");
    @Setter
    @Getter
    private String baseUrlApi;

    @Setter
    @Getter
    private String apiKey;

    private final CloseableHttpClient httpClient;
    private ClassicHttpRequest httpRequest;

    private CustomHttpClient() {
        this.baseUrlApi = "https://api.test.notifichedigitali.it";
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
                    ResponseType responseObject = gson.fromJson(responseString, responseType);
                    logger.info("Response body: {}", responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    logger.error("Response code: {}", response.getCode());
                    logger.error("Response body: {}", responseString);
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
                    logger.info("Response code: " + response.getCode());
                } else {
                    logger.error("Response code: " + response.getCode());
                    throw new IOException("Error in HTTP request to " + apiUrl + ": " + response.getCode());
                }
                return null;
            });
        }
    }

    public List<ResponseType> sendHttpGetRequestList(String endpoint, Map<String, String> headers, Class<ResponseType> responseType) throws IOException {
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
                    Type listType = new TypeToken<ArrayList<ResponseType>>() {
                    }.getType();
                    List<ResponseType> responseObject = gson.fromJson(responseString, listType);
                    logger.info("Response body: " + responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    logger.error("Response code: " + response.getCode());
                    logger.error("Response body: " + responseString);
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
                    logger.info("Response body: " + responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    logger.error("Response code: " + response.getCode());
                    logger.error("Response body: " + responseString);
                    throw new IOException("Error in HTTP request to " + apiUrl + ": " + response.getCode());
                }
            });
        }
    }

    public String getJwtToken(String TokenExchange) throws IOException {
        String env = System.getProperty("environment");
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
                logger.info("Response body: " + map.get("sessionToken"));
                return map.get("sessionToken");
            } else {
                entity = response.getEntity();
                responseString = EntityUtils.toString(entity);
                logger.error("Response code: " + response.getCode());
                logger.error("Response body: " + responseString);
                throw new IOException("Error in HTTP request to " + "https://webapi." + env + ".notifichedigitali.it/token-exchange" + ": " + response.getCode());
            }
        });
    }

    public ResponseType sendHttpDeleteRequest(String endpoint, Map<String, String> headers, Class<ResponseType> responseType) throws IOException {
        String env = System.getProperty("environment");
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
                        logger.info("Response body: " + responseObject);
                        return responseObject;
                    }
                    return null;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    logger.error("Response code: " + response.getCode());
                    logger.error("Response body: " + responseString);
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
                    logger.info("Response body: {}", responseObject);
                    return responseObject;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    logger.error("Response code: {}", response.getCode());
                    logger.error("Response body: {}", responseString);
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
            logger.info("\n\n\nheader della chiamata:\n{}\n\n\n", httpRequest);
            if (headers != null) {
                headers.forEach(this.httpRequest::addHeader);
            }
            client.execute(httpRequest, response -> {
                final HttpEntity entity;
                final String responseString;

                if (response.getCode() == 200 || response.getCode() == 202 || response.getCode() == 201) {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    logger.info("Response upload body: {}", responseString);
                    return null;
                } else {
                    entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    logger.error("Response upload code: {}", response.getCode());
                    logger.error("Response upload body: {}", responseString);
                    throw new IOException("Error in HTTP request to " + url + ": " + response.getCode());
                }
            });
        }
    }

    /*public ResponseType sendHttpPutRequest(String endpoint, Map<String, String> headers, Class<ResponseType> responseType) throws IOException {

    }*/
}
