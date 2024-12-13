package it.pn.frontend.e2e.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class DevToolsManager {
    private static ThreadLocal<DevTools> devToolsThread = ThreadLocal.withInitial(() -> {
        ChromeDriver driver = (ChromeDriver) WebDriverManager.getDriverThreadLocal().get();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        return devTools;
    });

    public static DevTools getDevTools() {
        return devToolsThread.get();
    }
/**
    public static void setupNetworkMonitoring() {
        DevTools devTools = getDevTools();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.responseReceived(), response -> {
            String url = response.getResponse().getUrl();
            int status = response.getResponse().getStatus();
            System.out.println("Captured URL: " + url + " with status: " + status);

            // Memorizza l'informazione per il thread corrente
            NetworkInfoManager.addNetworkInfo(new NetworkInfo(url, status));
        });
    }
**/
    public static void clearDevTools() {
        devToolsThread.remove();
    }
}
