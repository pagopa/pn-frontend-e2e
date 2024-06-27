package it.pn.frontend.e2e.model.enums;

public enum RecipientTypeEnum {
    PF, PG;

    public static RecipientTypeEnum fromString(String value){
        if (value.equalsIgnoreCase("PF")){
            return RecipientTypeEnum.PF;
        } else {
            return RecipientTypeEnum.PG;
        }
    }
}
