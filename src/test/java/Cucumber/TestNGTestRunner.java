package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(features = "src/test/java/Cucumber",
        glue = "StepsDefinitions",
        monochrome = true,
        plugin = {"html:reports/cucumber.html"},
        tags = "@BuyMultipleItems")

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
