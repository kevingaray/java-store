package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsCatalog extends AbstractComponent {
    WebDriver driver;

    public ProductsCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    @FindBy(css = "#toast-container")
    WebElement toastMessage;

    @FindBy(css = "[routerlink*='cart'] label")
    WebElement labelNumber;

    // items
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By viewDetails = By.cssSelector(".card-body button:first-of-type");

    public List<WebElement> getProductList() {
        waitForWebElementToAppear(products);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    }


    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        waitForElementToDisappear(spinner);
        waitForElementToBeClickable(prod.findElement(addToCart));
        prod.findElement(addToCart).click();
        waitForWebElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

    public int getCartNumber() {
        wait.until(_ -> !labelNumber.getText().isEmpty());
        return Integer.parseInt(labelNumber.getText());
    }

    public void waitUpdateCartNumber() {
        String previousValue = labelNumber.getText();

        try {
            wait.until(_ -> {
                String currentValue = labelNumber.getText();
                return !currentValue.isEmpty() && !currentValue.equals(previousValue);
            });
        } catch (TimeoutException e) {
            System.out.println("Timeout, but continuous with the process.");
        }
    }

    public void addProductsToCart(List<String> productsList) {
        for (String product : productsList) {
            try {
                addProductToCart(product);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Error adding product: " + product, e);
            }
        }
    }

    public ProductsDetail viewDetailsOfProduct(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(viewDetails).click();
        return new ProductsDetail(driver);

    }

    public CartPage goToCartPage() {
        waitForElementToDisappear(spinner);
        waitForElementToBeClickable(cartHeader);
        cartHeader.click();
        return new CartPage(driver);
    }


}
