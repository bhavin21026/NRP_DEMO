package nrp_utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nrp_base.BaseNRP;

public class WriteExcelDailyFormula extends BaseNRP {

	String PresentBy = null;
	String PathName = null;
	String fileName = null;
	String FileNameDaily = null;
	String FileNameWeekly = null;
	String FileNameMonthly = null;
	String FileNameER = null;
	String sheetName = "Fail-Single Store";
	String sheetName1 = "Pass-Single Store";
	String sheetName3 = "Fail-All Store";
	String sheetName4 = "Pass-All Store";
	String sheetName5 = "Raw Values From Portal";

	public void writeExcelDailyFormulaCheck(String[] dataToWrite, String DataGrain) throws IOException {

		if (DataGrain.equalsIgnoreCase("Daily"))

		{
			PresentBy = daily.getProperty("PresentBy");
			PathName = daily.getProperty("Path");
			FileNameDaily = daily.getProperty("FileName_DailyFormula");
			FileNameWeekly = daily.getProperty("FileName_WeeklyFormula");
			FileNameMonthly = daily.getProperty("FileName_Monthlyormula");
			FileNameER = daily.getProperty("FileName_EntireRange");

		} else if (DataGrain.equalsIgnoreCase("Weekly"))

		{

			PresentBy = weekly.getProperty("PresentBy");
			PathName = weekly.getProperty("Path");
			FileNameDaily = weekly.getProperty("FileName_DailyFormula");
			FileNameWeekly = weekly.getProperty("FileName_WeeklyFormula");
			FileNameMonthly = weekly.getProperty("FileName_Monthlyormula");
			FileNameER = weekly.getProperty("FileName_EntireRange");

		} else if (DataGrain.equalsIgnoreCase("Monthly"))

		{

			PresentBy = monthly.getProperty("PresentBy");
			PathName = monthly.getProperty("Path");
			FileNameDaily = monthly.getProperty("FileName_DailyFormula");
			FileNameWeekly = monthly.getProperty("FileName_WeeklyFormula");
			FileNameMonthly = monthly.getProperty("FileName_Monthlyormula");
			FileNameER = monthly.getProperty("FileName_EntireRange");

		}

		if (PresentBy.equalsIgnoreCase("Day")) {

			fileName = FileNameDaily;
		} else if (PresentBy.equalsIgnoreCase("Week")) {

			fileName = FileNameWeekly;
		} else if (PresentBy.equalsIgnoreCase("Month")) {

			fileName = FileNameMonthly;
		} else if (PresentBy.equalsIgnoreCase("EntireRange")) {

			fileName = FileNameER;
		}

		// Create an object of File class to open xlsx file

		File file = new File(PathName + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			wb = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			wb = new HSSFWorkbook(inputStream);

		}

		// Read excel sheet by sheet name

		Sheet sheet = wb.getSheet(sheetName);

		// Get the current count of rows in excel file

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum(); // 5
		// println rowCount

		// Get the first row from the sheet

		Row row = sheet.getRow(0);
		// println row

		int lrow = rowCount;
		Row Lastrow = sheet.getRow(lrow);
		// println Lastrow

		// Create a new row and append it at last of sheet

		Row newRow = sheet.createRow(rowCount + 1);
		// println newRow

		// Create a loop over the cell of newly created Row

		int LastCellNumber = Lastrow.getLastCellNum();
		// println LastCellNumber

		for (int j = 0; j < Lastrow.getLastCellNum(); j++) {

			// Fill data in row

			Cell cell = newRow.createCell(j);

			CellStyle style = wb.createCellStyle();
			// style.setAlignment(HorizontalAlignment.CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			// style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
			// style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Arial");
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(false);
			font.setItalic(false);
			style.setFont(font);

			cell.setCellStyle(style);

			cell.setCellValue(dataToWrite[j]);
			sheet.autoSizeColumn(j);

		}

		// Close input stream

		inputStream.close();

		// Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		// write data in the excel file

		wb.write(outputStream);

		// close output stream

		outputStream.close();

	}

