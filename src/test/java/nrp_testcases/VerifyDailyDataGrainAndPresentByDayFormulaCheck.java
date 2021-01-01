package nrp_testcases;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import nrp_base.BaseNRP;
import nrp_objectmanager.ObjectManager_NRP;

public class VerifyDailyDataGrainAndPresentByDayFormulaCheck extends BaseNRP {

	
	
	ObjectManager_NRP pm;
	ArrayList<String> listOfColumnsFromSetup = new ArrayList<String>();
	HashMap<String, Object> DateAndValueFromCalendar = new HashMap<String, Object>();
	HashMap<String, Object> DailyValuesfromReport = new HashMap<String, Object>();

	ArrayList<String> DayWiseDates = new ArrayList<String>();




	@Test(priority = 1)
	public void doLogin() throws InterruptedException, IOException {

		pm = new ObjectManager_NRP(driver);

		pm.loginPage.doLogin(setup.getProperty("username"), setup.getProperty("password"));
	}

	@Test(priority = 2)
	public void Verify_DailyDataGrain_And_PresentByDay_FormulaCheck() throws InterruptedException, IOException, ParseException {

		pm.ec.deleteFile("Daily");
		pm.formula.CreateExcelFormulaCheck("Daily");
		pm.HomePage.verifyCreatedReportOnLeftBar(daily.getProperty("CategoryName"), daily.getProperty("ReportName"));
		pm.CLR.verifyDatePickersOFReport("Daily","Report");
		DailyValuesfromReport=pm.fetchReportDaily.fetchVariablevaluesFromReport("Daily");
		pm.map.ToMap_And_Comapare_Dailyformulas(DailyValuesfromReport, "Daily");

	}
	
	
	
	
}
