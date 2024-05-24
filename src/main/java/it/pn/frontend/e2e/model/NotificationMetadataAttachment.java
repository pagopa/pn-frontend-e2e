package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NotificationMetadataAttachment extends NotificationPaymentAttachment{

    public NotificationMetadataAttachment(){
        super();
    }

    public NotificationMetadataAttachment(String sha256, String key, String versionToken){
        super(sha256, key, versionToken);
    }
}
