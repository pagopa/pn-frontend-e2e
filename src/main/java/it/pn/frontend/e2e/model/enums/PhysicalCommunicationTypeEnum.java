package it.pn.frontend.e2e.model.enums;

public enum PhysicalCommunicationTypeEnum {
    AR_REGISTERED_LETTER,
    REGISTERED_LETTER_890;

    public static PhysicalCommunicationTypeEnum fromString(String value){
        if (value.equalsIgnoreCase("890")){
            return PhysicalCommunicationTypeEnum.REGISTERED_LETTER_890;
        } else {
            return PhysicalCommunicationTypeEnum.AR_REGISTERED_LETTER;
        }
    }
}
