package it.pn.frontend.e2e.model;

import it.pn.frontend.e2e.model.enums.DigitalDomicileTypeEnum;
import lombok.Data;

@Data
public class DigitalDomicile {
    private DigitalDomicileTypeEnum type;
    private String address;

    public DigitalDomicile() {
        this.type = DigitalDomicileTypeEnum.PEC;
        this.address = "test@test.com";
    }

    public DigitalDomicile(DigitalDomicileTypeEnum type, String address) {
        this.type = type;
        this.address = address;
    }

    public DigitalDomicile( String address) {
        this.type = DigitalDomicileTypeEnum.PEC;
        this.address = address;
    }

}
