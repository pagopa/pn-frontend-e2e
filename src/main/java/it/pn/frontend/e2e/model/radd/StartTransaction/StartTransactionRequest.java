package it.pn.frontend.e2e.model.radd.StartTransaction;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StartTransactionRequest {
    @NonNull
    private String uid;
}
