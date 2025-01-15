package it.pn.frontend.e2e.model.payments;

import it.pn.frontend.e2e.model.documents.Attachment;
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

    long epochMillis = System.currentTimeMillis();

   private  static String randomNoticeCode(){
    Random random = new Random();
    StringBuilder sb =  new StringBuilder("30210");
        for(int i = 0; i< 13; i++){
            sb.append(random.nextInt(10));
        }
    return sb.toString();
   }


    public PagoPa(){

        this.noticeCode = String.format("30202%13d", epochMillis );
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
