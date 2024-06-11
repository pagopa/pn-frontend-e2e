package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.java.DocStringType;
import lombok.Data;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-06-07T16:01:53.520944300+02:00[Europe/Rome]")
@Data
public class PreLoadRequest {
    public static final String JSON_PROPERTY_PRELOAD_IDX = "preloadIdx";
    private String preloadIdx;

    public static final String JSON_PROPERTY_CONTENT_TYPE = "contentType";
    private String contentType;

    public static final String JSON_PROPERTY_SHA256 = "sha256";
    private String sha256;

    public PreLoadRequest() {
    }

    public PreLoadRequest preloadIdx(String preloadIdx) {

        this.preloadIdx = preloadIdx;
        return this;
    }

}
