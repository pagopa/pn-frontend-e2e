package it.pn.frontend.e2e.model.radd.StartTransaction;

import it.pn.frontend.e2e.model.enums.RaddAltResponseCode;
import lombok.Data;

@Data
public class StartTransactionResponseStatus {
    private RaddAltResponseCode code;
    private String message;
    //Tempo, espresso in ms comunicato al chiamante, trascorso il quale è possibile effettuare un nuovo tentativo di avvio della transazione.
    private int retryAfter;

    public StartTransactionResponseStatus(RaddAltResponseCode code, String message, int retryAfter) {
        this.code = code;
        this.message = message;
        this.retryAfter = retryAfter;
    }
}
