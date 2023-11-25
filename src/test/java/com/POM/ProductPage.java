package com.POM;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.Utils.DriverUtils;

public class ProductPage extends BasePage {
	
	@FindBy(name="submit.add-to-cart")
	private WebElement addTocart;

	
	@FindBy(name="proceedToRetailCheckout")
	private WebElement checkout;
	
	@FindBy(id="attachDisplayAddBaseAlert")
	public WebElement addedTocartAlert1;
	
	@FindBy(xpath="//div[contains(@id,'CONF_MSG_SUCCESS')]/Span")
	public WebElement addedTocartAlert2;
	
	@FindBy(id="availability")
	private WebElement inStock;
	
	@FindBy(xpath="//a[@id='nav-cart']")
	private WebElement cart;
	
	@FindBy(id="attach-close_sideSheet-link")
	private WebElement closeIcon;
	
	@FindBy(id="attachSiNoCoverage")
	private WebElement skipCoverage;
	
	public boolean isCoverageDisplayed() {
		if(skipCoverage.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public void clickSkipCoverage() {
		skipCoverage.click();
	}
	
	public void clickSideCloseIcon() {
		if(closeIcon.isDisplayed()) {
			closeIcon.click();
		}	
	}
	
	public boolean isProductAvailable() {
		if(inStock.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public void viewCart() {
		cart.click();
	}
	public void clickAddToCart() throws InterruptedException{
		Thread.sleep(8000);
		Actions act = new Actions(DriverUtils.getDriver());
		act.moveToElement(addTocart).build().perform();
		Thread.sleep(1000);
		addTocart.click();
	}
	
	public void clickCheckOut() {
		checkout.click();
	}

}
