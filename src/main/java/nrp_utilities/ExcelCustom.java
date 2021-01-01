package nrp_utilities;
import nrp_base.BaseNRP;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import java.io.*;
import org.apache.poi.ss.usermodel.Sheet;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;



public class ExcelCustom extends BaseNRP {
	
	
	
	public void createCell(Workbook wb, Row row, int column,String Date,Sheet sheet) {

		Cell cell = row.createCell(column);
		CellStyle style = wb.createCellStyle();
		
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("Calibri");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		font.setItalic(false);
		style.setFont(font);

		cell.setCellStyle(style);
		cell.setCellValue(Date);
		sheet.autoSizeColumn(column);
		
		
	}


	
	public void createDataCell(Workbook wb, Row row, int column,String Date,Sheet sheet) {

		Cell cell = row.createCell(column);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("Calibri");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		font.setItalic(false);
		style.setFont(font);

		cell.setCellStyle(style);
		cell.setCellValue(Date);
		sheet.autoSizeColumn(column);
	}



	
	public void deleteCalFile(String DataGrain) {

		String PresentBy=null;
		String PathName=null;
		String FileNameDaily=null;
		String FileNameWeekly=null;
		String FileNameMonthly=null;
		
		if(DataGrain.equalsIgnoreCase("Daily"))
			
		{
			 PresentBy=daily.getProperty("PresentBy");
			 PathName=daily.getProperty("Path");
			 FileNameDaily=daily.getProperty("FileName_compareWithCal");
			 FileNameWeekly=daily.getProperty("FileName_compareWithCalWeekly");
			 FileNameMonthly=daily.getProperty("FileName_compareWithCalMonthly");

			 

			
		}else if (DataGrain.equalsIgnoreCase("Weekly"))

		{
			
			 PresentBy=weekly.getProperty("PresentBy");
			 PathName=weekly.getProperty("Path");
			 FileNameDaily=weekly.getProperty("FileName_compareWithCal");
			 FileNameWeekly=weekly.getProperty("FileName_compareWithCalWeekly");
			 FileNameMonthly=weekly.getProperty("FileName_compareWithCalMonthly");


			
			
		}else if (DataGrain.equalsIgnoreCase("Monthly"))

		{
			
			 PresentBy=monthly.getProperty("PresentBy");
			 PathName=monthly.getProperty("Path");
			 FileNameDaily=monthly.getProperty("FileName_compareWithCal");
			 FileNameWeekly=monthly.getProperty("FileName_compareWithCalWeekly");
			 FileNameMonthly=monthly.getProperty("FileName_compareWithCalMonthly");


			
			
		}

		if(PresentBy.equalsIgnoreCase("Day")) {

			File file = new File(PathName+"\\"+FileNameDaily);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
		else if(PresentBy.equalsIgnoreCase("Week")) {

			File file = new File(PathName+"\\"+FileNameWeekly);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}

		else if(PresentBy.equalsIgnoreCase("Month")) {

			File file = new File(PathName+"\\"+FileNameMonthly);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
	}



	public void deleteFile(String DataGrain) {

		String PresentBy=null;
		String PathName=null;
		String FileNameDailyFormula=null;
		String FileNameWeeklyFormula=null;
		String FileNameMonthlyFormula=null;
		String FileNameERFormula=null;

		
		if(DataGrain.equalsIgnoreCase("Daily"))
			
		{
			 PresentBy=daily.getProperty("PresentBy");
			 PathName=daily.getProperty("Path");
			 FileNameDailyFormula=daily.getProperty("DailyFromulaResult");
			 FileNameWeeklyFormula=daily.getProperty("WeeklyFormulaResult.xlsx");
			 FileNameMonthlyFormula=daily.getProperty("MonthlyFormulaResult.xlsx");
			 FileNameERFormula=daily.getProperty("EntireRangeResult.xlsx");


			 

			
		}else if (DataGrain.equalsIgnoreCase("Weekly"))

		{
			
			 PresentBy=weekly.getProperty("PresentBy");
			 PathName=weekly.getProperty("Path");
			 FileNameDailyFormula=weekly.getProperty("DailyFromulaResult");
			 FileNameWeeklyFormula=weekly.getProperty("WeeklyFormulaResult.xlsx");
			 FileNameMonthlyFormula=weekly.getProperty("MonthlyFormulaResult.xlsx");
			 FileNameERFormula=weekly.getProperty("EntireRangeResult.xlsx");


			
			
		}else if (DataGrain.equalsIgnoreCase("Monthly"))

		{
			
			 PresentBy=monthly.getProperty("PresentBy");
			 PathName=monthly.getProperty("Path");
			 FileNameDailyFormula=monthly.getProperty("DailyFromulaResult");
			 FileNameWeeklyFormula=monthly.getProperty("WeeklyFormulaResult.xlsx");
			 FileNameMonthlyFormula=monthly.getProperty("MonthlyFormulaResult.xlsx");
			 FileNameERFormula=monthly.getProperty("EntireRangeResult.xlsx");


			
			
		}

		if(PresentBy.equalsIgnoreCase("Day")) {
			
			File file = new File(PathName+"\\"+FileNameDailyFormula);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
		else if(PresentBy.equalsIgnoreCase("Week")) {

			File file = new File(PathName+"\\"+FileNameWeeklyFormula);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
		else if(PresentBy.equalsIgnoreCase("Month")) {
			
			File file = new File(PathName+"\\"+FileNameMonthlyFormula);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
		else if(PresentBy.equalsIgnoreCase("EntireRange")) {
			
			File file = new File(PathName+"\\"+FileNameERFormula);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
	}

	/*public void deleteFileLossPrevention() {

		String PresentBy= GlobalVariable.PresentBy

		if(PresentBy.equalsIgnoreCase("Day")) {
			File file = new File(GlobalVariable.Path_CompareWithCalendar+"\\LossPrevention"+GlobalVariable.FileName_DailyFormula);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
		else if(PresentBy.equalsIgnoreCase("Week")) {

			File file = new File(GlobalVariable.Path_CompareWithCalendar+"\\LossPrevention"+GlobalVariable.FileName_WeeklyFormula);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
		else if(PresentBy.equalsIgnoreCase("Month")) {
			File file = new File(GlobalVariable.Path_CompareWithCalendar+"\\LossPrevention"+GlobalVariable.FileName_Monthlyormula);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
		else if(PresentBy.equalsIgnoreCase("EntireRange")) {
			File file = new File(GlobalVariable.Path_CompareWithCalendar+"\\LossPrevention"+GlobalVariable.FileName_EntireRange);

			if(file.delete()) {
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the file");
			}
		}
	}*/

	
	
	
	

}
