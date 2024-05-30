package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationPaymentAttachment {
    private Digests digests;
    private final String contentType = "application/pdf";
    private NotificationAttachmentBodyRef ref;

    public NotificationPaymentAttachment() {
        this.digests = new Digests("1QKD/Ks6BohyQ+bgMxHf9NrpNhVmGUPxRYE1aerU4JQ=");
        this.ref = new NotificationAttachmentBodyRef("PN_NOTIFICATION_ATTACHMENTS-1eaca1b63f8a4c519d1705c29e83b59c.pdf", "v1");
    }

    public NotificationPaymentAttachment(String sha256, String key, String versionToken) {
        this.digests = new Digests(sha256);
        this.ref = new NotificationAttachmentBodyRef(key, versionToken);
    }
}
