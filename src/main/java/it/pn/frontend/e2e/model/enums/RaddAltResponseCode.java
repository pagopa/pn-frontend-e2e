package it.pn.frontend.e2e.model.enums;

public enum RaddAltResponseCode {

    ZERO(0),
    TWO(2),
    THREE(3),
    FOUR(4),
    TEN(10),
    EIGHTY(80),
    NINETY_NINE(99);

    private final int value;

    RaddAltResponseCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RaddAltResponseCode fromValue(int value) {
        for (RaddAltResponseCode code : RaddAltResponseCode.values()) {
            if (code.value == value) {
                return code;
            }
        }
        throw new IllegalArgumentException("Valore non valido per Code: " + value);
    }
}
