package org.test.automation.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.CFITRException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Export extends BrowserManager {

	By client = By.xpath("(//*[text()='Automation1'])[2]");
	By proj = By.xpath("(//a[.='Auto-proj1'])[2]");
	By projname = By.xpath("//span[.='Auto-proj1']");
	By transactiontab = By.xpath("//*[text()=' Transactions']");
	By headerdropdown = By.className("cfitr-header-drop-down");
	By export = By.linkText("Prepare Export");
	By download = By.xpath("//button[.='Prepare ']");
	By save = By.xpath("//span[.='Save Export']");
	By successmsg = By.xpath("//*[contains(text(),'Export Complete')]");

	// public static void main(String[] args) throws InterruptedException,
	// AWTException {
	@Test
	public void testing() throws CFITRException, AWTException {

		log.info("Step1:: Click Client name");
		Helper.click(client);
		log.info("Step2:: select Engagement name");
		Helper.click(proj);
		log.info("Step3:: Click Engagement name");
		Helper.click(projname);
		log.info("Step4:: Click Transaction tab");
		Helper.click(transactiontab);
		log.info("Step5:: Click on Header dropdown");
		Helper.click(headerdropdown);
		log.info("Step6:: Click on prepare export");
		Helper.click(export);
		log.info("Step7:: Click on prepare button");
		Helper.click(download);
		log.info("Step8:: Click on save button");
		Helper.click(save);

		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ALT);
		r.keyPress(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_ALT);
		r.keyRelease(KeyEvent.VK_S);

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		log.info("Step9:: Verify download success message");
		Assert.assertTrue(Helper.IsElementDisplayed(successmsg));

	}

}
