package org.test.automation.testcases;

import org.openqa.selenium.By;
import org.test.automation.base.BrowserManager;
import org.test.automation.base.Helper;
import org.test.automation.exception.CFITRException;

public class LoginPage extends BrowserManager {

	By menuLink = By.linkText("MENU");
	By signOutLink = By.linkText("Sign Out");
	By signout = By.tagName("h1");
	By header = By.xpath("//*[text()='Recovery and Remediation']");

	// public static void main(String[] args) throws InterruptedException {

	public boolean isHeaderDisplayed() throws CFITRException {
		log.info("Step1:: Verifying home page header");
		return Helper.IsElementDisplayed(header);
	}

	public void clickMenuLink() throws CFITRException {
		log.info("Step2:: Clicking on menu dropdown");
		Helper.click(menuLink);
	}

	public void clickSignOutLink() throws CFITRException {
		log.info("Step3:: Clicking on signout link");
		Helper.click(signOutLink);
	}

	public boolean verifySignOutPage() throws CFITRException {

		log.info("Step4:: Verifying signout page");
		return Helper.IsElementDisplayed(signout);
	}

}
