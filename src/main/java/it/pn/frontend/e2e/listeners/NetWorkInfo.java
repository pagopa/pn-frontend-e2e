package it.pn.frontend.e2e.listeners;

import lombok.Data;

@Data
public class NetWorkInfo {

    private String requestId;
    private String requestUrl;
    private String requestMethod;
    private String responseStatus;
    private String responseBody;
    private String authorizationBearer;

}
