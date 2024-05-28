package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhysicalAddress {
    private String address;
    private String zip;
    private String municipality;
    private String municipalityDetails;
    private String province;
    private String foreignState;

    public PhysicalAddress() {
        this.address = "VIA ROMA 20";
        this.zip = "20147";
        this.municipality = "MILANO";
        this.municipalityDetails = "MILANO";
        this.province = "MI";
        this.foreignState = "ITALIA";
    }

    public PhysicalAddress(String address, String zip, String municipality, String municipalityDetails, String province, String foreignState) {
        this.address = address;
        this.zip = zip;
        this.municipality = municipality;
        this.municipalityDetails = municipalityDetails;
        this.province = province;
        this.foreignState = foreignState;
    }
}
