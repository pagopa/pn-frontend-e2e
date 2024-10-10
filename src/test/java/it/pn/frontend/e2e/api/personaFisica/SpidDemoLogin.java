package it.pn.frontend.e2e.api.personaFisica;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
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
public class SpidDemoLogin {

    private static final Logger logger = LoggerFactory.getLogger("SpidDemoLogin");
    private String spidDemoLoginEndPoint;
    private String responseBody;
    private final String username;
    private final String password;
    private final String spidLevelInput;
    private final String organizationDisplayNameInput;
    private final String samlRequestInput;
    private final String relayStateInput;
    private final String sigAlgInput;
    private final String signatureInput;
    private final String purposeInput;
    private final String minAgeInput;
    private final String maxAgeInput;
    private final String retryInput;

    public SpidDemoLogin(String username,
                         String password, String spidLevelInput,
                         String organizationDisplayNameInput,
                         String samlRequestInput, String relayStateInput,
                         String sigAlgInput, String signatureInput,
                         String purposeInput, String minAgeInput,
                         String maxAgeInput, String retryInput){

        this.username = username;
        this.password = password;
        this.spidLevelInput = spidLevelInput;
        this.organizationDisplayNameInput = organizationDisplayNameInput;
        this.samlRequestInput = samlRequestInput;
        this.relayStateInput = relayStateInput;
        this.sigAlgInput = sigAlgInput;
        this.signatureInput = signatureInput;
        this.purposeInput = purposeInput;
        this.minAgeInput = minAgeInput;
        this.maxAgeInput = maxAgeInput;
        this.retryInput = retryInput;

    }

    public void runSpidDemoLogin(){
        try{
            CloseableHttpClient httpclient = HttpClients.createDefault();
            ClassicHttpRequest httpPost = ClassicRequestBuilder
                    .post(this.getSpidDemoLoginEndPoint())
                    .addHeader(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded")
                    .addParameter("username", this.username)
                    .addParameter("password", this.password)
                    .addParameter("params[spidLevel]", this.spidLevelInput)
                    .addParameter("params[organizationDisplayName]", this.organizationDisplayNameInput)
                    .addParameter("params[samlRequest]", this.samlRequestInput)
                    .addParameter("params[relayState]", this.relayStateInput)
                    .addParameter("params[sigAlg]",this.sigAlgInput)
                    .addParameter("params[signature]", this.signatureInput)
                    .addParameter("params[purpose]", this.purposeInput)
                    .addParameter("params[minAge]", this.minAgeInput)
                    .addParameter("params[maxAge]", this.maxAgeInput)
                    .addParameter("retry", this.retryInput)
                    .build();
            this.responseBody = httpclient.execute(httpPost, classicHttpResponse -> {
                logger.info(classicHttpResponse.getCode() + " " + classicHttpResponse.getReasonPhrase());
                Assertions.assertEquals(classicHttpResponse.getCode(),200);
                final HttpEntity entity = classicHttpResponse.getEntity();
                String resultContent = EntityUtils.toString(entity);
                logger.info(resultContent);
                return resultContent;
            });
        } catch (IOException e){
            Assertions.fail(e.getMessage());
        }
    }

    public String getSpidDemoLoginEndPoint() {
        return spidDemoLoginEndPoint;
    }

    public void setSpidDemoLoginEndPoint(String spidDemoLoginEndPoint) {
        this.spidDemoLoginEndPoint = spidDemoLoginEndPoint;
    }

    public String getResponseBody() {
        return responseBody;
    }

    private Document doc(){
        return Jsoup.parse(this.responseBody);
    }

    public String getRelayStateOutput(){
        return this.doc().select("input[name=\"RelayState\"]").attr("value");
    }

    public String getSAMLResponseOutput(){
        logger.info("SAMLResponse "+this.doc().select("input[name=\"SAMLResponse\"]").attr("value"));
        return this.doc().select("input[name=\"SAMLResponse\"]").attr("value");
    }
}
