package it.pn.frontend.e2e.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.pn.frontend.e2e.model.NewNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CustomHttpClient {
    private static final Logger logger = LoggerFactory.getLogger("CustomHttpClient");

    private String baseUrlApi;
    private String authToken;

    public CustomHttpClient() {
        this.baseUrlApi = System.getProperty("baseUrlApi");
        if (System.getProperty("Authorization") != null) {
            this.authToken = System.getProperty("Authorization");
            logger.info("Authorization token found");
        } else {
            logger.error("Authorization token not found");
        }
    }

    public CustomHttpClient(String baseUrlApi, String authToken) {
        this.baseUrlApi = baseUrlApi;
        this.authToken = authToken;
    }

    public String sendHttpPostRequest(String endpoint, Map<String, String> headers, NewNotification newNotification) throws IOException {
        String apiUrl = baseUrlApi + endpoint;
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();

        // Set header auth
        if (authToken != null && !authToken.isEmpty()) {
            connection.setRequestProperty("Authorization", "Bearer " + authToken);
        }

        // Set content type to JSON
        connection.setRequestProperty("Content-Type", "application/json");

        // Add custom headers
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        // Enable input/output streams for writing/reading data
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // Convert NewNotification to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(newNotification);

        // Write JSON to the request body
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }

        // Check response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                return content.toString();
            }
        } else {
            logger.error("Failed to execute Post Request");
            return null;
//            throw new HttpErrorException("Failed to execute Post Request", responseCode);
        }
    }
}
