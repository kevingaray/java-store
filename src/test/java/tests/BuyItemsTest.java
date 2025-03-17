package tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.List;

public class BuyItemsTest extends BaseTest {

    @Test
    public void BuyAnItem() {
        //data
        String email = "kevger@gmail.com";
        String pass = "Iamking123";
        List<String> items = List.of("ADIDAS ORIGINAL", "ZARA COAT 3");

        landingPage.goTo();
        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);

        // items
        productsCatalog.addProductsToCart(items);
        CartPage cartPage = productsCatalog.goToCartPage();

        // buy item
        PaymentPage paymentPage = cartPage.buyItem(items.getFirst());
        Assert.assertTrue(paymentPage.VerifyProductDisplay(items.getFirst()));
    }

    @Test
    public void BuyMultipleItems() {
        //data
        String email = "kevger@gmail.com";
        String pass = "Iamking123";
        List<String> items = List.of("ADIDAS ORIGINAL", "ZARA COAT 3");

        landingPage.goTo();
        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);
        productsCatalog.addProductsToCart(items);

        // verify in cart page
        CartPage cartPage = productsCatalog.goToCartPage();
        Assert.assertTrue(cartPage.VerifyProductsDisplay(items));

        // checkout
        CheckoutPage checkoutPage = cartPage.goToCheckOut();

        // verify checkout
        Assert.assertTrue(checkoutPage.VerifyProductsInCheckout(items));

        // page
        checkoutPage.selectCountry("Peru");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        List<String> ordersList = confirmationPage.getOrders();

        OrderPage orderPage = confirmationPage.gotoOrderPage();

        Assert.assertTrue(orderPage.VerifyOrders(ordersList));

    }
}
