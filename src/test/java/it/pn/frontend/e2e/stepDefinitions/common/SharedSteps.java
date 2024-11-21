package it.pn.frontend.e2e.stepDefinitions.common;

import it.pn.frontend.e2e.model.recipients.NuovaDelega;
import it.pn.frontend.e2e.model.recipients.NuovaDelegaPg;
import it.pn.frontend.e2e.model.recipients.PersonaFisica;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SharedSteps {

    String iun;
    String codiceVerificaDelega;
    PersonaFisica personaFisica;
    NuovaDelegaPg nuovaDelegaPg;
    NuovaDelega nuovaDelega;

}