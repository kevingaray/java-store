package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement EmailInput;

    @FindBy(id = "userPassword")
    WebElement PasswordInput;

    @FindBy(id = "login")
    WebElement submitButton;

    @FindBy(css = "[class*='flyInOut']")
    WebElement alertMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public ProductsCatalog loginApplication(String email, String password) {
        EmailInput.sendKeys(email);
        PasswordInput.sendKeys(password);
        submitButton.click();
        return new ProductsCatalog(driver);

    }

    public String getAlertMessage() {
        waitForWebElementToAppear(alertMessage);
        return alertMessage.getText();
    }

}
