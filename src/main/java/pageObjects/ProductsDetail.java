package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsDetail extends AbstractComponent {
    WebDriver driver;
    @FindBy(xpath = "//h2")
    WebElement productName;
    @FindBy(xpath = "//div[@class='container mt-5']//h3")
    WebElement price;
    @FindBy(css = ".img-fluid")
    WebElement image;

    public ProductsDetail(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        waitForWebElementToAppear(image);
        return productName.getText();
    }

    public String getProductPrice() {
        return price.getText();
    }


}
