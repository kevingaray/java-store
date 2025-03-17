package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;
    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement orderHeader;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForWebElementToAppear(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForWebElementToAppear(List<WebElement> webElementList) {
        wait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
    }

    public void waitForElementToDisappear(WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public CartPage goToCartPage() {
        waitForWebElementToAppear(cartHeader);
        cartHeader.click();
        return new CartPage(driver);
    }

    public OrderPage gotoOrderPage() {
        orderHeader.click();
        return new OrderPage(driver);
    }

}
