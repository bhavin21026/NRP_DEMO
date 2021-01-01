package nrp_pageobjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;

public class CrossLocationReportPage_NRP extends BaseNRP {

	public CrossLocationReportPage_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		waitMore = new WebDriverWait(driver, 200);

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//label[@class='form-label'])[1]")
	WebElement StartDate;

	@FindBy(xpath = "(//label[@class='form-label'])[2]")
	WebElement EndDate;

	@FindBy(xpath = "(//div[contains(@class,'ui-kit-cell f-left')])[1]")
    WebElement StartDatePicker;

	@FindBy(xpath = "(//div[contains(@class,'ui-kit-cell f-left')])[2]")
	WebElement EndDatePicker;

	@FindBy(xpath = "(//button[contains(@class,'dp-calendar-nav-left')])[1]")
	WebElement cal_Closebtn;

	@FindBy(xpath = "(//button[contains(@class,'dp-calendar-nav-left')])[2]")
	WebElement cal_ClosebtnEnd;

	@FindBy(xpath = "(//button[contains(@class,'dp-nav-header-btn')])[1]")
	WebElement MonthValue;

	@FindBy(xpath = "(//button[contains(@class,'dp-nav-header-btn')])[2]")
	WebElement EndMonthValue;

	// StoreSelectors

	@FindBy(xpath = "//button[@class='btn btn--secondary nrpbtn']")
	WebElement drp_StoreSelector;

	@FindBy(xpath = "//span[text()='Check All']")
	WebElement btn_checkAll;//span[text()='BROWARD']
	
	@FindBy(xpath = "//span[text()='BROWARD']")
	WebElement btn_State;
	@FindBy(xpath = "//span[text()='Andrew']")
	WebElement btn_District;
	@FindBy(xpath = "//*[@id=\"panelContainer\"]/nrp-select-level/div/div/div[2]/div/nrp-select-level/div/div/div[2]/div/nrp-select-level/div/div/div[1]/div/div[1]/div/div/label")
	WebElement btn_Store;  
	@FindBy(xpath = "//button[text()='Apply']")
	WebElement btn_Apply;
	
	@FindBy(xpath = "//button[text()='Uncheck All']")
	WebElement link_uncheckAll;

	@FindBy(xpath = "(//input[@placeholder='Search...'])[1]")
	WebElement input_SearchStores;

	@FindBy(xpath = "//button[contains(@class,'btn btn--primary') and text() ='Refresh']")
	WebElement btn_RefreshBtn;

	public void setStartDateCal(String DataGrain, String location) {

		String StartDateDaily = null;
		String StartDateWeekly = null;
		String StartDateMonthly = null;
		String StartMonthDaily = null;
		String StartMonthWeekly = null;
		String StartMonthMonthly = null;

		if (location.equalsIgnoreCase("Report")) {

			StartDateDaily = daily.getProperty("ReportCalStartDate");
			StartDateWeekly = weekly.getProperty("ReportCalStartDate");
			StartDateMonthly = monthly.getProperty("ReportCalStartDate");
			StartMonthDaily = daily.getProperty("ReportCalStartMonthYear");
			StartMonthWeekly = weekly.getProperty("ReportCalStartMonthYear");
			StartMonthMonthly = monthly.getProperty("ReportCalStartMonthYear");

		} else if (location.equalsIgnoreCase("Calendar"))

		{
			StartDateDaily = daily.getProperty("stCalStartDate");
			StartDateWeekly = weekly.getProperty("stCalStartDate");
			StartDateMonthly = monthly.getProperty("stCalStartDate");
			StartMonthDaily = daily.getProperty("stCalStartMonthYear");
			StartMonthWeekly = weekly.getProperty("stCalStartMonthYear");
			StartMonthMonthly = monthly.getProperty("stCalStartMonthYear");

		}

		waitUntilClickable(StartDatePicker);
		StartDatePicker.click();

		if (DataGrain.equalsIgnoreCase("Daily")) {

			// scrollToElement(MonthValue);
			while (!MonthValue.getText().equalsIgnoreCase(StartMonthDaily)) {
				// scrollToElement(cal_Closebtn);
				cal_Closebtn.click();
			}
			WebElement picker = driver.findElement(By.xpath("(//div[@class='dp-calendar-wrapper'])[1]"));

			List<WebElement> Days = picker.findElements(By.tagName("div"));

			int totalelementsfind = Days.size();

			int flag = 0;
			for (int i = 1; i < totalelementsfind; i++) {

				List<WebElement> Days1 = Days.get(i)
						.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));

				int totalelementsfind1 = Days1.size();

				for (int j = 0; j < totalelementsfind1; j++) {
					
					List<WebElement> Days2 = Days.get(i)
							.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));
					String DayValue = Days2.get(j).getText();

