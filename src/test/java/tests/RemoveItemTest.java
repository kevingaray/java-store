package tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductsCatalog;

import java.util.List;

public class RemoveItemTest extends BaseTest {
    @Test(retryAnalyzer= Retry.class)
    public void RemoveItemOnCart() {
        String email = "kevger@gmail.com";
        String pass = "Iamking123";
        List<String> items = List.of("ADIDAS ORIGINAL", "ZARA COAT 3");

        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);
        productsCatalog.addProductsToCart(items);
        Assert.assertEquals(productsCatalog.getCartNumber(), items.size());

        CartPage cartPage = productsCatalog.goToCartPage();
        List<String> before_delete_list = cartPage.getProductList();
        cartPage.removeItem(items.get(1));
        productsCatalog.waitUpdateCartNumber();
        List<String> after_delete_list = cartPage.getProductList();

        Assert.assertEquals(before_delete_list.size() - after_delete_list.size(), 1);
    }
}