	public void writeExcelPassFormulaCheck(String[] dataToWrite,String DataGrain) throws IOException
	{



if(DataGrain.equalsIgnoreCase("Daily"))
			
		{
			 PresentBy=daily.getProperty("PresentBy");
			 PathName=daily.getProperty("Path");
			 FileNameDaily=daily.getProperty("FileName_DailyFormula");
			 FileNameWeekly=daily.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=daily.getProperty("FileName_Monthlyormula");
			 FileNameER=daily.getProperty("FileName_EntireRange");
			
			 

			
		}else if (DataGrain.equalsIgnoreCase("Weekly"))

		{
			
			 PresentBy=weekly.getProperty("PresentBy");
			 PathName=weekly.getProperty("Path");
			 FileNameDaily=weekly.getProperty("FileName_DailyFormula");
			 FileNameWeekly=weekly.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=weekly.getProperty("FileName_Monthlyormula");
			 FileNameER=weekly.getProperty("FileName_EntireRange");
			

			
			
		}else if (DataGrain.equalsIgnoreCase("Monthly"))

		{
			
			 PresentBy=monthly.getProperty("PresentBy");
			 PathName=monthly.getProperty("Path");
			 FileNameDaily=monthly.getProperty("FileName_DailyFormula");
			 FileNameWeekly=monthly.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=monthly.getProperty("FileName_Monthlyormula");
			 FileNameER=monthly.getProperty("FileName_EntireRange");
						
			
		}


		if(PresentBy.equalsIgnoreCase("Day")) {
			
			
			fileName=FileNameDaily;
		}
		else if(PresentBy.equalsIgnoreCase("Week")) {

			
			fileName=FileNameWeekly;
		}
		else if(PresentBy.equalsIgnoreCase("Month")) {
			
			fileName=FileNameMonthly;
		}
		else if(PresentBy.equalsIgnoreCase("EntireRange")) {
			
			
			fileName=FileNameER;
		}

		//Create an object of File class to open xlsx file

		File file =  new File(PathName+"\\"+fileName);

	

		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = null;

		//Find the file extension by splitting  file name in substring and getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		//Check condition if the file is xlsx file

		if(fileExtensionName.equals(".xlsx")){

			//If it is xlsx file then create object of XSSFWorkbook class

			wb = new XSSFWorkbook(inputStream);

		}

		//Check condition if the file is xls file

		else if(fileExtensionName.equals(".xls")){

			//If it is xls file then create object of XSSFWorkbook class

			wb = new HSSFWorkbook(inputStream);

		}

		//Read excel sheet by sheet name

		Sheet sheet = wb.getSheet(sheetName1);

		//Get the current count of rows in excel file

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();  //5
		//println rowCount

		//Get the first row from the sheet

		Row row = sheet.getRow(0);
		//println row

		int lrow=rowCount;
		Row Lastrow = sheet.getRow(lrow);
		//println Lastrow


		//Create a new row and append it at last of sheet

		Row newRow = sheet.createRow(rowCount+1);
		//println newRow

		//Create a loop over the cell of newly created Row

		int LastCellNumber= Lastrow.getLastCellNum();
		//println LastCellNumber

		for(int j = 0; j < Lastrow.getLastCellNum(); j++){

			//Fill data in row

			Cell cell = newRow.createCell(j);

			CellStyle style = wb.createCellStyle();
			//style.setAlignment(HorizontalAlignment.CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			//style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
			//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Arial");
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(false);
			font.setItalic(false);
			style.setFont(font);

			cell.setCellStyle(style);

			cell.setCellValue(dataToWrite[j]);
			sheet.autoSizeColumn(j);


		}

		//Close input stream

		inputStream.close();

		//Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		//write data in the excel file

		wb.write(outputStream);

		//close output stream

		outputStream.close();

	}


