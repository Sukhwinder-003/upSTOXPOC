package TestCases;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.UpstoxPage;

public class BuyShare {

	UpstoxPage upPage;

	@BeforeTest
	public void setup() throws IOException {
		upPage = new UpstoxPage();
	}

	@Test(priority = 1)
	public void buyShare() throws InterruptedException {

		upPage.loginWithCredential();
		upPage.addShare();
		upPage.buyShare();
		upPage.squareOff();

		
		

	}

}
