package StandalonesScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuyMultipleItems {
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

        //declare list
        List<String> elements_to_buy = new ArrayList<>();
        elements_to_buy.add(item_title);
        elements_to_buy.add(item_title_2);


        // buy items
        WebElement checkoutButton = driver.findElement(By.xpath("//button[text()='Checkout']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(checkoutButton).perform();
        checkoutButton.click();


        // verify page
        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 'item__title')]"));
        List<String> items_text = items.stream().map(WebElement::getText).toList();


        for (String item : elements_to_buy) {
            Assert.assertTrue(items_text.contains(item), "Item not in payment page: " + item_title);
        }


        //select country
        String xpath_input_dropdown = "//input[@placeholder='Select Country']";
        String country = "Peru";
        driver.findElement(By.xpath(xpath_input_dropdown)).sendKeys(country);

        String xpath_dropdown_options = "//span[normalize-space(text())='%s']";
        String format_option = String.format(xpath_dropdown_options,country);
        driver.findElement(By.xpath(format_option)).click();


        // paid button
        String xpath_place_order = "//a[normalize-space(text())='Place Order']";
        driver.findElement(By.xpath(xpath_place_order)).click();

        // save orders
        List<WebElement> orders = driver.findElements(By.xpath("//label[contains(@class, 'ng-star-inserted')]"));
        List<String> orders_list = orders.stream().map(WebElement::getText).toList();

        List<String> clean_list = orders_list.stream().map(s -> s.replaceAll("[|\\s]", "")).toList();

        System.out.println("lists of orders");
        System.out.println(clean_list);

        // go to orders
        WebElement ordersButton = driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']"));
        actions.moveToElement(ordersButton).perform();
        ordersButton.click();


        // list of orders
        List<WebElement> orders_history = driver.findElements(By.xpath("//tbody//tr/th"));
        List<String> orders_history_list = orders_history.stream().map(WebElement::getText).toList();


        for (String item_order : clean_list) {
            Assert.assertTrue(orders_history_list.contains(item_order), "Order not in payment page: " + item_title);
        }





    }
}
