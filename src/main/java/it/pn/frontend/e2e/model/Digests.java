package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Digests {
    private String sha256;

    public Digests() {
        this.sha256 = "jezIVxlG1M1woCSUngM6KipUN3/p8cG5RMIPnuEanlE=";
    }

    public Digests(String sha256) {
        this.sha256 = sha256;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String value) {
        this.sha256 = value;
    }
}
