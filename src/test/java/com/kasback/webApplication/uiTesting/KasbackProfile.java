package com.kasback.webApplication.uiTesting;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.pages.KasbackLoginPage;
import com.kasback.pages.KasbackProfilePage;

public class KasbackProfile extends AbstractTest{
	WebDriver driver = null;
	KasbackLoginPage loginPage = null;
	KasbackProfilePage profilePage = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";
	String existingSeller = "username9946@gmail.com";
	
	@Test
	public void loginWithExistingCustomerAndGoToProfilePage() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		profilePage = new KasbackProfilePage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingUserId);
		profilePage.goToEditProfile();
	}
	
	//Edit Profile as Buyer (User type and email should not be editable)
	@Test(dependsOnMethods="loginWithExistingCustomerAndGoToProfilePage")
	public void checkUserTypeAndEmailNotEditable() {
		profilePage.checkFieldIsEditable("email");
		profilePage.checkFieldIsEditable("userType");
	}
	
	//Try editing invalid format. Example: Add characters in Phone Number field
	@Test(dependsOnMethods="loginWithExistingCustomerAndGoToProfilePage")
	public void checkValidation() {
		profilePage.checkFieldValidation("phone", "aaaaaaaaaa");
		String validationText = profilePage.getWebElementText("phoneValidationText");
		Assert.assertEquals(validationText, "Valid phone number is required");
	}
	
	//Try to Edit Seller Profile
	@Test
	public void editSellerProfile() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		profilePage = new KasbackProfilePage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingSeller);
		profilePage.goToEditProfile();
		profilePage.editField("zipcode", "123123");
		profilePage.clickButton("updateProfileButton");
		profilePage.clickButton("okButtonOnPopup");
	}
	
	
}
