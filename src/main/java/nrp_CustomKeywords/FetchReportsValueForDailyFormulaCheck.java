package nrp_CustomKeywords;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;
import nrp_utilities.WriteExcelDailyFormula;

public class FetchReportsValueForDailyFormulaCheck extends BaseNRP {

	public FetchReportsValueForDailyFormulaCheck(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		waitMore = new WebDriverWait(driver, 200);

		PageFactory.initElements(driver, this);

	}

	public HashMap<String, Object> fetchVariablevaluesFromReport(String DataGrain) throws IOException, ParseException {

		String StoreName = null;
		String user = null;
		String PresentBy = null;
		int index = 0;
		int NewIndex = 0;
		String[] writeData;
		WriteExcelDailyFormula we = new WriteExcelDailyFormula();
		HashMap<String, Object> ValuesFromReport = new HashMap<String, Object>();

		if (DataGrain.equalsIgnoreCase("Daily"))

		{
			StoreName = daily.getProperty("PC");
			PresentBy = daily.getProperty("PresentBy");
			user = setup.getProperty("username");

		} else if (DataGrain.equalsIgnoreCase("Weekly"))

		{

			StoreName = weekly.getProperty("PC");
			PresentBy = weekly.getProperty("PresentBy");
			user = setup.getProperty("username");

		} else if (DataGrain.equalsIgnoreCase("Monthly"))

		{

			StoreName = monthly.getProperty("PC");
			PresentBy = monthly.getProperty("PresentBy");
			user = setup.getProperty("username");

		}

		if (user.equalsIgnoreCase("admin.insight360@analytix.com")) {

			WebElement Tabel = driver.findElement(By.xpath("(//tbody[@class=\"ui-table-tbody\"])[2]"));

			List<WebElement> Rows = Tabel.findElements(By.tagName("tr"));

			for (int j = 0; j < Rows.size(); j++) {

				WebElement VariableRow = Rows.get(j);

				List<WebElement> cells = VariableRow.findElements(By.tagName("td"));

				String Variablename = cells.get(0).getText();

				if (Variablename.trim().length() >= 7) {
					String gotStorename = Variablename.trim();

					if (gotStorename.equalsIgnoreCase(StoreName)) {

						NewIndex = j;

						break;
					}
				}
			}
		}

		else {

			WebElement Tabel = driver.findElement(By.xpath("(//tbody[@class=\"ui-table-tbody\"])[2]"));

			List<WebElement> Rows = Tabel.findElements(By.tagName("tr"));

			for (int j = 0; j < Rows.size(); j++) {

				WebElement VariableRow = Rows.get(j);

				List<WebElement> cells = VariableRow.findElements(By.tagName("td"));

				String Variablename = cells.get(0).getText();

				if (Variablename.trim().length() > 8) {
					String gotStorename = Variablename.substring(11, Variablename.length()).trim();

					if (gotStorename.equalsIgnoreCase(StoreName)) {

						NewIndex = j;

						break;
					}
				}
			}
		}

		WebElement HeaderRow = driver.findElement(By.xpath("(//thead[@class=\"ui-table-thead\"])[2]"));
		int Headersssize = 0;
		List<WebElement> Headers = null;

		if (PresentBy.equalsIgnoreCase("Day") || PresentBy.equalsIgnoreCase("Week")
				|| PresentBy.equalsIgnoreCase("Month")) {

			List<WebElement> HeadersRowTotal = HeaderRow.findElements(By.tagName("tr"));

			int rowsize = HeadersRowTotal.size();

			Headers = HeadersRowTotal.get(1).findElements(By.tagName("th"));
			Headersssize = Headers.size();

		} else if (PresentBy.equalsIgnoreCase("EntireRange"))

		{

			List<WebElement> HeadersRowTotal = HeaderRow.findElements(By.tagName("tr"));

			int rowsize = HeadersRowTotal.size();

			Headers = HeadersRowTotal.get(0).findElements(By.tagName("th"));
			Headersssize = Headers.size();
		}

		for (int i = 0; i < Headersssize; i++) {

			WebElement HeaderNameElement = Headers.get(i);
			justscrollToElement(HeaderNameElement);

			String HeaderName = HeaderNameElement.getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");

			index = i;

			WebElement Tabel2 = driver.findElement(By.xpath("(//tbody[@class=\"ui-table-tbody\"])[4]"));

			List<WebElement> Rows2 = Tabel2.findElements(By.tagName("tr"));

			WebElement RequiredRow = Rows2.get(NewIndex);

			List<WebElement> cellsNew = RequiredRow.findElements(By.tagName("td"));

			switch (HeaderName) {

			case "Net Sales $":

				log.info("FETCHING NET SALES TY VALUE FROM REPORT FOR FUTURE CALCULATION");

				String NetsalesReport = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, NetsalesReport, "-" };
				we.writeRawValues(writeData, DataGrain);

				double NetSales = removeCurrencyFormat(NetsalesReport);

				log.info(NetSales);

				ValuesFromReport.put("Net Sales $", NetSales);

				break;

			case "Net Sales LY $":

				log.info("FETCHING NET SALES LY $ VALUE FROM REPORT FOR FURUTE CALCULATION");

				String NetsalesLYReport = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, NetsalesLYReport, "-" };
				we.writeRawValues(writeData, DataGrain);

				double NetSalesLY = removeCurrencyFormat(NetsalesLYReport);

				log.info(NetSalesLY);

				ValuesFromReport.put("Net Sales LY $", NetSalesLY);

				break;

			case "Net Sales vs LY $":

				log.info("FETCHING NET SALES VS LY $ VALUE FROM REPORT FOR FURUTE CALCULATION");

				String Netsales_VS_LYReport = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, Netsales_VS_LYReport, "-" };
				we.writeRawValues(writeData, DataGrain);

