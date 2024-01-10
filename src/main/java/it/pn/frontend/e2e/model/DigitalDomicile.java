package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.pn.frontend.e2e.model.enums.DigitalDomicileTypeEnum;

public class DigitalDomicile {
    private DigitalDomicileTypeEnum type;
    private String address;

    public DigitalDomicile() {
        this.type = DigitalDomicileTypeEnum.EMAIL;
        this.address = "test@test.com";
    }

    public DigitalDomicile(DigitalDomicileTypeEnum type, String address) {
        this.type = type;
        this.address = address;
    }

    @JsonProperty("type")
    public DigitalDomicileTypeEnum getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(DigitalDomicileTypeEnum value) {
        this.type = value;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String value) {
        this.address = value;
    }
}
