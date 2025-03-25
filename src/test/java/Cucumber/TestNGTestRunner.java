package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber",
        glue = "StepDefinitions",
        monochrome = true,
        plugin = {"html:reports/cucumber.html"},
        tags = "@ErrorValidation")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
