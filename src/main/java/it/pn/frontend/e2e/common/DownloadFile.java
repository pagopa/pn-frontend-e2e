package it.pn.frontend.e2e.common;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFile extends BasePage{

    private static final Logger logger = LoggerFactory.getLogger("DownloadFile");
    public DownloadFile(WebDriver driver) {
        super(driver);
    }

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
            //fileLoc.createNewFile();
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

}
