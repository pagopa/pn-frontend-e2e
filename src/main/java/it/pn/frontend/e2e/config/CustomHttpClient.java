package it.pn.frontend.e2e.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CustomHttpClient {
    private String baseUrlApi;
    private String authToken;

    public CustomHttpClient(String baseUrlApi, String authToken) {
        this.baseUrlApi = baseUrlApi;
        this.authToken = authToken;
    }

    public String sendHttpPostRequest(String endpoint, Map<String, String> headers) throws Exception {
        String apiUrl = baseUrlApi + endpoint;
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();

        // Set header auth
        if (authToken != null && !authToken.isEmpty()) {
            connection.setRequestProperty("Authorization", "Bearer " + authToken);
        }

        // Add custom headers
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        
        // Check response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            return content.toString();
        } else {
            throw new RuntimeException("Failed to execute Post Request");
        }
    }
}
