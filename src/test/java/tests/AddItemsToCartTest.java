package tests;

import TestComponents.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductsCatalog;

import java.util.List;

public class AddItemsToCartTest extends BaseTest {
    @Test
    public void AddAnItem() throws InterruptedException {
        //data
        String email = "kevger@gmail.com";
        String pass = "Iamking123";
        String item_title = "IPHONE 13 PRO";

        landingPage.goTo();
        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);
        productsCatalog.addProductToCart(item_title);

        // validation
        Assert.assertEquals(productsCatalog.getCartNumber(), 1);

        // another validation
        CartPage cartPage = productsCatalog.goToCartPage();
        Assert.assertTrue(cartPage.VerifyProductDisplay(item_title));
    }

    @Test
    public void AddMultipleItems() {
        String email = "kevger@gmail.com";
        String pass = "Iamking123";
        List<String> items = List.of("ADIDAS ORIGINAL", "ZARA COAT 3");

        landingPage.goTo();
        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);

        productsCatalog.addProductsToCart(items);
        productsCatalog.waitUpdateCartNumber();
        Assert.assertEquals(productsCatalog.getCartNumber(), items.size());

        // verify in cart page
        CartPage cartPage = productsCatalog.goToCartPage();
        Assert.assertTrue(cartPage.VerifyProductsDisplay(items));

    }
}