	public void writeExcelAllStorePass(String[] dataToWrite,String DataGrain) throws IOException
	{




if(DataGrain.equalsIgnoreCase("Daily"))
			
		{
			 PresentBy=daily.getProperty("PresentBy");
			 PathName=daily.getProperty("Path");
			 FileNameDaily=daily.getProperty("FileName_DailyFormula");
			 FileNameWeekly=daily.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=daily.getProperty("FileName_Monthlyormula");
			 FileNameER=daily.getProperty("FileName_EntireRange");
			
			 

			
		}else if (DataGrain.equalsIgnoreCase("Weekly"))

		{
			
			 PresentBy=weekly.getProperty("PresentBy");
			 PathName=weekly.getProperty("Path");
			 FileNameDaily=weekly.getProperty("FileName_DailyFormula");
			 FileNameWeekly=weekly.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=weekly.getProperty("FileName_Monthlyormula");
			 FileNameER=weekly.getProperty("FileName_EntireRange");
			

			
			
		}else if (DataGrain.equalsIgnoreCase("Monthly"))

		{
			
			 PresentBy=monthly.getProperty("PresentBy");
			 PathName=monthly.getProperty("Path");
			 FileNameDaily=monthly.getProperty("FileName_DailyFormula");
			 FileNameWeekly=monthly.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=monthly.getProperty("FileName_Monthlyormula");
			 FileNameER=monthly.getProperty("FileName_EntireRange");
						
			
		}


		if(PresentBy.equalsIgnoreCase("Day")) {
			
			
			fileName=FileNameDaily;
		}
		else if(PresentBy.equalsIgnoreCase("Week")) {

			
			fileName=FileNameWeekly;
		}
		else if(PresentBy.equalsIgnoreCase("Month")) {
			
			fileName=FileNameMonthly;
		}
		else if(PresentBy.equalsIgnoreCase("EntireRange")) {
			
			
			fileName=FileNameER;
		}

		//Create an object of File class to open xlsx file

		File file =  new File(PathName+"\\"+fileName);

		

		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = null;

		//Find the file extension by splitting  file name in substring and getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		//Check condition if the file is xlsx file

		if(fileExtensionName.equals(".xlsx")){

			//If it is xlsx file then create object of XSSFWorkbook class

			wb = new XSSFWorkbook(inputStream);

		}

		//Check condition if the file is xls file

		else if(fileExtensionName.equals(".xls")){

			//If it is xls file then create object of XSSFWorkbook class

			wb = new HSSFWorkbook(inputStream);

		}

		//Read excel sheet by sheet name

		Sheet sheet = wb.getSheet(sheetName4);

		//Get the current count of rows in excel file

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();  //5
		//println rowCount

		//Get the first row from the sheet

		Row row = sheet.getRow(0);
		//println row

		int lrow=rowCount;
		Row Lastrow = sheet.getRow(lrow);
		//println Lastrow


		//Create a new row and append it at last of sheet

		Row newRow = sheet.createRow(rowCount+1);
		//println newRow

		//Create a loop over the cell of newly created Row

		int LastCellNumber= Lastrow.getLastCellNum();
		//println LastCellNumber

		for(int j = 0; j < Lastrow.getLastCellNum(); j++){

			//Fill data in row

			Cell cell = newRow.createCell(j);

			CellStyle style = wb.createCellStyle();
			//style.setAlignment(HorizontalAlignment.CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			//style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
			//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Arial");
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(false);
			font.setItalic(false);
			style.setFont(font);

			cell.setCellStyle(style);

			cell.setCellValue(dataToWrite[j]);
			sheet.autoSizeColumn(j);


		}

		//Close input stream

		inputStream.close();

		//Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		//write data in the excel file

		wb.write(outputStream);

		//close output stream

		outputStream.close();

	}


