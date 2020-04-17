package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularButtonText;
import com.paulhammant.ngwebdriver.ByAngularModel;
import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Helper;
import utils.Locators;

public class UpstoxPage {

	Properties prop;
	static String ShareName;

	By addBTTN = By.xpath(
			"//div[@class='panel webwidget condensed ng-scope ng-isolate-scope']//div[@class='pointer ng-scope']");
	By textField = By.xpath("//input[@placeholder='Enter at least 3 characters of symbol or name']");
	By addBTTNConfirmShare = By.xpath("//div[@class='button add']");

	By positionTABSHARELIST = By.xpath("//div[@class='ui-grid-cell-contents  stockName ng-scope']//span");

	By SigninBttn = By.xpath("//*[contains(text(),'Sign in')]");
	By signInID = By.xpath("//input[@id='login-id']");
	By signInPSW = By.xpath("//input[@id='login-password']");
	By sumitBttnLogin = By.xpath("//div[@id='login-submit-button']");
	By dOB = By.xpath("//input[@id='2fa-password']");

	/*
	 * ByAngularButtonText = ByAngular.buttonText("Sign in"); ByAngularModel
	 * PASSWORD = ByAngular.model("pwdModel"); ByAngularModel orderTextfield =
	 * ByAngular.model("fieldData.quantity"); ByAngularModel orderPrice =
	 * ByAngular.model("fieldData.price"); ByAngularModel orderQuanatity=
	 * ByAngular.model("fieldData.disclosedQty");
	 */

	public static By SUBMITBTTN = By.xpath("//div[@id='login-submit-button']");
	public static By graph = By.xpath("//div[@class='stx-subholder']");
	public static By listShare = By.xpath("//div[@class='item']//div[@class='name ng-binding']");
	public static By shareListBlock = By.xpath("//ul[@class='ui-sortable']");
	public static By detailBttn = By.xpath("//a[contains(text(),'DETAILS')]");

	// 16th April

	// public static By listToPrint =
	// By.xpath("//section[@class='watchlist_details']//div[@class='item']");

	/*
	 * public static By listToPrint = By
	 * .xpath("//section[@class='watchlist_details']//div[@class='item' and @title='"
	 * + ShareName + "']");
	 */
	By orderTextfieldNifty = By.xpath(
			"//input[@class='inp-lot bc-border ng-pristine ng-valid ng-isolate-scope ng-valid-maxlength ng-touched']");
	By orderPrice = By.xpath("//div[@class='input-div']//input[@ng-model='fieldData.price']");
	By orderQuanatity = By.xpath("//div[@class='input-div']//input[@ng-model='fieldData.disclosedQty']");
	By buyBTTN = By.xpath("//button[@class='button hover_button ng-binding green-background']");
	By buyBTTNCONFIRMORDER = By.xpath("//div[@class='ng-binding ng-scope'][contains(text(),'BUY')]");
	/*
	 * By positionTab = By.xpath("//button[contains(text(),'Delivery')]");
	 */

	By poistionList = By.xpath("//div[@class='dd-selectList']//div");
	By poistionBlock = By.xpath("//div[@class='dd-selectList']");
	By positionTab = By.xpath("//div[@class='tab positions_tab']");
	By squareOFF = By.xpath("//button[@class='square_off']");
	By checkBOX = By.xpath(
			"//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-0011']//div[@class='ui-grid-cell-contents ng-scope']//*[local-name()='svg']");
	By squareOFFYES = By.xpath(" //button[@class='overlay-sgnbdy-two']");

	public UpstoxPage() throws IOException {

		prop = new Properties();

		String currentDirectory = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(currentDirectory + "/configs/Configuartion.properties");
		prop.load(fis);

		ShareName = prop.getProperty("SHARENAME");
		Helper.initializeDriver();
	}

