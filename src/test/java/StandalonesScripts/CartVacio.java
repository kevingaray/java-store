package StandalonesScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartVacio {
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

        String button_cart_xpath = "//button[@routerlink='/dashboard/cart']";
        driver.findElement(By.xpath(button_cart_xpath)).click();

        String alert_xpath = "//div[@role='alert']";
        String text_xpath = "//h1[@style='color: lightgray;']";
        String text = driver.findElement(By.xpath(text_xpath)).getText();
        String alert = driver.findElement(By.xpath(alert_xpath)).getText();

        Assert.assertEquals(text,"No Products in Your Cart !");
        Assert.assertEquals(text,"No Products in Your Cart !");



    }
}
