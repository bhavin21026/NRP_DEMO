package nrp_formulas;

public class GuestCount_Formula {

	public double calculate_Guest_LY_Count(double GuestCountTY, double GuestCountLY) {

		double dblGuestCountLY = GuestCountTY - GuestCountLY;
		//double GuestCountLYValue = Math.round(dblGuestCountLY * 100) / 100;
		return ((long)(dblGuestCountLY * 1e2)) / 1e2;
	}

	public double calculate_GuestCountvsLY_Percent(double GuestCountTY, double GuestCountLY) {

		double dblGuestCountLYPercent = GuestCountLY == 0 ? 0 : ((GuestCountTY - GuestCountLY) / GuestCountLY) * 100;
		//double GuestCountLY_Percent_Value = Math.round(dblGuestCountLYPercent * 100) / 100;
		return ((long)(dblGuestCountLYPercent * 1e2)) / 1e2;
	}

}
