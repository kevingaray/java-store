package StandalonesScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.LandingPage;


public class LogInCredencialesIncorrectas {
    public static void main(String[] args) {
        //data
        String email = "kevger@gmail.com";
        String pass = "Iamking123_worng";

        // setup
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // test
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginApplication(email, pass);
        String textAlert = landingPage.getAlertMessage();

        // validation
        Assert.assertEquals(textAlert, "Incorrect email or password.");
    }
}
