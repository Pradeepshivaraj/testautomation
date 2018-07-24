package org.test.automation.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.automation.exception.CFITRException;
import org.test.automation.utils.DateUtils;
import org.test.automation.utils.PropertyReader;
import org.test.automation.utils.ReportGenerator;
import org.test.automation.utils.TimeUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BrowserManager {

	protected static WebDriver driver = null;
	String URL = "https://SuperUser:siteminder@cogtax-dev.deloitte.com/login2";
	boolean flag = false;
	public static Logger log = Logger.getLogger(BrowserManager.class);
	protected static String CURRENTDIR = System.getProperty("user.dir");
	protected static Properties props;
	protected static final String DATE_FORMAT_NOW = "dd-MM-yyyy-hh-mm-ss";
	protected static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	private static String MODULENAME = "";
	protected static String baseURL = "";
	private static ArrayList<String> startTimeList = new ArrayList<String>();
	private static ArrayList<String> endTimeList = new ArrayList<String>();
	private static ArrayList<String> exceptionList = new ArrayList<String>();
	private static ArrayList<String> modulesList = new ArrayList<String>();
	private static ArrayList<String> TCList = new ArrayList<String>();
	private static ArrayList<Integer> passedList = new ArrayList<Integer>();
	private static ArrayList<Integer> failedList = new ArrayList<Integer>();
	private static ArrayList<Integer> skippedList = new ArrayList<Integer>();
	private static ArrayList<Integer> totalList = new ArrayList<Integer>();
	private static ArrayList<String> totalTimeList = new ArrayList<String>();
	private static ArrayList<String> snapShotList = new ArrayList<String>();
	private static ArrayList<String> tcNameList = new ArrayList<>();
	private static ArrayList<String> exeStatusList = new ArrayList<>();
	
	private int passedCount1;
	private int failedCount1;
	private int skippedCount1;
	private int totalCount1;

	private int totalpassedCount;
	private int totalfailedCount;
	private int totalskippedCount;
	private int grandTotalCount;
	
	static String chartName = "";
	
	private static String startTime = "";
	private static String endTime = "";
	protected static String actual = "";
	protected static String expected = "";
	
	
	protected static final String TCPASSED = "PASSED";
	protected static final String TCFAILED = "FAILED";
	protected static final String TCSKIPPED = "SKIPPED";

	@BeforeMethod
	public void setup() throws Exception {

		File logsFolder = new File(CURRENTDIR + "/logs");
		File reportsDir = new File(CURRENTDIR + "/Reports");

		if (!logsFolder.exists()) {
			logsFolder.mkdirs();
		}

		if (!reportsDir.exists()) {
			reportsDir.mkdirs();
		}

		PropertyConfigurator.configure("log4j.properties");
		
		startTime = DateUtils.DateTime();
		log.info("Execution Started at " + startTime);

		driver = startBrowser("FIREFOX");
		launchURL(URL);
	}

	private void launchURL(String url) {
		driver.get(url);

	}

	private WebDriver startBrowser(String browser) throws Exception {
		WebDriver driver = null;
		switch (browser) {
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "./softwares/geckodriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
			capabilities.setCapability("acceptInsecureCerts", true);
			driver = new FirefoxDriver(capabilities);
			break;
		default:
			throw new Exception("Invalid browserName");

		}
		return driver;

	}

	public ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			try {
				return (Boolean) ((JavascriptExecutor) driver)
						.executeScript("return window.jQuery != undefined || jQuery.active == 0");
			} catch (Exception e) {
				return true;
			}
		}
	};

	public ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {

			return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete");
		}
	};
	public ExpectedCondition<Boolean> xhrLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return ((JavascriptExecutor) driver).executeScript("return XMLHttpRequest.DONE").toString().equals("4");
		}
	};

	HttpURLConnection con = null;

	public boolean browserResponse() {
		try {
			HttpURLConnection.setFollowRedirects(true);

			con = (HttpURLConnection) new java.net.URL(driver.getCurrentUrl()).openConnection();
			con.setRequestMethod("HEAD");

			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	public void waitForPageLoaded() {
		try {
			// 0.5 second delay beyond which we could say slow performance
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			flag = wait.until(jQueryLoad) && wait.until(jsLoad) && wait.until(xhrLoad);
			browserResponse();

		} catch (Exception e) {
			// System.out.println("wait for page to load exception");
			browserResponse();
		}
	}

	@AfterMethod
	public void tearDown(ITestResult iTestResult, ITestContext context) throws UnknownHostException, Exception {

		String s[] = iTestResult.getName().split(" ");

		System.out.println(s[0]);

		if (iTestResult.getStatus() == ITestResult.SUCCESS) {
			log.info("####Test Case PASSED:" + s[0]);
			exeStatusList.add("PASSED");
		} else if (iTestResult.getStatus() == ITestResult.FAILURE) {
			String txt = takeFullSnapShot();
			log.info("####Test Case FAILED:" + s[0]);
			TCList.add(s[0]);
			exeStatusList.add("FAILED");
			exceptionList.add(getModuleName() + "::" + getTestCaseNameFromScript(s[0]) + "::"
					+ CFITRException.failureMessage + "::" + txt);
		}

		else {
			log.info("####Test Case SKIPPED:" + s[0]);
			exeStatusList.add("SKIPPED");

		}

		driver.close();

	}

	@AfterClass
	public void prepareTestReport(ITestContext context) throws Exception {

		for (int i = 0; i < context.getAllTestMethods().length; i++) {
			if (context.getAllTestMethods()[i].getCurrentInvocationCount() > 1) {
				if (context.getFailedTests().getResults(context.getAllTestMethods()[i]).size() > 1
						|| context.getPassedTests().getResults(context.getAllTestMethods()[i]).size() == 1) {

					context.getFailedTests().removeResult(context.getAllTestMethods()[i]);
				}
			}
		}

		String className = this.getClass().getName();
		String myText = className.replace(".", "%");
		String s[] = myText.split("%");
		MODULENAME = s[s.length - 1];
		System.out.println("ModuleName: " + MODULENAME);

		for (int i = 0; i < context.getAllTestMethods().length; i++) {
			String s2 = context.getAllTestMethods()[i].getMethodName();
			tcNameList.add(s2);
			System.out.println("MODULENAME: " + MODULENAME + "::TCName: " + s2);

		}

		System.out.println("LIST: " + exeStatusList.size());
		
		String tcDetails = ReportGenerator.TCwriteToHTML(MODULENAME, tcNameList, exeStatusList);


		modulesList.add(s[s.length - 1]);

		System.out.println("Number of Modules Executed So Far: " + modulesList.size());

		System.out.println("Name of the Modules Executed So Far: ");
		for (int i = 0; i < modulesList.size(); i++) {
			System.out.println(modulesList.get(i));
		}

		passedCount1 = passedCount1 + context.getPassedTests().size();
		failedCount1 = failedCount1 + context.getFailedTests().size();
		skippedCount1 = skippedCount1 + context.getSkippedTests().size();
		totalCount1 = passedCount1 + failedCount1 + skippedCount1;

		System.out.println("PASSED: " + passedCount1);
		System.out.println("FAILED: " + failedCount1);
		System.out.println("SKIPPED: " + skippedCount1);
		System.out.println("TOTAL: " + totalCount1);

		passedList.add(passedCount1);
		failedList.add(failedCount1);
		skippedList.add(skippedCount1);
		totalList.add(totalCount1);

		for (int i = 0; i < passedList.size(); i++) {
			totalpassedCount = totalpassedCount + passedList.get(i);
		}
		for (int i = 0; i < failedList.size(); i++) {
			totalfailedCount = totalfailedCount + failedList.get(i);
		}
		for (int i = 0; i < skippedList.size(); i++) {
			totalskippedCount = totalskippedCount + skippedList.get(i);
		}
		grandTotalCount = totalpassedCount + totalfailedCount + totalskippedCount;

//		JFreeChart pieChart = setPieChartData(totalpassedCount, totalfailedCount, totalskippedCount);

//		chartName = "TestReport_" + getRandomValue() + ".png";
//		createChart(pieChart, System.getProperty("user.dir") + "\\Reports\\" + chartName, 500, 300, 100);

		if (baseURL == "") {
			baseURL = PropertyReader.getProperty("baseURL");
		}

		endTime = DateUtils.DateTime();
		endTimeList.add(endTime);

		log.info("Execution Completed at: " + endTime);

		String timeElapsed = TimeUtils.calculateTimeDifference(startTime, endTime);
		totalTimeList.add(timeElapsed);

		log.info("Time Taken for Execution: " + timeElapsed);

		String totalTimeTaken = TimeUtils.calculateTotalTimeTaken(startTimeList, endTimeList);
		log.info("Total Time Taken for Execution: " + totalTimeTaken);

		String reportPath = "";
		reportPath = "<img src=\"" + System.getProperty("user.dir") + "\\Reports\\" + chartName + "\">";

		ReportGenerator.writeToHTML(baseURL, modulesList, TCList, totalList, passedList, failedList, skippedList, totalpassedCount,
				totalfailedCount, totalskippedCount, grandTotalCount, totalTimeList, reportPath, exceptionList,
				snapShotList, totalTimeTaken, TimeUtils.getMessageBasedonTime(),tcDetails);

		String destpath = "";
		destpath = System.getProperty("user.dir") + "\\TestAutomationReports\\" + DateUtils.DateTime();

		FileUtils.copyDirectory(new File(System.getProperty("user.dir") + "\\Reports"), new File(destpath));
		log.info("Folder: " + System.getProperty("user.dir") + "\\Reports Copied to " + destpath);
		FileUtils.copyFileToDirectory(new File(System.getProperty("user.dir") + "\\TestReport.html"),
				new File(destpath));

		log.info("File: " + CURRENTDIR + "\\TestReport.html Copied to " + destpath);
		try {
			FileUtils.copyFileToDirectory(new File(System.getProperty("user.dir") + "\\testng-failed.xml"),
					new File(destpath));

			log.info("File: " + System.getProperty("user.dir") + "\\testng-failed.xml Copied to " + destpath);
		} catch (FileNotFoundException fnf) {
			System.out.println(System.getProperty("user.dir") + "\\testng-failed.xml does not exist");
		}

	}

	public String takeFullSnapShot() throws UnknownHostException, Exception {

		String fileName = "";
		File scrFile = null;
		try {
			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		} catch (Exception snf) {

			snf.printStackTrace();
			// log.info("ERROR" + snf.getMessage());
		}
		// log.info("scrFile: " + scrFile.getAbsolutePath());
		// log.info("ScreenShot Captured and the fileName is : "
		// + getUniqueFileName());
		fileName = "Report_Snapshot_" + getRandomValue() + ".png";
		String path = "";
		path = System.getProperty("user.dir") + "\\Snapshots\\" + fileName;
		if (new File(path).exists()) {
			new File(path).mkdirs();
		}
		// log.info("fileName: " + fileName);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\Snapshots\\" + fileName));
		} catch (IOException e) {

			e.printStackTrace();
			// log.info("ERROR" + e.getMessage());
		}

		path = "<a href=\"" + path + "\">ScreenShot</a>";
		return path;
	}

	public static int getRandomValue() throws CFITRException {
		Random rand = new Random();
		int r = 1;
		r = rand.nextInt(9999999);
		System.out.println(r);
		return r;
	}
	
	public String getModuleName() throws CFITRException {
		String className = this.getClass().getName();
		String myText = className.replace(".", "%");
		String s[] = myText.split("%");
		MODULENAME = s[s.length - 1];
		System.out.println("ModuleName: " + MODULENAME);
		return MODULENAME;
	}
	
	public String getTestCaseNameFromScript(String scriptName) {
		StringBuffer sb = new StringBuffer();
		String parts[] = scriptName.split("_");
		for (int i = 0; i < parts.length; i++) {
			if (i > 1) {
				sb.append(parts[i] + " ");
			}
		}
		return sb.toString().toUpperCase();
	}
	
	
	



}