	public void writeExcelAllStoreFail(String[] dataToWrite,String DataGrain) throws IOException
	{




if(DataGrain.equalsIgnoreCase("Daily"))
			
		{
			 PresentBy=daily.getProperty("PresentBy");
			 PathName=daily.getProperty("Path");
			 FileNameDaily=daily.getProperty("FileName_DailyFormula");
			 FileNameWeekly=daily.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=daily.getProperty("FileName_Monthlyormula");
			 FileNameER=daily.getProperty("FileName_EntireRange");
			
			 

			
		}else if (DataGrain.equalsIgnoreCase("Weekly"))

		{
			
			 PresentBy=weekly.getProperty("PresentBy");
			 PathName=weekly.getProperty("Path");
			 FileNameDaily=weekly.getProperty("FileName_DailyFormula");
			 FileNameWeekly=weekly.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=weekly.getProperty("FileName_Monthlyormula");
			 FileNameER=weekly.getProperty("FileName_EntireRange");
			

			
			
		}else if (DataGrain.equalsIgnoreCase("Monthly"))

		{
			
			 PresentBy=monthly.getProperty("PresentBy");
			 PathName=monthly.getProperty("Path");
			 FileNameDaily=monthly.getProperty("FileName_DailyFormula");
			 FileNameWeekly=monthly.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=monthly.getProperty("FileName_Monthlyormula");
			 FileNameER=monthly.getProperty("FileName_EntireRange");
						
			
		}


		if(PresentBy.equalsIgnoreCase("Day")) {
			
			
			fileName=FileNameDaily;
		}
		else if(PresentBy.equalsIgnoreCase("Week")) {

			
			fileName=FileNameWeekly;
		}
		else if(PresentBy.equalsIgnoreCase("Month")) {
			
			fileName=FileNameMonthly;
		}
		else if(PresentBy.equalsIgnoreCase("EntireRange")) {
			
			
			fileName=FileNameER;
		}
		//Create an object of File class to open xlsx file

		File file =  new File(PathName+"\\"+fileName);


		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = null;

		//Find the file extension by splitting  file name in substring and getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		//Check condition if the file is xlsx file

		if(fileExtensionName.equals(".xlsx")){

			//If it is xlsx file then create object of XSSFWorkbook class

			wb = new XSSFWorkbook(inputStream);

		}

		//Check condition if the file is xls file

		else if(fileExtensionName.equals(".xls")){

			//If it is xls file then create object of XSSFWorkbook class

			wb = new HSSFWorkbook(inputStream);

		}

		//Read excel sheet by sheet name

		Sheet sheet = wb.getSheet(sheetName3);

		//Get the current count of rows in excel file

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();  //5
		//println rowCount

		//Get the first row from the sheet

		Row row = sheet.getRow(0);
		//println row

		int lrow=rowCount;
		Row Lastrow = sheet.getRow(lrow);
		//println Lastrow


		//Create a new row and append it at last of sheet

		Row newRow = sheet.createRow(rowCount+1);
		//println newRow

		//Create a loop over the cell of newly created Row

		int LastCellNumber= Lastrow.getLastCellNum();
		//println LastCellNumber

		for(int j = 0; j < Lastrow.getLastCellNum(); j++){

			//Fill data in row

			Cell cell = newRow.createCell(j);

			CellStyle style = wb.createCellStyle();
			//style.setAlignment(HorizontalAlignment.CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			//style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
			//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Arial");
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(false);
			font.setItalic(false);
			style.setFont(font);

			cell.setCellStyle(style);

			cell.setCellValue(dataToWrite[j]);
			sheet.autoSizeColumn(j);


		}

		//Close input stream

		inputStream.close();

		//Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		//write data in the excel file

		wb.write(outputStream);

		//close output stream

		outputStream.close();

	}

	
	public void writeRawValues(String[] dataToWrite,String DataGrain) throws IOException
	{




if(DataGrain.equalsIgnoreCase("Daily"))
			
		{
			 PresentBy=daily.getProperty("PresentBy");
			 PathName=daily.getProperty("Path");
			 FileNameDaily=daily.getProperty("FileName_DailyFormula");
			 FileNameWeekly=daily.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=daily.getProperty("FileName_Monthlyormula");
			 FileNameER=daily.getProperty("FileName_EntireRange");
			
			 

			
		}else if (DataGrain.equalsIgnoreCase("Weekly"))

		{
			
			 PresentBy=weekly.getProperty("PresentBy");
			 PathName=weekly.getProperty("Path");
			 FileNameDaily=weekly.getProperty("FileName_DailyFormula");
			 FileNameWeekly=weekly.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=weekly.getProperty("FileName_Monthlyormula");
			 FileNameER=weekly.getProperty("FileName_EntireRange");
			

			
			
		}else if (DataGrain.equalsIgnoreCase("Monthly"))

		{
			
			 PresentBy=monthly.getProperty("PresentBy");
			 PathName=monthly.getProperty("Path");
			 FileNameDaily=monthly.getProperty("FileName_DailyFormula");
			 FileNameWeekly=monthly.getProperty("FileName_WeeklyFormula");
			 FileNameMonthly=monthly.getProperty("FileName_Monthlyormula");
			 FileNameER=monthly.getProperty("FileName_EntireRange");
						
			
		}


		if(PresentBy.equalsIgnoreCase("Day")) {
			
			
			fileName=FileNameDaily;
		}
		else if(PresentBy.equalsIgnoreCase("Week")) {

			
			fileName=FileNameWeekly;
		}
		else if(PresentBy.equalsIgnoreCase("Month")) {
			
			fileName=FileNameMonthly;
		}
		else if(PresentBy.equalsIgnoreCase("EntireRange")) {
			
			
			fileName=FileNameER;
		}
		//Create an object of File class to open xlsx file

		File file =  new File(PathName+"\\"+fileName);


		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = null;

		//Find the file extension by splitting  file name in substring and getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		//Check condition if the file is xlsx file

		if(fileExtensionName.equals(".xlsx")){

			//If it is xlsx file then create object of XSSFWorkbook class

			wb = new XSSFWorkbook(inputStream);

		}

		//Check condition if the file is xls file

		else if(fileExtensionName.equals(".xls")){

			//If it is xls file then create object of XSSFWorkbook class

			wb = new HSSFWorkbook(inputStream);

		}

		//Read excel sheet by sheet name

		Sheet sheet = wb.getSheet(sheetName5);

		//Get the current count of rows in excel file

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();  //5
		//println rowCount

		//Get the first row from the sheet

		Row row = sheet.getRow(0);
		//println row

		int lrow=rowCount;
		Row Lastrow = sheet.getRow(lrow);
		//println Lastrow


		//Create a new row and append it at last of sheet

		Row newRow = sheet.createRow(rowCount+1);
		//println newRow

		//Create a loop over the cell of newly created Row

		int LastCellNumber= Lastrow.getLastCellNum();
		//println LastCellNumber

		for(int j = 0; j < Lastrow.getLastCellNum(); j++){

			//Fill data in row

			Cell cell = newRow.createCell(j);

			CellStyle style = wb.createCellStyle();
			//style.setAlignment(HorizontalAlignment.CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			//style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
			//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Arial");
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(false);
			font.setItalic(false);
			style.setFont(font);

			cell.setCellStyle(style);

			cell.setCellValue(dataToWrite[j]);
			sheet.autoSizeColumn(j);


		}

		//Close input stream

		inputStream.close();

		//Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		//write data in the excel file

		wb.write(outputStream);

		//close output stream

		outputStream.close();

	}

}
