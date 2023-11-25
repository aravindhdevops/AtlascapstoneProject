package com.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.POM.*;
import com.Utils.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BuyItems {
	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();
	SearchPage sp = new SearchPage();
	ProductPage pp = new ProductPage();
	CheckOutPage cp = new CheckOutPage();
	OrdersListPage olp = new OrdersListPage();
	ScreenShot ss = new ScreenShot();
	String validemail = ExcelData.getdata("src/input.xlsx", "login", 1, 1);
	String validPassword = ExcelData.getdata("src/input.xlsx", "login", 1, 2);
	String type = "prod";
	WebDriverWait wait = new WebDriverWait(DriverUtils.getDriver(), 15);

	@BeforeTest
	public void setUp() {
		DriverUtils.hitbaseUrl();
		lp.signIn();
		lp.enterEmail(validemail);
		lp.submitEmail();
		lp.enterPassword(validPassword);
		lp.submit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-logo")));
		String user = lp.username.getText();
		Assert.assertEquals("Hello, Aravindhraj", user.trim());
	}
	
	@Test(priority = 1)
	public void searchItem() {
		hp.enterSearchKeyword(CSVReader.getData("Search Keyword", 0));
		hp.clickSearch();
	}

	@Test(priority = 2)
	public void openProductPage() {
		List<WebElement> productList = new ArrayList<>();
		productList = sp.getProductsList();
		productList.get(1).click();
		ArrayList<String> tabs = new ArrayList<String>(DriverUtils.getDriver().getWindowHandles());
		DriverUtils.getDriver().switchTo().window(tabs.get(1));
	}

	@Test(priority = 3)
	public void checkProductAvailability() {
		Assert.assertEquals(pp.isProductAvailable(), true);
	}

	@Test(priority = 4, dependsOnMethods = "checkProductAvailability")
	public void addToCart() throws InterruptedException, IOException {
		pp.clickAddToCart();
		ss.takeScreenshot("addToCart");
		try {
			if(pp.isCoverageDisplayed()) {
				pp.clickSkipCoverage();
			}
		}
		catch (Exception e) {
		}
		Thread.sleep(2000);
		try {
			Assert.assertEquals(pp.addedTocartAlert1.getText().trim(), "Added to Cart");
			pp.clickSideCloseIcon();
			
		} catch (Exception e) {
			Assert.assertEquals(pp.addedTocartAlert2.getText().trim(), "Added to Cart");
		}

	}

	@Test(priority = 5, dependsOnMethods = "addToCart")
	public void checkOut() throws InterruptedException {
		Thread.sleep(1500);
		pp.viewCart();
		pp.clickCheckOut();
	}

	@Test(priority = 6, dependsOnMethods = "checkOut")
	public void addShippingAddress() throws InterruptedException {
		cp.addNewAddress();
		cp.selectCountry(CSVReader.getData("Address", 0));
		cp.enterFullname(CSVReader.getData("Address", 1));
		cp.enterMobileNumber(CSVReader.getData("Address", 2));
		cp.enterPincode(CSVReader.getData("Address", 3));
		cp.enterDoorNo(CSVReader.getData("Address", 4));
		Thread.sleep(1000);
		cp.enterArea(CSVReader.getData("Address", 5));
		cp.enterLandMark(CSVReader.getData("Address", 6));
		Thread.sleep(1000);
		cp.enterCity(CSVReader.getData("Address", 7));
		cp.selectState(CSVReader.getData("Address", 8));
		cp.clickSubmit();
	}

	@Test(priority = 7, dependsOnMethods = "addShippingAddress")
	public void addPaymentMethod() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addressChangeLinkId")));
		cp.clickCreditOrDebitCard();
		cp.clickEnterCardDetails();
		Thread.sleep(2000);
		DriverUtils.getDriver().switchTo().frame("ApxSecureIframe");
		Thread.sleep(2000);
		cp.enterCreditCardNumber(CSVReader.getData("Card details", 0));
		cp.enterCardHolderName(CSVReader.getData("Card details", 1));
		cp.selectExpMonth(CSVReader.getData("Card details", 2));
		cp.selectExpYear(CSVReader.getData("Card details", 3));
		cp.clickCancelCard();
		DriverUtils.getDriver().switchTo().defaultContent();
		Thread.sleep(4000);
		cp.clickCOD();
		cp.clickConfirmPayment();
		Thread.sleep(2000);
	}
	
	
	@Test(priority = 8, dependsOnMethods = "addPaymentMethod")
	public void placeOrder() throws InterruptedException {
		if(type !="prod") {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitOrderButtonId")));
			for(int i=0; i<5; i++) {
				cp.clickPlaceOrder();
				if(cp.isOrdePlaced()) {
					break;
				}
			}
		}	
		DriverUtils.hitbaseUrl();
	}
	
	@Test(priority=9)
	public void viewOrder() throws InterruptedException {
		olp.clickOrderList();
	}
	
	@Test(priority = 10, dependsOnMethods = "viewOrder")
	public void dowloadOrderInvoice() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Invoice')])[1]")));
		olp.clickInvoice();
		Thread.sleep(2000);
		String orderNo = olp.orderNumber.getText();
		if(olp.invoice.isDisplayed()) {
			olp.invoice.click();
		}
		else {
			olp.clickPrintableSummary();
			String htmlContent = DriverUtils.getDriver().getPageSource();
			@SuppressWarnings("resource")
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/downloads/"+orderNo+"_invoice.html"));
			writer.write(htmlContent);
		}
			
	}
	
	@AfterTest
	public void close() {
		DriverUtils.quitDriver();
	}

}
