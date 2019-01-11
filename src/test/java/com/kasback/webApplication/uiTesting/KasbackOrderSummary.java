package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.abstestbase.UITestBase;
import com.kasback.pages.KasbackHomePage;
import com.kasback.pages.KasbackLoginPage;
import com.kasback.pages.KasbackOrderSummaryPage;
import com.kasback.pages.KasbackOrderWorkflowsPage;
import com.kasback.pages.KasbackProfilePage;

public class KasbackOrderSummary extends AbstractTest{
	WebDriver driver = null;
	KasbackLoginPage loginPage = null;
	UITestBase uitestbase = null;
	KasbackOrderWorkflowsPage workflowsPage = null;
	KasbackOrderSummaryPage summaryPage = null;
	KasbackHomePage homepage = null;
	KasbackProfilePage profilePage = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";
	
	@Test
	public void verifyOrderSummary() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		uitestbase = new UITestBase();
		workflowsPage = new KasbackOrderWorkflowsPage(driver);
		homepage =  new KasbackHomePage(driver);
		summaryPage = new KasbackOrderSummaryPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingUserId);
		homepage.selectAProduct("manam");
		workflowsPage.clickBuyNowButton();
		workflowsPage.manageAddress();
		workflowsPage.selectWalletPaymentMethod();
		//Check order summary of product
		summaryPage.verifyOrderDetails();
		//view shipping details of product
		summaryPage.viewShippingDetailsAndVerify("order placed");
	}
	
	//Check Ordered product status ie.,which is updated by seller or admin
	@Test(dependsOnMethods="verifyOrderSummary")
	public void verifyOrderStatusAfterUpdateFromAdmin() {
		uitestbase.updateOrderStatus();
		summaryPage.viewShippingDetailsAndVerify("shipped");
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
