package it.pn.frontend.e2e.model;

import it.pn.frontend.e2e.utility.WebTool;
import lombok.Builder;
import lombok.Setter;

import java.time.ZoneId;

@Builder
@Setter
public class Disservice {

    private String dataDa;
    private String dataA;
    private ZoneId targetZoneId; // zona target, puoi cambiarla a tuo piacimento

    public Disservice() {
        targetZoneId = ZoneId.of("Europe/ Paris");
    }

    public String getDataA() {
        String dataFormatted = dataA.replace("ore", "").trim();
        return WebTool.convertToLocalTime(dataFormatted, targetZoneId);
    }

    public String getDataDa() {
        String dataFormatted = dataA.replace("ore", "").trim();
        return WebTool.convertToLocalTime(dataFormatted, targetZoneId);
    }
}
