package nrp_pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;

public class ListOfDataGroupPage_NRP extends BaseNRP {

	public ListOfDataGroupPage_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 100);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[contains(text(),'Add Variables')]")
	WebElement link_AddVariables;

	@FindBy(xpath = "//ng-select[@placeholder='Select data']")
	WebElement drp_dataGroup;

	@FindBy(xpath = "//button[text()='Add']")
	WebElement btn_AddColumns;

	@FindBy(xpath = "(//span[text()='Net Sales'])[1]")
	WebElement tpl_NetSales;

	@FindBy(xpath = "(//span[text()='Guest Count'])[1]")
	WebElement tpl_GuestCount;

	@FindBy(xpath = "(//span[text()='Average Check'])[1]")
	WebElement tpl_AverageCheck;

	@FindBy(xpath = "(//span[text()='Labor Cost'])[1]")
	WebElement tpl_LaborCost;

	@FindBy(xpath = "(//span[text()='Labor Hours'])[1]")
	WebElement tpl_LaborHours;

	@FindBy(xpath = "(//span[text()='Gross Sales'])[1]")
	WebElement tpl_GrossSales;

	@FindBy(xpath = "(//span[text()='Cost'])[1]")
	WebElement tpl_Cost;

	@FindBy(xpath = "(//span[text()='Prime Cost'])[1]")
	WebElement tpl_PrimeCost;

	@FindBy(xpath = "(//span[text()='Loss Prevention'])[1]")
	WebElement tpl_LossPrevention;

