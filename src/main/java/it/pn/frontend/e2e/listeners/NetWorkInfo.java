package it.pn.frontend.e2e.listeners;

import lombok.Data;

import java.util.List;

@Data
public class NetWorkInfo {

    private String requestId;
    private String requestUrl;
    private String requestMethod;
    private String responseStatus;
    private String responseBody;
    private String authorizationBearer;

    public static boolean checkUrlIsCalled(List<NetWorkInfo> infos, String url, String httpVerbs) {
        for (NetWorkInfo netWorkInfo : infos) {
            if (netWorkInfo.getRequestUrl().contains(url) && netWorkInfo.getRequestMethod().equals(httpVerbs)) {
                return true;
            }
        }
        return false;
    }
}
