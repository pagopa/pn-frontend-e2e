package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class F24Payment {
    private String title;
    private boolean applyCost;
    private NotificationMetadataAttachment metadataAttachment;

    public F24Payment() {
        this.title = "F24_STANDARD_CLMCST42R12D969Z_0";
        this.applyCost = true;
        this.metadataAttachment = new NotificationMetadataAttachment();
    }

    public F24Payment(String sha256, String key, String versionToken) {
        this.title = "F24_STANDARD_CLMCST42R12D969Z_0";
        this.applyCost = true;
        this.metadataAttachment = new NotificationMetadataAttachment(sha256, key, versionToken);
    }

    public F24Payment(String title, boolean applyCost, String sha256, String key, String versionToken) {
        this.title = title;
        this.applyCost = applyCost;
        this.metadataAttachment = new NotificationMetadataAttachment(sha256, key, versionToken);
    }

    public F24Payment(boolean applyCost, String sha256, String key, String versionToken) {
        this.title = "F24_STANDARD_CLMCST42R12D969Z_0";
        this.applyCost = applyCost;
        this.metadataAttachment = new NotificationMetadataAttachment(sha256, key, versionToken);
    }
}