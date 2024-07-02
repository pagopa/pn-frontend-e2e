package it.pn.frontend.e2e.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MetadataAttachment {

    private Digests digests;

    private String contentType;

    private Ref ref;

    public MetadataAttachment() {
        digests = new Digests("jezIVxlG1M1woCSUngM6KipUN3/p8cG5RMIPnuEanlE=");
        contentType = "application/json";
        ref = new Ref("_BLe0LcIbGRbqeXP_R6B6wwDY5dRniI0VdT8xOdz6AYEuZECcwYJY161auA.4WJsN_0Fv6ffp23fIfeo1vGz47","tO7q?0{&_'{C]%D<Keq>px|FpU5");
    }

    public MetadataAttachment(Digests digests, String contentType, Ref ref) {
        this.digests = digests;
        this.contentType = contentType;
        this.ref = ref;

    }
}
