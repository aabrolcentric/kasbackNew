package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.pages.KasbackFavouritesPage;
import com.kasback.pages.KasbackHomePage;
import com.kasback.pages.KasbackLoginPage;
import com.kasback.pages.KasbackProfilePage;

public class KasbackFavourites extends AbstractTest{
	
		WebDriver driver = null;
		KasbackLoginPage loginPage = null;
		KasbackFavouritesPage favouritesPage = null;
		KasbackHomePage homepage = null;
		KasbackProfilePage profilePage = null;
		String existingUserId = "username2299@gmail.com";
		String existingPassword = "Test@1";
		
		@Test
		public void deleteProductFromFavouritesAndVerify() {
			driver = getDriver();
			loginPage = new KasbackLoginPage(driver);
			homepage =  new KasbackHomePage(driver);
			favouritesPage = new KasbackFavouritesPage(driver);
			loginPage.browseURL(environment.get("baseurl"));
			loginPage.login(existingUserId);
			homepage.selectAProduct("apple fresh fruits");
			favouritesPage.clickAddToFavoritesButtonAndVerify();
			favouritesPage.goToFavouritesPage();
			favouritesPage.deleteProductFromFavourites();
		}
		
		@AfterClass
		public void afterClass() {
			driver.close();
		}

}
