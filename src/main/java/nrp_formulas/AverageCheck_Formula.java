package nrp_formulas;

public class AverageCheck_Formula {

	public double calculate_AvgCheckTYDollar(double NetSalesTY, double GuestCountTY) {

		double dblAvgCheckTYDollar = GuestCountTY == 0 ? 0 : (NetSalesTY / GuestCountTY);
		//double AvgCheckTYDollar = Math.round(dblAvgCheckTYDollar * 100) / 100;
		return ((long)(dblAvgCheckTYDollar * 1e2)) / 1e2;
	}

	public double calculate_AvgCheckLYDollar(double NetSalesLY, double GuestCountLY) {

		double dblAvgCheckLYDollar = GuestCountLY == 0 ? 0 : (NetSalesLY / GuestCountLY);
		//double AvgCheckLYDollar = Math.round(dblAvgCheckLYDollar * 100) / 100;
		return ((long)(dblAvgCheckLYDollar * 1e2)) / 1e2;
		
	}

	public double calculate_AvgCheck_Vs_LY_Count_Dollar(double AvgTicketTY, double AvgTicketLY) {

		double dblAvgCheckLY = AvgTicketTY - AvgTicketLY;
		//double AvgChecktLYValue = Math.round(dblAvgCheckLY * 100) / 100;
		return ((long)(dblAvgCheckLY * 1e2)) / 1e2;
	}

	public double calculate_AvgCheck_Vs_LY_Count_Percent(double AvgTicketTY, double AvgTicketLY) {

		double dblAvgCheckLY = AvgTicketLY == 0 ? 0 : ((AvgTicketTY - AvgTicketLY) / AvgTicketLY) * 100;
		//double AvgChecktLYPercentValue = Math.round(dblAvgCheckLY * 100) / 100;
		return ((long)(dblAvgCheckLY * 1e2)) / 1e2;
	}

}
