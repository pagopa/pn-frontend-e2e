package it.pn.frontend.e2e.listeners;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
import java.util.Calendar;
import java.util.Date;

public class Hooks {
    private static final Logger logger =  LoggerFactory.getLogger("Hooks");

    public static WebDriver driver;
    private String url;

    private String headless;

    private String driverUpAuto;

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
            firefoxOptions.setHeadless(true);
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
/*        Map<String, Object> prefs = new HashMap<>();
        String percorsoProggetto = System.getProperty("user.dir");
        prefs.put("download.default_directory", percorsoProggetto+"/src/test/resources/download");
        prefs.put("plugins.always_open_pdf_externally", true);
        chromeOptions.setExperimentalOption("prefs", prefs);*/
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
        //driver.get(this.url);
        logger.info("chromedriver started");

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
            edgeOptions.setHeadless(true);
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
    public void startScenario(){

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
                Assert.fail(e.getCause().toString());
            }
        }

        logger.info("quit driver");
        driver.quit();

    }
}
