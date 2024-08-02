package it.pn.frontend.e2e.model.enums;

public enum DocumentType {
    ATTO_NOTIFICATO("ATTO_NOTIFICATO"),
    ATTESTAZIONE_OPPONIBILE_A_TERZI("ATTESTAZIONE_OPPONIBILE_A_TERZI");

    private final String value;

    DocumentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DocumentType fromValue(String value) {
        for (DocumentType code : DocumentType.values()) {
            if (code.value.equalsIgnoreCase(value)) {
                return code;
            }
        }
        throw new IllegalArgumentException("Valore non valido per DocumentType: " + value);
    }
}
