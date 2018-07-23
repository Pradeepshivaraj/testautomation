package Gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "./Softwares/geckodriver.exe");
		
		//Step-1
		WebDriver driver = new FirefoxDriver();
		
		//Step-2
		driver.get("https://www.google.com/gmail/about/");
		
		//Step-3
		//driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.xpath("//a[.='Sign In']")).click();
		
		//Step-4
		driver.findElement(By.id("identifierId")).sendKeys("pradeepkle@gmail.com");
		driver.findElement(By.xpath("//span[.='Next']")).click();
		Thread.sleep(3000);
		
		//Step-5
		driver.findElement(By.name("password")).sendKeys("pradeepraj@87");
		driver.findElement(By.xpath("//span[.='Next']")).click();
		Thread.sleep(3000);
		
		//Step-6
		
		String title=driver.getTitle();
				System.out.println(title);
				
				if(title.contains("Inbox"))
				{
					System.out.println("home page is displayed");
				} else {
					System.out.println("home page is not displayed");
				}
	
		
	}

}
