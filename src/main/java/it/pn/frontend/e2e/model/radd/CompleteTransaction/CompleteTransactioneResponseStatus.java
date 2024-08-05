package it.pn.frontend.e2e.model.radd.CompleteTransaction;

import it.pn.frontend.e2e.model.enums.RaddAltResponseCode;
import lombok.Data;

@Data
public class CompleteTransactioneResponseStatus {
    private RaddAltResponseCode code;
    private String message;

    public CompleteTransactioneResponseStatus(RaddAltResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
