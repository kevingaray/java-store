package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody//tr/th")
    List<WebElement> orders;

    public boolean VerifyOrders(List<String> names) {
        waitForWebElementToAppear(orders);
        return names.stream().allMatch(name -> orders.stream().anyMatch(order -> order.getText().equalsIgnoreCase(name)));
    }


}
