package it.pn.frontend.e2e.model.recipients;

import lombok.Data;

import java.util.List;

@Data
public class DatiNotifica {
    private String numeroProtocollo;
    private String oggettoDellaNotifica;
    private String descrizione;
    private String gruppoTest;
    private String gruppoDev;
    private String codiceTassonometrico;
    private String nomeDocumentoNotifica;
    private String codiceIUN;

    private String oggettoDellaNotificaFr;
    private String descrizioneFr;
    private String oggettoDellaNotificaDe;
    private String descrizioneDe;
    private String oggettoDellaNotificaSl;
    private String descrizioneSl;

    private List<String> codiciAvvisi;


}
