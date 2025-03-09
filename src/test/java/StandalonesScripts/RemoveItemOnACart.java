package StandalonesScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RemoveItemOnACart {
    public static void main(String[] args) throws InterruptedException {

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

        String item_title = "ADIDAS ORIGINAL";

        String xpath_add_to_cart = String.format("//b[text()='%s']/ancestor::div/button[normalize-space(text())='Add To Cart']",item_title);
        driver.findElement(By.xpath(xpath_add_to_cart)).click();

        // alert
        String add_to_cart_message = "//div[@aria-label='Product Added To Cart']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(add_to_cart_message)));

        //second item
        String item_title_2 = "ZARA COAT 3";
        String xpath_add_to_cart_2 = String.format("//b[text()='%s']/ancestor::div/button[normalize-space(text())='Add To Cart']",item_title_2);
        driver.findElement(By.xpath(xpath_add_to_cart_2)).click();

        //label 2
        String label_alert_xpath = "//button[@routerlink='/dashboard/cart']//label";
        WebElement label_number = driver.findElement(By.xpath(label_alert_xpath));
        wait.until(ExpectedConditions.textToBePresentInElement(label_number, "2"));

        // verify in cart page
        String button_cart_xpath = "//button[@routerlink='/dashboard/cart']";
        driver.findElement(By.xpath(button_cart_xpath)).click();

        //FIRST ITEM
        String item_in_cart_xpath = "//div[@class='infoWrap']/div[@class='cartSection']/h3[normalize-space(text())='%s']";
        String item_in_cart_1_xpath = String.format(item_in_cart_xpath,item_title);
        String item_in_cart_2_xpath = String.format(item_in_cart_xpath,item_title_2);

        WebElement item_in_cart_1 = driver.findElement(By.xpath(item_in_cart_1_xpath));
        WebElement item_in_cart_2 = driver.findElement(By.xpath(item_in_cart_2_xpath));

        Assert.assertTrue(item_in_cart_1.isDisplayed());
        Assert.assertTrue(item_in_cart_2.isDisplayed());

        // get elements in cart
        //div[@class='infoWrap'][.//h3]
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='infoWrap']//h3"));
        List<String> texts = elements.stream().map(WebElement::getText).toList();
        System.out.println("texts 1");
        System.out.println(texts);

        //REMOVE ITEM FROM A CART
        String remove_item_button_xpath = String.format("//div[@class='infoWrap'][.//h3[normalize-space()='%s']]//button[contains(@class, 'btn-danger')]",item_title);
        driver.findElement(By.xpath(remove_item_button_xpath)).click();


        // get elements in cart
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> elements_2 = driver.findElements(By.xpath("//div[@class='infoWrap']//h3"));
        List<String> texts_2 = elements_2.stream().map(WebElement::getText).toList();
        System.out.println("texts 2");
        System.out.println(texts_2);



    }
}
