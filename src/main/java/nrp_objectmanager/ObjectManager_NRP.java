package nrp_objectmanager;

import org.openqa.selenium.WebDriver;

import nrp_CustomKeywords.DateVerificationKeywords_NRP;
import nrp_CustomKeywords.FetchReportsValueForDailyFormulaCheck;
import nrp_CustomKeywords.FetchVariablefromReportAndcomapreWithCalendar_Daily;
import nrp_CustomKeywords.ToMapAndComapareDailyformulas;
import nrp_base.BaseNRP;
import nrp_pageobjects.CalendarViewPage_NRP;
import nrp_pageobjects.CompanySelectionPage__NRP;
import nrp_pageobjects.CrossLocationReportPage_NRP;
import nrp_pageobjects.HomePage_NRP;
import nrp_pageobjects.ListOfDataGroupPage_NRP;
import nrp_pageobjects.LoginPage_NRP;
import nrp_pageobjects.ReportSetupPage_NRP;
import nrp_pageobjects.ReportsPage_NRP;
import nrp_pageobjects.SetupPage_NRP;
import nrp_utilities.DailyFormulaCheckResult;
import nrp_utilities.ExcelCustom;
import nrp_utilities.WriteExcel;
import nrp_utilities.WriteExcelDailyFormula;

public class ObjectManager_NRP extends BaseNRP {

	
	public LoginPage_NRP loginPage;
	public CalendarViewPage_NRP calendarPage;
	public HomePage_NRP HomePage;
	public SetupPage_NRP setupPage;
	public ReportsPage_NRP ReportPage;
	public ReportSetupPage_NRP ReportSetup;
	public ListOfDataGroupPage_NRP DataGroup;
	public CrossLocationReportPage_NRP CLR;
	public DateVerificationKeywords_NRP dates;
	public FetchVariablefromReportAndcomapreWithCalendar_Daily FetchReport;
	public ExcelCustom ec;
	public WriteExcel write;
	public DailyFormulaCheckResult formula;
	public WriteExcelDailyFormula writeDaily;
	public FetchReportsValueForDailyFormulaCheck fetchReportDaily;
	public ToMapAndComapareDailyformulas map;
	public CompanySelectionPage__NRP comp;
	
	
	public ObjectManager_NRP(WebDriver driver) {

		loginPage = new LoginPage_NRP(driver);
		calendarPage= new CalendarViewPage_NRP(driver);
		HomePage=new HomePage_NRP(driver);
		setupPage=new SetupPage_NRP(driver);
		ReportPage=new ReportsPage_NRP(driver);
		ReportSetup=new ReportSetupPage_NRP(driver);
		DataGroup=new ListOfDataGroupPage_NRP(driver);
		CLR=new CrossLocationReportPage_NRP(driver);
		dates=new DateVerificationKeywords_NRP(driver);
		FetchReport=new FetchVariablefromReportAndcomapreWithCalendar_Daily(driver);
		ec= new ExcelCustom();
		write=new WriteExcel();
		formula=new DailyFormulaCheckResult();
		writeDaily=new WriteExcelDailyFormula();
		fetchReportDaily=new FetchReportsValueForDailyFormulaCheck(driver);
		map=new ToMapAndComapareDailyformulas(driver);
		comp=new CompanySelectionPage__NRP(driver);
	}
	
}
