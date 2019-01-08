package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.pages.KasbackLoginPage;

public class KasbackLogin extends AbstractTest{
	WebDriver driver = null;
	KasbackLoginPage loginPage = null;
	String buyerId = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";
	
	@Test
	public void loginWithExistingCustomer() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingUserId);
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
	
	@Test(dependsOnMethods="buyerSignUp")
	public void doLogin() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(buyerId);
	}
	
	
	
}
