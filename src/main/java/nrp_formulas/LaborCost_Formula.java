package nrp_formulas;

public class LaborCost_Formula {
	
	
	//Last Year Compare

		
		public double calculate_LaborCost_Percent(double ActualLaborCostTY, double NetSalesTY)

		{


			double dblLaborCostTY=NetSalesTY == 0 ? 0 :ActualLaborCostTY/NetSalesTY*100;
			//double LaborCostTYPercent= Math.round(dblLaborCostTY * 100) / 100;
			return ((long)(dblLaborCostTY * 1e2)) / 1e2;


		}

		
		public double calculate_LaborCost_LY__Percent(double ActualLaborCostLY, double NetSalesLY)

		{


			double dblLaborCostLY=NetSalesLY == 0 ? 0 :ActualLaborCostLY/NetSalesLY*100;
			//double LaborCostLYPercent= Math.round(dblLaborCostLY * 100) / 100;
			return ((long)(dblLaborCostLY * 1e2)) / 1e2;


		}




		
		public double calculate_LaborCost_Vs_LY__Dollar(double ActualLaborCostTY, double ActualLaborCostLY)

		{



			double dblLaborCostTY_vs_LY=ActualLaborCostTY-ActualLaborCostLY;
			//double LaborCostVsLY= Math.round(dblLaborCostTY_vs_LY * 100) / 100;
			return ((long)(dblLaborCostTY_vs_LY * 1e2)) / 1e2;


		}

		
		public double calculate_LaborCost_Vs_LY__PP(double ActualLaborCostTYPercent, double ActualLaborCostLYPercent)

		{


			double dblLaborCostTY_vs_LY_PP=ActualLaborCostTYPercent-ActualLaborCostLYPercent;
			//double LaborCostVsLY_PP= Math.round(dblLaborCostTY_vs_LY_PP * 100) / 100;
			return ((long)(dblLaborCostTY_vs_LY_PP * 1e2)) / 1e2;


		}

		//Last Week Compare

		
		public double calculate_LaborCostLW_Percent(double ActualLaborCostLW, double ActualNetSalesLW)

		{


			double dblLaborCostLWPercentage=ActualNetSalesLW == 0 ? 0 :(ActualLaborCostLW/ActualNetSalesLW)*100;
			//double LaborCostVsLYLW= Math.round(dblLaborCostLWPercentage * 100) / 100;
			return ((long)(dblLaborCostLWPercentage * 1e2)) / 1e2;


		}

		
		public double calculate_LaborCost_VS_LW_PP(double ActualLaborCostTWPercent, double ActualLaborCostLWPercent)

		{


			double dblLaborCostLWPP=ActualLaborCostTWPercent-ActualLaborCostLWPercent;
			//double LaborCostVsLYLW_PP= Math.round(dblLaborCostLWPP * 100) / 100;
			return ((long)(dblLaborCostLWPP * 1e2)) / 1e2;


		}

		
		public double calculate_LaborCost_VS_LW_Dollar(double ActualLaborCostTW, double ActualLaborCostLW)

		{


			double dblLaborCostVsLastWeek=ActualLaborCostTW-ActualLaborCostLW;
			//double LaborCostVsLW= Math.round(dblLaborCostVsLastWeek * 100) / 100;
			return ((long)(dblLaborCostVsLastWeek * 1e2)) / 1e2;


		}



		//Labor Budget Formulas


		
		public double calculate_LaborCostBudget_Percentage(double LaborCostBudgetBasedOnActualSales_dollar, double NetSalesTY)

		{


			double dblLaborCostBudgetPercent=NetSalesTY == 0 ? 0 :(LaborCostBudgetBasedOnActualSales_dollar/NetSalesTY)*100;
			//double LaborCostBudgetPercent= Math.round(dblLaborCostBudgetPercent * 100) / 100;
			return ((long)(dblLaborCostBudgetPercent * 1e2)) / 1e2;


		}





		
		public double calculate_LaborCostBudgetBasedOnActualSales_dollar(double ActualSalesTY, double LaborCostBudgetPercent)

		{


			double dblLaborCostBudgetForActualSales=(ActualSalesTY*LaborCostBudgetPercent)/100;
			//double LaborCostBudgetForActualSales= Math.round(dblLaborCostBudgetForActualSales * 100) / 100;
			return ((long)(dblLaborCostBudgetForActualSales * 1e2)) / 1e2;


		}

		
		public double calculate_LaborCostBudgetBasedOnBudgetedSales_dollar(double BudgetedSalesTY, double LaborCostBudgetPercent)

		{


			double dblLaborCostBudgetForBudgetedSales=(BudgetedSalesTY*LaborCostBudgetPercent)/100;
			//double LaborCostBudgetForBudgetedSales= Math.round(dblLaborCostBudgetForBudgetedSales * 100) / 100;
			return ((long)(dblLaborCostBudgetForBudgetedSales * 1e2)) / 1e2;


		}


		
		public double calculate_LaborCostVsBudget_PP(double LaborCostPercent, double LaborCostBudgetPercent)

		{


			double dblLaborCostVsBudget_PP=LaborCostPercent-LaborCostBudgetPercent;
			//double LaborCostVsBudget_PP= Math.round(dblLaborCostVsBudget_PP * 100) / 100;
			return ((long)(dblLaborCostVsBudget_PP * 1e2)) / 1e2;


		}


		
		public double calculate_LaborCostVsBudgetForActualSales_Dollar(double LaborCostDollar, double LaborCostBudgetForActualSales)

		{


			double dblLaborCostVsBudget_Dollar=LaborCostDollar-LaborCostBudgetForActualSales;
			//double LaborCostVsBudget_Dollar= Math.round(dblLaborCostVsBudget_Dollar * 100) / 100;
			return ((long)(dblLaborCostVsBudget_Dollar * 1e2)) / 1e2;


		}

		
		public double calculate_LaborCostVsBudgetForBudgetSales_Dollar(double LaborCostDollar, double LaborCostBudgetForBudgetSales)

		{


			double dblLaborCostVsBudgeted_Dollar=LaborCostDollar-LaborCostBudgetForBudgetSales;
			//double LaborCostVsBudgeted_Dollar= Math.round(dblLaborCostVsBudgeted_Dollar * 100) / 100;
			return ((long)(dblLaborCostVsBudgeted_Dollar * 1e2)) / 1e2;


		}



	
	

}