//****************Categories**************

	@FindBy(xpath = "//li[contains(text(),'Actual')]")
	WebElement ctg_Actual;

	@FindBy(xpath = "//li[contains(text(),'Last Year')]")
	WebElement ctg_LastYear;

	@FindBy(xpath = "//li[contains(text(),'Budget')]")
	WebElement ctg_Budget;
	
	
	///////***************************************
	
	//****************Unit & Comparison**************

		@FindBy(xpath = "(//span[text()='$'])[1]")
		WebElement ctg_Dollor; 

		@FindBy(xpath = "//span[contains(text(),'$ Variance to Actual')]")
		WebElement ctg_VarianceToActualDollor;

		@FindBy(xpath = "//span[contains(text(),'% Variance to Actual')]")
		WebElement ctg_VarianceToActualPercent;
		
		@FindBy(xpath = "(//span[text()='%'])[1]")
		WebElement ctg_Percentage;
		
		@FindBy(xpath = "(//span[text()='Count'])[1]")
		WebElement ctg_Count;
		
		@FindBy(xpath = "//span[contains(text(),'Count Variance to Actual')]")
		WebElement ctg_CountVariancetoActual;
		
		@FindBy(xpath = "//span[contains(text(),'% Variance to Actual')]")
		WebElement ctg_CountVariancetoActualPercent;
		
		@FindBy(xpath = "(//span[text()='Hours'])[1]")
		WebElement ctg_Hours;
		
		@FindBy(xpath = "//span[contains(text(),'Variance to Actual')]")
		WebElement ctg_VarianceToActual;
		
		@FindBy(xpath = "//span[contains(text(),'$ (actual sales)')]")
		WebElement ctg_DollorActualSales;
		
		@FindBy(xpath = "//span[contains(text(),'$ (budgeted sales)')]")
		WebElement ctg_DollorBudgetedSales;
		
		@FindBy(xpath = "//span[contains(text(),'% Variance to Budget'])[1]")
		WebElement ctg_VarianceToBudgetPercent;
		
		@FindBy(xpath = "(//span[text()='$ Variance to Budget (actual sales)'])[1]")
		WebElement ctg_VarianceToBudgetActualSales;
		
		@FindBy(xpath = "(//span[text()='% Variance to Budget (budgeted sales)'])[1]")
		WebElement ctg_VarianceToBudgetBudgetedSales;
		
		
		///////***************************************
	
	

	public void addcolumns() throws InterruptedException {

		scrollToElement(link_AddVariables);
		//waitForProgressbar();
		//waitUntilClickable(drp_dataGroup);
		drp_dataGroup.click();

	}
	
	public void clickOnDataGroup() throws InterruptedException {

		
		waitUntilClickable(drp_dataGroup);
		drp_dataGroup.click();

	}

	//*************Daily*****************
	
	public void add_Daily_NetSales() throws InterruptedException {

		addcolumns();
		log.info("Data group drpdown opened");
		waitUntilClickable(tpl_NetSales);
		scrollToElement(tpl_NetSales);
		log.info("Net Sales selected from dropdown");
		waitForProgressbarForDataGroup();
		//Thread.sleep(1000);
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		//scrollToElement(btn_AddColumns);

		/*if (!(driver.findElements(By.xpath("//span[text()='Add Columns']")).size() != 0)) {

			scrollToElement(btn_AddColumns);
		}*/
		
		log.info("All Net Sales columns added");


	}
	public void add_Daily_GuestCount() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_GuestCount);
		scrollToElement(tpl_GuestCount);
		waitForProgressbarForDataGroup();
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Count);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Count);
		scrollToElement(ctg_CountVariancetoActual);
		scrollToElement(ctg_CountVariancetoActualPercent);
		//scrollToElement(btn_AddColumns);
		log.info("All guest count columns added");


		
	}
	
	public void add_Daily_AverageCheck() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_AverageCheck);
		scrollToElement(tpl_AverageCheck);
		waitForProgressbarForDataGroup();
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		//scrollToElement(btn_AddColumns);
		log.info("All average check columns added");


		
	}
	
	public void add_Daily_LaborCost() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_LaborCost);
		scrollToElement(tpl_LaborCost);
		waitForProgressbarForDataGroup();
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		//scrollToElement(btn_AddColumns);
		log.info("All labor cost columns added");


		
	}
	
	public void add_Daily_LaborHours() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_LaborHours);
		scrollToElement(tpl_LaborHours);
		waitForProgressbarForDataGroup();
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Hours);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Hours);
		scrollToElement(ctg_VarianceToActual);
		//scrollToElement(btn_AddColumns);
		log.info("All labor Hours columns added");


		
	}
	
	public void add_Daily_Cost() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_Cost);
		scrollToElement(tpl_Cost);
		waitForProgressbarForDataGroup();
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_LastYear);
		//scrollToElement(btn_AddColumns);
		log.info("All  cost columns added");


		
	}
	
	public void add_Daily_PrimeCost() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_PrimeCost);
		scrollToElement(tpl_PrimeCost);
		waitForProgressbarForDataGroup();
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		//scrollToElement(btn_AddColumns);
		log.info("All prime cost columns added");


		
	}
	
	public void add_Daily_GrossSales() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_GrossSales);
		scrollToElement(tpl_GrossSales);
		waitForProgressbarForDataGroup();
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		scrollToElement(btn_AddColumns);
		log.info("All gross sales columns added");


		
	}
	
	//*************Weekly*****************
	
	public void add_Weekly_NetSales() throws InterruptedException {

		addcolumns();
		log.info("Data group drpdown opened");
		waitUntilClickable(tpl_NetSales);
		scrollToElement(tpl_NetSales);
		log.info("Net Sales selected from dropdown");
		//Thread.sleep(1000);
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		scrollToElement(ctg_Budget);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		

		//scrollToElement(btn_AddColumns);

		/*if (!(driver.findElements(By.xpath("//span[text()='Add Columns']")).size() != 0)) {

			scrollToElement(btn_AddColumns);
		}*/
		log.info("All guest count columns added");

	}
	public void add_Weekly_GuestCount() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_GuestCount);
		scrollToElement(tpl_GuestCount);
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Count);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Count);
		scrollToElement(ctg_CountVariancetoActual);
		scrollToElement(ctg_CountVariancetoActualPercent);
		//scrollToElement(btn_AddColumns);
		log.info("All guest count columns added");


		
	}
	
	public void add_Weekly_AverageCheck() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_AverageCheck);
		scrollToElement(tpl_AverageCheck);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		//scrollToElement(btn_AddColumns);
		log.info("All average check columns added");


		
	}
	
	public void add_Weekly_LaborCost() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_LaborCost);
		scrollToElement(tpl_LaborCost);
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		scrollToElement(ctg_Budget);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_DollorActualSales);
		scrollToElement(ctg_DollorBudgetedSales);
		scrollToElement(ctg_VarianceToBudgetPercent);
		scrollToElement(ctg_VarianceToBudgetActualSales);
		scrollToElement(ctg_VarianceToBudgetBudgetedSales);
		
		//scrollToElement(btn_AddColumns);
		log.info("All labor cost columns added");


		
	}
	
	public void add_Weekly_LaborHours() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_LaborHours);
		scrollToElement(tpl_LaborHours);
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Hours);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Hours);
		scrollToElement(ctg_VarianceToActual);
		//scrollToElement(btn_AddColumns);
		log.info("All labor Hours columns added");


		
	}
	
	public void add_Weekly_Cost() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_Cost);
		scrollToElement(tpl_Cost);
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_LastYear);
		ctg_Budget.click();

		//scrollToElement(btn_AddColumns);
		log.info("All  cost columns added");


		
	}
	
	public void add_Weekly_PrimeCost() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_PrimeCost);
		scrollToElement(tpl_PrimeCost);
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		scrollToElement(ctg_Budget);
		scrollToElement(ctg_Percentage);
		scrollToElement(ctg_DollorActualSales);
		scrollToElement(ctg_DollorBudgetedSales);
		scrollToElement(ctg_VarianceToBudgetPercent);
		scrollToElement(ctg_VarianceToBudgetActualSales);
		scrollToElement(ctg_VarianceToBudgetBudgetedSales);
		
		//scrollToElement(btn_AddColumns);
		log.info("All prime cost columns added");


		
	}
	
	public void add_Weekly_GrossSales() throws InterruptedException {

		clickOnDataGroup();
		waitUntilClickable(tpl_GrossSales);
		scrollToElement(tpl_GrossSales);
		waitUntilClickable(ctg_Actual);
		scrollToElement(ctg_Actual);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_LastYear);
		scrollToElement(ctg_Dollor);
		scrollToElement(ctg_VarianceToActualDollor);
		scrollToElement(ctg_VarianceToActualPercent);
		scrollToElement(btn_AddColumns);
		log.info("All gross sales columns added");


		
	}
	
	
	
	
	

}