package it.pn.frontend.e2e.utility;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFile{

    private static final Logger logger = LoggerFactory.getLogger("DownloadFile");

    public void download(String urlLink, File fileLoc) {
        try {
            byte[] buffer = new byte[1024];
            double TotalDownload = 0.00;
            int readbyte = 0; //Stores the number of bytes written in each iteration.
            double percentOfDownload = 0.00;

            URL url = new URL(urlLink);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            double filesize = (double)http.getContentLengthLong();

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
}
