package TestComponents;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        boolean headless = System.getProperty("headless") != null ? Boolean.parseBoolean(System.getProperty("headless")) : Boolean.parseBoolean(prop.getProperty("headless"));
        int width = System.getProperty("width") != null ? Integer.parseInt(System.getProperty("width")) : Integer.parseInt(prop.getProperty("width"));
        int height = System.getProperty("height") != null ? Integer.parseInt(System.getProperty("height")) : Integer.parseInt(prop.getProperty("height"));

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("headless");
                }
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "remote":
//                to try later
//                try {
//                    DesiredCapabilities capabilities = new DesiredCapabilities();
//                    capabilities.setBrowserName("chrome");
//                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
//                } catch (MalformedURLException e) {
//                    throw new RuntimeException("URL de Selenium Grid incorrecta", e);
//                }
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }
        driver.manage().window().setSize(new Dimension(width, height));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}
