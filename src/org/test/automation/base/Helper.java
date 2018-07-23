package org.test.automation.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.automation.exception.CFITRException;

public class Helper extends BrowserManager {
	
	public static WebElement getElement(By locator) throws CFITRException
	{
		WebElement element = null;
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			element = driver.findElement(locator);
		} catch (TimeoutException toe) {
			log.info("Element is not available: "+locator);
			throw new CFITRException("Element is not available: "+locator);
			
		}
		
		return element;
	}
	
	public static void click(By locator) throws CFITRException
	{
		getElement(locator).click();
	}
	
	public static void enterText(WebDriver driver,By locator,String value) throws CFITRException
	{
		getElement(locator).sendKeys(value);
	}
	
	public static boolean elementDisplayed(By locator) throws CFITRException
	{
		return getElement(locator).isDisplayed();
	}
	
	public static void setClipboardData(String inputString) {
		StringSelection stringSelection = new StringSelection(inputString);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	public static void selectFileFromExplorer() throws Exception {

		Robot r;
		try {
			r = new Robot();
		} catch (AWTException e) {
			throw new Exception("MyException: " + e.getMessage());

		}
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(1000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);

	}


}
