package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {
        // create only 1 test on ExtentReport
        if (extentTest.get() == null) {
            ExtentTest test = extent.createTest(result.getMethod().getMethodName());
            extentTest.set(test);
        }

        // Log if retry exists
        if (result.getMethod().getRetryAnalyzer(result) != null) {
            int retryCount = result.getMethod().getCurrentInvocationCount();
            extentTest.get().log(Status.INFO, "Starting Test - Attempt " + (retryCount + 1));
        } else {
            extentTest.get().log(Status.INFO, "Starting Test - No Retries");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        int retryCount = result.getMethod().getCurrentInvocationCount();
        int maxRetry = Integer.parseInt(System.getProperty("maxTry", ConfigReader.getProperty("maxTry")));

        if (retryCount < maxRetry) {
            extentTest.get().log(Status.WARNING, "Test Failed - Retry " + retryCount + " of " + maxRetry);
            return;
        }

        extentTest.get().fail(result.getThrowable());
        WebDriver driver = getDriver();
        String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        if (filePath != null) {
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } else {
            extentTest.get().log(Status.WARNING, "No screenshot available due to an error.");
        }
    }


    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }


    @Override
    public void onFinish(ITestContext context) {
        try {
            extent.flush();
        } catch (Exception e) {
            System.err.println("Error while flushing ExtentReports: " + e.getMessage());
        }
    }
}
