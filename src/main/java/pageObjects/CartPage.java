package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@role='alert']")
    WebElement alert;

    @FindBy(xpath = "//h1[@style='color: lightgray;']")
    WebElement title;

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkoutElement;


    String delete_item_xpath = "//div[@class='infoWrap'][.//h3[normalize-space()='%s']]//button[contains(@class, 'btn-danger')]";
    String buy_item_xpath = "//div[@class='infoWrap'][.//h3[normalize-space()='%s']]//button[contains(@class, 'btn-primary')]";

    public String getAlertMessage() {
        waitForWebElementToAppear(alert);
        return alert.getText();
    }

    public String getTitleMessage() {
        waitForWebElementToAppear(title);
        return title.getText();
    }

    public Boolean VerifyProductDisplay(String name) {
        waitForWebElementToAppear(cartProducts);
        return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(name));
    }

    public boolean VerifyProductsDisplay(List<String> names) {
        waitForWebElementToAppear(cartProducts);
        return names.stream().allMatch(name -> cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(name)));
    }

    public List<String> getProductList() {
        waitForWebElementToAppear(cartProducts);
        return cartProducts.stream().map(WebElement::getText).toList();
    }


    public void removeItem(String item) {
        String remove_item_button_xpath = String.format(delete_item_xpath, item);
        driver.findElement(By.xpath(remove_item_button_xpath)).click();
    }

    public PaymentPage buyItem(String item) {
        waitForWebElementToAppear(cartProducts);
        String buy_now_xpath = String.format(buy_item_xpath, item);
        driver.findElement(By.xpath(buy_now_xpath)).click();
        return new PaymentPage(driver);
    }

    public CheckoutPage goToCheckOut() {
        Actions actions = new Actions(driver);
        actions.moveToElement(checkoutElement).perform();
        checkoutElement.click();
        return new CheckoutPage(driver);
    }
}
