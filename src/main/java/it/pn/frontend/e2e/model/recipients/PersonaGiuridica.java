package it.pn.frontend.e2e.model.recipients;

import lombok.Data;

@Data
public class PersonaGiuridica extends PersonaFisica {


    private String ragioneSociale;
    private String codiceIUN;
    private String cellulare;
    private String bearerToken;


}
