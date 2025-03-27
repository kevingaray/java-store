package StepsDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.After;

public class CucumberHooks extends BaseTest {

    @After
    public void close_driver(){
        ThreadLocalContext.remove();
        tearDown();
    }
}
