package it.pn.frontend.e2e.api.personaGiuridica;

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

public class CreazioneDelega {
    private static final Logger logger = LoggerFactory.getLogger("CreazioneDelega");

    private String url;

    private String body;

    private String authorizationToken;
    private String responseBody;
    private int responseCode;

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public boolean runCreazioneDelegheApi(){
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            ClassicHttpRequest httpPost = ClassicRequestBuilder
                    .post(url)
                    .addHeader(HttpHeaders.AUTHORIZATION, this.authorizationToken)
                    .setEntity(body)
                    .addHeader(HttpHeaders.CONTENT_TYPE,"application/json")
                    .build();
            httpClient.execute(httpPost, response -> {
                logger.info("La request ha risposto con:"+response.getCode()+" "+response.getReasonPhrase());
                if (response.getCode()==201){
                    final HttpEntity entity = response.getEntity();
                    setResponseBody(EntityUtils.toString(entity));
                    return true;
                }else {
                    this.responseCode = response.getCode();
                    return false;
                }
            });
        }catch (IOException e){
            return false;
        }
        return this.responseBody!=null;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}




