package it.pn.frontend.e2e.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Attachment {
    private Digests digests;

    private String contentType;

    private Ref ref;

    public Attachment() {
        this.digests = new Digests("1QKD/Ks6BohyQ+bgMxHf9NrpNhVmGUPxRYE1aerU4JQ=");
        this.contentType = "application/pdf";
        this.ref = new Ref("PN_NOTIFICATION_ATTACHMENTS-4f661ba86794f918148ea97f1d7fa80.pdf","v1");
    }

    public Attachment(Digests digests, String contentType, Ref ref) {
        this.digests = digests;
        this.contentType = contentType;
        this.ref = ref;

    }
    }
