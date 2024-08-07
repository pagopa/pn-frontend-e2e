package it.pn.frontend.e2e.model.radd.ActInquiry;

import lombok.Data;

@Data
public class ActInquiryResponse {
    private boolean result;
    private ActInquiryResponseStatus actInquiryResponseStatus;

    public ActInquiryResponse(boolean result, ActInquiryResponseStatus actInquiryResponseStatus){
        this.result = result;
        this.actInquiryResponseStatus = actInquiryResponseStatus;
    }
}
