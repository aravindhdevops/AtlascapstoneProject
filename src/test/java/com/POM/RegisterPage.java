package com.POM;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
	
	@FindBy(linkText="Start here.")
	private WebElement start;
	
	@FindBy(id="nav-link-accountList-nav-line-1")
	public WebElement username;
	
	@FindBy(id="ap_customer_name")
	private WebElement name;
	
	@FindBy(xpath="//input[@placeholder='Mobile number']")
	private WebElement phoneNumber;
	
	@FindBy(id="ap_email")
	private WebElement email;
	
	@FindBy(id="ap_password")
	private WebElement password;
	
	@FindBy(id="continue")
	private WebElement verify;
	
	@FindBy(id="auth-email-invalid-email-alert")
	public WebElement invalidEmailAlert;
	
	@FindBy(xpath="//div[@id='auth-password-invalid-password-alert']")
	public WebElement invalidPasswordAlert;
	
	@FindBy(xpath="//div[@id='auth-password-missing-alert']")
	public WebElement missingPasswordAlert;
	
	@FindBy(xpath="//div[@id='auth-phoneNumber-missing-alert']")
	public WebElement missingPhoneNumberAlert;
	
	@FindBy(xpath="//div[@id='auth-phoneNumber-invalid-phone-alert']")
	public WebElement invalidPhoneNumberAlert;
	
	@FindBy(xpath="//div[@id='auth-customerName-missing-alert']")
	public WebElement nullCustomerNameAlert;
	
	@FindBy(id="auth-error-message-box")
	public WebElement existingEmailError;
	
	
	public void register() {
		start.click();
	}
	
	public void clearCustomerName() {
		name.clear();
	}
	public void enterCustomerName(String s) {
		name.clear();
		name.sendKeys(s);
	}
	
	public void clearPhoneNumber() {
		phoneNumber.clear();
	}
	public void enterPhoneNumber(String s) {
		phoneNumber.clear();
		phoneNumber.sendKeys(s);
	}
	
	
	public void clearEmail() {
		email.clear();
	}
	public void enterEmail(String s) {
		email.clear();
		email.sendKeys(s);
	}
	
	
	public void clearPassword() {
		password.clear();
	}
	public void enterPassword(String s) {
		password.clear();
		password.sendKeys(s);
	}
	
	public void verify() {
		verify.click();
	}	

}
