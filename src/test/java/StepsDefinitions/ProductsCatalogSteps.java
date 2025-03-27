package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.ProductsCatalog;
import pageObjects.ProductsDetail;

public class ProductsCatalogSteps extends BaseTest {
    public ProductsCatalog productsCatalog = (ProductsCatalog) ThreadLocalContext.get("productsCatalog");

    @When("^Select a product (.+) to see details$")
    public void select_a_product_to_see_details(String item_title) {
        ProductsDetail productsDetail = productsCatalog.viewDetailsOfProduct(item_title);
        ThreadLocalContext.set("productsDetail", productsDetail);
    }

    @Then("^Product (.+) name should coincide with the product name displayed$")
    public void product_name_should_coincide_with_the_product_name_displayed(String item_title) {
        ProductsDetail productsDetail =(ProductsDetail) ThreadLocalContext.get("productsDetail");
        Assert.assertEquals(productsDetail.getProductName(), item_title);
    }
}
