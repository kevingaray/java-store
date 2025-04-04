package TestComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.LandingPage;

import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URL;


public class BaseTest {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public LandingPage landingPage;

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

    public void initializeDriver() {
        String browserName = System.getProperty("browser", ConfigReader.getProperty("browser"));
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", ConfigReader.getProperty("headless")));
        int width = Integer.parseInt(System.getProperty("width", ConfigReader.getProperty("width")));
        int height = Integer.parseInt(System.getProperty("height", ConfigReader.getProperty("height")));
        String execution = System.getProperty("execution", ConfigReader.getProperty("execution")); // local / remote / browserstack

        MutableCapabilities capabilities;

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=" + width + "," + height);
                capabilities = chromeOptions;
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                capabilities = firefoxOptions;
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless=new");
                capabilities = edgeOptions;
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        try {
            switch (execution.toLowerCase()) {
                case "local":
                    switch (browserName.toLowerCase()) {
                        case "chrome":
                            assert capabilities instanceof ChromeOptions;
                            driver.set(ThreadGuard.protect(new ChromeDriver((ChromeOptions) capabilities)));
                            break;
                        case "firefox":
                            assert capabilities instanceof FirefoxOptions;
                            driver.set(ThreadGuard.protect(new FirefoxDriver((FirefoxOptions) capabilities)));
                            break;
                        case "edge":
                            assert capabilities instanceof EdgeOptions;
                            driver.set(ThreadGuard.protect(new EdgeDriver((EdgeOptions) capabilities)));
                            break;
                    }
                    break;

                case "remote":
                    String gridUrl = System.getProperty("gridUrl", ConfigReader.getProperty("gridUrl"));
                    URL url = URI.create(gridUrl).toURL();
                    driver.set(ThreadGuard.protect(new RemoteWebDriver(url, capabilities)));
                    break;

                case "browserstack":
                    String username = System.getProperty("bsUser", ConfigReader.getProperty("bsUser"));
                    String accessKey = System.getProperty("bsKey", ConfigReader.getProperty("bsKey"));
                    String browserstackUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

                    MutableCapabilities browserstackCaps = new MutableCapabilities();
                    browserstackCaps.setCapability("browserName", browserName);
                    browserstackCaps.setCapability("browserVersion", System.getProperty("browserVersion", "latest"));

                    // Set platformName and other options
                    MutableCapabilities bstackOptions = new MutableCapabilities();
                    bstackOptions.setCapability("os", System.getProperty("os", "Windows"));
                    bstackOptions.setCapability("osVersion", System.getProperty("osVersion", "10"));
                    bstackOptions.setCapability("projectName", "MyProject");
                    bstackOptions.setCapability("buildName", "Build_1");
                    bstackOptions.setCapability("sessionName", "My Test");

                    browserstackCaps.setCapability("bstack:options", bstackOptions);
                    URL url_bs = URI.create(browserstackUrl).toURL();
                    driver.set(ThreadGuard.protect(new RemoteWebDriver(url_bs, browserstackCaps)));
                    break;

                default:
                    throw new IllegalArgumentException("Execution mode not supported: " + execution);
            }

            getDriver().manage().window().setSize(new Dimension(width, height));

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Remote WebDriver URL", e);
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() {
        initializeDriver();
        landingPage = new LandingPage(getDriver());
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}

