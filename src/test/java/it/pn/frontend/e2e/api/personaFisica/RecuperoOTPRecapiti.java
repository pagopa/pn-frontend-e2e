package it.pn.frontend.e2e.api.personaFisica;

import lombok.Data;
import net.minidev.json.writer.BeansMapper;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Data
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RecuperoOTPRecapiti {

    private static final Logger logger = LoggerFactory.getLogger("RecuperoOTPRecapiti");
    private String startUrl;
    private final String urlEndPoint = "external-channels/verification-code/";
    private String digitalAddress;
    private String responseBody;
    private int responseCode;


    public boolean runRecuperoOTPRecapiti(String url) {
        try {
            logger.info("Run Recupero OTP Recapiti URL: "+url);

            CloseableHttpClient httpClient = HttpClients.createDefault();
            ClassicHttpRequest httpGet = ClassicRequestBuilder
                    .get(url)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .build();
            logger.info("Run Recupero OTP Recapiti httpGet: "+httpGet);

            httpClient.execute(httpGet, response -> {
                logger.info("La request get verso " + url + " ha risposto con codice : " + response.getCode() + "e la reason Phrase è " + response.getReasonPhrase());
                if (response.getCode() == 200) {
                    final HttpEntity entity = response.getEntity();
                    setResponseBody(EntityUtils.toString(entity));
                    return true;
                } else {
                    logger.info("Run Recupero OTP Recapiti response.getCode(): " +response.getCode());
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
