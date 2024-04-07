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

   private  static String randomNoticeCode(){
    Random random = new Random();
    StringBuilder sb =  new StringBuilder(18);
        for(int i = 0; i< 18; i++){
            sb.append(random.nextInt(10));
        }
    return sb.toString();
   }


    public PagoPa(){
        this.noticeCode = randomNoticeCode();
        this.creditorTaxId = "77777777777";
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
