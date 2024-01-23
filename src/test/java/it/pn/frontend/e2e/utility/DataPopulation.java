package it.pn.frontend.e2e.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataPopulation {
    private static final Logger logger = LoggerFactory.getLogger("DataPopulation");

    private ObjectMapper objectMapper;

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

    /**
     * Generate a random protocol number
     *
     * @return a random protocol number as a String
     */
    public static String generatePaProtocolNumber() {
        // Get the current date in the format "yyyyMMdd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(new Date());

        // Generate a random number between 0 and 100
        Random random = new Random();
        String randomNumber = String.valueOf(random.nextInt(100));

        // Concatenate the current date and the random number
        String protocolNumber = "TA-FFSMRC-" + currentDate + "-" + randomNumber;
        logger.info("Protocol number generated: " + protocolNumber);

        return protocolNumber;
    }

    /**
     * Wait for the specified number of seconds
     *
     * @param seconds the number of seconds to wait
     */
    public static void waitTime(int seconds) {
        try {
            long millisecondsToWait = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(millisecondsToWait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Errore durante l'attesa.", e);
        }
    }
}
