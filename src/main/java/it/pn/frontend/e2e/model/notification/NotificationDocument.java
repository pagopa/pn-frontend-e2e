package it.pn.frontend.e2e.model.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.pn.frontend.e2e.model.documents.Digests;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class NotificationDocument {
    public static final String JSON_PROPERTY_DIGESTS = "digests";
    private Digests digests;

    public static final String JSON_PROPERTY_CONTENT_TYPE = "contentType";
    private String contentType;

    public static final String JSON_PROPERTY_REF = "ref";
    private NotificationAttachmentBodyRef ref;

    public static final String JSON_PROPERTY_TITLE = "title";
    private String title;

    public static final String JSON_PROPERTY_DOC_IDX = "docIdx";
    private String docIdx;
}
