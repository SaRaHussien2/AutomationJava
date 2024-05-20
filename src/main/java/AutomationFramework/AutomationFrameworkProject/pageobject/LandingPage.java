package AutomationFramework.AutomationFrameworkProject.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		// Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmails = driver.findElement(By.id("userEmail"));

	// PageFactory

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginBtn;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCataloguePage loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
		return productCataloguePage;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");

	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
