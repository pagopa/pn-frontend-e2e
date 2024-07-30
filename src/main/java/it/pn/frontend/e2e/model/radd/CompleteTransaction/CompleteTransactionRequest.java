package it.pn.frontend.e2e.model.radd.CompleteTransaction;

import lombok.Data;

@Data
public class CompleteTransactionRequest {
    private String operationId;
    private String operationDate;

    public CompleteTransactionRequest() {
        this.operationId = "5d2d53ad-6306-40fe-8126-51ccdd917cef";
        this.operationDate = "2022-06-21T11:44:28Z";
    }
}