	public void process() throws InterruptedException {

		Helper.waitForElementToBeClickable(graph);

		Helper.clickElementByXPath(SigninBttn);
		Helper.sendKeysbyXpath(signInID, prop.getProperty("USERID"));
		Helper.sendKeysbyXpath(signInPSW, prop.getProperty("PASSWORD"));
		Helper.clickElementByXPath(sumitBttnLogin);
		Helper.sendKeysbyXpath(dOB, prop.getProperty("DOB"));
		Helper.waitForElementToBeClickable(graph);
		Helper.getDriver().navigate().refresh();

		/*
		 * By sharenameFromProp = By
		 * .xpath("//section[@class='watchlist_details']//div[@class='item' and @title='"
		 * + ShareName + "']");
		 * 
		 * 
		 * System.out.println((Helper.getDataInList(sharenameFromProp)+"shshgushghsdgh")
		 * );
		 * 
		 * if(!Helper.getDataInList(sharenameFromProp)) {
		 * 
		 * Helper.getDriver().navigate().refresh();
		 * System.out.println("under false condiction");
		 * 
		 * Helper.clickElementByXPath(addBTTN);
		 * 
		 * Helper.sendKeysbyXpath(textField, ShareName);
		 * Helper.clickElementByXPath(addBTTNConfirmShare);
		 * Helper.clickElementByXPath(addBTTN);
		 * 
		 * Helper.getDriver().navigate().refresh();
		 * Helper.getDataInList(sharenameFromProp);
		 * 
		 * }
		 */
		// Helper.waitTillElePresent(Helper.getDriver(), UpstoxPage.shareListBlock);

		// Helper.waitForElementToBeClickable(graph);

		By listToPrint = By
				.xpath("//section[@class='watchlist_details']//div[@class='item' and @title='" + ShareName + "']");
		
		
		Helper.selectDataFromList(listToPrint, prop.getProperty("SHARENAME"), Helper.getDriver(),
				UpstoxPage.shareListBlock);
		
		if (!Helper.selectDataFromList(listToPrint, prop.getProperty("SHARENAME"), Helper.getDriver(),
				UpstoxPage.shareListBlock)) {

			Helper.clickElementByXPath(addBTTN);
			Helper.sendKeysbyXpath(textField, ShareName);
			Helper.clickElementByXPath(addBTTNConfirmShare);
			Helper.clickElementByXPath(addBTTN);
			Helper.getDriver().navigate().refresh();

		}

		Helper.waitForElementToBeClickable(Locators.graph);
		Helper.selectDataFromList(listToPrint, prop.getProperty("SHARENAME"), Helper.getDriver(),
				UpstoxPage.shareListBlock);

		
		
		  Helper.waitForElementToBeClickable(Locators.graph);
		  Actions actions = new
		  Actions(Helper.getDriver()); 
		  WebElement btnElement = Helper.getDriver().findElement(graph);
		  actions.contextClick(btnElement).perform(); 
		  WebElement elementOpen = Helper.getDriver().findElement(detailBttn); 
		  elementOpen.click();
		  Helper.clickElementByXPath(Locators.buyBttn);
		  Helper.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		  Helper.sendKeysbyXpath(orderTextfieldNifty,
		  prop.getProperty("OrderQuantity"));
		  Helper.clickElementByXPath(buyBTTN);
		  Helper.clickElementByXPath(buyBTTNCONFIRMORDER);
		  Helper.clickElementByXPath(Locators.closeBttnPOPUP);
		  
		 

		// Helper.clickElementByXPath(positionTab);

		// Helper.selectDataFromList(positionTABSHARELIST,
		// prop.getProperty("SHARENAME"), Helper.getDriver(), positionTab);

		/*
		 * Helper.clickElementByXPath(checkBOX); Helper.clickElementByXPath(squareOFF);
		 * Helper.clickElementByXPath(squareOFFYES); WebElement checkboxElement =
		 * Locators.getWebElement(Helper.getDriver(), checkBOX); boolean bst =
		 * Helper.isClickable(checkboxElement); System.out.println("Checkbox Value is" +
		 * bst + " i.e. Non Clickable"); Helper.closeBrowser();
		 */

	}

}
