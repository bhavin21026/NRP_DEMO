package nrp_CustomKeywords;

import java.io.IOException;

import nrp_base.BaseNRP;
import nrp_utilities.WriteExcelDailyFormula;

public class VerifyingDailyFormulas extends BaseNRP {

	WriteExcelDailyFormula we=new WriteExcelDailyFormula();
	String[] writeData;
	
	public void toVerifyActualvsExpectedValues(double NetSalesVSLY,double NetSalesvsLY$,String store,String variable,String DataGrain) throws IOException {

		int Decison1=Double.compare(NetSalesvsLY$, NetSalesVSLY);


		if(Decison1==0) {
			if(store.equalsIgnoreCase("Single")) {
				
				writeData=new String[] {variable,String.valueOf(NetSalesVSLY),String.valueOf(NetSalesvsLY$)};
				we.writeExcelPassFormulaCheck(writeData,DataGrain);
			}
			else {
				writeData=new String[] {variable,String.valueOf(NetSalesVSLY),String.valueOf(NetSalesvsLY$)};
				we.writeExcelAllStorePass(writeData,DataGrain);
			}
			log.info(variable+" "+" value found On Report is"+  NetSalesVSLY  +"And Value derived using formula is"+ NetSalesvsLY$);
			
		}

		else {
			if(store.equalsIgnoreCase("Single")) {
				
				writeData=new String[] {variable,String.valueOf(NetSalesVSLY),String.valueOf(NetSalesvsLY$)};
				we.writeExcelDailyFormulaCheck(writeData,DataGrain);
			
			}
			else {
				
				writeData=new String[] {variable,String.valueOf(NetSalesVSLY),String.valueOf(NetSalesvsLY$)};
				we.writeExcelAllStoreFail(writeData,DataGrain);
			}


			log.info(variable+" "+" value found On Report is"+  NetSalesVSLY  +"And Value derived using formula is"+ NetSalesvsLY$);
			
		}
	}

	
	
	
	
}
