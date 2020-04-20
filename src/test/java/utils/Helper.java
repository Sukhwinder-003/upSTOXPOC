package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.UpstoxPage;

import static testngConstants.Constants.WAIT_EXPLICIT_SEC;

public class Helper {

	static ConfigFileReader config = new ConfigFileReader();
	public static String myText;
	public static WebDriver driver;
	static Properties prop = new Properties();

	/*
	 * // This method is to initialize the driver public static void
	 * driverInitialization() {
	 * 
	 * }
	 */

	public static void driverInitializationChrome() {
		System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");

		Map<String, Object> prefs = new HashMap<String, Object>();

		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
//		WebDriver driver = new ChromeDriver(options);

		driver = new ChromeDriver(options);

	}

	public static void driverInitializationFirefox() {
		System.setProperty("webdriver.gecko.driver", config.getDriverPath());
		driver = new FirefoxDriver();

	}

	public static WebDriver getDriver() {
		if (driver == null) {
			driverInitializationChrome();
		}
		return driver;
	}

	public static void initializeDriver() throws IOException {
		String currentDirectory = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(currentDirectory + "/configs/Configuartion.properties");
		prop.load(fis);

		getDriver();

		Helper.getDriver().manage().window().maximize();
		Helper.getDriver().get("https://pro.upstox.com/");
		// Helper.getDriver().navigate().refresh();

	}

	public static void clickElementByXPath(By Xpath) {

		waitForElementToBeClickable(Xpath);
		Locators.getWebElement(driver, Xpath).click();

	}

	public static boolean isElementDisplayedByXpath(WebElement webElement) {

		return isElementDisplayed(webElement, WAIT_EXPLICIT_SEC);

	}

	public static boolean isElementPresent(By xpath) {
		try {
			waitForElementToBeClickable(xpath);
			driver.findElement(xpath);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isElementDisplayed(WebElement webElement) {

		return isElementDisplayed(webElement, WAIT_EXPLICIT_SEC);

	}

	public static void waitForElementToBeClickable(By Xpath) {

		WebDriverWait wait = new WebDriverWait(Helper.getDriver(), WAIT_EXPLICIT_SEC);
		wait.until(ExpectedConditions.elementToBeClickable(Xpath));

	}

	public static void waitForElementToBeClickable(WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_EXPLICIT_SEC);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));

	}

	public static boolean isElementDisplayed(WebElement webElement, int timeOut) {

		WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
		wait.until(ExpectedConditions.visibilityOf(webElement));
		return webElement.isDisplayed();

	}

	public static void waitForVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_EXPLICIT_SEC);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void waitForVisibilityAll(WebElement list) {
		WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_EXPLICIT_SEC);
		wait.until(ExpectedConditions.visibilityOfAllElements(list));

	}

	// This method is used to hold the list of elements
	/*
	 * public static void getDataFromList(By List, String Text, WebDriver driver) {
	 * 
	 * waitForElementToBeClickable(Locators.targetOption);
	 * driver.findElement(Locators.targetOption).click();
	 * 
	 * List<WebElement> list = driver.findElements(List);
	 * 
	 * for (int i = 0; i < list.size(); i++)
	 * 
	 * { list.get(0).click();
	 * 
	 * break; } }
	 */

	public static void verifyDataFromList(By List, String Text, WebDriver driver) throws InterruptedException {

		List<WebElement> list = driver.findElements(List);
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getText().contentEquals(Text)) {
				/*
				 * System.out.println(list.get(i).getText());
				 */ Assert.assertEquals(Text, list.get(i).getText());

				break;

			}

		}
	}

	public static boolean selectDataFromList(By List, String Text, WebDriver driver, By dropdwn) {
		boolean flag= false;
		
		System.out.println("inside selectDta frm lis");
		
		System.out.println(List);
		System.out.println(dropdwn);
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		List<WebElement> list = Helper.getDriver().findElements(List);

		for (int i = 0; i < list.size(); i++)
			
		{
			
			System.out.println("inside for loop");
			if (list.get(i).getAttribute("title").contains(Text)) {

				System.out.println(list.get(i));
				
				list.get(i).click();
				flag=true;
				break;

			}

		}
		return flag;

	}

	public static boolean getDataInList(By xpath) {

		System.out.println("insde getDataInList");
		WebElement element = driver.findElement(xpath);
		System.out.println(element);

/*		if (element != null) {*/
			element.click();
			
			System.out.println("insode ");
			/* return true; */

//		}

		return false;

	}

	public static boolean selectDataFromList1(By List, String Text, WebDriver driver, By dropdwn) {

		System.out.println("inside list");
		driver.findElement(dropdwn).click();
		List<WebElement> list = driver.findElements(List);

		for (int i = 0; i < list.size(); i++)

		{
			System.out.println(i);

			if (list.get(i).getText().contains(Text)) {

				System.out.println(list.get(i).getText() + "@@@@@@@@@@@@");

				list.get(i).click();

				break;

			}

		}
		return true;

	}

	public static String getText(By xpath) {

		String text = driver.findElement(xpath).getText();

		return text;

	}

	public static void assert_element_present(By xpath) {

		if (driver.findElement(xpath) != null) {
			System.out.println("Element is Present");
		} else {
			System.out.println("Element is Absent");
		}

	}

	public static void assert_element_textPresent(String ExpectedText, By xpath) throws InterruptedException {
		{

			Helper.waitForElementToBeClickable(xpath);

			Assert.assertEquals(ExpectedText, driver.findElement(xpath).getText());
			System.out.println(ExpectedText);
			System.out.println(driver.findElement(xpath).getText());
		}

	}

	public static void sendKeysbyXpath(By Xpath, CharSequence... keysToSend) {
		WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_EXPLICIT_SEC);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Xpath));
		WebElement wl = Locators.getWebElement(driver, Xpath);
		wl.clear();
		wl.sendKeys(keysToSend);
	}

	public static void closeBrowser() throws InterruptedException {

		if (driver == null) {
			return;
		}
		driver.quit();
		driver = null;

	}

	public static void waitTillElePresent(WebDriver driver, By Locator) {

		/*
		 * System.out.println("insiderwaitTillElePresent ");
		 */
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(Locator));

	}

	public static void one() {
		boolean value = false;

		if (value == false) {

			boolean valueGET = Helper.selectDataFromList1(UpstoxPage.listShare, prop.getProperty("shareName"),
					Helper.getDriver(), UpstoxPage.shareListBlock);
		}

		if (value == false) {

			Helper.clickElementByXPath(Locators.addShareBTTN);
			Helper.sendKeysbyXpath(Locators.addShareTextField, "ABCC");

		}

	}

	public static boolean isClickable(WebElement webe) {
		try {
			WebDriverWait wait = new WebDriverWait(Helper.getDriver(), 10);
			wait.until(ExpectedConditions.elementToBeClickable(webe));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
