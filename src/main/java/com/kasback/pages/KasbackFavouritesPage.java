package com.kasback.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.kasback.abstestbase.AbstractPage;

public class KasbackFavouritesPage extends AbstractPage{
	
	WebDriver driver;
	KasbackLoginPage loginPage = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";
	
	public KasbackFavouritesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	public void goToFavouritesPage() {
		checkPageIsReady();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement("profileMenu")).perform();
		click(getWebElement("favouritesNavMenuButton"));
	}
	
	public void clickAddToFavoritesButtonAndVerify() {
		checkPageIsReady();
		click(getWebElement("addToFavoritesButton"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(getWebElement("okButtonOnPopup"));
	}
	
	public void deleteProductFromFavourites() {
		click(getWebElement("deleteButton"));
		click(getWebElement("okButtonOnPopup"));
	}
}
