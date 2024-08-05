package it.pn.frontend.e2e.model.radd.CompleteTransaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CompleteTransactionRequest {
    private String operationId;
    private String operationDate;

    public CompleteTransactionRequest(String operationId) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendInstant(3) // Formatta l'istante con precisione di tre cifre per i millisecondi
                .toFormatter();
        this.operationId = operationId;
        this.operationDate = formatter.format(Instant.now());
    }
}
