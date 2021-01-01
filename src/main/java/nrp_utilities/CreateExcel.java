package nrp_utilities;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nrp_base.BaseNRP;
import nrp_utilities.ExcelCustom;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CreateExcel extends BaseNRP {

	String PresentBy=null;
	String PathName=null;
	String FileNameDaily=null;
	String FileNameWeekly=null;
	String FileNameMonthly=null;
	String PCname=null;
	String DateRange=null;



	public String dateToday() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateNow = dtf.format(now);
		return dateNow;
	}

	public void createExcel(String DataGrain) throws FileNotFoundException, IOException {
		
		ExcelCustom ec=new ExcelCustom();
		
		if(DataGrain.equalsIgnoreCase("Daily"))
			
		{
			 PresentBy=daily.getProperty("PresentBy");
			 PathName=daily.getProperty("Path");
			 FileNameDaily=daily.getProperty("FileName_compareWithCal");
			 FileNameWeekly=daily.getProperty("FileName_compareWithCalWeekly");
			 FileNameMonthly=daily.getProperty("FileName_compareWithCalMonthly");
			 PCname=daily.getProperty("PC");
			 DateRange=daily.getProperty("OperationalCalendar");
			 

			
		}else if (DataGrain.equalsIgnoreCase("Weekly"))

		{
			
			 PresentBy=weekly.getProperty("PresentBy");
			 PathName=weekly.getProperty("Path");
			 FileNameDaily=weekly.getProperty("FileName_compareWithCal");
			 FileNameWeekly=weekly.getProperty("FileName_compareWithCalWeekly");
			 FileNameMonthly=weekly.getProperty("FileName_compareWithCalMonthly");
			 PCname=weekly.getProperty("PC");
			 DateRange=weekly.getProperty("OperationalCalendar");



			
			
		}else if (DataGrain.equalsIgnoreCase("Monthly"))

		{
			
			 PresentBy=monthly.getProperty("PresentBy");
			 PathName=monthly.getProperty("Path");
			 FileNameDaily=monthly.getProperty("FileName_compareWithCal");
			 FileNameWeekly=monthly.getProperty("FileName_compareWithCalWeekly");
			 FileNameMonthly=monthly.getProperty("FileName_compareWithCalMonthly");
			 PCname=monthly.getProperty("PC");
			 DateRange=monthly.getProperty("OperationalCalendar");




			
			
		}

				if(PresentBy.equalsIgnoreCase("Day")) {

					Workbook wb = new XSSFWorkbook();

					OutputStream fileOut = new FileOutputStream(PathName+"\\"+FileNameDaily);


					// Creating Sheets using sheet object
					Sheet sheet = wb.createSheet("Fail-Compare Result");
					Sheet sheet1 = wb.createSheet("Pass-Compare Result");




					System.out.println("Sheets Has been Created successfully");


					Row row = sheet.createRow(0);
					Row row1 = sheet.createRow(2);
					Row row2 = sheet.createRow(5);
					sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));

					//CustomKeywords."excelUtils.CreateSheet.ec.createCell"(wb, row, 1, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY)


					// Create a cell and put a value in it.
					ec.createCell(wb, row, 0,"Test Objective",sheet);
					ec.createDataCell(wb, row, 1, "To compare Calendar and Report values", sheet);
					ec.createCell(wb, row, 2,"Store Name",sheet);
					ec.createDataCell(wb, row, 3, PCname, sheet);
					ec.createCell(wb, row1, 0,"Date OF Execution",sheet);
					ec.createDataCell(wb, row1, 1, dateToday(), sheet);
					ec.createCell(wb, row1, 2,"Date Range",sheet);
					ec.createDataCell(wb, row1, 3, DateRange, sheet);

					ec.createCell(wb, row2, 0,"Name OF Variable",sheet);
					ec.createCell(wb, row2, 1,"Date",sheet);
					ec.createCell(wb, row2, 2,"Values At Calendar",sheet);
					ec.createCell(wb, row2, 3,"Values At Report",sheet);

					//Fail Case

					Row row3 = sheet1.createRow(0);
					Row row4 = sheet1.createRow(2);
					Row row5 = sheet1.createRow(5);
					sheet1.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));

					//CustomKeywords."excelUtils.CreateSheet.ec.createCell"(wb, row, 1, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY)


					// Create a cell and put a value in it.
					ec.createCell(wb, row3, 0,"Test Objective",sheet1);
					ec.createDataCell(wb, row3, 1, "To compare Calendar and Report for daily values", sheet1);
					ec.createCell(wb, row3,2,"Store Name",sheet1);
					ec.createDataCell(wb, row3, 3, PCname, sheet1);
					ec.createCell(wb, row4, 0,"Date OF Execution",sheet1);
					ec.createDataCell(wb, row4, 1, dateToday(), sheet1);
					ec.createCell(wb, row4, 2,"Date Range",sheet1);
					ec.createDataCell(wb, row4, 3, DateRange, sheet1);

					ec.createCell(wb, row5, 0,"Name OF Variable",sheet1);
					ec.createCell(wb, row5, 1,"Date",sheet1);
					ec.createCell(wb, row5, 2,"Values At Calendar",sheet1);
					ec.createCell(wb, row5, 3,"Values At Report",sheet1);




					wb.write(fileOut);

				}



				else if(PresentBy.equalsIgnoreCase("Week")) {


					Workbook wb = new XSSFWorkbook();

					OutputStream fileOut = new FileOutputStream(PathName+"\\"+FileNameWeekly);


					// Creating Sheets using sheet object
					Sheet sheet = wb.createSheet("Fail-Compare Result");
					Sheet sheet1 = wb.createSheet("Pass-Compare Result");




					System.out.println("Sheets Has been Created successfully");


					Row row = sheet.createRow(0);
					Row row1 = sheet.createRow(2);
					Row row2 = sheet.createRow(5);
					sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));

					//CustomKeywords."excelUtils.CreateSheet.ec.createCell"(wb, row, 1, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY)


					// Create a cell and put a value in it.
					ec.createCell(wb, row, 0,"Test Objective",sheet);
					ec.createDataCell(wb, row, 1, "To compare Calendar and Report values for weekly values", sheet);
					ec.createCell(wb, row, 2,"Store Name",sheet);
					ec.createDataCell(wb, row, 3, PCname, sheet);
					ec.createCell(wb, row1, 0,"Date OF Execution",sheet);
					ec.createDataCell(wb, row1, 1, dateToday(), sheet);
					ec.createCell(wb, row1, 2,"Date Range",sheet);
					ec.createDataCell(wb, row1, 3, DateRange, sheet);


					ec.createCell(wb, row2, 0,"Name OF Variable",sheet);
					ec.createCell(wb, row2, 1,"Date",sheet);
					ec.createCell(wb, row2, 2,"Values At Calendar",sheet);
					ec.createCell(wb, row2, 3,"Values At Report",sheet);

					//Fail Case

					Row row3 = sheet1.createRow(0);
					Row row4 = sheet1.createRow(2);
					Row row5 = sheet1.createRow(5);
					sheet1.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));

					//CustomKeywords."excelUtils.CreateSheet.ec.createCell"(wb, row, 1, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY)


					// Create a cell and put a value in it.
					ec.createCell(wb, row3, 0,"Test Objective",sheet1);
					ec.createDataCell(wb, row3, 1, "To compare Calendar and Report values", sheet1);
					ec.createCell(wb, row3,2,"Store Name",sheet1);
					ec.createDataCell(wb, row3, 3, PCname, sheet1);
					ec.createCell(wb, row4, 0,"Date OF Execution",sheet1);
					ec.createDataCell(wb, row4, 1, dateToday(), sheet1);
					ec.createCell(wb, row4, 2,"Date Range",sheet1);
					ec.createDataCell(wb, row4, 3, DateRange, sheet1);

					ec.createCell(wb, row5, 0,"Name OF Variable",sheet1);
					ec.createCell(wb, row5, 1,"Date",sheet1);
					ec.createCell(wb, row5, 2,"Values At Calendar",sheet1);
					ec.createCell(wb, row5, 3,"Values At Report",sheet1);




					wb.write(fileOut);


				}

				else if(PresentBy.equalsIgnoreCase("Month")) {


					Workbook wb = new XSSFWorkbook();

					OutputStream fileOut = new FileOutputStream(PathName+"\\"+FileNameMonthly);


					// Creating Sheets using sheet object
					Sheet sheet = wb.createSheet("Fail-Compare Result");
					Sheet sheet1 = wb.createSheet("Pass-Compare Result");




					System.out.println("Sheets Has been Created successfully");


					Row row = sheet.createRow(0);
					Row row1 = sheet.createRow(2);
					Row row2 = sheet.createRow(5);
					sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));

					//CustomKeywords."excelUtils.CreateSheet.ec.createCell"(wb, row, 1, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY)


					// Create a cell and put a value in it.
					ec.createCell(wb, row, 0,"Test Objective",sheet);
					ec.createDataCell(wb, row, 1, "To compare Calendar and Report values for Monthly values", sheet);
					ec.createCell(wb, row, 2,"Store Name",sheet);
					ec.createDataCell(wb, row, 3, PCname, sheet);
					ec.createCell(wb, row1, 0,"Date OF Execution",sheet);
					ec.createDataCell(wb, row1, 1, dateToday(), sheet);
					ec.createCell(wb, row1, 2,"Date Range",sheet);
					ec.createDataCell(wb, row1, 3, DateRange, sheet);

					ec.createCell(wb, row2, 0,"Name OF Variable",sheet);
					ec.createCell(wb, row2, 1,"Date",sheet);
					ec.createCell(wb, row2, 2,"Values At Calendar",sheet);
					ec.createCell(wb, row2, 3,"Values At Report",sheet);

					//Fail Case

					Row row3 = sheet1.createRow(0);
					Row row4 = sheet1.createRow(2);
					Row row5 = sheet1.createRow(5);
					sheet1.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));

					//CustomKeywords."excelUtils.CreateSheet.ec.createCell"(wb, row, 1, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY)


					// Create a cell and put a value in it.
					ec.createCell(wb, row3, 0,"Test Objective",sheet1);
					ec.createDataCell(wb, row3, 1, "To compare Calendar and Report values", sheet1);
					ec.createCell(wb, row3,2,"Store Name",sheet1);
					ec.createDataCell(wb, row3, 3, PCname, sheet1);
					ec.createCell(wb, row4, 0,"Date OF Execution",sheet1);
					ec.createDataCell(wb, row4, 1, dateToday(), sheet1);
					ec.createCell(wb, row4, 2,"Date Range",sheet1);
					ec.createDataCell(wb, row4, 3, DateRange, sheet1);


					ec.createCell(wb, row5, 0,"Name OF Variable",sheet1);
					ec.createCell(wb, row5, 1,"Date",sheet1);
					ec.createCell(wb, row5, 2,"Values At Calendar",sheet1);
					ec.createCell(wb, row5, 3,"Values At Report",sheet1);




					wb.write(fileOut);


				}
			}

}
