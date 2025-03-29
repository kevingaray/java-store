package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Then;

import pageObjects.OrderPage;

import java.util.ArrayList;
import java.util.List;

public class OrderSteps extends BaseTest {
    public OrderPage orderPage = (OrderPage) ThreadLocalContext.get("orderPage");

    @Then("My orders should be displayed")
    public void my_orders_should_be_displayed() {
        List<?> ordersListRaw = (List<?>) ThreadLocalContext.get("ordersList");

        System.out.println(ordersListRaw);

        List<String> ordersList = new ArrayList<>();
        for (Object order : ordersListRaw) {
            if (order instanceof String) {
                ordersList.add((String) order);
            }
        }
        assert orderPage.VerifyOrders(ordersList);
    }
}
