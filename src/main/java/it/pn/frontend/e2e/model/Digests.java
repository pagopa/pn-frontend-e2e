package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Digests {
    private String sha256;

    public Digests() {
        this.sha256 = "z18S3aiGYNsz4FMCZSkuQhnF7yxcqkt1ehJlFc7c88I=";
    }

    public Digests(String sha256) {
        this.sha256 = sha256;
    }

    @JsonProperty("sha256")
    public String getSha256() {
        return sha256;
    }

    @JsonProperty("sha256")
    public void setSha256(String value) {
        this.sha256 = value;
    }
}
