package com.kasback.pages;

import org.openqa.selenium.WebDriver;

import com.kasback.abstestbase.AbstractPage;
import com.kasback.abstestbase.UITestBase;


public class KasbackHomePage extends AbstractPage{
	WebDriver driver;
	UITestBase uitestbase = new UITestBase();
	String host = "imap.googlemail.com";// change accordingly
	String mailStoreType = "imap";
	String identificationcode;
	String finalCode;


	public KasbackHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void searchProductName(String productName) {
		checkPageIsReady();
		click(getWebElement("searchBox"));
		getWebElement("searchBox").sendKeys(productName);
	}
	
	public void clickSearchButton() {
		click(getWebElement("searchButton"));
	}
	
	public void clickViewMoreButtonOfFirstProductOnHomePage() {
		click(getWebElement("viewMoreButton"));
	}
	
	public void clickProductName() {
		click(getWebElement("firstServiceInSearchAutoFill"));
	}
	
	public KasbackHomePage selectAProduct(String productName) {
		searchProductName(productName);
		checkPageIsReady();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickProductName();
		checkPageIsReady();
		return new KasbackHomePage(driver);
	}
	
	
	
	
	
}
