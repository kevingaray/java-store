package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.ProductsCatalog;

import java.util.List;

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

    @Then("I should see an empty cart")
    public void i_should_see_an_empty_cart() {
        String cartPageAlert = cartPage.getAlertMessage();
        String cartPageTitle = cartPage.getTitleMessage();
        assert cartPageAlert.equals("No Product in Your Cart");
        assert cartPageTitle.equals("No Products in Your Cart !");
    }

    @Then("^only the item (.+) should be displayed in the cart$")
    public void should_be_displayed_in_the_cart(String item) {
        assert cartPage.VerifyProductDisplay(item);
    }

    @Then("^\\b([^\\\"]+)\\b and \\b([^\\\"]+)\\b should be displayed in the cart$")
    public void verifyMultipleProductsInCart(String product1, String product2) {
        List<String> items = List.of(product1, product2);
        assert cartPage.VerifyProductsDisplay(items);
    }
}
