package StandalonesScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SeeItemDetails {
    public static void main(String[] args) {

        String email = "kevger@gmail.com";
        String pass = "Iamking123";
        String base_url = "https://rahulshettyacademy.com/client";
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(base_url);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userPassword")).sendKeys(pass);
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String good_message = "//div[@aria-label='Login Successfully']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(good_message)));

        String item_title = "ADIDAS ORIGINAL";

        //xpath //h5/b[text()="ADIDAS ORIGINAL"]/../../button[text()=" View"]
        //b[text()="ADIDAS ORIGINAL"]/ancestor::div/button[normalize-space(text())='View']

        String xpath_view_item = String.format("//b[text()='%s']/ancestor::div/button[normalize-space(text())='View']",item_title);

        driver.findElement(By.xpath(xpath_view_item)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add to Cart']")));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h2"), item_title));

        String price_xpath = "//div/h3[contains(text(), '$')]";

        String item_price = driver.findElement(By.xpath(price_xpath)).getText();
        System.out.println(item_price);





    }
}
