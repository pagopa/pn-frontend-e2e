package it.pn.frontend.e2e.utility;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import static it.pn.frontend.e2e.listeners.Hooks.netWorkInfos;
/*
Principali Miglioramenti:
try-with-resources: Per la gestione automatica delle risorse come BufferedInputStream e BufferedOutputStream.
Uso di Optional: Per evitare possibili null pointer exception e migliorare la sicurezza del codice.
Rimozione di variabili ridondanti: Ho migliorato la leggibilità del codice eliminando dichiarazioni di variabili duplicate e accorpando operazioni dove possibile.
stream() e findFirst(): Ho usato le Stream API di Java 8+ per migliorare la ricerca di elementi, riducendo il numero di cicli e rendendo il codice più leggibile.
Gestione delle eccezioni: Ho migliorato il tracciamento delle eccezioni, fornendo messaggi più descrittivi e chiari quando si verifica un errore.
*
* */

public class DownloadFile extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DownloadFile");

    public DownloadFile(WebDriver driver) {
        super(driver);
    }

    public void download(String urlLink, File fileLoc, boolean headless) {
        if (headless) {
            downloadInHeadlessMode(urlLink, fileLoc);
        } else {
            downloadInNonHeadlessMode(fileLoc);
        }
    }

    private void downloadInHeadlessMode(String urlLink, File fileLoc) {
        try (var outputFile = new FileOutputStream(fileLoc);
             var input = new BufferedInputStream(new URL(urlLink).openStream());
             var bufferOut = new BufferedOutputStream(outputFile, 1024)) {

            var http = (HttpURLConnection) new URL(urlLink).openConnection();
            http.setRequestProperty("Authorization", getBearerSessionToken());
            byte[] buffer = new byte[1024];
            double totalDownload = 0.00;
            int bytesRead;
            double fileSize = (double) http.getContentLengthLong();

            while ((bytesRead = input.read(buffer, 0, 1024)) >= 0) {
                bufferOut.write(buffer, 0, bytesRead);
                totalDownload += bytesRead;
                double percentOfDownload = (totalDownload * 100) / fileSize;
                logger.info("Downloaded {}%", String.format("%.2f", percentOfDownload));
            }

            logger.info("Your download is now complete.");

        } catch (IOException e) {
            throw new RuntimeException("Error downloading file in headless mode", e);
        }
    }

    private void downloadInNonHeadlessMode(File fileLoc) {
        try {
            URL url = new URL(driver.getCurrentUrl());
            FileUtils.copyURLToFile(url, fileLoc, 1000, 1000);
        } catch (IOException e) {
            throw new RuntimeException("Error downloading file in non-headless mode", e);
        }
    }

    public void downloadAttestazioneDisservizi(String urlLink, File fileLoc, boolean headless) throws IOException {
        if (headless) {
            downloadInHeadlessMode(urlLink, fileLoc);
        } else {
            try {
                URL url = new URL(urlLink);
                FileUtils.copyURLToFile(url, fileLoc, 1000, 1000);
            } catch (IOException e) {
                throw new RuntimeException("Error downloading attestazione disservizi", e);
            }
        }
    }

    public void controlloDownload(String path, int numberOfFile) {
        File directory = new File(path);
        File[] fList = Optional.ofNullable(directory.listFiles(File::isFile))
                .orElse(new File[]{});

        if (fList.length != 0) {
            for (File file : fList) {
                if (file.getName().endsWith(".pdf") && file.delete()) {
                    logger.info("File: {} è stato scaricato e eliminato", file.getName());
                }
            }
        } else {
            logger.error("File non scaricato o non completo numberOfFile={}", numberOfFile);
            Assertions.fail("File non scaricato");
        }
    }

    public boolean controlloEsistenzaCartella(File cartella) {
        return cartella.exists();
    }

    public String getUrl(String urlChiamata) {
        String url = netWorkInfos.stream()
                .filter(netWorkInfo -> netWorkInfo.getRequestUrl().contains(urlChiamata) &&
                        netWorkInfo.getRequestMethod().equals("GET") &&
                        netWorkInfo.getResponseStatus().equals("200"))
                .map(NetWorkInfo::getResponseBody)
                .flatMap(values -> Splitter.on(CharMatcher.anyOf(",;:")).splitToList(values).stream())
                .filter(result -> result.startsWith("//"))
                .findFirst()
                .orElse("");

        if (url.isEmpty()) {
            logger.error("Non è stata trovata la chiamata {}", urlChiamata);
        } else if (url.endsWith("}")) {
            url = "https:" + url.substring(0, url.length() - 2);
        } else {
            url = "https:" + url.substring(0, url.length() - 1);
        }

        logger.info("URL: {}", url);
        return url;
    }

    public String getLegalFactId() {
        try {
            URL url = new URL("https://webapi.test.notifichedigitali.it/downtime/v1/history?fromTime=1900-01-01T00%3A00%3A00Z&toTime=2024-03-28T10%3A38%3A50.251Z&functionality=NOTIFICATION_CREATE&functionality=NOTIFICATION_VISUALIZATION&functionality=NOTIFICATION_WORKFLOW&page=0&size=10");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", getBearerSessionToken(".notifichedigitali.it/bff/v1/downtime/history?"));

            try (var in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                JSONArray resultArray = new JSONObject(response.toString()).getJSONArray("result");
                if (!resultArray.isEmpty()) {
                    return resultArray.getJSONObject(0).getString("legalFactId");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch legalFactId", e);
        }
        return null;
    }

    private String getBearerSessionToken() {
        String environment = System.getProperty("environment");
        String urlChiamata = "https://webapi." + environment + ".notifichedigitali.it/delivery/notifications/received?";
        return netWorkInfos.stream()
                .filter(netWorkInfo -> netWorkInfo.getRequestUrl().contains(urlChiamata))
                .map(NetWorkInfo::getAuthorizationBearer)
                .findFirst()
                .orElse("");
    }

    private String getBearerSessionToken(String url) {
        String environment = System.getProperty("environment");
        String urlChiamata = "https://webapi." + environment + url;
        return netWorkInfos.stream()
                .filter(netWorkInfo -> netWorkInfo.getRequestUrl().contains(urlChiamata))
                .map(NetWorkInfo::getAuthorizationBearer)
                .findFirst()
                .orElse("");
    }
}
