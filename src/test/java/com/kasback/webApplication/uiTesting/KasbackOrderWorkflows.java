package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.pages.KasbackHomePage;
import com.kasback.pages.KasbackLoginPage;
import com.kasback.pages.KasbackOrderWorkflowsPage;
import com.kasback.pages.KasbackProfilePage;

public class KasbackOrderWorkflows extends AbstractTest{

	WebDriver driver;
	KasbackLoginPage loginPage = null;
	KasbackLogin login = null;
	KasbackHomePage homepage = null;
	KasbackOrderWorkflowsPage workflowsPage = null;
	KasbackProfilePage profilePage = null;
	String emailId;
	String existingUserId = "username2299@gmail.com";
	
	@Test
	public void buyerSignUp() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("signupurl"));
		loginPage = new KasbackLoginPage(driver);
		emailId = loginPage.signUpAsBuyer();
	}
	
	@Test
	public void sellerSignUp() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("signupurl"));
		loginPage = new KasbackLoginPage(driver);
		emailId = loginPage.signUpAsSeller();
	}
	
	@Test(dependsOnMethods="buyerSignUp")
	public void loginAsBuyer() {
		homepage =  new KasbackHomePage(driver);
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(emailId);
	}
	
	@Test(dependsOnMethods="sellerSignUp")
	public void loginAsSeller() {
		homepage =  new KasbackHomePage(driver);
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(emailId);
	}
	
	@Test(dependsOnMethods="loginAsBuyer")
	public void placeNewOrder() {
		homepage =  new KasbackHomePage(driver);
		workflowsPage = new KasbackOrderWorkflowsPage(driver);
		homepage.selectAProduct("apple fresh fruits");
		workflowsPage.clickBuyNowButton();
		workflowsPage.manageAddress();
		workflowsPage.selectWalletPaymentMethod();
		String ordersCount = pgutil.executeSelectQuery("select count(*) from kasback.order where buyer_id in (select id from kasback.user where email="+"'"+emailId+"')");
		log.info("Orders count is "+ordersCount);
		Assert.assertEquals(ordersCount, "1");
	}
	
	@Test(dependsOnMethods="loginWithExistingCustomer")
	public void AddProductToCart() {
		workflowsPage = new KasbackOrderWorkflowsPage(driver);
		homepage =  new KasbackHomePage(driver);
		homepage.selectAProduct("apple fresh fruits");
		workflowsPage.clickAddToCartButtonAndVerify();
	}
	
	@Test(dependsOnMethods="AddProductToCart")
	public void changeOrderQuantity() {
		workflowsPage.increaseDecreaseOrderQuantity();
	}
	
	@Test(dependsOnMethods="changeOrderQuantity")
	public void removeProductFromCart() {
		workflowsPage.removeProduct();
	}
	
	@Test
	public void loginWithExistingCustomer() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingUserId);
	}
	
	@Test(dependsOnMethods="loginWithExistingCustomer")
	public void searchForNonExistingProduct() {
		loginPage = new KasbackLoginPage(driver);
		homepage =  new KasbackHomePage(driver);
		workflowsPage = new KasbackOrderWorkflowsPage(driver);
		homepage.searchProductName("Agam");
		homepage.clickSearchButton();
		workflowsPage.checkNonExistingProduct();
	}
	
	
	
//	@AfterClass
//	public void afterClass() {
//		driver.close();
//	}
}
