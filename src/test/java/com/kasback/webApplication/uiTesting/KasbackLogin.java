package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.abstestbase.UITestBase;
import com.kasback.pages.KasbackLoginPage;

public class KasbackLogin extends AbstractTest{
	WebDriver driver = null;
	KasbackLoginPage loginPage = null;
	UITestBase uitestbase = null;
	String buyerId = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";
	String unverifiedUser="usename6180@gmail.com";
	String blockedUser="username6918@gmail.com";
	String existingSeller = "username9946@gmail.com";
	String unverifiedSeller="usename1827@gmail.com";
	String existingSellerCaseSensitive = "USERNAME9946@GMAIL.COM";
	String blockedSeller="username4651@gmail.com";
	
	@Test
	public void loginWithExistingCustomer() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingUserId);
	}
	
	//Login with credentials of Unverified User
	@Test
	public void loginWithUnverifiedUser() {
		driver=getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(unverifiedUser);
	}
	
	//Try to Login When account is not verified by Admin
	@Test
	public void loginWithUnverifiedSeller() {
		driver=getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(unverifiedSeller);
	}
	
	//Add blocked user
	@Test
	public void loginWithBlockedBuyer() {
		driver=getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(blockedUser);
	}
	
	
	//Try to Create Account as a Seller with existing credentials
	@Test
	public void tryLoginWithExistingSeller() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingSeller);
	}
	
	//After executing this function execute the activateSellerAndLogin function without fail
	//Deactivate Seller Account From Admin and try to login again
	@Test
	public void deactivateSellerAndLogin() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		uitestbase = new UITestBase();
		uitestbase.deactivateSeller(existingSeller);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingSeller);
	}
	
	//Re-Activate Seller Account From Admin and try to login again
	@Test
	public void activateSellerAndLogin() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		uitestbase = new UITestBase();
		uitestbase.activateSeller(existingSeller);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingSeller);
	}
	
	//Try to Login with Case Insensitive credentials
	@Test
	public void tryLoginWithExistingSellerWithCaseSensitiveCredentials() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingSellerCaseSensitive);
	}
	
	@Test
	public void buyerSignUp() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		System.out.println(environment.get("signupurl"));
		loginPage.browseURL(environment.get("signupurl"));
		loginPage = new KasbackLoginPage(driver);
		buyerId = loginPage.signUpAsBuyer();
	}
	
	//Block a Buyer and try to create new account with same email ID
	@Test
	public void signUpWithBockedId() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		System.out.println(environment.get("signupurl"));
		loginPage.browseURL(environment.get("signupurl"));
		loginPage = new KasbackLoginPage(driver);
		loginPage.signUpWithBlockedId(blockedUser);
	}
	
	//Block a Seller and try to create new account with same email ID
	@Test
	public void signUpWithBockedSellerId() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		System.out.println(environment.get("signupurl"));
		loginPage.browseURL(environment.get("signupurl"));
		loginPage = new KasbackLoginPage(driver);
		loginPage.signUpWithBlockedId(blockedSeller);
	}
	
	@Test(dependsOnMethods="buyerSignUp")
	public void doLogin() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(buyerId);
	}
	
	
}
