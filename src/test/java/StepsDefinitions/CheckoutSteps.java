package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;

import java.util.List;

public class CheckoutSteps extends BaseTest {
    public CheckoutPage checkoutPage = (CheckoutPage) ThreadLocalContext.get("checkoutPage");

    @Then("^I should see the item (.+) in the checkout page$")
    public void should_be_displayed_in_the_payment_page(String item) {
        assert checkoutPage.VerifyProductDisplay(item);
    }

    @Then("^I should see the items (.+) and (.+) in the checkout page$")
    public void should_be_displayed_in_the_payment_page(String item1, String item2) {
        List<String> items = List.of(item1, item2);
        checkoutPage.VerifyProductsInCheckout(items);
    }

    @When("^I select the country (.+)$")
    public void select_country(String country) {
        checkoutPage.selectCountry(country);
    }

    @When("I click the submit button")
    public void i_click_the_submit_button() {
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        ThreadLocalContext.set("confirmationPage", confirmationPage);
    }

}
