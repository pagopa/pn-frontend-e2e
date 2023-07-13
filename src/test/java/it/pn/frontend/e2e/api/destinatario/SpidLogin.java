package it.pn.frontend.e2e.api.destinatario;
import java.io.IOException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;


public class SpidLogin {

    private static final Logger logger = LoggerFactory.getLogger("SpidLogin");
    private String spidLoginEndPoint;
    private String responseBody;

    private final  String authLevel;

    private final String entityID;

    public SpidLogin (String entityID, String authLevel){
        this.entityID = entityID;
        this.authLevel = authLevel;

    }

    public void runSpidLogin() {

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder
                    .get(this.getSpidLoginEndPoint())
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .addParameter("entityID", this.entityID)
                    .addParameter("authLevel", this.authLevel)
                    .build();
            this.responseBody = httpclient.execute(httpGet, response -> {
                logger.info(response.getCode() + " " + response.getReasonPhrase());
                Assert.assertEquals(this.getSpidLoginEndPoint()+" risponde con : "+response.getCode(), response.getCode(),200);
                final HttpEntity entity = response.getEntity();
                String resultContent = EntityUtils.toString(entity);
                logger.info(resultContent);
                return resultContent;
            });
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    public String getSamlRequest(){
        return this.doc().select("input[name=\"samlRequest\"]").attr("value");
    }

    public String getRelayState(){
        return this.doc().select("input[name=\"relayState\"]").attr("value");
    }

    public String getSigAlg(){
        return this.doc().select("input[name=\"sigAlg\"]").attr("value");
    }

    public String getSignature(){
        return this.doc().select("input[name=\"signature\"]").attr("value");
    }

    public String getBinding(){
        return this.doc().select("input[name=\"binding\"]").attr("value");
    }

    public String getSpidLoginEndPoint() {
        return spidLoginEndPoint;
    }

    public void setSpidLoginEndPoint(String spidLoginEndPoint) {
        this.spidLoginEndPoint = spidLoginEndPoint;
    }

    public String getResponseBody() {
        return responseBody;
    }

    private Document doc(){
        return Jsoup.parse(this.responseBody);
    }
}
