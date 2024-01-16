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

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String value) {
        this.zip = value;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String value) {
        this.municipality = value;
    }

    public String getMunicipalityDetails() {
        return municipalityDetails;
    }

    public void setMunicipalityDetails(String value) {
        this.municipalityDetails = value;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String value) {
        this.province = value;
    }

    public String getForeignState() {
        return foreignState;
    }

    public void setForeignState(String value) {
        this.foreignState = value;
    }
}
