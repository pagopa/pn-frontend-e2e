package it.pn.frontend.e2e.presentation.model.common.component;

import java.util.Map;

public interface Form extends Component {

    /**
     * Compila i campi della UI usando la reflection e mappando i campi di Form con i campi di "data" che hanno lo stesso nome.
     */
    void fill(Object data);

    /**
     * Compila i campi della UI usando le key di data per trovare i campi in Form.
     */
    void fill(Map<?,?> data);

    /**
     * Esegue l’azione principale del form (submit, conferma, continua...).
     */
    void submit();
}

