package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Loc_tagname {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "./softwares/geckodriver.exe");
		FirefoxDriver driver=new FirefoxDriver();
		driver.get("file:///C:/Users/pshivaraju/Desktop/links.html");
		
		WebElement ele=driver.findElement(By.tagName("a"));
		ele.click();
	}

}
