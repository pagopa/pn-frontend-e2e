package it.pn.frontend.e2e.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
public class PagoPa {
    private String noticeCode;

    private String creditorTaxId;

    private Boolean applyCost;

    private Attachment attachment;

    Random random = new Random();
    long min = 10000000000L; // Minimum 11-digit number
    long max = 99999999999L; // Maximum 11-digit number
    long range = max - min + 1;
    long creditIdLong = (long)(random.nextDouble() * range) + min;
    String creditorId = String.valueOf(creditIdLong);


    public PagoPa(){
        this.noticeCode = "302000100000019421";
        this.creditorTaxId = creditorId;
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
