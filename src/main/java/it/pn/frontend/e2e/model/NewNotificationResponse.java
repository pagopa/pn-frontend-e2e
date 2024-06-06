package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
public class NewNotificationResponse {
    private String notificationRequestId;
    private String paProtocolNumber;
    private String idempotenceToken;


    public NewNotificationResponse() {
    }

    public NewNotificationResponse(String notificationRequestId, String paProtocolNumber, String idempotenceToken) {
        this.notificationRequestId = notificationRequestId;
        this.paProtocolNumber = paProtocolNumber;
        this.idempotenceToken = idempotenceToken;
    }
}
