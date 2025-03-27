package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.LandingPage;
import pageObjects.ProductsCatalog;


public class LoginSteps extends BaseTest {
    public LandingPage landingPage;


    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        ProductsCatalog productsCatalog = landingPage.loginApplication(username, password);
        ThreadLocalContext.set("productsCatalog", productsCatalog);
    }


    @When("Logged in with correct credentials")
    public void logged_in() {
        String email = "kevger@gmail.com";
        String pass = "Iamking123";
        ProductsCatalog productsCatalog = landingPage.loginApplication(email, pass);
        ThreadLocalContext.set("productsCatalog", productsCatalog);
    }


    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) {
        Assert.assertEquals(strArg1, landingPage.getAlertMessage());
    }


}
