package com.adp.main.config;

import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.testng.TestNG;
import com.adp.localization.test.TestLocalization;
import com.google.common.collect.Lists;

/**
 * @author Swapnil Sonawane
 * @Description This main class to trigger the test execution
 */
public class StartTest {
	public static Map<String, String> locators;
	public static Map<String, String> testconfig;
	public final static Logger logger = Logger.getLogger(TestLocalization.class.getName());
	public static GetData data = new GetData();

	/**
	 * @param args
	 * @Description Main method start of framework
	 */
	public static void main(String[] args) {
		Config.setLogger();
		logger.info("Starting Script Execution.");
		logger.info("Getting Test cases set for execution");
		Config.configReport();
		String[][] mainExcel = data
				.getDataWithYesRunMode(data.getDataFromExcel(Constants.setTestCaseFile, "TestCases"));
		testconfig = GetData.getProperty(Constants.test_config_properties);
		Config.updateXML(mainExcel);
		TestNG testng = new TestNG();
		ArrayList<String> suites = Lists.newArrayList();
		if (StartTest.testconfig.get("parallel_Mode").equalsIgnoreCase("On")) {
			logger.info("Parallel execution mode is set");
			suites.add(Constants.parellelTestngXML);
		} else if (StartTest.testconfig.get("parallel_Mode").equalsIgnoreCase("Off")) {
			logger.info("Sequential execution mode is set");
			suites.add(Constants.testngXML);
		}
		testng.setTestSuites(suites);
		testng.run();
		Config.tearDown();

	}

}
