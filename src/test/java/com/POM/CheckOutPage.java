package com.POM;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.Utils.DriverUtils;


public class CheckOutPage extends BasePage {
	
	@FindBy(id="add-new-address-popover-link")
	private WebElement addNewAddress;
	
	public void addNewAddress() {
		addNewAddress.click();
	}
	
	@FindBy(name="address-ui-widgets-countryCode")
	private WebElement country;
	
	public void selectCountry(String ctry) {
		Select sc = new Select(country);
		sc.selectByVisibleText(ctry);
	}
	
	
	@FindBy(id="address-ui-widgets-enterAddressFullName")
	private WebElement fullname;
	
	public void enterFullname(String name) {
		fullname.clear();
		fullname.sendKeys(name);
	}
	
	@FindBy(id="address-ui-widgets-enterAddressPhoneNumber")
	private WebElement mobileNumber;
	
	public void enterMobileNumber(String number) {
		mobileNumber.clear();
		mobileNumber.sendKeys(number);
	}
	
	@FindBy(id="address-ui-widgets-enterAddressPostalCode")
	private WebElement postalCode;
	
	public void enterPincode(String code) {
		postalCode.clear();
		postalCode.sendKeys(code);
	}
	
	@FindBy(id="address-ui-widgets-enterAddressLine1")
	private WebElement houseNo;
	
	public void enterDoorNo(String number) {
		houseNo.clear();
		houseNo.sendKeys(number);
	}
	
	@FindBy(id="address-ui-widgets-enterAddressLine2")
	private WebElement area;
	
	public void enterArea(String ar) {
		area.clear();
		area.sendKeys(ar);
	}
	
	@FindBy(id="address-ui-widgets-landmark")
	private WebElement landMark;
	
	public void enterLandMark(String lm) {
		landMark.clear();
		landMark.sendKeys(lm);
	}
	
	@FindBy(id="address-ui-widgets-enterAddressCity")
	private WebElement city;
	
	public void enterCity(String ct) {
		city.clear();
		city.sendKeys(ct);
	}
	
	@FindBy(name="address-ui-widgets-enterAddressStateOrRegion")
	private WebElement state;
	
	public void selectState(String st) {
		Select sc = new Select(state);
		sc.selectByVisibleText(st);
	}
		
	@FindBy(id="address-ui-widgets-form-submit-button")
	private WebElement submit;
	
	public void clickSubmit() {
		submit.click();
	}
	
	@FindBy(id="shipToThisAddressButton")
	private WebElement useThisAddressButton;
	
	public void clickUseThisAddress() {
		useThisAddressButton.click();
	}
	
	@FindBy(xpath="//input[contains(@value,'SelectableAddCreditCard')]")
	private WebElement creditOrDebitCard;
	
	public void clickCreditOrDebitCard() {
		creditOrDebitCard.click();
	}
	
	@FindBy(linkText="Enter card details")
	private WebElement enterCardDetails;
	
	public void clickEnterCardDetails() {
		enterCardDetails.click();
	}
	
	
	@FindBy(name="addCreditCardNumber")
	private WebElement Cardnumber;
	
	public void enterCreditCardNumber(String number) {
		Cardnumber.clear();
		Cardnumber.sendKeys(number);
	}
	
	@FindBy(name="ppw-accountHolderName")
	private WebElement name;
	
	public void enterCardHolderName(String n) {
		name.clear();
		name.sendKeys(n);
	}
	
	@FindBy(name="ppw-expirationDate_month")
	private WebElement expMonth;
	
	public void selectExpMonth(String st) {
		Select sc = new Select(expMonth);
		sc.selectByVisibleText(st);
	}
	
	@FindBy(name="ppw-expirationDate_year")
	private WebElement expYear;
	
	public void selectExpYear(String st) {
		Select sc = new Select(expYear);
		sc.selectByVisibleText(st);
	}
	
	@FindBy(name="ppw-widgetEvent:AddCreditCardEvent")
	private WebElement CardDetails;
	
	public void clickSubmitCard() {
		CardDetails.click();
	}
	
	@FindBy(name="ppw-widgetEvent:CancelAddCreditCardEvent")
	private WebElement cancelCard;
	
	public void clickCancelCard() {
		cancelCard.click();
	}
	
	@FindBy(xpath="//input[contains(@value,'Method=COD')]")
	private WebElement COD;
	
	public void clickCOD() {
		Actions act = new Actions(DriverUtils.getDriver());
		act.moveToElement(COD).build().perform();
		COD.click();
	}
	
	@FindBy(xpath="(//input[contains(@name,'SetPaymentPlanSelectContinueEvent')])[1]")
	private WebElement usePayment;
	
	public void clickConfirmPayment() {
		usePayment.click();
	}
	
	@FindBy(id="submitOrderButtonId")
	private WebElement placeOrder;
	
	public void clickPlaceOrder() {
		placeOrder.click();
	}
	public boolean isOrdePlaced() {
		if(placeOrder.isDisplayed()) {
			return false;
		}
		return true;
	}
	
}
