package StandalonesScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.ProductsCatalog;

public class AddItemtoCart {
    public static void main(String[] args) throws InterruptedException {

        //data
        String email = "kevger@gmail.com";
        String pass = "Iamking123";

        // setup
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // login
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);
        String textAlert = landingPage.getAlertMessage();
        Assert.assertEquals(textAlert, "Login Successfully");

        // test
        String item_title = "IPHONE 13 PRO";
        productsCatalog.addProductToCart(item_title);

        // validation
        Assert.assertEquals(productsCatalog.getCartNumber(), 1);

        // another validation
        CartPage cartPage = productsCatalog.goToCartPage();
        Assert.assertTrue(cartPage.VerifyProductDisplay(item_title));

    }
}
