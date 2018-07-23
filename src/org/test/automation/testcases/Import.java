package org.test.automation.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Import extends BrowserManager {
	
	
	By verify = By.xpath("(//*[text()='Automation1'])[2]");
	By client = By.xpath("(//*[text()='Automation1'])[2]");
	By proj = By.xpath("(//a[.='Auto-proj1'])[2]");
	By data = By.xpath("//*[text()=' Data ']");
	By link = By.xpath("(//span[.='Import'])[1]");
	By select = By.xpath("//span[contains(.,' Select File(s) ')]/input");
	By imp = By.xpath("//div[.=' Import ']");
	By approve = By.xpath("//div[contains(text(),'Approve and Save Engagement Data')]");
	By save = By.xpath("//div[.='Save Data']");
	By msg = By.xpath("//*[contains(text(),'Import Approval Complete')]");
	By selectFilesLink = By.xpath("//span[contains(.,' Select File(s) ')]");
	
	
	//public static void main(String[] args) throws Exception {
	@Test
	public void testing() throws Exception{ 
		
		log.info("Step1:: Click Client name");
		Helper.click(client);
		log.info("Step2:: Click Engagement name");
		Helper.click(proj);
		log.info("Step3:: Click Data tab");
		Helper.click(data);
		log.info("Step4:: Click Import link");
		Helper.click(link);
		
		log.info("Step5:: Browse and select the file");
		String fileName = "Chevrondata2.csv";
		
		Helper.setClipboardData(System.getProperty("user.dir")+"\\Files\\"+fileName);
		
		Helper.click(selectFilesLink);
		
		Helper.selectFileFromExplorer();
		
		log.info("Step6:: Click on Import button");
		Helper.click(imp);
		log.info("Step7:: Click on Approve button");
		Helper.click(approve);
		log.info("Step8:: Click on Save button");
		Helper.click(save);
		
		log.info("Step9:: Verify import successfull message");
		Assert.assertTrue(Helper.IsElementDisplayed(msg));
		
		

	}

}
