package Testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo1 {
	@Test
	public void test1()
	{
		Reporter.log("1", true);
	}

}
