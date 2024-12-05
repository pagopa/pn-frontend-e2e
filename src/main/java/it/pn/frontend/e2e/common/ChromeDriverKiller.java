package it.pn.frontend.e2e.common;

import java.io.IOException;

public class ChromeDriverKiller {

    public static void killChromeDriverProcesses() {
        String os = System.getProperty("os.name").toLowerCase();
        String command;

        if (os.contains("win")) {
            command = "taskkill /F /IM chromedriver.exe";
        } else {
            command = "pkill -f chromedriver";
        }

        try {
            Runtime.getRuntime().exec(command);
            System.out.println("Terminati tutti i processi ChromeDriver.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        killChromeDriverProcesses();
    }
}
