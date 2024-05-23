package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class F24 {
    private String title;
    private boolean applyCost;
    private NotificationPaymentAttachment notificationPaymentAttachment;

    public F24() {
        this.title = "F24_STANDARD_CLMCST42R12D969Z_0";
        this.applyCost = true;
        this.notificationPaymentAttachment = new NotificationPaymentAttachment();
    }

    public F24(String sha256, String key, String versionToken) {
        this.title = "F24_STANDARD_CLMCST42R12D969Z_0";
        this.applyCost = true;
        this.notificationPaymentAttachment = new NotificationPaymentAttachment(sha256, key, versionToken);
    }

    public F24(String title, boolean applyCost, String sha256, String key, String versionToken) {
        this.title = title;
        this.applyCost = applyCost;
        this.notificationPaymentAttachment = new NotificationPaymentAttachment(sha256, key, versionToken);
    }
}