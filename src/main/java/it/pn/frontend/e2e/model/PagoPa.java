package it.pn.frontend.e2e.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagoPa {
    private String noticeCode;

    private String creditorTaxId;

    private Boolean applyCost;

    private Attachment attachment;


    public PagoPa(){
        this.noticeCode = "302000100000019421";
        this.creditorTaxId = "CSRGGL44L13H501E";
        this.applyCost = false;
        this.attachment = new Attachment();
    }

    public PagoPa(String noticeCode,String creditorTaxId, Boolean applyCost, Attachment attachment ){
        this.noticeCode = noticeCode;
        this.creditorTaxId = creditorTaxId;
        this.applyCost = applyCost;
        this.attachment = attachment;
    }


}
