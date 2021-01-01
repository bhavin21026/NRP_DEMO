package nrp_CustomKeywords;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;

public class DateVerificationKeywords_NRP extends BaseNRP {

	public DateVerificationKeywords_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 100);
		PageFactory.initElements(driver, this);

	}

		public ArrayList<String> verifyDatesOnReport(String DataGrain) throws ParseException {
		ArrayList<String> TotalDays = new ArrayList<String>();
		ArrayList<String> DayWiseDate = new ArrayList<String>();
		ArrayList<String> NoWeeks = new ArrayList<String>();
		String[] OnlyDays;

		if (DataGrain.equalsIgnoreCase("Daily"))

		{

		
		if (daily.getProperty("PresentBy").equalsIgnoreCase("Day")) {

			WebElement HeaderRow = driver.findElement(By.xpath("(//thead[@class=\"ui-table-thead\"])[2]"));

			List<WebElement> Headers = HeaderRow.findElements(By.tagName("tr"));

			List<WebElement> Days = Headers.get(0).findElements(By.tagName("th"));

			int NoOfdaysSelected = Days.size();

			for (int i = 0; i < NoOfdaysSelected; i++) {

				WebElement DateElement = Days.get(i);
				justscrollToElement(DateElement);

				String FirstDate = DateElement.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n",
						" ");

				TotalDays.add(FirstDate);

				String dateToBeSplited = TotalDays.get(i);

				OnlyDays = dateToBeSplited.split("/");

				String FinalDay = OnlyDays[1].replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

				DayWiseDate.add(FinalDay);
				
			}
			
			log.info("Total no of days stored"+ DayWiseDate);


			int daySize = TotalDays.size();

			String FirstDate = TotalDays.get(0);
			verifyStartDateOnReport(FirstDate);

			String EndDate = null;

			if (TotalDays.size() > 1) {

				int LastDate = (TotalDays.size() - 1);

				EndDate = TotalDays.get(LastDate);

				verifyEndDateOnReport(EndDate);

			}

			String DayLimit = daily.getProperty("daysLimit");

			String diffrence = givenTwoDates(FirstDate, EndDate);

			if (DayLimit.equalsIgnoreCase(diffrence)) {

				assert true;
			}
		} else if (daily.getProperty("PresentBy").equalsIgnoreCase("Week")) {

			WebElement HeaderRow1 = driver.findElement(By.xpath("(//thead[@class=\"ui-table-thead\"])[2]"));

			List<WebElement> Headers1 = HeaderRow1.findElements(By.tagName("tr"));

			List<WebElement> weeks = Headers1.get(0).findElements(By.tagName("th"));

			int NoOfWeekSelected = weeks.size();

			for (int i = 0; i < NoOfWeekSelected; i++) {

				WebElement DateWeekElement = weeks.get(i);

				justscrollToElement(DateWeekElement);
				String FirstWeek = DateWeekElement.getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				NoWeeks.add(FirstWeek);

			}

			String FirstWeekDate = NoWeeks.get(0);
			verifyStartWeekDateOnReport(FirstWeekDate);

			if (NoWeeks.size() > 1) {
				int LastDateCnt = (NoWeeks.size() - 1);

				String LastDateWeek = NoWeeks.get(LastDateCnt);

				verifyEndWeekDateOnReport(LastDateWeek);

			}

			double noOfweesdiff = noOfWeeksBasedOnDate();
			String weekDiff = String.valueOf(noOfweesdiff);
			String WeekLimit = daily.getProperty("weekssLimit");

			if (WeekLimit.equalsIgnoreCase(weekDiff)) {
				assert true;
			}

		} else if (daily.getProperty("PresentBy").equalsIgnoreCase("Month")) {

			assert true;
		}
		}

		return DayWiseDate;

	}

	public void verifyStartDateOnReport(String Date) {
		
		System.out.println("StartDate"+Date);

		String Month = Date.substring(0, 2);
		String Day = Date.substring(3, 5);
		String Year = Date.substring(6, 10);

		String enteredDate = daily.getProperty("stCalStartDate");
		String enteredEndDate = daily.getProperty("stCalEndDate");
		String enteredYear = daily.getProperty("stCalStartMonthYear");

		String startYear = enteredYear.substring(5, 9);

		System.out.println("StartYear"+startYear);

		if (Day.equalsIgnoreCase(enteredDate) && Year.equals(startYear)) {
			
			log.info("Start Date same as expected");

			assert true;
		}

	}

	public void verifyEndDateOnReport(String Date) {

		System.out.println("EndDate"+Date);
		
		String Month = Date.substring(0, 2);
		String Day = Date.substring(3, 5);
		String Year = Date.substring(6, 10);

		String enteredDate = daily.getProperty("stCalStartDate");
		String enteredEndDate = daily.getProperty("stCalEndDate");
		String enteredYear = daily.getProperty("stCalEndDate");
		String enteredEndYear = daily.getProperty("stCalEndMonthYear");

		String endYear = enteredEndYear.substring(5, 9);

		System.out.println("EndYear"+endYear);

		if (Day.equalsIgnoreCase(enteredEndDate) && Year.equals(endYear)) {

			log.info("End Date same as expected");
			assert true;
		}

	}

	public void verifyStartWeekDateOnReport(String StartWeekDate) throws ParseException {

		System.out.println("Start Week"+StartWeekDate);

		String StartingWeek = getWeekEndDateOfFirstWeek();

		if (StartWeekDate.equalsIgnoreCase(StartingWeek)) {

			log.info("Start Week same as expected");

			assert true;
		}
	}

	public void verifyEndWeekDateOnReport(String EndWeekDate) throws ParseException {

		System.out.println("End Week"+EndWeekDate);

		String EndingWeek = getWeekEndDateOfLastWeek();

		if (EndWeekDate.equalsIgnoreCase(EndingWeek)) {

			log.info("End Week same as expected");
			assert true;
		}
	}

	public String getWeekEndDateOfFirstWeek() throws ParseException

	{

		String start = daily.getProperty("stCalStartMonthYear"); // Apr, 2019

		String dates = daily.getProperty("stCalStartDate");

		String firstMonth1 = start.substring(0, 3);

		Date date = new SimpleDateFormat("MMMM").parse(firstMonth1);

		Calendar cal1 = Calendar.getInstance();

		cal1.setTime(date);

		int finalMonth2 = cal1.get(Calendar.MONTH);

		int firstMonth = (finalMonth2 + 1);

		String firstYear1 = start.substring(5, 9);

		int firstYear = Integer.parseInt(firstYear1);

		String findingDateSecond1 = dates;
		int findingDateOne = Integer.parseInt(findingDateSecond1);

		// LocalDate date1 = LocalDate.of(firstYear, firstMonth, findingDateOne)

		LocalDate date1 = LocalDate.of(firstYear, firstMonth, findingDateOne);

		java.time.DayOfWeek dayOfWeek = date1.getDayOfWeek();
		// System.out.println(date1 + " was a " + dayOfWeek);
		LocalDate firstWorkingDay = null;
		long six = 6;
		long five = 5;
		long four = 4;
		long three = 3;
		long two = 2;
		long one = 1;
		long zero = 0;

		// Using DayOfWeek to execute dependent business logic
		switch (dayOfWeek) {

		case SUNDAY:
			firstWorkingDay = date1.plusDays(six);
			break;
		case MONDAY:
			firstWorkingDay = date1.plusDays(five);
			break;
		case TUESDAY:
			firstWorkingDay = date1.plusDays(four);
			break;
		case WEDNESDAY:
			firstWorkingDay = date1.plusDays(three);
			break;
		case THURSDAY:
			firstWorkingDay = date1.plusDays(two);
			break;
		case FRIDAY:
			firstWorkingDay = date1.plusDays(one);
			break;
		case SATURDAY:
			firstWorkingDay = date1.plusDays(zero);
			break;

		}
		System.out.println("Last Day of first week " + firstWorkingDay);

		Date WeekEnddate = java.sql.Date.valueOf(firstWorkingDay);
		DateFormat df = new SimpleDateFormat("MM-dd");

		String FirstWeekEnd = df.format(WeekEnddate);

		String FirstWeekEndNew = "WE" + " " + FirstWeekEnd;

		System.out.println("First weekend date"+FirstWeekEndNew);

		return FirstWeekEndNew;

	}

	public String getWeekEndDateOfLastWeek() throws ParseException

	{

		String start = daily.getProperty("stCalEndMonthYear"); // Apr, 2019

		String dates = daily.getProperty("stCalEndDate");

		String firstMonth1 = start.substring(0, 3);

		Date date = new SimpleDateFormat("MMMM").parse(firstMonth1);

		Calendar cal1 = Calendar.getInstance();

		cal1.setTime(date);

		int finalMonth2 = cal1.get(Calendar.MONTH);

		int firstMonth = (finalMonth2 + 1);

		String firstYear1 = start.substring(5, 9);

		int firstYear = Integer.parseInt(firstYear1);

		String findingDateSecond1 = dates;
		int findingDateOne = Integer.parseInt(findingDateSecond1);

		// LocalDate date1 = LocalDate.of(firstYear, firstMonth, findingDateOne)

		LocalDate date1 = LocalDate.of(firstYear, firstMonth, findingDateOne);

		java.time.DayOfWeek dayOfWeek = date1.getDayOfWeek();
		// System.out.println(date1 + " was a " + dayOfWeek);
		LocalDate firstWorkingDay = null;
		long six = 6;
		long five = 5;
		long four = 4;
		long three = 3;
		long two = 2;
		long one = 1;
		long zero = 0;

		// Using DayOfWeek to execute dependent business logic
		switch (dayOfWeek) {

		case SUNDAY:
			firstWorkingDay = date1.plusDays(six);
			break;
		case MONDAY:
			firstWorkingDay = date1.plusDays(five);
			break;
		case TUESDAY:
			firstWorkingDay = date1.plusDays(four);
			break;
		case WEDNESDAY:
			firstWorkingDay = date1.plusDays(three);
			break;
		case THURSDAY:
			firstWorkingDay = date1.plusDays(two);
			break;
		case FRIDAY:
			firstWorkingDay = date1.plusDays(one);
			break;
		case SATURDAY:
			firstWorkingDay = date1.plusDays(zero);
			break;

		}
		System.out.println("Last Day of first week " + firstWorkingDay);

		Date WeekEnddate = java.sql.Date.valueOf(firstWorkingDay);
		DateFormat df = new SimpleDateFormat("MM-dd");

		String FirstWeekEnd = df.format(WeekEnddate);

		String LastWeekEndNew = "WE" + " " + FirstWeekEnd;

		System.out.println("Last weekend date"+LastWeekEndNew);

		return LastWeekEndNew;

	}

	public String givenTwoDates(String Date1, String Date2) throws ParseException {

		String pattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date Startdate = formatter.parse(Date1);
		Date Enddate = formatter.parse(Date2);

		long duration = Enddate.getTime() - Startdate.getTime();
		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

		long Difference = diffInDays + 1;
		String DiffofWeek = String.valueOf(Difference);
		return DiffofWeek;
	}

	public double noOfWeeksBasedOnDate() throws ParseException {

		String enteredDate = daily.getProperty("stCalStartDate");
		String enteredEndDate = daily.getProperty("stCalEndDate");
		String enteredYear = daily.getProperty("stCalEndDate");
		String enteredEndYear = daily.getProperty("stCalEndMonthYear");

		String start = daily.getProperty("stCalStartMonthYear"); // Apr, 2019
		String End = daily.getProperty("stCalEndMonthYear");
		String dates = daily.getProperty("stCalEndMonthYear"); // Apr 28 - May 25

		String firstMonth1 = start.substring(0, 3);

		Date date = new SimpleDateFormat("MMMM").parse(firstMonth1);

		Calendar cal1 = Calendar.getInstance();

		cal1.setTime(date);

		int finalMonth2 = cal1.get(Calendar.MONTH);

		int firstMonth = (finalMonth2 + 1);

		String firstYear1 = start.substring(5, 9);
		int firstYear = Integer.parseInt(firstYear1);

		String secondMonth1 = End.substring(0, 3);
		Date secondMonthDate = new SimpleDateFormat("MMMM").parse(secondMonth1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(secondMonthDate);
		int finalMonth3 = cal2.get(Calendar.MONTH);
		int secondMonth = (finalMonth3 + 1);

		String secondYear1 = End.substring(5, 9);
		int secondYear = Integer.parseInt(secondYear1);

		String findingDateOne1 = dates.substring(4, 6);
		int findingDateOne = Integer.parseInt(findingDateOne1);

		String findingDateSecond1 = dates.substring(13, 15);
		int findingDateSecond = Integer.parseInt(findingDateSecond1);

		LocalDate date1 = LocalDate.of(firstYear, firstMonth, findingDateOne);

		LocalDate date2 = LocalDate.of(secondYear, secondMonth, findingDateSecond);

		double diffInDays1 = ChronoUnit.DAYS.between(date1, date2);
		double diffInDays = (diffInDays1 + 1);
		double weeks1 = (diffInDays / 7.0);

		// If you want to include the fraction, replace long with double and 7 with 7.0
		System.out.println((((("Weeks between " + date1) + " and ") + date2) + ": ") + weeks1);

		return weeks1;

	}

}
