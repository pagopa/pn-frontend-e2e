package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationPaymentAttachment {
    private NotificationAttachmentDigests digests;
    private String contentType;
    private NotificationAttachmentBodyRef ref;

    public NotificationPaymentAttachment() {
        this.digests = new NotificationAttachmentDigests("1QKD/Ks6BohyQ+bgMxHf9NrpNhVmGUPxRYE1aerU4JQ=");
        this.contentType = "application/pdf";
        this.ref = new NotificationAttachmentBodyRef("PN_NOTIFICATION_ATTACHMENTS-1eaca1b63f8a4c519d1705c29e83b59c.pdf", "v1");
    }

    public NotificationPaymentAttachment(String sha256, String key, String versionToken) {
        this.digests = new NotificationAttachmentDigests(sha256);
        this.contentType = "application/pdf";
        this.ref = new NotificationAttachmentBodyRef(key, versionToken);
    }
}
