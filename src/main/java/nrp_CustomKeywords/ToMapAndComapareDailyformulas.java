package nrp_CustomKeywords;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;
import nrp_formulas.AverageCheck_Formula;
import nrp_formulas.GrossSales_Formula;
import nrp_formulas.GuestCount_Formula;
import nrp_formulas.LaborCost_Formula;
import nrp_formulas.LaborHours_Formula;
import nrp_formulas.NetSales_Formula;

public class ToMapAndComapareDailyformulas extends BaseNRP {

	public ToMapAndComapareDailyformulas(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		waitMore = new WebDriverWait(driver, 200);

		PageFactory.initElements(driver, this);

	}

	public void ToMap_And_Comapare_Dailyformulas(HashMap<String, Object> CreatedReportValues, String DataGrain)
			throws IOException {

		String PresentBy = null;
		String Store = "Single";
		String SalesBudgetActualValue = null;
		GuestCount_Formula gf = new GuestCount_Formula();
		NetSales_Formula nf = new NetSales_Formula();
		AverageCheck_Formula ac = new AverageCheck_Formula();
		LaborCost_Formula lf = new LaborCost_Formula();
		LaborHours_Formula lh = new LaborHours_Formula();
		GrossSales_Formula gs = new GrossSales_Formula();
		VerifyingDailyFormulas vf = new VerifyingDailyFormulas();

		log.info("Now mapping and calculation formulas with actual portal values");

		if (DataGrain.equalsIgnoreCase("Daily"))

		{
			PresentBy = daily.getProperty("PresentBy");
			SalesBudgetActualValue = daily.getProperty("SalesBudget");

		} else if (DataGrain.equalsIgnoreCase("Weekly"))

		{

			PresentBy = weekly.getProperty("PresentBy");
			SalesBudgetActualValue = weekly.getProperty("SalesBudget");

		} else if (DataGrain.equalsIgnoreCase("Monthly"))

		{

			PresentBy = monthly.getProperty("PresentBy");
			SalesBudgetActualValue = monthly.getProperty("SalesBudget");

		}

		double dblSalesBudget = Double.parseDouble(SalesBudgetActualValue);

		double NetSalesTY = (double) CreatedReportValues.get("Net Sales $");

		double NetSalesLY = (double) CreatedReportValues.get("Net Sales LY $");

		double NetSalesVSLY = (double) CreatedReportValues.get("Net Sales vs LY $");

		double NetSalesVSLYPercent = (double) CreatedReportValues.get("Net Sales vs LY %");

		double NetSalesBudgetDollars = 0;

		double NetSalesBudgetPercent = 0;
		;

		double SalesVSBudgetDollar = 0;
		;

		double SalesVSBudgetPercent = 0;
		;

		double LaborCostBudgetPercent = 0;
		;

		double LaborCostBudget_ActualSales = 0;
		;

		double LaborCostBudget_BudgetSales = 0;
		;

		double LaborCostBudgetVsBudgetPP_Dollar = 0;
		;

		double LaborCostVsBudget_ActualSales = 0;
		;

		double LaborCostVsBudget_BudgetSales = 0;
		;

		if ((PresentBy.equalsIgnoreCase("Week") || PresentBy.equalsIgnoreCase("Month"))
				|| PresentBy.equalsIgnoreCase("EntireRange")) {

			NetSalesBudgetDollars = (double) CreatedReportValues.get("Sales Budget $");

			NetSalesBudgetPercent = (double) CreatedReportValues.get("Sales Budget %");

			SalesVSBudgetDollar = (double) CreatedReportValues.get("Sales vs Budget $");

			SalesVSBudgetPercent = (double) CreatedReportValues.get("Sales vs Budget %");

			// Budget
			LaborCostBudgetPercent = (double) CreatedReportValues.get("Labor Cost Budget, %");

			LaborCostBudget_ActualSales = (double) CreatedReportValues.get("Labor Cost Budget, $ (actual sales)");

			LaborCostBudget_BudgetSales = (double) CreatedReportValues.get("Labor Cost Budget, $ (budgeted sales)");

			LaborCostBudgetVsBudgetPP_Dollar = (double) CreatedReportValues.get("Labor Cost vs Budget, p.p.");

			LaborCostVsBudget_ActualSales = (double) CreatedReportValues.get("Labor Cost vs Budget, $ (actual sales)");

			LaborCostVsBudget_BudgetSales = (double) CreatedReportValues
					.get("Labor Cost vs Budget, $ (budgeted sales)");
		}

		// Grab GuestCount Values
		double GuestCountTY = (double) CreatedReportValues.get("Guest Count");

		double GuestCountLY = (double) CreatedReportValues.get("Guest LY Count");

		double GuestCountVsLYCountt = (double) CreatedReportValues.get("Guest vs LY Count");

		double GuestCountVsLYPercent = (double) CreatedReportValues.get("Guest vs LY %");

		// Grab AvgCheck Values
		double AvgCheckTY = (double) CreatedReportValues.get("Avg Check $");

		double AvgCheckLY = (double) CreatedReportValues.get("Avg Check LY $");

		double AvgCheckVsLY = (double) CreatedReportValues.get("Avg Check vs LY $");

		double AvgCheckVsLYPercent = (double) CreatedReportValues.get("Avg Check vs LY %");

		// Grab LaborCost Values
		double LaborCostTYDollar = (double) CreatedReportValues.get("Labor Cost, $");

		double LaborCostTYPercent = (double) CreatedReportValues.get("Labor Cost, %");

		// last year
		double LaborCostLYDollar = (double) CreatedReportValues.get("Labor Cost LY, $");

		double LaborCostLYPercentReport = (double) CreatedReportValues.get("Labor Cost LY, %");

		double LaborCostVsLYDollar = (double) CreatedReportValues.get("Labor Cost vs LY, $");

		double LaborCostVsLY_PP = (double) CreatedReportValues.get("Labor Cost vs LY, p.p.");

		// last Week
		// double LaborCostVsLWDollar = CreatedReportValues.get("Labor Cost LW, $");
		// double LaborCostVsLWPercent = CreatedReportValues.get("Labor Cost LW, %");
		// double LaborCostVsLW_PP = CreatedReportValues.get("Labor Cost vs LW, p.p.");
		// Grab Gross Sales Values
		double GrossalesDollor = (double) CreatedReportValues.get("Gross Sales $");

		double GrossalesLYDollor = (double) CreatedReportValues.get("Gross Sales LY $");

		double GrossalesvsLYDollor = (double) CreatedReportValues.get("Gross Sales vs LY $");

		double GrossalesvsLYPercent = (double) CreatedReportValues.get("Gross Sales vs LY %");

		// Grab LaborHours Values
		double TotalHours = (double) CreatedReportValues.get("Total hours");

		double OTHours = (double) CreatedReportValues.get("OT hours");

		double TotalHoursLY = (double) CreatedReportValues.get("Total hours LY");

		double OTHoursLY = (double) CreatedReportValues.get("OT hours LY");

		double TotalHoursvsLY = (double) CreatedReportValues.get("Total hours vs LY");

		double OTHoursvsLY = (double) CreatedReportValues.get("OT hours vs LY");

		// ******************************************************************************************************************************//
		// Calculation of NetSales Formula

		log.info("Calculation of NetSales Formula start");

		double NetSalesvsLY$ = nf.calculate_NetSalesVsLastYear_Dollar(NetSalesTY, NetSalesLY);

		double NetSalesvsLYPercent = nf.calculate_NetSalesVsLastYear_Percent(NetSalesTY, NetSalesLY);

		// Calculation of NetSales Budget Formula

		log.info("Calculation of NetSales Budget  Formula start");

		double SalesBudgetPercent = 0;

		double SalesvsBudgetDollar = 0;
		;

		double SalesvsBudgetPercent = 0;
		;

		double SalesBudgetedDollor = 0;
		;

		if ((PresentBy.equalsIgnoreCase("Week") || PresentBy.equalsIgnoreCase("Month"))
				|| PresentBy.equalsIgnoreCase("EntireRange")) {

			SalesBudgetedDollor = nf.calculate_NetSalesBudget_Dollor(dblSalesBudget, NetSalesLY);

			SalesBudgetPercent = nf.calculate_NetSalesBudgetPercent(NetSalesBudgetDollars, NetSalesLY);

			SalesvsBudgetDollar = nf.calculate_SalesVsBudget(NetSalesBudgetDollars, NetSalesTY);

			SalesvsBudgetPercent = nf.calculate_SalesVsBudgetPercent(NetSalesBudgetDollars, NetSalesTY);
		}

		// Calculation of GuestCount Formula

		log.info("Calculation of GuestCount Formula starts");

		double GuestvsLYCount = gf.calculate_Guest_LY_Count(GuestCountTY, GuestCountLY);

		double GuestvsLYCountPercent = gf.calculate_GuestCountvsLY_Percent(GuestCountTY, GuestCountLY);

		// Calculation of AvgCheck Formula

		log.info("Calculation of AvgCheck Formula Starts");

		double AvgCheckTY$ = ac.calculate_AvgCheckTYDollar(NetSalesTY, GuestCountTY);

		double AvgCheckLY$ = ac.calculate_AvgCheckLYDollar(NetSalesLY, GuestCountLY);

		double AvgCheckvsLY$ = ac.calculate_AvgCheck_Vs_LY_Count_Dollar(AvgCheckTY$, AvgCheckLY$);

		double AvgCheckvsLYPercent = ac.calculate_AvgCheck_Vs_LY_Count_Percent(AvgCheckTY$, AvgCheckLY$);

		// Calculation of LaborCost Formula
		log.info("Calculation of LaborCost Formula Starts");

		double LaborCostPercent = lf.calculate_LaborCost_Percent(LaborCostTYDollar, NetSalesTY);

		double LaborCostLYPercent = lf.calculate_LaborCost_LY__Percent(LaborCostLYDollar, NetSalesLY);

		double LaborCostvsLY$ = lf.calculate_LaborCost_Vs_LY__Dollar(LaborCostTYDollar, LaborCostLYDollar);

		double LaborCostvsLYpp = lf.calculate_LaborCost_Vs_LY__PP(LaborCostPercent, LaborCostLYPercent);

		// Calculation of Gross Sales Formula
		log.info("Calculation of Gross Sales Formula Starts");

		double GrossalesvsLYDollorCalculated = gs.calculate_GrossSalesvsLYDollor(GrossalesDollor, GrossalesLYDollor);

		double GrossalesvsLYPercentCalculated = gs.calculate_GrossSalesvsLYPercent(GrossalesDollor, GrossalesLYDollor);

		// Calculation of LaborHours Formula
		double TotalHoursvsLYCalculated = lh.calculate_TotalHoursvsLY(TotalHours, TotalHoursLY);

		double OTHoursvsLYCalculated = lh.calculate_OTHoursvsLY(OTHours, OTHoursLY);

		// Calculation of LaborCost Last Week Formula
		// ********************************************************/////////////////////
		// double
		// LaborCostLWPercent=CustomKeywords."reportbuildercustom.Labor_Formulas.calculate_LaborCostLW_Percent"(AvgCheckTY,
		// AvgCheckLY)
		// double
		// LaborCostvsLW$=CustomKeywords."reportbuildercustom.Labor_Formulas.calculate_LaborCost_VS_LW_Dollar"(AvgCheckTY,
		// AvgCheckLY)
		// double
		// LaborCostvsLWpp=CustomKeywords."reportbuildercustom.Labor_Formulas.calculate_LaborCost_VS_LW_PP"(AvgCheckTY,
		// AvgCheckLY)
		// ********************************************************/////////////////////
		// Calculation of LaborCost Budget Formula

		log.info("Calculation of LaborCost Budget Formula Starts");

		double LaborCostBudgetPercentage = 0;

		double LaborCostBudgetDollarActualsales = 0;

		double LaborCostBudgetDollarBudgetsales = 0;

		double LaborCostvsBudgetPP = 0;

		double LaborCostVsBudgetDollarActualsales = 0;

		double LaborCostVsBudgetDollarBudgetsales = 0;

		if ((PresentBy.equalsIgnoreCase("Week") || PresentBy.equalsIgnoreCase("Month"))
				|| PresentBy.equalsIgnoreCase("EntireRange")) {
			LaborCostBudgetPercentage = lf.calculate_LaborCostBudget_Percentage(LaborCostBudget_ActualSales,
					NetSalesTY);

			LaborCostBudgetDollarActualsales = lf.calculate_LaborCostBudgetBasedOnActualSales_dollar(NetSalesTY,
					LaborCostBudgetPercent);

			LaborCostBudgetDollarBudgetsales = lf.calculate_LaborCostBudgetBasedOnBudgetedSales_dollar(
					NetSalesBudgetDollars, LaborCostBudgetPercent);

			LaborCostvsBudgetPP = lf.calculate_LaborCostVsBudget_PP(LaborCostPercent, LaborCostBudgetPercent);

			LaborCostVsBudgetDollarActualsales = lf.calculate_LaborCostVsBudgetForActualSales_Dollar(LaborCostTYDollar,
					LaborCostBudget_ActualSales);

			LaborCostVsBudgetDollarBudgetsales = lf.calculate_LaborCostVsBudgetForBudgetSales_Dollar(LaborCostTYDollar,
					LaborCostBudget_BudgetSales);
		}

		// **********************************************COMPARE
		// STARTS********************************************************************************//
		// Net Sales Compare

		log.info("Net Sales Compare Starts");

		vf.toVerifyActualvsExpectedValues(NetSalesVSLY, NetSalesvsLY$, Store, "Net Sales vs LY $", DataGrain);

		vf.toVerifyActualvsExpectedValues(NetSalesVSLYPercent, NetSalesvsLYPercent, Store, "Net Sales vs LY %",
				DataGrain);

		if ((PresentBy.equalsIgnoreCase("Week") || PresentBy.equalsIgnoreCase("Month"))
				|| PresentBy.equalsIgnoreCase("EntireRange")) {
			vf.toVerifyActualvsExpectedValues(NetSalesBudgetPercent, SalesBudgetPercent, Store, "Sales Budget, %",
					DataGrain);

			vf.toVerifyActualvsExpectedValues(SalesVSBudgetDollar, SalesvsBudgetDollar, Store, "Sales vs Budget ,$",
					DataGrain);

			vf.toVerifyActualvsExpectedValues(SalesVSBudgetPercent, SalesvsBudgetPercent, "Sales vs Budget, %", Store,
					DataGrain);
		}

		// GuestCount Compare
		log.info("GuestCount Compare Starts");

		vf.toVerifyActualvsExpectedValues(GrossalesvsLYDollor, GrossalesvsLYDollorCalculated, Store,
				"Guest vs LY ", DataGrain);

		vf.toVerifyActualvsExpectedValues(GuestCountVsLYPercent, GuestvsLYCountPercent, Store, "Guest vs LY ,%",
				DataGrain);

		// Average Check Compare
		vf.toVerifyActualvsExpectedValues(AvgCheckTY, AvgCheckTY$, Store, "AvgCheck TY", DataGrain);

		vf.toVerifyActualvsExpectedValues(AvgCheckLY, AvgCheckLY$, Store, "AvgCheck LY ", DataGrain);

		vf.toVerifyActualvsExpectedValues(AvgCheckVsLY, AvgCheckvsLY$, Store, "AvgCheck vs LY, $", DataGrain);

		vf.toVerifyActualvsExpectedValues(AvgCheckVsLYPercent, AvgCheckvsLYPercent, Store, "AvgCheck vs LY ,%", DataGrain);

		// Gross Sales Compare

		log.info("Gross Sales Compare");

		vf.toVerifyActualvsExpectedValues(GrossalesvsLYDollor, GrossalesvsLYDollorCalculated, Store,
				"Gross sales vs LY, $", DataGrain);
		vf.toVerifyActualvsExpectedValues(GrossalesvsLYPercent, GrossalesvsLYPercentCalculated, Store,
				"Gross sales vs LY,%", DataGrain);

		// Labor HoursCompare
		log.info("Labor Hours Compare starts");

		vf.toVerifyActualvsExpectedValues(TotalHoursvsLY, TotalHoursvsLYCalculated, Store, "Total Hours vs LY",
				DataGrain);
		vf.toVerifyActualvsExpectedValues(OTHoursvsLY, OTHoursvsLYCalculated, Store, "OT Hours vs LY", DataGrain);

		// Labor cost Compare
		vf.toVerifyActualvsExpectedValues(LaborCostTYPercent, LaborCostPercent, Store, "Labor Cost TY, %", DataGrain);

		vf.toVerifyActualvsExpectedValues(LaborCostLYPercentReport, LaborCostLYPercent, Store, "Labor Cost LY ,%",
				DataGrain);

		vf.toVerifyActualvsExpectedValues(LaborCostVsLYDollar, LaborCostvsLY$, Store, "LaborCost Vs LY, $", DataGrain);

		vf.toVerifyActualvsExpectedValues(LaborCostVsLY_PP, LaborCostvsLYpp, Store, "LaborCost Vs LY PP", DataGrain);

		// CustomKeywords."compareColumnsValues.LaborCostValuesCompare.toVerifyLaborCostLW_PercentColumnsValues"(LaborCostVsLWPercent,
		// LaborCostLWPercent,Store)
		// CustomKeywords."compareColumnsValues.LaborCostValuesCompare.toVerifyLaborCostVsLW_DollarColumnsValues"(LaborCostVsLWDollar,
		// LaborCostvsLW$,Store)
		// CustomKeywords."compareColumnsValues.LaborCostValuesCompare.toVerifyLaborCostVsLW_PPColumnsValues"(LaborCostVsLW_PP,LaborCostvsLWpp,Store)

		// Labor cost Budget Compare
		log.info("Labor cost Budget Compare starts");

		if ((PresentBy.equalsIgnoreCase("Week") || PresentBy.equalsIgnoreCase("Month"))
				|| PresentBy.equalsIgnoreCase("EntireRange")) {
			vf.toVerifyActualvsExpectedValues(LaborCostBudgetPercent, LaborCostBudgetPercentage, Store,
					"Labor Cost Budget, %", DataGrain);

			vf.toVerifyActualvsExpectedValues(LaborCostBudget_ActualSales, LaborCostBudgetDollarActualsales, Store,
					"Labor Cost Budget, $ (actual sales)", DataGrain);

			vf.toVerifyActualvsExpectedValues(LaborCostBudget_BudgetSales, LaborCostBudgetDollarBudgetsales, Store,
					"Labor Cost Budget, $ (budgeted sales)", DataGrain);

			vf.toVerifyActualvsExpectedValues(LaborCostBudgetVsBudgetPP_Dollar, LaborCostvsBudgetPP, Store,
					"Labor Cost vs Budget, p.p.", DataGrain);

			vf.toVerifyActualvsExpectedValues(LaborCostVsBudget_ActualSales, LaborCostVsBudgetDollarActualsales, Store,
					"Labor Cost vs Budget, $ (actual sales)", DataGrain);

			vf.toVerifyActualvsExpectedValues(LaborCostVsBudget_BudgetSales, LaborCostVsBudgetDollarBudgetsales, Store,
					"Labor Cost vs Budget, $ (budgeted sales)", DataGrain);

		}
	}

}
