package AutomationFramework.AutomationFrameworkProject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationFramework.AutomationFrameworkProject.pageobject.ConfirmationPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.CartPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.CheckoutPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.OrderPage;
import AutomationFramework.AutomationFrameworkProject.pageobject.ProductCataloguePage;
import TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("email"),
				input.get("password"));

		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(input.get("productName"));

		CartPage cartPage = productCataloguePage.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	// Verify ZARA COAT 3 is displaying in orders page

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderPage = productCataloguePage.goToOrdersPage();
		orderPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "anshika@gmail.com", "Iamking@000", "ZARA COAT 3" },
//				{ "sara.tesrter.12345@gmail.com", "Sara.tester@123", "ADIDAS ORIGINAL" } };
//	}

//	@DataProvider
//	public Object[][] getData() {
//
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
////		map1.put("email", "shetty@gmail.com");
////		map1.put("password", "Iamking@000");
//
//		map1.put("email", "sara.tesrter.12345@gmail.com");
//		map1.put("password", "Sara.tester@123");
//		map1.put("productName", "ADIDAS ORIGINAL");
//
//		return new Object[][] { { map }, { map1 } };
//	}

}
