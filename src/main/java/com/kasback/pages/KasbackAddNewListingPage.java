package com.kasback.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.kasback.abstestbase.AbstractPage;

public class KasbackAddNewListingPage extends AbstractPage {

	WebDriver driver;
	KasbackLoginPage loginPage = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";

	public KasbackAddNewListingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void goToAddListingPage() {
		checkPageIsReady();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(getWebElement("sellOnKasbackLink"));
		checkPageIsReady();
	}

	public void selectNewCategory() {
		checkPageIsReady();
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement("mainCategory")).perform();
		actions.moveToElement(getWebElement("category2")).perform();
		actions.moveToElement(getWebElement("category3")).perform();
		actions.moveToElement(getWebElement("category4")).perform();
		actions.moveToElement(getWebElement("category5")).perform();
		click(getWebElement("category6"));
		actions.sendKeys(Keys.ESCAPE).perform();
	}

	public void addProductDetails() {
		type(getWebElement("productName"), "BANANATEST");
		type(getWebElement("brandName"), "BANANATEST");
		type(getWebElement("weight"), "12");
		selectByOption(getWebElement("units"), "grams");
		click(getWebElement("producDetailsINextButton"));
		type(getWebElement("margin"), "2");
		clear(getWebElement("price"));
		type(getWebElement("price"), "20");
		type(getWebElement("quantity"), "20");
		type(getWebElement("shortDesc"), "BANANATEST");
		type(getWebElement("longDesc"), "BANANATEST");
		click(getWebElement("producDetailsIINextButton"));
		type(getWebElement("hsnName"), "BANANA");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(getWebElement("hsnNameLink"));
		click(getWebElement("gstNextButton"));
		click(getWebElement("specificationNextButton"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		// click(getWebElement("uploadImageButton"));
		String workingDir = System.getProperty("user.dir");
		type(getWebElement("uploadImageButton"), workingDir + "/src/test/resources/computer_pc_PNG7720.png");
		click(getWebElement("submitButton"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String errorText = getWebElementText("errorContent");
		log.info(errorText);
		click(getWebElement("okButtonOnPopup"));
	}

	public void deleteAProduct() {
		click(getWebElement("deleteProductButton"));
		click(getWebElement("okButtonOnPopup"));
	}

	public void uploadImageInInvalidFormat() {
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement("profileMenu")).perform();
		click(getWebElement("dashboardNavMenuButton"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(getWebElement("productListing"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(getWebElement("editProductButton"));
		click(getWebElement("producDetailsINextButton"));
		click(getWebElement("producDetailsIINextButton"));
		click(getWebElement("gstNextEditButton"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		String workingDir = System.getProperty("user.dir");
		type(getWebElement("uploadImageButton"), workingDir + "/src/test/resources/instagram-400x400-300x300.jpg");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(getWebElement("submitEditButton"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String errorText = getWebElementText("errorContent");
		log.info(errorText);
		click(getWebElement("okButtonOnPopup"));

	}
}
