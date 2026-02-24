package it.pn.frontend.e2e.framework.core.adapter.model;

@FunctionalInterface
public interface AssertionAction<T> {
    /**
     * Esegue l'assertion sul risultato
     * @param result il risultato dell'operazione
     * @throws AssertionError se l'assertion fallisce
     */
    void assertOn(T result);
}
