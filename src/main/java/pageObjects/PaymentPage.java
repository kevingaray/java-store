package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage extends AbstractComponent {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".item__title")
    List<WebElement> paymentProducts;

    public List<WebElement> getProductList() {
        waitForWebElementToAppear(paymentProducts);
        return paymentProducts;
    }

    public Boolean VerifyProductDisplay(String name) {
        waitForWebElementToAppear(paymentProducts);
        return paymentProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(name));
    }
}
