package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        // Create directory if it doesn't exist
        String reportPath = System.getProperty("user.dir") + "/reports/";
        File reportDir = new File(reportPath);
        if (!reportDir.exists()) {
            boolean created = reportDir.mkdirs();
            if (!created) {
                System.out.println("⚠ Directory not created " + reportPath);
            }
        }

        // Create report if it doesn't exist
        String path = reportPath + "index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Kevin Garay");

        return extent;
    }
}