package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationAttachmentBodyRef {
    private String key;
    private String versionToken;

    public NotificationAttachmentBodyRef() {
        this.key = "PN_NOTIFICATION_ATTACHMENTS-da1f715b18a04dd2a7db502dd068bde6.pdf";
        this.versionToken = "x.zyzguesUQfFBpFz3EFmY_mzq_IbVyX";
    }

    public NotificationAttachmentBodyRef(String key, String versionToken) {
        this.key = key;
        this.versionToken = versionToken;
    }
}