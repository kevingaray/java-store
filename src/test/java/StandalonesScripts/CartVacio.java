package StandalonesScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.LandingPage;


public class CartVacio {
    public static void main(String[] args) {
        //data
        String email = "kevger@gmail.com";
        String pass = "Iamking123";

        // setup
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // login
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginApplication(email, pass);
        String textAlert = landingPage.getAlertMessage();
        Assert.assertEquals(textAlert, "Login Successfully");

        // test
        CartPage cartPage = landingPage.goToCartPage();
        String cartPageAlert = cartPage.getAlertMessage();
        String cartPageTitle = cartPage.getTitleMessage();

        // validation
        Assert.assertEquals(cartPageAlert, "No Product in Your Cart");
        Assert.assertEquals(cartPageTitle, "No Products in Your Cart !");
    }
}
