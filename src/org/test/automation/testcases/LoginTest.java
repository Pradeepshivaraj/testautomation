package org.test.automation.testcases;

import org.test.automation.exception.CFITRException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

	LoginPage login = new LoginPage();

	@Test
	public void testing() throws CFITRException {

		Assert.assertTrue(login.isHeaderDisplayed());
		login.clickMenuLink();
		login.clickSignOutLink();
		Assert.assertTrue(login.verifySignOutPage());
	}

}
