package com.adp.main.config;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.adp.localization.test.TestLocalization;

/**
 * @author Swapnil Sonawane
 * @Description This class contains all the methods and utilities to handle web
 *              driver operations
 *
 */
public class SeleniumUtils {
	public final static Logger logger = Logger.getLogger(TestLocalization.class.getName());

	/**
	 * @param driver
	 * @Description This method wait for page to load
	 */
	public static void waitForPageLoad(WebDriver driver) {
		logger.info("Waiting for page to load");
		ExpectedCondition<Boolean> pageLoadCondition = new

		ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				Boolean pageReady = ((JavascriptExecutor) driver).executeScript("return document.readyState")
						.equals("complete");
				if (pageReady) {
					try {
						Thread.sleep(2000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				return pageReady;
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(pageLoadCondition);
		logger.info("Page loaded successfully");
	}

	/**
	 * @param driver
	 * @param by
	 * @param timeout
	 * @param element
	 * @Description This method waits for given element for given time
	 */
	public static void explicitWaitForElement(WebDriver driver, By by, int timeout, String element) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			logger.info("In explicitWaitForElement Function waiting for element " + timeout + " Seconds");
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			logger.info("Not able to find element " + element);
			logger.info(e.getMessage());
			Assert.fail("Not able to find element " + element);

		}
	}

}
