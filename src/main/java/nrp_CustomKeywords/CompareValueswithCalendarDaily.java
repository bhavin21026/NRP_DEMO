package nrp_CustomKeywords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import nrp_base.BaseNRP;
import nrp_utilities.WriteExcel;

public class CompareValueswithCalendarDaily extends BaseNRP {

	public boolean CompareWithCalender(HashMap<String, String> map1, HashMap<String, String> map2, String variableName,
			String DataGrain) throws IOException {

		WriteExcel we = new WriteExcel();
		int index = 0;
		boolean flag = false;
		String ValueFromCalendar = null;
		String ValuesFromReports = null;
		String ValueFromCalendarActual = null;
		String ValuesFromReportsActual = null;
		String ActualDate = null;
		double ValueFromCal = 0;
		double ValuesFromRep = 0;
		
		try {

			// ActualDateSet=map1.keySet();
			for (String k : map2.keySet()) {

				ValueFromCalendar = map1.get(k);
				ValuesFromReports = map2.get(k);

				if (ValueFromCalendar.startsWith("$") && (ValuesFromReports.startsWith("$"))) {
					ValueFromCalendarActual = ValueFromCalendar.substring(1);
					ValuesFromReportsActual = ValuesFromReports.substring(1);

					ValueFromCal = Double.parseDouble(ValueFromCalendarActual);
					ValuesFromRep = Double.parseDouble(ValuesFromReportsActual);

				} else

				{

					ValueFromCal = Double.parseDouble(map1.get(k));
					ValuesFromRep = Double.parseDouble(map2.get(k));

				}

				if (ValueFromCal == ValuesFromRep) {

					ValueFromCalendar = map1.get(k);
					ValuesFromReports = map2.get(k);
					ActualDate = k;
					String[] writedata = { variableName, ActualDate, ValueFromCalendar, ValuesFromReports };
					log.info(variableName + " " + "values matched with calendar for day " + ActualDate);
					we.writeExcelForPass(writedata, DataGrain);
				}

				else {

					ValueFromCalendar = map1.get(k);
					ValuesFromReports = map2.get(k);
					ActualDate = k;
					String[] writedata = { variableName, ActualDate, ValueFromCalendar, ValuesFromReports };
					log.info(variableName + " " + "values not getting matched with calendar for day " + ActualDate);
					we.writeExcel(writedata, DataGrain);
					flag = true;
				}
			}
			if (flag == false) {

				log.info("All" + variableName + " " + "Values Matched with Calendar");

			}
		} catch (NullPointerException np) {
			return false;
		}
		return true;

	}
}