package com.kasback.abstestbase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.kasback.constants.UiConstants;
import com.kasback.constants.UtilConstants;
import com.kasback.utils.PostgresqlUtil;

public abstract class AbstractTest extends AbstractTestBase{

	public static PostgresqlUtil pgutil = null;
	public WebDriver driver;
	public static String browser = null;
	public static String envoir = null;
	public static String sessionId = null;
	public static boolean isrunOnRemote = false;
	public static String testResultsFolder = null;
	
	@Parameters({ "env", "browser" })
	@BeforeSuite(alwaysRun = true)
	public void beforesuite(@Optional("test") String env, @Optional("chrome") String browser) {
		printBanner("TEST SUITE STARTED");
		envoir = env;
		log.info("before env->" + env);
		log.info("before browser->" + browser);
		if (env.equals("") || env.equals(null)) {
			env = System.getProperty("env");
			log.info("before env inside" + env);
		}
		loadEnv(env);		

		pgutil = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"), environment.get("dbpwd"));
		if (pgutil == null) {
			log.info("DB Connection failed first attempt");
			pgutil = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"), environment.get("dbpwd"));
			if (pgutil == null) {
				log.info("DB Connection failed second attempt");
				pgutil = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"),
						environment.get("dbpwd"));
				if (pgutil == null) {
					log.info("DB Connection failed Third attempt Aborting test....");
					Assert.fail("Aborting test due to db connection failed ...");
				}
			}

		}
		AbstractTest.browser = browser;
		//login();
		

	}


private void printBanner(String s) {
	log.info("========================================= " + s + " ================================");

}

public static WebDriver getDriver() {
	String remoteHub = null;
	String proxy = null;
	URL remoteHubUrl = null;
	String targetOS = null;
	DesiredCapabilities cap = null;
	isrunOnRemote = Boolean.valueOf(PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "runOnRemoteMachine"));
	log.info(isrunOnRemote);
	if (isrunOnRemote == true) {
		remoteHub = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "remoteHubIP");
		targetOS = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "targetOS");
		log.info(remoteHub);
		proxy = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "proxy");
		try {
			remoteHubUrl = new URL(remoteHub);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		WebDriverCapabilities webcap = new WebDriverCapabilities(browser, proxy);
		cap = webcap.getDesiredCapabilities(targetOS);
	}
	WebDriver driver = null;
	if (browser == null) {
		browser = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "defaultBrowser");
	}
	log.info("running on browser " + browser);

	log.info(browser);
	if (browser.equalsIgnoreCase("firefox")) {
		if (os.equalsIgnoreCase("linux")) {
			/*if (!isrunOnRemote && !FileIOUtility.isFileExists("./src/test/resources/drivers/geckodriver_linux")) {
				AWSutils.getFileFromS3("data/drivers", "geckodriver_linux", "./src/test/resources/drivers");
			}*/
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver_linux");
		} else if (os.startsWith("Windows")) {
			/*if (!isrunOnRemote && !FileIOUtility.isFileExists("./src/test/resources/drivers/geckodriver.exe")) {
				AWSutils.getFileFromS3("data/drivers", "geckodriver.exe", "./src/test/resources/drivers");
			}*/
			System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
		} else if (os.startsWith("mac")) {
			/*if (!isrunOnRemote && !FileIOUtility.isFileExists("./src/test/resources/drivers/geckodriver_mac")) {
				AWSutils.getFileFromS3("data/drivers", "geckodriver_mac", "./src/test/resources/drivers");
			}*/
			System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver_mac");
		}
		if (isrunOnRemote == true) {
			log.info("starting gecko driver on server");
			driver = new RemoteWebDriver(remoteHubUrl, cap);
		} else {
			log.info("starting gecko driver on local");
			driver = new FirefoxDriver();
		}

	} else if (browser.equalsIgnoreCase("chrome")) {
		if (os.equalsIgnoreCase("linux")) {
			/*if (!isrunOnRemote && !FileIOUtility.isFileExists("./src/test/resources/drivers/chromedriver_linux")) {
				log.info("driver downloading");
				AWSutils.getFileFromS3("data/drivers", "chromedriver_linux", "./src/test/resources/drivers");
			}*/
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_linux");
		} else if (os.startsWith("Windows")) {
			/*if (!isrunOnRemote && !FileIOUtility.isFileExists("./src/test/resources/drivers/chromedriver.exe")) {
				AWSutils.getFileFromS3("data/drivers", "chromedriver.exe", "./src/test/resources/drivers");
			}*/
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		} else if (os.startsWith("Mac")) {
			/*if (!isrunOnRemote && !FileIOUtility.isFileExists("./src/test/resources/drivers/chromedriver_mac")) {
				AWSutils.getFileFromS3("data/drivers", "chromedriver_mac", "./src/test/resources/drivers");
			}*/
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver_mac");
		}
		if (isrunOnRemote == true) {
			log.info("starting chrome driver on server");
			log.info(remoteHubUrl.toString());
			log.info("cap " + cap.getBrowserName());
			driver = new RemoteWebDriver(remoteHubUrl, cap);
		} else {
			log.info("starting chrome driver on local");
			driver = new ChromeDriver();
		}

	} else if (browser.equalsIgnoreCase("ie")) {
		/*if (!isrunOnRemote && !FileIOUtility.isFileExists("./src/test/resources/drivers/IEDriverServer.exe")) {
			AWSutils.getFileFromS3("data/drivers", "IEDriverServer.exe", "./src/test/resources/drivers");
		}*/
		System.setProperty("webdriver.ie.driver", "./src/test/resources/drivers/IEDriverServer.exe");
		if (isrunOnRemote == true) {
			log.info("starting ie driver on server");
			driver = new RemoteWebDriver(remoteHubUrl, cap);
		} else {
			log.info("starting ie driver on local");
			driver = new InternetExplorerDriver();
		}
	} else if (browser.equalsIgnoreCase("safari")) {
		log.info("starting safari driver on local");
		driver = new SafariDriver();
	} else
		driver = new FirefoxDriver();

	driver.manage().timeouts().pageLoadTimeout(UiConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().setScriptTimeout(UiConstants.SCRIPT_LOAD_TIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(UiConstants.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	return driver;

}




	

}
