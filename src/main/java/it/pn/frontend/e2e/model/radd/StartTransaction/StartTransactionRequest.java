package it.pn.frontend.e2e.model.radd.StartTransaction;

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
    private String fileKey;
    private String checksum;
    private ZonedDateTime operationDate;
    private String iun;

    public StartTransactionRequest( String recipientTaxId , String iun) {
        this.versionToken =  "OXSnS_28NF1sKiaZsniWHbqwX2LktjCd";
        this.operationId = "5d2d53ad-6306-40fe-8126-51ccdd917cef";
        this.recipientTaxId = recipientTaxId;
        this.recipientType = recipientType;
        this.delegateTaxId =  "RFRGRZ66E21H751B";
        this.fileKey =  "PN_RADD_ALT_ATTACHMENT-17a4d40e08d8498a9d78f495c6bfd56a.zip";
        this.checksum = "Kg2Uwyt7rYE4II1giJwh2MtaNtq6Obup2eDE0kAps+c=";
        this.operationDate = ZonedDateTime.now();
        this.iun = iun;
    }
}
