package com.kasback.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.kasback.abstestbase.AbstractPage;


public class KasbackProfilePage extends AbstractPage{

	WebDriver driver;
	KasbackLoginPage loginPage = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";
	
	public KasbackProfilePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	public void goToEditProfile() {
		loginPage = new KasbackLoginPage(driver);
		checkPageIsReady();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement("profileMenu")).perform();
		click(getWebElement("profileNavButton"));
		checkPageIsReady();
		click(getWebElement("editButton"));
	}
	
	public void checkFieldIsEditable(String field) {
		if(isWebElementEnabled(field) == true) {
			log.info(field+ " field is Enabled");
		}else {
			log.info(field + " field is disabled");
		}
	}
	
	public void checkFieldValidation(String field, String content) {
		getWebElement(field).sendKeys(content);
	}
}
