package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.ProductsCatalog;

public class CartSteps extends BaseTest {
    public CartPage cartPage = (CartPage) ThreadLocalContext.get("cartPage");

    @When("^I remove (.+) from cart$")
    public void remove_item_from_cart(String item) {
        cartPage.removeItem(item);
    }

    @Then("^(.+) should not be displayed in the cart$")
    public void should_not_be_displayed_in_the_cart(String item) {
        ProductsCatalog productsCatalog = (ProductsCatalog) ThreadLocalContext.get("productsCatalog");
        productsCatalog.waitUpdateCartNumber();
        assert !cartPage.VerifyProductDisplay(item);
    }
}
