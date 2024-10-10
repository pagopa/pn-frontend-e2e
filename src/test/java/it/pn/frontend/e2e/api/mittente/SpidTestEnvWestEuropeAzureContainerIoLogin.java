package it.pn.frontend.e2e.api.mittente;

import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
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


import java.io.IOException;
import java.util.List;

public class SpidTestEnvWestEuropeAzureContainerIoLogin {

    private static final Logger logger = LoggerFactory.getLogger("SpidTestenvWesteuropeAzurecontainerIoLogin");
    private String spidTestEnvWestEuropeAzureContainerIoLoginEndPoint;
    private final String requestKey;
    private final String relayState;
    private final String username;
    private final String password;
    private final BasicCookieStore cookieStore;
    private String responseBody;

    public SpidTestEnvWestEuropeAzureContainerIoLogin(String requestKey,
                                                      String relayState,
                                                      String username,
                                                      String password,
                                                      BasicCookieStore cookieStore){

        this.requestKey = requestKey;
        this.relayState = relayState;
        this.username = username;
        this.password = password;
        this.cookieStore = cookieStore;
    }

    public void runSpidTestEnvWestEuropeAzureContainerIoLogin(){
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();

        HttpClientContext context = HttpClientContext.create();
        context.setAttribute(HttpClientContext.COOKIE_STORE, this.cookieStore);

        ClassicHttpRequest httpPost = ClassicRequestBuilder
                .post(this.spidTestEnvWestEuropeAzureContainerIoLoginEndPoint)
                .addHeader(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded")
                .addParameter("request_key",this.requestKey)
                .addParameter("relay_state", this.relayState)
                .addParameter("username", this.username)
                .addParameter("password", this.password)
                .addParameter("confirm","")
                .addParameter("wrong_conditions_notbefore","")
                .addParameter("wrong_conditions_notonorafter","")
                .addParameter("wrong_subj_notonorafter","")
                .addParameter("wrong_subj_notonorafter","")
                .addParameter("spid_level","")
                .addParameter("sign_assertion","on")
                .addParameter("sign_message","on")
                .addParameter("private_key","")
                .addParameter("public_key","")
                .build();
        try {
            this.responseBody = httpclient.execute(httpPost, classicHttpResponse -> {
                logger.info(classicHttpResponse.getCode() + " " + classicHttpResponse.getReasonPhrase());
                List<Cookie> cookieList =context.getCookieStore().getCookies();
                for (Cookie cookie : cookieList){
                    logger.info(cookie.getName());
                    logger.info(cookie.getValue());
                    logger.info(cookie.getPath());
                    logger.info(String.valueOf(cookie.isHttpOnly()));
                }
                Assertions.assertEquals(classicHttpResponse.getCode(),200);
                final HttpEntity entity = classicHttpResponse.getEntity();
                String resultContent = EntityUtils.toString(entity);
                logger.info(resultContent);
                return resultContent;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSpidTestEnvWestEuropeAzureContainerIoLoginEndPoint(String spidTestEnvWestEuropeAzureContainerIoLoginEndPoint) {
        this.spidTestEnvWestEuropeAzureContainerIoLoginEndPoint = spidTestEnvWestEuropeAzureContainerIoLoginEndPoint;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getRequestKeyOutput(){
        return this.doc().select("input[name='request_key']").attr("value");
    }

    private Document doc() {
        return Jsoup.parse(this.responseBody);
    }



}
