package com.kasback.pages;

import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.testng.annotations.BeforeClass;

import com.kasback.abstestbase.AbstractPage;
import com.kasback.abstestbase.UITestBase;

public class KasbackLoginPage extends AbstractPage{
	WebDriver driver;
	KasbackHomePage homepage = null;
	UITestBase uitestbase = new UITestBase();
	static Random rad = new Random();
	String password="Test@1";
	
	@BeforeClass
		 public static String randomEmail() {
			String email = null;
			for (int j=1; j<=1; j++ )
	        {
				email="username"+rad.nextInt(10000)+"@gmail.com";
	        System.out.print(email);  
	        }
	        return email;
	    }
	
	
	String buyerId = randomEmail();
	String sellerId = randomEmail();
	
	public KasbackLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void browseURL(String url) {
		driver.get(url);
		setPageLoadTimeout(20000);
	}
	
	public void logout() {
		checkPageIsReady();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement("profileMenu")).perform();
		click(getWebElement("logoutButton"));
		click(getWebElement("okButtonOnLogoutPopup"));
		checkPageIsReady();
	}
	
	
	
	public void signUpCommon(String type, String id) {
		uitestbase = new UITestBase();
		String firstname = "automationFirst";
		String lastname = "automationlast";
		getWebElement("firstName").sendKeys(firstname);
		getWebElement("lastName").sendKeys(lastname);
		getWebElement("email").sendKeys(id);
		getWebElement("mobileNumber").sendKeys("9999999999");
		click(getWebElement("genderMale"));
		selectByOption(getWebElement("usertype"), type);
		selectByOption(getWebElement("selectCountry"), "India");
		selectByOption(getWebElement("selectState"), "Delhi");
		selectByOption(getWebElement("selectCity"), "Delhi");
		getWebElement("postalCode").sendKeys("123456");
		getWebElement("addressLine1").sendKeys("test");
		getWebElement("password").sendKeys(password);
		getWebElement("confirmPassword").sendKeys(password);
		click(getWebElement("termsCheckbox"));
		getWebElement("digitalSign").sendKeys(firstname + " " + lastname);
		click(getWebElement("createAccountButton"));
		checkPageIsReady();
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		uitestbase.updateEmailVerificationStatus(id);
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		click(getWebElement("okButton"));
	}
	
	public String signUpAsBuyer() {
		signUpCommon("Buyer", buyerId);
		checkPageIsReady();
		return buyerId;
	}
	
	public void signUpWithBlockedId(String email) {
		signUpCommon("Buyer", email);
		checkPageIsReady();
	}
	
	public String signUpAsSeller() {
		signUpCommon("Seller", sellerId);
		checkPageIsReady();
		return sellerId;
	}
	
	public KasbackLoginPage login(String emailId) {
		click(getWebElement("login/SignUpButton"));
		getWebElement("enterEmail").sendKeys(emailId);
		getWebElement("enterPassword").sendKeys(password);
		click(getWebElement("loginButton"));
		try {
			if(isWebElementPresent("okButton")==true) {
			String errorText=getWebElementText("loginErrorContent");
			log.info(errorText);
			click(getWebElement("okButton"));
			}
		}catch(NoSuchElementException e) {
			log.info("Login Not Successful");
		}
		return new KasbackLoginPage(driver);
	}
	

}
