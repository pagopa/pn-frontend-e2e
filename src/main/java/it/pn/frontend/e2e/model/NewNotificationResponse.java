package it.pn.frontend.e2e.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewNotificationResponse {
    private String notificationRequestId;
    private String paProtocolNumber;
    private String idempotenceToken;
    private String notificationIUN;


    public NewNotificationResponse() {
    }

    public NewNotificationResponse(String notificationRequestId, String paProtocolNumber, String idempotenceToken) {
        this.notificationRequestId = notificationRequestId;
        this.paProtocolNumber = paProtocolNumber;
        this.idempotenceToken = idempotenceToken;
    }
}
