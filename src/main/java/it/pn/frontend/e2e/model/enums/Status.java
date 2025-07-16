package it.pn.frontend.e2e.model.enums;

public enum Status {
    OK("OK"),
    KO("KO"),
    INSERISCI_KO("KO-insert"),
    RISOLVI_KO("KO-resolve");
    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
