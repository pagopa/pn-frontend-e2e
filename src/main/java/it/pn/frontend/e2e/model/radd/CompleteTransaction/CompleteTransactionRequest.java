package it.pn.frontend.e2e.model.radd.CompleteTransaction;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Data
public class CompleteTransactionRequest {
    private String operationId;
    private ZonedDateTime operationDate;

    public CompleteTransactionRequest() {
        this.operationId = "5d2d53ad-6306-40fe-8126-51ccdd917cef";
        this.operationDate = ZonedDateTime.now();
    }
}
