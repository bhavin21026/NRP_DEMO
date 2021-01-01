package nrp_formulas;

public class NetSales_Formula {

	public double calculate_NetSalesVsLastYear_Dollar(double ActualSalesTY, double ActualSalesLY) {

		double NetSalesVsLastYear_DollarNew = ActualSalesTY - ActualSalesLY;
		//double NetSalesVsLastYear_Dollar = Math.round(NetSalesVsLastYear_DollarNew * 100) / 100;
		return ((long)(NetSalesVsLastYear_DollarNew * 1e2)) / 1e2;
	}

	public double calculate_NetSalesVsLastYear_Percent(double ActualSalesTY, double ActualSalesLY) {

		double NetSalesVsLastYear_DollarNew = ActualSalesLY == 0 ? 0
				: (ActualSalesTY - ActualSalesLY) / ActualSalesLY * 100;
		//double NetSalesVsLastYear_Dollar = Math.round(NetSalesVsLastYear_DollarNew * 100) / 100;
		return ((long)(NetSalesVsLastYear_DollarNew * 1e2)) / 1e2;
	}

	// Net Sales Budget Formulas

	public double calculate_NetSalesBudget_Dollor(double SalesBudgetPercent, double NetSalesLY)

	{

		double NetSalesBudget_DollarNew = (NetSalesLY * SalesBudgetPercent / 100) + NetSalesLY;
		//double NetSalesBudget_Dollar = Math.round(NetSalesBudget_DollarNew * 100) / 100;
		return ((long)(NetSalesBudget_DollarNew * 1e2)) / 1e2;

	}

	public double calculate_NetSalesBudgetPercent(double SalesBudget, double NetSalesLY)

	{

		// double WeeklySalesBudget=SalesBudget/NoOfWeeksInMonth
		double dblNetSalesBudgetPercent = NetSalesLY == 0 ? 0 : (SalesBudget - NetSalesLY) / NetSalesLY * 100;
		//double NetSalesBudgetPercent = Math.round(dblNetSalesBudgetPercent * 100) / 100;
		return ((long)(dblNetSalesBudgetPercent * 1e2)) / 1e2;
	}

	public double calculate_SalesVsBudget(double SalesBudgetTY, double ActualSalesTY)

	{
		double dblSalesVsBudget = ActualSalesTY - SalesBudgetTY;
		//double SalesVsBudget = Math.round(dblSalesVsBudget * 100) / 100;
		return ((long)(dblSalesVsBudget * 1e2)) / 1e2;

	}

	public double calculate_SalesVsBudgetPercent(double SalesBudgetTY, double ActualSalesTY)

	{

		double dblSalesVsBudgetPercent = SalesBudgetTY == 0 ? 0
				: ((ActualSalesTY - SalesBudgetTY) / SalesBudgetTY) * 100;
		//double SalesVsBudgetPercent = Math.round(dblSalesVsBudgetPercent * 100) / 100;
		return ((long)(dblSalesVsBudgetPercent * 1e2)) / 1e2;

	}

}
