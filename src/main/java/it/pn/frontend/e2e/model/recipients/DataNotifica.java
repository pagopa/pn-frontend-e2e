package it.pn.frontend.e2e.model.recipients;

import lombok.Data;

@Data
public class DataNotifica {
    private String numeroProtocollo;
    private String oggettoDellaNotifica;
    private String descrizione;
    private String gruppoTest;
    private String gruppoDev;
    private String codiceTassonometrico;
    private String nomeDocumentoNotifica;
    private String codiceIUN;

}
