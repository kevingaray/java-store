package tests;

import TestComponents.BaseTest;

import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProductsCatalog;
import pageObjects.ProductsDetail;

public class SeeItemsDetailsTest extends BaseTest {
    @Test(retryAnalyzer= Retry.class)
    public void SeeItemsDetails() {
        String email = "kevger@gmail.com";
        String pass = "Iamking123";
        String item_title = "ADIDAS ORIGINAL";

        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);
        ProductsDetail productsDetail = productsCatalog.viewDetailsOfProduct(item_title);
        Assert.assertEquals(productsDetail.getProductName(), item_title);
    }

}
