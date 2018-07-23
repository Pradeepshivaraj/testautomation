package org.test.automation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.test.automation.base.BrowserManager;
import org.test.automation.exception.CFITRException;


public class TimeUtils extends BrowserManager {
	
	
	public static String calculateTimeDifference(String startDate, String endDate) throws CFITRException, ParseException {

		Date d1 = null;
		Date d2 = null;
		String timeDiff = "";

		d1 = sdf.parse(startDate);
		d2 = sdf.parse(endDate);

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		if (diffDays != 0) {
			timeDiff = timeDiff + diffDays + " Day(s), ";
		}
		if (diffHours != 0) {
			timeDiff = timeDiff + diffHours + " Hour(s), ";
		}
		if (diffMinutes != 0) {
			timeDiff = timeDiff + diffMinutes + " Minute(s), ";
		}
		if (diffSeconds != 0) {
			timeDiff = timeDiff + diffSeconds + " Second(s).";
		}

		System.out.println(timeDiff);
		return timeDiff;
	}

	public static String calculateTotalTimeTaken(ArrayList<String> startDateList, ArrayList<String> endDateList)
			throws CFITRException, ParseException {

		Date d1 = null;
		Date d2 = null;
		String timeDiff = "";
		long total = 0;

		System.out.println("startDateList.size(): " + startDateList.size());
		System.out.println("endDateList.size(): " + endDateList.size());
		if (startDateList.size() > 0) {
			for (int i = 0; i < startDateList.size(); i++) {
				d1 = sdf.parse(startDateList.get(i));
				d2 = sdf.parse(endDateList.get(i));
				long diff = d2.getTime() - d1.getTime();
				System.out.println(diff);
				total = total + diff;
			}
		}
		System.out.println("total: " + total);
		// in milliseconds

		// totalTimeTaken = totalTimeTaken + diff;
		long diffSeconds = total / 1000 % 60;
		long diffMinutes = total / (60 * 1000) % 60;
		long diffHours = total / (60 * 60 * 1000) % 24;
		long diffDays = total / (24 * 60 * 60 * 1000);

		if (diffDays != 0) {
			timeDiff = timeDiff + diffDays + " Day(s), ";
		}
		if (diffHours != 0) {
			timeDiff = timeDiff + diffHours + " Hour(s), ";
		}
		if (diffMinutes != 0) {
			timeDiff = timeDiff + diffMinutes + " Minute(s), ";
		}
		if (diffSeconds != 0) {
			timeDiff = timeDiff + diffSeconds + " Second(s).";
		}
		System.out.println(timeDiff);
		return timeDiff;
	}
	
	public static String getMessageBasedonTime() {

		String message = "";
		int currenTime = Integer.parseInt(DateTimeinMyFormat("HH"));
		if (currenTime < 12) {
			message = "Good Morning !";
		} else if (currenTime < 17) {
			message = "Good Afternoon !";
		} else {
			message = "Good Evening !";
		}
		return message;

	}
	
	public static String DateTimeinMyFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		return sdf.format(date);

	}


}
