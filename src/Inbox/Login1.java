package Inbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login1 {
	public static void main(String[] args) {
		String key="webdriver.gecko.driver";
		String value="./Softwares/geckodriver.exe";
		
		System.setProperty(key, value);
		WebDriver driver=new FirefoxDriver();
		driver.get("https://www.google.com");
		
		String title=driver.getTitle();
		System.out.println(title);
		
		String url=driver.getCurrentUrl();
		System.out.println(url);
		
		/*String src=driver.getPageSource();
		System.out.println(src);*/
		
	}

}
