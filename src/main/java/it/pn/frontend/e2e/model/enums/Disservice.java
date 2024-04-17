package it.pn.frontend.e2e.model.enums;

public enum Disservice {
    CREAZIONE_NOTIFICHE("Creazione Notifiche"),
    VISUALIZZAZIONE_NOTIFICHE("Visualizzazione Notifiche"),
    WORKFLOW_NOTIFICHE("Workflow Notifiche");
    private final String value;

    Disservice(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
