package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationMetadataAttachment {
    private Digests digests;
    private final String contentType = "application/json";
    private NotificationAttachmentBodyRef ref;

    public NotificationMetadataAttachment() {
        this.digests = new Digests("UtTwRseo3KHQIFkl7+VGNeZDWauWqYyDEjrxl9w/h2g=");
        this.ref = new NotificationAttachmentBodyRef("PN_F24_META-5cf8e14c93c34ed194ff3ace14620fc1.json", "v1");
    }

    public NotificationMetadataAttachment(String sha256, String key, String versionToken) {
        this.digests = new Digests(sha256);
        this.ref = new NotificationAttachmentBodyRef(key, versionToken);
    }
}
