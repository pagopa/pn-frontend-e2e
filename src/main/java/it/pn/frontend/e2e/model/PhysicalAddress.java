package it.pn.frontend.e2e.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String value) {
        this.address = value;
    }

    @JsonProperty("zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("zip")
    public void setZip(String value) {
        this.zip = value;
    }

    @JsonProperty("municipality")
    public String getMunicipality() {
        return municipality;
    }

    @JsonProperty("municipality")
    public void setMunicipality(String value) {
        this.municipality = value;
    }

    @JsonProperty("municipalityDetails")
    public String getMunicipalityDetails() {
        return municipalityDetails;
    }

    @JsonProperty("municipalityDetails")
    public void setMunicipalityDetails(String value) {
        this.municipalityDetails = value;
    }

    @JsonProperty("province")
    public String getProvince() {
        return province;
    }

    @JsonProperty("province")
    public void setProvince(String value) {
        this.province = value;
    }

    @JsonProperty("foreignState")
    public String getForeignState() {
        return foreignState;
    }

    @JsonProperty("foreignState")
    public void setForeignState(String value) {
        this.foreignState = value;
    }
}
