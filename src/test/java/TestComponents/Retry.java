package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class Retry implements IRetryAnalyzer {
    int maxTry = Integer.parseInt(System.getProperty("maxTry", ConfigReader.getProperty("maxTry")));
    int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        if(count<maxTry)
        {
            count++;
            return true;
        }
        return false;
    }
}
