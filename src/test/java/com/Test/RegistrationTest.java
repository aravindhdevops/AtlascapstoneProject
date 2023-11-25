package com.Test;

import com.POM.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.Utils.DriverUtils;
import com.Utils.ExcelData;

public class RegistrationTest {
	RegisterPage rp = new RegisterPage();
	String validname = ExcelData.getdata("src/input.xlsx", "register", 1, 1);
	String validMobileNumber = ExcelData.getdata("src/input.xlsx", "register", 2, 1);
	String validEmail = ExcelData.getdata("src/input.xlsx", "register", 3, 1);
	String validPassword = ExcelData.getdata("src/input.xlsx", "register", 4, 1);
	String invalidEmail = ExcelData.getdata("src/input.xlsx", "register", 3, 2);
	String invalidPassword = ExcelData.getdata("src/input.xlsx", "register", 4, 2);
	String invalidMobileNumber = ExcelData.getdata("src/input.xlsx", "register", 2, 2);
	String type = "prod";
	
	@BeforeTest
	public void registerPageNavigation() {
		DriverUtils.hitbaseUrl();
		Actions act = new Actions(DriverUtils.getDriver());
		act.moveToElement(rp.username).build().perform();
		rp.register();
	}
	
	@Test(priority=1)
	public void registerHappyFlow() {
		if(type != "prod") {
			System.out.println(type);
			rp.enterCustomerName(validname);
			rp.enterPhoneNumber(validMobileNumber);
			rp.enterEmail(validEmail);
			rp.enterPassword(validPassword);
			rp.verify();
		}
	}
	
	@Test(priority=2)
	public void invalidEmail() {
		rp.enterCustomerName(validname);
		rp.enterPhoneNumber(validMobileNumber);
		rp.enterEmail(invalidEmail);
		rp.enterPassword(validPassword);
		rp.verify();
		Assert.assertEquals("Enter a valid email address",rp.invalidEmailAlert.getText().trim());
	}

	@Test(priority=3)
	public void nullPassword() {
		rp.enterCustomerName(validname);
		rp.enterPhoneNumber(validMobileNumber);
		rp.clearEmail();
		rp.clearPassword();
		rp.verify();
		Assert.assertEquals("Enter your password",rp.missingPasswordAlert.getText().trim());
	}
	
	@Test(priority=4)
	public void passwordLengthTest() {
		rp.enterCustomerName(validname);
		rp.enterPhoneNumber("95517");
		rp.clearEmail();
		rp.enterPassword(invalidPassword);
		rp.verify();
		Assert.assertEquals("Passwords must be at least 6 characters.",rp.invalidPasswordAlert.getText().trim());
	}
	
	@Test(priority=5)
	public void nullMobileNumber() {
		rp.enterCustomerName(validname);
		rp.clearPhoneNumber();
		rp.enterPassword(validPassword);
		rp.verify();
		Assert.assertEquals("Enter your mobile number",rp.missingPhoneNumberAlert.getText().trim());
	}
	
	@Test(priority=6)
	public void invalidMobileNumber() {
		rp.enterCustomerName(validname);
		rp.enterPhoneNumber(invalidMobileNumber);
		rp.enterPassword(validPassword);
		rp.verify();
		Assert.assertEquals("The mobile number you entered does not seem to be valid",rp.invalidPhoneNumberAlert.getText().trim());
	}
	
	@Test(priority=7)
	public void nullCustomerName() {
		rp.clearCustomerName();
		rp.enterPhoneNumber(validMobileNumber);
		rp.enterPassword(validPassword);
		rp.verify();
		Assert.assertEquals("Enter your name",rp.nullCustomerNameAlert.getText().trim());
	}
	
	@Test(priority=8)
	public void existingEmail() {
		rp.enterCustomerName(validname);
		rp.enterPhoneNumber(validMobileNumber);
		rp.enterEmail(validEmail);
		rp.enterPassword(validPassword);
		rp.verify();
		Assert.assertTrue(rp.existingEmailError.getText().trim().contains("has already been used"));
	}

}
