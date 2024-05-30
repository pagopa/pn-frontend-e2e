package it.pn.frontend.e2e.model;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Disservice {
    private String dataDa;
    private String dataA;

    public String getDataA() {
        return dataA.replace("ore", "").trim();
    }

    public String getDataDa() {
        return dataDa.replace("ore", "").trim();
    }
}
