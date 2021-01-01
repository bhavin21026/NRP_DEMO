package nrp_testcases;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import nrp_base.BaseNRP;
import nrp_objectmanager.ObjectManager_NRP;

public class VerifyCrossLocationDatawithCalendar extends BaseNRP {

	ObjectManager_NRP pm;
	ArrayList<String> listOfColumnsFromSetup = new ArrayList<String>();
	HashMap<String, Object> DateAndValueFromCalendar = new HashMap<String, Object>();
	ArrayList<String> DayWiseDates = new ArrayList<String>();

	


	

	@Test(priority = 1)
	public void VerifyDataOfCrossLocationwithCalendar() throws InterruptedException, IOException, ParseException {

		//Thread.sleep(2000);
		pm = new ObjectManager_NRP(driver);
		pm.loginPage.doLogin(setup.getProperty("username"), setup.getProperty("password"));
		pm.comp.selectClient();
		
		pm.calendarPage.waitforRefreshButton();
		
		
		
		//pm.HomePage.clickOnSnapShotReport();
		
		pm.setupPage.navigateToReportsPage();
		pm.ReportPage.clickOncreateReportLink();
		pm.ReportSetup.giveNameToReport(daily.getProperty("ReportName"));
		pm.ReportSetup.selectReportFormat(daily.getProperty("FormatName"));
		pm.ReportSetup.selectReportCategory(daily.getProperty("CategoryName"));

		pm.ReportSetup.selectDataGraingAndPresentBy("Daily");
		listOfColumnsFromSetup=pm.ReportSetup.validateListOfAddedColumns();
		pm.ReportSetup.selectGroupingHierarchy();
		pm.ReportSetup.saveCreatedReport();
		pm.ReportPage.searchAndValidatecreatedReport(daily.getProperty("ReportName"));
		pm.HomePage.navigateToLeftBar();
		pm.HomePage.clickOnCalendardashboard();
		//pm.calendarPage.selectPCName(daily.getProperty("PC"));
		pm.calendarPage.selectOperationCalendar(daily.getProperty("CalendarYear"),daily.getProperty("OperationalCalendar"));
		DateAndValueFromCalendar=pm.calendarPage.getDailyvaluesFromCalendarView();
		pm.HomePage.verifyCreatedReportOnLeftBar(daily.getProperty("CategoryName"), daily.getProperty("ReportName"));
		pm.CLR.verifyDatePickersOFReport("Daily","Calendar");
		DayWiseDates=pm.dates.verifyDatesOnReport("Daily");
		pm.CLR.verifyAddedColumnsInReports(listOfColumnsFromSetup);
		pm.FetchReport.fetchDailyReportValueAndComapreWithCalendar(DateAndValueFromCalendar,DayWiseDates,listOfColumnsFromSetup.size(),"Daily");
		//pm.FetchReport.fetchDailyReportValueAndComapreWithCalendar("Daily");



	}

}
