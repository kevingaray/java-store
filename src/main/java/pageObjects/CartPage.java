package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        return names.stream()
                .allMatch(name -> cartProducts.stream()
                        .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(name))
                );
    }


//    @FindBy(css = ".totalRow button")
//    WebElement checkOuElement;
//

//    public CheckoutPage goToCheckOut(){
//        checkOuElement.click();
//        return new CheckoutPage(driver);
//    }
}
