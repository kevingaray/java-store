package StandalonesScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AddItemtoCart {
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

        String xpath_add_to_cart = String.format("//b[text()='%s']/ancestor::div/button[normalize-space(text())='Add To Cart']",item_title);
        driver.findElement(By.xpath(xpath_add_to_cart)).click();

        // alert
        String add_to_cart_message = "//div[@aria-label='Product Added To Cart']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(add_to_cart_message)));

        //label
        String label_alert_xpath = "//button[@routerlink='/dashboard/cart']//label";
        String label_number = driver.findElement(By.xpath(label_alert_xpath)).getText();
        Assert.assertEquals(label_number,"1");

        // verify in cart page
        String button_cart_xpath = "//button[@routerlink='/dashboard/cart']";
        driver.findElement(By.xpath(button_cart_xpath)).click();

        String element_in_cart_xpath = "//div[@class='infoWrap']/div[@class='cartSection']/h3";
        String element_text = driver.findElement(By.xpath(element_in_cart_xpath)).getText();
        Assert.assertEquals(element_text,item_title);


    }
}
