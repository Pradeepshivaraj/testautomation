package org.test.automation.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.CFITRException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends BrowserManager {

	By menuLink = By.linkText("MENU1");
	By signOutLink = By.linkText("Sign Out");
	By signout = By.tagName("h1");
	By header = By.xpath("//*[text()='Recovery and Remediation']");

	// public static void main(String[] args) throws InterruptedException {

	@Test
	public void testing() throws CFITRException {

		log.info("Step1:: Verifying home page header");
		Assert.assertTrue(Helper.elementDisplayed(header));
		log.info("Step2:: Clicking on menu dropdown");
		Helper.click(menuLink);
		log.info("Step3:: Clicking on signout link");
		Helper.click(signOutLink);
		log.info("Step4:: Verifying signout page");
		Assert.assertTrue(Helper.elementDisplayed(signout));

	}

}
