package it.pn.frontend.e2e.model.radd.ActInquiry;

import it.pn.frontend.e2e.model.enums.RecipientTypeEnum;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ActInquiryRequest {
    @NonNull
    private String uid;
    @NonNull
    //Codice Fiscale Destinatario
    private String recipientTaxId;
    @NonNull
    private RecipientTypeEnum recipientType;

    private String iun;

    public ActInquiryRequest(@NonNull String uid, @NonNull String recipientTaxId, @NonNull RecipientTypeEnum recipientType, String iun) {
        this.uid = uid;
        this.recipientTaxId = recipientTaxId;
        this.recipientType = recipientType;
        this.iun = iun;
    }
}
