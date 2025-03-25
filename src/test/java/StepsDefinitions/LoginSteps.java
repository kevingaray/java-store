package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.LandingPage;


public class LoginSteps extends BaseTest {
    public LandingPage landingPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        landingPage.loginApplication(username, password);
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) {
        Assert.assertEquals(strArg1, landingPage.getAlertMessage());
    }

}
