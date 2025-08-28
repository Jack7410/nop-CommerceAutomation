package com.Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGenerator extends TestListenerAdapter {

	ExtentHtmlReporter htmlReporter;
	ExtentReports reporter;
	ExtentTest test;

	@Override
	public void onTestSuccess(ITestResult tr) {
		test = reporter.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getMethod() + "Passed", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		test = reporter.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName() + "Failed", ExtentColor.RED));

		String screenShot = "./Screenshots/" + tr.getName() + ".png";
		File file = new File(screenShot);
		if (file.exists()) {
			try {
				test.addScreenCaptureFromPath(screenShot);
			} catch (IOException e) {
				System.out.print("Unable to add error screenshot to report ");
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		test = reporter.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName() + "Skiped", ExtentColor.ORANGE));
	}

	@Override
	public void onStart(ITestContext testContext) {
		String dateTime = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		String reportName = "Report" + dateTime + ".html";
		File reportFile = new File(".//Reports/" + reportName);

		htmlReporter = new ExtentHtmlReporter(reportFile);
		htmlReporter.config().setReportName("nop-Commerce Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("nop-Commerce Automation Report");

		reporter = new ExtentReports();
		reporter.attachReporter(htmlReporter);
		reporter.setSystemInfo("dateTime", dateTime);
		reporter.setSystemInfo("OS", "Windows 11");
		reporter.setSystemInfo("Env", "QE");
		reporter.setSystemInfo("Tester", "Jack");
		reporter.setSystemInfo("Project", "Nop-Commerce Automation");

	}

	@Override
	public void onFinish(ITestContext testContext) {
		reporter.flush();
	}

}
