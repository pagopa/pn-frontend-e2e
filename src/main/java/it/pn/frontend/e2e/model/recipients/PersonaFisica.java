package it.pn.frontend.e2e.model.recipients;

import lombok.Data;

@Data
public class PersonaFisica {
    private String url;
    private String email;
    private String mail;
    private String emailPec;
    private String emailPecErrore;
    private String pec;
    private String pecErrore;
    private String telefono;
    private String name;
    private String familyName;
    private String codiceFiscale;
    private String fiscalNumber;
    private String  indirizzo;
    private String numeroCivico;
    private String localita;
    private String comune;
    private String provincia;
    private String codicePostale;
    private String stato;
    private String OTPPec;
    private String OTPMail;
    private String additionalEmail;

}
