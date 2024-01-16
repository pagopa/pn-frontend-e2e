package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ref {
    private String key;
    private String versionToken;

    public Ref() {
        this.key = "PN_NOTIFICATION_ATTACHMENTS-da1f715b18a04dd2a7db502dd068bde6.pdf";
        this.versionToken = "x.zyzguesUQfFBpFz3EFmY_mzq_IbVyX";
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
