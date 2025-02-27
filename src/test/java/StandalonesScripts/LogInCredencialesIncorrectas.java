package StandalonesScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInCredencialesIncorrectas {
    public static void main(String[] args) {

        String email = "kevger@gmail.com";
        String pass = "Iamking123_worng";
        String base_url = "https://rahulshettyacademy.com/client";
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(base_url);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userPassword")).sendKeys(pass);
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String wrong_message = "//div[@aria-label='Incorrect email or password.']";
        String good_message = "//div[@aria-label='Login Successfully']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wrong_message)));

    }
}
