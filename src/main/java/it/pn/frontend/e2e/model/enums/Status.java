package it.pn.frontend.e2e.model.enums;

public enum Status {
    OK("OK"),
    KO("KO");
    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
