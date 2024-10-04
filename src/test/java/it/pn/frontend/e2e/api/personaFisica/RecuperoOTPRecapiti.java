package it.pn.frontend.e2e.api.personaFisica;

import lombok.Data;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Data
public class RecuperoOTPRecapiti {

    private static final Logger logger = LoggerFactory.getLogger("RecuperoOTPRecapiti");
    private String startUrl;
    private final String urlEndPoint = "external-channels/verification-code/";
    private String digitalAddress;
    private String responseBody;
    private int responseCode;

    public boolean runRecuperoOTPRecapiti(String url) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            ClassicHttpRequest httpGet = ClassicRequestBuilder
                    .get(url)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .build();
            httpClient.execute(httpGet, response -> {
                logger.info("La request get verso " + url + " ha risposto con codice : " + response.getCode() + "e la reason Phrase è " + response.getReasonPhrase());
                if (response.getCode() == 200) {
                    final HttpEntity entity = response.getEntity();
                    setResponseBody(EntityUtils.toString(entity));
                    return true;
                } else {
                    this.responseCode = response.getCode();
                    return false;
                }
            });
        } catch (IOException e) {
            return false;
        }
        return this.responseBody != null;
    }
}
