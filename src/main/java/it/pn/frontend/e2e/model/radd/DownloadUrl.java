package it.pn.frontend.e2e.model.radd;

import it.pn.frontend.e2e.model.enums.DocumentType;
import lombok.Data;

@Data
public class DownloadUrl {
    private String url;
    private String needAuthentication;
    private DocumentType documentType;

    public DownloadUrl(String url, String needAuthentication, DocumentType documentType) {
        this.url = url;
        this.needAuthentication = needAuthentication;
        this.documentType = documentType;
    }
}
