package com.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public class ScreenShot {
	
	public void takeScreenshot(String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) DriverUtils.getDriver();
		File src=ts.getScreenshotAs(OutputType.FILE);
		File destinationfile = new File("src/screenshots/"+name+".jpg");
		FileUtils.copyFile(src, destinationfile);
	}

}
