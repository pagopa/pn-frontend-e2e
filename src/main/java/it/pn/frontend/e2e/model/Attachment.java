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
        this.digests = new Digests("jezIVxlG1M1woCSUngM6KipUN3/p8cG5RMIPnuEanlE=");
        this.contentType = "application/pdf";
        this.ref = new Ref("safestorage://1bd8b2NprkYpytFzpkxMIOf4T","\\\"^Fx{'f(l_f&9!=O*y<D%sA>");
    }

    public Attachment(Digests digests, String contentType, Ref ref) {
        this.digests = digests;
        this.contentType = contentType;
        this.ref = ref;

    }
    }
