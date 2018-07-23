package org.test.automation.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Create_Proj extends BrowserManager{
	String name = "Auto-proj14";
	
	
	By Addenglink = By.xpath("//div[.='Add an Engagement ']");
	By enterclientname = By.name("clientName");
	By enterEinno = By.name("clientEin");
	By clickengbutton = By.xpath("(//span[.='Engagement'])[1]");
	By enterprojname = By.name("searchProjectName");
	By enterwbscode = By.name("searchProjectCode");
	By enterdesc = By.name("projectDescription");
	By clicksavebutton = By.xpath("//button[.=' Save Engagement ']");
	By verify = By.xpath("(//*[text()='Automation1'])[2]");
	By engcreated = By.xpath("(//*[text()='"+name+"'])[2]");
	By header = By.xpath("//*[text()='Recovery and Remediation']");
	String description = "project description";
	
	//public static void main(String[] args) throws InterruptedException {
	
	@Test
	public void testing() throws Exception{ 
		
	
		
		String clientName = "Automation1";
		String Ein = "12-8778977";
		String code = "Auto698";
		
		Thread.sleep(5000);
		waitForPageLoaded();
		log.info("Step1:: Verifying home page header");
		Assert.assertTrue(Helper.elementDisplayed(header));
		log.info("Step2:: Click on add an engagement link");
		Helper.click(Addenglink);
		log.info("Step3:: Enter clientname");
		Helper.enterText(driver, enterclientname, clientName);
		log.info("Step4:: Enter Ein no");
		Helper.enterText(driver, enterEinno, Ein);
		log.info("Step5:: Click on engagement button");
		Helper.click(clickengbutton);
		log.info("Step6:: Enter project name");
		Helper.enterText(driver, enterprojname, name);
		log.info("Step7:: Enter wbs code");
		Helper.enterText(driver, enterwbscode, code);
		
//		Helper.enterText(driver, desc, "project description");
		log.info("Step8:: enter description");
		for(int i=0;i<description.length();i++)
		{
			driver.findElement(enterdesc).sendKeys(String.valueOf(description.charAt(i)));
			Thread.sleep(500);
		}
		
		log.info("Step9:: Click on save engagement button");
		Helper.click(clicksavebutton);
		log.info("Step10:: click on client name");
		Helper.click(verify);
		log.info("Step11:: verify eng created");
		Assert.assertTrue(Helper.elementDisplayed(engcreated));
		
		
	}

}
