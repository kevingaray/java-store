package StandalonesScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.ProductsCatalog;

import java.util.List;

public class AddAnotherItemToCart {
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
        Assert.assertEquals(productsCatalog.getCartNumber(), items.size());

        // verify in cart page
        CartPage cartPage = productsCatalog.goToCartPage();
        Assert.assertTrue(cartPage.VerifyProductsDisplay(items));


    }
}
