package org.test.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	
	private static final String DATE_FORMAT_NOW = "dd-MM-yyyy-hh-mm-ss";
	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	
	
	public static String DateTime() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		return sdf.format(date);// returns date in dd-MM-yyyy HH:mm:ss
								// format eg:12-02-2012 12:34:42
	}

}
