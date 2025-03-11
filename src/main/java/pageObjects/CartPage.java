package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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


    public String getAlertMessage() {
        waitForWebElementToAppear(alert);
        return alert.getText();
    }

    public String getTitleMessage() {
        waitForWebElementToAppear(title);
        return title.getText();
    }


//    @FindBy(css = ".cartSection h3")
//    List<WebElement> cartProducts;
//
//    @FindBy(css = ".totalRow button")
//    WebElement checkOuElement;
//
//    public Boolean VerifyProductDisplay(String productName) {
//        return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
//    }
//
//    public CheckoutPage goToCheckOut(){
//        checkOuElement.click();
//        return new CheckoutPage(driver);
//    }
}
