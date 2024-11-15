package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test (groups={"Regression", "Master"})
	public void verify_account_registration() {
		
		try {
		logger.info("*****Starting TC001_AccountRegistrationTest******");

		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		hp.clickRegister();
		logger.info("Clicked on Register link");

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		regpage.SetFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString() + "@gmail.com");
		regpage.setTelephone(randomNumber());

		String pass = randomAlphaNumeric();
		regpage.setPassword(pass);
		regpage.setConfirmPassword(pass);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validation expected message..");

		String confmsg = regpage.getConfirmationMsg();

		Assert.assertEquals(confmsg, "Your Account Has Been Created!");

	}
	catch(Exception e) {
		logger.error("Test Failed");
		logger.debug("Debug logs...");
		Assert.fail();
	}
		logger.info("Test TC001_Completed");
	}

}
