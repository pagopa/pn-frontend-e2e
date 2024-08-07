package it.pn.frontend.e2e.model.documents;

import lombok.Data;

@Data
public class Digests {
    private String sha256;

    public Digests() {
        this.sha256 = "jezIVxlG1M1woCSUngM6KipUN3/p8cG5RMIPnuEanlE=";
    }

    public Digests(String sha256) {
        this.sha256 = sha256;
    }
}
