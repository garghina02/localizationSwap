package com.adp.main.config;

/**
 * @author Sonawane Swapnil
 * @Description This class contains all the constant parameters like file paths
 */
public class Constants {
	public static String workingDir = System.getProperty("user.dir");
	public static final String test_config_properties = workingDir + "\\config\\Test_Configuration.properties";
	public static final String english_properties_path = workingDir + "\\Locators\\English\\English.properties";
	public static final String japanese_properties_path = workingDir + "\\Locators\\Japanese\\Japanese.properties";
	public static final String chinese_properties_path = workingDir + "\\Locators\\Chinese\\Chinese.properties";
	public static final String spanish_properties_path = workingDir + "\\Locators\\Spanish\\Spanish.properties";
	public static final String arabic_properties_path = workingDir + "\\Locators\\Arabic\\Arabic.properties";
	public static final String testDataFile = workingDir + "\\config\\TestData.xlsx";
	public static final String setTestCaseFile = workingDir + "\\config\\SetTestCases.xlsx";
	public static final String testngXML = workingDir + "\\config\\testNG.xml";
	public static final String parellelTestngXML = workingDir + "\\config\\parellelTest.xml";
	public static final int runModeIndex = 1;
	public static final String atuReportProperties="C:\\ProjectWorkspace\\Localization\\config\\Report\\atu.properties";
}
