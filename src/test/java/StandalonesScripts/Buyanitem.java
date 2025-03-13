package StandalonesScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;
import pageObjects.ProductsCatalog;

import java.util.List;

public class Buyanitem {
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
        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);
        String textAlert = landingPage.getAlertMessage();
        Assert.assertEquals(textAlert, "Login Successfully");

        // items
        List<String> items = List.of("ADIDAS ORIGINAL", "ZARA COAT 3");
        productsCatalog.addProductsToCart(items);
        CartPage cartPage = productsCatalog.goToCartPage();

        // buy item
        PaymentPage paymentPage = cartPage.buyItem(items.getFirst());
        Assert.assertTrue(paymentPage.VerifyProductDisplay(items.getFirst()));

    }
}
