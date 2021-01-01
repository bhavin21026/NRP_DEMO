package nrp_pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;
import nrp_objectmanager.ObjectManager_NRP;

public class ReportSetupPage_NRP extends BaseNRP {

	ObjectManager_NRP pm;

	public ReportSetupPage_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 100);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='model-label']")
	WebElement txt_nameOfReport;

	@FindBy(xpath = "//*[@id=\"main-field\"]/div/div/div/app-reports/div/app-report-details/section/div[3]/div/form/div/div/div[2]/div[1]/ng-select/div/span")
	WebElement txt_nameOfReportCategory;
	
	@FindBy(xpath = "(//input[@Role='combobox'])[2]")
	WebElement txt_nameOfCat;
	
	@FindBy(xpath = "(//input[@Role='combobox'])[1]")
	WebElement txt_nameOfReportFormat;

	@FindBy(xpath = "//button[contains(@class, 'btn btn--group w-tab') and (text()=' Daily ')]")
	WebElement btn_Daily;

	@FindBy(xpath = "//button[contains(@class, 'btn btn--group w-tab') and (text()=' Day ')]")
	WebElement btn_Day;

	@FindBy(xpath = "//button[contains(@class, 'btn btn--group w-tab') and (text()=' Weekly ')]")
	WebElement btn_Weekly;

	@FindBy(xpath = "//button[contains(@class, 'btn btn--group w-tab') and (text()=' Week ')]")
	WebElement btn_Week;

	@FindBy(xpath = "//button[contains(@class, 'btn btn--group w-tab') and (text()=' Monthly ')]")
	WebElement btn_Monthly;

	@FindBy(xpath = "//button[contains(@class, 'btn btn--group w-tab') and (text()=' Month ')]")
	WebElement btn_Month;

	@FindBy(xpath = "//button[contains(@class, 'btn btn--group w-tab') and (text()=' Entire Range ')]")
	WebElement btn_EntireRange;

	@FindBy(xpath = "//input[@max='35']")
	WebElement txt_daysLimit;

	@FindBy(xpath = "//input[@max='13']")
	WebElement txt_weeksLimit;

	@FindBy(xpath = "//input[@max='12']")
	WebElement txt_monthsLimit;

	// Grouping hierarchy elements

	@FindBy(xpath = "//button[contains(@class,'btn btn--group w-tab') and text() =' By Hierarchy ']")
	WebElement btn_byHierarchy;

	@FindBy(xpath = "//span[contains(@class,'checkbox__txt') and text() ='By Region']")
	WebElement btn_byRegion;

	@FindBy(xpath = "//span[contains(@class,'checkbox__txt') and text() ='Show Region Subtotal']")
	WebElement btn_RegionSubtotal;

	@FindBy(xpath = "//span[contains(@class,'checkbox__txt') and text() ='By District']")
	WebElement btn_byDistrict;

	@FindBy(xpath = "//span[contains(@class,'checkbox__txt') and text() ='Show District Subtotal']")
	WebElement btn_byDistrictSubtotal;

	@FindBy(xpath = "//button[text()=' Create report ']")
	WebElement btn_CreateReport;
	
	@FindBy(xpath = "//*[@id=\"main-field\"]/div/div/div/app-reports/div/app-report-details/section/div[3]/div/div/div/svg-icon")
	WebElement btn_listOfColumns;
	
	@FindBy(xpath = "//button[@type='button'and text()='OK']")
	WebElement txt_OkBtn;

	public void selectReportCategory(String CategoryName) throws InterruptedException {

		txt_nameOfReportCategory.click();
		Thread.sleep(5000);
		//WebElement tplCategory = driver.findElement(By.xpath("(//span[contains(text(),"+CategoryName+")])[2]"));
		//waitUntilClickable(tplCategory);
		//tplCategory.click();
		txt_nameOfCat.sendKeys(CategoryName);
	    log.info("Report category entered ");
		Thread.sleep(2000);

		
		txt_nameOfCat.sendKeys(Keys.ENTER);
		log.info("Report category selected ");

		// WebElement tplCategory =
		// driver.findElement(By.xpath("(//span[contains(text(),"+CategoryName+")])[2]"));
		// waitUntilClickable(tplCategory);
		// tplCategory.click();

	}
	
	public void selectReportFormat(String FormatName) throws InterruptedException {

		txt_nameOfReportFormat.click();
		Thread.sleep(1000);
		txt_nameOfReportFormat.sendKeys(FormatName);
		log.info("Report format entered ");
	
		txt_nameOfReportFormat.sendKeys(Keys.ENTER);
		log.info("Report format selected ");

		// WebElement tplCategory =
		// driver.findElement(By.xpath("(//span[contains(text(),"+CategoryName+")])[2]"));
		// waitUntilClickable(tplCategory);
		// tplCategory.click();

	}
	

	public void giveNameToReport(String ReportName) throws InterruptedException {

		 waitUntilClickable(txt_nameOfReport);
		//txt_nameOfReport.click();
		//txt_nameOfReport.sendKeys(ReportName);
		 divType(txt_nameOfReport,ReportName);
		//txt_nameOfReport.click();
		//txt_nameOfReport.sendKeys(ReportName);
		log.info("Report Name entered");
	}
	
	

	public void grainByDay_PresentByDay(String daysLimit) {

		scrollToElement(btn_Daily);
		scrollToElement(btn_Day);
		txt_daysLimit.click();
		txt_daysLimit.sendKeys(daysLimit);
		log.info("Daily data grain and day present by selected");

	}

	public void grainByDay_PresentByWeek(String weeksLimit) {

		scrollToElement(btn_Daily);
		scrollToElement(btn_Week);
		txt_weeksLimit.click();
		txt_weeksLimit.sendKeys(weeksLimit);
		log.info("Daily data grain and week present by selected");

	}

	public void grainByDay_PresentByMonth(String monthsLimit) {

		scrollToElement(btn_Daily);
		scrollToElement(btn_Month);
		txt_monthsLimit.click();
		txt_monthsLimit.sendKeys(monthsLimit);
		log.info("Daily data grain and month present by selected");

	}

	public void grainByDay_PresentByER() {

		scrollToElement(btn_Daily);
		scrollToElement(btn_EntireRange);
		log.info("Daily data grain and ER present by selected");

	}

	public void grainByWeek_PresentByWeek(String weeksLimit) {

		scrollToElement(btn_Weekly);
		scrollToElement(btn_Week);
		txt_weeksLimit.click();
		txt_weeksLimit.sendKeys(weeksLimit);
		log.info("Weekly data grain and Week present by selected");

	}

	public void grainByWeek_PresentByMonth(String monthsLimit) {

		scrollToElement(btn_Weekly);
		scrollToElement(btn_Month);
		txt_monthsLimit.click();
		txt_monthsLimit.sendKeys(monthsLimit);
		log.info("Weekly data grain and Month present by selected");

	}

	public void grainByWeek_PresentByER() {

		scrollToElement(btn_Weekly);
		scrollToElement(btn_EntireRange);
		log.info("Weekly data grain and ER present by selected");

	}

	public void grainByMonth_PresentByMonth(String monthsLimit) {

		scrollToElement(btn_Monthly);
		scrollToElement(btn_Month);
		txt_monthsLimit.click();
		txt_monthsLimit.sendKeys(monthsLimit);
		log.info("Monthly data grain and Month present by selected");

	}

	public void grainByMonth_PresentByER() {

		scrollToElement(btn_Monthly);
		scrollToElement(btn_EntireRange);
		log.info("Monthly data grain and ER present by selected");

	}

	public void selectDataGraingAndPresentBy(String DataGrain) throws InterruptedException {

		pm = new ObjectManager_NRP(driver);

		if (DataGrain.equalsIgnoreCase("Daily")) {

			System.out.println(daily.getProperty("PresentBy").equalsIgnoreCase("Day"));

			if (daily.getProperty("PresentBy").equalsIgnoreCase("Day"))

			{
				grainByDay_PresentByDay(daily.getProperty("daysLimit"));
				pm.DataGroup.add_Daily_NetSales();
				pm.DataGroup.add_Daily_GuestCount();
				pm.DataGroup.add_Daily_AverageCheck();
				pm.DataGroup.add_Daily_LaborCost();
				pm.DataGroup.add_Daily_LaborHours();
				//pm.DataGroup.add_Daily_Cost();
				pm.DataGroup.add_Daily_PrimeCost();
				pm.DataGroup.add_Daily_GrossSales();

			} else if (daily.getProperty("PresentBy").equalsIgnoreCase("Week"))

			{
				grainByDay_PresentByWeek(daily.getProperty("weeksLimit"));
				pm.DataGroup.add_Daily_NetSales();
				pm.DataGroup.add_Daily_GuestCount();
				pm.DataGroup.add_Daily_AverageCheck();
				pm.DataGroup.add_Daily_LaborCost();
				pm.DataGroup.add_Daily_LaborHours();
				//pm.DataGroup.add_Daily_Cost();
				pm.DataGroup.add_Daily_PrimeCost();
				pm.DataGroup.add_Daily_GrossSales();

			} else if (daily.getProperty("PresentBy").equalsIgnoreCase("Month"))

			{
				grainByDay_PresentByMonth(daily.getProperty("monthsLimit"));
				pm.DataGroup.add_Daily_NetSales();
				pm.DataGroup.add_Daily_GuestCount();
				pm.DataGroup.add_Daily_AverageCheck();
				pm.DataGroup.add_Daily_LaborCost();
				pm.DataGroup.add_Daily_LaborHours();
				//pm.DataGroup.add_Daily_Cost();
				pm.DataGroup.add_Daily_PrimeCost();
				pm.DataGroup.add_Daily_GrossSales();

			} else if (daily.getProperty("PresentBy").equalsIgnoreCase("EntireRange"))

			{
				grainByDay_PresentByER();
				pm.DataGroup.add_Daily_NetSales();
				pm.DataGroup.add_Daily_GuestCount();
				pm.DataGroup.add_Daily_AverageCheck();
				pm.DataGroup.add_Daily_LaborCost();
				pm.DataGroup.add_Daily_LaborHours();
				//pm.DataGroup.add_Daily_Cost();
				pm.DataGroup.add_Daily_PrimeCost();
				pm.DataGroup.add_Daily_GrossSales();
			}

		}

		else if (DataGrain.equalsIgnoreCase("Weekly")) {

			if (weekly.getProperty("PresentBy").equalsIgnoreCase("Week"))

			{
				grainByWeek_PresentByWeek(weekly.getProperty("weeksLimit"));
				pm.DataGroup.add_Weekly_NetSales();
				pm.DataGroup.add_Weekly_GuestCount();
				pm.DataGroup.add_Weekly_AverageCheck();
				pm.DataGroup.add_Weekly_LaborCost();
				pm.DataGroup.add_Weekly_LaborHours();
				//pm.DataGroup.add_Weekly_Cost();
				pm.DataGroup.add_Weekly_PrimeCost();
				pm.DataGroup.add_Weekly_GrossSales();
				

			} else if (weekly.getProperty("PresentBy").equalsIgnoreCase("Month")) {
				grainByWeek_PresentByMonth(weekly.getProperty("monthLimit"));
				pm.DataGroup.add_Weekly_NetSales();
				pm.DataGroup.add_Weekly_GuestCount();
				pm.DataGroup.add_Weekly_AverageCheck();
				pm.DataGroup.add_Weekly_LaborCost();
				pm.DataGroup.add_Weekly_LaborHours();
				//pm.DataGroup.add_Weekly_Cost();
				pm.DataGroup.add_Weekly_PrimeCost();
				pm.DataGroup.add_Weekly_GrossSales();
				

			} else if (weekly.getProperty("PresentBy").equalsIgnoreCase("EntireRange")) {
				grainByWeek_PresentByER();
				pm.DataGroup.add_Weekly_NetSales();
				pm.DataGroup.add_Weekly_GuestCount();
				pm.DataGroup.add_Weekly_AverageCheck();
				pm.DataGroup.add_Weekly_LaborCost();
				pm.DataGroup.add_Weekly_LaborHours();
				//pm.DataGroup.add_Weekly_Cost();
				pm.DataGroup.add_Weekly_PrimeCost();
				pm.DataGroup.add_Weekly_GrossSales();
				

			}

		}

		if (DataGrain.equalsIgnoreCase("Monthly")) {

			if (monthly.getProperty("PresentBy").equalsIgnoreCase("Month"))

			{
				grainByMonth_PresentByMonth(monthly.getProperty("monthsLimit"));
				pm.DataGroup.add_Weekly_NetSales();
				pm.DataGroup.add_Weekly_GuestCount();
				pm.DataGroup.add_Weekly_AverageCheck();
				pm.DataGroup.add_Weekly_LaborCost();
				pm.DataGroup.add_Weekly_LaborHours();
				//pm.DataGroup.add_Weekly_Cost();
				pm.DataGroup.add_Weekly_PrimeCost();
				pm.DataGroup.add_Weekly_GrossSales();
				

			} else if (monthly.getProperty("PresentBy").equalsIgnoreCase("EntireRange")) {
				grainByMonth_PresentByER();
				pm.DataGroup.add_Weekly_NetSales();
				pm.DataGroup.add_Weekly_GuestCount();
				pm.DataGroup.add_Weekly_AverageCheck();
				pm.DataGroup.add_Weekly_LaborCost();
				pm.DataGroup.add_Weekly_LaborHours();
				//pm.DataGroup.add_Weekly_Cost();
				pm.DataGroup.add_Weekly_PrimeCost();
				pm.DataGroup.add_Weekly_GrossSales();
				

			}

		}

	}

	public void selectGroupingHierarchy() {

		scrollToElement(btn_byHierarchy);
		scrollToElement(btn_byRegion);
		scrollToElement(btn_RegionSubtotal);
		scrollToElement(btn_byDistrict);
		scrollToElement(btn_byDistrictSubtotal);
		log.info("Reportgroup hirarchy checkbox selected");

	}

	public void saveCreatedReport() {

		scrollToElement(btn_CreateReport);
		log.info("Report has been saved and created");

	}

	public ArrayList<String> validateListOfAddedColumns() throws InterruptedException {

		Thread.sleep(2000);
		//waitUntilClickable(btn_listOfColumns);
		justscrollToElement(txt_nameOfReport);
		Thread.sleep(1000);
		btn_listOfColumns.click();	
		Thread.sleep(2000);
		ArrayList<String> listOfColumns = new ArrayList<String>();
		List<WebElement> fields = driver.findElements(By.xpath("//div[@class='drag-item ng-star-inserted']"));
		int totalelementsfind = fields.size();
		for (int i = 0; i < totalelementsfind; i++) {

			WebElement Addedcolumns=fields.get(i);
			justscrollToElement(Addedcolumns);
			String AddedColumnName = Addedcolumns.getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");
			listOfColumns.add(i, AddedColumnName);

			log.info("No of Added columns" + listOfColumns.size());

		}
		txt_OkBtn.click();

		log.info("Retirning list of added columns after verify");

		return listOfColumns;

	}

}
