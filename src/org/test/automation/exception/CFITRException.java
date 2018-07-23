package org.test.automation.exception;

public class CFITRException extends Exception {

	public static String failureMessage = "";

	public CFITRException(String reason) {
		super(reason);
		failureMessage = reason;
	}

}
