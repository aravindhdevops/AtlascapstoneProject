package com.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.POM.*;
import com.Utils.DriverUtils;
import com.Utils.ExcelData;
import com.Utils.ScreenShot;

public class LoginTest {
	LoginPage lp = new LoginPage();
	ScreenShot ss = new ScreenShot();
	String validemail = ExcelData.getdata("src/input.xlsx", "login", 1, 1);
	String validPassword = ExcelData.getdata("src/input.xlsx", "login", 1, 2);
	String invalidEmail = ExcelData.getdata("src/input.xlsx", "login", 2, 1);
	String invalidPassword = ExcelData.getdata("src/input.xlsx", "login", 2, 2);
	
	@Test(priority=1)
	public void loginHappyFlow() throws InterruptedException {
		lp.signIn();
		lp.enterEmail(validemail);
		lp.submitEmail();
		lp.enterPassword(validPassword);
		lp.submit();
		WebDriverWait wait = new WebDriverWait(DriverUtils.getDriver(),30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-logo")));
		String user = lp.username.getText();
		Assert.assertEquals("Hello, Aravindhraj",user.trim());
		Actions act = new Actions(DriverUtils.getDriver());
		act.moveToElement(lp.username).build().perform();
		lp.signOut();
	}

	
	@Test(priority=2)
	public void invalidEmail() throws IOException {
		lp.enterEmail(invalidEmail);
		lp.submitEmail();
		Assert.assertEquals("We cannot find an account with that email address",lp.errorAlert.getText().trim());
		ss.takeScreenshot("invalidEmail");
	}

	@Test(priority=3)
	public void nullEmail() {
		lp.clearEmail();
		lp.submitEmail();
		Assert.assertEquals("Enter your email or mobile phone number",lp.nullAlert.getText().trim());
	}

	@Test(priority=4)
	public void invalidPassword() {
		lp.enterEmail(validemail);
		lp.submitEmail();
		lp.enterPassword(invalidPassword);
		lp.submit();
		Assert.assertEquals("Your password is incorrect",lp.errorAlert.getText().trim());
		
	}
	@Test(priority=5)
	public void nullPassword() {		
		lp.clearPassword();
		lp.submit();
		Assert.assertEquals("Enter your password",lp.nullAlert.getText().trim());
	}
	
}
