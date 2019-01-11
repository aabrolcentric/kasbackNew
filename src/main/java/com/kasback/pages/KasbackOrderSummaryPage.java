package com.kasback.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.kasback.abstestbase.AbstractPage;
import com.kasback.webApplication.uiTesting.KasbackOrderWorkflows;

public class KasbackOrderSummaryPage extends AbstractPage {
	WebDriver driver;
	KasbackLoginPage loginPage = null;
	KasbackOrderWorkflows workflows = null;
	String existingUserId = "username2299@gmail.com";
	String existingPassword = "Test@1";

	public KasbackOrderSummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void goToOrderSummaryPage() {
		checkPageIsReady();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement("profileMenu")).perform();
		click(getWebElement("dashboardNavMenuButton"));
		checkPageIsReady();
	}

	public void verifyOrderDetails() {
		long millis = System.currentTimeMillis();
		java.util.Date date = new java.util.Date(millis);
		String currentDateAndTime = date.toString();
		String currentMonth = currentDateAndTime.substring(4, 7);
		String currentDate = currentDateAndTime.substring(9, 11);
		String currentMonthAndDate = currentMonth + " " + currentDate;
		goToOrderSummaryPage();
		String orderDate = getWebElementText("orderedDate");
		Assert.assertEquals(currentMonthAndDate, orderDate.substring(0, 6).replace(",", " "));
	}

	public void viewShippingDetailsAndVerify(String OrderStatus) {
		refreshPage();
		click(getWebElement("viewShippingDetailsButton"));
		checkPageIsReady();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String orderStatus = getWebElementValue("orderStatus");
		System.out.println(orderStatus);
		Assert.assertEquals(orderStatus, OrderStatus);
		click(getWebElement("closeShippingDetailsModal"));
	}

}
