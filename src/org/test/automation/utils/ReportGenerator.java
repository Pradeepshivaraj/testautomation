package org.test.automation.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.test.automation.base.BrowserManager;
import org.test.automation.exception.CFITRException;

public class ReportGenerator extends BrowserManager {

	public static void writeToHTML(String url, ArrayList<String> _Modules, ArrayList<String> tCList,
			ArrayList<Integer> totalList2, ArrayList<Integer> passedList2, ArrayList<Integer> failedList2,
			ArrayList<Integer> skippedList2, int pc, int fc, int sc, int total, ArrayList<String> totalTimeList2,
			String reportPath, ArrayList<String> exceptionList, ArrayList<String> snapShotList2, String totalTimeTaken,
			String msg, String tcDetails) throws CFITRException, IOException {

		StringBuilder sb = new StringBuilder();

		Iterator<String> it2 = exceptionList.iterator();
		Iterator<String> tc = tCList.iterator();

		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("<font family= \"Times New Roman\">");

		sb.append("Hi Team,");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("<b>");
		sb.append(msg);
		sb.append("</b>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("Please find below Test Execution Results for ");
		sb.append("<b>");
		sb.append("Test Execution.");
		sb.append("</b>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("Environment");
		sb.append("<b>");

		sb.append(" : " + url);
		sb.append("</b>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("Operating System");
		sb.append("<b>");
		
		sb.append(" : " + System.getProperty("os.name"));
		sb.append("</b>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("OS Architecture");
		sb.append("<b>");

		sb.append(" : " + System.getProperty("os.arch"));
		sb.append("</b>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("Java Version");
		sb.append("<b>");

		sb.append(" : " + System.getProperty("java.version"));
		sb.append("</b>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("</font>");
		sb.append("</table>");

		sb.append("<table>");

		if (exceptionList.size() > 0) {
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<font color= \"red\",family= \"Times New Roman\">");
			sb.append("<b>");
			sb.append("Failures : ");
			sb.append("</b>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<th>");
			sb.append("<tr>");

			sb.append(
					"<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append("Name of the Module");
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append("Testcase Name");
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append("Failure Description");
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append("ScreenShot");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("</th>");
		}
		while (it2.hasNext()) {
			sb.append("<tr>");
			try {
				String parts[] = it2.next().split("::");
				sb.append(
						"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: left;width:15%\">");
				sb.append(parts[0]);
				sb.append("</td>");

				sb.append(
						"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: left;width:15%\">");
				sb.append("&nbsp;");

				sb.append(tc.next().toUpperCase());
				sb.append("</td>");

				sb.append(
						"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: left;width:15%\">");
				sb.append("&nbsp;");

				sb.append(parts[2].replaceAll("_", " "));
				sb.append("</td>");

				sb.append(
						"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: center;width:15%\">");
				sb.append(parts[3]);
				sb.append("</td>");

				sb.append("</tr>");
			} catch (ArrayIndexOutOfBoundsException aiobe) {
				System.out.println("No Failures Found.");
			}
		}
		sb.append("<br>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("<br>");

		sb.append("<tr>");
		sb.append("<td>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td>");
		sb.append("<font color= \"Green\">");
		sb.append("<b>");
		sb.append("Test Summary : ");
		sb.append("</b>");
		sb.append("</font>");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Name of the Module");
		sb.append("</td>");

		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Executed");
		sb.append("</td>");

		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Passed");
		sb.append("</td>");

		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Failed");
		sb.append("</td>");

		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Skipped");

		sb.append("</td>");

		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Pass Percentage");

		sb.append("</td>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Time Taken for Execution");

		sb.append("</td>");
		sb.append("</tr>");
		Iterator<String> it = _Modules.iterator();
		Iterator<Integer> totalList = totalList2.iterator();
		Iterator<Integer> passedList = passedList2.iterator();
		Iterator<Integer> failedList = failedList2.iterator();
		Iterator<Integer> skippedList = skippedList2.iterator();
		Iterator<String> timetakenList = totalTimeList2.iterator();
		while (it.hasNext()) {
			sb.append("<tr>");
			sb.append(
					"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: left;width:15%\">");
			sb.append(it.next().replace("_", " "));
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: center;width:15%\">");
			int totalExecuted = totalList.next();
			sb.append(totalExecuted);
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: center;width:15%\">");
			int passedCases = passedList.next();
			sb.append(passedCases);
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append(failedList.next());
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append(skippedList.next());
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: center;width:15%\">");
			DecimalFormat f = new DecimalFormat("00.00");
			double percentage = ((double) passedCases / (double) totalExecuted) * 100;
			sb.append(f.format(percentage) + "%");
			sb.append("</td>");

			sb.append(
					"<td style = \"background: #E0EEEE; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append(timetakenList.next());
			sb.append("</td>");

			sb.append("</tr>");
		}

		sb.append("<tr>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Total");
		sb.append("</td>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append(total);
		sb.append("</td>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append(pc);
		sb.append("</td>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append(fc);
		sb.append("</td>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append(sc);
		sb.append("</td>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		DecimalFormat f = new DecimalFormat("00.00");
		double totalPercent = ((double) pc / (double) total) * 100;
		sb.append(f.format(totalPercent) + "%");
		sb.append("</td>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append(totalTimeTaken);
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<br>");
		sb.append("<br>");
		sb.append("<br>");

		sb.append("<tr>");
		sb.append("<td>");
		sb.append("<font color= \"Green\">");
		sb.append("<br>");
		sb.append("<b>");
		sb.append("Test Execution Details : ");
		sb.append("</b>");
		sb.append("<br>");
		sb.append("</font>");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td");
		sb.append(tcDetails);
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<br>");
		sb.append("<br>");
		sb.append("<br>");

		sb.append("<tr>");
		sb.append("<td style = \"width:30%\">");
		sb.append(reportPath);
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("</table>");

		sb.append("</body>");
		sb.append("</html>");

		System.out.println(sb.toString());

		File file = new File(System.getProperty("user.dir") + "\\TestReport.html");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());

		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();
	}

	public static String TCwriteToHTML(String moduleName, ArrayList<String> tcNameList,
			ArrayList<String> executionStatusList) throws CFITRException, IOException {

		StringBuilder sb = new StringBuilder();

		Iterator<String> tc = tcNameList.iterator();
		Iterator<String> status = executionStatusList.iterator();

		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<table>");
		sb.append("<th>");
		sb.append("<tr>");
		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Name of the Module");
		sb.append("</td>");

		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Test case Name");
		sb.append("</td>");

		sb.append("<td style = \"background: #05B8CC; color: #000; font-weight: bold;text-align: center;width:15%\">");
		sb.append("Status");
		sb.append("</td>");

		sb.append("</tr>");
		sb.append("</th>");

		while (tc.hasNext()) {
			sb.append("<tr>");
			sb.append(
					"<td style = \"border:solid 1px; background: #FFFFFF; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append(moduleName);
			sb.append("</td>");
			sb.append(
					"<td style = \"border:solid 1px; background: #FFFFFF; color: #000; font-weight: bold;text-align: center;width:15%\">");
			sb.append(StringUtils.toCamelCase(tc.next().toUpperCase()));
			sb.append("</td>");

			String _Status = status.next();

			System.out.println("::" + _Status + "::" + TCPASSED + "::" + TCFAILED + "::" + TCSKIPPED);

			if (_Status.equals(TCPASSED)) {
				sb.append(
						"<td style = \"border:solid 1px; background: #FFFFFF; color: green; font-weight: bold;text-align: center;width:15%\">");
				sb.append(_Status);
				sb.append("</td>");
			} else if (_Status.equals(TCFAILED)) {
				sb.append(
						"<td style = \"border:solid 1px; background: #FFFFFF; color: #ff0000; font-weight: bold;text-align: center;width:15%\">");
				sb.append(_Status);
				sb.append("</td>");
			} else if (_Status.equals(TCSKIPPED)) {
				sb.append(
						"<td style = \"border:solid 1px; background: #FFFFFF; color: #ff8c00; font-weight: bold;text-align: center;width:15%\">");
				sb.append(_Status);
				sb.append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");

		// System.out.println(sb.toString());

		File file = new File(System.getProperty("user.dir") + "\\TCTestReport.html");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());

		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();
		return sb.toString();
	}


}
