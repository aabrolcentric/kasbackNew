package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.pages.KasbackAddNewListingPage;
import com.kasback.pages.KasbackFavouritesPage;
import com.kasback.pages.KasbackHomePage;
import com.kasback.pages.KasbackLoginPage;
import com.kasback.pages.KasbackProfilePage;

public class KasbackAddNewListing extends AbstractTest{
	WebDriver driver = null;
	KasbackLoginPage loginPage = null;
	KasbackFavouritesPage favouritesPage = null;
	KasbackAddNewListingPage newListingPage = null;
	KasbackHomePage homepage = null;
	KasbackProfilePage profilePage = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";
	String existingSeller = "username9946@gmail.com";

	
	@Test
	public void AddNewListing() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		newListingPage = new KasbackAddNewListingPage(driver);
		homepage =  new KasbackHomePage(driver);
		favouritesPage = new KasbackFavouritesPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login(existingSeller);
		newListingPage.goToAddListingPage();
		newListingPage.selectNewCategory();
		newListingPage.addProductDetails();
	}
	
	//Delete a Product from inventory
	@Test(dependsOnMethods="AddNewListing")
	public void deleteAProduct() {
		newListingPage.deleteAProduct();
	}
	
	//Try to Upload Image of Product in Invalid Format and Resolution
	@Test(dependsOnMethods="AddNewListing")
	public void uploadImageInWrongFormat() {
		newListingPage.uploadImageInInvalidFormat();
	}
}
