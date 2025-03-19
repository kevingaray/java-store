package tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WrongCredentialsTest extends BaseTest {

    @Test(retryAnalyzer= Retry.class)
    public void testWrongCredentials() {
        String email = "kevger@gmail.com";
        String pass = "Iamking123_wrong";
        landingPage.loginApplication(email, pass);
        String textAlert = landingPage.getAlertMessage();
        Assert.assertEquals(textAlert, "Incorrect email or password.");
    }

}
