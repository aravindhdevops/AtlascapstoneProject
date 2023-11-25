package com.POM;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	@FindBy(xpath = "(//a[contains(@data-nav-role,'signin')])[1]")
	private WebElement signIn;
	
	public void signIn() {
		signIn.click();
	}
	
	@FindBy(id="ap_email")
	private WebElement emailInput;
	
	public void clearEmail() {
		emailInput.clear();
	}
	
	public void enterEmail(String s) {
		emailInput.sendKeys(s);
	}
	
	@FindBy(id = "continue")
	private WebElement emailContinue;
	
	public void submitEmail() {
		emailContinue.click();
	}
	
	@FindBy(id="ap_password")
	private WebElement passwordInput;
	
	public void clearPassword() {
		passwordInput.clear();
	}
	
	public void enterPassword(String s) {
		passwordInput.sendKeys(s);
	}
	
	@FindBy(id = "signInSubmit")
	private WebElement signInButton;
	
	public void submit() {
		signInButton.click();
	}
	
	@FindBy(id="nav-link-accountList-nav-line-1")
	public WebElement username;
	
	@FindBy(xpath="//a/span[text()='Sign Out']")
	private WebElement signOut;
	
	public void signOut() {
		signOut.click();
	}
	
	@FindBy(xpath="//div[contains(@class,'a-alert-content')]//span")
	public WebElement errorAlert;
	
	@FindBy(xpath="(//div[contains(@class,'a-alert-content')])[3]")
	public WebElement nullAlert;

}
