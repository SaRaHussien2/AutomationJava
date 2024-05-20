package AutomationFramework.AutomationFrameworkProject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import AutomationFramework.AutomationFrameworkProject.pageobject.CartPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.ProductCataloguePage;
import TestComponents.BaseTest;

public class ErrorValidation extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		landingPage.loginApplication("anshika@gmail.com", "xxx");

		Assert.assertEquals("Incorrect email or pasddsword.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("rahulshetty@gmail.com",
				"Iamking@000");

		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(productName);

		CartPage cartPage = productCataloguePage.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertTrue(match);

	}

}
