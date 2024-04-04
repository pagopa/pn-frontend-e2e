package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ref {
    private String key;
    private String versionToken;

    public Ref() {
        this.key = "PN_NOTIFICATION_ATTACHMENTS-2996b353d33a42e49e42d0745ca6e712.pdf";
        this.versionToken = "v1";
    }

    public Ref(String key, String versionToken) {
        this.key = key;
        this.versionToken = versionToken;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public String getVersionToken() {
        return versionToken;
    }

    public void setVersionToken(String value) {
        this.versionToken = value;
    }
}
