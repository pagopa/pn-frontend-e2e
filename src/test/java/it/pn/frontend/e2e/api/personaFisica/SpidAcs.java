package it.pn.frontend.e2e.api.personaFisica;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpidAcs {

    private static final Logger logger = LoggerFactory.getLogger("SpidAcs");
    private String spidAcsEndPoint;
    private Map<String, String> headerLocal;
    private final String relayStateInput;
    private final String samlResponseInput;

    public SpidAcs(String relayStateInput, String samlResponseInput){
        this.relayStateInput = relayStateInput;
        this.samlResponseInput = samlResponseInput;
    }

    public void runSpidAcs(){
        try{
            CloseableHttpClient httpclient = HttpClients.custom().disableRedirectHandling().build();
            ClassicHttpRequest httpPost = ClassicRequestBuilder
                    .post(this.getSpidAcsEndPoint())
                    .addHeader(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded")
                    .addParameter("RelayState", this.relayStateInput)
                    .addParameter("SAMLResponse", this.samlResponseInput)
                    .build();
            this.headerLocal = httpclient.execute(httpPost, classicHttpResponse -> {
                logger.info(classicHttpResponse.getCode() + " " + classicHttpResponse.getReasonPhrase());
                Header[] headers = classicHttpResponse.getHeaders();
                for (Header header : headers) {
                    logger.info("Key : " + header.getName()
                            + " ,Value : " + header.getValue());
                }
                Map<String, String> hm = new HashMap<>();
                hm.put("responseCode", String.valueOf(classicHttpResponse.getCode()));
                hm.put("urlPortale",classicHttpResponse.getHeader("Location").getValue());
                logger.info(classicHttpResponse.getHeader("Location").getValue());
                logger.info(hm.get("urlPortale"));
                return hm;
            });
        }catch (IOException e){
            Assertions.fail(e.getMessage());
        }
    }

    public String getSpidAcsEndPoint() {
        return spidAcsEndPoint;
    }

    public void setSpidAcsEndPoint(String spidAcsEndPoint) {
        this.spidAcsEndPoint = spidAcsEndPoint;
    }

    public Map<String, String> getHeaderLocal() {
        return headerLocal;
    }
}
