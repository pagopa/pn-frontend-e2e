package it.pn.frontend.e2e.api.mittente;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.Assertion;

import java.io.IOException;

public class SpidLoginMittente {

    private static final Logger logger = LoggerFactory.getLogger("SpidLoginMittente");
    private final String entityID;
    private final String authLevel;
    private String spidLoginMittenteEndPoint;
    private String cookieName;
    private String cookieValue;
    private String cookieDomain;
    private String cookiePath;
    private boolean cookieHttpOnly;
    private String responseBody;

    public SpidLoginMittente(String entityID, String authLevel) {
        this.entityID = entityID;
        this.authLevel = authLevel;
    }

    public void runSpidLoginMittente() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpClientContext context = HttpClientContext.create();
            ClassicHttpRequest httpGet = ClassicRequestBuilder
                    .get(this.getSpidLoginMittenteEndPoint())
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addParameter("entityID", this.entityID)
                    .addParameter("authLevel", this.authLevel)
                    .build();

            httpclient.execute(httpGet, context, response -> {
                logger.info(response.getCode() + " " + response.getReasonPhrase());
                Assertions.assertEquals(response.getCode(), 200);
                final HttpEntity entity = response.getEntity();
                this.responseBody = EntityUtils.toString(entity);
                this.cookieName = context.getCookieStore().getCookies().get(0).getName();
                this.cookieValue = context.getCookieStore().getCookies().get(0).getValue();
                this.cookieDomain = context.getCookieStore().getCookies().get(0).getDomain();
                this.cookiePath = context.getCookieStore().getCookies().get(0).getPath();
                this.cookieHttpOnly = context.getCookieStore().getCookies().get(0).isHttpOnly();
                return null;
            });

        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
    }


    public String getSpidLoginMittenteEndPoint() {
        return spidLoginMittenteEndPoint;
    }

    public void setSpidLoginMittenteEndPoint(String spidLoginMittenteEndPoint) {
        this.spidLoginMittenteEndPoint = spidLoginMittenteEndPoint;
    }

    public String getCookieName() {
        return cookieName;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public boolean getCookieHttpOnly() {
        return cookieHttpOnly;
    }

    public String getResponseBody() {
        return responseBody;
    }

    private Document doc() {
        return Jsoup.parse(this.responseBody);
    }

    public String getRequestKey() {
        return this.doc().select("input[name='request_key']").attr("value");
    }

    public String getRelayState() {
        return this.doc().select("input[name='relay_state']").attr("value");
    }

}
