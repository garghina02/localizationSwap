package com.adp.main.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.adp.localization.test.TestLocalization;

/**
 * @author Swapnil Sonawane
 * @Description This class initially do all framework related configurations
 *
 */

public class Config {
	public final static Logger logger = Logger.getLogger(TestLocalization.class.getName());
	static WebDriver driver;

	/**
	 * @return driver
	 * @param driverName
	 * @Description This method returns driver
	 */
	public static WebDriver getDriver(String browser) {
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\ProjectWorkspace\\Localization\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			logger.info("Driver initialization successful");
		} catch (Exception e) {
			logger.info("Something went wrong while driver initialization " + e.getMessage());
			Assert.fail("Something went wrong while driver initialization " + e.getMessage());
		}
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * @Description THis method set logger for the project
	 */
	public static void setLogger() {
		Properties properties = new Properties();
		properties.setProperty("log4j.rootLogger", "info,stdout,MyFile");
		properties.setProperty("log4j.rootCategory", "TRACE");

		properties.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
		properties.setProperty("log4j.appender.Target", "System.out");
		properties.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.stdout.layout.ConversionPattern",
				"%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");

		properties.setProperty("log4j.appender.MyFile", "org.apache.log4j.RollingFileAppender");
		properties.setProperty("log4j.appender.MyFile.File", "logs/execution.log");
		properties.setProperty("log4j.appender.MyFile.MaxFileSize", "100KB");
		properties.setProperty("log4j.appender.MyFile.MaxBackupIndex", "1");
		properties.setProperty("log4j.appender.MyFile.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.MyFile.layout.ConversionPattern",
				"%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");

		PropertyConfigurator.configure(properties);
		logger.info("logger configuration successful.");
	}

	/**
	 * @param language
	 * @return Locators as per the language
	 * @Description This method returns locators as per the given language
	 */

	public static Map<String, String> setLanguage(String language) {
		Map<String, String> locators = null;
		switch (language.toLowerCase()) {
		case "english":
			locators = GetData.getProperty(Constants.english_properties_path);
			logger.info("Locators loaded successfully for " + language + " language");
			break;
		case "japanese":
			locators = GetData.getProperty(Constants.japanese_properties_path);
			logger.info("Locators loaded successfully for " + language + " language");
			break;
		case "chinese":
			locators = GetData.getProperty(Constants.chinese_properties_path);
			logger.info("Locators loaded successfully for " + language + " language");
			break;
		case "spanish":
			locators = GetData.getProperty(Constants.spanish_properties_path);
			logger.info("Locators loaded successfully for " + language + " language");
			break;
		case "arabic":
			locators = GetData.getProperty(Constants.arabic_properties_path);
			logger.info("Locators loaded successfully for " + language + " language");
			break;
		default:
			logger.info("Script not supported for given " + language + " language");
			Assert.fail("Test not supported for specified language ");
		}

		return locators;
	}

	/**
	 * @Description This method clear all the open browsers
	 */

	public static void tearDown() {
		logger.info("Execution completed");

		driver.quit();

		logger.info("Driver closed successfully");
	}

	/**
	 * @param mainExcel
	 * @Description This method update xml file as per execution data
	 */
	public static void updateXML(String[][] mainExcel) {

		if (StartTest.testconfig.get("parallel_Mode").equalsIgnoreCase("On")) {
			try {

				// Get test case name with 'Yes' Options and store in to test
				// execution array
				ArrayList<String> testCaseForExecution = new ArrayList<>();
				for (int i = 0; i < mainExcel.length; i++) {
					testCaseForExecution.add(mainExcel[i][2]);
				}

				// Read Test Ng XML file

				File inputFile = new File(Constants.parellelTestngXML);
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(inputFile);

				// Update Parallel Mode
				NodeList nodeList = doc.getElementsByTagName("methods");

				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					NodeList list1 = element.getChildNodes();
					Node node1 = list1.item(1);
					if (node1.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node1;
						if (testCaseForExecution.contains(eElement.getAttribute("name"))) {
							doc.renameNode(list1.item(1), "", "include");
						} else {
							doc.renameNode(list1.item(1), "", "exclude");
						}
					}
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult consoleResult = new StreamResult(new File(Constants.parellelTestngXML));
				transformer.transform(source, consoleResult);
				logger.info("Execution XML file updated successfully...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// If execution is sequential
		else if (StartTest.testconfig.get("parallel_Mode").equalsIgnoreCase("off")) {
			try {

				// Get test case name with 'Yes' Options and store in to test
				// execution array
				ArrayList<String> testCaseForExecution = new ArrayList<>();
				for (int i = 0; i < mainExcel.length; i++) {
					testCaseForExecution.add(mainExcel[i][2]);
				}

				// Read Test Ng XML file

				File inputFile = new File(Constants.testngXML);
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(inputFile);

				// Update test case for execution Mode
				NodeList nodeList = doc.getElementsByTagName("methods");

				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					NodeList list1 = element.getChildNodes();

					for (int j = 0; j < list1.getLength(); j++) {
						Node node1 = list1.item(j);
						if (node1.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) node1;
							if (testCaseForExecution.contains(eElement.getAttribute("name"))) {
								doc.renameNode(list1.item(j), "", "include");
							} else {
								doc.renameNode(list1.item(j), "", "exclude");
							}
						}
					}
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult consoleResult = new StreamResult(new File(Constants.testngXML));
				transformer.transform(source, consoleResult);
				logger.info("Execution XML file updated successfully...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void configReport()
	{
		logger.info("Setting report properties");
		{
			System.setProperty("atu.reporter.config", Constants.atuReportProperties);
		}
		logger.info("Report Properties set..");
		
	}
}
