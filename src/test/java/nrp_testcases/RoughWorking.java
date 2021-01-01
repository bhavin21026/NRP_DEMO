package nrp_testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import nrp_CustomKeywords.FetchVariablefromReportAndcomapreWithCalendar_Daily;
import nrp_base.BaseNRP;
import nrp_utilities.CreateExcel;
import nrp_utilities.ExcelCustom;
import nrp_utilities.WriteExcel;

public class RoughWorking extends BaseNRP {

	@Test

	public void test() throws IOException {

		HashMap<String, String> CompareNetSales = new HashMap<String, String>();
		HashMap<String, String> CompareNetSalesLY = new HashMap<String, String>();

		CompareNetSales.put("1", "123.00");
		CompareNetSalesLY.put("1", "123");

		CompareWithCalender(CompareNetSales, CompareNetSalesLY, "NetSales", "Daily");

	}

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

				}else 
				
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
