package com.POM;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.Utils.DriverUtils;


public class SearchPage extends BasePage {
	
	public List<WebElement> getProductsList() {
		List<WebElement> productList = DriverUtils.getDriver().findElements(By.xpath("//h2/a"));
		return productList;
		
	}

}
