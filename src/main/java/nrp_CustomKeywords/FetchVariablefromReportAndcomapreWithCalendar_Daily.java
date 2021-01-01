package nrp_CustomKeywords;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;
import nrp_utilities.CreateExcel;
import nrp_utilities.ExcelCustom;

public class FetchVariablefromReportAndcomapreWithCalendar_Daily extends BaseNRP {

	public FetchVariablefromReportAndcomapreWithCalendar_Daily(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		waitMore = new WebDriverWait(driver, 200);

		PageFactory.initElements(driver, this);

	}

	public void fetchDailyReportValueAndComapreWithCalendar(HashMap<String, Object> DateAndValueFromCalendar,ArrayList<String> DayWiseDates,int NoOfColumns,String DataGrain) throws InterruptedException, IOException
	{
		
		HashMap<String, String> NetSalesFromReport = new HashMap<String, String>();
		HashMap<String, String> NetSalesLastYearFromReport = new HashMap<String, String>();
		HashMap<String, String> BudgetedSalesFromReport = new HashMap<String, String>();
		HashMap<String, String> GuestCountFromReport = new HashMap<String, String>();
		HashMap<String, String> GuestCountLastYearFromReport = new HashMap<String, String>();
		HashMap<String, String> GuestCountBudgetFromReport = new HashMap<String, String>();
		HashMap<String, String> AvgCheckFromReport = new HashMap<String, String>();
		HashMap<String, String> AvgCheckLastYearFromReport = new HashMap<String, String>();
		HashMap<String, String> AvgCheckBudgetFromReport = new HashMap<String, String>();
		HashMap<String, String> LaborFromReport = new HashMap<String, String>();
		HashMap<String, String> LaborLastYearFromReport = new HashMap<String, String>();
		HashMap<String, String> LaborBudgetFromReport = new HashMap<String, String>();
	    HashMap<String, Object> AllVariablesFromReport = new HashMap<String, Object>();
		HashMap<String, Object> DateAndValueFromCal = new HashMap<String, Object>();
		
		HashMap<String, String> CompareNetSales = new HashMap<String, String>();
		HashMap<String, String> CompareNetSalesLY = new HashMap<String, String>();
		HashMap<String, String> CompareGuestCount = new HashMap<String, String>();
		HashMap<String, String> ComapreGuestCountLY = new HashMap<String, String>();
		HashMap<String, String> CompareAvgTck = new HashMap<String, String>();
		HashMap<String, String> CompareAvgTckLY = new HashMap<String, String>();
		HashMap<String, String> CompareLaborCostTY = new HashMap<String, String>();
	    HashMap<String, String> CompareLaborCostLY = new HashMap<String, String>();
		HashMap<String, String> CompareSalesBudget = new HashMap<String, String>();
		HashMap<String, Object> ValuesFromCalender = new HashMap<String, Object>();

		
		HashMap<String, Object> allvaluesFromCal = new HashMap<String, Object>();
		ArrayList<String> fecthDates=new ArrayList<String>();

		String StoreName=null;
		String user=null;
		int index=0;
		int NewIndex=0;
		
		if(DataGrain.equalsIgnoreCase("Daily"))
		
		{
			 StoreName = daily.getProperty("PC");

			 user = setup.getProperty("username");
			
		}else if (DataGrain.equalsIgnoreCase("Weekly"))

		{
			
			 StoreName = weekly.getProperty("PC");

			 user = setup.getProperty("username");
			
			
		}else if (DataGrain.equalsIgnoreCase("Monthly"))

		{
			
			 StoreName = monthly.getProperty("PC");

			 user = setup.getProperty("username");
			
			
		}

			allvaluesFromCal = DateAndValueFromCalendar;

				fecthDates = (ArrayList<String>) allvaluesFromCal.get("CalandarValues");

				int sizeOfColumns = NoOfColumns;


				ValuesFromCalender = (HashMap<String, Object>) allvaluesFromCal.get("VariableValues");

				int DateIndex = 0;

				
				Set<String> listForReportColumns = new HashSet<String>(Arrays.asList("Net Sales $", "Net Sales LY $", "Sales Budget $", "Guest Count", "Guest LY Count", "Avg Check $" , "Avg Check LY $", "Labor Cost, $", "Labor Cost LY, $"));

				Thread.sleep(3000);

			if (user.equalsIgnoreCase("admin.insight360@analytix.com")) {
				
				log.info("Welcome BK");

				    WebElement Tabel = driver.findElement(By.xpath("(//tbody[@class=\"ui-table-tbody\"])[2]"));

				    List<WebElement> Rows = Tabel.findElements(By.tagName("tr"));

				    for (int j = 0; j < Rows.size(); j++) {
						
				        WebElement VariableRow = Rows.get(j);

				        List<WebElement> cells = VariableRow.findElements(By.tagName("td"));

				        List<WebElement> Variablename1 = cells.get(0).findElements(By.tagName("div"));
				        String Variablename=Variablename1.get(1).getText();
				        if (Variablename.trim().length() >= 7) {
				            String gotStorename = Variablename.trim();

				           log.info(gotStorename);

				            if (gotStorename.equalsIgnoreCase(StoreName)) {
				                NewIndex = j;

				                log.info("Here is new index value" + NewIndex);

				                break;
				            }
				        }
				    }
				} else {

				    WebElement Tabel = driver.findElement(By.xpath("(//tbody[@class=\"ui-table-tbody\"])[2]"));

				    List<WebElement> Rows = Tabel.findElements(By.tagName("tr"));

				    for (int j = 0; j < Rows.size(); j++) {
				        WebElement VariableRow = Rows.get(j);

				        List<WebElement> cells = VariableRow.findElements(By.tagName("td"));

				        String Variablename = cells.get(0).getText();

				        if (Variablename.trim().length() > 8) {
				            String gotStorename = Variablename.substring(11, Variablename.length()).trim();

				           log.info(gotStorename);

				            if (gotStorename.equalsIgnoreCase(StoreName)) {
				                NewIndex = j;

				               log.info("Here is new index value" + NewIndex);

				                break;
				            }
				        }
				    }
				}




				WebElement HeaderRow = driver.findElement(By.xpath("(//thead[@class=\"ui-table-thead\"])[2]"));

				List<WebElement> HeadersRowTotal = HeaderRow.findElements(By.tagName("tr"));

				List<WebElement> Headers = HeadersRowTotal.get(1).findElements(By.tagName("th"));

				//log.info(Headers.size())

				//Fetching variable values from tabel for all variables.
				for (int i = 0; i < Headers.size(); i++) {
					
					

					
				    WebElement HeaderNameElement = Headers.get(i);

				    justscrollToElement(HeaderNameElement);

				    //WebUI.scrollToElement(HeaderNameElement, i)
				    //WebUI.executeJavaScript("arguments[0].scrollIntoView(true);",HeaderNameElement)
				    String HeaderName = HeaderNameElement.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

				   log.info(HeaderName);

				    if (listForReportColumns.contains(HeaderName)) {
				    	
				    	WebElement Tabel2 = driver.findElement(By.xpath("(//tbody[@class=\"ui-table-tbody\"])[4]"));

		                List<WebElement> Rows2 = Tabel2.findElements(By.tagName("tr"));

		                WebElement RequiredRow = Rows2.get(NewIndex);

		                List<WebElement> cellsNew = RequiredRow.findElements(By.tagName("td"));
						
				        switch (HeaderName) {  
							
				            case "Total  Net Sales $":
				                log.info("FETCHING NET SALES TY VALUE FROM REPORT FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				                

				                WebElement NetsalesReport1 = cellsNew.get(index).findElement(By.tagName("div"));

				                justscrollToElement(NetsalesReport1);

				                //WebUI.scrollToElement(HeaderNameElement, i)
				                String NetsalesReport = NetsalesReport1.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(NetsalesReport);


				                NetSalesFromReport.put(fecthDates.get(DateIndex), NetsalesReport // AllVariablesFromReport.put("NetSales", NetSalesFromReport)
				                    );

				                break;
				            case "Sales Budget $":
				                log.info("FETCHING  Sales Budget $  VALUE FROM REPORT FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				               

				                WebElement NetsalesBudgetReport2 = cellsNew.get(index).findElement(By.tagName("div"));

				                justscrollToElement(NetsalesBudgetReport2);

				                //WebUI.scrollToElement(HeaderNameElement, i)
				                String NetsalesBudgetReport = NetsalesBudgetReport2.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(NetsalesBudgetReport);


				                BudgetedSalesFromReport.put(fecthDates.get(DateIndex), NetsalesBudgetReport);

				                break;
				            case "Total  Net Sales LY $":
				                log.info("FETCHING NET SALES LY VALUE FROM REPORT FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				                
				                WebElement NetsalesLYReport1 = cellsNew.get(index).findElement(By.tagName("div"));

				                justscrollToElement(NetsalesLYReport1);

				                //WebUI.scrollToElement(HeaderNameElement, i)
				                String NetsalesLYReport = NetsalesLYReport1.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(NetsalesLYReport);


				                NetSalesLastYearFromReport.put(fecthDates.get(DateIndex), NetsalesLYReport);

				                break;
				            case "Guest Count":
				                log.info("FETCHING GUEST COUNT  FROM CALENDAR FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				               
				                WebElement GuestCountReport1 = cellsNew.get(index).findElement(By.tagName("div"));

				                justscrollToElement(GuestCountReport1);


				                String GuestCountReport = GuestCountReport1.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(GuestCountReport);


				               GuestCountFromReport.put(fecthDates.get(DateIndex), GuestCountReport);

				                break;
				            case "Guest LY Count":
				                log.info("FETCHING GUEST COUNT LY FROM CALENDAR FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				              
				                WebElement GuestCountLYReport1 = cellsNew.get(index).findElement(By.tagName("div"));

				                justscrollToElement(GuestCountLYReport1);

				                String GuestCountLYReport = GuestCountLYReport1.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(GuestCountLYReport);


				                GuestCountLastYearFromReport.put(fecthDates.get(DateIndex), GuestCountLYReport);

				                break;
				            case "Avg Check $":
				                log.info("FETCHING AVG CHECK VALUE FROM REPORT FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				                

				                WebElement AvgCheckReport1 = cellsNew.get(index).findElement(By.tagName("div"));

						        justscrollToElement(AvgCheckReport1);


				                String AvgCheckReport = AvgCheckReport1.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(AvgCheckReport);


				                AvgCheckFromReport.put(fecthDates.get(DateIndex), AvgCheckReport);

				                break;
				            case "Avg Check LY $":
				                log.info("FETCHING AVG CHECK LY VALUE FROM REPORT FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				              
				                WebElement AvgCheckLYReport1 = cellsNew.get(index).findElement(By.tagName("div"));

						        justscrollToElement(AvgCheckLYReport1);


				                String AvgCheckLYReport = AvgCheckLYReport1.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(AvgCheckLYReport);


				                AvgCheckLastYearFromReport.put(fecthDates.get(DateIndex), AvgCheckLYReport);

				                break;
				            case "Labor Cost, $":
				                log.info("FETCHING LABOR COST TY VALUE FROM REPORT FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				                

				                WebElement LaborCostTYReport1 = cellsNew.get(index).findElement(By.tagName("div"));

						        justscrollToElement(LaborCostTYReport1);


				                String LaborCostTYReport = LaborCostTYReport1.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(LaborCostTYReport);


				                LaborFromReport.put(fecthDates.get(DateIndex), LaborCostTYReport);

				                break;
				            case "Labor Cost LY, $":
				                log.info("FETCHING LABOR COST LY VALUE FROM REPORT FOR DATE:" + (fecthDates.get(DateIndex)));

				                index = i;

				               

				                WebElement LaborCostLYReport1 = cellsNew.get(index).findElement(By.tagName("div"));

						        justscrollToElement(LaborCostLYReport1);


				                String LaborCostLYReport = LaborCostLYReport1.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll(
				                    "\n", " ");

				               log.info(LaborCostLYReport);


				                LaborLastYearFromReport.put(fecthDates.get(DateIndex), LaborCostLYReport);

				                break;
				            default:
				                break;
				        }
				    }
				    
				    int noofcolumn = sizeOfColumns;

				    //sizeOfColumns
				    if (((i + 1) % noofcolumn) == 0) {
				       log.info(("EXECUTION OF GETTING DATA FROM REPORT FOR DATE  +" + (fecthDates.get(DateIndex)) + "+ is completed successfully...."));

				        DateIndex++;
				    }
				
				}
				
			    	excelCreation();
				
				 log.info("\"******************EXECUTION FINSISHED AND NOW START COMPARING CALENDAR AND REPORT VALUE FOR SAME PERIOD*******************\"");

					CompareNetSales = (HashMap<String, String>) ValuesFromCalender.get("NetSalesValues");

					CompareNetSalesLY = (HashMap<String, String>) ValuesFromCalender.get("NetSalesLastYearValues");

					CompareSalesBudget = (HashMap<String, String>) ValuesFromCalender.get("BudgetedSalesValues");

					CompareGuestCount = (HashMap<String, String>) ValuesFromCalender.get("GuestCountValues");

					ComapreGuestCountLY = (HashMap<String, String>) ValuesFromCalender.get("GuestCountLastYearValues");

					CompareAvgTck = (HashMap<String, String>) ValuesFromCalender.get("AvgCheckValues");

					CompareAvgTckLY = (HashMap<String, String>) ValuesFromCalender.get("AvgCheckLastYearValues");	
				
					
					
					CompareValueswithCalendarDaily cwc=new CompareValueswithCalendarDaily();
					
					cwc.CompareWithCalender(CompareNetSales, NetSalesFromReport,"NetSales",DataGrain);

					cwc.CompareWithCalender(CompareNetSalesLY,NetSalesLastYearFromReport,"NetSales LY",DataGrain);

					cwc.CompareWithCalender(CompareGuestCount, GuestCountFromReport,"Guest Count",DataGrain);

					cwc.CompareWithCalender(ComapreGuestCountLY,GuestCountLastYearFromReport,"Guest Count LY",DataGrain);

					cwc.CompareWithCalender(CompareAvgTck, AvgCheckFromReport,"Average Check",DataGrain);

					cwc.CompareWithCalender(CompareAvgTckLY,AvgCheckLastYearFromReport,"Average Check LY",DataGrain);
					
					log.info("******************COMPARISION FINISHED*******************");
					
					
	
	}
	
	public void excelCreation() throws FileNotFoundException, IOException
	{
		
		
		ExcelCustom ec=new ExcelCustom();
		
		log.info("\"******************DELETE EXCEL FILE*******************\"");
		
		ec.deleteCalFile("Daily");
		
		CreateExcel ce=new CreateExcel();
		
		
		
		log.info("\"******************CREATING EXCEL FILE*******************\"");

		ce.createExcel("Daily");

		log.info("\"******************EXCEL CREATION DONE SUCCESSFULLY*******************\"");
			
		
	}

		
		   

				
				
		
	
		
	}

