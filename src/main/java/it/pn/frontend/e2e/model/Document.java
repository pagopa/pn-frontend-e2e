package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
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
}
