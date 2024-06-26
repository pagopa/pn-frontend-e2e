package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhysicalAddress {
    private String at;
    private String address;
    private String addressDetails;
    private String zip;
    private String municipality;
    private String municipalityDetails;
    private String province;
    private String foreignState;

    public PhysicalAddress() {
        this.at = "Presso";
        this.address = "VIA ROMA 20";
        this.addressDetails = "Scala b";
        this.zip = "20147";
        this.municipality = "MILANO";
        this.municipalityDetails = "MILANO";
        this.province = "MI";
        this.foreignState = "ITALIA";
    }

    public PhysicalAddress(String at, String address, String addressDetails, String zip, String municipality, String municipalityDetails, String province, String foreignState) {
        this.at = at;
        this.address = address;
        this.addressDetails = addressDetails;
        this.zip = zip;
        this.municipality = municipality;
        this.municipalityDetails = municipalityDetails;
        this.province = province;
        this.foreignState = foreignState;
    }
}
