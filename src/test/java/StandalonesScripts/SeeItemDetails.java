package StandalonesScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.LandingPage;
import pageObjects.ProductsCatalog;
import pageObjects.ProductsDetail;

public class SeeItemDetails {
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

        String item_title = "ADIDAS ORIGINAL";

        ProductsDetail productsDetail = productsCatalog.viewDetailsOfProduct(item_title);

        Assert.assertEquals(productsDetail.getProductName(), item_title);
        System.out.println(productsDetail.getProductPrice());


    }
}
