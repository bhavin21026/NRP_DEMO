package nrp_formulas;

public class LaborHours_Formula {

	public double calculate_TotalHoursvsLY(double TotalHoursTY, double TotalHoursLY) {

		double dblTotalHoursLYLY = TotalHoursTY - TotalHoursLY;
		//double TotalHoursvsLYValue = Math.round(dblTotalHoursLYLY * 100) / 100;
		return ((long)(dblTotalHoursLYLY * 1e2)) / 1e2;
	}

	public double calculate_OTHoursvsLY(double OTHoursTY, double OTHoursLY) {

		double dblOTHoursLYLY = OTHoursTY - OTHoursLY;
		//double OTHoursvsLYValue = Math.round(dblOTHoursLYLY * 100) / 100;
		return ((long)(dblOTHoursLYLY * 1e2)) / 1e2;
	}
}
