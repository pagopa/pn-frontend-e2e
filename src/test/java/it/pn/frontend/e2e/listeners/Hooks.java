package it.pn.frontend.e2e.listeners;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v117.network.model.RequestWillBeSent;
import org.openqa.selenium.devtools.v117.network.Network;
import org.openqa.selenium.devtools.v117.network.model.ResourceType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Hooks {
    private static final Logger logger =  LoggerFactory.getLogger("Hooks");

    public static WebDriver driver;

    public  DevTools devTools;

    public Map<String, RequestWillBeSent> requests = new HashMap<>();

    public static List<NetWorkInfo> netWorkInfos = new ArrayList<>();

    private String headless;

    private final String os = System.getProperty("os.name");

    protected void firefox(){

        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        firefoxOptions.addArguments("-private");

        if(this.headless!=null && this.headless.equalsIgnoreCase("true")){
            firefoxOptions.addArguments("--width=1200");
            firefoxOptions.addArguments("--height=800");
            firefoxOptions.addArguments("--headless");
        }
        driver = new FirefoxDriver(firefoxOptions);
        if(this.headless!=null && this.headless.equalsIgnoreCase("false")){
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.get(this.url);
        logger.info("firefox driver started");

    }

    protected void chrome(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=it");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--remote-allow-origins=*");
        if(this.headless!=null && this.headless.equalsIgnoreCase("true")){
            chromeOptions.addArguments("no-sandbox");
            chromeOptions.addArguments("headless");
            chromeOptions.addArguments("window-size=1920,1080");
        }

        driver = new ChromeDriver(chromeOptions);

        if(this.headless!=null && this.headless.equalsIgnoreCase("false")){
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        devTools = ((HasDevTools)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));

        this.captureHttpRequests();
        this.captureHttpResponse();

        //driver.get(this.url);
        logger.info("chromedriver started");

    }

    private void captureHttpRequests(){
        devTools.addListener(
                Network.requestWillBeSent(),
                request ->{
                    requests.put(request.getRequestId().toString(),request);
                    //logger.info("Request URL : "+request.getRequest().getUrl());
                }
        );
    }

    public void captureHttpResponse(){
        devTools.addListener(
                Network.responseReceived(),
                response -> {
                    String requestId = response.getRequestId().toString();
                    if(requests.containsKey(requestId)){
                        RequestWillBeSent request = requests.get(requestId);
                        if(response.getType().equals(ResourceType.XHR)){
                            NetWorkInfo netWorkInfo = new NetWorkInfo();
                            netWorkInfo.setRequestId(requestId);
                            netWorkInfo.setRequestUrl(request.getRequest().getUrl());
                            netWorkInfo.setRequestMethod(request.getRequest().getMethod());
                            netWorkInfo.setResponseStatus(response.getResponse().getStatus().toString());
                            String bodyResponse = devTools.send(Network.getResponseBody(response.getRequestId())).getBody();
                            netWorkInfo.setResponseBody(bodyResponse);
                            this.netWorkInfos.add(netWorkInfo);
                        }
                    }
                    requests.remove(requestId);
                }
        );
    }


    protected void edge(){

        if(this.os.toLowerCase().contains("windows")){
            WebDriverManager.edgedriver().setup();
        }else{
            Assert.fail("browser edge non capatibile con il os : "+this.os);
        }

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("ms:inPrivate", true);
        if(this.headless!=null && this.headless.equalsIgnoreCase("true")){
            edgeOptions.addArguments("window-size=1920,1080");
            edgeOptions.addArguments("--headless");
        }
        driver = new EdgeDriver(edgeOptions);
        if(this.headless!=null && this.headless.equalsIgnoreCase("false")){
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.get(this.url);
        logger.info("edge driver started");
    }

    @Before
    public void startScenario(Scenario scenario){

        logger.info("-------------------------------------------START SCENARIO: "+scenario.getName()+"------------------------------------------------");

        logger.info("os type : "+this.os);
        logger.info("user language : "+System.getProperty("user.language"));


        String browser = null;
        if(System.getProperty("browser") == null){
            Assert.fail(" valorizzare la variabile browser");
        }else{
            browser = System.getProperty("browser");
        }

        if(System.getProperty("headless") != null){
            this.headless = System.getProperty("headless");
        }

        if (System.getProperty("environment") == null){
            Assert.fail(" valorizzare la variabile environment");
        }

        switch (browser) {
            case "firefox" -> firefox();
            case "chrome" -> chrome();
            case "edge" -> edge();
            default -> {
                logger.error("browser not correct");
                Assert.fail("browser not correct");
            }
        }

    }

    @After
    public void endScenario(Scenario scenario) {

        for(NetWorkInfo netWorkInfo : netWorkInfos){
            logger.info(netWorkInfo.getRequestId());
            logger.info(netWorkInfo.getRequestUrl());
            logger.info(netWorkInfo.getRequestMethod());
            logger.info(netWorkInfo.getResponseStatus());
            logger.info(netWorkInfo.getResponseBody());
        }

        if(scenario.isFailed()){
            logger.error("scenario go to error : "+scenario.getName());
            try {

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                byte[] screenshotByte = FileUtils.readFileToByteArray(screenshot);

                Date date = Calendar.getInstance().getTime();
                DateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
                String today = formatter.format(date);

                String testCaseFailed = "screenShots/"+scenario.getName()+"_"+today+".png";

                FileUtils.copyFile(screenshot, new File(testCaseFailed));

                scenario.attach(screenshotByte, "image/png", scenario.getName());

            } catch (IOException e) {
                logger.error(e.getCause().toString());
            }

            /*
            time out che serve per pulire la sessione


             */

        }

        logger.info("quit driver");
        driver.quit();
        requests.clear();
        netWorkInfos.clear();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("-------------------------------------------END SCENARIO: "+scenario.getName()+"------------------------------------------------");
    }
}
