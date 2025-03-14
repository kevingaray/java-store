package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[contains(@class, 'ng-star-inserted')]")
    List<WebElement> orders;
    @FindBy(xpath = "//label[@routerlink='/dashboard/myorders']")
    WebElement orderPageLink;

    public List<String> getOrders() {
        waitForWebElementToAppear(orders);
        List<String> orders_list = orders.stream().map(WebElement::getText).toList();
        return orders_list.stream().map(s -> s.replaceAll("[|\\s]", "")).toList();
    }


    public OrderPage gotoOrderPage() {
        waitForWebElementToAppear(orderPageLink);
        orderPageLink.click();
        return new OrderPage(driver);
    }
}
