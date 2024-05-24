package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationAttachmentDigests {
    private String sha256;

    public NotificationAttachmentDigests() {
        this.sha256 = "z18S3aiGYNsz4FMCZSkuQhnF7yxcqkt1ehJlFc7c88I=";
    }

    public NotificationAttachmentDigests(String sha256) {
        this.sha256 = sha256;
    }
}
