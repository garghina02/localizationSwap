package com.adp.localization.test;


import java.util.Map;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adp.main.config.Config;
import com.adp.main.config.StartTest;
import com.adp.main.config.SeleniumUtils;


import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;


public class TestLocalization {
	final static Logger logger = Logger.getLogger(TestLocalization.class.getName());
	WebDriver driver=Config.getDriver("Chrome");	
	
	@BeforeMethod
	public void setUp() throws Exception 
	{
		ATUReports.setWebDriver(driver);
		ATUReports.indexPageDescription = "Test Reports";
	}
			
	@Test(testName="Test Subscribe Functionality",description = "Verify Subscribe Functionality ",dataProvider="testSubscribe",dataProviderClass=TestDataProvider.class)
	public void testSubscribe(String srno,String runmode,String language,String name,String email) {
		System.out.println("Test Case one with Thread Id:- "
				+ Thread.currentThread().getId());
		
		Map<String, String> locators=Config.setLanguage(language);
		logger.info("Test Subscribe execution started");
		logger.info("Language set for testing is :-"+language);
		ATUReports.add("Language set for testing is",language, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		driver.get(StartTest.testconfig.get("Test_URL"));
		SeleniumUtils.waitForPageLoad(driver);
		logger.info("Page loaded successfully");
		driver.findElement(By.xpath(locators.get("Select_Language"))).click();
		SeleniumUtils.waitForPageLoad(driver);
		logger.info("Selected language page loaded successfully");
		ATUReports.add("language page loaded successfully",language, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		WebElement homeMenu=driver.findElement(By.xpath(locators.get("Home_Menu")));
		Actions actions = new Actions(driver);
		actions.moveToElement(homeMenu).build().perform();
		
		driver.get(driver.findElement(By.xpath(locators.get("Contact_us_menu"))).getAttribute("href"));
	//	WebUtility.explicitWaitForElement(driver, By.xpath(StartTest.locators.get("Contact_us_menu")), 10,"Contact Menu");
		//driver.findElement(By.xpath(StartTest.locators.get("Contact_us_menu"))).click();
		logger.info("Clicked on Contact us menu");
		SeleniumUtils.waitForPageLoad(driver);
		ATUReports.add("Contact us menu page loaded successfully",language, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		driver.findElement(By.xpath(locators.get("Subscribe_Name"))).sendKeys(name);
		logger.info("Subscribers Name Entered:-"+"test Subscriber");
		ATUReports.add("Subscribers Name Entered",name, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		driver.findElement(By.xpath(locators.get("Subscribe_email"))).sendKeys(email);
		ATUReports.add("Subscribers Email Entered",email, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logger.info("Subscribers Email Entered:-"+email);
		logger.info("******Test Subscribe Test Case passed*****");
		ATUReports.add("Test Case passed", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		//driver.findElement(By.xpath(StartTest.locators.get("Subscribe_submit_button"))).click();
	}
	@Test(testName="Test Blog",description = "Verify Blogs Functionality ",dataProvider="testBlogs",dataProviderClass=TestDataProvider.class)
	public void testBlogs(String srno,String runmode,String language)
	{
		try{
		Map<String, String> locators=Config.setLanguage(language);
		logger.info("Language set for testing is :-"+language);
		ATUReports.add("Language set for testing is",language, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		driver.get(StartTest.testconfig.get("Test_URL"));
		SeleniumUtils.waitForPageLoad(driver);
		logger.info("Page loaded successfully");
		driver.findElement(By.xpath(locators.get("Select_Language"))).click();
		SeleniumUtils.waitForPageLoad(driver);
		logger.info("Selected language page loaded successfully");
		ATUReports.add("language page loaded successfully",language, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		driver.findElement(By.xpath(locators.get("Blog_Link"))).click();
		logger.info("Clicked on Blog link");
		SeleniumUtils.waitForPageLoad(driver);
		driver.findElement(By.xpath(locators.get("Announcement_img"))).click();
		ATUReports.add("Clicked on Announcement image",LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logger.info("Clicked on Announcement image");
		SeleniumUtils.waitForPageLoad(driver);
		driver.findElement(By.xpath(locators.get("WPML_link"))).click();
		logger.info("Clicked on WPML link");
		ATUReports.add("Clicked on WPML link",LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		SeleniumUtils.waitForPageLoad(driver);
		
		if(srno.equalsIgnoreCase("3"))
		{
			Assert.fail("Test Fail");
			ATUReports.add("Test Case Fail",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		logger.info("******Test Blogs Test Case passed*****");
		}catch (Exception e)
		{
			Assert.fail("Not able to click on element "+e.getMessage());
		}
	}
	


}
