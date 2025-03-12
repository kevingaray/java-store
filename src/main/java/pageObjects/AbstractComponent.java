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

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;

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
        cartHeader.click();
        return new CartPage(driver);
    }

}
