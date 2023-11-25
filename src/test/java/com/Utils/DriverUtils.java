package com.Utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.*;

public class DriverUtils {
	
	static WebDriver driver;
	private static final String baseUrl = "https://www.amazon.in/";
	
	public static void createDriver() {
		
		//setting up Chrome driver capabilities
		String downloadFilePath = System.getProperty("user.dir");
		System.out.println(downloadFilePath+"/src/downloads/");
		//String downloadFilePath = "src/downloads/";
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadFilePath+"\\src\\downloads\\");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("download.prompt_for_download", false);
		
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver(options);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
	}
	
	public static WebDriver getDriver() {
		if(driver == null) {
			createDriver();
		}
		return driver;
	}
	
	public static void closeDriver() {
		driver.close();
	}
	
	public static void quitDriver() {
		driver.quit();
	}
	
	public static void hitbaseUrl() {
		driver.get(baseUrl);
	}
	

}
