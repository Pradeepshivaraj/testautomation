package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Loc_cssselector {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "./softwares/geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.get("file:///C:/Users/pshivaraju/Desktop/links.html");
		
		//by using "ID"
		//driver.findElement(By.cssSelector("a[id='a1']")).click();
		//driver.findElement(By.cssSelector("a#a1")).click();
		
		//by using "class"
		//driver.findElement(By.cssSelector("a[class='c1']")).click();
		driver.findElement(By.cssSelector("a.c1")).click();
	}

}
