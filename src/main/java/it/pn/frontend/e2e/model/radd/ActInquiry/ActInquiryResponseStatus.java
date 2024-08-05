package it.pn.frontend.e2e.model.radd.ActInquiry;

import it.pn.frontend.e2e.model.enums.RaddAltResponseCode;
import lombok.Data;

@Data
public class ActInquiryResponseStatus {
    private RaddAltResponseCode code;
    private String message;

    public ActInquiryResponseStatus(RaddAltResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
