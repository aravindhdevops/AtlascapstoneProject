package com.POM;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class OrdersListPage extends BasePage {
	
	@FindBy(id="nav-orders")
	private WebElement orderList;
	
	public void clickOrderList() {
		orderList.click();
	}
	
	@FindBy(xpath="(//a[contains(text(),'Invoice')])[1]")
	private WebElement invoicelink;
	
	public void clickInvoice() {
		invoicelink.click();
	}
	
	@FindBy(xpath="//a[contains(text(),'Invoice  1')]")
	public WebElement invoice;
	
	public void clickInvoice1() {
		invoice.click();
	}
	
	@FindBy(partialLinkText="Printable Order Summary")
	private WebElement printSummary;
	
	public void clickPrintableSummary() {
		printSummary.click();
	}
	
	@FindBy(xpath="//a[contains(text(),'Print this page for your records')]")
	private WebElement Summary;
	
	public void clickDownloadOrderSummary() {
		Summary.click();
	}
	
	@FindBy(xpath="(//div[contains(@class,'background order-info')])[1]//span[contains(@class,'secondary value')]/bdi")
	public WebElement orderNumber;
	
	@FindBy(partialLinkText="View or edit order")
	public WebElement editOrder;
	
	public void clickEditOrder() {
		editOrder.click();
	}
	
	@FindBy(partialLinkText="Cancel items")
	public WebElement cancelOrder;
	
	public void clickCancelOrder() {
		cancelOrder.click();
	}
	
	@FindBy(name="cancel.reason")
	private WebElement cancel;
	
	public void selectReson() {
		Select reason = new Select(cancel);
		reason.selectByIndex(1);
	}
	
	
}
