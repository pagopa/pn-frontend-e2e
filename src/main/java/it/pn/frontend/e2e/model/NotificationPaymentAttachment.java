package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationPaymentAttachment {
    private Digests digests;
    private String contentType;
    private Ref ref;

    public NotificationPaymentAttachment() {
        this.digests = new Digests("1QKD/Ks6BohyQ+bgMxHf9NrpNhVmGUPxRYE1aerU4JQ=");
        this.contentType = "application/pdf";
        this.ref = new Ref("PN_NOTIFICATION_ATTACHMENTS-735290b9a029454e8b58651ecba8e6ff.pdf", "v1");
    }

    public NotificationPaymentAttachment(String sha256, String key, String versionToken) {
        this.digests = new Digests(sha256);
        this.contentType = "application/pdf";
        this.ref = new Ref(key, versionToken);
    }
}
