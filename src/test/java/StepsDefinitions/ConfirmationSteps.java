package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.When;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;

import java.util.List;

public class ConfirmationSteps extends BaseTest {
    public ConfirmationPage confirmationPage = (ConfirmationPage) ThreadLocalContext.get("confirmationPage");

    @When("I save the orders numbers")
    public void i_save_the_orders_numbers() {
        List<String> ordersList = confirmationPage.getOrders();
        ThreadLocalContext.set("ordersList", ordersList);
    }

    @When("I go to orders page")
    public void i_go_to_orders_page() {
        OrderPage orderPage = confirmationPage.gotoOrderPage();
        ThreadLocalContext.set("orderPage", orderPage);
    }


}
