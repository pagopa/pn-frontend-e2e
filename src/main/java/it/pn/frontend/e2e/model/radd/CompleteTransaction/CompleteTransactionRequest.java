package it.pn.frontend.e2e.model.radd.CompleteTransaction;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CompleteTransactionRequest {
    @NonNull
    private String uid;
}