				double NetSalesLY_vs_LY = removeCurrencyFormat(Netsales_VS_LYReport);

				log.info(NetSalesLY_vs_LY);

				ValuesFromReport.put("Net Sales vs LY $", NetSalesLY_vs_LY);

				break;

			case "Net Sales vs LY %":

				log.info("FETCHING NET SALES VS LY % VALUE FROM REPORT FOR FURUTE CALCULATION");

				String Netsales_VS_LY_Percent_Report = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, Netsales_VS_LY_Percent_Report, "-" };
				we.writeRawValues(writeData, DataGrain);

				double Netsales_VS_LY_Percent = setNumberFormat(Netsales_VS_LY_Percent_Report);

				log.info(Netsales_VS_LY_Percent);

				ValuesFromReport.put("Net Sales vs LY %", Netsales_VS_LY_Percent);

				break;

			case "Sales Budget $":

				log.info("FETCHING  Sales Budget $  VALUE FROM REPORT FOR FUTURE CALCULATION");

				String SalesBudgetDollar = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, SalesBudgetDollar, "-" };
				we.writeRawValues(writeData, DataGrain);

				double SalesBudgetDollars = removeCurrencyFormat(SalesBudgetDollar);

				log.info(SalesBudgetDollars);

				ValuesFromReport.put("Sales Budget $", SalesBudgetDollars);

				break;

			case "Sales Budget %":

				log.info("FETCHING Sales Budget % VALUE FROM REPORT FOR FUTURE CALCULATION");

				String NetsalesBudgetPercent = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, NetsalesBudgetPercent, "-" };
				we.writeRawValues(writeData, DataGrain);

				double NetSalesBudget = setNumberFormat(NetsalesBudgetPercent);

				log.info(NetSalesBudget);

				ValuesFromReport.put("Sales Budget %", NetSalesBudget);

				break;

			case "Sales vs Budget $":

				log.info("FETCHING Sales vs Budget $ VALUE FROM REPORT FOR FUTURE CALCULATION");

				String NetsalesVSBudgetDollar = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, NetsalesVSBudgetDollar, "-" };
				we.writeRawValues(writeData, DataGrain);

				double NetSalesVsBudget = removeCurrencyFormat(NetsalesVSBudgetDollar);

				log.info(NetSalesVsBudget);

				ValuesFromReport.put("Sales vs Budget $", NetSalesVsBudget);

				break;

			case "Sales vs Budget %":

				log.info("FETCHING Sales vs Budget % VALUE FROM REPORT FOR FUTURE CALCULATION");

				String NetsalesVSBudgetPercentage = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, NetsalesVSBudgetPercentage, "-" };
				we.writeRawValues(writeData, DataGrain);

				double NetSalesVsBudgetPercent = setNumberFormat(NetsalesVSBudgetPercentage);

				log.info(NetSalesVsBudgetPercent);

				ValuesFromReport.put("Sales vs Budget %", NetSalesVsBudgetPercent);

				break;

			case "Guest Count":

				log.info("FETCHING GUEST COUNT  FROM CALENDAR FOR FUTURE CALCULATION");

				String GuestCountReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, GuestCountReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double GuestCountReport = setNumberFormat(GuestCountReport1);

				log.info(GuestCountReport);

				ValuesFromReport.put("Guest Count", GuestCountReport);

				break;

			case "Guest LY Count":

				log.info("FETCHING GUEST COUNT LY FROM CALENDAR FOR FUTURE CALCULATION");

				String GuestCountLYReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, GuestCountLYReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double GuestCountLYReport = setNumberFormat(GuestCountLYReport1);

				log.info(GuestCountLYReport);

				ValuesFromReport.put("Guest LY Count", GuestCountLYReport);

				break;

			case "Guest vs LY Count":

				log.info("FETCHING GUEST COUNT VS LY COUNT FROM CALENDAR FOR FUTURE CALCULATION");

				String GuestCount_VS_LY_COUNT_Report1 = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, GuestCount_VS_LY_COUNT_Report1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double GuestCount_VS_LY_COUNT_Report = setNumberFormat(GuestCount_VS_LY_COUNT_Report1);

				log.info(GuestCount_VS_LY_COUNT_Report);

				ValuesFromReport.put("Guest vs LY Count", GuestCount_VS_LY_COUNT_Report);

				break;

			case "Guest vs LY %":

				log.info("FETCHING GUEST COUNT VS LY % COUNT FROM CALENDAR FOR FUTURE CALCULATION");

				String GuestCount_VS_LY_PERCENT_Report = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, GuestCount_VS_LY_PERCENT_Report, "-" };
				we.writeRawValues(writeData, DataGrain);

				double GuestCount_VS_LY_PERCENT = setNumberFormat(GuestCount_VS_LY_PERCENT_Report);

				log.info(GuestCount_VS_LY_PERCENT);

				ValuesFromReport.put("Guest vs LY %", GuestCount_VS_LY_PERCENT);

				break;

			case "Avg Check $":

				log.info("FETCHING AVG CHECK VALUE FROM REPORT FOR FUTURE CALCULATION");

				String AvgCheckReport = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, AvgCheckReport, "-" };
				we.writeRawValues(writeData, DataGrain);

				double AvgCheckDollor = removeCurrencyFormat(AvgCheckReport);

				log.info(AvgCheckDollor);

				ValuesFromReport.put("Avg Check $", AvgCheckDollor);

				break;

			case "Avg Check LY $":

				log.info("FETCHING AVG CHECK VALUE LY FROM REPORT FOR FUTURE CALCULATION");

				String AvgCheckLYReport = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, AvgCheckLYReport, "-" };
				we.writeRawValues(writeData, DataGrain);

				double AvgCheckLY = removeCurrencyFormat(AvgCheckLYReport);

				log.info(AvgCheckLY);

				ValuesFromReport.put("Avg Check LY $", AvgCheckLY);

				break;

			case "Avg Check vs LY $":

				log.info("FETCHING AvgCheck_VS_LYReport FROM REPORT FOR FUTURE CALCULATION");

				String AvgCheck_VS_LYReport = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, AvgCheck_VS_LYReport, "-" };
				we.writeRawValues(writeData, DataGrain);

				double AvgCheck_VS_LY = removeCurrencyFormat(AvgCheck_VS_LYReport);

				log.info(AvgCheck_VS_LY);

				ValuesFromReport.put("Avg Check vs LY $", AvgCheck_VS_LY);
				break;

			case "Avg Check vs LY %":

				log.info("FETCHING AvgCheckvsLY_PERCENT FROM REPORT FOR FUTURE CALCULATION");

				String AvgCheckvsLY_PERCENT = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");
				writeData = new String[] { HeaderName, AvgCheckvsLY_PERCENT, "-" };
				we.writeRawValues(writeData, DataGrain);

				double AvgCheckvs_LY_PERCENTEGE = setNumberFormat(AvgCheckvsLY_PERCENT);

				log.info(AvgCheckvs_LY_PERCENTEGE);

				ValuesFromReport.put("Avg Check vs LY %", AvgCheckvs_LY_PERCENTEGE);

				break;

			case "Labor Cost, $":

				log.info("FETCHING Labor Cost, $  FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_$ = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_$, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_$_Amount = removeCurrencyFormat(LaborCost_$);

				log.info(LaborCost_$_Amount);

				ValuesFromReport.put("Labor Cost, $", LaborCost_$_Amount);

				break;

			case "Labor Cost, %":

				log.info("FETCHING Labor Cost, %  FROM REPORT FOR FUTURE CALCULATION");

				String LaborCostPercent = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCostPercent, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_Percent = setNumberFormat(LaborCostPercent);

				log.info(LaborCost_Percent);

				ValuesFromReport.put("Labor Cost, %", LaborCost_Percent);
				break;

			case "Labor Cost LY, $":

				log.info("FETCHING Labor Cost LY, $  FROM REPORT FOR FUTURE CALCULATION");

				String LaborCostLY$ = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCostLY$, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_LY$ = removeCurrencyFormat(LaborCostLY$);

				log.info(LaborCost_LY$);

				ValuesFromReport.put("Labor Cost LY, $", LaborCost_LY$);
				break;

			case "Labor Cost LY, %":

				log.info("FETCHING Labor Cost LY, % FROM REPORT FOR FUTURE CALCULATION");

				String LaborCostLYPercent = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCostLYPercent, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCostLY_Percent = setNumberFormat(LaborCostLYPercent);

				log.info(LaborCostLY_Percent);

				ValuesFromReport.put("Labor Cost LY, %", LaborCostLY_Percent);

				break;

			case "Labor Cost vs LY, $":

				log.info("FETCHING Labor Cost vs LY, $ FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_VS_LY_$ = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_VS_LY_$, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_VS_LY = removeCurrencyFormat(LaborCost_VS_LY_$);

				log.info(LaborCost_VS_LY);

				ValuesFromReport.put("Labor Cost vs LY, $", LaborCost_VS_LY);
				break;

			case "Labor Cost vs LY, p.p.":

				log.info("FETCHING Labor Cost vs LY, p.p. FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_VS_LY_PP1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_VS_LY_PP1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_VS_LY_PP = setNumberFormat(LaborCost_VS_LY_PP1);

				log.info(LaborCost_VS_LY_PP);

				ValuesFromReport.put("Labor Cost vs LY, p.p.", LaborCost_VS_LY_PP);

				break;

			case "Labor Cost LW, $":

				log.info("FETCHING Labor Cost LW, $ FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_LW_$ = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_LW_$, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_LW_$_Report = removeCurrencyFormat(LaborCost_LW_$);

				log.info(LaborCost_LW_$_Report);

				ValuesFromReport.put("Labor Cost LW, $", LaborCost_LW_$_Report);
				break;

			case "Labor Cost LW, %":

				log.info("FETCHING Labor Cost LW, % FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_LW_Percent = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_LW_Percent, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_LW = setNumberFormat(LaborCost_LW_Percent);

				log.info(LaborCost_LW);

				ValuesFromReport.put("Labor Cost LW, %", LaborCost_LW);

				break;

			case "Labor Cost vs LW, $":

				log.info("FETCHING Labor Cost vs LW, $ FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_VS_LW_$ = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_VS_LW_$, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_VS_LW_$_report = removeCurrencyFormat(LaborCost_VS_LW_$);

				log.info(LaborCost_VS_LW_$_report);

				ValuesFromReport.put("Labor Cost vs LW, $", LaborCost_VS_LW_$_report);
				break;

			case "Labor Cost vs LW, p.p.":

				log.info("FETCHING Labor Cost vs LW, p.p. FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_VS_LW_PP1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_VS_LW_PP1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_VS_LW_PP = setNumberFormat(LaborCost_VS_LW_PP1);

				log.info(LaborCost_VS_LW_PP);

				ValuesFromReport.put("Labor Cost vs LW, p.p.", LaborCost_VS_LW_PP);

				break;

			case "Labor Cost Budget, %":

				log.info("FETCHING Labor Cost Budget, % FROM REPORT FOR FUTURE CALCULATION");

				String LaborCostBudgetPercent = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCostBudgetPercent, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCostBudgetPercentage = setNumberFormat(LaborCostBudgetPercent);

				log.info(LaborCostBudgetPercentage);

				ValuesFromReport.put("Labor Cost Budget, %", LaborCostBudgetPercentage);

				break;

			case "Labor Cost Budget, $ (actual sales)":

				log.info("FETCHING LaborCostBudget_$_ActualSales FROM REPORT FOR FUTURE CALCULATION");

				String LaborCostBudget_$_ActualSales = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCostBudget_$_ActualSales, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCostBudget$_ActualSales = removeCurrencyFormat(LaborCostBudget_$_ActualSales);

				log.info(LaborCostBudget$_ActualSales);

				ValuesFromReport.put("Labor Cost Budget, $ (actual sales)", LaborCostBudget$_ActualSales);

				break;

			case "Labor Cost Budget, $ (budgeted sales)":

				log.info("FETCHING  Labor Cost Budget, $ (budgeted sales) FROM REPORT FOR FUTURE CALCULATION");

				String LaborCostBudget_$_BudgetedSales = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCostBudget_$_BudgetedSales, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCostBudget$_BudgetedSales = removeCurrencyFormat(LaborCostBudget_$_BudgetedSales);

				log.info(LaborCostBudget$_BudgetedSales);

				ValuesFromReport.put("Labor Cost Budget, $ (budgeted sales)", LaborCostBudget$_BudgetedSales);

				break;

			case "Labor Cost vs Budget, p.p.":

				log.info("FETCHING  Labor Cost vs Budget, p.p. FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_VS_Budget_PP1 = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_VS_Budget_PP1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_VS_Budget_PP = setNumberFormat(LaborCost_VS_Budget_PP1);

				log.info(LaborCost_VS_Budget_PP);

				ValuesFromReport.put("Labor Cost vs Budget, p.p.", LaborCost_VS_Budget_PP);

				break;

			case "Labor Cost vs Budget, $ (actual sales)":

				log.info("FETCHING  Labor Cost vs Budget, $ (actual sales)  FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_VS_Budget_$_ActualSales = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_VS_Budget_$_ActualSales, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_VSBudget$_ActualSales = removeCurrencyFormat(LaborCost_VS_Budget_$_ActualSales);

				log.info(LaborCost_VSBudget$_ActualSales);

				ValuesFromReport.put("Labor Cost vs Budget, $ (actual sales)", LaborCost_VSBudget$_ActualSales);

				break;

			case "Labor Cost vs Budget, $ (budgeted sales)":

				log.info("FETCHING  Labor Cost vs Budget, $ (actual sales)  FROM REPORT FOR FUTURE CALCULATION");

				String LaborCost_VS_Budget_$_BudgetedSales = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, LaborCost_VS_Budget_$_BudgetedSales, "-" };
				we.writeRawValues(writeData, DataGrain);

				double LaborCost_VSBudget_$_BudgetedSales = removeCurrencyFormat(LaborCost_VS_Budget_$_BudgetedSales);

				log.info(LaborCost_VSBudget_$_BudgetedSales);

				ValuesFromReport.put("Labor Cost vs Budget, $ (budgeted sales)", LaborCost_VSBudget_$_BudgetedSales);

				break;

			case "Gross Sales $":

				log.info("FETCHING Gross Sales $ FROM REPORT FOR FUTURE CALCULATION");

				String GrossSalesDollorReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, GrossSalesDollorReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double GrossSalesDollorReport = removeCurrencyFormat(GrossSalesDollorReport1);

				log.info(GrossSalesDollorReport);

				ValuesFromReport.put("Gross Sales $", GrossSalesDollorReport);

				break;

			case "Gross Sales LY $":

				log.info("FETCHING Gross Sales LY $ FROM REPORT FOR FUTURE CALCULATION");

				String GrossSalesLYDollorReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, GrossSalesLYDollorReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double GrossSalesDollorLYReport = removeCurrencyFormat(GrossSalesLYDollorReport1);

				log.info(GrossSalesDollorLYReport);

				ValuesFromReport.put("Gross Sales LY $", GrossSalesDollorLYReport);

				break;

			case "Gross Sales vs LY $":

				log.info("FETCHING Gross Sales LY $ FROM REPORT FOR FUTURE CALCULATION");

				String GrossSalesvsLYDollorReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, GrossSalesvsLYDollorReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double GrossSalesDollorvsLYReport = removeCurrencyFormat(GrossSalesvsLYDollorReport1);

				log.info(GrossSalesDollorvsLYReport);

				ValuesFromReport.put("Gross Sales vs LY $", GrossSalesDollorvsLYReport);

				break;

			case "Gross Sales vs LY %":

				log.info("FETCHING Gross Sales LY % FROM REPORT FOR FUTURE CALCULATION");

				String GrossSalesLYPercentReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, GrossSalesLYPercentReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double GrossSalesPercentvsLYReport = removeCurrencyFormat(GrossSalesLYPercentReport1);

				log.info(GrossSalesPercentvsLYReport);

				ValuesFromReport.put("Gross Sales vs LY %", GrossSalesPercentvsLYReport);

				break;

			case "Total hours":

				;
				log.info("FETCHING Total hours FROM REPORT FOR FUTURE CALCULATION");

				String TotalHoursReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, TotalHoursReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double TotalHoursReport = removeCurrencyFormat(TotalHoursReport1);

				log.info(TotalHoursReport);

				ValuesFromReport.put("Total hours", TotalHoursReport);

				break;

			case "OT hours":

				log.info("FETCHING OT hours FROM REPORT FOR FUTURE CALCULATION");

				String OTHoursReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, OTHoursReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double OTHoursReport = removeCurrencyFormat(OTHoursReport1);

				log.info(OTHoursReport);

				ValuesFromReport.put("OT hours", OTHoursReport);

				break;

			case "Total hours LY":

				log.info("FETCHING Total hours LY FROM REPORT FOR FUTURE CALCULATION");

				String TotalhoursLYReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, TotalhoursLYReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double TotalhoursLYReport = removeCurrencyFormat(TotalhoursLYReport1);

				log.info(TotalhoursLYReport);

				ValuesFromReport.put("Total hours LY", TotalhoursLYReport);

				break;

			case "OT hours LY":

				log.info("FETCHING OT hours LY FROM REPORT FOR FUTURE CALCULATION");

				String OThoursLYReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, OThoursLYReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double OThoursLYReport = removeCurrencyFormat(OThoursLYReport1);

				log.info(OThoursLYReport);

				ValuesFromReport.put("OT hours LY", OThoursLYReport);

				break;

			case "Total hours vs LY":

				log.info("FETCHING Total hours vs LY FROM REPORT FOR FUTURE CALCULATION");

				String TotalhoursvsLYReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ")
						.replaceAll("\r", " ").replaceAll("\n", " ");

				writeData = new String[] { HeaderName, TotalhoursvsLYReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double TotalhoursvsLYReport = removeCurrencyFormat(TotalhoursvsLYReport1);

				log.info(TotalhoursvsLYReport);

				ValuesFromReport.put("Total hours vs LY", TotalhoursvsLYReport);

				break;

			case "OT hours vs LY":

				log.info("FETCHING OT hours vs LY FROM REPORT FOR FUTURE CALCULATION");

				String OThoursvsLYReport1 = cellsNew.get(index).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
						.replaceAll("\n", " ");

				writeData = new String[] { HeaderName, OThoursvsLYReport1, "-" };
				we.writeRawValues(writeData, DataGrain);

				double OThoursvsLYReport = removeCurrencyFormat(OThoursvsLYReport1);

				log.info(OThoursvsLYReport);

				ValuesFromReport.put("OT hours vs LY", OThoursvsLYReport);

				break;

			default:

				break;

			}

		}

		return ValuesFromReport;

	}

}
