package TestComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.LandingPage;

import java.io.File;
import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public LandingPage landingPage;


    public void initializeDriver(){

        String browserName = System.getProperty("browser", ConfigReader.getProperty("browser"));
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", ConfigReader.getProperty("headless")));
        int width = Integer.parseInt(System.getProperty("width", ConfigReader.getProperty("width")));
        int height = Integer.parseInt(System.getProperty("height", ConfigReader.getProperty("height")));

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                driver.set(ThreadGuard.protect(new ChromeDriver(options)));
                break;
            case "firefox":
                driver.set(ThreadGuard.protect(new FirefoxDriver()));
                break;
            case "edge":
                driver.set(ThreadGuard.protect(new EdgeDriver()));
                break;
            case "remote":
                // Preparado para Selenium Grid en el futuro
                // driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
                throw new UnsupportedOperationException("Remote execution is not yet implemented.");
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    public WebDriver getDriver() {
        return driver.get();
    }


    @BeforeMethod(alwaysRun = true)
    public void launchApplication() {
        initializeDriver();
        landingPage = new LandingPage(getDriver());
        landingPage.goTo();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
    }


    public static String getScreenshot(String testCaseName, WebDriver driver) {
        if (driver == null) {
            System.err.println("No WebDriver instance available for screenshot.");
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + File.separator + "reports";
        String screenshotPath = screenshotDir + File.separator + testCaseName + "_" + timestamp + ".jpg";

        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("Error trying to save screenshot " + e.getMessage());
            return null;
        }
    }
}
