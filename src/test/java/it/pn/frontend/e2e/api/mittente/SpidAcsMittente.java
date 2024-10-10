package it.pn.frontend.e2e.api.mittente;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpidAcsMittente {

    private static final Logger logger = LoggerFactory.getLogger("SpidAcsMittente");
    private String spidAcsEndPoint;
    private final String relayState;
    private final String samlResponse;
    private String bearerToken;
    private final BasicCookieStore cookieStore;
    private final Map<String, String> spidAcsMittenteResponse = new HashMap<>();

    public SpidAcsMittente( String samlResponse, String relayState,BasicCookieStore cookieStore){
        this.samlResponse = samlResponse;
        this.relayState = relayState;
        this.cookieStore = cookieStore;
    }

    public void runSpidAcs(){
        try{
            CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).disableRedirectHandling().build();
            ClassicHttpRequest httpPost = ClassicRequestBuilder
                    .post(this.spidAcsEndPoint)
                    .addHeader(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded")
                    .addParameter("RelayState", "")
                    .addParameter("SAMLResponse", this.samlResponse)
                    .build();

            httpclient.execute(httpPost, classicHttpResponse -> {
                logger.info(classicHttpResponse.getCode() + " " + classicHttpResponse.getReasonPhrase());
                Header[] headers = classicHttpResponse.getHeaders();
                for (Header header : headers) {
                    logger.info("Key : " + header.getName()
                            + " ,Value : " + header.getValue());
                }
                spidAcsMittenteResponse.put("responseCode", String.valueOf(classicHttpResponse.getCode()));
                spidAcsMittenteResponse.put("urlPortale",classicHttpResponse.getHeader("Location").getValue());
                logger.info(classicHttpResponse.getHeader("Location").getValue());
                logger.info(spidAcsMittenteResponse.get("urlPortale"));
                return null;
            });

        }catch (IOException e){
            Assertions.fail(e.getMessage());
        }
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public void setSpidAcsEndPoint(String spidAcsEndPoint) {
        this.spidAcsEndPoint = spidAcsEndPoint;
    }

    public Map<String, String> getSpidAcsMittenteResponse() {
        return spidAcsMittenteResponse;
    }
}
