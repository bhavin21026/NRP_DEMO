package nrp_pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;

public class CalendarViewPage_NRP extends BaseNRP {

	public CalendarViewPage_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[text()='Refresh']")
	WebElement btnRefresh;

	@FindBy(xpath = "//input[@role='combobox']")
	public WebElement PCName;

	@FindBy(xpath = "(//button[contains(@class,'btn btn--primary')]//span)[1]")
	WebElement dashBoardButton;

	@FindBy(xpath = "//select[@name='selectPeriodYearCalander']")
	WebElement drp_YearPeriod;

	@FindBy(xpath = "//select[@name='selectPeriodMonthCalander']")
	WebElement drp_Calendar;

	@FindBy(xpath = "//div[@class='daily-actuals__date']//div[1]")
	WebElement div_DailyDateHeader;

	@FindBy(css = "#mdl-dailyActuals > div > div > div.mdl__body.mdl__body--daily-actuals.sidepart.one > div > div.ui-kit-row > div > div > table > thead > tr > th:nth-child(3) > ul > li:nth-child(2) > b")
	WebElement span_Last_Year;

	@FindBy(xpath = "//*[@id=\"mdl-dailyActuals\"]/div/div/div[2]/div/div[1]/div/div/table/thead/tr/th[3]/ul/li[2]/span[1]")
	WebElement btn_toChangeBudget;

	@FindBy(xpath = "//div[@class='mdl__cross w-mdl-close']")
	WebElement btn_closePopUp;

	public void hitRefresh()

	{
		btnRefresh.click();
	}

	public void selectPCName(String PC_Name) throws InterruptedException

	{
		PCName.click();
		PCName.sendKeys(PC_Name);
		PCName.sendKeys(Keys.ENTER);
		log.info("PC name selected");

		isProgressbarDisplayed();
		Thread.sleep(4000);

	}

	public void selectOperationCalendar(String Year, String OperationalCalender) throws InterruptedException

	{
		scrollToElement(dashBoardButton);
		//dashBoardButton.click();
		log.info("dashboard clicked ");
		waitUntilClickable(drp_YearPeriod);
		Thread.sleep(1000);
		drp_YearPeriod.click();
		log.info("Yearperiod dropdown clicked");
		Thread.sleep(1000);
		WebElement yearfiled = driver.findElement(By.xpath("//option[contains(text()," + Year + ")]"));
		yearfiled.click();
		log.info("Calendar Year selected");

		isProgressbarDisplayed();

		waitUntilClickable(drp_Calendar);
		drp_Calendar.click();
		log.info("Operational calendar dropdown clicked");

		Thread.sleep(3000);

		// String OpCal=" "+OperationalCalender+" ";
		// System.out.System.out.println(OpCal);

		WebElement operatinalCal = driver
				.findElement(By.xpath("//option[contains(text(),'" + OperationalCalender + "')]"));
		operatinalCal.click();
		log.info("Operational Calendar selected");

		isProgressbarDisplayed();

	}

	public void waitforRefreshButton() throws InterruptedException

	{
		
		log.info("waiting for progressbar");
		
		log.info("waiting for refresh button to be clickable");
		//waitForProgressbar();
		waitUntilClickable(dashBoardButton);
		Thread.sleep(5000);
		log.info("5 seonds wait over and dashBoardButton clickable");
	}