					if (DayValue.equalsIgnoreCase(StartDateDaily)) {
						Days2.get(j).click();
						flag = 1;
						log.info("Start date for cal test case is selected");
						break;
					}

				}

				if (flag == 1) {
					break;
				}

			}

		} else if (DataGrain.equalsIgnoreCase("Weekly")) {

			while (!MonthValue.getText().equalsIgnoreCase(StartMonthWeekly)) {

				cal_Closebtn.click();
			}
			WebElement picker = driver.findElement(By.xpath("(//div[@class='dp-calendar-wrapper'])[1]"));

			List<WebElement> Days = picker.findElements(By.tagName("div"));

			int totalelementsfind = Days.size();

			int flag = 0;
			for (int i = 1; i < totalelementsfind; i++) {

				List<WebElement> Days1 = Days.get(i)
						.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));

				int totalelementsfind1 = Days1.size();

				for (int j = 0; j < totalelementsfind1; j++) {
					List<WebElement> Days2 = Days.get(i)
							.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));

					String DayValue = Days2.get(j).getText();

					if (DayValue.equalsIgnoreCase(StartDateWeekly)) {
						Days2.get(j).click();
						flag = 1;
						log.info("Start date for cal test case is selected");

						break;
					}

				}

				if (flag == 1) {
					break;
				}

			}

		} else if (DataGrain.equalsIgnoreCase("Monthly")) {

			while (!MonthValue.getText().equalsIgnoreCase(StartMonthMonthly)) {

				cal_Closebtn.click();
			}
			WebElement picker = driver.findElement(By.xpath("(//div[@class='dp-calendar-wrapper'])[1]"));

			List<WebElement> Days = picker.findElements(By.tagName("div"));

			int totalelementsfind = Days.size();

			int flag = 0;
			for (int i = 1; i < totalelementsfind; i++) {

				List<WebElement> Days1 = Days.get(i)
						.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));

				int totalelementsfind1 = Days1.size();

				for (int j = 0; j < totalelementsfind1; j++) {
					List<WebElement> Days2 = Days.get(i)
							.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));
					String DayValue = Days2.get(j).getText();

					if (DayValue.equalsIgnoreCase(StartDateMonthly)) {
						Days2.get(j).click();
						flag = 1;
						log.info("Start date for cal test case is selected");

						break;
					}

				}

				if (flag == 1) {
					break;
				}

			}

		}

	}

	public void setEndDateCal(String DataGrain, String location) {

		String EndDateDaily = null;
		String EndDateWeekly = null;
		String EndDateMonthly = null;
		String EndMonthDaily = null;
		String EndMonthWeekly = null;
		String EndMonthMonthly = null;

		if (location.equalsIgnoreCase("Report")) {

			EndDateDaily = daily.getProperty("ReportCalEndDate");
			EndDateWeekly = weekly.getProperty("ReportCalEndDate");
			EndDateMonthly = monthly.getProperty("ReportCalEndDate");
			EndMonthDaily = daily.getProperty("ReportCalEndMonthYear");
			EndMonthWeekly = weekly.getProperty("ReportCalEndMonthYear");
			EndMonthMonthly = monthly.getProperty("ReportCalEndMonthYear");

		} else if (location.equalsIgnoreCase("Calendar"))

		{
			EndDateDaily = daily.getProperty("stCalEndDate");
			EndDateWeekly = weekly.getProperty("stCalEndDate");
			EndDateMonthly = monthly.getProperty("stCalEndDate");
			EndMonthDaily = daily.getProperty("stCalEndMonthYear");
			EndMonthWeekly = weekly.getProperty("stCalEndMonthYear");
			EndMonthMonthly = monthly.getProperty("stCalEndMonthYear");

		}

		waitUntilClickable(EndDatePicker);
		EndDatePicker.click();

		if (DataGrain.equalsIgnoreCase("Daily")) {

			// scrollToElement(MonthValue);
			while (!EndMonthValue.getText().equalsIgnoreCase(EndMonthDaily)) {
				// scrollToElement(cal_Closebtn);
				cal_ClosebtnEnd.click();
			}
			WebElement picker = driver.findElement(By.xpath("(//div[@class='dp-calendar-wrapper'])[1]"));

			List<WebElement> Days = picker.findElements(By.tagName("div"));

			int totalelementsfind = Days.size();

			int flag = 0;
			for (int i = 1; i < totalelementsfind; i++) {

				List<WebElement> Days1 = Days.get(i)
						.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));

				int totalelementsfind1 = Days1.size();

				for (int j = 0; j < totalelementsfind1; j++) {
					List<WebElement> Days2 = Days.get(i)
							.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));
					String DayValue = Days2.get(j).getText();

					if (DayValue.equalsIgnoreCase(EndDateDaily)) {
						Days2.get(j).click();
						flag = 1;
						log.info("End date for cal test case is selected");
						break;
					}

				}

				if (flag == 1) {
					break;
				}

			}

		} else if (DataGrain.equalsIgnoreCase("Weekly")) {

			while (!EndMonthValue.getText().equalsIgnoreCase(EndMonthWeekly)) {

				cal_ClosebtnEnd.click();
			}
			WebElement picker = driver.findElement(By.xpath("(//div[@class='dp-calendar-wrapper'])[1]"));

			List<WebElement> Days = picker.findElements(By.tagName("div"));

			int totalelementsfind = Days.size();

			int flag = 0;
			for (int i = 1; i < totalelementsfind; i++) {

				List<WebElement> Days1 = Days.get(i)
						.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));

				int totalelementsfind1 = Days1.size();

				for (int j = 0; j < totalelementsfind1; j++) {
					List<WebElement> Days2 = Days.get(i)
							.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));
					String DayValue = Days2.get(j).getText();

					if (DayValue.equalsIgnoreCase(EndDateWeekly)) {
						Days2.get(j).click();
						flag = 1;
						log.info("End date for cal test case is selected");

						break;
					}

				}

				if (flag == 1) {
					break;
				}

			}

		} else if (DataGrain.equalsIgnoreCase("Monthly")) {

			while (!EndMonthValue.getText().equalsIgnoreCase(EndMonthMonthly)) {

				cal_ClosebtnEnd.click();
			}
			WebElement picker = driver.findElement(By.xpath("(//div[@class='dp-calendar-wrapper'])[1]"));

			List<WebElement> Days = picker.findElements(By.tagName("div"));

			int totalelementsfind = Days.size();

			int flag = 0;
			for (int i = 1; i < totalelementsfind; i++) {

				List<WebElement> Days1 = Days.get(i)
						.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));

				int totalelementsfind1 = Days1.size();

				for (int j = 0; j < totalelementsfind1; j++) {
					List<WebElement> Days2 = Days.get(i)
							.findElements(By.xpath("//button[@class='dp-calendar-day dp-current-month ng-star-inserted']"));
					String DayValue = Days2.get(j).getText();

					if (DayValue.equalsIgnoreCase(EndDateMonthly)) {
						Days2.get(j).click();
						flag = 1;
						log.info("End date for cal test case is selected");

						break;
					}

				}

				if (flag == 1) {
					break;
				}

			}

		}

	}

	/// ****************************************************//

	public void verifyDatePickersOFReport(String DataGrain, String Location) throws InterruptedException, IOException {

		waitForVisibility(StartDate);
		waitForVisibility(EndDate);

		String StartDates = StartDate.getText();

		String EndDates = EndDate.getText();

		if (DataGrain.equalsIgnoreCase("Daily")) {

			if (daily.getProperty("PresentBy").equalsIgnoreCase("Day")
					|| daily.getProperty("PresentBy").equalsIgnoreCase("EntireRange"))

			{
				if (StartDates.equalsIgnoreCase("Start Date:") && EndDates.equalsIgnoreCase("End Date:")) {

					log.info("Verification of date picker done for present by Day.");
					setStartDateCal(DataGrain, Location);
					setEndDateCal(DataGrain, Location);
					selectStores(daily.getProperty("PC"));
					hitRefreshBtn();
					waitForReportprogressbar();

				} else {
					log.info("Date picker coming wrong as per setup configuration");
					getScreenshot("DatePicker Issue");

					sa.assertFalse(false);

				}

			} else if (daily.getProperty("PresentBy").equalsIgnoreCase("Week")) {

				if (StartDates.equalsIgnoreCase("Start Week:") && EndDates.equalsIgnoreCase("End Week:")) {

					log.info("Verification of date picker done for present by Week.");

					setStartDateCal(DataGrain, Location);
					setEndDateCal(DataGrain, Location);
					selectStores(daily.getProperty("PC"));
					hitRefreshBtn();
					waitForReportprogressbar();

				} else {
					log.info("Date picker coming wrong as per setup configuration");
					getScreenshot("DatePicker Issue");

					sa.assertFalse(false);

				}

			} else if (daily.getProperty("PresentBy").equalsIgnoreCase("Month")) {

				if (StartDates.equalsIgnoreCase("Start Month:") && EndDates.equalsIgnoreCase("End Month:")) {

					log.info("Verification of date picker done for present by Month.");

					setStartDateCal(DataGrain, Location);
					setEndDateCal(DataGrain, Location);
					selectStores(daily.getProperty("PC"));
					hitRefreshBtn();
					waitForReportprogressbar();

				} else {
					log.info("Date picker coming wrong as per setup configuration");
					getScreenshot("DatePicker Issue");

					sa.assertFalse(false);

				}

			}

		} else if (DataGrain.equalsIgnoreCase("Weekly")) {

			if (weekly.getProperty("PresentBy").equalsIgnoreCase("Week")
					|| weekly.getProperty("PresentBy").equalsIgnoreCase("EntireRange"))

			{
				if (StartDates.equalsIgnoreCase("Start Week:") && EndDates.equalsIgnoreCase("End Week:")) {

					log.info("Verification of date picker done for present by Week.");

					setStartDateCal(DataGrain, Location);
					setEndDateCal(DataGrain, Location);
					selectStores(weekly.getProperty("PC"));
					hitRefreshBtn();
					waitForReportprogressbar();

				} else {
					log.info("Date picker coming wrong as per setup configuration");
					getScreenshot("DatePicker Issue");

					sa.assertFalse(false);

				}

			} else if (weekly.getProperty("PresentBy").equalsIgnoreCase("Month")) {

				if (StartDates.equalsIgnoreCase("Start Month:") && EndDates.equalsIgnoreCase("End Month:")) {

					log.info("Verification of date picker done for present by Month.");

					setStartDateCal(DataGrain, Location);
					setEndDateCal(DataGrain, Location);
					selectStores(weekly.getProperty("PC"));
					hitRefreshBtn();
					waitForReportprogressbar();

				} else {
					log.info("Date picker coming wrong as per setup configuration");
					getScreenshot("DatePicker Issue");

					sa.assertFalse(false);

				}

			}

			else if (DataGrain.equalsIgnoreCase("Monthly")) {

				if (monthly.getProperty("PresentBy").equalsIgnoreCase("Month")
						|| daily.getProperty("PresentBy").equalsIgnoreCase("EntireRange"))

				{
					if (StartDates.equalsIgnoreCase("Start Month:") && EndDates.equalsIgnoreCase("End Month:")) {

						log.info("Verification of date picker done for present by Month.");
						setStartDateCal(DataGrain, Location);
						setEndDateCal(DataGrain, Location);
						selectStores(monthly.getProperty("PC"));
						hitRefreshBtn();
						waitForReportprogressbar();

					} else {
						log.info("Date picker coming wrong as per setup configuration");
						getScreenshot("DatePicker Issue");

						sa.assertFalse(false);

					}

				}

			}

		}
	}

	public void selectStores(String PC) {

		waitUntilClickable(drp_StoreSelector);
		drp_StoreSelector.click();
		waitUntilClickable(btn_checkAll);
		scrollToElement(btn_State);
		scrollToElement(btn_District);
		scrollToElement(btn_Store);
		scrollToElement(btn_Apply);
		

		
		//btn_checkAll.click();
		//link_uncheckAll.click();
		
		
		/*waitUntilClickable(input_SearchStores);
		input_SearchStores.click();
		input_SearchStores.sendKeys(PC);
		log.info("PC name typed");
		WebElement StoreInList = driver.findElement(By.xpath("(//span[contains(text(),'" + PC + "')])[1]"));
		// WebElement StoreInList=driver.findElement(By.xpath("//span[contains(text(),'-
		// "+PCName+"')]"))
		StoreInList.click();
		log.info("PC selected");*/

	}

	public void waitForReportprogressbar() throws InterruptedException {

		WebElement progressbar = driver.findElement(By.xpath("(//div[@class='spinner'])[1]"));
		waitMore.until(ExpectedConditions.visibilityOf(progressbar));
		waitMore.until(ExpectedConditions.invisibilityOf(progressbar));
		Thread.sleep(3000);
	}

	public void hitRefreshBtn() throws InterruptedException {

		waitUntilClickable(btn_RefreshBtn);
		btn_RefreshBtn.click();
		log.info("Please wait report is loading........");
	}

	public void verifyAddedColumnsInReports(ArrayList<String> ListOfColumnsFromReport) throws InterruptedException {

		WebElement HeaderRow = driver.findElement(By.xpath("(//thead[@class=\"ui-table-thead\"])[2]"));

		List<WebElement> Headers = HeaderRow.findElements(By.tagName("tr"));

		List<WebElement> TotalAddedColumns = Headers.get(1).findElements(By.tagName("th"));

		int NoOfdaysSelected = TotalAddedColumns.size();

		ArrayList<String> ColumnListFromReport = new ArrayList<String>();

		int AddedColumns = ListOfColumnsFromReport.size();

		for (int i = 0; i < AddedColumns; i++) {

			WebElement AddedColumnNameOnReport = TotalAddedColumns.get(i);

			justscrollToElement(AddedColumnNameOnReport);

			String AddedColumnNameReport = AddedColumnNameOnReport.getText().replaceAll("\r\n", " ")
					.replaceAll("\r", " ").replaceAll("\n", " ");

			ColumnListFromReport.add(i, AddedColumnNameReport);

			log.info("*************ON REPORT******************");

			log.info(ColumnListFromReport.get(i));

			log.info("*************ON REPORT******************");
		}

		if (ColumnListFromReport.equals(ListOfColumnsFromReport)) {

			log.info("Report  colums matched with added columns in setup");
			assert true;
		} else {
			assert false;
		}
	}

}
