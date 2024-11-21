package it.pn.frontend.e2e.model.recipients;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersoneGiuridiche {

    private List<PersonaGiuridica> persone;

    public PersoneGiuridiche() {
        this.persone = new ArrayList<>();
    }

    public void aggiungiPersona(PersonaGiuridica personaPG) {
        this.persone.add(personaPG);
    }
}
