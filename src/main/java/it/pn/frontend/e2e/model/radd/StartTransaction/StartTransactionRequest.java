package it.pn.frontend.e2e.model.radd.StartTransaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StartTransactionRequest {
    private String versionToken;
    private String operationId;
    private String recipientTaxId;
    private String recipientType;
    private String delegateTaxId;
    private String operationDate;
    private String iun;

    public StartTransactionRequest( String recipientTaxId,String recipientType , String iun,String operationId) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendInstant(3) // Formatta l'istante con precisione di tre cifre per i millisecondi
                .toFormatter();
        this.versionToken =  "V1";
        this.operationId =operationId;
        this.recipientTaxId = recipientTaxId;
        this.recipientType = recipientType;
        this.delegateTaxId=null;
        this.operationDate = formatter.format(Instant.now());
        this.iun = iun;
    }
}
