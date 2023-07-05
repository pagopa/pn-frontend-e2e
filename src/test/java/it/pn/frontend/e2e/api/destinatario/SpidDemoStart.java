package it.pn.frontend.e2e.api.destinatario;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
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

public class SpidDemoStart {

    private static final Logger logger = LoggerFactory.getLogger("SpidDemoStart");
    private String spidDemoStartEndPoint;
    private String responseBody;

    /*
    parametri di input
     */
    private final String samlRequestInput;
    private final String relayStateInput;
    private final String sigAlgInput;
    private final String signatureInput;
    private final String bindingInput;

    /*
    end parametri di input
     */


    public SpidDemoStart (String samlRequestInput,
                          String relayStateInput,
                          String sigAlgInput,
                          String signatureInput,
                          String bindingInput){
        this.samlRequestInput = samlRequestInput;
        this.relayStateInput = relayStateInput;
        this.sigAlgInput = sigAlgInput;
        this.signatureInput = signatureInput;
        this.bindingInput = bindingInput;
    }


    public void runSpidDemoStart(){
        try  {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            ClassicHttpRequest httpPost = ClassicRequestBuilder
                    .post(this.getSpidDemoStartEndPoint())
                    .addHeader(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded")
                    .addParameter("samlRequest",this.samlRequestInput)
                    .addParameter("relayState", this.relayStateInput)
                    .addParameter("sigAlg", this.sigAlgInput)
                    .addParameter("signature", this.signatureInput)
                    .addParameter("binding", this.bindingInput)
                    .build();
            this.responseBody = httpclient.execute(httpPost, classicHttpResponse -> {
                logger.info(classicHttpResponse.getCode() + " " + classicHttpResponse.getReasonPhrase());
                Assert.assertEquals(this.getSpidDemoStartEndPoint()+" risponde con : "+classicHttpResponse.getCode(), classicHttpResponse.getCode(),200);
                final HttpEntity entity = classicHttpResponse.getEntity();
                String resultContent = EntityUtils.toString(entity);
                logger.info(resultContent);
                return resultContent;
            });
        }catch (IOException e){
            Assert.fail(e.getMessage());
        }
    }


    public String getSpidDemoStartEndPoint() {
        return spidDemoStartEndPoint;
    }

    public void setSpidDemoStartEndPoint(String spidDemoStartEndPoint) {
        this.spidDemoStartEndPoint = spidDemoStartEndPoint;
    }

    public String getResponseBody() {
        return responseBody;
    }

    private Document doc(){
        return Jsoup.parse(this.responseBody);
    }

    public String getSpidLevelOutput() {
        return this.doc().select("input#spidLevel").attr("value");
    }

    public String getOrganizationDisplayNameOutput() {
        return this.doc().select("input#organizationDisplayName").attr("value");
    }

    public String getSamlRequestOutput() {
        return this.doc().select("input#samlRequest").attr("value");
    }

    public String getRelayStateOutput() {
        return this.doc().select("input#relayState").attr("value");
    }

    public String getSigAlgOutput() {
        return this.doc().select("input#sigAlg").attr("value");
    }

    public String getSignatureOutput() {
        return this.doc().select("input#signature").attr("value");
    }

    public String getPurposeOutput() {
        return this.doc().select("input#purpose").attr("value");
    }

    public String getMinAgeOutput() {
        return this.doc().select("input#minAge").attr("value");
    }

    public String getMaxAgeOutput() {
        return this.doc().select("input#maxAge").attr("value");
    }

    public String getRetryOutput() {
        return this.doc().select("input#retry").attr("value");
    }
}
