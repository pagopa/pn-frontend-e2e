package it.pn.frontend.e2e.model.radd.CompleteTransaction;

import lombok.Data;

@Data
public class CompleteTransactionResponse {
    private boolean result;
    private CompleteTransactioneResponseStatus completeTransactioneResponseStatus;

    public CompleteTransactionResponse(boolean result, CompleteTransactioneResponseStatus completeTransactioneResponseStatus){
        this.result = result;
        this.completeTransactioneResponseStatus = completeTransactioneResponseStatus;
    }
}
