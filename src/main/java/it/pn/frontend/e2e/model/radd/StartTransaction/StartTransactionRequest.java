package it.pn.frontend.e2e.model.radd.StartTransaction;

import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import io.cucumber.messages.types.Hook;
import it.pn.frontend.e2e.listeners.Hooks;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@RequiredArgsConstructor
public class StartTransactionRequest {
    private String versionToken;
    private String operationId;
    private String recipientTaxId;
    private String recipientType;
    private String delegateTaxId;
    private String operationDate;
    private String iun;

    public StartTransactionRequest( String recipientTaxId,String recipientType , String iun) {
        this.versionToken =  "string";
        this.operationId = Hooks.getScenario();
        this.recipientTaxId = recipientTaxId;
        this.recipientType = recipientType;
        this.delegateTaxId =  null;
        this.operationDate = "2022-06-21T11:44:28Z";
        this.iun = iun;
    }
}
