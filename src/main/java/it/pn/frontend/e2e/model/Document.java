package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Document {
    private Digests digests;
    private String contentType;
    private Ref ref;
    private String title;


    public Document() {
        this.digests = new Digests("jezIVxlG1M1woCSUngM6KipUN3/p8cG5RMIPnuEanlE=");
        this.contentType = "application/pdf";
        this.ref = new Ref("PN_NOTIFICATION_ATTACHMENTS-4fd03e274fd2456abcb2cbb255e7fdbb.pdf", "v1");
        this.title = "RATA SCADUTA IMU";
    }

    public Document(Digests digests, String contentType, Ref ref, String title) {
        this.digests = digests;
        this.contentType = contentType;
        this.ref = ref;
        this.title = title;
    }

    public Document(Digests digests, String contentType, Ref ref) {
        this.digests = digests;
        this.contentType = contentType;
        this.ref = ref;
    }

    public Document(Digests digests, Ref ref) {
        this.digests = digests;
        this.ref = ref;
    }
}
