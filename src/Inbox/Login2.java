package Inbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Login2 {
	public static void main(String[] args) {
		String key="webdriver.chrome.driver";
		String value="./Softwares/chromedriver.exe";
		
		System.setProperty(key, value);
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com");
		
		String title=driver.getTitle();
		System.out.println(title);
		
		String url=driver.getCurrentUrl();
		System.out.println(url);
		
		/*String src=driver.getPageSource();
		System.out.println(src);*/
		
	}

}
