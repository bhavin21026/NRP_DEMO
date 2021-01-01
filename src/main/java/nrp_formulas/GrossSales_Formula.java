package nrp_formulas;

public class GrossSales_Formula {

public double calculate_GrossSalesvsLYDollor(double GrossSalesTY, double GrossSalesLY) {

		double dblGrossSalesLYLY=GrossSalesTY-GrossSalesLY;
		//double GrossSalesvsLYValue= Math.round(dblGrossSalesLYLY * 100) / 100;
		return ((long)(dblGrossSalesLYLY * 1e2)) / 1e2;
	}

	public double calculate_GrossSalesvsLYPercent(double GrossSalesTY, double GrossSalesLY) {

		double GrossSalesVsLastYear_DollarNew=GrossSalesLY == 0 ? 0 :(GrossSalesTY-GrossSalesLY)/GrossSalesLY*100;
		//double GrossSalesVsLastYear_Dollar= Math.round(GrossSalesVsLastYear_DollarNew * 100) / 100;
		return ((long)(GrossSalesVsLastYear_DollarNew * 1e2)) / 1e2;
	}

}
