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

public class DailyFormulaCheckResult extends BaseNRP {
	
	
	String PresentBy=null;
	String PathName=null;
	String FileNameDaily=null;
	String FileNameWeekly=null;
	String FileNameMonthly=null;
	String PCname=null;
	String StartDate=null;
	String EndDate=null;
	String StartMonth=null;
	String EndMonth=null;
	String FileNameER=null;
	ExcelCustom ec=new ExcelCustom();
	
public String dateToday() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateNow = dtf.format(now);
		return dateNow;
	}


		
			public void CreateExcelFormulaCheck(String DataGrain) throws FileNotFoundException, IOException {

				
				
				if(DataGrain.equalsIgnoreCase("Daily"))
					
				{
					 PresentBy=daily.getProperty("PresentBy");
					 PathName=daily.getProperty("Path");
					 FileNameDaily=daily.getProperty("FileName_DailyFormula");
					 FileNameWeekly=daily.getProperty("FileName_WeeklyFormula");
					 FileNameMonthly=daily.getProperty("FileName_Monthlyormula");
					 FileNameER=daily.getProperty("FileName_EntireRange");
					 PCname=daily.getProperty("PC");
					 StartDate=daily.getProperty("stCalStartDate");
					 EndDate=daily.getProperty("stCalEndDate");
					 StartMonth=daily.getProperty("stCalStartMonthYear");
					 EndMonth=daily.getProperty("stCalEndMonthYear");

					 

					
				}else if (DataGrain.equalsIgnoreCase("Weekly"))

				{
					
					 PresentBy=weekly.getProperty("PresentBy");
					 PathName=weekly.getProperty("Path");
					 FileNameDaily=weekly.getProperty("FileName_DailyFormula");
					 FileNameWeekly=weekly.getProperty("FileName_WeeklyFormula");
					 FileNameMonthly=weekly.getProperty("FileName_Monthlyormula");
					 FileNameER=weekly.getProperty("FileName_EntireRange");
					 PCname=weekly.getProperty("PC");
					 StartDate=weekly.getProperty("stCalStartDate");
					 EndDate=weekly.getProperty("stCalEndDate");
					 StartMonth=weekly.getProperty("stCalStartMonthYear");
					 EndMonth=weekly.getProperty("stCalEndMonthYear");



					
					
				}else if (DataGrain.equalsIgnoreCase("Monthly"))

				{
					
					 PresentBy=monthly.getProperty("PresentBy");
					 PathName=monthly.getProperty("Path");
					 FileNameDaily=monthly.getProperty("FileName_DailyFormula");
					 FileNameWeekly=monthly.getProperty("FileName_WeeklyFormula");
					 FileNameMonthly=monthly.getProperty("FileName_Monthlyormula");
					 FileNameER=monthly.getProperty("FileName_EntireRange");
					 PCname=monthly.getProperty("PC");
					 StartDate=monthly.getProperty("stCalStartDate");
					 EndDate=monthly.getProperty("stCalEndDate");
					 StartMonth=monthly.getProperty("stCalStartMonthYear");
					 EndMonth=monthly.getProperty("stCalEndMonthYear");




					
					
				}

					
					
				if(PresentBy.equalsIgnoreCase("Day")) {	
					
					
					Workbook wb = new XSSFWorkbook();

					OutputStream fileOut = new FileOutputStream(PathName+"\\"+FileNameDaily);


					// Creating Sheets using sheet object
					Sheet sheet = wb.createSheet("Fail-Single Store");
					Sheet sheet1 = wb.createSheet("Pass-Single Store");
					Sheet sheet2 = wb.createSheet("Fail-All Store");
					Sheet sheet3 = wb.createSheet("Pass-All Store");
					Sheet sheet4 = wb.createSheet("Raw Values From Portal");

					System.out.println("Sheets Has been Created successfully");
					
					ExcelColumnDesign(sheet,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT- SINGLE STORE");
					ExcelColumnDesign(sheet1,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-SINGLE STORE");
					ExcelColumnDesignAllStore(sheet2,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-MULTISTORE");
					ExcelColumnDesignAllStore(sheet3,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-MULTISTORE");
					ExcelColumnDesignAllStore(sheet4,wb,"ALL FETCHED COLUMNS VALUES FROM PORTAL");

					wb.write(fileOut);

				}
				else if(PresentBy.equalsIgnoreCase("Week")) {

					Workbook wb = new XSSFWorkbook();

					OutputStream fileOut = new FileOutputStream(PathName+"\\"+FileNameWeekly);


					// Creating Sheets using sheet object
					Sheet sheet = wb.createSheet("Fail-Single Store");
					Sheet sheet1 = wb.createSheet("Pass-Single Store");
					Sheet sheet2 = wb.createSheet("Fail-All Store");
					Sheet sheet3 = wb.createSheet("Pass-All Store");
					Sheet sheet4 = wb.createSheet("Raw Values From Portal");

					System.out.println("Sheets Has been Created successfully");


					ExcelColumnDesign(sheet,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT- SINGLE STORE");
					ExcelColumnDesign(sheet1,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-SINGLE STORE");
					ExcelColumnDesignAllStore(sheet2,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-MULTISTORE");
					ExcelColumnDesignAllStore(sheet3,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-MULTISTORE");
					ExcelColumnDesignAllStore(sheet4,wb,"ALL FETCHED COLUMNS VALUES FROM PORTAL");

					wb.write(fileOut);

				}

				else if(PresentBy.equalsIgnoreCase("Month")) {

					Workbook wb = new XSSFWorkbook();

					OutputStream fileOut = new FileOutputStream(PathName+"\\"+FileNameMonthly);


					// Creating Sheets using sheet object
					Sheet sheet = wb.createSheet("Fail-Single Store");
					Sheet sheet1 = wb.createSheet("Pass-Single Store");
					Sheet sheet2 = wb.createSheet("Fail-All Store");
					Sheet sheet3 = wb.createSheet("Pass-All Store");
					Sheet sheet4 = wb.createSheet("Raw Values From Portal");

					System.out.println("Sheets Has been Created successfully");


					ExcelColumnDesign(sheet,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT- SINGLE STORE");
					ExcelColumnDesign(sheet1,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-SINGLE STORE");
					ExcelColumnDesignAllStore(sheet2,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-MULTISTORE");
					ExcelColumnDesignAllStore(sheet3,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-MULTISTORE");
					ExcelColumnDesignAllStore(sheet4,wb,"ALL FETCHED COLUMNS VALUES FROM PORTAL");

					wb.write(fileOut);


				}

				else if(PresentBy.equalsIgnoreCase("EntireRange")) {


					Workbook wb = new XSSFWorkbook();

					OutputStream fileOut = new FileOutputStream(PathName+"\\"+FileNameER);


					// Creating Sheets using sheet object
					Sheet sheet = wb.createSheet("Fail-Single Store");
					Sheet sheet1 = wb.createSheet("Pass-Single Store");
					Sheet sheet2 = wb.createSheet("Fail-All Store");
					Sheet sheet3 = wb.createSheet("Pass-All Store");
					Sheet sheet4 = wb.createSheet("Raw Values From Portal");

					System.out.println("Sheets Has been Created successfully");

					ExcelColumnDesign(sheet,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT- SINGLE STORE");
					ExcelColumnDesign(sheet1,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-SINGLE STORE");
					ExcelColumnDesignAllStore(sheet2,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-MULTISTORE");
					ExcelColumnDesignAllStore(sheet3,wb,"CHECKING FORMULA CALCULATIONS FOR DAILY PRESENT REPORT-MULTISTORE");
					ExcelColumnDesignAllStore(sheet4,wb,"ALL FETCHED COLUMNS VALUES FROM PORTAL");

					wb.write(fileOut);


				}
			}


			public void ExcelColumnDesign(Sheet sheet,Workbook wb,String objective) throws FileNotFoundException, IOException {

				Row row = sheet.createRow(0);
				Row row1 = sheet.createRow(2);
				Row row2 = sheet.createRow(5);
				Row row3 = sheet.createRow(7);
				Row row4 = sheet.createRow(9);
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));



				//CustomKeywords."excelUtils.CreateSheet.ec.createCell"(wb, row, 1, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY)


				// Create a cell and put a value in it.
				ec.createCell(wb, row, 0,"Test Objective",sheet);
				ec.createDataCell(wb, row, 1,objective, sheet);
				ec.createCell(wb, row1, 0,"Date OF Execution",sheet);
				ec.createDataCell(wb, row1, 1, dateToday(), sheet);
				ec.createCell(wb, row1, 2,"Store Name",sheet);
				ec.createDataCell(wb, row1, 3,PCname, sheet);
				ec.createCell(wb, row2, 0,"Start Date",sheet);
				ec.createDataCell(wb, row2, 1,StartDate+" "+StartMonth, sheet);
				ec.createCell(wb, row2, 2,"End Date",sheet);
				ec.createDataCell(wb, row2, 3,EndDate+" "+EndMonth, sheet);


				ec.createCell(wb, row4, 0,"Name OF Variable",sheet);
				ec.createCell(wb, row4, 1,"Values On Portal",sheet);
				ec.createCell(wb, row4, 2,"Values By Calculation",sheet);




			}

			public void ExcelColumnDesignAllStore(Sheet sheet,Workbook wb,String objective) throws FileNotFoundException, IOException {

				Row row = sheet.createRow(0);
				Row row1 = sheet.createRow(2);
				Row row2 = sheet.createRow(5);
				Row row3 = sheet.createRow(7);
				Row row4 = sheet.createRow(9);
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));



				//CustomKeywords."excelUtils.CreateSheet.ec.createCell"(wb, row, 1, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY)


				// Create a cell and put a value in it.
				ec.createCell(wb, row, 0,"Test Objective",sheet);
				ec.createDataCell(wb, row, 1,objective, sheet);
				ec.createCell(wb, row1, 0,"Date OF Execution",sheet);
				ec.createDataCell(wb, row1, 1, dateToday(), sheet);
				ec.createCell(wb, row1, 2,"Store Name",sheet);
				ec.createDataCell(wb, row1, 3,PCname, sheet);
				ec.createCell(wb, row2, 0,"Start Date",sheet);
				ec.createDataCell(wb, row2, 1,StartDate+" "+StartMonth, sheet);
				ec.createCell(wb, row2, 2,"End Date",sheet);
				ec.createDataCell(wb, row2, 3,EndDate+" "+EndMonth, sheet);


				ec.createCell(wb, row4, 0,"Name OF Variable",sheet);
				ec.createCell(wb, row4, 1,"Values On Portal",sheet);
				ec.createCell(wb, row4, 2,"Values By Calculation",sheet);




			}

	
	
	
	
	

}
