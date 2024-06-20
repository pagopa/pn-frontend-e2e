package it.pn.frontend.e2e.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.Setter;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class DataPopulation {
    private static final Logger logger = LoggerFactory.getLogger("DataPopulation");

    private ObjectMapper objectMapper;

    @Setter
    private String dataDa;
    @Setter
    private String dataA;
    private ZoneId targetZoneId; // zona target, puoi cambiarla a tuo piacimento

    public DataPopulation() {
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

    public Map<String, Object> readDataPopulation(String dpFile) {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> readValue = new HashMap<>();
        try {
            return objectMapper.readValue(new File("src/test/resources/dataPopulation/" + dpFile), typeReference);
        } catch (IOException e) {
            logger.error(e.getMessage());
            Assert.fail(e.getMessage());
        }
        return readValue;
    }

    public void writeDataPopulation(String dpFile, Map<String, Object> dp) {
        this.objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        try {
            this.objectMapper.writeValue(new File("src/test/resources/dataPopulation/" + dpFile), dp);
        } catch (IOException e) {
            logger.error(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
