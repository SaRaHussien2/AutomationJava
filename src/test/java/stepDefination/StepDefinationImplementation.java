package stepDefination;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import AutomationFramework.AutomationFrameworkProject.pageobject.CartPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.CheckoutPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.ConfirmationPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.LandingPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.ProductCataloguePage;
import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest {

	public LandingPage landinPage;
	public ProductCataloguePage productCataloguePage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landinPage = launchAppliaction();
	}

	@Given("^Logged in with username (.+) and (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		// Write code here that turns the phrase above into concrete actions
		productCataloguePage = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		// Write code here that turns the phrase above into concrete actions
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String productName) {
		CartPage cartPage = productCataloguePage.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {

		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}

}
