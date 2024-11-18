package it.pn.frontend.e2e.model.recipients;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersoneFisiche {

    private List<PersonaFisica> persone;

    public PersoneFisiche() {
        this.persone = new ArrayList<>();
    }

    public void aggiungiPersona(PersonaFisica persona) {
        this.persone.add(persona);
    }


}
