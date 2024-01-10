package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {
    private Digests digests;
    private String contentType;
    private Ref ref;
    private String title;


    public Document() {
        this.digests = new Digests();
        this.contentType = "application/pdf";
        this.ref = new Ref();
        this.title = "RATA SCADUTA IMU";
    }

    public Document(Digests digests, String contentType, Ref ref, String title) {
        this.digests = digests;
        this.contentType = contentType;
        this.ref = ref;
        this.title = title;

    }

    @JsonProperty("digests")
    public Digests getDigests() {
        return digests;
    }

    @JsonProperty("digests")
    public void setDigests(Digests value) {
        this.digests = value;
    }

    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("contentType")
    public void setContentType(String value) {
        this.contentType = value;
    }

    @JsonProperty("ref")
    public Ref getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(Ref value) {
        this.ref = value;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.title = value;
    }
}
