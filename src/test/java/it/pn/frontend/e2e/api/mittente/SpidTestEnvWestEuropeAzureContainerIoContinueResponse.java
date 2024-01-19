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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

public class SpidTestEnvWestEuropeAzureContainerIoContinueResponse {

    private static final Logger logger = LoggerFactory.getLogger("SpidTestenvWesteuropeAzurecontainerIoContinueResponse");
    private String spidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint;
    private final String requestKey;
    private final BasicCookieStore cookieStore;
    private String responseBody;

    public SpidTestEnvWestEuropeAzureContainerIoContinueResponse(
            String requestKey, BasicCookieStore cookieStore
    ){
        this.requestKey = requestKey;
        this.cookieStore = cookieStore;
    }

    public void runSpidTestEnvWestEuropeAzureContainerIoContinueResponse(){
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();

        HttpClientContext context = HttpClientContext.create();
        context.setAttribute(HttpClientContext.COOKIE_STORE, this.cookieStore);


        ClassicHttpRequest httpPost = ClassicRequestBuilder
                .post(this.spidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint)
                .addHeader(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded")
                .addParameter("request_key",this.requestKey)
                .addParameter("confirm", "")
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
                Assert.assertEquals( this.spidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint +" risponde con : "+classicHttpResponse.getCode(),classicHttpResponse.getCode(),200);
                final HttpEntity entity = classicHttpResponse.getEntity();
                String resultContent = EntityUtils.toString(entity);
                logger.info(resultContent);
                return resultContent;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSpidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint(String spidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint) {
        this.spidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint = spidTestEnvWestEuropeAzureContainerIoContinueResponseEndPoint;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getRelayStateOutput(){
        return this.doc().select("input[name='RelayState']").attr("value");
    }

    public String getSamlResponseOutput(){
        return this.doc().select("input[name='SAMLResponse']").attr("value");
    }

    private Document doc() {
        return Jsoup.parse(this.responseBody);
    }



}