	public HashMap<String, Object> getDailyvaluesFromCalendarView() throws InterruptedException {

		HashMap<String, String> NetSales = new HashMap<String, String>();
		HashMap<String, String> NetSalesLastYear = new HashMap<String, String>();
		HashMap<String, String> BudgetedSales = new HashMap<String, String>();
		HashMap<String, String> GuestCount = new HashMap<String, String>();
		HashMap<String, String> GuestCountLastYear = new HashMap<String, String>();
		HashMap<String, String> GuestCountBudget = new HashMap<String, String>();
		HashMap<String, String> AvgCheck = new HashMap<String, String>();
		HashMap<String, String> AvgCheckLastYear = new HashMap<String, String>();
		HashMap<String, String> AvgCheckBudget = new HashMap<String, String>();
		HashMap<String, String> Labor = new HashMap<String, String>();
		HashMap<String, String> LaborLastYear = new HashMap<String, String>();
		HashMap<String, String> LaborBudget = new HashMap<String, String>();
		HashMap<String, Object> AllVariables = new HashMap<String, Object>();
		HashMap<String, Object> DateAndValueFromCal = new HashMap<String, Object>();

		// String[] DayWiseDate;
		String[] CalendarDates;
		ArrayList<String> listOfColumns = new ArrayList<String>();
		ArrayList<String> DayWiseDate = new ArrayList<String>();
		// HashMap<String, HashMap<String, String>> DayWiseDate = new HashMap<String,
		// HashMap<String,String>>();

		String DailyDate = null;

		// WebUI.scrollToElement(findTestObject('Page_Restaurant
		// Intranet/Dashboard/btn_DashboardCalender'), 10)
		Thread.sleep(3000);

		WebElement Wrappers = driver.findElement(By.xpath("//div[@class='calendar-wrap dbschema_wraper']"));

		List<WebElement> Calenderrow = Wrappers.findElements(By.xpath(
				"((//div[@class='col-center col-center--chart']))//div[@class='chart-data chart-data--future']"));

		int NoOfcalenderRows = Calenderrow.size();

		System.out.println("No of calender rows are" + NoOfcalenderRows);

		for (int l = 0; l < NoOfcalenderRows; l++) {
			List<WebElement> Row = Calenderrow.get(l)
					.findElements(By.xpath("//div[@class='chart-data chart-data--future']"));

			int NoOfRows = Row.size();

			System.out.println("No of  rows " + NoOfRows);

			WebElement CalenderView = Row.get(l);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			if (l > 6) {

				js.executeScript("arguments[0].scrollIntoView(true);", CalenderView);
				js.executeScript("arguments[0].click();", CalenderView);

			} else {

				js.executeScript("arguments[0].click();", CalenderView);
			}

			// WebElement Row =
			// CalenderViewNew.findElement(By.xpath("//div[starts-with(@class,'chart-data
			// chart-data--future')]"))
			String DateOnCalendar = div_DailyDateHeader.getText();

			CalendarDates = DateOnCalendar.split(",");

			String DailyDates = CalendarDates[1].replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

			DayWiseDate.add(l, DailyDates);

			if (DailyDates.length() == 1) {
				DailyDate = ('0' + DailyDates);
			} else {
				DailyDate = DailyDates;
			}

			// Fetching data From Calender
			Set<String> HeaderOftabel = new HashSet<String>(Arrays.asList("Total"));

			WebElement HeaderRow = driver.findElement(
					By.xpath("//*[@id=\"mdl-dailyActuals\"]/div/div/div[2]/div/div[1]/div/div/table/thead"));

			List<WebElement> Headers = HeaderRow.findElements(By.tagName("th"));

			System.out.println(Headers.size());

			for (int i = 1; i < (Headers.size() - 1); i++) {
				String HeaderName = Headers.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");
				System.out.println(HeaderName);

				if (HeaderOftabel.contains(HeaderName)) {

					int index = i;

					WebElement Tabel = driver.findElement(
							By.xpath("//*[@id=\"mdl-dailyActuals\"]/div/div/div[2]/div/div[1]/div/div/table/tbody"));

					List<WebElement> Rows = Tabel.findElements(By.tagName("tr"));

					for (int j = 0; j < Rows.size(); j++) {
						WebElement VariableRow = Rows.get(j);

						List<WebElement> cells = VariableRow.findElements(By.tagName("td"));

						String Variablename = cells.get(0).getText();

						if (Variablename.equalsIgnoreCase("SALES")) {
							log.info("FETCHING NET SALES TY VALUE FROM CALENDAR FOR DATE:" + DailyDate);

							String NetSalesTY = cells.get(index).getText();

							log.info(NetSalesTY);

							System.out.println(NetSalesTY);

							NetSales.put(DailyDate, NetSalesTY);

							AllVariables.put("NetSalesValues", NetSales);

							// Net Sales with Last Year
							log.info("FETCHING NET SALES LY VALUE FROM CALENDAR FOR DATE:" + DailyDate);

							List<WebElement> NetSalesLYData = cells.get(index + 1).findElements(By.tagName("span"));

							String NetSalesLY = NetSalesLYData.get(0).getText();

							log.info(NetSalesLY);

							System.out.println(NetSalesLY);

							NetSalesLastYear.put(DailyDate, NetSalesLY // Guest Count with Last Year
							);

							AllVariables.put("NetSalesLastYearValues", NetSalesLastYear // GUEST COUNT with Last Year
							// Avg Check with Last Year
							); // Net Sales with Budget
							// Guest with Budget
							// Avg Check With Budget
							/*
							 * }else if (Variablename.equalsIgnoreCase("LABOR")) {
							 * 
							 * log.info("FETCHING LABOR COST TY FROM CALENDAR FOR DATE:" + DailyDate)
							 * System.out.println index List<WebElement> Labordata=
							 * cells.get(index+1).findElements(By.tagName("div")) WebElement
							 * Labordata2=Labordata.get(1).findElements(By.tagName("span")).get(1)
							 * WebElement Labordata3=Labordata2.findElement(By.tagName("span")) String
							 * LaborCostTY=Labordata3.findElement(By.tagName("strong")).getText()
							 * log.info(LaborCostTY)
							 * 
							 * //*[@id="mdl-dailyActuals"]/div/div/div[2]/div/div[1]/div/div/table/tbody/tr[
							 * 4]/td[3]/div[2]/span[2]/span/strong
							 * 
							 * System.out.println(LaborCostTY)
							 * 
							 * Labor.put(DailyDate, LaborCostTY)
							 * 
							 * AllVariables.put("LaborThisYear", Labor)
							 * 
							 * log.info("FETCHING LABOR LY FROM CALENDAR FOR DATE:" + DailyDate)
							 * 
							 * List<WebElement> LabordataLY=
							 * cells.get(index+1).findElements(By.tagName("div")) String
							 * LaborCostLY=LabordataLY.get(2).findElements(By.tagName("span")).get(1).
							 * getText() //WebElement
							 * LabordataLY3=LabordataLY2.findElement(By.tagName("span")) //String
							 * LaborCostLY=LabordataLY3.findElement(By.tagName("strong")).getText()
							 * log.info(LaborCostLY)
							 * 
							 * 
							 * LaborLastYear.put(DailyDate, LaborCostLY)
							 * 
							 * AllVariables.put("LaborLastYear", LaborLastYear)
							 */
						} else if (Variablename.equalsIgnoreCase("GUESTS")) {
							log.info("FETCHING GUEST COUNT TY FROM CALENDAR FOR DATE:" + DailyDate);

							String GuestCountTY = cells.get(index).getText();

							log.info(GuestCountTY);

							System.out.println(GuestCountTY);

							GuestCount.put(DailyDate, GuestCountTY);

							AllVariables.put("GuestCountValues", GuestCount);

							log.info("FETCHING GUEST COUNT LY FROM CALENDAR FOR DATE:" + DailyDate);

							List<WebElement> GuestCountLYData = cells.get(index + 1).findElements(By.tagName("span"));

							String GuestCountLY = GuestCountLYData.get(0).getText();

							log.info(GuestCountLY);

							System.out.println(GuestCountLY);

							GuestCountLastYear.put(DailyDate, GuestCountLY);

							AllVariables.put("GuestCountLastYearValues", GuestCountLastYear);
						} else if (Variablename.equalsIgnoreCase("PPA") || Variablename.equalsIgnoreCase("AVG.CHECK")) {
							log.info("FETCHING AVG CHECK TY FROM CALENDAR FOR DATE:" + DailyDate);

							String AvgCheckTY = cells.get(index).getText();

							log.info(AvgCheckTY);

							System.out.println(AvgCheckTY);

							AvgCheck.put(DailyDate, AvgCheckTY);

							AllVariables.put("AvgCheckValues", AvgCheck);

							log.info("FETCHING AVG CHECK LY FROM CALENDAR FOR DATE:" + DailyDate);

							List<WebElement> AvgCheckLYData = cells.get(index + 1).findElements(By.tagName("span"));

							String AvgCheckLY = AvgCheckLYData.get(0).getText();

							log.info(AvgCheckLY);

							System.out.println(AvgCheckLY);

							AvgCheckLastYear.put(DailyDate, AvgCheckLY);

							AllVariables.put("AvgCheckLastYearValues", AvgCheckLastYear);

							String currentComparisionWith = span_Last_Year.getText();

							if (currentComparisionWith.equalsIgnoreCase("Last Year")) {
								log.info("NOW FETCHING VARIABLE DATA FOR BUDGET FOR SAME PERIOD");

								btn_toChangeBudget.click();
								WebElement Tabe2 = driver.findElement(By.xpath(
										"//*[@id=\"mdl-dailyActuals\"]/div/div/div[2]/div/div[1]/div/div/table/tbody"));

								List<WebElement> Rows2 = Tabe2.findElements(By.tagName("tr"));

								for (int k = 0; k < Rows2.size(); k++) {

									WebElement VariableRowBudget = Rows2.get(k);

									List<WebElement> cellsBudget = VariableRowBudget.findElements(By.tagName("td"));

									String VariablenameBudget = cellsBudget.get(0).getText();

									if (VariablenameBudget.equalsIgnoreCase("SALES")) {
										log.info("FETCHING NET SALES BUDGET FROM CALENDAR FOR DATE:" + DailyDate);

										List<WebElement> NetSalesBudgetedData = cellsBudget.get(index + 1)
												.findElements(By.tagName("span"));

										String NetSalesBudgeted = NetSalesBudgetedData.get(0).getText();

										log.info(NetSalesBudgeted);

										System.out.println(NetSalesBudgeted);

										BudgetedSales.put(DailyDate, NetSalesBudgeted);

										AllVariables.put("BudgetedSalesValues", BudgetedSales);
									} else if (VariablenameBudget.equalsIgnoreCase("GUESTS")) {
										log.info("FETCHING GUEST COUNT BUDGET FROM CALENDAR FOR DATE:" + DailyDate);

										List<WebElement> GuestCountBudgetedData = cellsBudget.get(index + 1)
												.findElements(By.tagName("span"));

										String GuestCountBudgeted = GuestCountBudgetedData.get(0).getText();

										log.info(GuestCountBudgeted);

										System.out.println(GuestCountBudgeted);

										GuestCountBudget.put(DailyDate, GuestCountBudgeted);

										AllVariables.put("GuestCountBudgetValues", GuestCountBudget);
									} else if (VariablenameBudget.equalsIgnoreCase("PPA")
											|| VariablenameBudget.equalsIgnoreCase("AVG.CHECK")) {
										log.info("FETCHING AVG CHECK BUDGET FROM CALENDAR FOR DATE:" + DailyDate);

										List<WebElement> AvgCheckBudgetedData = cellsBudget.get(index + 1)
												.findElements(By.tagName("span"));

										String AvegCheckBudgeted = AvgCheckBudgetedData.get(0).getText();

										log.info(AvegCheckBudgeted);

										System.out.println(AvegCheckBudgeted);

										AvgCheckBudget.put(DailyDate, AvegCheckBudgeted);

										AllVariables.put("AvgCheckBudgetValues", AvgCheckBudget);
									}
								}

								btn_toChangeBudget.click();
							}
						}
					}
				}
			}

			waitUntilClickable(btn_closePopUp);
			btn_closePopUp.click();
			log.info("EXECUTION OF GETTING DATA FROM CALENDAR DASHBOARD FOR DATE" + "  " + DailyDate + " "
					+ "is completed successfully....");
		}

		System.out.println(AllVariables.size());

		HashMap<String, String> valueFromCal = new HashMap<String, String>();

		valueFromCal = (HashMap<String, String>) AllVariables.get("NetSalesValues");

		System.out.println(valueFromCal.size());

		DateAndValueFromCal.put("VariableValues", AllVariables);

		DateAndValueFromCal.put("CalandarValues", DayWiseDate);

		return DateAndValueFromCal;

	}

}
