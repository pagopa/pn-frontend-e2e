package it.pn.frontend.e2e.model;

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

    public String getNotificationRequestId() {
        return notificationRequestId;
    }

    public String getPaProtocolNumber() {
        return paProtocolNumber;
    }

    public String getIdempotenceToken() {
        return idempotenceToken;
    }

    public void setNotificationRequestId(String notificationRequestId) {
        this.notificationRequestId = notificationRequestId;
    }

    public void setPaProtocolNumber(String paProtocolNumber) {
        this.paProtocolNumber = paProtocolNumber;
    }

    public void setIdempotenceToken(String idempotenceToken) {
        this.idempotenceToken = idempotenceToken;
    }

}
