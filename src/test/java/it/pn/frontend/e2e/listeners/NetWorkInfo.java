package it.pn.frontend.e2e.listeners;

public class NetWorkInfo {

    private String requestId;
    private String requestUrl;
    private String requestMethod;
    private String responseStatus;
    private String responseBody;

    private String authorizationBearer;

    public String getAuthorizationBearer() {
        return authorizationBearer;
    }

    public void setAuthorizationBearer(String authorizationBearer) {
        this.authorizationBearer = authorizationBearer;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }


}
