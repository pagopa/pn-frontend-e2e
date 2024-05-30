package it.pn.frontend.e2e.model;

import it.pn.frontend.e2e.utility.WebTool;
import lombok.Setter;

import java.time.ZoneId;

@Setter
public class Disservice {

    private String dataDa;
    private String dataA;
    private ZoneId targetZoneId; // zona target, puoi cambiarla a tuo piacimento

    public Disservice() {
        this.targetZoneId = ZoneId.of("Europe/Paris");
    }

    public String getDataA() {
        String dataFormatted = dataA.replace("ore", "").trim();
        return WebTool.convertToLocalTime(dataFormatted, targetZoneId);
    }

    public String getDataDa() {
        String dataFormatted = dataDa.replace("ore", "").trim();
        return WebTool.convertToLocalTime(dataFormatted, targetZoneId);
    }
}
