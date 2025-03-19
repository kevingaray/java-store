package tests;

import TestComponents.BaseTest;

import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;

public class EmptyCartTest extends BaseTest {

    @Test(retryAnalyzer= Retry.class)
    public void EmptyCart() {
        String email = "kevger@gmail.com";
        String pass = "Iamking123";

        landingPage.loginApplication(email, pass);
        // test
        CartPage cartPage = landingPage.goToCartPage();
        String cartPageAlert = cartPage.getAlertMessage();
        String cartPageTitle = cartPage.getTitleMessage();

        // validation
        Assert.assertEquals(cartPageAlert, "No Product in Your Cart");
        Assert.assertEquals(cartPageTitle, "No Products in Your Cart !");

    }
}
