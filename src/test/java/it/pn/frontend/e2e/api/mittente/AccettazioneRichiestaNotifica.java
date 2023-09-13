package it.pn.frontend.e2e.api.mittente;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class AccettazioneRichiestaNotifica {
    private static final Logger logger = LoggerFactory.getLogger("AccettazioneRichiestaNotifica");

    private String notificationRequestId;

    private final String xApikey = "5ae98457-3428-428f-a419-967ce9f08ac6";

    private String richiestaNotificaEndPoint;

    private String responseBody;

    private String responseReasonPhrase;

    public boolean runGetRichiestaNotifica(){
        try{

            CloseableHttpClient httpclient = HttpClients.createDefault();
            ClassicHttpRequest httpGet = ClassicRequestBuilder
                    .get(this.getRichiestaNotificaEndPoint())
                    .addHeader(HttpHeaders.CONTENT_TYPE,"application/json")
                    .addHeader("x-api-key",this.xApikey)
                    .addParameter("notificationRequestId",this.notificationRequestId)
                    .build();
            httpclient.execute(httpGet, response -> {
                logger.info(response.getCode() + " " + response.getReasonPhrase());
                setResponseReasonPhrase(response.getReasonPhrase());
                if (response.getCode() == 200){
                    final HttpEntity entity = response.getEntity();
                    this.responseBody = EntityUtils.toString(entity);
                    return true;
                }else {
                    return false;
                }

            });
        }catch (IOException e){
            Assert.fail(e.getMessage());
        }
        if (this.responseBody != null){
            return true;
        }else {
            return false;
        }
    }

    public String getRichiestaNotificaEndPoint() {
        return richiestaNotificaEndPoint;
    }

    public void setRichiestaNotificaEndPoint(String richiestaNotificaEndPoint) {
        this.richiestaNotificaEndPoint = richiestaNotificaEndPoint;
    }

    public void setNotificationRequestId(String notificationRequestId) {
        this.notificationRequestId = notificationRequestId;
    }

    public String getresponseBody() {
        return responseBody;
    }

    public String getStatusNotifica() {
        String body = getresponseBody();
        List<String> results = Splitter.on(CharMatcher.anyOf(",:")).splitToList(body);
        String result = results.get(results.size()-3);
        String statusNotifica = result.substring(1,result.length()-1);
        return statusNotifica;
    }

    public String getCodiceIUN() {
        String body = getresponseBody();
        List<String> results = Splitter.on(CharMatcher.anyOf(",:")).splitToList(body);
        String result = results.get(results.size()-1);
        String codiceIUN = result.substring(1,result.length()-2);
        return codiceIUN;
    }
    public String getResponseReasonPhrase() {
        return responseReasonPhrase;
    }

    public void setResponseReasonPhrase(String responseReasonPhrase) {
        this.responseReasonPhrase = responseReasonPhrase;
    }

}
