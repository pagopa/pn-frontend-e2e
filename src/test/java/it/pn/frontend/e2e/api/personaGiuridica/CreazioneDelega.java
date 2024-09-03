package it.pn.frontend.e2e.api.personaGiuridica;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CreazioneDelega {
    private static final Logger logger = LoggerFactory.getLogger("CreazioneDelega");
    private String url;
    private String responseBody;

    public String getResponseBody() {
        return responseBody;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}




