package it.pn.frontend.e2e.model;

import lombok.Data;

@Data
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
}
