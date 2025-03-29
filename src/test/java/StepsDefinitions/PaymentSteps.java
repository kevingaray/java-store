package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Then;
import pageObjects.PaymentPage;

public class PaymentSteps extends BaseTest {
    public PaymentPage paymentPage = (PaymentPage) ThreadLocalContext.get("paymentPage");

    @Then("^I should see the item (.+) in the payment page$")
    public void should_be_displayed_in_the_payment_page(String item) {
        assert paymentPage.VerifyProductDisplay(item);
    }


}
