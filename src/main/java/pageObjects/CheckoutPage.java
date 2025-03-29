package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".item__title")
    List<WebElement> checkoutProducts;
    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryInput;
    @FindBy(css = ".action__submit")
    WebElement submit;
    @FindBy(css = ".ta-results")
    WebElement results;
    String xpath_dropdown_options = "//span[normalize-space(text())='%s']";

    public boolean VerifyProductsInCheckout(List<String> names) {
        waitForWebElementToAppear(checkoutProducts);
        return names.stream().allMatch(name -> checkoutProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(name)));
    }

    public void selectCountry(String countryName) {
        countryInput.sendKeys(countryName);
        waitForWebElementToAppear(results);
        String format_option = String.format(xpath_dropdown_options, countryName);
        WebElement format_element = driver.findElement(By.xpath(format_option));
        format_element.click();
    }

    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }

    public Boolean VerifyProductDisplay(String name) {
        waitForWebElementToAppear(checkoutProducts);
        return checkoutProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(name));
    }
}
