package it.pn.frontend.e2e.utility;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static it.pn.frontend.e2e.listeners.Hooks.netWorkInfos;

public class DownloadFile extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger("DownloadFile");

    public DownloadFile(WebDriver driver) {
        super(driver);
    }

    public void download(String urlLink, File fileLoc, boolean healdess) {
        if (healdess){
            try {
                byte[] buffer = new byte[1024];
                double TotalDownload = 0.00;
                int readbyte = 0; //Stores the number of bytes written in each iteration.
                double percentOfDownload = 0.00;

                URL url = new URL(urlLink);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                double filesize = (double)http.getContentLengthLong();

                http.setRequestProperty("Authorization", getBearerSessionToken());

                BufferedInputStream input = new BufferedInputStream(http.getInputStream());
                FileOutputStream ouputfile = new FileOutputStream(fileLoc);
                BufferedOutputStream bufferOut = new BufferedOutputStream(ouputfile, 1024);
                while((readbyte = input.read(buffer, 0, 1024)) >= 0) {
                    //Writing the content onto the file.
                    bufferOut.write(buffer,0,readbyte);
                    //TotalDownload is the total bytes written onto the file.
                    TotalDownload += readbyte;
                    //Calculating the percentage of download.
                    percentOfDownload = (TotalDownload*100)/filesize;
                    //Formatting the percentage up to 2 decimal points.
                    String percent = String.format("%.2f", percentOfDownload);
                    System.out.println("Downloaded "+ percent + "%");
                }
                System.out.println("Your download is now complete.");
                bufferOut.close();
                input.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }else {
            try {
                String url = this.driver.getCurrentUrl();
                URL urlPDF = new URL(this.driver.getCurrentUrl());
                File pdf = new File(fileLoc.getAbsolutePath());
                FileUtils.copyURLToFile(urlPDF,pdf,1000,1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void downloadAttestazioneDisservizi(String urlLink, File fileLoc, boolean headless) throws IOException {
        if (headless) {
            try {
                byte[] buffer = new byte[1024];
                double totalDownload = 0.00;
                int readBytes; // Stores the number of bytes read in each iteration.
                double percentOfDownload = 0.00;

                URL url = new URL(urlLink);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Authorization", getBearerSessionToken());
                double fileSize = (double) http.getContentLengthLong();

                BufferedInputStream input = new BufferedInputStream(http.getInputStream());
                FileOutputStream output = new FileOutputStream(fileLoc);
                BufferedOutputStream bufferOut = new BufferedOutputStream(output, 1024);

                while ((readBytes = input.read(buffer, 0, 1024)) >= 0) {
                    // Writing the content onto the file.
                    bufferOut.write(buffer, 0, readBytes);
                    // TotalDownload is the total bytes written onto the file.
                    totalDownload += readBytes;
                    // Calculating the percentage of download.
                    percentOfDownload = (totalDownload * 100) / fileSize;
                    // Formatting the percentage up to 2 decimal points.
                    String percent = String.format("%.2f", percentOfDownload);
                    System.out.println("Downloaded " + percent + "%");
                }

                System.out.println("Your download is now complete.");

                // Closing streams
                bufferOut.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                URL url = new URL(urlLink);
                FileUtils.copyURLToFile(url, fileLoc, 1000, 1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void controlloDownload(String path, int numberOfFile){
        File directory = new File(path);

        File[] fList = directory.listFiles(File::isFile);

        if (fList != null && fList.length != 0){
            for (File file : fList) {
                if(file.getName().endsWith(".pdf")){
                    String filename = file.getName();
                    boolean result = file.delete();
                    if (result) {
                        logger.info("File: "+filename+ " è stato scaricato e eliminato ");
                    }
                }
            }
        }else {
            logger.error("File non scaricato o non completo numberOfFile="+numberOfFile);
            Assert.fail("File non scaricato");
        }
    }

    public boolean controlloEsistenzaCartella(File cartella) {
        return cartella.exists();
    }

    public String getUrl(String urlChiamata) {
        String url = "";
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            if (netWorkInfo.getRequestUrl().contains(urlChiamata) && netWorkInfo.getRequestMethod().equals("GET")) {
                if (!netWorkInfo.getResponseStatus().equals("200")){
                    logger.error("La chiamata "+netWorkInfo.getRequestUrl()+"ha risposto con questo codice: "+ netWorkInfo.getResponseStatus());
                }
                String values = netWorkInfo.getResponseBody();
                List<String> results = Splitter.on(CharMatcher.anyOf(",;:")).splitToList(values);

                for (String result : results) {
                    if (result.startsWith("//")) {
                        url = result;
                        break;
                    }
                }
                if (url.endsWith("}")) {
                    url = "https:" + url.substring(0, url.length() - 2);
                } else {
                    url = "https:" + url.substring(0, url.length() - 1);
                }
                logger.info("url: "+ url);
            }
        }
        if (url.isEmpty()){
            logger.error("Non è stata trovata la chiamata "+ urlChiamata);
        }
        return url;
    }

    public String getLegalFactId() {
        try {
            URL url = new URL("https://webapi.test.notifichedigitali.it/downtime/v1/history?fromTime=1900-01-01T00%3A00%3A00Z&toTime=2024-03-28T10%3A38%3A50.251Z&functionality=NOTIFICATION_CREATE&functionality=NOTIFICATION_VISUALIZATION&functionality=NOTIFICATION_WORKFLOW&page=0&size=10");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", getBearerSessionToken() );

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray resultArray = jsonResponse.getJSONArray("result");
            if (!resultArray.isEmpty()) {
                JSONObject firstResult = resultArray.getJSONObject(0);
                return firstResult.getString("legalFactId");
            }
        } catch (Exception e) {
           throw new RuntimeException("Failed to fetch legalFactId",e);
        }
        return null;
    }

    private String getBearerSessionToken() {
        List<NetWorkInfo> netWorkInfos = Hooks.netWorkInfos;
        String bearerToken = "";
        for (NetWorkInfo netWorkInfo : netWorkInfos) {
            String variabileAmbiente = System.getProperty("environment");
            String urlChiamata = "https://webapi." + variabileAmbiente + ".notifichedigitali.it/delivery/notifications/received?";
            if (netWorkInfo.getRequestUrl().contains(urlChiamata)) {
                bearerToken = netWorkInfo.getAuthorizationBearer();
            }
        }
        return bearerToken;
    }
}
