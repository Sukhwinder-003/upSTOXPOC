package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularButtonText;
import com.paulhammant.ngwebdriver.ByAngularModel;
import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Helper;
import utils.Locators;

public class UpstoxPage {

	// NOTE:- The comment code stands for Work in Progress

	/*
	 * WebDriver driver; NgWebDriver ngWebDriver; JavascriptExecutor jsDriver;
	 */

	/*
	 * ByAngularModel secondElement = ByAngular.model("second"); ByAngularModel
	 * operator = ByAngular.model("operator");
	 * 
	 */

	static Properties prop = new Properties();

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

	By orderTextfield = By.xpath(
			"//input[@class='inp-no-lot bc-border ng-pristine ng-valid ng-isolate-scope ng-valid-maxlength ng-touched']");
	By orderPrice = By.xpath("//div[@class='input-div']//input[@ng-model='fieldData.price']");
	By orderQuanatity = By.xpath("//div[@class='input-div']//input[@ng-model='fieldData.disclosedQty']");
	By buyBTTN = By.xpath("//button[@class='button hover_button ng-binding green-background']");
	By buyBTTNCONFIRMORDER = By.xpath("//div[@class='ng-binding ng-scope'][contains(text(),'BUY')]");
	By positionTab = By.xpath("//button[contains(text(),'Delivery')]");
	By poistionList = By.xpath("//div[@class='dd-selectList']//div");
	By poistionBlock = By.xpath("//div[@class='dd-selectList']");

	public UpstoxPage() throws IOException {

		String currentDirectory = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(currentDirectory + "/configs/Configuartion.properties");
		prop.load(fis);
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
		Helper.waitForElementToBeClickable(graph);

		//Helper.waitTillElePresent(Helper.getDriver(), UpstoxPage.shareListBlock);
		Helper.waitForElementToBeClickable(graph);

		Helper.selectDataFromList(UpstoxPage.listShare, prop.getProperty("shareName"), Helper.getDriver(),
				UpstoxPage.shareListBlock);

		// Helper.one();//IN PROGRESS
		Helper.waitForElementToBeClickable(Locators.graph);
		Actions actions = new Actions(Helper.getDriver());
		WebElement btnElement = Helper.getDriver().findElement(graph);
		actions.contextClick(btnElement).perform();
		WebElement elementOpen = Helper.getDriver().findElement(detailBttn);
		elementOpen.click();
		Helper.clickElementByXPath(Locators.buyBttn);
		Helper.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		Helper.sendKeysbyXpath(orderTextfield, prop.getProperty("OrderQuantity"));
		Helper.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		Helper.sendKeysbyXpath(orderQuanatity, prop.getProperty("OrderDISCLOSEDQuantity"));
		Helper.clickElementByXPath(buyBTTN);
		Helper.clickElementByXPath(buyBTTNCONFIRMORDER);
		Helper.clickElementByXPath(Locators.closeBttnPOPUP);
		Helper.closeBrowser();

	}

}
