package com.POM;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchBox;
	
	@FindBy(id="nav-search-submit-button")
	private WebElement searchIcon;
	
	public void enterSearchKeyword(String s) {
		searchBox.sendKeys(s);
	}
	
	public void clickSearch() {
		searchIcon.click();
	}
	
}
